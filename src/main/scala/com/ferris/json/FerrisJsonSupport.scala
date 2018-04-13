package com.ferris.json

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.DateTime
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

trait FerrisJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object UUIDFormat extends RootJsonFormat[UUID] {
    override def write(obj: UUID): JsValue = {
      require(obj ne null)
      JsString(obj.toString)
    }
    override def read(value: JsValue): UUID = value match {
      case JsString(str) => UUID.fromString(str)
      case other => throw DeserializationException(s"Expected a UUID as JsString, but got $other")
    }
  }

  implicit object DateFormat extends RootJsonFormat[DateTime] {
    override def write(obj: DateTime): JsValue = JsString(obj.toString)
    override def read(value: JsValue): DateTime = {
      def exception(actual: String) = DeserializationException(s"Expected an ISO formatted date as JsString, but got $actual")
      value match {
        case JsString(str) => DateTime.fromIsoDateTimeString(str).getOrElse(throw exception(str))
        case other => throw exception(other.toString)
      }
    }
  }
}
