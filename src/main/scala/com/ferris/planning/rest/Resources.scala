package com.ferris.planning.rest

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
      backlogItemType: String
    )

    case class BacklogItemUpdate (
      summary: Option[String],
      description: Option[String],
      backlogItemType: Option[String]
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
      status: String,
      graduation: String
    )

    case class GoalUpdate (
      themeId: Option[UUID],
      backlogItems: Option[Seq[UUID]],
      summary: Option[String],
      description: Option[String],
      level: Option[Int],
      priority: Option[Boolean],
      status: Option[String],
      graduation: Option[String]
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
      status: String,
      `type`: String
    )

    case class WeaveUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      status: Option[String],
      `type`: Option[String]
    )

    case class LaserDonutCreation (
      goalId: UUID,
      summary: String,
      description: String,
      milestone: String,
      order: Int,
      status: String,
      `type`: String
    )

    case class LaserDonutUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      milestone: Option[String],
      order: Option[Int],
      status: Option[String],
      `type`: Option[String]
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
      status: String,
      `type`: String
    )

    case class HobbyUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      frequency: Option[String],
      status: Option[String],
      `type`: Option[String]
    )
  }

  object Out {
    case class MessageView (
      sender: String,
      content: String
    )


  }
}
