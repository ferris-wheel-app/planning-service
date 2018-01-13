package com.ferris.planning.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.ferris.planning.rest.Resources.In.{MessageCreation, MessageUpdate}
import com.ferris.planning.rest.Resources.Out.MessageView
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait PlanningJsonProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val messageCreationFormat: RootJsonFormat[MessageCreation] = jsonFormat2(MessageCreation)
  implicit val messageUpdateFormat: RootJsonFormat[MessageUpdate] = jsonFormat2(MessageUpdate)
  implicit val messageViewFormat: RootJsonFormat[MessageView] = jsonFormat2(MessageView)
}
