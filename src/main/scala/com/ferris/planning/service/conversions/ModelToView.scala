package com.ferris.planning.service.conversions

import com.ferris.planning.contract.resource.Resources.Out._
import com.ferris.planning.model.Model._

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
        `type` = TypeResolvers.BacklogItemType.toString(backlogItem.`type`)
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
        status = TypeResolvers.GoalStatus.toString(goal.status),
        graduation = TypeResolvers.GraduationType.toString(goal.graduation)
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
        status = TypeResolvers.Status.toString(thread.status)
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
        status = TypeResolvers.Status.toString(weave.status),
        `type` = TypeResolvers.WeaveType.toString(weave.`type`)
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
        status = TypeResolvers.Status.toString(laserDonut.status),
        `type` = TypeResolvers.DonutType.toString(laserDonut.`type`)
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
        status = TypeResolvers.Status.toString(portion.status)
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
        status = TypeResolvers.Status.toString(todo.status)
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
        frequency = TypeResolvers.HobbyFrequency.toString(hobby.frequency),
        status = TypeResolvers.Status.toString(hobby.status),
        `type` = TypeResolvers.HobbyType.toString(hobby.`type`)
      )
    }
  }
}
