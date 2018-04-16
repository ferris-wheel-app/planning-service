package com.ferris.planning.client

import java.util.UUID

import akka.http.scaladsl.model.Uri
import Uri.Path._
import akka.stream.ActorMaterializer
import com.ferris.planning.contract.format.PlanningRestFormats
import com.ferris.planning.contract.resource.Resources.In._
import com.ferris.planning.contract.resource.Resources.Out._
import com.ferris.service.client.{HttpServer, ServiceClient}

import scala.concurrent.Future

class PlanningServiceClient(val server: HttpServer, implicit val mat: ActorMaterializer) extends ServiceClient with PlanningRestFormats {

  def this(server: HttpServer) = this(server, server.mat)

  private val apiPath = /("api")

  private val messagesPath = "messages"

  def createMessage(creation: MessageCreation): Future[MessageView] =
    makePostRequest[MessageCreation, MessageView](Uri(path = apiPath / messagesPath), creation)

  def updateMessage(id: UUID, update: MessageUpdate): Future[MessageView] =
    makePutRequest[MessageUpdate, MessageView](Uri(path = apiPath / messagesPath / id.toString), update)

  def message(id: UUID): Future[Option[MessageView]] =
    makeGetRequest[Option[MessageView]](Uri(path = apiPath / messagesPath / id.toString))

  def messages: Future[List[MessageView]] =
    makeGetRequest[List[MessageView]](Uri(path = apiPath / messagesPath))

  def deleteMessage(id: UUID): Future[DeletionResultView] =
    makeDeleteRequest[DeletionResultView](Uri(path = apiPath / messagesPath / id.toString))
}
