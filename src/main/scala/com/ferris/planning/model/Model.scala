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
    associatedMissions: Seq[AssociatedMission],
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
    valueDimensions: ValueDimensions,
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
    associatedSkills: Seq[AssociatedSkill],
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
    associatedSkills: Seq[AssociatedSkill],
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
    associatedSkills: Seq[AssociatedSkill],
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
    associatedSkills: Seq[AssociatedSkill],
    frequency: HobbyFrequencies.HobbyFrequency,
    `type`: HobbyTypes.HobbyType,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime],
    lastPerformed: Option[LocalDateTime]
  )

  case class OneOff (
    uuid: UUID,
    goalId: Option[UUID],
    description: String,
    associatedSkills: Seq[AssociatedSkill],
    estimate: Long,
    order: Int,
    status: Statuses.Status,
    createdOn: LocalDateTime,
    lastModified: Option[LocalDateTime],
    lastPerformed: Option[LocalDateTime]
  )

  case class ScheduledOneOff (
    uuid: UUID,
    occursOn: LocalDateTime,
    goalId: Option[UUID],
    description: String,
    associatedSkills: Seq[AssociatedSkill],
    estimate: Long,
    status: Statuses.Status,
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

  case class SkillCategory (
    uuid: UUID,
    name: String,
    parentCategory: Option[UUID],
    lastModified: Option[LocalDateTime]
  )

  case class Skill (
    uuid: UUID,
    name: String,
    parentCategory: UUID,
    proficiency: Proficiencies.Proficiency,
    practisedHours: Long,
    lastApplied: Option[LocalDateTime],
    lastModified: Option[LocalDateTime]
  )

  case class AssociatedSkill (
    skillId: UUID,
    relevance: SkillRelevances.SkillRelevance,
    level: Proficiencies.SkillLevel
  )

  case class Relationship(
    uuid: UUID,
    name: Seq[String],
    category: RelationshipCategories.RelationshipCategory,
    traits: String,
    likes: Seq[String],
    dislikes: Seq[String],
    hobbies: Seq[String],
    lastMeet: Option[LocalDate]
  )

  case class Mission(
    uuid: UUID,
    name: String,
    description: String
  )

  case class AssociatedMission(
    missionId: UUID,
    level: MissionLevels.MissionLevel
  )

  case class ValueDimensions(
    associatedMissions: Seq[UUID],
    associatedSkills: Seq[AssociatedSkill],
    relationships: Seq[UUID],
    helpsSafetyNet: Boolean,
    expandsWorldView: Boolean
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

    case object ChipOnAShoulder extends BacklogItemType {
      override val dbValue = "CHIP_ON_A_SHOULDER"
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

  object Proficiencies {

    def skillLevel(name: String): SkillLevel = name match {
      case Basic.dbValue => Basic
      case Novice.dbValue => Novice
      case Intermediate.dbValue => Intermediate
      case Advanced.dbValue => Advanced
      case Expert.dbValue => Expert
    }

    def withName(name: String): Proficiency = name match {
      case Zero.dbValue => Zero
      case Basic.dbValue => Basic
      case Novice.dbValue => Novice
      case Intermediate.dbValue => Intermediate
      case Advanced.dbValue => Advanced
      case Expert.dbValue => Expert
    }

    sealed trait Proficiency extends TypeEnum

    sealed trait SkillLevel extends TypeEnum

    case object Zero extends Proficiency {
      override val dbValue = "ZERO"
    }

    case object Basic extends SkillLevel with Proficiency {
      override val dbValue = "BASIC"
    }

    case object Novice extends SkillLevel with Proficiency {
      override val dbValue = "NOVICE"
    }

    case object Intermediate extends SkillLevel with Proficiency {
      override val dbValue = "INTERMEDIATE"
    }

    case object Advanced extends SkillLevel with Proficiency {
      override val dbValue = "ADVANCED"
    }

    case object Expert extends SkillLevel with Proficiency {
      override val dbValue = "EXPERT"
    }
  }

  object SkillRelevances {

    def withName(name: String): SkillRelevance = name match {
      case Needed.dbValue => Needed
      case ToBeAcquired.dbValue => ToBeAcquired
      case Maintenance.dbValue => Maintenance
    }

    sealed trait SkillRelevance extends TypeEnum

    case object Needed extends SkillRelevance {
      override val dbValue = "NEEDED"
    }

    case object ToBeAcquired extends SkillRelevance {
      override val dbValue = "TO_BE_ACQUIRED"
    }

    case object Maintenance extends SkillRelevance {
      override val dbValue = "MAINTENANCE"
    }
  }

  object RelationshipCategories {

    def withName(name: String): RelationshipCategory = name match {
      case Family.dbValue => Family
      case Friends.dbValue => Friends
      case Work.dbValue => Work
      case Romantic.dbValue => Romantic
      case Mentorship.dbValue => Mentorship
    }

    sealed trait RelationshipCategory extends TypeEnum

    case object Family extends RelationshipCategory {
      override val dbValue = "FAMILY"
    }

    case object Friends extends RelationshipCategory {
      override val dbValue = "FRIENDS"
    }

    case object Work extends RelationshipCategory {
      override val dbValue = "WORK"
    }

    case object Romantic extends RelationshipCategory {
      override val dbValue = "ROMANTIC"
    }

    case object Mentorship extends RelationshipCategory {
      override val dbValue = "MENTORSHIP"
    }
  }

  object MissionLevels {

    def withName(name: String): MissionLevel = name match {
      case Major.dbValue => Major
      case Minor.dbValue => Minor
    }

    sealed trait MissionLevel extends TypeEnum

    case object Major extends MissionLevel {
      override val dbValue = "MAJOR"
    }

    case object Minor extends MissionLevel {
      override val dbValue = "MINOR"
    }
  }
}
