package com.ferris.microservice.exceptions

import akka.http.scaladsl.model.StatusCodes
import com.ferris.json.FerrisJsonSupport
import com.ferris.microservice.exceptions.ApiExceptions._
import spray.json._

object ApiExceptions {

  sealed trait ExceptionPayload

  sealed trait MicroServiceException extends Exception {
    val httpStatusCode: Int
    val message: String
    val code: String
    val payload: Option[ExceptionPayload]
  }

  sealed trait InvalidRequestException extends MicroServiceException

  case class InvalidFieldPayload(fieldName: String, details: Map[String, String] = Map.empty) extends ExceptionPayload
  case class InvalidFieldException(code: String, message: String, payload: Option[InvalidFieldPayload], httpStatusCode: Int = StatusCodes.BadRequest.intValue) extends InvalidRequestException
  case class InvalidOperationPayload(operationName: String) extends ExceptionPayload
  case class InvalidOperationException(code: String, message: String, payload: Option[InvalidOperationPayload], httpStatusCode: Int = StatusCodes.BadRequest.intValue) extends InvalidRequestException
  case class InvalidInputException(code: String, message: String, httpStatusCode: Int = StatusCodes.BadRequest.intValue, payload: Option[ExceptionPayload] = None) extends InvalidRequestException

  case class ConflictingVersionPayload(requested: Int, actual: Int) extends ExceptionPayload
  case class ConflictingVersionException(code: String, message: String, payload: Option[ConflictingVersionPayload], httpStatusCode: Int = StatusCodes.Conflict.intValue) extends MicroServiceException

  case class NotFoundPayload(entity: String) extends ExceptionPayload
  case class NotFoundException(code: String, message: String, payload: Option[NotFoundPayload], httpStatusCode: Int = StatusCodes.NotFound.intValue) extends MicroServiceException

  case class UnexpectedErrorException(code: String = "UnexpectedError", message: String = "an unexpected error occurred",
    httpStatusCode: Int = StatusCodes.InternalServerError.intValue, payload: Option[ExceptionPayload] = None) extends MicroServiceException

  case class UnauthorisedException(message: String, code: String,
    httpStatusCode: Int = StatusCodes.Forbidden.intValue, payload: Option[ExceptionPayload] = None) extends MicroServiceException

  case class ExceptionList(errors: List[MicroServiceException]) extends Exception
}

trait ApiExceptionFormats extends FerrisJsonSupport {

  implicit val invalidFieldPayloadFormat: RootJsonFormat[InvalidFieldPayload] = jsonFormat2(InvalidFieldPayload)
  implicit val invalidOperationPayloadFormat: RootJsonFormat[InvalidOperationPayload] = jsonFormat1(InvalidOperationPayload)
  implicit val conflictingVersionPayloadFormat: RootJsonFormat[ConflictingVersionPayload] = jsonFormat2(ConflictingVersionPayload)
  implicit val notFoundPayload: RootJsonFormat[NotFoundPayload] = jsonFormat1(NotFoundPayload)

  implicit object ExceptionPayloadFormat extends RootJsonFormat[ExceptionPayload] {
    override def write(obj: ExceptionPayload): JsValue = obj match {
      case o: InvalidFieldPayload => o.toJson
      case o: InvalidOperationPayload => o.toJson
      case o: ConflictingVersionPayload => o.toJson
      case o: NotFoundPayload => o.toJson
    }

    override def read(json: JsValue): ExceptionPayload = ???
  }

  implicit val invalidFieldExceptionFormat: RootJsonFormat[InvalidFieldException] = jsonFormat4(InvalidFieldException)
  implicit val invalidOperationExceptionFormat: RootJsonFormat[InvalidOperationException] = jsonFormat4(InvalidOperationException)
  implicit val invalidInputExceptionFormat: RootJsonFormat[InvalidInputException] = jsonFormat4(InvalidInputException)
  implicit val conflictingVersionExceptionFormat: RootJsonFormat[ConflictingVersionException] = jsonFormat4(ConflictingVersionException)
  implicit val notFoundExceptionFormat: RootJsonFormat[NotFoundException] = jsonFormat4(NotFoundException)
  implicit val unexpectedErrorExceptionFormat: RootJsonFormat[UnexpectedErrorException] = jsonFormat4(UnexpectedErrorException)
  implicit val unauthorisedExceptionFormat: RootJsonFormat[UnauthorisedException] = jsonFormat4(UnauthorisedException)

  implicit object MicroServiceExceptionFormat extends RootJsonFormat[MicroServiceException] {
    override def write(obj: MicroServiceException): JsValue = obj match {
      case ex: InvalidFieldException => ex.toJson
      case ex: InvalidOperationException => ex.toJson
      case ex: InvalidInputException => ex.toJson
      case ex: ConflictingVersionException => ex.toJson
      case ex: NotFoundException => ex.toJson
      case ex: UnexpectedErrorException => ex.toJson
      case ex: UnauthorisedException => ex.toJson
    }

    override def read(json: JsValue): MicroServiceException = ???
  }

  implicit val ExceptionListFormat: RootJsonFormat[ExceptionList] = jsonFormat1(ExceptionList)
}

object ApiExceptionFormats extends ApiExceptionFormats
