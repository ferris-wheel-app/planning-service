package com.ferris.planning.repo

import java.util.UUID

import org.scalatest.{AsyncFunSpec, BeforeAndAfterEach, Matchers}
import org.scalatest.concurrent.ScalaFutures
import com.ferris.planning.sample.{SampleData => SD}
import com.ferris.planning.service.exceptions.Exceptions.MessageNotFoundException

import scala.concurrent.duration._

class PlanningRepositoryTest extends AsyncFunSpec
  with Matchers
  with ScalaFutures
  with BeforeAndAfterEach
  with H2PlanningRepositoryComponent {

  implicit val dbTimeout: FiniteDuration = 20.seconds

  override def beforeEach(): Unit = {
    RepositoryUtils.createOrResetTables(db, dbTimeout)(repoEc)
    super.beforeEach
  }

  describe("message creating") {
    it("should create a message") {
      val created = repo.createMessage(SD.messageCreation).futureValue
      created.sender shouldBe SD.messageCreation.sender
      created.content shouldBe SD.messageCreation.content
    }
  }

  describe("message updating") {
    it("should update a message") {
      val original = repo.createMessage(SD.messageCreation).futureValue
      val updated = repo.updateMessage(original.uuid, SD.messageUpdate).futureValue
      updated should not be empty
      updated.get should not be original
      updated.get.uuid shouldBe original.uuid
      updated.get.sender shouldBe SD.messageUpdate.sender.get
      updated.get.content shouldBe SD.messageUpdate.content.get
    }

    it("should throw an exception if a message is not found") {
      whenReady(repo.updateMessage(UUID.randomUUID, SD.messageUpdate).failed) { exception =>
        exception shouldBe MessageNotFoundException()
      }
    }
  }

  describe("message retrieving") {
    it("should retrieve a message") {
      val created = repo.createMessage(SD.messageCreation).futureValue
      val retrieved = repo.getMessage(created.uuid).futureValue
      retrieved should not be empty
      retrieved.get shouldBe created
    }

    it("should return none if a message is not found") {
      val retrieved = repo.getMessage(UUID.randomUUID).futureValue
      retrieved shouldBe empty
    }

    it("should retrieve a list of messages") {
      val created1 = repo.createMessage(SD.messageCreation).futureValue
      val created2 = repo.createMessage(SD.messageCreation.copy(sender = "HAL", content = "Never!")).futureValue
      val retrieved = repo.getMessages.futureValue
      retrieved should not be empty
      retrieved shouldBe Seq(created1, created2)
    }
  }

  describe("message deleting") {
    it("should delete a message") {
      val created = repo.createMessage(SD.messageCreation).futureValue
      val deletion = repo.deleteMessage(created.uuid).futureValue
      val retrieved = repo.getMessage(created.uuid).futureValue
      deletion shouldBe true
      retrieved shouldBe empty
    }
  }
}
