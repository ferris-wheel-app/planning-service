package com.ferris.microservice.directive

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.marshalling.{PredefinedToResponseMarshallers, ToEntityMarshaller, ToResponseMarshaller}
import akka.http.scaladsl.model.StatusCodes.InternalServerError
import akka.http.scaladsl.model._
import akka.http.scaladsl.server._
import com.ferris.microservice.exceptions.ApiExceptions._
import com.ferris.microservice.exceptions.ApiExceptionFormats._
import spray.json._

import scala.util.control.NonFatal

trait FerrisDirectives extends Directives {

  protected val mapToStructuredJson: Directive0 =
    mapResponse { response =>
      response.entity match {
        case HttpEntity.Strict(ContentTypes.`text/plain(UTF-8)`, content) => jsonResponse(response, JsString(content.utf8String))
        case HttpEntity.Strict(ContentTypes.`application/json`, content) => jsonResponse(response, content.utf8String.parseJson)
        case _ if response.status.isFailure => jsonResponse(response, JsString(response.status.defaultMessage))
        case _ => response
      }
    }

  private def jsonResponse(response: HttpResponse, js: JsValue) = response.copy(
    entity = HttpEntity(
      ContentTypes.`application/json`,
      JsObject("status" -> JsString(response.status.reason), "data" -> js).compactPrint
    )
  )

  val ferrisExceptionHandler: ExceptionHandler = ExceptionHandler {
    case es: ExceptionList ⇒ ctx ⇒ {
      ctx.log.warning(s"Errors occured: ${es.errors.map(_.message).mkString("\n")}")
      ctx.complete(StatusCodes.BadRequest, es.toJson)
    }
    case e: MicroServiceException ⇒ ctx ⇒ {
      ctx.log.warning(s"An error occurred: ${e.message}")
      ctx.complete(e.httpStatusCode, ExceptionList(e :: Nil).toJson)
    }
    case IllegalRequestException(info, status) ⇒ ctx ⇒ {
      ctx.log.warning("Illegal request {} \n\t {} \n\tCompleting with '{}' response", ctx.request, info.formatPretty, status)
      ctx.complete((status, info.format(false)))
    }
    case NonFatal(e) ⇒ ctx ⇒ {
      ctx.log.error(e, "Error during processing of request {}", ctx.request)
      ctx.complete(InternalServerError)
    }
  }

  val ferrisRejectionHandler: RejectionHandler = RejectionHandler.newBuilder()
    .handle {
      case ValidationRejection(_, Some(e: MicroServiceException)) => complete(e.httpStatusCode, ExceptionList(e :: Nil).toJson)
    }
    .handle {
      case MalformedRequestContentRejection(_, e: MicroServiceException) => complete(e.httpStatusCode, ExceptionList(e :: Nil).toJson)
    }
    .handle {
      case MalformedRequestContentRejection(_, e: ExceptionList) => complete((StatusCodes.BadRequest, e.toJson))
    }
    .handle {
      case MalformedRequestContentRejection(msg, _) => complete((StatusCodes.BadRequest, ExceptionList(UnexpectedErrorException(message = msg) :: Nil).toJson))
    }
    .result().withFallback(RejectionHandler.default)

  def api(externalRoutes: => Route): Route = mapToStructuredJson {
    handleExceptions(ferrisExceptionHandler) {
      handleRejections(ferrisRejectionHandler) {
        pathPrefix("api") {
          externalRoutes
        }
      }
    }
  }

  implicit def customOptionMarshaller[A](implicit jf: RootJsonFormat[A]): ToResponseMarshaller[Option[A]] = {
    val optFmt = new RootJsonWriter[Option[A]] {
      override def write(obj: Option[A]): JsValue = obj match {
        case Some(b) => b.toJson
        case None => JsNull
      }
    }
    def toEntityMarshaller: ToEntityMarshaller[Option[A]] = sprayJsonMarshaller[Option[A]](optFmt)
    PredefinedToResponseMarshallers.fromToEntityMarshaller()(toEntityMarshaller)
  }
}
