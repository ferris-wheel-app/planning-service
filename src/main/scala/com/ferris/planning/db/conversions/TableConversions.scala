package com.ferris.planning.db.conversions

import java.util.UUID

import akka.http.scaladsl.model.DateTime
import com.ferris.planning.table.Tables
import com.ferris.planning.model.Model._

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
      epochId = UUID.fromString(row.uuid),
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

  implicit def sqlDate2DateTime(date: java.sql.Date): DateTime = DateTime.apply(date.getTime)

  implicit def byte2Boolean(byte: Byte): Boolean = byte == 1
}
