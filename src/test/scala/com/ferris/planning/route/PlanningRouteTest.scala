package com.ferris.planning.route

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import com.ferris.microservice.service.Envelope
import com.ferris.planning.rest.conversions.ExternalToCommand._
import com.ferris.planning.rest.conversions.ModelToView._
import com.ferris.planning.rest.Resources.Out._
import com.ferris.planning.sample.SampleData.{domain, rest}
import org.mockito.Matchers.{any, eq => eqTo}
import org.mockito.Mockito.{times, verify, verifyNoMoreInteractions, when}
import spray.json._

import scala.concurrent.Future

class PlanningRouteTest extends RouteTestFramework {

  describe("a planning API") {
    describe("handling messages") {
      describe("creating a message") {
        it("should respond with the created message") {
          when(testServer.planningService.createMessage(eqTo(domain.messageCreation))(any())).thenReturn(Future.successful(domain.message))
          Post("/api/messages", rest.messageCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[MessageView]].data shouldBe rest.message
            verify(testServer.planningService, times(1)).createMessage(eqTo(domain.messageCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a message") {
        it("should respond with the updated message") {
          val id = UUID.randomUUID
          val update = rest.messageUpdate
          val updated = domain.message

          when(testServer.planningService.updateMessage(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/messages/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[MessageView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateMessage(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the message is not found") {
          val id = UUID.randomUUID
          val update = rest.messageUpdate

          when(testServer.planningService.updateMessage(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/messages/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateMessage(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a message") {
        it("should respond with the requested message") {
          val id = UUID.randomUUID

          when(testServer.planningService.getMessage(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.message)))
          Get(s"/api/messages/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[MessageView]].data shouldBe rest.message
            verify(testServer.planningService, times(1)).getMessage(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the message is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getMessage(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/messages/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getMessage(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting messages") {
        it("should retrieve a list of all messages") {
          val messages = Seq(domain.message, domain.message.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getMessages(any())).thenReturn(Future.successful(messages))
          Get(s"/api/messages") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[MessageView]]].data shouldBe messages.map(_.toView)
            verify(testServer.planningService, times(1)).getMessages(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a message") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteMessage(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/messages/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteMessage(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteMessage(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/messages/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteMessage(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling backlog-items") {
      describe("creating a backlog-item") {
        it("should respond with the created backlog-item") {
          when(testServer.planningService.createBacklogItem(eqTo(domain.backlogItemCreation))(any())).thenReturn(Future.successful(domain.backlogItem))
          Post("/api/backlog-items", rest.backlogItemCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[BacklogItemView]].data shouldBe rest.backlogItem
            verify(testServer.planningService, times(1)).createBacklogItem(eqTo(domain.backlogItemCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a backlog-item") {
        it("should respond with the updated backlog-item") {
          val id = UUID.randomUUID
          val update = rest.backlogItemUpdate
          val updated = domain.backlogItem

          when(testServer.planningService.updateBacklogItem(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/backlog-items/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[BacklogItemView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateBacklogItem(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the backlog-item is not found") {
          val id = UUID.randomUUID
          val update = rest.backlogItemUpdate

          when(testServer.planningService.updateBacklogItem(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/backlog-items/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateBacklogItem(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a backlog-item") {
        it("should respond with the requested backlog-item") {
          val id = UUID.randomUUID

          when(testServer.planningService.getBacklogItem(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.backlogItem)))
          Get(s"/api/backlog-items/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[BacklogItemView]].data shouldBe rest.backlogItem
            verify(testServer.planningService, times(1)).getBacklogItem(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the backlog-item is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getBacklogItem(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/backlog-items/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getBacklogItem(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting backlog-item") {
        it("should retrieve a list of all backlog-items") {
          val backlogItems = Seq(domain.backlogItem, domain.backlogItem.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getBacklogItems(any())).thenReturn(Future.successful(backlogItems))
          Get(s"/api/backlog-items") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[BacklogItemView]]].data shouldBe backlogItems.map(_.toView)
            verify(testServer.planningService, times(1)).getBacklogItems(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a backlog-item") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteBacklogItem(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/backlog-items/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteBacklogItem(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteBacklogItem(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/backlog-items/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteBacklogItem(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }
  }
}
