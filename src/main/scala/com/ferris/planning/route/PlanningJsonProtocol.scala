package com.ferris.planning.route

import com.ferris.json.FerrisJsonSupport
import com.ferris.planning.rest.Resources.In.{MessageCreation, MessageUpdate}
import com.ferris.planning.rest.Resources.Out.MessageView
import spray.json.RootJsonFormat

trait PlanningJsonProtocol extends FerrisJsonSupport {
  implicit val messageCreationFormat: RootJsonFormat[MessageCreation] = jsonFormat2(MessageCreation)
  implicit val messageUpdateFormat: RootJsonFormat[MessageUpdate] = jsonFormat2(MessageUpdate)
  implicit val messageViewFormat: RootJsonFormat[MessageView] = jsonFormat2(MessageView)
}
