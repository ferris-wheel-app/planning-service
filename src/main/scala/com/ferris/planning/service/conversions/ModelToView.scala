package com.ferris.planning.service.conversions

import com.ferris.planning.contract.resource.Resources.Out._
import com.ferris.planning.model.Model._

object ModelToView {

  implicit class SkillCategoryConversion(skillCategory: SkillCategory) {
    def toView: SkillCategoryView = {
      SkillCategoryView(
        uuid = skillCategory.uuid,
        name = skillCategory.name,
        parentCategory = skillCategory.parentCategory
      )
    }
  }

  implicit class SkillConversion(skill: Skill) {
    def toView: SkillView = {
      SkillView(
        uuid = skill.uuid,
        name = skill.name,
        parentCategory = skill.parentCategory,
        proficiency = TypeResolvers.Proficiency.toString(skill.proficiency),
        practisedHours = skill.practisedHours,
        lastApplied = skill.lastApplied
      )
    }
  }

  implicit class AssociatedSkillConversion(associatedSkill: AssociatedSkill) {
    def toView: AssociatedSkillView = {
      AssociatedSkillView(
        skillId = associatedSkill.skillId,
        relevance = TypeResolvers.SkillRelevance.toString(associatedSkill.relevance),
        level = TypeResolvers.SkillLevel.toString(associatedSkill.level)
      )
    }
  }

  implicit class RelationshipConversion(relationship: Relationship) {
    def toView: RelationshipView = {
      RelationshipView(
        uuid = relationship.uuid,
        name = relationship.name,
        category = TypeResolvers.RelationshipCategory.toString(relationship.category),
        traits = relationship.traits,
        likes = relationship.likes,
        dislikes = relationship.dislikes,
        hobbies = relationship.hobbies,
        lastMeet = relationship.lastMeet
      )
    }
  }

  implicit class MissionConversion(mission: Mission) {
    def toView: MissionView = {
      MissionView(
        uuid = mission.uuid,
        name = mission.name,
        description = mission.description
      )
    }
  }

  implicit class AssociatedMissionConversion(associatedMission: AssociatedMission) {
    def toView: AssociatedMissionView = {
      AssociatedMissionView(
        missionId = associatedMission.missionId,
        level = TypeResolvers.MissionLevel.toString(associatedMission.level)
      )
    }
  }

  implicit class ValueDimensionsConversion(valueDimensions: ValueDimensions) {
    def toView: ValueDimensionsView = {
      ValueDimensionsView(
        associatedMissions = valueDimensions.associatedMissions,
        associatedSkills = valueDimensions.associatedSkills.map(_.toView),
        relationships = valueDimensions.relationships,
        helpsSafetyNet = valueDimensions.helpsSafetyNet,
        expandsWorldView = valueDimensions.expandsWorldView
      )
    }
  }

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
        lastModified = epoch.lastModified,
        associatedMissions = epoch.associatedMissions.map(_.toView)
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
        valueDimensions = goal.valueDimensions.toView,
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
        valueDimensions = thread.valueDimensions.toView,
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
        valueDimensions = weave.valueDimensions.toView,
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
        valueDimensions = laserDonut.valueDimensions.toView,
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
        valueDimensions = portion.valueDimensions.toView,
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
        valueDimensions = hobby.valueDimensions.toView,
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
        valueDimensions = oneOff.valueDimensions.toView,
        estimate = oneOff.estimate,
        order = oneOff.order,
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
        valueDimensions = oneOff.valueDimensions.toView,
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
