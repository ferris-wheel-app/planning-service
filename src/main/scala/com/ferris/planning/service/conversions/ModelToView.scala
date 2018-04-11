package com.ferris.planning.service.conversions

import com.ferris.planning.contract.resource.TypeFields
import com.ferris.planning.model.Model._
import com.ferris.planning.rest.Resources.Out._

object ModelToView {

  implicit class MessageConversion(message: Message) {
    def toView: MessageView = {
      MessageView(
        uuid = message.uuid,
        sender = message.sender,
        content = message.content
      )
    }
  }

  implicit class BacklogItemConversion(backlogItem: BacklogItem) {
    def toView: BacklogItemView = {
      BacklogItemView(
        uuid = backlogItem.uuid,
        summary = backlogItem.summary,
        description = backlogItem.description,
        `type` = TypeFields.BacklogItemType.toString(backlogItem.`type`)
      )
    }
  }

  implicit class EpochConversion(epoch: Epoch) {
    def toView: EpochView = {
      EpochView(
        uuid = epoch.uuid,
        name = epoch.name,
        totem = epoch.totem,
        question = epoch.question
      )
    }
  }

  implicit class YearConversion(year: Year) {
    def toView: YearView = {
      YearView(
        uuid = year.uuid,
        epochId = year.epochId,
        startDate = year.startDate,
        finishDate = year.finishDate
      )
    }
  }

  implicit class ThemeConversion(theme: Theme) {
    def toView: ThemeView = {
      ThemeView(
        uuid = theme.uuid,
        yearId = theme.yearId,
        name = theme.name
      )
    }
  }

  implicit class GoalConversion(goal: Goal) {
    def toView: GoalView = {
      GoalView(
        uuid = goal.uuid,
        themeId = goal.themeId,
        backlogItems = goal.backlogItems,
        summary = goal.summary,
        description = goal.description,
        level = goal.level,
        priority = goal.priority,
        status = TypeFields.GoalStatus.toString(goal.status),
        graduation = TypeFields.GraduationType.toString(goal.graduation)
      )
    }
  }

  implicit class ThreadConversion(thread: Thread) {
    def toView: ThreadView = {
      ThreadView(
        uuid = thread.uuid,
        goalId = thread.goalId,
        summary = thread.summary,
        description = thread.description,
        status = TypeFields.Status.toString(thread.status)
      )
    }
  }

  implicit class WeaveConversion(weave: Weave) {
    def toView: WeaveView = {
      WeaveView(
        uuid = weave.uuid,
        goalId = weave.goalId,
        summary = weave.summary,
        description = weave.description,
        status = TypeFields.Status.toString(weave.status),
        `type` = TypeFields.WeaveType.toString(weave.`type`)
      )
    }
  }

  implicit class LaserDonutConversion(laserDonut: LaserDonut) {
    def toView: LaserDonutView = {
      LaserDonutView(
        uuid = laserDonut.uuid,
        goalId = laserDonut.goalId,
        summary = laserDonut.summary,
        description = laserDonut.description,
        milestone = laserDonut.milestone,
        order = laserDonut.order,
        status = TypeFields.Status.toString(laserDonut.status),
        `type` = TypeFields.DonutType.toString(laserDonut.`type`)
      )
    }
  }

  implicit class PortionConversion(portion: Portion) {
    def toView: PortionView = {
      PortionView(
        uuid = portion.uuid,
        laserDonutId = portion.laserDonutId,
        summary = portion.summary,
        order = portion.order,
        status = TypeFields.Status.toString(portion.status)
      )
    }
  }

  implicit class TodoConversion(todo: Todo) {
    def toView: TodoView = {
      TodoView(
        uuid = todo.uuid,
        portionId = todo.portionId,
        description = todo.description,
        order = todo.order,
        status = TypeFields.Status.toString(todo.status)
      )
    }
  }

  implicit class HobbyConversion(hobby: Hobby) {
    def toView: HobbyView = {
      HobbyView(
        uuid = hobby.uuid,
        goalId = hobby.goalId,
        summary = hobby.summary,
        description = hobby.description,
        frequency = TypeFields.HobbyFrequency.toString(hobby.frequency),
        status = TypeFields.Status.toString(hobby.status),
        `type` = TypeFields.HobbyType.toString(hobby.`type`)
      )
    }
  }
}
