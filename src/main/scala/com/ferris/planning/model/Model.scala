package com.ferris.planning.model

import java.time.LocalDate
import java.util.UUID

object Model {

  case class Message (
    uuid: UUID,
    sender: String,
    content: String
  )

  case class BacklogItem (
    uuid: UUID,
    summary: String,
    description: String,
    `type`: BacklogItemTypes.BacklogItemType
  )

  case class Epoch (
    uuid: UUID,
    name: String,
    totem: String,
    question: String
  )

  case class Year (
    uuid: UUID,
    epochId: UUID,
    startDate: LocalDate,
    finishDate: LocalDate
  )

  case class Theme (
    uuid: UUID,
    yearId: UUID,
    name: String
  )

  case class Goal (
    uuid: UUID,
    themeId: UUID,
    backlogItems: Seq[UUID],
    summary: String,
    description: String,
    level: Int,
    priority: Boolean,
    graduation: GraduationTypes.GraduationType,
    status: GoalStatuses.GoalStatus
  )

  case class Thread (
    uuid: UUID,
    goalId: Option[UUID],
    summary: String,
    description: String,
    status: Statuses.Status
  )

  case class Weave (
    uuid: UUID,
    goalId: Option[UUID],
    summary: String,
    description: String,
    `type`: WeaveTypes.WeaveType,
    status: Statuses.Status
  )

  case class LaserDonut (
    uuid: UUID,
    goalId: UUID,
    summary: String,
    description: String,
    milestone: String,
    order: Int,
    `type`: DonutTypes.DonutType,
    status: Statuses.Status
  )

  case class Portion (
    uuid: UUID,
    laserDonutId: UUID,
    summary: String,
    order: Int,
    status: Statuses.Status
  )

  case class Todo (
    uuid: UUID,
    portionId: UUID,
    description: String,
    order: Int,
    status: Statuses.Status
  )

  case class Hobby (
    uuid: UUID,
    goalId: Option[UUID],
    summary: String,
    description: String,
    frequency: HobbyFrequencies.HobbyFrequency,
    `type`: HobbyTypes.HobbyType,
    status: Statuses.Status
  )

  case class Tier (
    laserDonuts: Seq[UUID]
  )

  case class PyramidOfImportance (
    tiers: Seq[Tier]
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
      case Unknown.dbValue => Unknown
      case NotReached.dbValue => NotReached
      case NotStarted.dbValue => NotStarted
      case Incomplete.dbValue => Incomplete
      case Complete.dbValue => Complete
    }

    sealed trait Status extends TypeEnum

    case object Unknown extends Status {
      override val dbValue = "UNKNOWN"
    }

    case object NotReached extends Status {
      override val dbValue = "NOT_REACHED"
    }

    case object NotStarted extends Status {
      override val dbValue = "NOT_STARTED"
    }

    case object Incomplete extends Status {
      override val dbValue = "INCOMPLETE"
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
      case OneOff.dbValue => OneOff
      case Continuous.dbValue => Continuous
    }

    sealed trait HobbyFrequency extends TypeEnum

    case object OneOff extends HobbyFrequency {
      override val dbValue = "ONE_OFF"
    }

    case object Continuous extends HobbyFrequency {
      override val dbValue = "CONTINUOUS"
    }
  }
}
