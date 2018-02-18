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

    }

    it("should respond with the appropriate error if the message is not found") {

    }
  }

  describe("getting a message") {
    it("should respond with the requested message") {

    }

    it("should respond with the appropriate error if the message is not found") {

    }
  }

  describe("getting messages") {
    it("should retrieve a list of messages") {

    }
  }

  describe("deleting a message") {
    it("should return the result of the deletion as a boolean") {

    }
  }
}
