package com.ferris.planning.service

import java.util.UUID

import com.ferris.planning.sample.{SampleData => SD}
import org.mockito.Matchers.{any, eq => eqTo}
import org.mockito.Mockito._
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

class PlanningServiceTest extends FunSpec with ScalaFutures with Matchers {

  implicit val defaultTimeout: PatienceConfig = PatienceConfig(scaled(15.seconds))

  def newServer = new DefaultPlanningServiceComponent with MockPlanningRepositoryComponent {
    override val planningService: DefaultPlanningService = new DefaultPlanningService()
  }

  describe("a planning service") {
    it("should be able to create a message") {
      val server = newServer
      when(server.repo.createMessage(SD.messageCreation)).thenReturn(Future.successful(SD.message))
      whenReady(server.planningService.createMessage(SD.messageCreation)) { result =>
        result shouldBe SD.message
        verify(server.repo, times(1)).createMessage(SD.messageCreation)
      }
    }

    it("should be able to update a message") {
      val server = newServer
      val messageId = UUID.randomUUID
      val updated = SD.message.copy(sender = "Al Capone", content = "Eat this!")
      when(server.repo.updateMessage(eqTo(messageId), eqTo(SD.messageUpdate))).thenReturn(Future.successful(Some(updated)))
      whenReady(server.planningService.updateMessage(messageId, SD.messageUpdate)) { result =>
        result shouldBe Some(updated)
        verify(server.repo, times(1)).updateMessage(eqTo(messageId), eqTo(SD.messageUpdate))
      }
    }

    it("should be able to retrieve a message") {
      val server = newServer
      val messageId = UUID.randomUUID
      when(server.repo.getMessage(messageId)).thenReturn(Future.successful(Some(SD.message)))
      whenReady(server.planningService.getMessage(messageId)) { result =>
        result shouldBe Some(SD.message)
        verify(server.repo, times(1)).getMessage(messageId)
      }
    }
  }
}
