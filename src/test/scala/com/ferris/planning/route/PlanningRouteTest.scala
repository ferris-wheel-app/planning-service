package com.ferris.planning.route

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import com.ferris.microservice.service.Envelope
import com.ferris.planning.service.conversions.ExternalToCommand._
import com.ferris.planning.service.conversions.ModelToView._
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

      describe("getting backlog-items") {
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

    describe("handling epochs") {
      describe("creating a epoch") {
        it("should respond with the created epoch") {
          when(testServer.planningService.createEpoch(eqTo(domain.epochCreation))(any())).thenReturn(Future.successful(domain.epoch))
          Post("/api/epochs", rest.epochCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[EpochView]].data shouldBe rest.epoch
            verify(testServer.planningService, times(1)).createEpoch(eqTo(domain.epochCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a epoch") {
        it("should respond with the updated epoch") {
          val id = UUID.randomUUID
          val update = rest.epochUpdate
          val updated = domain.epoch

          when(testServer.planningService.updateEpoch(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/epochs/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[EpochView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateEpoch(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the epoch is not found") {
          val id = UUID.randomUUID
          val update = rest.epochUpdate

          when(testServer.planningService.updateEpoch(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/epochs/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateEpoch(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a epoch") {
        it("should respond with the requested epoch") {
          val id = UUID.randomUUID

          when(testServer.planningService.getEpoch(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.epoch)))
          Get(s"/api/epochs/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[EpochView]].data shouldBe rest.epoch
            verify(testServer.planningService, times(1)).getEpoch(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the epoch is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getEpoch(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/epochs/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getEpoch(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting epochs") {
        it("should retrieve a list of all epochs") {
          val epochs = Seq(domain.epoch, domain.epoch.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getEpochs(any())).thenReturn(Future.successful(epochs))
          Get(s"/api/epochs") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[EpochView]]].data shouldBe epochs.map(_.toView)
            verify(testServer.planningService, times(1)).getEpochs(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a epoch") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteEpoch(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/epochs/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteEpoch(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteEpoch(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/epochs/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteEpoch(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling years") {
      describe("creating a year") {
        it("should respond with the created year") {
          when(testServer.planningService.createYear(eqTo(domain.yearCreation))(any())).thenReturn(Future.successful(domain.year))
          Post("/api/years", rest.yearCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[YearView]].data shouldBe rest.year
            verify(testServer.planningService, times(1)).createYear(eqTo(domain.yearCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a year") {
        it("should respond with the updated year") {
          val id = UUID.randomUUID
          val update = rest.yearUpdate
          val updated = domain.year

          when(testServer.planningService.updateYear(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/years/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[YearView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateYear(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the year is not found") {
          val id = UUID.randomUUID
          val update = rest.yearUpdate

          when(testServer.planningService.updateYear(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/years/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateYear(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a year") {
        it("should respond with the requested year") {
          val id = UUID.randomUUID

          when(testServer.planningService.getYear(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.year)))
          Get(s"/api/years/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[YearView]].data shouldBe rest.year
            verify(testServer.planningService, times(1)).getYear(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the year is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getYear(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/years/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getYear(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting years") {
        it("should retrieve a list of all years") {
          val years = Seq(domain.year, domain.year.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getYears(any())).thenReturn(Future.successful(years))
          Get(s"/api/years") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[YearView]]].data shouldBe years.map(_.toView)
            verify(testServer.planningService, times(1)).getYears(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a year") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteYear(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/years/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteYear(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteYear(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/years/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteYear(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling themes") {
      describe("creating a theme") {
        it("should respond with the created theme") {
          when(testServer.planningService.createTheme(eqTo(domain.themeCreation))(any())).thenReturn(Future.successful(domain.theme))
          Post("/api/themes", rest.themeCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[ThemeView]].data shouldBe rest.theme
            verify(testServer.planningService, times(1)).createTheme(eqTo(domain.themeCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a theme") {
        it("should respond with the updated theme") {
          val id = UUID.randomUUID
          val update = rest.themeUpdate
          val updated = domain.theme

          when(testServer.planningService.updateTheme(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/themes/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[ThemeView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateTheme(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the theme is not found") {
          val id = UUID.randomUUID
          val update = rest.themeUpdate

          when(testServer.planningService.updateTheme(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/themes/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateTheme(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a theme") {
        it("should respond with the requested theme") {
          val id = UUID.randomUUID

          when(testServer.planningService.getTheme(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.theme)))
          Get(s"/api/themes/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[ThemeView]].data shouldBe rest.theme
            verify(testServer.planningService, times(1)).getTheme(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the theme is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getTheme(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/themes/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getTheme(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting themes") {
        it("should retrieve a list of all themes") {
          val themes = Seq(domain.theme, domain.theme.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getThemes(any())).thenReturn(Future.successful(themes))
          Get(s"/api/themes") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[ThemeView]]].data shouldBe themes.map(_.toView)
            verify(testServer.planningService, times(1)).getThemes(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a theme") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteTheme(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/themes/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteTheme(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteTheme(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/themes/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteTheme(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling goals") {
      describe("creating a goal") {
        it("should respond with the created goal") {
          when(testServer.planningService.createGoal(eqTo(domain.goalCreation))(any())).thenReturn(Future.successful(domain.goal))
          Post("/api/goals", rest.goalCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[GoalView]].data shouldBe rest.goal
            verify(testServer.planningService, times(1)).createGoal(eqTo(domain.goalCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a goal") {
        it("should respond with the updated goal") {
          val id = UUID.randomUUID
          val update = rest.goalUpdate
          val updated = domain.goal

          when(testServer.planningService.updateGoal(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/goals/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[GoalView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateGoal(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the goal is not found") {
          val id = UUID.randomUUID
          val update = rest.goalUpdate

          when(testServer.planningService.updateGoal(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/goals/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateGoal(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a goal") {
        it("should respond with the requested goal") {
          val id = UUID.randomUUID

          when(testServer.planningService.getGoal(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.goal)))
          Get(s"/api/goals/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[GoalView]].data shouldBe rest.goal
            verify(testServer.planningService, times(1)).getGoal(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the goal is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getGoal(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/goals/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getGoal(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting goals") {
        it("should retrieve a list of all goals") {
          val goals = Seq(domain.goal, domain.goal.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getGoals(any())).thenReturn(Future.successful(goals))
          Get(s"/api/goals") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[GoalView]]].data shouldBe goals.map(_.toView)
            verify(testServer.planningService, times(1)).getGoals(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a goal") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteGoal(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/goals/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteGoal(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteGoal(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/goals/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteGoal(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling threads") {
      describe("creating a thread") {
        it("should respond with the created thread") {
          when(testServer.planningService.createThread(eqTo(domain.threadCreation))(any())).thenReturn(Future.successful(domain.thread))
          Post("/api/threads", rest.threadCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[ThreadView]].data shouldBe rest.thread
            verify(testServer.planningService, times(1)).createThread(eqTo(domain.threadCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a thread") {
        it("should respond with the updated thread") {
          val id = UUID.randomUUID
          val update = rest.threadUpdate
          val updated = domain.thread

          when(testServer.planningService.updateThread(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/threads/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[ThreadView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateThread(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the thread is not found") {
          val id = UUID.randomUUID
          val update = rest.threadUpdate

          when(testServer.planningService.updateThread(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/threads/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateThread(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a thread") {
        it("should respond with the requested thread") {
          val id = UUID.randomUUID

          when(testServer.planningService.getThread(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.thread)))
          Get(s"/api/threads/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[ThreadView]].data shouldBe rest.thread
            verify(testServer.planningService, times(1)).getThread(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the thread is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getThread(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/threads/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getThread(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting threads") {
        it("should retrieve a list of all threads") {
          val threads = Seq(domain.thread, domain.thread.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getThreads(any())).thenReturn(Future.successful(threads))
          Get(s"/api/threads") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[ThreadView]]].data shouldBe threads.map(_.toView)
            verify(testServer.planningService, times(1)).getThreads(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a thread") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteThread(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/threads/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteThread(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteThread(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/threads/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteThread(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling weaves") {
      describe("creating a weave") {
        it("should respond with the created weave") {
          when(testServer.planningService.createWeave(eqTo(domain.weaveCreation))(any())).thenReturn(Future.successful(domain.weave))
          Post("/api/weaves", rest.weaveCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[WeaveView]].data shouldBe rest.weave
            verify(testServer.planningService, times(1)).createWeave(eqTo(domain.weaveCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a weave") {
        it("should respond with the updated weave") {
          val id = UUID.randomUUID
          val update = rest.weaveUpdate
          val updated = domain.weave

          when(testServer.planningService.updateWeave(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/weaves/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[WeaveView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateWeave(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the weave is not found") {
          val id = UUID.randomUUID
          val update = rest.weaveUpdate

          when(testServer.planningService.updateWeave(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/weaves/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateWeave(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a weave") {
        it("should respond with the requested weave") {
          val id = UUID.randomUUID

          when(testServer.planningService.getWeave(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.weave)))
          Get(s"/api/weaves/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[WeaveView]].data shouldBe rest.weave
            verify(testServer.planningService, times(1)).getWeave(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the weave is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getWeave(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/weaves/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getWeave(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting weaves") {
        it("should retrieve a list of all weaves") {
          val weaves = Seq(domain.weave, domain.weave.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getWeaves(any())).thenReturn(Future.successful(weaves))
          Get(s"/api/weaves") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[WeaveView]]].data shouldBe weaves.map(_.toView)
            verify(testServer.planningService, times(1)).getWeaves(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a weave") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteWeave(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/weaves/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteWeave(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteWeave(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/weaves/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteWeave(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling laser-donuts") {
      describe("creating a weave") {
        it("should respond with the created laser-donut") {
          when(testServer.planningService.createLaserDonut(eqTo(domain.laserDonutCreation))(any())).thenReturn(Future.successful(domain.laserDonut))
          Post("/api/laser-donuts", rest.laserDonutCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[LaserDonutView]].data shouldBe rest.laserDonut
            verify(testServer.planningService, times(1)).createLaserDonut(eqTo(domain.laserDonutCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a laser-donut") {
        it("should respond with the updated laser-donut") {
          val id = UUID.randomUUID
          val update = rest.laserDonutUpdate
          val updated = domain.laserDonut

          when(testServer.planningService.updateLaserDonut(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/laser-donuts/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[LaserDonutView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateLaserDonut(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the laser-donut is not found") {
          val id = UUID.randomUUID
          val update = rest.laserDonutUpdate

          when(testServer.planningService.updateLaserDonut(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/laser-donuts/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateLaserDonut(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a laser-donut") {
        it("should respond with the requested laser-donut") {
          val id = UUID.randomUUID

          when(testServer.planningService.getLaserDonut(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.laserDonut)))
          Get(s"/api/laser-donuts/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[LaserDonutView]].data shouldBe rest.laserDonut
            verify(testServer.planningService, times(1)).getLaserDonut(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the laser-donut is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getLaserDonut(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/laser-donuts/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getLaserDonut(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting laser-donuts") {
        it("should retrieve a list of all laser-donuts") {
          val laserDonuts = Seq(domain.laserDonut, domain.laserDonut.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getLaserDonuts(any())).thenReturn(Future.successful(laserDonuts))
          Get(s"/api/laser-donuts") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[LaserDonutView]]].data shouldBe laserDonuts.map(_.toView)
            verify(testServer.planningService, times(1)).getLaserDonuts(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a laser-donut") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteLaserDonut(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/laser-donuts/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteLaserDonut(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteLaserDonut(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/laser-donuts/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteLaserDonut(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling portions") {
      describe("creating a portion") {
        it("should respond with the created portion") {
          when(testServer.planningService.createPortion(eqTo(domain.portionCreation))(any())).thenReturn(Future.successful(domain.portion))
          Post("/api/portions", rest.portionCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[PortionView]].data shouldBe rest.portion
            verify(testServer.planningService, times(1)).createPortion(eqTo(domain.portionCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a portion") {
        it("should respond with the updated portion") {
          val id = UUID.randomUUID
          val update = rest.portionUpdate
          val updated = domain.portion

          when(testServer.planningService.updatePortion(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/portions/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[PortionView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updatePortion(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the portion is not found") {
          val id = UUID.randomUUID
          val update = rest.portionUpdate

          when(testServer.planningService.updatePortion(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/portions/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updatePortion(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a portion") {
        it("should respond with the requested portion") {
          val id = UUID.randomUUID

          when(testServer.planningService.getPortion(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.portion)))
          Get(s"/api/portions/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[PortionView]].data shouldBe rest.portion
            verify(testServer.planningService, times(1)).getPortion(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the portion is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getPortion(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/portions/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getPortion(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting portions") {
        it("should retrieve a list of all portions") {
          val portions = Seq(domain.portion, domain.portion.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getPortions(any())).thenReturn(Future.successful(portions))
          Get(s"/api/portions") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[PortionView]]].data shouldBe portions.map(_.toView)
            verify(testServer.planningService, times(1)).getPortions(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a portion") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deletePortion(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/portions/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deletePortion(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deletePortion(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/portions/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deletePortion(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling todos") {
      describe("creating a todo") {
        it("should respond with the created todo") {
          when(testServer.planningService.createTodo(eqTo(domain.todoCreation))(any())).thenReturn(Future.successful(domain.todo))
          Post("/api/todos", rest.todoCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[TodoView]].data shouldBe rest.todo
            verify(testServer.planningService, times(1)).createTodo(eqTo(domain.todoCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a todo") {
        it("should respond with the updated todo") {
          val id = UUID.randomUUID
          val update = rest.todoUpdate
          val updated = domain.todo

          when(testServer.planningService.updateTodo(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/todos/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[TodoView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateTodo(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the todo is not found") {
          val id = UUID.randomUUID
          val update = rest.todoUpdate

          when(testServer.planningService.updateTodo(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/todos/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateTodo(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a todo") {
        it("should respond with the requested todo") {
          val id = UUID.randomUUID

          when(testServer.planningService.getTodo(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.todo)))
          Get(s"/api/todos/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[TodoView]].data shouldBe rest.todo
            verify(testServer.planningService, times(1)).getTodo(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the todo is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getTodo(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/todos/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getTodo(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting todos") {
        it("should retrieve a list of all todos") {
          val todos = Seq(domain.todo, domain.todo.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getTodos(any())).thenReturn(Future.successful(todos))
          Get(s"/api/todos") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[TodoView]]].data shouldBe todos.map(_.toView)
            verify(testServer.planningService, times(1)).getTodos(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a todo") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteTodo(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/todos/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteTodo(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteTodo(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/todos/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteTodo(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }

    describe("handling hobbies") {
      describe("creating a hobby") {
        it("should respond with the created hobby") {
          when(testServer.planningService.createHobby(eqTo(domain.hobbyCreation))(any())).thenReturn(Future.successful(domain.hobby))
          Post("/api/hobbies", rest.hobbyCreation) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[HobbyView]].data shouldBe rest.hobby
            verify(testServer.planningService, times(1)).createHobby(eqTo(domain.hobbyCreation))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("updating a hobby") {
        it("should respond with the updated hobby") {
          val id = UUID.randomUUID
          val update = rest.hobbyUpdate
          val updated = domain.hobby

          when(testServer.planningService.updateHobby(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(Some(updated)))
          Put(s"/api/hobbies/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[HobbyView]].data shouldBe updated.toView
            verify(testServer.planningService, times(1)).updateHobby(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the hobby is not found") {
          val id = UUID.randomUUID
          val update = rest.hobbyUpdate

          when(testServer.planningService.updateHobby(eqTo(id), eqTo(update.toCommand))(any())).thenReturn(Future.successful(None))
          Put(s"/api/hobbies/$id", update) ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).updateHobby(eqTo(id), eqTo(update.toCommand))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting a hobby") {
        it("should respond with the requested hobby") {
          val id = UUID.randomUUID

          when(testServer.planningService.getHobby(eqTo(id))(any())).thenReturn(Future.successful(Some(domain.hobby)))
          Get(s"/api/hobbies/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[HobbyView]].data shouldBe rest.hobby
            verify(testServer.planningService, times(1)).getHobby(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the hobby is not found") {
          val id = UUID.randomUUID

          when(testServer.planningService.getHobby(eqTo(id))(any())).thenReturn(Future.successful(None))
          Get(s"/api/hobbies/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).getHobby(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("getting hobbies") {
        it("should retrieve a list of all hobbies") {
          val hobbies = Seq(domain.hobby, domain.hobby.copy(uuid = UUID.randomUUID))

          when(testServer.planningService.getHobbies(any())).thenReturn(Future.successful(hobbies))
          Get(s"/api/hobbies") ~> route ~> check {
            status shouldBe StatusCodes.OK
            responseAs[Envelope[Seq[HobbyView]]].data shouldBe hobbies.map(_.toView)
            verify(testServer.planningService, times(1)).getHobbies(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }

      describe("deleting a hobby") {
        it("should return OK if the deletion is completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteHobby(eqTo(id))(any())).thenReturn(Future.successful(true))
          Delete(s"/api/hobbies/$id") ~> route ~> check {
            status shouldBe StatusCodes.OK
            verify(testServer.planningService, times(1)).deleteHobby(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }

        it("should respond with the appropriate error if the deletion could not be completed") {
          val id = UUID.randomUUID

          when(testServer.planningService.deleteHobby(eqTo(id))(any())).thenReturn(Future.successful(false))
          Delete(s"/api/hobbies/$id") ~> route ~> check {
            status shouldBe StatusCodes.NotFound
            verify(testServer.planningService, times(1)).deleteHobby(eqTo(id))(any())
            verifyNoMoreInteractions(testServer.planningService)
          }
        }
      }
    }
  }
}
