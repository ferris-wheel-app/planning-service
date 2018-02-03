package com.ferris.planning.route

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{PathMatchers, Route}
import akka.stream.Materializer
import com.ferris.microservice.directive.FerrisDirectives
import com.ferris.planning.rest.conversions.ExternalToCommand._
import com.ferris.planning.service.PlanningServiceComponent
import com.ferris.planning.rest.conversions.ModelToView._
import com.ferris.planning.rest.Resources.In._
import com.ferris.planning.service.exceptions.Exceptions.MessageNotFoundException

import scala.concurrent.ExecutionContext

trait PlanningRoute extends FerrisDirectives with PlanningJsonProtocol {
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

  private val updateMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { messageId =>
    pathEndOrSingleSlash {
      put {
        entity(as[MessageUpdate]) { messageUpdate =>
          val updatedMessage = planningService.updateMessage(messageId, messageUpdate.toCommand).map(_.map(_.toView))
          complete(updatedMessage.map(_.getOrElse (throw MessageNotFoundException())))
        }
      }
    }
  }

  private val getMessagesRoute = pathPrefix(messagesPathSegment) {
    pathEndOrSingleSlash {
      get {
        val messages = planningService.getMessages.map(_.map(_.toView))
        complete(messages)
      }
    }
  }

  private val getMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { messageId =>
    pathEndOrSingleSlash {
      get {
        val message = planningService.getMessage(messageId).map(_.map(_.toView))
        complete(message.map(_.getOrElse (throw MessageNotFoundException())))
      }
    }
  }

  private val deleteMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { messageId =>
    pathEndOrSingleSlash {
      delete {
        val deleteMessage = planningService.deleteMessage(messageId)
        onSuccess(deleteMessage) {
          case true => complete(StatusCodes.OK)
          case false => complete(StatusCodes.NotFound)
        }
      }
    }
  }

  val planningRoute: Route = {
    createMessageRoute ~
      updateMessageRoute ~
      getMessagesRoute ~
      getMessageRoute ~
      deleteMessageRoute
  }
}
