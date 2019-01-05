package com.ferris.planning.contract.validation

import java.time.LocalDateTime

import com.ferris.microservice.validation.InputValidation
import com.ferris.planning.contract.resource.Resources.In._
import com.ferris.planning.contract.resource.TypeFields._
import com.ferris.utils.FerrisImplicits._

object InputValidators extends InputValidation {
  private val CONVERSION_RATE = 60 * 60 * 1000

  private val TypeField = "type"
  private val BacklogItemsField = "backlogItems"
  private val GraduationField = "graduation"
  private val StatusField = "status"
  private val PerformanceField = "performance"
  private val FrequencyField = "frequency"
  private val LaserDonutsField = "laserDonuts"
  private val TiersField = "tiers"
  private val EstimateField = "estimate"
  private val ProficiencyField = "proficiency"
  private val SkillLevelField = "level"
  private val SkillRelevanceField = "relevance"

  private val MaxGoalBacklogItemsSize = 10
  private val MaxTierSize = 10
  private val MinTierSize = 5
  private val MinPyramidSize = 5
  private val MaxOneOffEstimateInHours = 4
  private val MaxOneOffEstimate = MaxOneOffEstimateInHours * CONVERSION_RATE

  def checkValidity(backlogItemCreation: BacklogItemCreation): Unit = {
    checkField(BacklogItemType.values.contains(backlogItemCreation.`type`), TypeField)
  }

  def checkValidity(backlogItemUpdate: BacklogItemUpdate): Unit = {
    backlogItemUpdate.`type`.foreach(`type` => checkField(BacklogItemType.values.contains(`type`), TypeField))
  }

  def checkValidity(goalCreation: GoalCreation): Unit = {
    checkMaxSize(goalCreation.backlogItems, BacklogItemsField, MaxGoalBacklogItemsSize)
    checkForDuplication(goalCreation.backlogItems, BacklogItemsField)
    checkField(GraduationType.values.contains(goalCreation.graduation), GraduationField)
    checkField(GoalStatus.values.contains(goalCreation.status), StatusField)
  }

  def checkValidity(goalUpdate: GoalUpdate): Unit = {
    goalUpdate.backlogItems.foreach(backlogItems => checkMaxSize(backlogItems, BacklogItemsField, MaxGoalBacklogItemsSize))
    goalUpdate.backlogItems.foreach(backlogItems => checkForDuplication(backlogItems, BacklogItemsField))
    goalUpdate.graduation.foreach(graduation => checkField(GraduationType.values.contains(graduation), GraduationField))
    goalUpdate.status.foreach(status => checkField(GoalStatus.values.contains(status), StatusField))
  }

  def checkValidity(threadCreation: ThreadCreation): Unit = {
    checkField(ThreadPerformance.values.contains(threadCreation.performance), PerformanceField)
  }

  def checkValidity(threadUpdate: ThreadUpdate): Unit = {
    threadUpdate.performance.foreach(performance => checkField(ThreadPerformance.values.contains(performance), PerformanceField))
  }

  def checkValidity(weaveCreation: WeaveCreation): Unit = {
    checkField(WeaveType.values.contains(weaveCreation.`type`), TypeField)
    checkField(Status.values.contains(weaveCreation.status), StatusField)
  }

  def checkValidity(weaveUpdate: WeaveUpdate): Unit = {
    weaveUpdate.`type`.foreach(`type` => checkField(WeaveType.values.contains(`type`), TypeField))
    weaveUpdate.status.foreach(status => checkField(Status.values.contains(status), StatusField))
  }

  def checkValidity(laserDonutCreation: LaserDonutCreation): Unit = {
    checkField(DonutType.values.contains(laserDonutCreation.`type`), TypeField)
    checkField(Status.values.contains(laserDonutCreation.status), StatusField)
  }

  def checkValidity(laserDonutUpdate: LaserDonutUpdate): Unit = {
    laserDonutUpdate.`type`.foreach(`type` => checkField(DonutType.values.contains(`type`), TypeField))
    laserDonutUpdate.status.foreach(status => checkField(Status.values.contains(status), StatusField))
  }

  def checkValidity(portionCreation: PortionCreation): Unit = {
    checkField(Status.values.contains(portionCreation.status), StatusField)
  }

  def checkValidity(portionUpdate: PortionUpdate): Unit = {
    portionUpdate.status.foreach(status => checkField(Status.values.contains(status), StatusField))
  }

  def checkValidity(hobbyCreation: HobbyCreation): Unit = {
    checkField(HobbyFrequency.values.contains(hobbyCreation.frequency), FrequencyField)
    checkField(HobbyType.values.contains(hobbyCreation.`type`), TypeField)
  }

  def checkValidity(hobbyUpdate: HobbyUpdate): Unit = {
    hobbyUpdate.frequency.foreach(frequency => checkField(HobbyFrequency.values.contains(frequency), FrequencyField))
    hobbyUpdate.`type`.foreach(`type` => checkField(HobbyType.values.contains(`type`), TypeField))
  }

  def checkValidity(oneOffCreation: OneOffCreation): Unit = {
    checkField(Status.values.contains(oneOffCreation.status), StatusField)
    checkEstimate(oneOffCreation.estimate)
  }

  def checkValidity(oneOffUpdate: OneOffUpdate): Unit = {
    oneOffUpdate.status.foreach(status => checkField(Status.values.contains(status), StatusField))
    oneOffUpdate.estimate.foreach(checkEstimate)
  }

  def checkValidity(scheduledOneOffCreation: ScheduledOneOffCreation): Unit = {
    checkField(Status.values.contains(scheduledOneOffCreation.status), StatusField)
    checkScheduleSpan(scheduledOneOffCreation.occursOn, scheduledOneOffCreation.estimate)
  }

  def checkValidity(scheduledOneOffUpdate: ScheduledOneOffUpdate): Unit = {
    scheduledOneOffUpdate.status.foreach(status => checkField(Status.values.contains(status), StatusField))
    (scheduledOneOffUpdate.occursOn, scheduledOneOffUpdate.estimate) match {
      case (Some(occursOn), Some(estimate)) => checkScheduleSpan(occursOn, estimate)
      case _ => //
    }
  }

  def checkValidity(tierCreation: TierUpsert): Unit = {
    checkMaxSize(tierCreation.laserDonuts, LaserDonutsField, MaxTierSize)
    checkMinSize(tierCreation.laserDonuts, LaserDonutsField, MinTierSize)
    checkForDuplication(tierCreation.laserDonuts, LaserDonutsField)
  }

  def checkValidity(pyramidCreation: PyramidOfImportanceUpsert): Unit = {
    checkMinSize(pyramidCreation.tiers, TiersField, MinPyramidSize)
  }

  def checkValidity(skillCreation: SkillCreation): Unit = {
    checkField(Proficiency.values.contains(skillCreation.proficiency), ProficiencyField)
  }

  def checkValidity(skillUpdate: SkillUpdate): Unit = {
    skillUpdate.proficiency.foreach(proficiency => checkField(Proficiency.values.contains(proficiency), ProficiencyField))
  }

  def checkValidity(skillAssociation: AssociatedSkillInsertion): Unit = {
    checkField(SkillRelevance.values.contains(skillAssociation.relevance), SkillRelevanceField)
    checkField(SkillLevel.values.contains(skillAssociation.level), SkillLevelField)
  }

  private def checkEstimate(estimate: Long): Unit = {
    checkField(estimate <= MaxOneOffEstimate, EstimateField, s"a one-off estimate must be a maximum of $MaxOneOffEstimateInHours hours")
  }

  private def checkScheduleSpan(occursOn: LocalDateTime, estimate: Long): Unit = {
    val startDay = occursOn.toLocalDate
    val endDay = occursOn.plusMillis(estimate).toLocalDate
    checkField(startDay == endDay, EstimateField, "a scheduled one-off should not span multiple days")
  }
}
