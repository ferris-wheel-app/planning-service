package com.ferris.planning.db.conversions

import java.sql.{Date, Timestamp}
import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

import akka.http.scaladsl.model.DateTime
import com.ferris.planning.db.Tables
import com.ferris.planning.model.Model._
import com.ferris.utils.FerrisImplicits._

import scala.language.implicitConversions

class DomainConversions(val tables: Tables) {

  implicit class BacklogItemBuilder(val row: tables.BacklogItemRow) {
    def asBacklogItem: BacklogItem = BacklogItem(
      uuid = UUID.fromString(row.uuid),
      summary = row.summary,
      description = row.description,
      `type` = BacklogItemTypes.withName(row.`type`),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime)
    )
  }

  implicit class EpochBuilder(val rows: (tables.EpochRow, Seq[(tables.EpochMissionRow, UUID)])) {
    def asEpoch: Epoch = rows match {
      case (epoch, associatedMissions) =>
        Epoch(
          uuid = UUID.fromString(epoch.uuid),
          name = epoch.name,
          totem = epoch.totem,
          question = epoch.question,
          associatedMissions = associatedMissions.map { case (mission, id) =>
            AssociatedMission(
              missionId = id,
              level = MissionLevels.withName(mission.level)
            )
          },
          createdOn = epoch.createdOn.toLocalDateTime,
          lastModified = epoch.lastModified.map(_.toLocalDateTime)
        )
    }
  }

  implicit class YearBuilder(val row: tables.YearRow) {
    def asYear: Year = Year(
      uuid = UUID.fromString(row.uuid),
      epochId = UUID.fromString(row.epochId),
      startDate = row.startDate.toLocalDate,
      finishDate = row.finishDate.toLocalDate,
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime)
    )
  }

  implicit class ThemeBuilder(val row: tables.ThemeRow) {
    def asTheme: Theme = Theme(
      uuid = UUID.fromString(row.uuid),
      yearId = UUID.fromString(row.yearId),
      name = row.name,
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime)
    )
  }

  implicit class GoalBuilder(val rows: (tables.GoalRow, Seq[tables.BacklogItemRow], Seq[UUID], Seq[(tables.GoalSkillRow, UUID)], Seq[UUID])) {
    def asGoal: Goal = rows match {
      case (goal, backlogItems, missions, associatedSkills, relationships) =>
        Goal(
          uuid = UUID.fromString(goal.uuid),
          themeId = UUID.fromString(goal.themeId),
          backlogItems = backlogItems.map(item => UUID.fromString(item.uuid)),
          summary = goal.summary,
          description = goal.description,
          valueDimensions = ValueDimensions(
            associatedMissions = missions,
            associatedSkills = associatedSkills.map { case (skill, id) =>
              AssociatedSkill(
                skillId = id,
                relevance = SkillRelevances.withName(skill.relevance),
                level = Proficiencies.skillLevel(skill.level)
              )
            },
            relationships = relationships,
            helpsSafetyNet = goal.safetyNet,
            expandsWorldView = goal.worldView
          ),
          status = GoalStatuses.withName(goal.status),
          graduation = GraduationTypes.withName(goal.graduation),
          createdOn = goal.createdOn.toLocalDateTime,
          lastModified = goal.lastModified.map(_.toLocalDateTime)
        )
    }
  }

  implicit class ThreadBuilder(val row: (tables.ThreadRow, Seq[UUID], Seq[(tables.ThreadSkillRow, UUID)], Seq[UUID])) {
    def asThread: Thread = row match {
      case (thread, missions, associatedSkills, relationships) =>
        Thread(
          uuid = UUID.fromString(thread.uuid),
          goalId = thread.goalId.map(UUID.fromString),
          summary = thread.summary,
          description = thread.description,
          valueDimensions = ValueDimensions(
            associatedMissions = missions,
            associatedSkills = associatedSkills.map { case (skill, id) =>
              AssociatedSkill(
                skillId = id,
                relevance = SkillRelevances.withName(skill.relevance),
                level = Proficiencies.skillLevel(skill.level)
              )
            },
            relationships = relationships,
            helpsSafetyNet = thread.safetyNet,
            expandsWorldView = thread.worldView
          ),
          performance = ThreadPerformances.withName(thread.performance),
          createdOn = thread.createdOn.toLocalDateTime,
          lastModified = thread.lastModified.map(_.toLocalDateTime),
          lastPerformed = thread.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class WeaveBuilder(val row: (tables.WeaveRow, Seq[UUID], Seq[(tables.WeaveSkillRow, UUID)], Seq[UUID])) {
    def asWeave: Weave = row match {
      case (weave, missions, associatedSkills, relationships) =>
        Weave(
          uuid = UUID.fromString(weave.uuid),
          goalId = weave.goalId.map(UUID.fromString),
          summary = weave.summary,
          description = weave.description,
          valueDimensions = ValueDimensions(
            associatedMissions = missions,
            associatedSkills = associatedSkills.map { case (skill, id) =>
              AssociatedSkill(
                skillId = id,
                relevance = SkillRelevances.withName(skill.relevance),
                level = Proficiencies.skillLevel(skill.level)
              )
            },
            relationships = relationships,
            helpsSafetyNet = weave.safetyNet,
            expandsWorldView = weave.worldView
          ),
          status = Statuses.withName(weave.status),
          `type` = WeaveTypes.withName(weave.`type`),
          createdOn = weave.createdOn.toLocalDateTime,
          lastModified = weave.lastModified.map(_.toLocalDateTime),
          lastPerformed = weave.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class LaserDonutBuilder(val row: (tables.LaserDonutRow, Seq[UUID], Seq[(tables.LaserDonutSkillRow, UUID)], Seq[UUID])) {
    def asLaserDonut: LaserDonut = row match {
      case (laserDonut, missions, associatedSkills, relationships) =>
        LaserDonut(
          uuid = UUID.fromString(laserDonut.uuid),
          goalId = UUID.fromString(laserDonut.goalId),
          summary = laserDonut.summary,
          description = laserDonut.description,
          valueDimensions = ValueDimensions(
            associatedMissions = missions,
            associatedSkills = associatedSkills.map { case (skill, id) =>
              AssociatedSkill(
                skillId = id,
                relevance = SkillRelevances.withName(skill.relevance),
                level = Proficiencies.skillLevel(skill.level)
              )
            },
            relationships = relationships,
            helpsSafetyNet = laserDonut.safetyNet,
            expandsWorldView = laserDonut.worldView
          ),
          milestone = laserDonut.milestone,
          order = laserDonut.order,
          status = Statuses.withName(laserDonut.status),
          `type` = DonutTypes.withName(laserDonut.`type`),
          createdOn = laserDonut.createdOn.toLocalDateTime,
          lastModified = laserDonut.lastModified.map(_.toLocalDateTime),
          lastPerformed = laserDonut.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class PortionBuilder(val row: (tables.PortionRow, Seq[UUID], Seq[(tables.PortionSkillRow, UUID)], Seq[UUID])) {
    def asPortion: Portion = row match {
      case (portion, missions, associatedSkills, relationships) =>
        Portion(
          uuid = UUID.fromString(portion.uuid),
          laserDonutId = UUID.fromString(portion.laserDonutId),
          summary = portion.summary,
          valueDimensions = ValueDimensions(
            associatedMissions = missions,
            associatedSkills = associatedSkills.map { case (skill, id) =>
              AssociatedSkill(
                skillId = id,
                relevance = SkillRelevances.withName(skill.relevance),
                level = Proficiencies.skillLevel(skill.level)
              )
            },
            relationships = relationships,
            helpsSafetyNet = portion.safetyNet,
            expandsWorldView = portion.worldView
          ),
          order = portion.order,
          status = Statuses.withName(portion.status),
          createdOn = portion.createdOn.toLocalDateTime,
          lastModified = portion.lastModified.map(_.toLocalDateTime),
          lastPerformed = portion.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class TodoBuilder(val row: tables.TodoRow) {
    def asTodo: Todo = Todo(
      uuid = UUID.fromString(row.uuid),
      parentId = UUID.fromString(row.parentId),
      description = row.description,
      order = row.order,
      isDone = row.isDone,
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastPerformed = row.lastPerformed.map(_.toLocalDateTime)
    )
  }

  implicit class HobbyBuilder(val row: (tables.HobbyRow, Seq[UUID], Seq[(tables.HobbySkillRow, UUID)], Seq[UUID])) {
    def asHobby: Hobby = row match {
      case (hobby, missions, associatedSkills, relationships) =>
        Hobby(
          uuid = UUID.fromString(hobby.uuid),
          goalId = hobby.goalId.map(UUID.fromString),
          summary = hobby.summary,
          description = hobby.description,
          valueDimensions = ValueDimensions(
            associatedMissions = missions,
            associatedSkills = associatedSkills.map { case (skill, id) =>
              AssociatedSkill(
                skillId = id,
                relevance = SkillRelevances.withName(skill.relevance),
                level = Proficiencies.skillLevel(skill.level)
              )
            },
            relationships = relationships,
            helpsSafetyNet = hobby.safetyNet,
            expandsWorldView = hobby.worldView
          ),
          frequency = HobbyFrequencies.withName(hobby.frequency),
          `type` = HobbyTypes.withName(hobby.`type`),
          createdOn = hobby.createdOn.toLocalDateTime,
          lastModified = hobby.lastModified.map(_.toLocalDateTime),
          lastPerformed = hobby.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class OneOffBuilder(val row: (tables.OneOffRow, Seq[UUID], Seq[(tables.OneOffSkillRow, UUID)], Seq[UUID])) {
    def asOneOff: OneOff = row match {
      case (oneOff, missions, associatedSkills, relationships) =>
        OneOff(
          uuid = UUID.fromString(oneOff.uuid),
          goalId = oneOff.goalId.map(UUID.fromString),
          description = oneOff.description,
          valueDimensions = ValueDimensions(
            associatedMissions = missions,
            associatedSkills = associatedSkills.map { case (skill, id) =>
              AssociatedSkill(
                skillId = id,
                relevance = SkillRelevances.withName(skill.relevance),
                level = Proficiencies.skillLevel(skill.level)
              )
            },
            relationships = relationships,
            helpsSafetyNet = oneOff.safetyNet,
            expandsWorldView = oneOff.worldView
          ),
          estimate = oneOff.estimate,
          order = oneOff.order,
          status = Statuses.withName(oneOff.status),
          createdOn = oneOff.createdOn.toLocalDateTime,
          lastModified = oneOff.lastModified.map(_.toLocalDateTime),
          lastPerformed = oneOff.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class ScheduledOneOffBuilder(val row: (tables.ScheduledOneOffRow, Seq[UUID], Seq[(tables.ScheduledOneOffSkillRow, UUID)], Seq[UUID])) {
    def asScheduledOneOff: ScheduledOneOff = row match {
      case (scheduledOneOff, missions, associatedSkills, relationships) =>
        ScheduledOneOff(
          uuid = UUID.fromString(scheduledOneOff.uuid),
          occursOn = scheduledOneOff.occursOn.toLocalDateTime,
          goalId = scheduledOneOff.goalId.map(UUID.fromString),
          description = scheduledOneOff.description,
          valueDimensions = ValueDimensions(
            associatedMissions = missions,
            associatedSkills = associatedSkills.map { case (skill, id) =>
              AssociatedSkill(
                skillId = id,
                relevance = SkillRelevances.withName(skill.relevance),
                level = Proficiencies.skillLevel(skill.level)
              )
            },
            relationships = relationships,
            helpsSafetyNet = scheduledOneOff.safetyNet,
            expandsWorldView = scheduledOneOff.worldView
          ),
          estimate = scheduledOneOff.estimate,
          status = Statuses.withName(scheduledOneOff.status),
          createdOn = scheduledOneOff.createdOn.toLocalDateTime,
          lastModified = scheduledOneOff.lastModified.map(_.toLocalDateTime),
          lastPerformed = scheduledOneOff.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class SkillCategoryBuilder(val row: (tables.SkillCategoryRow, Option[String])) {
    def asSkillCategory: SkillCategory = row match {
      case (skillCategory, parentCategory) =>
        SkillCategory(
          uuid = UUID.fromString(skillCategory.uuid),
          name = skillCategory.name,
          parentCategory = parentCategory.map(UUID.fromString),
          createdOn = skillCategory.createdOn.toLocalDateTime,
          lastModified = skillCategory.lastModified.map(_.toLocalDateTime)
        )
    }
  }

  implicit class SkillBuilder(val row: (tables.SkillRow, String)) {
    def asSkill: Skill = row match {
      case (skill, categoryId) =>
        Skill(
          uuid = UUID.fromString(skill.uuid),
          name = skill.name,
          parentCategory = UUID.fromString(categoryId),
          proficiency = Proficiencies.withName(skill.proficiency),
          practisedHours = skill.practisedHours,
          createdOn = skill.createdOn.toLocalDateTime,
          lastApplied = skill.lastApplied.map(_.toLocalDateTime),
          lastModified = skill.lastModified.map(_.toLocalDateTime)
        )
    }
  }

  implicit class RelationshipBuilder(val row: tables.RelationshipRow) {
    def asRelationship: Relationship = Relationship(
      uuid = UUID.fromString(row.uuid),
      name = row.name,
      category = RelationshipCategories.withName(row.category),
      traits = row.traits.split(",").map(_.trim),
      likes = row.likes.split(",").map(_.trim),
      dislikes = row.dislikes.split(",").map(_.trim),
      hobbies = row.hobbies.split(",").map(_.trim),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastMeet = row.lastMeet.map(_.toLocalDate)
    )
  }

  implicit class MissionBuilder(val row: tables.MissionRow) {
    def asMission: Mission = Mission(
      uuid = UUID.fromString(row.uuid),
      name = row.name,
      description = row.description,
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime)
    )
  }

  implicit class PyramidBuilder(val rows: Seq[(tables.ScheduledLaserDonutRow, tables.LaserDonutRow)]) {
    def asPyramid: PyramidOfImportance = {
      val currentLaserDonut = rows.find(_._1.isCurrent).map(row => UUID.fromString(row._2.uuid))
      val tiers = rows.filterNot(_._1.isCurrent).groupBy(_._1.tier).map { case (tierNumber, laserDonutRows) =>
        (tierNumber, Tier(laserDonutRows.map(row => UUID.fromString(row._2.uuid))))
      }.toSeq.sortBy(_._1).map(_._2)
      PyramidOfImportance(tiers = tiers, currentLaserDonut = currentLaserDonut)
    }
  }

  implicit class ScheduledLaserDonutsBuilder(val rows: Seq[(tables.ScheduledLaserDonutRow, tables.LaserDonutRow, tables.PortionRow, tables.TodoRow)]) {
    def asScheduledLaserDonuts: Seq[ScheduledLaserDonut] = {
      rows.groupBy(row => (row._1, row._2)).map { case ((scheduledLaserDonutRow, laserDonutRow), groupedRows) =>
        val scheduledPortions: Seq[ScheduledPortion] = groupedRows.map(row => (row._3, row._4)).asScheduledPortions
        ScheduledLaserDonut(
          id = laserDonutRow.id,
          uuid = UUID.fromString(laserDonutRow.uuid),
          portions = scheduledPortions,
          tier = scheduledLaserDonutRow.tier,
          status = Statuses.withName(laserDonutRow.status),
          lastPerformed = laserDonutRow.lastPerformed.map(_.toLocalDateTime)
        )
      }(scala.collection.breakOut)
    }
  }

  implicit class ScheduledPortionsBuilder(val rows: Seq[(tables.PortionRow, tables.TodoRow)]) {
    def asScheduledPortions: Seq[ScheduledPortion] = {
      rows.groupBy(_._1).map { case (portionRow, groupedRows) =>
        val scheduledTodos = groupedRows.map { case (_, todoRow) =>
          ScheduledTodo(
            uuid = UUID.fromString(todoRow.uuid),
            order = todoRow.order,
            isDone = todoRow.isDone
          )
        }
        ScheduledPortion(
          id = portionRow.id,
          uuid = UUID.fromString(portionRow.uuid),
          todos = scheduledTodos,
          order = portionRow.order,
          status = Statuses.withName(portionRow.status)
        )
      }(scala.collection.breakOut)
    }
  }

  sealed trait Update[T, U] {
    def keepOrReplace(newVersion: Option[T], oldVersion: U): U
  }

  object UpdateId extends Update[UUID, String] {
    override def keepOrReplace(newVersion: Option[UUID], oldVersion: String): String = {
      newVersion.map(uuid2String).getOrElse(oldVersion)
    }
  }

  object UpdateIdOption extends Update[UUID, Option[String]] {
    override def keepOrReplace(newVersion: Option[UUID], oldVersion: Option[String]): Option[String] = {
      newVersion.map(uuid2String).orElse(oldVersion)
    }
  }

  object UpdateDate extends Update[LocalDate, Date] {
    override def keepOrReplace(newVersion: Option[LocalDate], oldVersion: Date): Date = {
      newVersion.map(localDate2SqlDate).getOrElse(oldVersion)
    }

    def keepOrReplace(newVersion: Option[LocalDate], oldVersion: Option[Date]): Option[Date] = {
      newVersion.map(localDate2SqlDate).orElse(oldVersion)
    }
  }

  object UpdateDateTime extends Update[LocalDateTime, Timestamp] {
    override def keepOrReplace(newVersion: Option[LocalDateTime], oldVersion: Timestamp): Timestamp = {
      newVersion.map(_.toTimestamp).getOrElse(oldVersion)
    }

    def keepOrReplace(newVersion: Option[LocalDateTime], oldVersion: Option[Timestamp]): Option[Timestamp] = {
      newVersion.map(_.toTimestamp).orElse(oldVersion)
    }
  }

  object UpdateTypeEnum extends Update[TypeEnum, String] {
    override def keepOrReplace(newVersion: Option[TypeEnum], oldVersion: String): String = {
      newVersion.map(_.dbValue).getOrElse(oldVersion)
    }
  }

  object UpdateBoolean extends Update[Boolean, Byte] {
    override def keepOrReplace(newVersion: Option[Boolean], oldVersion: Byte): Byte = {
      newVersion.map(boolean2Byte).getOrElse(oldVersion)
    }
  }

  implicit def timestamp2DateTime(date: Timestamp): DateTime = DateTime.apply(date.getTime)
}
