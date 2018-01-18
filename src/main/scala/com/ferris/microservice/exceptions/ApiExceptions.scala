package com.ferris.microservice.exceptions

import akka.http.scaladsl.model.StatusCodes
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
  case class InvalidInputException(code: String, message: String, httpStatusCode: Int = StatusCodes.BadRequest.intValue) extends InvalidRequestException {
    override val payload = None
  }

  case class ConflictingVersionPayload(requested: Int, actual: Int) extends ExceptionPayload
  case class ConflictingVersionException(code: String, message: String, payload: Option[ConflictingVersionPayload], httpStatusCode: Int = StatusCodes.Conflict.intValue) extends MicroServiceException

  case class NotFoundPayload(entity: String) extends ExceptionPayload
  case class NotFoundException(code: String, message: String, payload: Option[NotFoundPayload], httpStatusCode: Int = StatusCodes.NotFound.intValue) extends MicroServiceException

  case class UnexpectedErrorException(code: String = "UnexpectedError", message: String = "an unexpected error occurred", httpStatusCode: Int = StatusCodes.InternalServerError.intValue) extends MicroServiceException {
    override val payload = None
  }

  case class UnauthorisedException(message: String, code: String) extends MicroServiceException {
    override val httpStatusCode: Int = StatusCodes.Forbidden.intValue
    override val payload: Option[ExceptionPayload] = None
  }

  case class ExceptionList(errors: List[MicroServiceException]) extends Exception
}

trait ApiExceptionFormats {
  import fommil.sjs.FamilyFormats._

  implicit val invalidFieldPayloadFormat: RootJsonFormat[InvalidFieldPayload] = jsonFormat2(InvalidFieldPayload)
  implicit val invalidFieldExceptionFormat: RootJsonFormat[InvalidFieldException] = jsonFormat4(InvalidFieldException)
  implicit val invalidOperationPayloadFormat: RootJsonFormat[InvalidOperationPayload] = jsonFormat1(InvalidOperationPayload)
  implicit val invalidOperationExceptionFormat: RootJsonFormat[InvalidOperationException] = jsonFormat4(InvalidOperationException)
  implicit val invalidInputExceptionFormat: RootJsonFormat[InvalidInputException] = jsonFormat3(InvalidInputException)
  implicit val conflictingVersionPayloadFormat: RootJsonFormat[ConflictingVersionPayload] = jsonFormat2(ConflictingVersionPayload)
  implicit val conflictingVersionExceptionFormat: RootJsonFormat[ConflictingVersionException] = jsonFormat4(ConflictingVersionException)
  implicit val notFoundPayload: RootJsonFormat[NotFoundPayload] = jsonFormat1(NotFoundPayload)
  implicit val notFoundExceptionFormat: RootJsonFormat[NotFoundException] = jsonFormat4(NotFoundException)
  implicit val unexpectedErrorExceptionFormat: RootJsonFormat[UnexpectedErrorException] = jsonFormat3(UnexpectedErrorException)
  implicit val unauthorisedExceptionFormat: RootJsonFormat[UnauthorisedException] = jsonFormat2(UnauthorisedException)

  implicit object MicroServiceExceptionFormat extends RootJsonFormat[MicroServiceException] {
    override def write(obj: MicroServiceException): JsValue = obj match {
      case invalidFieldException: InvalidFieldException => invalidFieldException.toJson
      case invalidOperationException: InvalidOperationException => invalidOperationException.toJson
      case invalidInputException: InvalidInputException => invalidInputException.toJson
      case conflictingVersionException: ConflictingVersionException => conflictingVersionException.toJson
      case notFoundException: NotFoundException => notFoundException.toJson
      case unexpectedErrorException: UnexpectedErrorException => unexpectedErrorException.toJson
      case unauthorisedException: UnauthorisedException => unauthorisedException.toJson
    }

    override def read(json: JsValue): MicroServiceException = ???
  }

  implicit val ExceptionListFormat: RootJsonFormat[ExceptionList] = jsonFormat1(ExceptionList)
}

object ApiExceptionFormats extends ApiExceptionFormats
