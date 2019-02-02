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
      question: String,
      associatedMissions: Seq[AssociatedMissionInsertion]
    )

    case class EpochUpdate (
      name: Option[String],
      totem: Option[String],
      question: Option[String],
      associatedMissions: Option[Seq[AssociatedMissionInsertion]]
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
      valueDimensions: ValueDimensionsCreation,
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
      valueDimensions: Option[ValueDimensionsUpdate],
      graduation: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class ThreadCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      valueDimensions: ValueDimensionsCreation,
      performance: String
    ) {
      checkValidity(this)
    }

    case class ThreadUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      valueDimensions: Option[ValueDimensionsUpdate],
      performance: Option[String]
    ) {
      checkValidity(this)
    }

    case class WeaveCreation (
      goalId: Option[UUID],
      summary: String,
      description: String,
      valueDimensions: ValueDimensionsCreation,
      `type`: String,
      status: String
    ) {
      checkValidity(this)
    }

    case class WeaveUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      valueDimensions: Option[ValueDimensionsUpdate],
      `type`: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class LaserDonutCreation (
      goalId: UUID,
      summary: String,
      description: String,
      valueDimensions: ValueDimensionsCreation,
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
      valueDimensions: Option[ValueDimensionsUpdate],
      milestone: Option[String],
      `type`: Option[String],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class PortionCreation (
      laserDonutId: UUID,
      summary: String,
      status: String,
      valueDimensions: ValueDimensionsCreation
    ) {
      checkValidity(this)
    }

    case class PortionUpdate (
      laserDonutId: Option[UUID],
      summary: Option[String],
      status: Option[String],
      valueDimensions: Option[ValueDimensionsUpdate]
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
      valueDimensions: ValueDimensionsCreation,
      frequency: String,
      `type`: String
    ) {
      checkValidity(this)
    }

    case class HobbyUpdate (
      goalId: Option[UUID],
      summary: Option[String],
      description: Option[String],
      valueDimensions: Option[ValueDimensionsUpdate],
      frequency: Option[String],
      `type`: Option[String]
    ) {
      checkValidity(this)
    }

    case class OneOffCreation (
      goalId: Option[UUID],
      description: String,
      valueDimensions: ValueDimensionsCreation,
      estimate: Long,
      status: String
    ) {
      checkValidity(this)
    }

    case class OneOffUpdate (
      goalId: Option[UUID],
      description: Option[String],
      valueDimensions: Option[ValueDimensionsUpdate],
      estimate: Option[Long],
      status: Option[String]
    ) {
      checkValidity(this)
    }

    case class ScheduledOneOffCreation (
      occursOn: LocalDateTime,
      goalId: Option[UUID],
      description: String,
      valueDimensions: ValueDimensionsCreation,
      estimate: Long,
      status: String
    ) {
      checkValidity(this)
    }

    case class ScheduledOneOffUpdate (
      occursOn: Option[LocalDateTime],
      goalId: Option[UUID],
      description: Option[String],
      valueDimensions: Option[ValueDimensionsUpdate],
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

    case class SkillCategoryCreation (
      name: String,
      parentCategory: Option[UUID]
    )

    case class SkillCategoryUpdate (
      name: Option[String],
      parentCategory: Option[UUID]
    )

    case class SkillCreation (
      name: String,
      parentCategory: UUID,
      proficiency: String,
      practisedHours: Long
    ) {
      checkValidity(this)
    }

    case class SkillUpdate (
      name: Option[String],
      parentCategory: Option[UUID],
      proficiency: Option[String],
      practisedHours: Option[Long],
      lastPractise: Option[LocalDateTime]
    ) {
      checkValidity(this)
    }

    case class AssociatedSkillInsertion (
      skillId: UUID,
      relevance: String,
      level: String
    ) {
      checkValidity(this)
    }

    case class PractisedHours (
      value: Long,
      time: LocalDateTime
    ) {
      checkValidity(this)
    }

    case class RelationshipCreation (
      name: String,
      category: String,
      traits: Seq[String],
      likes: Seq[String],
      dislikes: Seq[String],
      hobbies: Seq[String],
      lastMeet: Option[LocalDate]
    )

    case class RelationshipUpdate (
      name: Option[String],
      category: Option[String],
      traits: Option[Seq[String]],
      likes: Option[Seq[String]],
      dislikes: Option[Seq[String]],
      hobbies: Option[Seq[String]],
      lastMeet: Option[LocalDate]
    )

    case class MissionCreation(
      name: String,
      description: String
    )

    case class MissionUpdate(
      name: Option[String],
      description: Option[String]
    )

    case class AssociatedMissionInsertion (
      missionId: UUID,
      level: String
    )

    case class ValueDimensionsCreation (
      associatedMissions: Seq[UUID],
      associatedSkills: Seq[AssociatedSkillInsertion],
      relationships: Seq[UUID],
      helpsSafetyNet: Boolean,
      expandsWorldView: Boolean
    )

    case class ValueDimensionsUpdate(
      associatedMissions: Option[Seq[UUID]],
      associatedSkills: Option[Seq[AssociatedSkillInsertion]],
      relationships: Option[Seq[UUID]],
      helpsSafetyNet: Option[Boolean],
      expandsWorldView: Option[Boolean]
    )
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
      associatedMissions: Seq[AssociatedMissionView],
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
      valueDimensions: ValueDimensionsView,
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
      valueDimensions: ValueDimensionsView,
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
      valueDimensions: ValueDimensionsView,
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
      valueDimensions: ValueDimensionsView,
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
      valueDimensions: ValueDimensionsView,
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
      valueDimensions: ValueDimensionsView,
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
      valueDimensions: ValueDimensionsView,
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
      valueDimensions: ValueDimensionsView,
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

    case class SkillCategoryView (
      uuid: UUID,
      name: String,
      parentCategory: Option[UUID]
    )

    case class SkillView (
      uuid: UUID,
      name: String,
      parentCategory: UUID,
      proficiency: String,
      practisedHours: Long,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime],
      lastApplied: Option[LocalDateTime]
    )

    case class AssociatedSkillView (
      skillId: UUID,
      relevance: String,
      level: String
    )

    case class RelationshipView(
      uuid: UUID,
      name: String,
      category: String,
      traits: Seq[String],
      likes: Seq[String],
      dislikes: Seq[String],
      hobbies: Seq[String],
      lastMeet: Option[LocalDate],
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime]
    )

    case class MissionView(
      uuid: UUID,
      name: String,
      description: String,
      createdOn: LocalDateTime,
      lastModified: Option[LocalDateTime]
    )

    case class AssociatedMissionView(
      missionId: UUID,
      level: String
    )

    case class ValueDimensionsView(
      associatedMissions: Seq[UUID],
      associatedSkills: Seq[AssociatedSkillView],
      relationships: Seq[UUID],
      helpsSafetyNet: Boolean,
      expandsWorldView: Boolean
    )
  }
}
