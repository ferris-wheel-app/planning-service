package com.ferris.planning.contract.format

import com.ferris.json.FerrisJsonSupport
import com.ferris.planning.contract.resource.Resources.In._
import com.ferris.planning.contract.resource.Resources.Out._
import spray.json._

trait PlanningRestFormats extends FerrisJsonSupport {
  implicit val messageCreationFormat: RootJsonFormat[MessageCreation] = jsonFormat2(MessageCreation)
  implicit val messageUpdateFormat: RootJsonFormat[MessageUpdate] = jsonFormat2(MessageUpdate)
  implicit val messageViewFormat: RootJsonFormat[MessageView] = jsonFormat3(MessageView)

  implicit val backlogItemCreationFormat: RootJsonFormat[BacklogItemCreation] = jsonFormat3(BacklogItemCreation)
  implicit val backlogItemUpdateFormat: RootJsonFormat[BacklogItemUpdate] = jsonFormat3(BacklogItemUpdate)
  implicit val backlogItemViewFormat: RootJsonFormat[BacklogItemView] = jsonFormat4(BacklogItemView)

  implicit val epochCreationFormat: RootJsonFormat[EpochCreation] = jsonFormat3(EpochCreation)
  implicit val epochUpdateFormat: RootJsonFormat[EpochUpdate] = jsonFormat3(EpochUpdate)
  implicit val epochViewFormat: RootJsonFormat[EpochView] = jsonFormat4(EpochView)

  implicit val yearCreationFormat: RootJsonFormat[YearCreation] = jsonFormat3(YearCreation)
  implicit val yearUpdateFormat: RootJsonFormat[YearUpdate] = jsonFormat3(YearUpdate)
  implicit val yearViewFormat: RootJsonFormat[YearView] = jsonFormat4(YearView)

  implicit val themeCreationFormat: RootJsonFormat[ThemeCreation] = jsonFormat2(ThemeCreation)
  implicit val themeUpdateFormat: RootJsonFormat[ThemeUpdate] = jsonFormat2(ThemeUpdate)
  implicit val themeViewFormat: RootJsonFormat[ThemeView] = jsonFormat3(ThemeView)

  implicit val goalCreationFormat: RootJsonFormat[GoalCreation] = jsonFormat8(GoalCreation)
  implicit val goalUpdateFormat: RootJsonFormat[GoalUpdate] = jsonFormat8(GoalUpdate)
  implicit val goalViewFormat: RootJsonFormat[GoalView] = jsonFormat9(GoalView)

  implicit val threadCreationFormat: RootJsonFormat[ThreadCreation] = jsonFormat4(ThreadCreation)
  implicit val threadUpdateFormat: RootJsonFormat[ThreadUpdate] = jsonFormat4(ThreadUpdate)
  implicit val threadViewFormat: RootJsonFormat[ThreadView] = jsonFormat5(ThreadView)

  implicit val weaveCreationFormat: RootJsonFormat[WeaveCreation] = jsonFormat5(WeaveCreation)
  implicit val weaveUpdateFormat: RootJsonFormat[WeaveUpdate] = jsonFormat5(WeaveUpdate)
  implicit val weaveViewFormat: RootJsonFormat[WeaveView] = jsonFormat6(WeaveView)

  implicit val laserDonutCreationFormat: RootJsonFormat[LaserDonutCreation] = jsonFormat7(LaserDonutCreation)
  implicit val laserDonutUpdateFormat: RootJsonFormat[LaserDonutUpdate] = jsonFormat7(LaserDonutUpdate)
  implicit val laserDonutViewFormat: RootJsonFormat[LaserDonutView] = jsonFormat8(LaserDonutView)

  implicit val portionCreationFormat: RootJsonFormat[PortionCreation] = jsonFormat4(PortionCreation)
  implicit val portionUpdateFormat: RootJsonFormat[PortionUpdate] = jsonFormat4(PortionUpdate)
  implicit val portionViewFormat: RootJsonFormat[PortionView] = jsonFormat5(PortionView)

  implicit val todoCreationFormat: RootJsonFormat[TodoCreation] = jsonFormat4(TodoCreation)
  implicit val todoUpdateFormat: RootJsonFormat[TodoUpdate] = jsonFormat4(TodoUpdate)
  implicit val todoViewFormat: RootJsonFormat[TodoView] = jsonFormat5(TodoView)

  implicit val hobbyCreationFormat: RootJsonFormat[HobbyCreation] = jsonFormat6(HobbyCreation)
  implicit val hobbyUpdateFormat: RootJsonFormat[HobbyUpdate] = jsonFormat6(HobbyUpdate)
  implicit val hobbyViewFormat: RootJsonFormat[HobbyView] = jsonFormat7(HobbyView)

  implicit val deletionResultFormat: RootJsonFormat[DeletionResultView] = jsonFormat1(DeletionResultView)
}
