package com.ferris.planning.client

import java.time.LocalDate
import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.actor.ActorSystem
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{HttpResponse, RequestEntity, ResponseEntity}
import akka.stream.ActorMaterializer
import com.ferris.microservice.resource.DeletionResult
import com.ferris.planning.contract.format.PlanningRestFormats
import com.ferris.planning.contract.sample.{SampleData => SD}
import com.ferris.planning.server.PlanningServer
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.OptionValues._
import org.mockito.Mockito._
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PlanningServiceClientTest extends FunSpec with Matchers with ScalaFutures with MockitoSugar with PlanningRestFormats {

  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()

  val mockServer: PlanningServer = mock[PlanningServer]
  val client = new PlanningServiceClient(mockServer)

  case class Envelope[T](status: String, data: T)

  implicit def envFormat[T](implicit ev: JsonFormat[T]): RootJsonFormat[Envelope[T]] = jsonFormat2(Envelope[T])

  describe("a planning service client") {
    describe("handling creations") {
      it("should be able to create a skill-category") {
        val creationRequest = Marshal(SD.skillCategoryCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.skillCategory)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/skills/categories", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createSkillCategory(SD.skillCategoryCreation)) { response =>
          response shouldBe SD.skillCategory
        }
      }

      it("should be able to create a skill") {
        val creationRequest = Marshal(SD.skillCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.skill)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/skills", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createSkill(SD.skillCreation)) { response =>
          response shouldBe SD.skill
        }
      }

      it("should be able to create a backlog-item") {
        val creationRequest = Marshal(SD.backlogItemCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.backlogItem)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/backlog-items", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createBacklogItem(SD.backlogItemCreation)) { response =>
          response shouldBe SD.backlogItem
        }
      }

      it("should be able to create a epoch") {
        val creationRequest = Marshal(SD.epochCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.epoch)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/epochs", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createEpoch(SD.epochCreation)) { response =>
          response shouldBe SD.epoch
        }
      }

      it("should be able to create a year") {
        val creationRequest = Marshal(SD.yearCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.year)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/years", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createYear(SD.yearCreation)) { response =>
          response shouldBe SD.year
        }
      }

      it("should be able to create a theme") {
        val creationRequest = Marshal(SD.themeCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.theme)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/themes", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createTheme(SD.themeCreation)) { response =>
          response shouldBe SD.theme
        }
      }

      it("should be able to create a goal") {
        val creationRequest = Marshal(SD.goalCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.goal)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/goals", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createGoal(SD.goalCreation)) { response =>
          response shouldBe SD.goal
        }
      }

      it("should be able to create a thread") {
        val creationRequest = Marshal(SD.threadCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.thread)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/threads", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createThread(SD.threadCreation)) { response =>
          response shouldBe SD.thread
        }
      }

      it("should be able to create a weave") {
        val creationRequest = Marshal(SD.weaveCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.weave)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/weaves", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createWeave(SD.weaveCreation)) { response =>
          response shouldBe SD.weave
        }
      }

      it("should be able to create a laser-donut") {
        val creationRequest = Marshal(SD.laserDonutCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.laserDonut)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/laser-donuts", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createLaserDonut(SD.laserDonutCreation)) { response =>
          response shouldBe SD.laserDonut
        }
      }

      it("should be able to create a portion") {
        val creationRequest = Marshal(SD.portionCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.portion)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/portions", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createPortion(SD.portionCreation)) { response =>
          response shouldBe SD.portion
        }
      }

      it("should be able to create a todo") {
        val creationRequest = Marshal(SD.todoCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.todo)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/todos", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createTodo(SD.todoCreation)) { response =>
          response shouldBe SD.todo
        }
      }

      it("should be able to create a hobby") {
        val creationRequest = Marshal(SD.hobbyCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.hobby)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/hobbies", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createHobby(SD.hobbyCreation)) { response =>
          response shouldBe SD.hobby
        }
      }

      it("should be able to create a one-off") {
        val creationRequest = Marshal(SD.oneOffCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.oneOff)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/one-offs", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createOneOff(SD.oneOffCreation)) { response =>
          response shouldBe SD.oneOff
        }
      }

      it("should be able to create a scheduled-one-off") {
        val creationRequest = Marshal(SD.scheduledOneOffCreation.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.scheduledOneOff)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/scheduled-one-offs", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createScheduledOneOff(SD.scheduledOneOffCreation)) { response =>
          response shouldBe SD.scheduledOneOff
        }
      }

      it("should be able to create a pyramid of importance") {
        val creationRequest = Marshal(SD.pyramidUpsert.toJson).to[RequestEntity].futureValue
        val creationResponse = Marshal(Envelope("OK", SD.pyramid)).to[ResponseEntity].futureValue
        when(mockServer.sendPostRequest("/api/pyramid", creationRequest)).thenReturn(Future.successful(HttpResponse(entity = creationResponse)))
        whenReady(client.createPyramidOfImportance(SD.pyramidUpsert)) { response =>
          response shouldBe SD.pyramid
        }
      }
    }

    describe("handling updates") {
      it("should be able to update a skill-category") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.skillCategoryUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.skillCategory)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/skills/categories/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateSkillCategory(id, SD.skillCategoryUpdate)) { response =>
          response shouldBe SD.skillCategory
        }
      }

      it("should be able to update a skill") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.skillUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.skill)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/skills/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateSkill(id, SD.skillUpdate)) { response =>
          response shouldBe SD.skill
        }
      }

      it("should be able to update the practised hours for a skill") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.practisedHours.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.skill)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/skills/$id/practised-hours/increment", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updatePractisedHours(id, SD.practisedHours.value)) { response =>
          response shouldBe SD.skill
        }
      }

      it("should be able to update a backlog-item") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.backlogItemUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.backlogItem)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/backlog-items/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateBacklogItem(id, SD.backlogItemUpdate)) { response =>
          response shouldBe SD.backlogItem
        }
      }

      it("should be able to update a epoch") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.epochUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.epoch)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/epochs/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateEpoch(id, SD.epochUpdate)) { response =>
          response shouldBe SD.epoch
        }
      }

      it("should be able to update a year") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.yearUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.year)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/years/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateYear(id, SD.yearUpdate)) { response =>
          response shouldBe SD.year
        }
      }

      it("should be able to update a theme") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.themeUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.theme)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/themes/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateTheme(id, SD.themeUpdate)) { response =>
          response shouldBe SD.theme
        }
      }

      it("should be able to update a goal") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.goalUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.goal)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/goals/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateGoal(id, SD.goalUpdate)) { response =>
          response shouldBe SD.goal
        }
      }

      it("should be able to update a thread") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.threadUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.thread)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/threads/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateThread(id, SD.threadUpdate)) { response =>
          response shouldBe SD.thread
        }
      }

      it("should be able to update a weave") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.weaveUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.weave)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/weaves/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateWeave(id, SD.weaveUpdate)) { response =>
          response shouldBe SD.weave
        }
      }

      it("should be able to update a laser-donut") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.laserDonutUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.laserDonut)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/laser-donuts/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateLaserDonut(id, SD.laserDonutUpdate)) { response =>
          response shouldBe SD.laserDonut
        }
      }

      it("should be able to update a portion") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.portionUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.portion)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/portions/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updatePortion(id, SD.portionUpdate)) { response =>
          response shouldBe SD.portion
        }
      }

      it("should be able to update a todo") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.todoUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.todo)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/todos/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateTodo(id, SD.todoUpdate)) { response =>
          response shouldBe SD.todo
        }
      }

      it("should be able to update a hobby") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.hobbyUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.hobby)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/hobbies/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateHobby(id, SD.hobbyUpdate)) { response =>
          response shouldBe SD.hobby
        }
      }

      it("should be able to update a one-off") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.oneOffUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.oneOff)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/one-offs/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateOneOff(id, SD.oneOffUpdate)) { response =>
          response shouldBe SD.oneOff
        }
      }

      it("should be able to update a scheduled-one-off") {
        val id = UUID.randomUUID
        val updateRequest = Marshal(SD.scheduledOneOffUpdate.toJson).to[RequestEntity].futureValue
        val updateResponse = Marshal(Envelope("OK", SD.scheduledOneOff)).to[ResponseEntity].futureValue
        when(mockServer.sendPutRequest(s"/api/scheduled-one-offs/$id", updateRequest)).thenReturn(Future.successful(HttpResponse(entity = updateResponse)))
        whenReady(client.updateScheduledOneOff(id, SD.scheduledOneOffUpdate)) { response =>
          response shouldBe SD.scheduledOneOff
        }
      }
    }

    describe("handling retrievals") {
      it("should be able to retrieve a skill-category") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.skillCategory)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/skills/categories/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.skillCategory(id)) { response =>
          response.value shouldBe SD.skillCategory
        }
      }

      it("should be able to retrieve a list of skill-categories") {
        val list = SD.skillCategory :: SD.skillCategory :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/skills/categories")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.skillCategories) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a skill") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.skill)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/skills/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.skill(id)) { response =>
          response.value shouldBe SD.skill`
        }
      }

      it("should be able to retrieve a list of skill") {
        val list = SD.skill :: SD.skill :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/skills")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.skills) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a backlog-item") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.backlogItem)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/backlog-items/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.backlogItem(id)) { response =>
          response.value shouldBe SD.backlogItem
        }
      }

      it("should be able to retrieve a list of backlog-items") {
        val list = SD.backlogItem :: SD.backlogItem :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/backlog-items")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.backlogItems) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a epoch") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.epoch)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/epochs/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.epoch(id)) { response =>
          response.value shouldBe SD.epoch
        }
      }

      it("should be able to retrieve a list of epochs") {
        val list = SD.epoch :: SD.epoch :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/epochs")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.epochs) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a year") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.year)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/years/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.year(id)) { response =>
          response.value shouldBe SD.year
        }
      }

      it("should be able to retrieve a list of years") {
        val list = SD.year :: SD.year :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/years")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.years) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a theme") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.theme)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/themes/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.theme(id)) { response =>
          response.value shouldBe SD.theme
        }
      }

      it("should be able to retrieve a list of themes") {
        val list = SD.theme :: SD.theme :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/themes")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.themes) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a goal") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.goal)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/goals/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.goal(id)) { response =>
          response.value shouldBe SD.goal
        }
      }

      it("should be able to retrieve a list of goals") {
        val list = SD.goal :: SD.goal :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/goals")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.goals) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a thread") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.thread)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/threads/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.thread(id)) { response =>
          response.value shouldBe SD.thread
        }
      }

      it("should be able to retrieve a list of threads") {
        val list = SD.thread :: SD.thread :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/threads")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.threads) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a weave") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.weave)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/weaves/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.weave(id)) { response =>
          response.value shouldBe SD.weave
        }
      }

      it("should be able to retrieve a list of weaves") {
        val list = SD.weave :: SD.weave :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/weaves")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.weaves) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a laser-donut") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.laserDonut)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/laser-donuts/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.laserDonut(id)) { response =>
          response.value shouldBe SD.laserDonut
        }
      }

      it("should be able to retrieve the current laser-donut") {
        val response = Marshal(Envelope("OK", SD.laserDonut)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/laser-donuts/current")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.currentLaserDonut) { response =>
          response.value shouldBe SD.laserDonut
        }
      }

      it("should be able to retrieve a list of laser-donuts") {
        val list = SD.laserDonut :: SD.laserDonut :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/laser-donuts")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.laserDonuts) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a portion") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.portion)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/portions/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.portion(id)) { response =>
          response.value shouldBe SD.portion
        }
      }

      it("should be able to retrieve the current portion") {
        val response = Marshal(Envelope("OK", SD.portion)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/portions/current")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.currentPortion) { response =>
          response.value shouldBe SD.portion
        }
      }

      it("should be able to retrieve a list of portions") {
        val list = SD.portion :: SD.portion :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/portions")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.portions) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a todo") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.todo)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/todos/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.todo(id)) { response =>
          response.value shouldBe SD.todo
        }
      }

      it("should be able to retrieve a list of todos") {
        val list = SD.todo :: SD.todo :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/todos")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.todos) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a hobby") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.hobby)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/hobbies/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.hobby(id)) { response =>
          response.value shouldBe SD.hobby
        }
      }

      it("should be able to retrieve a list of hobbies") {
        val list = SD.hobby :: SD.hobby :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/hobbies")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.hobbies) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a one-off") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.oneOff)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/one-offs/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.oneOff(id)) { response =>
          response.value shouldBe SD.oneOff
        }
      }

      it("should be able to retrieve a list of one-offs") {
        val list = SD.oneOff :: SD.oneOff :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/one-offs")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.oneOffs) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a scheduled-one-off") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", SD.scheduledOneOff)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/scheduled-one-offs/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.scheduledOneOff(id)) { response =>
          response.value shouldBe SD.scheduledOneOff
        }
      }

      it("should be able to retrieve a list of all scheduled-one-offs") {
        val list = SD.scheduledOneOff :: SD.scheduledOneOff :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest("/api/scheduled-one-offs")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.scheduledOneOffs(None)) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a list of scheduled-one-offs by date") {
        val date = LocalDate.of(2018, 12, 3)
        val list = SD.scheduledOneOff :: SD.scheduledOneOff :: Nil
        val response = Marshal(Envelope("OK", list)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/scheduled-one-offs?date=${date.toString}")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.scheduledOneOffs(Some(date))) { response =>
          response shouldBe list
        }
      }

      it("should be able to retrieve a pyramid") {
        val response = Marshal(Envelope("OK", SD.pyramid)).to[ResponseEntity].futureValue
        when(mockServer.sendGetRequest(s"/api/pyramid")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.pyramidOfImportance) { response =>
          response.value shouldBe SD.pyramid
        }
      }
    }

    describe("handling deletions") {
      it("should be able to delete a skill-category") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/skills/categories/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteSkillCategory(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a skill") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/skills/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteSkill(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a backlog-item") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/backlog-items/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteBacklogItem(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a epoch") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/epochs/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteEpoch(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a year") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/years/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteYear(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a theme") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/themes/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteTheme(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a goal") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/goals/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteGoal(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a thread") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/threads/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteThread(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a weave") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/weaves/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteWeave(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a laser-donut") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/laser-donuts/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteLaserDonut(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a portion") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/portions/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deletePortion(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a todo") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/todos/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteTodo(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a hobby") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/hobbies/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteHobby(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a one-off") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/one-offs/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteOneOff(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }

      it("should be able to delete a scheduled-one-off") {
        val id = UUID.randomUUID
        val response = Marshal(Envelope("OK", DeletionResult.successful)).to[ResponseEntity].futureValue
        when(mockServer.sendDeleteRequest(s"/api/scheduled-one-offs/$id")).thenReturn(Future.successful(HttpResponse(entity = response)))
        whenReady(client.deleteScheduledOneOff(id)) { response =>
          response shouldBe DeletionResult.successful
        }
      }
    }
  }
}
