package com.ferris.planning.contract.validation

import com.ferris.microservice.exceptions.ApiExceptions.{InvalidFieldException, InvalidFieldPayload}
import com.ferris.planning.contract.resource.Resources.In._
import com.ferris.planning.contract.resource.TypeFields._

object InputValidators {

  val TypeField = "type"
  val BacklogItemsField = "backlogItems"
  val GraduationField = "graduation"
  val StatusField = "status"
  val FrequencyField = "frequency"
  val LaserDonutsField = "laserDonuts"
  val TiersField = "tiers"

  val MaxGoalBacklogItemsSize = 10
  val MaxTierSize = 5
  val MinTopTierSize = 5
  val MinPyramidSize = 5

  def checkValidity(backlogItemCreation: BacklogItemCreation): Unit = {
    checkField(BacklogItemType.values.contains(backlogItemCreation.`type`), TypeField)
  }

  def checkValidity(goalCreation: GoalCreation): Unit = {
    checkMaxSize(goalCreation.backlogItems, BacklogItemsField, MaxGoalBacklogItemsSize)
    checkForDuplication(goalCreation.backlogItems, BacklogItemsField)
    checkField(GraduationType.values.contains(goalCreation.status), GraduationField)
    checkField(GoalStatus.values.contains(goalCreation.status), StatusField)
  }

  def checkValidity(threadCreation: ThreadCreation): Unit = {
    checkField(Status.values.contains(threadCreation.status), StatusField)
  }

  def checkValidity(weaveCreation: WeaveCreation): Unit = {
    checkField(WeaveType.values.contains(weaveCreation.`type`), TypeField)
    checkField(Status.values.contains(weaveCreation.status), StatusField)
  }

  def checkValidity(laserDonutCreation: LaserDonutCreation): Unit = {
    checkField(DonutType.values.contains(laserDonutCreation.`type`), TypeField)
    checkField(Status.values.contains(laserDonutCreation.status), StatusField)
  }

  def checkValidity(portionCreation: PortionCreation): Unit = {
    checkField(Status.values.contains(portionCreation.status), StatusField)
  }

  def checkValidity(todoCreation: TodoCreation): Unit = {
    checkField(Status.values.contains(todoCreation.status), StatusField)
  }

  def checkValidity(hobbyCreation: HobbyCreation): Unit = {
    checkField(HobbyFrequency.values.contains(hobbyCreation.frequency), FrequencyField)
    checkField(HobbyType.values.contains(hobbyCreation.`type`), TypeField)
    checkField(Status.values.contains(hobbyCreation.status), StatusField)
  }

  def checkValidity(tierCreation: TierCreation): Unit = {
    checkMaxSize(tierCreation.laserDonuts, LaserDonutsField, MaxTierSize)
  }

  def checkValidity(pyramidCreation: PyramidOfImportanceCreation): Unit = {
    checkField(pyramidCreation.tiers.headOption.map(_.laserDonuts).sum >= MinTopTierSize)
    checkMinSize(pyramidCreation.tiers.map(_.laserDonuts.size).sum, TiersField, MinTopTierSize)
  }

  private def checkMaxSize[T](list: Seq[T], name: String, max: Int): Unit = {
    checkField(list.size <= max, name, s"${camelCaseToSpaced(name)} must be up to a list of $max or fewer")
  }

  private def checkMinSize[T](list: Seq[T], name: String, min: Int): Unit = {
    checkField(list.size >= min, name, s"${camelCaseToSpaced(name)} must be a list of at least $min or more")
  }

  private def checkForDuplication[A](ls: Seq[A], name: String): Unit = {
    checkField(ls.toSet.size == ls.size, name, s"${camelCaseToSpaced(name)} cannot contain duplicate entries")
  }

  private def checkField(cond: Boolean, name: String): Unit = {
    val displayedName: String = camelCaseToSpaced(name)
    if (!cond) throw InvalidFieldException("InvalidField", s"Invalid $displayedName", Some(InvalidFieldPayload(name)))
  }

  private def checkField(cond: Boolean, name: String, msg: String): Unit = {
    if (!cond) throw InvalidFieldException("InvalidField", msg, Some(InvalidFieldPayload(name)))
  }

  private def camelCaseToSpaced(s: String): String = {
    require(s.length > 0)
    val tail = s.substring(1).foldRight(List.empty[Char]){ (char, arr) =>
      if (char.isUpper) ' ' :: char :: arr
      else char :: arr
    }
    s.charAt(0).toUpper :: tail mkString ""
  }
}
