package com.ferris.planning.rest

import java.util.UUID

import akka.http.scaladsl.model.DateTime

object Resources {

  object In {
    case class MessageCreation(sender: String, content: String)
    case class MessageUpdate(sender: Option[String], content: Option[String])

    case class BacklogItemCreation(summary: String, description: String, backlogItemType: String)
    case class BacklogItemUpdate(summary: Option[String], description: Option[String], backlogItemType: Option[String])

    case class EpochCreation(name: String, totem: String, question: String)
    case class EpochUpdate(name: Option[String], totem: Option[String], question: Option[String])

    case class YearCreation(epochId: UUID, startDate: DateTime, finishDate: DateTime)
    case class YearUpdate(epochId: Option[UUID], startDate: Option[DateTime], finishDate: Option[DateTime])

    case class ThemeCreation(yearId: UUID, name: String)
    case class ThemeUpdate(yearId: Option[UUID], name: Option[String])

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
  }

  object Out {
    case class MessageView(sender: String, content: String)
  }
}
