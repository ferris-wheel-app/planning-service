package com.ferris.planning.scheduler

import com.ferris.planning.model.Model._

trait LifeScheduler {

  def schedule(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
    ???
  }
}

object LaserDonutOrdering extends Ordering[ScheduledLaserDonut] {
  override def compare(x: ScheduledLaserDonut, y: ScheduledLaserDonut) = {

  }
}
