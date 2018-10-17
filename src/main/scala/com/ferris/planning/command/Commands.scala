package com.ferris.planning.command

import java.time.LocalDate
import java.util.UUID

import com.ferris.planning.model.Model._

object Commands {

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
    startDate: LocalDate
  )

  case class UpdateYear (
    epochId: Option[UUID],
    startDate: Option[LocalDate]
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
    graduation: GraduationTypes.GraduationType,
    status: GoalStatuses.GoalStatus
  )

  case class UpdateGoal (
    themeId: Option[UUID],
    backlogItems: Option[Seq[UUID]],
    summary: Option[String],
    description: Option[String],
    graduation: Option[GraduationTypes.GraduationType],
    status: Option[GoalStatuses.GoalStatus]
  )

  case class CreateThread (
    goalId: Option[UUID],
    summary: String,
    description: String,
    performance: ThreadPerformances.ThreadPerformance
  )

  case class UpdateThread (
    goalId: Option[UUID],
    summary: Option[String],
    description: Option[String],
    performance: Option[ThreadPerformances.ThreadPerformance]
  )

  case class CreateWeave (
    goalId: Option[UUID],
    summary: String,
    description: String,
    `type`: WeaveTypes.WeaveType,
    status: Statuses.Status
  )

  case class UpdateWeave (
    goalId: Option[UUID],
    summary: Option[String],
    description: Option[String],
    `type`: Option[WeaveTypes.WeaveType],
    status: Option[Statuses.Status]
  )

  case class CreateLaserDonut (
    goalId: UUID,
    summary: String,
    description: String,
    milestone: String,
    `type`: DonutTypes.DonutType,
    status: Statuses.Status
  )

  case class UpdateLaserDonut (
    goalId: Option[UUID],
    summary: Option[String],
    description: Option[String],
    milestone: Option[String],
    `type`: Option[DonutTypes.DonutType],
    status: Option[Statuses.Status]
  )

  case class CreatePortion (
    laserDonutId: UUID,
    summary: String,
    status: Statuses.Status
  )

  case class UpdatePortion (
    laserDonutId: Option[UUID],
    summary: Option[String],
    status: Option[Statuses.Status]
  )

  case class CreateTodo (
    parentId: UUID,
    description: String
  )

  case class UpdateTodo (
    parentId: Option[UUID],
    description: Option[String],
    isDone: Option[Boolean]
  )

  case class CreateHobby (
    goalId: Option[UUID],
    summary: String,
    description: String,
    frequency: HobbyFrequencies.HobbyFrequency,
    `type`: HobbyTypes.HobbyType
  )

  case class UpdateHobby (
    goalId: Option[UUID],
    summary: Option[String],
    description: Option[String],
    frequency: Option[HobbyFrequencies.HobbyFrequency],
    `type`: Option[HobbyTypes.HobbyType]
  )

  case class UpdateList (
    reordered: Seq[UUID]
  )

  case class UpsertTier(
    laserDonuts: Seq[UUID]
  )

  case class UpsertPyramidOfImportance(
    tiers: Seq[UpsertTier]
  )
}
