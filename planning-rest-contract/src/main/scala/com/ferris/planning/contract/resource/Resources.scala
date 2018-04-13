package com.ferris.planning.contract.resource

import java.util.UUID

import akka.http.scaladsl.model.DateTime

object Resources {

  object In {

    case class MessageCreation (
      sender: String,
      content: String
    )

    case class MessageUpdate (
      sender: Option[String],
      content: Option[String]
    )

    case class BacklogItemCreation (
      summary: String,
      description: String,
      `type`: String
    )

    case class BacklogItemUpdate (
      summary: Option[String],
      description: Option[String],
      `type`: Option[String]
    )

    case class EpochCreation (
      name: String,
      totem: String,
      question: String
    )

    case class EpochUpdate (
      name: Option[String],
      totem: Option[String],
      question: Option[String]
    )

    case class YearCreation (
      epochId: UUID,
      startDate: DateTime,
      finishDate: DateTime
    )

    case class YearUpdate (
      epochId: Option[UUID],
      startDate: Option[DateTime],
      finishDate: Option[DateTime]
    )

    case class ThemeCreation (
      yearId: UUID,
      name: String
    )

    case class ThemeUpdate(
      yearId: Option[UUID],
      name: Option[String]
    )

    case class GoalCreation (
      themeId: UUID,
      backlogItems: Seq[UUID],
      summary: String,
      description: String,
      level: Int,
      priority: Boolean,
      graduation: String,
      status: String
    )

    case class GoalUpdate (
      themeId: Option[UUID],
      backlogItems: Option[Seq[UUID]],
      summary: Option[String],
      description: Option[String],
      level: Option[Int],
      priority: Option[Boolean],
      graduation: Option[String],
      status: Option[String]
    )

    case class ThreadCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      status: String
    )

    case class ThreadUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      status: Option[String]
    )

    case class WeaveCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      `type`: String,
      status: String
    )

    case class WeaveUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      `type`: Option[String],
      status: Option[String]
    )

    case class LaserDonutCreation (
      goalId: UUID,
      summary: String,
      description: String,
      milestone: String,
      order: Int,
      `type`: String,
      status: String
    )

    case class LaserDonutUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      milestone: Option[String],
      order: Option[Int],
      `type`: Option[String],
      status: Option[String]
    )

    case class PortionCreation (
      laserDonutId: UUID,
      summary: String,
      order: Int,
      status: String
    )

    case class PortionUpdate (
      laserDonutId: Option[UUID],
      summary: Option[String],
      order: Option[Int],
      status: Option[String]
    )

    case class TodoCreation (
      portionId: UUID,
      description: String,
      order: Int,
      status: String
    )

    case class TodoUpdate (
      portionId: Option[UUID],
      description: Option[String],
      order: Option[Int],
      status: Option[String]
    )

    case class HobbyCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      frequency: String,
      `type`: String,
      status: String
    )

    case class HobbyUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      frequency: Option[String],
      `type`: Option[String],
      status: Option[String]
    )
  }

  object Out {

    case class MessageView (
      uuid: UUID,
      sender: String,
      content: String
    )

    case class BacklogItemView (
      uuid: UUID,
      summary: String,
      description: String,
      `type`: String
    )

    case class EpochView (
      uuid: UUID,
      name: String,
      totem: String,
      question: String
    )

    case class YearView (
      uuid: UUID,
      epochId: UUID,
      startDate: DateTime,
      finishDate: DateTime
    )

    case class ThemeView (
      uuid: UUID,
      yearId: UUID,
      name: String
    )

    case class GoalView (
      uuid: UUID,
      themeId: UUID,
      backlogItems: Seq[UUID],
      summary: String,
      description: String,
      level: Int,
      priority: Boolean,
      graduation: String,
      status: String
    )

    case class ThreadView (
      uuid: UUID,
      goalId: Option[UUID],
      summary: String,
      description: String,
      status: String
    )

    case class WeaveView (
      uuid: UUID,
      goalId: Option[UUID],
      summary: String,
      description: String,
      `type`: String,
      status: String
    )

    case class LaserDonutView (
      uuid: UUID,
      goalId: UUID,
      summary: String,
      description: String,
      milestone: String,
      order: Int,
      `type`: String,
      status: String
    )

    case class PortionView (
      uuid: UUID,
      laserDonutId: UUID,
      summary: String,
      order: Int,
      status: String
    )

    case class TodoView (
      uuid: UUID,
      portionId: UUID,
      description: String,
      order: Int,
      status: String
    )

    case class HobbyView (
      uuid: UUID,
      goalId: Option[UUID],
      summary: String,
      description: String,
      frequency: String,
      `type`: String,
      status: String
    )
  }
}
