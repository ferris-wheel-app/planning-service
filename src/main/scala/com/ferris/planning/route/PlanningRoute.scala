package com.ferris.planning.route

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{PathMatchers, Route}
import akka.stream.Materializer
import com.ferris.microservice.directive.FerrisDirectives
import com.ferris.planning.rest.conversions.ExternalToCommand._
import com.ferris.planning.service.PlanningServiceComponent
import com.ferris.planning.rest.Resources.In._
import com.ferris.planning.service.exceptions.Exceptions.MessageNotFoundException

import scala.concurrent.ExecutionContext

trait PlanningRoute extends FerrisDirectives with PlanningJsonProtocol {
this: PlanningServiceComponent =>

  implicit def routeEc: ExecutionContext
  implicit val materializer: Materializer

  private val messagesPathSegment = "messages"

  private val createMessageRoute = pathPrefix(messagesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[MessageCreation]) { messageCreation =>
          complete(planningService.createMessage(messageCreation.toCommand))
        }
      }
    }
  }

  private val updateMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { messageId =>
    pathEndOrSingleSlash {
      put {
        entity(as[MessageUpdate]) { messageUpdate =>
          val updatedMessage = planningService.updateMessage(messageId, messageUpdate.toCommand)
          complete(updatedMessage.map(_.getOrElse (throw MessageNotFoundException())))
        }
      }
    }
  }

  private val getMessagesRoute = pathPrefix(messagesPathSegment) {
    pathEndOrSingleSlash {
      get {
        val messages = planningService.getMessages
        complete(messages)
      }
    }
  }

  private val getMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { messageId =>
    pathEndOrSingleSlash {
      get {
        val message = planningService.getMessage(messageId)
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

  val planningRoute: Route = createMessageRoute ~
    updateMessageRoute ~
    getMessagesRoute ~
    getMessageRoute ~
    deleteMessageRoute
}
