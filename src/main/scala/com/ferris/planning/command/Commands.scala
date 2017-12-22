package com.ferris.planning.command

import java.util.UUID

import akka.http.scaladsl.model.DateTime
import com.ferris.planning.model.Model._

object Commands {

  case class CreateMessage(sender: String, content: String)

  case class UpdateMessage(sender: Option[String], content: Option[String])

  case class CreateBacklogItem (
    summary: String,
    description: String,
    `type`: BacklogItemTypes.BacklogItemType
  )

  case class UpdateBacklogItem (
    summary: Option[String],
    description: Option[String],
    `type`: Option[BacklogItemTypes.BacklogItemType]
  )

  case class CreateEpoch (
    name: String,
    totem: String,
    question: String
  )

  case class UpdateEpoch (
    name: Option[String],
    totem: Option[String],
    question: Option[String]
  )

  case class CreateYear (
    epochId: UUID,
    startDate: DateTime,
    finishDate: DateTime
  )

  case class UpdateYear (
    epochId: Option[UUID],
    startDate: Option[DateTime],
    finishDate: Option[DateTime]
  )

  case class CreateTheme (
    yearId: UUID,
    name: String
  )

  case class UpdateTheme (
    yearId: Option[UUID],
    name: Option[String]
  )

  case class CreateGoal (
    themeId: UUID,
    backlogItems: Seq[UUID],
    summary: String,
    description: String,
    level: Int,
    priority: Boolean,
    status: GoalStatuses.GoalStatus,
    graduation: GraduationTypes.GraduationType
  )

  case class UpdateGoal (
    themeId: Option[UUID],
    backlogItems: Option[Seq[UUID]],
    summary: Option[String],
    description: Option[String],
    level: Option[Int],
    priority: Option[Boolean],
    status: Option[GoalStatuses.GoalStatus],
    graduation: Option[GraduationTypes.GraduationType]
  )

  case class CreateThread (
    goalId: Option[UUID],
    summary: String,
    description: String,
    status: Statuses.Status
  )

  case class UpdateThread (
    goalId: Option[UUID],
    summary: Option[String],
    description: Option[String],
    status: Option[Statuses.Status]
  )

  case class CreateWeave (
    goalId: Option[UUID],
    summary: String,
    description: String,
    status: Statuses.Status,
    `type`: WeaveTypes.WeaveType
  )

  case class UpdateWeave (
    goalId: Option[UUID],
    summary: Option[String],
    description: Option[String],
    status: Option[Statuses.Status],
    `type`: Option[WeaveTypes.WeaveType]
  )

  case class CreateLaserDonut (
    goalId: UUID,
    summary: String,
    description: String,
    milestone: String,
    order: Int,
    status: Statuses.Status,
    `type`: DonutTypes.DonutType
  )

  case class UpdateLaserDonut (
    goalId: Option[UUID],
    summary: Option[String],
    description: Option[String],
    milestone: Option[String],
    order: Option[Int],
    status: Option[Statuses.Status],
    `type`: Option[DonutTypes.DonutType]
  )

  case class CreatePortion (
    laserDonutId: UUID,
    summary: String,
    order: Int,
    status: Statuses.Status
  )

  case class UpdatePortion (
    laserDonutId: Option[UUID],
    summary: Option[String],
    order: Option[Int],
    status: Option[Statuses.Status]
  )

  case class CreateTodo (
    portionId: UUID,
    description: String,
    order: Int,
    status: Statuses.Status
  )

  case class UpdateTodo (
    portionId: Option[UUID],
    description: Option[String],
    order: Option[Int],
    status: Option[Statuses.Status]
  )

  case class CreateHobby (
    goalId: Option[UUID],
    summary: String,
    description: String,
    frequency: HobbyFrequencies.HobbyFrequency,
    status: Statuses.Status,
    `type`: HobbyTypes.HobbyType
  )

  case class UpdateHobby (
    goalId: Option[UUID],
    summary: Option[String],
    description: Option[String],
    frequency: Option[HobbyFrequencies.HobbyFrequency],
    status: Option[Statuses.Status],
    `type`: Option[HobbyTypes.HobbyType]
  )
}
