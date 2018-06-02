package com.ferris.planning.scheduler

import java.time.{LocalDateTime, ZoneId}

import com.ferris.planning.config.{DefaultPlanningServiceConfig, PlanningServiceConfig}
import com.ferris.planning.model.Model._
import com.ferris.planning.model.Model.Statuses._

import scala.util.Random

trait LifeSchedulerComponent {

  def lifeScheduler: LifeScheduler

  trait LifeScheduler {
    def refreshPyramid(laserDonuts: Seq[ScheduledLaserDonut]): ScheduledPyramid
    def decideNextPortion(portions: Seq[ScheduledPortion]): Option[ScheduledPortion]
  }
}

trait DefaultLifeSchedulerComponent extends LifeSchedulerComponent {

  override def lifeScheduler = new DefaultLifeScheduler(DefaultPlanningServiceConfig.apply)

  class DefaultLifeScheduler(config: PlanningServiceConfig) extends LifeScheduler {

    import LifeSchedulerOperations._

    private type Criteria = Seq[ScheduledLaserDonut] => Seq[ScheduledLaserDonut]

    override def refreshPyramid(laserDonuts: Seq[ScheduledLaserDonut]): ScheduledPyramid = {
      def getPortions(laserDonut: Option[ScheduledLaserDonut]): Seq[ScheduledPortion] = {
        laserDonut.map(_.portions.sortBy(_.order)).getOrElse(Nil)
      }

      def getCurrentLaserDonut(laserDonuts: Seq[ScheduledLaserDonut]): Option[ScheduledLaserDonut] = {
        laserDonuts.partition(_.status == Planned) match {
          case (Nil, Nil) => None
          case (Nil, inProgress) => chooseRandom(applyCriteria(inProgress, Seq(timeRule, progressRule)))
          case (planned, _) => chooseRandom(planned)
        }
      }

      laserDonuts match {
        case Nil => ScheduledPyramid(Nil, None, None)
        case _ =>
          val (topTier, bottomTiers) = laserDonuts.partition(_.tier == 1)
          val (completed, leftOvers) = topTier.partition(_.status == Complete)
          val shiftSize = completed match {
            case Nil if acceptableProgress(topTier) => 1
            case _ => completed.size
          }
          val shifted = leftOvers ++ shift(bottomTiers, shiftSize)
          val (newTopTier, newBottomTiers) = shifted.partition(_.tier == 1)
          val currentLaserDonut = getCurrentLaserDonut(newTopTier)
          val currentPortion = decideNextPortion(getPortions(currentLaserDonut))
          ScheduledPyramid(newTopTier ++ newBottomTiers, currentLaserDonut, currentPortion)
      }
    }

    override def decideNextPortion(portions: Seq[ScheduledPortion]): Option[ScheduledPortion] = {
      val plannedPortions = portions.filter(_.status == Planned)
      val portionsInProgress = portions.filter(_.status == InProgress)
      (plannedPortions, portionsInProgress) match {
        case (Nil, Nil) => None
        case (_, Nil) => plannedPortions.headOption
        case (_, _) => portionsInProgress.headOption
      }
    }

    private def timeRule(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
      laserDonuts.groupBy(donut => System.currentTimeMillis() - donut.lastPerformed.head.toLong)
        .toSeq.sortBy(_._1).reverse.map(_._2).headOption.getOrElse(Nil)
    }

    private def progressRule(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
      laserDonuts.groupBy(progressPercentage).toSeq.sortBy(_._1).map(_._2).headOption.getOrElse(Nil)
    }

    private def applyCriteria(laserDonuts: Seq[ScheduledLaserDonut], rules: Seq[Criteria]): Seq[ScheduledLaserDonut] = {
      rules.headOption match {
        case None => laserDonuts
        case Some(rule) => rule(laserDonuts) match {
          case Nil => laserDonuts
          case leftOvers => applyCriteria(leftOvers, rules.tail)
        }
      }
    }

    private def chooseRandom[T](list: Seq[T]): Option[T] = {
      list match {
        case Nil => None
        case head :: Nil => Some(head)
        case _ => Some(list(Random.nextInt(list.length)))
      }
    }

    private def shift(laserDonuts: Seq[ScheduledLaserDonut], steps: Int): Seq[ScheduledLaserDonut] = {
      require(steps >= 0)

      def shiftByOne(list: Seq[ScheduledLaserDonut]) = {
        list.map(donut => donut.copy(tier = donut.tier - 1))
      }

      steps match {
        case 0 => laserDonuts
        case _ => laserDonuts match {
          case smallList if smallList.size <= steps => shiftByOne(smallList)
          case bigList =>
            val (before, after) = bigList.splitAt(steps)
            shiftByOne(before) ++ shift(after, steps)
        }
      }
    }

    private def acceptableProgress(laserDonuts: Seq[ScheduledLaserDonut]): Boolean = {
      laserDonuts.forall(progressPercentage(_) > config.acceptableProgress)
    }

    private def progressPercentage(laserDonut: ScheduledLaserDonut): Int = {
      val all = laserDonut.portions.flatMap(_.todos)
      val total = all.size
      val completed = all.count(_.status == Statuses.Complete)
      (completed / total) * 100
    }
  }

  private object LifeSchedulerOperations {
    implicit class LocalDateTimeOps(time: LocalDateTime) {
      def toLong: Long = {
        time.atZone(ZoneId.systemDefault()).toInstant.toEpochMilli
      }
    }

  }

  private object StatusOrdering extends Ordering[Status] {
    override def compare(x: Status, y: Status): Int = (x, y) match {
      case (Planned, Planned) | (InProgress, InProgress) | (Complete, Complete) => 0
      case (Planned, InProgress) | (InProgress, Complete) | (Planned, Complete) => -1
      case (InProgress, Planned) | (Complete, InProgress) | (Complete, Planned) => 1
    }
  }
}
