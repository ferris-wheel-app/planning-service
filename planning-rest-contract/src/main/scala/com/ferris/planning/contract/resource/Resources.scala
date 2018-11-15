package com.ferris.planning.contract.resource

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

import com.ferris.planning.contract.validation.InputValidators._

object Resources {

  object In {

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
      graduation: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class ThreadCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      performance: String
    ) {
      checkValidity(this)
    }

    case class ThreadUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      performance: Option[String]
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
      parentId: UUID,
      description: String
    )

    case class TodoUpdate (
      parentId: Option[UUID],
      description: Option[String],
      isDone: Option[Boolean]
    )

    case class HobbyCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      frequency: String,
      `type`: String
    ) {
      checkValidity(this)
    }

    case class HobbyUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      frequency: Option[String],
      `type`: Option[String]
    ) {
      checkValidity(this)
    }

    case class OneOffCreation (
      goalId: Option[UUID],
      description: String,
      estimate: Long,
      status: String
    ) {
      checkValidity(this)
    }

    case class OneOffUpdate (
      goalId: Option[UUID],
      description: Option[String],
      estimate: Option[Long],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class ScheduledOneOffCreation (
      occursOn: LocalDateTime,
      goalId: Option[UUID],
      description: String,
      estimate: Long,
      status: String
    ) {
      checkValidity(this)
    }

    case class ScheduledOneOffUpdate (
      occursOn: Option[LocalDateTime],
      goalId: Option[UUID],
      description: Option[String],
      estimate: Option[Long],
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

    case class BacklogItemView (
      uuid: UUID,
      summary: String,
      description: String,
      `type`: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime]
    )

    case class EpochView (
      uuid: UUID,
      name: String,
      totem: String,
      question: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime]
    )

    case class YearView (
      uuid: UUID,
      epochId: UUID,
      startDate: LocalDate,
      finishDate: LocalDate,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime]
    )

    case class ThemeView (
      uuid: UUID,
      yearId: UUID,
      name: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime]
    )

    case class GoalView (
      uuid: UUID,
      themeId: UUID,
      backlogItems: Seq[UUID],
      summary: String,
      description: String,
      graduation: String,
      status: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime]
    )

    case class ThreadView (
      uuid: UUID,
      goalId: Option[UUID],
      summary: String,
      description: String,
      performance: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class WeaveView (
      uuid: UUID,
      goalId: Option[UUID],
      summary: String,
      description: String,
      `type`: String,
      status: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class LaserDonutView (
      uuid: UUID,
      goalId: UUID,
      summary: String,
      description: String,
      milestone: String,
      order: Int,
      `type`: String,
      status: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class SlimLaserDonutView (
      uuid: UUID,
      summary: String,
      status: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class PortionView (
      uuid: UUID,
      laserDonutId: UUID,
      summary: String,
      order: Int,
      status: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class TodoView (
      uuid: UUID,
      parentId: UUID,
      description: String,
      order: Int,
      isDone: Boolean,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class HobbyView (
      uuid: UUID,
      goalId: Option[UUID],
      summary: String,
      description: String,
      frequency: String,
      `type`: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class OneOffView (
      uuid: UUID,
      goalId: Option[UUID],
      description: String,
      estimate: Long,
      order: Int,
      status: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class ScheduledOneOffView (
      uuid: UUID,
      occursOn: LocalDateTime,
      goalId: Option[UUID],
      description: String,
      estimate: Long,
      status: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastPerformed: Option[LocalDateTime]
    )

    case class TierView (
      laserDonuts: Seq[UUID]
    )

    case class PyramidOfImportanceView (
      tiers: Seq[TierView]
    )
  }
}
