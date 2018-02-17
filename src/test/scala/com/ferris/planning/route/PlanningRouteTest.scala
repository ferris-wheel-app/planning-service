package com.ferris.planning.route

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.ferris.microservice.exceptions.ApiExceptionFormats
import org.scalatest.FunSpec

class PlanningRouteTest extends FunSpec with ScalatestRouteTest with PlanningRestFormats with ApiExceptionFormats {

  describe("creating a message") {
    it("should respond with the created message") {

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
