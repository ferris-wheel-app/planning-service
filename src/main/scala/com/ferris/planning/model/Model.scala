package com.ferris.planning.model

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

object Model {

  case class BacklogItem (
    uuid: UUID,
    summary: String,
    description: String,
    `type`: BacklogItemTypes.BacklogItemType,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime]
  )

  case class Epoch (
    uuid: UUID,
    name: String,
    totem: String,
    question: String,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime]
  )

  case class Year (
    uuid: UUID,
    epochId: UUID,
    startDate: LocalDate,
    finishDate: LocalDate,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime]
  )

  case class Theme (
    uuid: UUID,
    yearId: UUID,
    name: String,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime]
  )

  case class Goal (
    uuid: UUID,
    themeId: UUID,
    backlogItems: Seq[UUID],
    summary: String,
    description: String,
    graduation: GraduationTypes.GraduationType,
    status: GoalStatuses.GoalStatus,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime]
  )

  case class Thread (
    uuid: UUID,
    goalId: Option[UUID],
    summary: String,
    description: String,
    performance: ThreadPerformances.ThreadPerformance,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime],
    lastPerformed: Option[LocalDateTime]
  )

  case class Weave (
    uuid: UUID,
    goalId: Option[UUID],
    summary: String,
    description: String,
    `type`: WeaveTypes.WeaveType,
    status: Statuses.Status,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime],
    lastPerformed: Option[LocalDateTime]
  )

  case class LaserDonut (
    uuid: UUID,
    goalId: UUID,
    summary: String,
    description: String,
    milestone: String,
    order: Int,
    `type`: DonutTypes.DonutType,
    status: Statuses.Status,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime],
    lastPerformed: Option[LocalDateTime]
  )

  case class Portion (
    uuid: UUID,
    laserDonutId: UUID,
    summary: String,
    order: Int,
    status: Statuses.Status,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime],
    lastPerformed: Option[LocalDateTime]
  )

  case class Todo (
    uuid: UUID,
    parentId: UUID,
    description: String,
    order: Int,
    isDone: Boolean,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime],
    lastPerformed: Option[LocalDateTime]
  )

  case class Hobby (
    uuid: UUID,
    goalId: Option[UUID],
    summary: String,
    description: String,
    frequency: HobbyFrequencies.HobbyFrequency,
    `type`: HobbyTypes.HobbyType,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime],
    lastPerformed: Option[LocalDateTime]
  )

  case class Tier (
    laserDonuts: Seq[UUID]
  )

  case class PyramidOfImportance (
    tiers: Seq[Tier],
    currentLaserDonut: Option[UUID]
  )

  case class ScheduledPyramid (
    laserDonuts: Seq[ScheduledLaserDonut],
    currentLaserDonut: Option[Long],
    currentPortion: Option[Long],
    lastUpdate: Option[LocalDateTime]
  )

  case class ScheduledLaserDonut (
    id: Long,
    uuid: UUID,
    portions: Seq[ScheduledPortion],
    tier: Int,
    status: Statuses.Status,
    lastPerformed: Option[LocalDateTime]
  )

  case class ScheduledPortion (
    id: Long,
    uuid: UUID,
    todos: Seq[ScheduledTodo],
    order: Int,
    status: Statuses.Status
  )

  case class ScheduledTodo (
    uuid: UUID,
    order: Int,
    isDone: Boolean
  )

  trait TypeEnum {
    def dbValue: String
  }

  object BacklogItemTypes {

    def withName(name: String): BacklogItemType = name match {
      case Idea.dbValue => Idea
      case Issue.dbValue => Issue
    }

    sealed trait BacklogItemType extends TypeEnum

    case object Idea extends BacklogItemType {
      override val dbValue = "IDEA"
    }

    case object Issue extends BacklogItemType {
      override val dbValue = "ISSUE"
    }
  }

  object Statuses {

    def withName(name: String): Status = name match {
      case Planned.dbValue => Planned
      case InProgress.dbValue => InProgress
      case Complete.dbValue => Complete
    }

    sealed trait Status extends TypeEnum

    case object Planned extends Status {
      override val dbValue = "PLANNED"
    }

    case object InProgress extends Status {
      override val dbValue = "IN_PROGRESS"
    }

    case object Complete extends Status {
      override val dbValue = "COMPLETE"
    }
  }

  object GoalStatuses {

    def withName(name: String): GoalStatus = name match {
      case NotAchieved.dbValue => NotAchieved
      case Employed.dbValue => Employed
      case Unemployed.dbValue => Unemployed
    }

    sealed trait GoalStatus extends TypeEnum

    case object NotAchieved extends GoalStatus {
      override val dbValue = "NOT_ACHIEVED"
    }

    case object Employed extends GoalStatus {
      override val dbValue = "EMPLOYED"
    }

    case object Unemployed extends GoalStatus {
      override val dbValue = "UNEMPLOYED"
    }
  }

  object ThreadPerformances {

    def withName(name: String): ThreadPerformance = name match {
      case Poor.dbValue => Poor
      case Slipping.dbValue => Slipping
      case Improving.dbValue => Improving
      case OnTrack.dbValue => OnTrack
    }

    sealed trait ThreadPerformance extends TypeEnum

    case object Poor extends ThreadPerformance {
      override val dbValue = "POOR"
    }

    case object Slipping extends ThreadPerformance {
      override val dbValue = "SLIPPING"
    }

    case object Improving extends ThreadPerformance {
      override val dbValue = "IMPROVING"
    }

    case object OnTrack extends ThreadPerformance {
      override val dbValue = "ON_TRACK"
    }
  }

  object GraduationTypes {

    def withName(name: String): GraduationType = name match {
      case Abandoned.dbValue => Abandoned
      case Thread.dbValue => Thread
      case Weave.dbValue => Weave
      case Hobby.dbValue => Hobby
      case Goal.dbValue => Goal
    }

    sealed trait GraduationType extends TypeEnum

    case object Abandoned extends GraduationType {
      override val dbValue = "ABANDONED"
    }

    case object Thread extends GraduationType {
      override val dbValue = "THREAD"
    }

    case object Weave extends GraduationType {
      override val dbValue = "WEAVE"
    }

    case object Hobby extends GraduationType {
      override val dbValue = "HOBBY"
    }

    case object Goal extends GraduationType {
      override val dbValue = "GOAL"
    }
  }

  object DonutTypes {

    def withName(name: String): DonutType = name match {
      case ProjectFocused.dbValue => ProjectFocused
      case SkillFocused.dbValue => SkillFocused
    }

    sealed trait DonutType extends TypeEnum

    case object ProjectFocused extends DonutType {
      override val dbValue = "PROJECT_FOCUSED"
    }

    case object SkillFocused extends DonutType {
      override val dbValue = "SKILL_FOCUSED"
    }
  }

  object WeaveTypes {

    def withName(name: String): WeaveType = name match {
      case Priority.dbValue => Priority
      case PDR.dbValue => PDR
      case BAU.dbValue => BAU
    }

    sealed trait WeaveType extends TypeEnum

    case object Priority extends WeaveType {
      override val dbValue = "PRIORITY"
    }

    case object PDR extends WeaveType {
      override val dbValue = "PDR"
    }

    case object BAU extends WeaveType {
      override val dbValue = "BAU"
    }
  }

  object HobbyTypes {

    def withName(name: String): HobbyType = name match {
      case Active.dbValue => Active
      case Passive.dbValue => Passive
    }

    sealed trait HobbyType extends TypeEnum

    case object Active extends HobbyType {
      override val dbValue = "ACTIVE"
    }

    case object Passive extends HobbyType {
      override val dbValue = "PASSIVE"
    }
  }

  object HobbyFrequencies {

    def withName(name: String): HobbyFrequency = name match {
      case Frequent.dbValue => Frequent
      case Scattered.dbValue => Scattered
      case Rare.dbValue => Rare
      case Unexplored.dbValue => Unexplored
    }

    sealed trait HobbyFrequency extends TypeEnum

    case object Frequent extends HobbyFrequency {
      override val dbValue = "FREQUENT"
    }

    case object Scattered extends HobbyFrequency {
      override val dbValue = "SCATTERED"
    }

    case object Rare extends HobbyFrequency {
      override val dbValue = "RARE"
    }

    case object Unexplored extends HobbyFrequency {
      override val dbValue = "UNEXPLORED"
    }
  }
}
