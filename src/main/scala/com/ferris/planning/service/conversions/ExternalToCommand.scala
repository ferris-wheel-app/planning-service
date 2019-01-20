package com.ferris.planning.service.conversions

import com.ferris.planning.command.Commands._
import com.ferris.planning.contract.resource.Resources.In._
import com.ferris.planning.model.Model.AssociatedSkill

object ExternalToCommand {

  sealed trait CommandConversion[T] {
    def toCommand: T
  }

  implicit class SkillCategoryCreationConversion(skillCategory: SkillCategoryCreation) extends CommandConversion[CreateSkillCategory] {
    override def toCommand = CreateSkillCategory(
      name = skillCategory.name,
      parentCategory = skillCategory.parentCategory
    )
  }

  implicit class SkillCategoryUpdateConversion(skillCategory: SkillCategoryUpdate) extends CommandConversion[UpdateSkillCategory] {
    override def toCommand = UpdateSkillCategory(
      name = skillCategory.name,
      parentCategory = skillCategory.parentCategory
    )
  }

  implicit class SkillCreationConversion(skill: SkillCreation) extends CommandConversion[CreateSkill] {
    override def toCommand = CreateSkill(
      name = skill.name,
      parentCategory = skill.parentCategory,
      proficiency = TypeResolvers.Proficiency.withName(skill.proficiency),
      practisedHours = skill.practisedHours
    )
  }

  implicit class SkillUpdateConversion(skill: SkillUpdate) extends CommandConversion[UpdateSkill] {
    override def toCommand = UpdateSkill(name = skill.name, parentCategory = skill.parentCategory, proficiency = skill.proficiency.map(TypeResolvers.Proficiency.withName), practisedHours = skill.practisedHours)
  }

  implicit class BacklogItemCreationConversion(backlogItem: BacklogItemCreation) extends CommandConversion[CreateBacklogItem] {
    override def toCommand = CreateBacklogItem(
      summary = backlogItem.summary,
      description = backlogItem.description,
      `type` = TypeResolvers.BacklogItemType.withName(backlogItem.`type`)
    )
  }

  implicit class BacklogItemUpdateConversion(backlogItem: BacklogItemUpdate) extends CommandConversion[UpdateBacklogItem] {
    override def toCommand = UpdateBacklogItem(
      summary = backlogItem.summary,
      description = backlogItem.description,
      `type` = backlogItem.`type`.map(TypeResolvers.BacklogItemType.withName)
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
      startDate = year.startDate
    )
  }

  implicit class YearUpdateConversion(year: YearUpdate) extends CommandConversion[UpdateYear] {
    override def toCommand = UpdateYear(
      epochId = year.epochId,
      startDate = year.startDate
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
      associatedSkills = goal.associatedSkills.map(_.toCommand),
      status = TypeResolvers.GoalStatus.withName(goal.status),
      graduation = TypeResolvers.GraduationType.withName(goal.graduation)
    )
  }

  implicit class GoalUpdateConversion(goal: GoalUpdate) extends CommandConversion[UpdateGoal] {
    override def toCommand = UpdateGoal(
      themeId = goal.themeId,
      backlogItems = goal.backlogItems,
      summary = goal.summary,
      description = goal.description,
      associatedSkills = goal.associatedSkills.map(_.map(_.toCommand)),
      status = goal.status.map(TypeResolvers.GoalStatus.withName),
      graduation = goal.graduation.map(TypeResolvers.GraduationType.withName)
    )
  }

  implicit class ThreadCreationConversion(thread: ThreadCreation) extends CommandConversion[CreateThread] {
    override def toCommand = CreateThread(
      goalId = thread.goalId,
      summary = thread.summary,
      description = thread.description,
      associatedSkills = thread.associatedSkills.map(_.toCommand),
      performance = TypeResolvers.ThreadPerformance.withName(thread.performance)
    )
  }

  implicit class ThreadUpdateConversion(thread: ThreadUpdate) extends CommandConversion[UpdateThread] {
    override def toCommand = UpdateThread(
      goalId = thread.goalId,
      summary = thread.summary,
      description = thread.description,
      associatedSkills = thread.associatedSkills.map(_.map(_.toCommand)),
      performance = thread.performance.map(TypeResolvers.ThreadPerformance.withName)
    )
  }

  implicit class WeaveCreationConversion(weave: WeaveCreation) extends CommandConversion[CreateWeave] {
    override def toCommand = CreateWeave(
      goalId = weave.goalId,
      summary = weave.summary,
      description = weave.description,
      associatedSkills = weave.associatedSkills.map(_.toCommand),
      status = TypeResolvers.Status.withName(weave.status),
      `type` = TypeResolvers.WeaveType.withName(weave.`type`)
    )
  }

  implicit class WeaveUpdateConversion(weave: WeaveUpdate) extends CommandConversion[UpdateWeave] {
    override def toCommand = UpdateWeave(
      goalId = weave.goalId,
      summary = weave.summary,
      description = weave.description,
      associatedSkills = weave.associatedSkills.map(_.map(_.toCommand)),
      status = weave.status.map(TypeResolvers.Status.withName),
      `type` = weave.`type`.map(TypeResolvers.WeaveType.withName)
    )
  }

  implicit class LaserDonutCreationConversion(laserDonut: LaserDonutCreation) extends CommandConversion[CreateLaserDonut] {
    override def toCommand = CreateLaserDonut(
      goalId = laserDonut.goalId,
      summary = laserDonut.summary,
      description = laserDonut.description,
      milestone = laserDonut.milestone,
      status = TypeResolvers.Status.withName(laserDonut.status),
      `type` = TypeResolvers.DonutType.withName(laserDonut.`type`)
    )
  }

  implicit class LaserDonutUpdateConversion(laserDonut: LaserDonutUpdate) extends CommandConversion[UpdateLaserDonut] {
    override def toCommand = UpdateLaserDonut(
      goalId = laserDonut.goalId,
      summary = laserDonut.summary,
      description = laserDonut.description,
      milestone = laserDonut.milestone,
      status = laserDonut.status.map(TypeResolvers.Status.withName),
      `type` = laserDonut.`type`.map(TypeResolvers.DonutType.withName)
    )
  }

  implicit class PortionCreationConversion(portion: PortionCreation) extends CommandConversion[CreatePortion] {
    override def toCommand = CreatePortion(
      laserDonutId = portion.laserDonutId,
      summary = portion.summary,
      status = TypeResolvers.Status.withName(portion.status),
      associatedSkills = portion.associatedSkills.map(_.toCommand)
    )
  }

  implicit class PortionUpdateConversion(portion: PortionUpdate) extends CommandConversion[UpdatePortion] {
    override def toCommand = UpdatePortion(
      laserDonutId = portion.laserDonutId,
      summary = portion.summary,
      status = portion.status.map(TypeResolvers.Status.withName),
      associatedSkills = portion.associatedSkills.map(_.map(_.toCommand))
    )
  }

  implicit class TodoCreationConversion(todo: TodoCreation) extends CommandConversion[CreateTodo] {
    override def toCommand = CreateTodo(
      parentId = todo.parentId,
      description = todo.description
    )
  }

  implicit class TodoUpdateConversion(todo: TodoUpdate) extends CommandConversion[UpdateTodo] {
    override def toCommand = UpdateTodo(
      parentId = todo.parentId,
      description = todo.description,
      isDone = todo.isDone
    )
  }

  implicit class HobbyCreationConversion(hobby: HobbyCreation) extends CommandConversion[CreateHobby] {
    override def toCommand = CreateHobby(
      goalId = hobby.goalId,
      summary = hobby.summary,
      description = hobby.description,
      associatedSkills = hobby.associatedSkills.map(_.toCommand),
      frequency = TypeResolvers.HobbyFrequency.withName(hobby.frequency),
      `type` = TypeResolvers.HobbyType.withName(hobby.`type`)
    )
  }

  implicit class HobbyUpdateConversion(hobby: HobbyUpdate) extends CommandConversion[UpdateHobby] {
    override def toCommand = UpdateHobby(
      goalId = hobby.goalId,
      summary = hobby.summary,
      description = hobby.description,
      associatedSkills = hobby.associatedSkills.map(_.map(_.toCommand)),
      frequency = hobby.frequency.map(TypeResolvers.HobbyFrequency.withName),
      `type` = hobby.`type`.map(TypeResolvers.HobbyType.withName)
    )
  }

  implicit class OneOffCreationConversion(oneOff: OneOffCreation) extends CommandConversion[CreateOneOff] {
    override def toCommand = CreateOneOff(
      goalId = oneOff.goalId,
      description = oneOff.description,
      associatedSkills = oneOff.associatedSkills.map(_.toCommand),
      estimate = oneOff.estimate,
      status = TypeResolvers.Status.withName(oneOff.status)
    )
  }

  implicit class OneOffUpdateConversion(oneOff: OneOffUpdate) extends CommandConversion[UpdateOneOff] {
    override def toCommand = UpdateOneOff(
      goalId = oneOff.goalId,
      description = oneOff.description,
      associatedSkills = oneOff.associatedSkills.map(_.map(_.toCommand)),
      estimate = oneOff.estimate,
      status = oneOff.status.map(TypeResolvers.Status.withName)
    )
  }

  implicit class ScheduledOneOffCreationConversion(oneOff: ScheduledOneOffCreation) extends CommandConversion[CreateScheduledOneOff] {
    override def toCommand = CreateScheduledOneOff(
      occursOn = oneOff.occursOn,
      goalId = oneOff.goalId,
      description = oneOff.description,
      associatedSkills = oneOff.associatedSkills.map(_.toCommand),
      estimate = oneOff.estimate,
      status = TypeResolvers.Status.withName(oneOff.status)
    )
  }

  implicit class ScheduledOneOffUpdateConversion(oneOff: ScheduledOneOffUpdate) extends CommandConversion[UpdateScheduledOneOff] {
    override def toCommand = UpdateScheduledOneOff(
      occursOn = oneOff.occursOn,
      goalId = oneOff.goalId,
      description = oneOff.description,
      associatedSkills = oneOff.associatedSkills.map(_.map(_.toCommand)),
      estimate = oneOff.estimate,
      status = oneOff.status.map(TypeResolvers.Status.withName)
    )
  }

  implicit class ListUpdateConversion(list: ListUpdate) extends CommandConversion[UpdateList] {
    override def toCommand = UpdateList(
      reordered = list.reordered
    )
  }

  implicit class PyramidOfImportanceUpsertConversion(pyramid: PyramidOfImportanceUpsert) extends CommandConversion[UpsertPyramidOfImportance] {
    override def toCommand = UpsertPyramidOfImportance(
      tiers = pyramid.tiers.map(tier => UpsertTier(tier.laserDonuts))
    )
  }

  implicit class AssociatedSkillConversion(associatedSkill: AssociatedSkillInsertion) extends CommandConversion[AssociatedSkill] {
    override def toCommand = AssociatedSkill(
      skillId = associatedSkill.skillId,
      relevance = TypeResolvers.SkillRelevance.withName(associatedSkill.relevance),
      level = TypeResolvers.SkillLevel.withName(associatedSkill.level)
    )
  }
}
