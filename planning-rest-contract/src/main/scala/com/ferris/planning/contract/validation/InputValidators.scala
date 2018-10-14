package com.ferris.planning.contract.validation

import com.ferris.microservice.validation.InputValidation
import com.ferris.planning.contract.resource.Resources.In._
import com.ferris.planning.contract.resource.TypeFields._

object InputValidators extends InputValidation {

  private val TypeField = "type"
  private val BacklogItemsField = "backlogItems"
  private val GraduationField = "graduation"
  private val StatusField = "status"
  private val PerformanceField = "performance"
  private val FrequencyField = "frequency"
  private val LaserDonutsField = "laserDonuts"
  private val TiersField = "tiers"

  private val MaxGoalBacklogItemsSize = 10
  private val MaxTierSize = 10
  private val MinTierSize = 5
  private val MinPyramidSize = 5

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

  def checkValidity(tierCreation: TierUpsert): Unit = {
    checkMaxSize(tierCreation.laserDonuts, LaserDonutsField, MaxTierSize)
    checkMinSize(tierCreation.laserDonuts, LaserDonutsField, MinTierSize)
    checkForDuplication(tierCreation.laserDonuts, LaserDonutsField)
  }

  def checkValidity(pyramidCreation: PyramidOfImportanceUpsert): Unit = {
    checkMinSize(pyramidCreation.tiers, TiersField, MinPyramidSize)
  }
}
