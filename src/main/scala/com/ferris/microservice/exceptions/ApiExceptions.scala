package com.ferris.microservice.exceptions

import akka.http.scaladsl.model.StatusCodes
import com.ferris.microservice.exceptions.ApiExceptions._
import spray.json.RootJsonFormat

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
  implicit val FerrisExceptionFormat: RootJsonFormat[MicroServiceException] = shapeless.cachedImplicit
  implicit val ExceptionListFormat: RootJsonFormat[ExceptionList] = shapeless.cachedImplicit
}

object ApiExceptionFormats extends ApiExceptionFormats
