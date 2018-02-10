package com.ferris.planning.repo

import org.scalatest.{AsyncFunSpec, BeforeAndAfterEach, Matchers}
import org.scalatest.concurrent.ScalaFutures
import com.ferris.planning.sample.{SampleData => SD}

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

  describe("creating") {
    it("should be possible to create a message") {
      val created = repo.createMessage(SD.messageCreation).futureValue
      created.sender shouldBe SD.messageCreation.sender
      created.content shouldBe SD.messageCreation.content
    }
  }

  describe("updating") {
    it("should be possible to update a message") {
      val original = repo.createMessage(SD.messageCreation).futureValue
      val updated = repo.updateMessage(original.uuid, SD.messageUpdate).futureValue
      updated should not be empty
      updated.get should not be original
      updated.get.uuid shouldBe original.uuid
      updated.get.sender shouldBe SD.messageUpdate.sender.get
      updated.get.content shouldBe SD.messageUpdate.content.get
    }
  }

  describe("getting") {
    it("should be possible to retrieve a message") {
      val created = repo.createMessage(SD.messageCreation).futureValue
      val retrieved = repo.getMessage(created.uuid).futureValue
      retrieved should not be empty
      retrieved.get shouldBe created
    }
  }

  describe("deleting") {
    it("should be possible to delete a message") {
      val created = repo.createMessage(SD.messageCreation).futureValue
      val deletion = repo.deleteMessage(created.uuid).futureValue
      val retrieved = repo.getMessage(created.uuid).futureValue
      deletion shouldBe true
      retrieved shouldBe empty
    }
  }
}
