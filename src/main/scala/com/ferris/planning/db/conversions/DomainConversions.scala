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

  implicit class GoalBuilder(val rows: (tables.GoalRow, Seq[tables.BacklogItemRow])) {
    def asGoal: Goal = rows match { case (goal, backlogItems) =>
      Goal(
        uuid = UUID.fromString(goal.uuid),
        themeId = UUID.fromString(goal.themeId),
        backlogItems = backlogItems.map(item => UUID.fromString(item.uuid)),
        summary = goal.summary,
        description = goal.description,
        status = GoalStatuses.withName(goal.status),
        graduation = GraduationTypes.withName(goal.graduation),
        createdOn = goal.createdOn.toLocalDateTime,
        lastModified = goal.lastModified.map(_.toLocalDateTime)
      )
    }
  }

  implicit class ThreadBuilder(val row: tables.ThreadRow) {
    def asThread: Thread = Thread(
      uuid = UUID.fromString(row.uuid),
      goalId = row.goalId.map(UUID.fromString),
      summary = row.summary,
      description = row.description,
      performance = ThreadPerformances.withName(row.performance),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastPerformed = row.lastPerformed.map(_.toLocalDateTime)
    )
  }

  implicit class WeaveBuilder(val row: tables.WeaveRow) {
    def asWeave: Weave = Weave(
      uuid = UUID.fromString(row.uuid),
      goalId = row.goalId.map(UUID.fromString),
      summary = row.summary,
      description = row.description,
      status = Statuses.withName(row.status),
      `type` = WeaveTypes.withName(row.`type`),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastPerformed = row.lastPerformed.map(_.toLocalDateTime)
    )
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

  implicit class HobbyBuilder(val row: tables.HobbyRow) {
    def asHobby: Hobby = Hobby(
      uuid = UUID.fromString(row.uuid),
      goalId = row.goalId.map(UUID.fromString),
      summary = row.summary,
      description = row.description,
      frequency = HobbyFrequencies.withName(row.frequency),
      `type` = HobbyTypes.withName(row.`type`),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastPerformed = row.lastPerformed.map(_.toLocalDateTime)
    )
  }

  implicit class OneOffBuilder(val row: tables.OneOffRow) {
    def asOneOff: OneOff = OneOff(
      uuid = UUID.fromString(row.uuid),
      goalId = row.goalId.map(UUID.fromString),
      description = row.description,
      estimate = row.estimate,
      order = row.order,
      status = Statuses.withName(row.status),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastPerformed = row.lastPerformed.map(_.toLocalDateTime)
    )
  }

  implicit class ScheduledOneOffBuilder(val row: tables.ScheduledOneOffRow) {
    def asScheduledOneOff: ScheduledOneOff = ScheduledOneOff(
      uuid = UUID.fromString(row.uuid),
      occursOn = row.occursOn.toLocalDateTime,
      goalId = row.goalId.map(UUID.fromString),
      description = row.description,
      estimate = row.estimate,
      status = Statuses.withName(row.status),
      createdOn = row.createdOn.toLocalDateTime,
      lastModified = row.lastModified.map(_.toLocalDateTime),
      lastPerformed = row.lastPerformed.map(_.toLocalDateTime)
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
