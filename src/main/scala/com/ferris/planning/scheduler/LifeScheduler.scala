package com.ferris.planning.scheduler

import java.time.LocalDateTime
import java.util.UUID

import com.ferris.planning.model.Model.Statuses

case class ScheduledLaserDonut(
  uuid: UUID,
  portions: Seq[ScheduledPortion],
  status: Statuses.Status,
  lastPerformed: Option[LocalDateTime]
)

case class ScheduledPortion(
  uuid: UUID,
  todos: Seq[ScheduledTodo]
)

case class ScheduledTodo(
  uuid: UUID,
  status: Statuses.Status
)

trait LifeScheduler {

  def schedule(laserDonuts: Seq[ScheduledLaserDonut]): Seq[ScheduledLaserDonut] = {
    ???
  }
}

object LaserDonutOrdering extends Ordering[ScheduledLaserDonut] {
  override def compare(x: ScheduledLaserDonut, y: ScheduledLaserDonut) = {

  }
}
