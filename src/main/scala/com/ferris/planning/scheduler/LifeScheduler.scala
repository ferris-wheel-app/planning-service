package com.ferris.planning.scheduler

import scala.util.Random

import com.ferris.planning.model.Model._

trait LifeScheduler {

  def schedule(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
    val topTier = laserDonuts.filter(_.tier == 1)
    val incompleteItems = incomplete(laserDonuts)
    val nextLaserDonut = planned(incompleteItems) match {
      case Nil => {}
      case notStartedItems => chooseRandom(notStartedItems)
    }
  }

  private def incomplete(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
    laserDonuts.filter(_.status != Statuses.Complete)
  }

  private def planned(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
    laserDonuts.filter(_.status == Statuses.Planned)
  }

  def chooseRandom[T](list: Seq[T]): Option[T] = {
    if (list.nonEmpty) Some(list(Random.nextInt(list.length)))
    else None
  }
}

object LaserDonutOrdering extends Ordering[ScheduledLaserDonut] {
  override def compare(x: ScheduledLaserDonut, y: ScheduledLaserDonut) = {

  }
}
