package com.ferris.planning.route

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.StatusCodes.Success
import com.ferris.planning.contract.resource.Resources.Out._
import com.ferris.planning.model.Model._
import com.ferris.planning.service.conversions.ModelToView._
import com.ferris.planning.service.exceptions.Exceptions._
import spray.json.RootJsonFormat

trait ResponseMappings {

  def mapMessage(response: Option[Message]): (Success, MessageView) = response match {
    case Some(message) => (StatusCodes.OK, message.toView)
    case None => throw MessageNotFoundException()
  }

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

  def mapDeletion(deleted: Boolean): (Success, DeletionResultView) =
    if (deleted) (StatusCodes.OK, DeletionResult.successful)
    else (StatusCodes.OK, DeletionResult.unsuccessful)
}
