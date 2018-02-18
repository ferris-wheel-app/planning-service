package com.ferris.planning.route

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import com.ferris.microservice.service.Envelope
import com.ferris.planning.rest.conversions.ExternalToCommand._
import com.ferris.planning.rest.conversions.ModelToView._
import com.ferris.planning.rest.Resources.Out.MessageView
import com.ferris.planning.sample.SampleData.{domain, rest}
import org.mockito.Matchers.{any, eq => eqTo}
import org.mockito.Mockito.{times, verify, when}
import spray.json._

import scala.concurrent.Future

class PlanningRouteTest extends RouteTestFramework {

  describe("creating a message") {
    it("should respond with the created message") {
      when(testServer.planningService.createMessage(eqTo(domain.messageCreation))(any())).thenReturn(Future.successful(domain.message))
      Post("/api/messages", rest.messageCreation) ~> route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[Envelope[MessageView]].data shouldBe rest.message
      }
    }
  }

  describe("updating a message") {
    it("should respond with the updated message") {
      val messageId = UUID.randomUUID
      val messageUpdate = rest.messageUpdate.copy(sender = Some("Judge Dredd"), content = Some("I have a small mouth, and a big helmet."))
      val updatedMessage = domain.message.copy(sender = "Judge Dredd", content = "I have a small mouth, and a big helmet.")

      when(testServer.planningService.updateMessage(eqTo(messageId), eqTo(messageUpdate.toCommand))(any())).thenReturn(Future.successful(Some(updatedMessage)))
      Put(s"/api/messages/$messageId", messageUpdate) ~> route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[Envelope[MessageView]].data shouldBe updatedMessage.toView
      }
    }

    it("should respond with the appropriate error if the message is not found") {
      val messageId = UUID.randomUUID
      val messageUpdate = rest.messageUpdate

      when(testServer.planningService.updateMessage(eqTo(messageId), eqTo(messageUpdate.toCommand))(any())).thenReturn(Future.successful(None))
      Put(s"/api/messages/$messageId", messageUpdate) ~> route ~> check {
        status shouldBe StatusCodes.NotFound
      }
    }
  }

  describe("getting a message") {
    it("should respond with the requested message") {
      val messageId = UUID.randomUUID

      when(testServer.planningService.getMessage(eqTo(messageId))(any())).thenReturn(Future.successful(Some(domain.message)))
      Get(s"/api/messages/$messageId") ~> route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[Envelope[MessageView]].data shouldBe rest.message
      }
    }

    it("should respond with the appropriate error if the message is not found") {
      val messageId = UUID.randomUUID

      when(testServer.planningService.getMessage(eqTo(messageId))(any())).thenReturn(Future.successful(None))
      Get(s"/api/messages/$messageId") ~> route ~> check {
        status shouldBe StatusCodes.NotFound
      }
    }
  }

  describe("getting messages") {
    it("should retrieve a list of all messages") {
      val messages = Seq(domain.message, domain.message.copy(uuid = UUID.randomUUID, sender = "different sender", content = "different content"))

      when(testServer.planningService.getMessages(any())).thenReturn(Future.successful(messages))
      Get(s"/api/messages") ~> route ~> check {
        status shouldBe StatusCodes.OK
        responseAs[Envelope[Seq[MessageView]]].data shouldBe messages.map(_.toView)
      }
    }
  }

  describe("deleting a message") {
    it("should return OK if the deletion is completed") {
      val messageId = UUID.randomUUID

      when(testServer.planningService.deleteMessage(eqTo(messageId))(any())).thenReturn(Future.successful(true))
      Delete(s"/api/messages/$messageId") ~> route ~> check {
        status shouldBe StatusCodes.OK
      }
    }

    it("should respond with the appropriate error if the deletion could not be completed") {
      val messageId = UUID.randomUUID

      when(testServer.planningService.deleteMessage(eqTo(messageId))(any())).thenReturn(Future.successful(false))
      Delete(s"/api/messages/$messageId") ~> route ~> check {
        status shouldBe StatusCodes.NotFound
      }
    }
  }
}
