package com.ferris.planning.service.exceptions

import java.util.UUID

object Exceptions {

  sealed abstract class PlanningServiceException(message: String = "oops!") extends Exception(message)

  case class MessageNotFoundException(message: String = "message not found") extends PlanningServiceException(message)

  case class BacklogItemNotFoundException(message: String = "backlog-item not found") extends PlanningServiceException(message)

  case class EpochNotFoundException(message: String = "epoch not found") extends PlanningServiceException(message)

  case class YearNotFoundException(message: String = "year not found") extends PlanningServiceException(message)

  case class ThemeNotFoundException(message: String = "theme not found") extends PlanningServiceException(message)

  case class GoalNotFoundException(message: String = "goal not found") extends PlanningServiceException(message)

  case class ThreadNotFoundException(message: String = "thread not found") extends PlanningServiceException(message)

  case class WeaveNotFoundException(message: String = "weave not found") extends PlanningServiceException(message)

  case class LaserDonutNotFoundException(message: String = "laser-donut not found") extends PlanningServiceException(message)

  case class PortionNotFoundException(message: String = "portion not found") extends PlanningServiceException(message)

  case class TodoNotFoundException(message: String = "to-do not found") extends PlanningServiceException(message)

  case class HobbyNotFoundException(message: String = "hobby not found") extends PlanningServiceException(message)

  case class InvalidPortionsUpdateException(laserDonutId: UUID, portionIds: List[UUID]) extends PlanningServiceException() {
    override def getMessage: String = s"the portions (${portionIds.mkString(", ")}) do not belong to the laser-donut $laserDonutId"
  }

  case class InvalidTodosUpdateException(portionId: UUID, todoIds: List[UUID]) extends PlanningServiceException() {
    override def getMessage: String = s"the todos (${todoIds.mkString(", ")}) do not belong to the portion $portionId"
  }
}
