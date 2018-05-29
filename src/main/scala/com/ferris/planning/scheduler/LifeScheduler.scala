package com.ferris.planning.scheduler

import java.time.{LocalDateTime, ZoneId}

import scala.util.Random
import com.ferris.planning.model.Model._

trait LifeScheduler {

  import LifeSchedulerOperations._

  type Criteria = Seq[ScheduledLaserDonut] => Seq[ScheduledLaserDonut]

  def refresh(laserDonuts: Seq[ScheduledLaserDonut]): ScheduledPyramid = {
    laserDonuts match {
      case Nil => ScheduledPyramid(Nil, None, None)
      case _ =>
        val (topTier, bottomTiers) =
        val (completed, leftOvers) = laserDonuts.partition(_.status == Statuses.Complete)
        val currentLaserDonut = leftOvers.partition(_.status == Statuses.Planned) match {
          case (Nil, Nil) => None
          case (Nil, inProgress) => applyCriteria(inProgress, Seq(timeRule, progressRule))
          case (planned, inProgress) => chooseRandom(planned)
        }
        ScheduledPyramid(Nil, currentLaserDonut, None)
    }

  }

  def timeRule(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
    laserDonuts.groupBy(donut => System.currentTimeMillis() - donut.lastPerformed.head.toLong)
      .toSeq.sortBy(_._1).reverse.map(_._2).headOption.getOrElse(Nil)
  }

  def progressRule(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
    laserDonuts.groupBy { donut =>
      val all = donut.portions.flatMap(_.todos)
      val total = all.size
      val completed = all.count(_.status == Statuses.Complete)
      (completed / total) * 100
    }.toSeq.sortBy(_._1).map(_._2).headOption.getOrElse(Nil)
  }

  def applyCriteria(laserDonuts: Seq[ScheduledLaserDonut], rules: Seq[Criteria]): Seq[ScheduledLaserDonut] = {
    rules.headOption match {
      case None => laserDonuts
      case Some(rule) => rule(laserDonuts) match {
        case Nil => laserDonuts
        case leftOvers => applyCriteria(leftOvers, rules.tail)
      }
    }
  }

  def chooseRandom[T](list: Seq[T]): Option[T] = {
    list match {
      case Nil => None
      case head :: Nil => Some(head)
      case _ => Some(list(Random.nextInt(list.length)))
    }
  }
}

object LifeSchedulerOperations {
  implicit class LocalDateTimeOps(time: LocalDateTime) {
    def toLong: Long = {
      time.atZone(ZoneId.systemDefault()).toInstant.toEpochMilli
    }
  }
}
