package com.ferris.planning.model

import java.util.UUID

import akka.http.scaladsl.model.DateTime

object Model {

  case class Message (uuid: UUID, sender: String, content: String)

  case class BacklogItem (
    id: UUID,
    yearId: UUID,
    summary: String,
    description: String,
    `type`: BacklogItemTypes.BacklogItemType
  )

  case class Epoch (
    id: UUID,
    name: String,
    totem: String,
    question: String
  )

  case class Year (
    id: UUID,
    epochId: UUID,
    startDate: DateTime,
    finishDate: DateTime
  )

  case class Theme (
    id: UUID,
    yearId: UUID,
    name: String
  )

  case class Goal (
    id: UUID,
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
    id: UUID,
    summary: String,
    description: String,
    goalId: Option[UUID],
    status: Statuses.Status
  )

  case class Weave (
    id: UUID,
    summary: String,
    description: String,
    goalId: Option[UUID],
    status: Statuses.Status,
    `type`: WeaveTypes.WeaveType
  )

  case class LaserDonut (
    id: UUID,
    summary: String,
    description: String,
    goalId: UUID,
    status: Statuses.Status,
    milestone: String,
    order: Int,
    typeOf: DonutTypes.DonutType
  )

  case class Portion (
    id: UUID,
    laserDonutId: UUID,
    summary: String,
    order: Int,
    status: Statuses.Status
  )

  case class Todo (
    id: UUID,
    portionId: UUID,
    description: String,
    order: Int,
    status: Statuses.Status
  )

  case class Hobby (
    id: UUID,
    goalId: Option[UUID],
    summary: String,
    description: String,
    typeOf: HobbyTypes.HobbyType,
    frequency: HobbyFrequencies.HobbyFrequency,
    status: Statuses.Status
  )

  object BacklogItemTypes {

    sealed trait BacklogItemType

    case object Idea extends BacklogItemType

    case object Issue extends BacklogItemType
  }

  object Statuses {

    sealed trait Status

    case object Unknown extends Status

    case object NotReached extends Status

    case object NotStarted extends Status

    case object Incomplete extends Status

    case object Complete extends Status
  }

  object GoalStatuses {

    sealed trait GoalStatus

    case object NotAchieved extends GoalStatus

    case object Employed extends GoalStatus

    case object Unemployed extends GoalStatus
  }

  object GraduationTypes {

    sealed trait GraduationType

    case object Abandoned extends GraduationType

    case object Thread extends GraduationType

    case object Weave extends GraduationType

    case object Hobby extends GraduationType

    case object Goal extends GraduationType
  }

  object DonutTypes {

    sealed trait DonutType

    case object ProjectFocused extends DonutType

    case object SkillFocused extends DonutType
  }

  object WeaveTypes {

    sealed trait WeaveType

    case object Priority extends WeaveType

    case object PDR extends WeaveType

    case object BAU extends WeaveType
  }

  object HobbyTypes {

    sealed trait HobbyType

    case object Active extends HobbyType

    case object Passive extends HobbyType
  }

  object HobbyFrequencies {

    sealed trait HobbyFrequency

    case object OneOff extends HobbyFrequency

    case object Continuous extends HobbyFrequency
  }
}
