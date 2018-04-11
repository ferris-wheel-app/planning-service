package com.ferris.planning.service.conversions

import com.ferris.planning.command.Commands._
import com.ferris.planning.contract.resource.TypeFields
import com.ferris.planning.rest.Resources.In._

object ExternalToCommand {

  sealed trait CommandConversion[T] {
    def toCommand: T
  }

  implicit class MessageCreationConversion(message: MessageCreation) extends CommandConversion[CreateMessage] {
    override def toCommand = CreateMessage(
      sender = message.sender,
      content = message.content
    )
  }

  implicit class MessageUpdateConversion(message: MessageUpdate) extends CommandConversion[UpdateMessage] {
    override def toCommand = UpdateMessage(
      sender = message.sender,
      content = message.content
    )
  }

  implicit class BacklogItemCreationConversion(backlogItem: BacklogItemCreation) extends CommandConversion[CreateBacklogItem] {
    override def toCommand = CreateBacklogItem(
      summary = backlogItem.summary,
      description = backlogItem.description,
      `type` = TypeFields.BacklogItemType.withName(backlogItem.`type`)
    )
  }

  implicit class BacklogItemUpdateConversion(backlogItem: BacklogItemUpdate) extends CommandConversion[UpdateBacklogItem] {
    override def toCommand = UpdateBacklogItem(
      summary = backlogItem.summary,
      description = backlogItem.description,
      `type` = backlogItem.`type`.map(TypeFields.BacklogItemType.withName)
    )
  }

  implicit class EpochCreationConversion(epoch: EpochCreation) extends CommandConversion[CreateEpoch] {
    override def toCommand = CreateEpoch(
      name = epoch.name,
      totem = epoch.totem,
      question = epoch.question
    )
  }

  implicit class EpochUpdateConversion(epoch: EpochUpdate) extends CommandConversion[UpdateEpoch] {
    override def toCommand = UpdateEpoch(
      name = epoch.name,
      totem = epoch.totem,
      question = epoch.question
    )
  }

  implicit class YearCreationConversion(year: YearCreation) extends CommandConversion[CreateYear] {
    override def toCommand = CreateYear(
      epochId = year.epochId,
      startDate = year.startDate,
      finishDate = year.finishDate
    )
  }

  implicit class YearUpdateConversion(year: YearUpdate) extends CommandConversion[UpdateYear] {
    override def toCommand = UpdateYear(
      epochId = year.epochId,
      startDate = year.startDate,
      finishDate = year.finishDate
    )
  }

  implicit class ThemeCreationConversion(theme: ThemeCreation) extends CommandConversion[CreateTheme] {
    override def toCommand = CreateTheme(
      yearId = theme.yearId,
      name = theme.name
    )
  }

  implicit class ThemeUpdateConversion(theme: ThemeUpdate) extends CommandConversion[UpdateTheme] {
    override def toCommand = UpdateTheme(
      yearId = theme.yearId,
      name = theme.name
    )
  }

  implicit class GoalCreationConversion(goal: GoalCreation) extends CommandConversion[CreateGoal] {
    override def toCommand = CreateGoal(
      themeId = goal.themeId,
      backlogItems = goal.backlogItems,
      summary = goal.summary,
      description = goal.description,
      level = goal.level,
      priority = goal.priority,
      status = TypeFields.GoalStatus.withName(goal.status),
      graduation = TypeFields.GraduationType.withName(goal.graduation)
    )
  }

  implicit class GoalUpdateConversion(goal: GoalUpdate) extends CommandConversion[UpdateGoal] {
    override def toCommand = UpdateGoal(
      themeId = goal.themeId,
      backlogItems = goal.backlogItems,
      summary = goal.summary,
      description = goal.description,
      level = goal.level,
      priority = goal.priority,
      status = goal.status.map(TypeFields.GoalStatus.withName),
      graduation = goal.graduation.map(TypeFields.GraduationType.withName)
    )
  }

  implicit class ThreadCreationConversion(thread: ThreadCreation) extends CommandConversion[CreateThread] {
    override def toCommand = CreateThread(
      goalId = thread.goalId,
      summary = thread.summary,
      description = thread.description,
      status = TypeFields.Status.withName(thread.status)
    )
  }

  implicit class ThreadUpdateConversion(thread: ThreadUpdate) extends CommandConversion[UpdateThread] {
    override def toCommand = UpdateThread(
      goalId = thread.goalId,
      summary = thread.summary,
      description = thread.description,
      status = thread.status.map(TypeFields.Status.withName)
    )
  }

  implicit class WeaveCreationConversion(weave: WeaveCreation) extends CommandConversion[CreateWeave] {
    override def toCommand = CreateWeave(
      goalId = weave.goalId,
      summary = weave.summary,
      description = weave.description,
      status = TypeFields.Status.withName(weave.status),
      `type` = TypeFields.WeaveType.withName(weave.`type`)
    )
  }

  implicit class WeaveUpdateConversion(weave: WeaveUpdate) extends CommandConversion[UpdateWeave] {
    override def toCommand = UpdateWeave(
      goalId = weave.goalId,
      summary = weave.summary,
      description = weave.description,
      status = weave.status.map(TypeFields.Status.withName),
      `type` = weave.`type`.map(TypeFields.WeaveType.withName)
    )
  }

  implicit class LaserDonutCreationConversion(laserDonut: LaserDonutCreation) extends CommandConversion[CreateLaserDonut] {
    override def toCommand = CreateLaserDonut(
      goalId = laserDonut.goalId,
      summary = laserDonut.summary,
      description = laserDonut.description,
      milestone = laserDonut.milestone,
      order = laserDonut.order,
      status = TypeFields.Status.withName(laserDonut.status),
      `type` = TypeFields.DonutType.withName(laserDonut.`type`)
    )
  }

  implicit class LaserDonutUpdateConversion(laserDonut: LaserDonutUpdate) extends CommandConversion[UpdateLaserDonut] {
    override def toCommand = UpdateLaserDonut(
      goalId = laserDonut.goalId,
      summary = laserDonut.summary,
      description = laserDonut.description,
      milestone = laserDonut.milestone,
      order = laserDonut.order,
      status = laserDonut.status.map(TypeFields.Status.withName),
      `type` = laserDonut.`type`.map(TypeFields.DonutType.withName)
    )
  }

  implicit class PortionCreationConversion(portion: PortionCreation) extends CommandConversion[CreatePortion] {
    override def toCommand = CreatePortion(
      laserDonutId = portion.laserDonutId,
      summary = portion.summary,
      order = portion.order,
      status = TypeFields.Status.withName(portion.status)
    )
  }

  implicit class PortionUpdateConversion(portion: PortionUpdate) extends CommandConversion[UpdatePortion] {
    override def toCommand = UpdatePortion(
      laserDonutId = portion.laserDonutId,
      summary = portion.summary,
      order = portion.order,
      status = portion.status.map(TypeFields.Status.withName)
    )
  }

  implicit class TodoCreationConversion(todo: TodoCreation) extends CommandConversion[CreateTodo] {
    override def toCommand = CreateTodo(
      portionId = todo.portionId,
      description = todo.description,
      order = todo.order,
      status = TypeFields.Status.withName(todo.status)
    )
  }

  implicit class TodoUpdateConversion(todo: TodoUpdate) extends CommandConversion[UpdateTodo] {
    override def toCommand = UpdateTodo(
      portionId = todo.portionId,
      description = todo.description,
      order = todo.order,
      status = todo.status.map(TypeFields.Status.withName)
    )
  }

  implicit class HobbyCreationConversion(hobby: HobbyCreation) extends CommandConversion[CreateHobby] {
    override def toCommand = CreateHobby(
      goalId = hobby.goalId,
      summary = hobby.summary,
      description = hobby.description,
      frequency = TypeFields.HobbyFrequency.withName(hobby.frequency),
      status = TypeFields.Status.withName(hobby.status),
      `type` = TypeFields.HobbyType.withName(hobby.`type`)
    )
  }

  implicit class HobbyUpdateConversion(hobby: HobbyUpdate) extends CommandConversion[UpdateHobby] {
    override def toCommand = UpdateHobby(
      goalId = hobby.goalId,
      summary = hobby.summary,
      description = hobby.description,
      frequency = hobby.frequency.map(TypeFields.HobbyFrequency.withName),
      status = hobby.status.map(TypeFields.Status.withName),
      `type` = hobby.`type`.map(TypeFields.HobbyType.withName)
    )
  }
}
