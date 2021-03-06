package com.ferris.planning.server

import akka.actor.ActorSystem
import akka.http.scaladsl.model.Uri
import akka.stream.ActorMaterializer
import com.ferris.service.client.DefaultServer

class PlanningServer(uri: Uri)(implicit mat: ActorMaterializer, system: ActorSystem) extends DefaultServer(uri)
