package ferris.planning.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers
import akka.stream.Materializer
import ferris.planning.rest.conversions.ExternalToCommand._
import ferris.planning.rest.Resources.In._
import ferris.planning.rest.Resources.Out.MessageView
import ferris.planning.service.PlanningServiceComponent
import ferris.planning.service.exceptions.Exceptions.MessageNotFoundException
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContext

trait Protocols extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val messageCreationFormat = jsonFormat2(MessageCreation)
  implicit val messageUpdateFormat = jsonFormat2(MessageUpdate)
  implicit val messageViewFormat = jsonFormat2(MessageView)
}

trait PlanningRoute extends Protocols {
this: PlanningServiceComponent =>

  implicit def routeEc: ExecutionContext
  implicit val materializer: Materializer

  private val apiPathSegment = "api"
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
          val updateMessage = planningService.updateMessage(messageId, messageUpdate.toCommand)
          onSuccess(updateMessage) {
            case true => complete(StatusCodes.OK)
            case false => complete(StatusCodes.BadRequest)
          }
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

  val planningRoute = pathPrefix(apiPathSegment) {
    createMessageRoute ~
    updateMessageRoute ~
    getMessagesRoute ~
    getMessageRoute ~
    deleteMessageRoute
  }
}
