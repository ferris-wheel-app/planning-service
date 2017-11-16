package com.ferris.planning.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.ferris.planning.rest.Resources.In.{MessageCreation, MessageUpdate}
import com.ferris.planning.rest.Resources.Out.MessageView
import spray.json.DefaultJsonProtocol

trait PlanningJsonProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val messageCreationFormat = jsonFormat2(MessageCreation)
  implicit val messageUpdateFormat = jsonFormat2(MessageUpdate)
  implicit val messageViewFormat = jsonFormat2(MessageView)
}
