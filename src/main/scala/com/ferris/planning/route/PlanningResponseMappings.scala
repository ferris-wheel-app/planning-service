package com.ferris.planning.route

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.StatusCodes.Success
import com.ferris.microservice.route.FerrisResponseMappings
import com.ferris.planning.contract.resource.Resources.Out._
import com.ferris.planning.model.Model._
import com.ferris.planning.service.conversions.ModelToView._
import com.ferris.planning.service.exceptions.Exceptions._

trait PlanningResponseMappings extends FerrisResponseMappings {

  def mapBacklogItem(response: Option[BacklogItem]): (Success, BacklogItemView) = response match {
    case Some(backlogItem) => (StatusCodes.OK, backlogItem.toView)
    case None => throw BacklogItemNotFoundException()
  }

  def mapEpoch(response: Option[Epoch]): (Success, EpochView) = response match {
    case Some(epoch) => (StatusCodes.OK, epoch.toView)
    case None => throw EpochNotFoundException()
  }

  def mapYear(response: Option[Year]): (Success, YearView) = response match {
    case Some(year) => (StatusCodes.OK, year.toView)
    case None => throw YearNotFoundException()
  }

  def mapTheme(response: Option[Theme]): (Success, ThemeView) = response match {
    case Some(theme) => (StatusCodes.OK, theme.toView)
    case None => throw ThemeNotFoundException()
  }

  def mapGoal(response: Option[Goal]): (Success, GoalView) = response match {
    case Some(goal) => (StatusCodes.OK, goal.toView)
    case None => throw GoalNotFoundException()
  }

  def mapThread(response: Option[Thread]): (Success, ThreadView) = response match {
    case Some(thread) => (StatusCodes.OK, thread.toView)
    case None => throw ThreadNotFoundException()
  }

  def mapWeave(response: Option[Weave]): (Success, WeaveView) = response match {
    case Some(weave) => (StatusCodes.OK, weave.toView)
    case None => throw WeaveNotFoundException()
  }

  def mapLaserDonut(response: Option[LaserDonut]): (Success, LaserDonutView) = response match {
    case Some(laserDonut) => (StatusCodes.OK, laserDonut.toView)
    case None => throw LaserDonutNotFoundException()
  }

  def mapLaserDonuts(response: Seq[LaserDonut]): (Success, Seq[LaserDonutView]) = {
    (StatusCodes.OK, response.map(_.toView))
  }

  def mapPortion(response: Option[Portion]): (Success, PortionView) = response match {
    case Some(portion) => (StatusCodes.OK, portion.toView)
    case None => throw PortionNotFoundException()
  }

  def mapTodo(response: Option[Todo]): (Success, TodoView) = response match {
    case Some(todo) => (StatusCodes.OK, todo.toView)
    case None => throw TodoNotFoundException()
  }

  def mapHobby(response: Option[Hobby]): (Success, HobbyView) = response match {
    case Some(hobby) => (StatusCodes.OK, hobby.toView)
    case None => throw HobbyNotFoundException()
  }

  def mapOneOff(response: Option[OneOff]): (Success, OneOffView) = response match {
    case Some(oneOff) => (StatusCodes.OK, oneOff.toView)
    case None => throw OneOffNotFoundException()
  }

  def mapScheduledOneOff(response: Option[ScheduledOneOff]): (Success, ScheduledOneOffView) = response match {
    case Some(scheduledOneOff) => (StatusCodes.OK, scheduledOneOff.toView)
    case None => throw ScheduledOneOffNotFoundException()
  }

  def mapPyramid(response: Option[PyramidOfImportance]): (Success, PyramidOfImportanceView) = response match {
    case Some(pyramid) => (StatusCodes.OK, pyramid.toView)
    case None => throw PyramidNotFoundException()
  }
}
