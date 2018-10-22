package com.ferris.planning.service.conversions

import com.ferris.planning.contract.resource.Resources.Out._
import com.ferris.planning.model.Model._

object ModelToView {

  implicit class BacklogItemConversion(backlogItem: BacklogItem) {
    def toView: BacklogItemView = {
      BacklogItemView(
        uuid = backlogItem.uuid,
        summary = backlogItem.summary,
        description = backlogItem.description,
        `type` = TypeResolvers.BacklogItemType.toString(backlogItem.`type`),
        createdOn = backlogItem.createdOn,
        lastModified = backlogItem.lastModified
      )
    }
  }

  implicit class EpochConversion(epoch: Epoch) {
    def toView: EpochView = {
      EpochView(
        uuid = epoch.uuid,
        name = epoch.name,
        totem = epoch.totem,
        question = epoch.question,
        createdOn = epoch.createdOn,
        lastModified = epoch.lastModified
      )
    }
  }

  implicit class YearConversion(year: Year) {
    def toView: YearView = {
      YearView(
        uuid = year.uuid,
        epochId = year.epochId,
        startDate = year.startDate,
        finishDate = year.finishDate,
        createdOn = year.createdOn,
        lastModified = year.lastModified
      )
    }
  }

  implicit class ThemeConversion(theme: Theme) {
    def toView: ThemeView = {
      ThemeView(
        uuid = theme.uuid,
        yearId = theme.yearId,
        name = theme.name,
        createdOn = theme.createdOn,
        lastModified = theme.lastModified
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
        status = TypeResolvers.GoalStatus.toString(goal.status),
        graduation = TypeResolvers.GraduationType.toString(goal.graduation),
        createdOn = goal.createdOn,
        lastModified = goal.lastModified
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
        performance = TypeResolvers.ThreadPerformance.toString(thread.performance),
        createdOn = thread.createdOn,
        lastModified = thread.lastModified,
        lastPerformed = thread.lastPerformed
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
        `type` = TypeResolvers.WeaveType.toString(weave.`type`),
        createdOn = weave.createdOn,
        lastModified = weave.lastModified,
        lastPerformed = weave.lastPerformed
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
        `type` = TypeResolvers.DonutType.toString(laserDonut.`type`),
        createdOn = laserDonut.createdOn,
        lastModified = laserDonut.lastModified,
        lastPerformed = laserDonut.lastPerformed
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
        status = TypeResolvers.Status.toString(portion.status),
        createdOn = portion.createdOn,
        lastModified = portion.lastModified,
        lastPerformed = portion.lastPerformed
      )
    }
  }

  implicit class TodoConversion(todo: Todo) {
    def toView: TodoView = {
      TodoView(
        uuid = todo.uuid,
        parentId = todo.parentId,
        description = todo.description,
        order = todo.order,
        isDone = todo.isDone,
        createdOn = todo.createdOn,
        lastModified = todo.lastModified,
        lastPerformed = todo.lastPerformed
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
        `type` = TypeResolvers.HobbyType.toString(hobby.`type`),
        createdOn = hobby.createdOn,
        lastModified = hobby.lastModified,
        lastPerformed = hobby.lastPerformed
      )
    }
  }

  implicit class OneOffConversion(oneOff: OneOff) {
    def toView: OneOffView = {
      OneOffView(
        uuid = oneOff.uuid,
        goalId = oneOff.goalId,
        description = oneOff.description,
        estimate = oneOff.estimate,
        status = TypeResolvers.Status.toString(oneOff.status),
        createdOn = oneOff.createdOn,
        lastModified = oneOff.lastModified,
        lastPerformed = oneOff.lastPerformed
      )
    }
  }

  implicit class ScheduledOneOffConversion(oneOff: ScheduledOneOff) {
    def toView: ScheduledOneOffView = {
      ScheduledOneOffView(
        uuid = oneOff.uuid,
        occursOn = oneOff.occursOn,
        goalId = oneOff.goalId,
        description = oneOff.description,
        estimate = oneOff.estimate,
        status = TypeResolvers.Status.toString(oneOff.status),
        createdOn = oneOff.createdOn,
        lastModified = oneOff.lastModified,
        lastPerformed = oneOff.lastPerformed
      )
    }
  }

  implicit class PyramidOfImportanceConversion(pyramid: PyramidOfImportance) {
    def toView: PyramidOfImportanceView = {
      PyramidOfImportanceView(
        tiers = pyramid.tiers.map(tier => TierView(tier.laserDonuts))
      )
    }
  }
}
