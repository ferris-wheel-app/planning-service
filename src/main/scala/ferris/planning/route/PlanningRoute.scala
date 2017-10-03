package ferris.planning.route

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers
import akka.stream.Materializer
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import ferris.planning.rest.conversions.ExternalToCommand._
import ferris.planning.rest.conversions.ModelToView._
import ferris.planning.rest.Resources.In._
import ferris.planning.service.PlanningServiceComponent
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

trait PlanningRoute extends FailFastCirceSupport {
this: PlanningServiceComponent =>

  implicit def routeEc: ExecutionContext
  implicit val materializer: Materializer

  private val messagesPathSegment = "messages"

  private val createMessageRoute = pathPrefix(messagesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[MessageCreation]) { messageCreation =>
          complete(planningService.createMessage(messageCreation.toCommand).map(_.asJson))
        }
      }
    }
  }

  private val updateMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { messageId =>
    pathEndOrSingleSlash {
      post {
        entity(as[MessageUpdate]) { messageUpdate =>
          complete(planningService.updateMessage(messageId, messageUpdate.toCommand).map(_.asJson))
        }
      }
    }
  }

  private val getMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { messageId =>
    pathEndOrSingleSlash {
      get {
        complete(planningService.getMessage(messageId).map(_.toView).map(_.asJson))
      }
    }
  }

  private val deleteMessageRoute = pathPrefix(messagesPathSegment / PathMatchers.JavaUUID) { messageId =>
    pathEndOrSingleSlash {
      delete {
        val result = planningService.deleteMessage(messageId) map {
          case true => HttpResponse(status = StatusCodes.OK, entity = "")
          case false => HttpResponse(status = StatusCodes.NotFound)
        }
        complete(result)
      }
    }
  }

  val planningRoute = createMessageRoute ~ updateMessageRoute ~ getMessageRoute ~ deleteMessageRoute
}
