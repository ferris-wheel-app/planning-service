package com.ferris.planning.route

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{PathMatchers, Route}
import akka.stream.Materializer
import com.ferris.microservice.directive.FerrisDirectives
import com.ferris.planning.rest.conversions.ExternalToCommand._
import com.ferris.planning.service.PlanningServiceComponent
import com.ferris.planning.rest.conversions.ModelToView._
import com.ferris.planning.rest.Resources.In._
import com.ferris.planning.service.exceptions.Exceptions._

import scala.concurrent.ExecutionContext

trait PlanningRoute extends FerrisDirectives with PlanningRestFormats {
this: PlanningServiceComponent =>

  implicit def routeEc: ExecutionContext
  implicit val materializer: Materializer

  private val messagesPathSegment = "messages"
  private val backlogItemsPathSegment = "backlog-items"
  private val epochsPathSegment = "epochs"
  private val yearsPathSegment = "years"
  private val themesPathSegment = "themes"
  private val goalsPathSegment = "goals"
  private val threadsPathSegment = "threads"
  private val weavesPathSegment = "weaves"
  private val laserDonutsPathSegment = "laser-donuts"
  private val portionsPathSegment = "portions"
  private val todosPathSegment = "todos"
  private val hobbiesPathSegment = "hobbies"

  private val createMessageRoute = pathPrefix(messagesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[MessageCreation]) { creation =>
          complete(planningService.createMessage(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createBacklogItemRoute = pathPrefix(backlogItemsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[BacklogItemCreation]) { creation =>
          complete(planningService.createBacklogItem(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createEpochRoute = pathPrefix(epochsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[EpochCreation]) { creation =>
          complete(planningService.createEpoch(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createYearRoute = pathPrefix(yearsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[YearCreation]) { creation =>
          complete(planningService.createYear(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createThemesRoute = pathPrefix(themesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[ThemeCreation]) { creation =>
          complete(planningService.createTheme(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createGoalRoute = pathPrefix(goalsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[GoalCreation]) { creation =>
          complete(planningService.createGoal(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createThreadRoute = pathPrefix(threadsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[ThreadCreation]) { creation =>
          complete(planningService.createThread(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createWeaveRoute = pathPrefix(weavesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[WeaveCreation]) { creation =>
          complete(planningService.createWeave(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createLaserDonutRoute = pathPrefix(laserDonutsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[LaserDonutCreation]) { creation =>
          complete(planningService.createLaserDonut(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createPortionRoute = pathPrefix(portionsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[PortionCreation]) { creation =>
          complete(planningService.createPortion(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createTodoRoute = pathPrefix(todosPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[TodoCreation]) { creation =>
          complete(planningService.createTodo(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val createHobbyRoute = pathPrefix(hobbiesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[HobbyCreation]) { creation =>
          complete(planningService.createHobby(creation.toCommand).map(_.toView))
        }
      }
    }
  }

  private val updateMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[MessageUpdate]) { update =>
          val updated = planningService.updateMessage(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw MessageNotFoundException())))
        }
      }
    }
  }

  private val updateBacklogItemRoute = pathPrefix(backlogItemsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[BacklogItemUpdate]) { update =>
          val updated = planningService.updateBacklogItem(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw BacklogItemNotFoundException())))
        }
      }
    }
  }

  private val updateEpochRoute = pathPrefix(epochsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[EpochUpdate]) { update =>
          val updated = planningService.updateEpoch(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw EpochNotFoundException())))
        }
      }
    }
  }

  private val updateYearRoute = pathPrefix(yearsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[YearUpdate]) { update =>
          val updated = planningService.updateYear(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw YearNotFoundException())))
        }
      }
    }
  }

  private val updateThemeRoute = pathPrefix(themesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[ThemeUpdate]) { update =>
          val updated = planningService.updateTheme(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw ThemeNotFoundException())))
        }
      }
    }
  }

  private val updateGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[GoalUpdate]) { update =>
          val updated = planningService.updateGoal(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw GoalNotFoundException())))
        }
      }
    }
  }

  private val updateThreadRoute = pathPrefix(threadsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[ThreadUpdate]) { update =>
          val updated = planningService.updateThread(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw ThreadNotFoundException())))
        }
      }
    }
  }

  private val updateWeaveRoute = pathPrefix(weavesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[WeaveUpdate]) { update =>
          val updated = planningService.updateWeave(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw WeaveNotFoundException())))
        }
      }
    }
  }

  private val updateLaserDonutRoute = pathPrefix(laserDonutsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[LaserDonutUpdate]) { update =>
          val updated = planningService.updateLaserDonut(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw LaserDonutNotFoundException())))
        }
      }
    }
  }

  private val updatePortionRoute = pathPrefix(portionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[PortionUpdate]) { update =>
          val updated = planningService.updatePortion(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw PortionNotFoundException())))
        }
      }
    }
  }

  private val updateTodoRoute = pathPrefix(todosPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[TodoUpdate]) { update =>
          val updated = planningService.updateTodo(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw TodoNotFoundException())))
        }
      }
    }
  }

  private val updateHobbyRoute = pathPrefix(hobbiesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[HobbyUpdate]) { update =>
          val updated = planningService.updateHobby(id, update.toCommand).map(_.map(_.toView))
          complete(updated.map(_.getOrElse (throw HobbyNotFoundException())))
        }
      }
    }
  }

  private val getMessagesRoute = pathPrefix(messagesPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getMessages.map(_.map(_.toView)))
      }
    }
  }

  private val getBacklogItemsRoute = pathPrefix(backlogItemsPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getBacklogItems.map(_.map(_.toView)))
      }
    }
  }

  private val getEpochsRoute = pathPrefix(epochsPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getEpochs.map(_.map(_.toView)))
      }
    }
  }

  private val getYearsRoute = pathPrefix(yearsPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getYears.map(_.map(_.toView)))
      }
    }
  }

  private val getThemesRoute = pathPrefix(themesPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getThemes.map(_.map(_.toView)))
      }
    }
  }

  private val getGoalsRoute = pathPrefix(goalsPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getGoals.map(_.map(_.toView)))
      }
    }
  }

  private val getThreadsRoute = pathPrefix(threadsPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getThreads.map(_.map(_.toView)))
      }
    }
  }

  private val getWeavesRoute = pathPrefix(weavesPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getWeaves.map(_.map(_.toView)))
      }
    }
  }

  private val getLaserDonutsRoute = pathPrefix(laserDonutsPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getLaserDonuts.map(_.map(_.toView)))
      }
    }
  }

  private val getPortionsRoute = pathPrefix(portionsPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getPortions.map(_.map(_.toView)))
      }
    }
  }

  private val getTodosRoute = pathPrefix(todosPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getTodos.map(_.map(_.toView)))
      }
    }
  }

  private val getHobbiesRoute = pathPrefix(hobbiesPathSegment) {
    pathEndOrSingleSlash {
      get {
        complete(planningService.getHobbies.map(_.map(_.toView)))
      }
    }
  }

  private val getMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val message = planningService.getMessage(id).map(_.map(_.toView))
        complete(message.map(_.getOrElse (throw MessageNotFoundException())))
      }
    }
  }

  private val getBacklogItemRoute = pathPrefix(backlogItemsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val backlogItem = planningService.getBacklogItem(id).map(_.map(_.toView))
        complete(backlogItem.map(_.getOrElse (throw BacklogItemNotFoundException())))
      }
    }
  }

  private val getEpochRoute = pathPrefix(epochsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val epoch = planningService.getEpoch(id).map(_.map(_.toView))
        complete(epoch.map(_.getOrElse (throw EpochNotFoundException())))
      }
    }
  }

  private val getYearRoute = pathPrefix(yearsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val year = planningService.getYear(id).map(_.map(_.toView))
        complete(year.map(_.getOrElse (throw YearNotFoundException())))
      }
    }
  }

  private val getThemeRoute = pathPrefix(themesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val theme = planningService.getTheme(id).map(_.map(_.toView))
        complete(theme.map(_.getOrElse (throw ThemeNotFoundException())))
      }
    }
  }

  private val getGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val goal = planningService.getGoal(id).map(_.map(_.toView))
        complete(goal.map(_.getOrElse (throw GoalNotFoundException())))
      }
    }
  }

  private val getThreadRoute = pathPrefix(threadsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val thread = planningService.getThread(id).map(_.map(_.toView))
        complete(thread.map(_.getOrElse (throw ThreadNotFoundException())))
      }
    }
  }

  private val getWeaveRoute = pathPrefix(weavesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val weave = planningService.getWeave(id).map(_.map(_.toView))
        complete(weave.map(_.getOrElse (throw WeaveNotFoundException())))
      }
    }
  }

  private val getLaserDonutRoute = pathPrefix(laserDonutsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val laserDonut = planningService.getLaserDonut(id).map(_.map(_.toView))
        complete(laserDonut.map(_.getOrElse (throw LaserDonutNotFoundException())))
      }
    }
  }

  private val getPortionRoute = pathPrefix(portionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val portion = planningService.getPortion(id).map(_.map(_.toView))
        complete(portion.map(_.getOrElse (throw PortionNotFoundException())))
      }
    }
  }

  private val getTodoRoute = pathPrefix(todosPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val todo = planningService.getTodo(id).map(_.map(_.toView))
        complete(todo.map(_.getOrElse (throw TodoNotFoundException())))
      }
    }
  }

  private val getHobbyRoute = pathPrefix(hobbiesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        val hobby = planningService.getHobby(id).map(_.map(_.toView))
        complete(hobby.map(_.getOrElse (throw HobbyNotFoundException())))
      }
    }
  }

  private val deleteMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteMessage(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteBacklogItemRoute = pathPrefix(backlogItemsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteBacklogItem(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteEpochRoute = pathPrefix(epochsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteEpoch(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteYearRoute = pathPrefix(yearsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteYear(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteThemeRoute = pathPrefix(themesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteTheme(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteGoal(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteThreadRoute = pathPrefix(threadsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteThread(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteWeaveRoute = pathPrefix(weavesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteWeave(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteLaserDonutRoute = pathPrefix(laserDonutsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteLaserDonut(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deletePortionRoute = pathPrefix(portionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deletePortion(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteTodoRoute = pathPrefix(todosPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteTodo(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  private val deleteHobbyRoute = pathPrefix(hobbiesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteHobby(id)) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  val planningRoute: Route = {
    createMessageRoute ~
    createBacklogItemRoute ~
    createEpochRoute ~
    createYearRoute ~
    createThemesRoute ~
    createGoalRoute ~
    createThreadRoute ~
    createWeaveRoute ~
    createLaserDonutRoute ~
    createPortionRoute ~
    createTodoRoute ~
    createHobbyRoute ~
    updateMessageRoute ~
    updateBacklogItemRoute ~
    updateEpochRoute ~
    updateYearRoute ~
    updateThemeRoute ~
    updateGoalRoute ~
    updateThreadRoute ~
    updateWeaveRoute ~
    updateLaserDonutRoute ~
    updatePortionRoute ~
    updateTodoRoute ~
    updateHobbyRoute ~
    getMessagesRoute ~
    getBacklogItemsRoute ~
    getEpochsRoute ~
    getYearsRoute ~
    getThemesRoute ~
    getGoalsRoute ~
    getThreadsRoute ~
    getWeavesRoute ~
    getLaserDonutsRoute ~
    getPortionsRoute ~
    getTodosRoute ~
    getHobbiesRoute ~
    getMessageRoute ~
    getBacklogItemRoute ~
    getEpochRoute ~
    getYearRoute ~
    getThemeRoute ~
    getGoalRoute ~
    getThreadRoute ~
    getWeaveRoute ~
    getLaserDonutRoute ~
    getPortionRoute ~
    getTodoRoute ~
    getHobbyRoute ~
    deleteMessageRoute ~
    deleteBacklogItemRoute ~
    deleteEpochRoute ~
    deleteYearRoute ~
    deleteThemeRoute ~
    deleteGoalRoute ~
    deleteThreadRoute ~
    deleteWeaveRoute ~
    deleteLaserDonutRoute ~
    deletePortionRoute ~
    deleteTodoRoute ~
    deleteHobbyRoute
  }
}
