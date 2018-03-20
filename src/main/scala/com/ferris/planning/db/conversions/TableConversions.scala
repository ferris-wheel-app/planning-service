package com.ferris.planning.db.conversions

import java.sql.Timestamp
import java.util.UUID

import akka.http.scaladsl.model.DateTime
import com.ferris.planning.table.Tables
import com.ferris.planning.model.Model._

import scala.language.implicitConversions

class TableConversions(val tables: Tables) {

  implicit class MessageBuilder(val row: tables.MessageRow) {
    def asMessage: Message = Message(
      uuid = UUID.fromString(row.uuid),
      sender = row.sender,
      content = row.content
    )
  }

  implicit class BacklogItemBuilder(val row: tables.BacklogItemRow) {
    def asBacklogItem: BacklogItem = BacklogItem(
      uuid = UUID.fromString(row.uuid),
      summary = row.summary,
      description = row.description,
      `type` = BacklogItemTypes.withName(row.`type`)
    )
  }

  implicit class EpochBuilder(val row: tables.EpochRow) {
    def asEpoch: Epoch = Epoch(
      uuid = UUID.fromString(row.uuid),
      name = row.name,
      totem = row.totem,
      question = row.question
    )
  }

  implicit class YearBuilder(val row: tables.YearRow) {
    def asYear: Year = Year(
      uuid = UUID.fromString(row.uuid),
      epochId = UUID.fromString(row.epochId),
      startDate = row.startDate,
      finishDate = row.finishDate
    )
  }

  implicit class ThemeBuilder(val row: tables.ThemeRow) {
    def asTheme: Theme = Theme(
      uuid = UUID.fromString(row.uuid),
      yearId = UUID.fromString(row.yearId),
      name = row.name
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
        level = goal.level,
        priority = goal.priority,
        status = GoalStatuses.withName(goal.status),
        graduation = GraduationTypes.withName(goal.graduation)
      )
    }
  }

  implicit class ThreadBuilder(val row: tables.ThreadRow) {
    def asThread: Thread = Thread(
      uuid = UUID.fromString(row.uuid),
      goalId = row.goalId.map(UUID.fromString),
      summary = row.summary,
      description = row.description,
      status = Statuses.withName(row.status)
    )
  }

  implicit class WeaveBuilder(val row: tables.WeaveRow) {
    def asWeave: Weave = Weave(
      uuid = UUID.fromString(row.uuid),
      goalId = row.goalId.map(UUID.fromString),
      summary = row.summary,
      description = row.description,
      status = Statuses.withName(row.status),
      `type` = WeaveTypes.withName(row.`type`)
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
      `type` = DonutTypes.withName(row.`type`)
    )
  }

  implicit class PortionBuilder(val row: tables.PortionRow) {
    def asPortion: Portion = Portion(
      uuid = UUID.fromString(row.uuid),
      laserDonutId = UUID.fromString(row.laserDonutId),
      summary = row.summary,
      order = row.order,
      status = Statuses.withName(row.status)
    )
  }

  implicit class TodoBuilder(val row: tables.TodoRow) {
    def asTodo: Todo = Todo(
      uuid = UUID.fromString(row.uuid),
      portionId = UUID.fromString(row.portionId),
      description = row.description,
      order = row.order,
      status = Statuses.withName(row.status)
    )
  }

  implicit class HobbyBuilder(val row: tables.HobbyRow) {
    def asHobby: Hobby = Hobby(
      uuid = UUID.fromString(row.uuid),
      goalId = row.goalId.map(UUID.fromString),
      summary = row.summary,
      description = row.description,
      frequency = HobbyFrequencies.withName(row.frequency),
      status = Statuses.withName(row.status),
      `type` = HobbyTypes.withName(row.`type`)
    )
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

  object UpdateDate extends Update[DateTime, Timestamp] {
    override def keepOrReplace(newVersion: Option[DateTime], oldVersion: Timestamp): Timestamp = {
      newVersion.map(dateTime2Timestamp).getOrElse(oldVersion)
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

  implicit def uuid2String(uuid: UUID): String = uuid.toString

  implicit def uuid2String(uuid: Option[UUID]): Option[String] = uuid.map(_.toString)

  implicit def uuid2String(uuid: Seq[UUID]): Seq[String] = uuid.map(_.toString)

  implicit def timestamp2DateTime(date: Timestamp): DateTime = DateTime.apply(date.getTime)

  implicit def dateTime2Timestamp(date: DateTime): Timestamp = new Timestamp(date.clicks)

  implicit def byte2Boolean(byte: Byte): Boolean = byte == 1

  implicit def boolean2Byte(bool: Boolean): Byte = if (bool) 1 else 0
}
