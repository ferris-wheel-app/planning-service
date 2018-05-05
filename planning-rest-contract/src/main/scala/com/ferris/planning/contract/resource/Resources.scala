package com.ferris.planning.contract.resource

import java.time.LocalDate
import java.util.UUID

import com.ferris.planning.contract.validation.InputValidators._

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
    ) {
      checkValidity(this)
    }

    case class BacklogItemUpdate (
      summary: Option[String],
      description: Option[String],
      `type`: Option[String]
    ) {
      checkValidity(this)
    }

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
      startDate: LocalDate
    )

    case class YearUpdate (
      epochId: Option[UUID],
      startDate: Option[LocalDate]
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
    ) {
      checkValidity(this)
    }

    case class GoalUpdate (
      themeId: Option[UUID],
      backlogItems: Option[Seq[UUID]],
      summary: Option[String],
      description: Option[String],
      level: Option[Int],
      priority: Option[Boolean],
      graduation: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class ThreadCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      status: String
    ) {
      checkValidity(this)
    }

    case class ThreadUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class WeaveCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      `type`: String,
      status: String
    ) {
      checkValidity(this)
    }

    case class WeaveUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      `type`: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class LaserDonutCreation (
      goalId: UUID,
      summary: String,
      description: String,
      milestone: String,
      `type`: String,
      status: String
    ) {
      checkValidity(this)
    }

    case class LaserDonutUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      milestone: Option[String],
      `type`: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class PortionCreation (
      laserDonutId: UUID,
      summary: String,
      status: String
    ) {
      checkValidity(this)
    }

    case class PortionUpdate (
      laserDonutId: Option[UUID],
      summary: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class TodoCreation (
      portionId: UUID,
      description: String,
      status: String
    ) {
      checkValidity(this)
    }

    case class TodoUpdate (
      portionId: Option[UUID],
      description: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class HobbyCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      frequency: String,
      `type`: String,
      status: String
    ) {
      checkValidity(this)
    }

    case class HobbyUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      frequency: Option[String],
      `type`: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class ListUpdate (
      reordered: Seq[UUID]
    )

    case class TierUpsert(
      laserDonuts: Seq[UUID]
    ) {
      checkValidity(this)
    }

    case class PyramidOfImportanceUpsert(
      tiers: Seq[TierUpsert]
    ) {
      checkValidity(this)
    }
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
      startDate: LocalDate,
      finishDate: LocalDate
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

    case class SlimLaserDonutView (
      uuid: UUID,
      summary: String,
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

    case class TierView (
      laserDonuts: List[SlimLaserDonutView]
    )

    case class PyramidOfImportanceView (
      tiers: List[TierView]
    )
    
    case class DeletionResult(
      isSuccessful: Boolean
    )

    object DeletionResult {
      val successful = DeletionResult(true)
      val unsuccessful = DeletionResult(false)
    }
  }
}
