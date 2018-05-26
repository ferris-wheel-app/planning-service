package com.ferris.planning.scheduler

import java.time.LocalDateTime

import scala.util.Random
import com.ferris.planning.model.Model._

trait LifeConveyor {

  def refresh(laserDonuts: Seq[ScheduledLaserDonut], lastUpdate: LocalDateTime): Seq[ScheduledLaserDonut] = {
    val (completed, leftOvers) = laserDonuts.partition(_.status == Statuses.Complete)
    val (planned, inProgress) = leftOvers.partition(_.status == Statuses.Planned)
    val
  }

  private def sortByFurthest(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {

  }

  private def shift(laserDonuts: Seq[ScheduledLaserDonut], steps: Int): Seq[ScheduledLaserDonut] = {
    ???
  }
}
