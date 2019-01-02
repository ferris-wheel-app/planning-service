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

  implicit class EpochBuilder(val row: tables.EpochRow) {
    def asEpoch: Epoch = Epoch(
      uuid = UUID.fromString(row.uuid),
      name = row.name,
      totem = row.totem,
      question = row.question,
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime)
    )
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

  implicit class GoalBuilder(val rows: (tables.GoalRow, Seq[tables.BacklogItemRow], Seq[(tables.GoalSkillRow, UUID)])) {
    def asGoal: Goal = rows match {
      case (goal, backlogItems, associatedSkills) =>
        Goal(
          uuid = UUID.fromString(goal.uuid),
          themeId = UUID.fromString(goal.themeId),
          backlogItems = backlogItems.map(item => UUID.fromString(item.uuid)),
          summary = goal.summary,
          description = goal.description,
          associatedSkills = associatedSkills.map { case (skill, id) =>
            AssociatedSkill(
              skillId = id,
              relevance = SkillRelevances.withName(skill.relevance),
              level = Proficiencies.skillLevel(skill.level)
            )
          },
          status = GoalStatuses.withName(goal.status),
          graduation = GraduationTypes.withName(goal.graduation),
          createdOn = goal.createdOn.toLocalDateTime,
          lastModified = goal.lastModified.map(_.toLocalDateTime)
        )
    }
  }

  implicit class ThreadBuilder(val row: (tables.ThreadRow, Seq[(tables.ThreadSkillRow, UUID)])) {
    def asThread: Thread = row match {
      case (thread, associatedSkills) =>
        Thread(
          uuid = UUID.fromString(thread.uuid),
          goalId = thread.goalId.map(UUID.fromString),
          summary = thread.summary,
          description = thread.description,
          associatedSkills = associatedSkills.map { case (skill, id) =>
            AssociatedSkill(
              skillId = UUID.fromString(id),
              relevance = SkillRelevances.withName(skill.relevance),
              level = Proficiencies.skillLevel(skill.level)
            )
          },
          performance = ThreadPerformances.withName(thread.performance),
          createdOn = thread.createdOn.toLocalDateTime,
          lastModified = thread.lastModified.map(_.toLocalDateTime),
          lastPerformed = thread.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class WeaveBuilder(val row: (tables.WeaveRow, Seq[(tables.WeaveSkillRow, UUID)])) {
    def asWeave: Weave = row match {
      case (weave, associatedSkills) =>
        Weave(
          uuid = UUID.fromString(weave.uuid),
          goalId = weave.goalId.map(UUID.fromString),
          summary = weave.summary,
          description = weave.description,
          associatedSkills = associatedSkills.map { case (skill, id) =>
            AssociatedSkill(
              skillId = UUID.fromString(id),
              relevance = SkillRelevances.withName(skill.relevance),
              level = Proficiencies.skillLevel(skill.level)
            )
          },
          status = Statuses.withName(weave.status),
          `type` = WeaveTypes.withName(weave.`type`),
          createdOn = weave.createdOn.toLocalDateTime,
          lastModified = weave.lastModified.map(_.toLocalDateTime),
          lastPerformed = weave.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class LaserDonutBuilder(val row: tables.LaserDonutRow) {
    def asLaserDonut: LaserDonut = LaserDonut(
      uuid = UUID.fromString(row.uuid),
      goalId = UUID.fromString(row.goalId),
      summary = row.summary,
      description = row.description,
      milestone = row.milestone,
      order = row.order,
      status = Statuses.withName(row.status),
      `type` = DonutTypes.withName(row.`type`),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastPerformed = row.lastPerformed.map(_.toLocalDateTime)
    )
  }

  implicit class PortionBuilder(val row: tables.PortionRow) {
    def asPortion: Portion = Portion(
      uuid = UUID.fromString(row.uuid),
      laserDonutId = UUID.fromString(row.laserDonutId),
      summary = row.summary,
      order = row.order,
      status = Statuses.withName(row.status),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastPerformed = row.lastPerformed.map(_.toLocalDateTime)
    )
  }

  implicit class TodoBuilder(val row: (tables.TodoRow, Seq[(tables.TodoSkillRow, UUID)])) {
    def asTodo: Todo = row match {
      case (todo, associatedSkills) =>
        Todo(
          uuid = UUID.fromString(todo.uuid),
          parentId = UUID.fromString(todo.parentId),
          description = todo.description,
          associatedSkills = associatedSkills.map { case (skill, id) =>
            AssociatedSkill(
              skillId = UUID.fromString(id),
              relevance = SkillRelevances.withName(skill.relevance),
              level = Proficiencies.skillLevel(skill.level)
            )
          },
          order = todo.order,
          isDone = todo.isDone,
          createdOn = todo.createdOn.toLocalDateTime,
          lastModified = todo.lastModified.map(_.toLocalDateTime),
          lastPerformed = todo.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class HobbyBuilder(val row: (tables.HobbyRow, Seq[(tables.HobbySkillRow, UUID)])) {
    def asHobby: Hobby = row match {
      case (hobby, associatedSkills) =>
        Hobby(
          uuid = UUID.fromString(hobby.uuid),
          goalId = hobby.goalId.map(UUID.fromString),
          summary = hobby.summary,
          description = hobby.description,
          associatedSkills = associatedSkills.map { case (skill, id) =>
            AssociatedSkill(
              skillId = UUID.fromString(id),
              relevance = SkillRelevances.withName(skill.relevance),
              level = Proficiencies.skillLevel(skill.level)
            )
          },
          frequency = HobbyFrequencies.withName(hobby.frequency),
          `type` = HobbyTypes.withName(hobby.`type`),
          createdOn = hobby.createdOn.toLocalDateTime,
          lastModified = hobby.lastModified.map(_.toLocalDateTime),
          lastPerformed = hobby.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class OneOffBuilder(val row: (tables.OneOffRow, Seq[(tables.OneOffSkillRow, UUID)])) {
    def asOneOff: OneOff = row match {
      case (oneOff, associatedSkills) =>
        OneOff(
          uuid = UUID.fromString(oneOff.uuid),
          goalId = oneOff.goalId.map(UUID.fromString),
          description = oneOff.description,
          associatedSkills = associatedSkills.map { case (skill, id) =>
            AssociatedSkill(
              skillId = UUID.fromString(id),
              relevance = SkillRelevances.withName(skill.relevance),
              level = Proficiencies.skillLevel(skill.level)
            )
          },
          estimate = oneOff.estimate,
          order = oneOff.order,
          status = Statuses.withName(oneOff.status),
          createdOn = oneOff.createdOn.toLocalDateTime,
          lastModified = oneOff.lastModified.map(_.toLocalDateTime),
          lastPerformed = oneOff.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class ScheduledOneOffBuilder(val row: (tables.ScheduledOneOffRow, Seq[(tables.ScheduledOneOffSkillRow, UUID)])) {
    def asScheduledOneOff: ScheduledOneOff = row match {
      case (scheduledOneOff, associatedSkills) =>
        ScheduledOneOff(
          uuid = UUID.fromString(scheduledOneOff.uuid),
          occursOn = scheduledOneOff.occursOn.toLocalDateTime,
          goalId = scheduledOneOff.goalId.map(UUID.fromString),
          description = scheduledOneOff.description,
          associatedSkills = associatedSkills.map { case (skill, id) =>
            AssociatedSkill(
              skillId = UUID.fromString(id),
              relevance = SkillRelevances.withName(skill.relevance),
              level = Proficiencies.skillLevel(skill.level)
            )
          },
          estimate = scheduledOneOff.estimate,
          status = Statuses.withName(scheduledOneOff.status),
          createdOn = scheduledOneOff.createdOn.toLocalDateTime,
          lastModified = scheduledOneOff.lastModified.map(_.toLocalDateTime),
          lastPerformed = scheduledOneOff.lastPerformed.map(_.toLocalDateTime)
        )
    }
  }

  implicit class SkillCategoryBuilder(val row: (tables.SkillCategoryRow, String)) {
    def asSkillCategory: SkillCategory = row match {
      case (skillCategory, categoryId) =>
        SkillCategory(
          uuid = UUID.fromString(skillCategory.uuid),
          name = skillCategory.name,
          categoryId = UUID.fromString(categoryId)
        )
    }
  }

  implicit class SkillBuilder(val row: (tables.SkillRow, String)) {
    def asSkill: Skill = row match {
      case (skill, categoryId) =>
        Skill(
          uuid = UUID.fromString(skill.uuid),
          name = skill.name,
          categoryId = UUID.fromString(categoryId),
          proficiency = Proficiencies.withName(skill.proficiency),
          practisedHours = skill.practisedHours,
          lastApplied = skill.lastApplied.map(_.toLocalDateTime)
        )
    }
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
  }

  object UpdateDateTime extends Update[LocalDateTime, Timestamp] {
    override def keepOrReplace(newVersion: Option[LocalDateTime], oldVersion: Timestamp): Timestamp = {
      newVersion.map(_.toTimestamp).getOrElse(oldVersion)
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
