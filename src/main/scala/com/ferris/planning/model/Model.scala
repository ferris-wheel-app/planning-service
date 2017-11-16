package com.ferris.planning.model

import java.util.UUID

import akka.http.scaladsl.model.DateTime

object Model {

  case class Message (uuid: UUID, sender: String, content: String)

  case class BacklogItem (
    uuid: UUID,
    yearId: UUID,
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
    startDate: DateTime,
    finishDate: DateTime
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
    status: GoalStatuses.GoalStatus,
    graduation: GraduationTypes.GraduationType
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
    status: Statuses.Status,
    `type`: WeaveTypes.WeaveType
  )

  case class LaserDonut (
    uuid: UUID,
    goalId: UUID,
    summary: String,
    description: String,
    milestone: String,
    order: Int,
    status: Statuses.Status,
    `type`: DonutTypes.DonutType
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
    status: Statuses.Status,
    `type`: HobbyTypes.HobbyType
  )

  trait DatabaseEnum {
    def dbValue: String
  }

  object BacklogItemTypes {

    sealed trait BacklogItemType extends DatabaseEnum

    case object Idea extends BacklogItemType {
      override val dbValue = "IDEA"
    }

    case object Issue extends BacklogItemType {
      override val dbValue = "ISSUE"
    }
  }

  object Statuses {

    sealed trait Status extends DatabaseEnum

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

    sealed trait GoalStatus extends DatabaseEnum

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

    sealed trait GraduationType extends DatabaseEnum

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

    sealed trait DonutType extends DatabaseEnum

    case object ProjectFocused extends DonutType {
      override val dbValue = "PROJECT_FOCUSED"
    }

    case object SkillFocused extends DonutType {
      override val dbValue = "SKILL_FOCUSED"
    }
  }

  object WeaveTypes {

    sealed trait WeaveType extends DatabaseEnum

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

    sealed trait HobbyType extends DatabaseEnum

    case object Active extends HobbyType {
      override val dbValue = "ACTIVE"
    }

    case object Passive extends HobbyType {
      override val dbValue = "PASSIVE"
    }
  }

  object HobbyFrequencies {

    sealed trait HobbyFrequency extends DatabaseEnum

    case object OneOff extends HobbyFrequency {
      override val dbValue = "ONE_OFF"
    }

    case object Continuous extends HobbyFrequency {
      override val dbValue = "CONTINUOUS"
    }
  }
}
