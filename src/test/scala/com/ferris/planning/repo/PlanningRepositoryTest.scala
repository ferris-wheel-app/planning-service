package com.ferris.planning.repo

import java.util.UUID

import org.scalatest.{AsyncFunSpec, BeforeAndAfterEach, Matchers}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.OptionValues._
import com.ferris.planning.sample.SampleData.{domain => SD}
import com.ferris.planning.service.exceptions.Exceptions._

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

  describe("message") {
    describe("creating") {
      it("should create a message") {
        val created = repo.createMessage(SD.messageCreation).futureValue
        created.sender shouldBe SD.messageCreation.sender
        created.content shouldBe SD.messageCreation.content
      }
    }

    describe("updating") {
      it("should update a message") {
        val original = repo.createMessage(SD.messageCreation).futureValue
        val updated = repo.updateMessage(original.uuid, SD.messageUpdate).futureValue
        updated should not be empty
        updated.value should not be original
        updated.value.uuid shouldBe original.uuid
        updated.value.sender shouldBe SD.messageUpdate.sender.value
        updated.value.content shouldBe SD.messageUpdate.content.value
      }

      it("should throw an exception if a message is not found") {
        whenReady(repo.updateMessage(UUID.randomUUID, SD.messageUpdate).failed) { exception =>
          exception shouldBe MessageNotFoundException()
        }
      }
    }

    describe("retrieving") {
      it("should retrieve a message") {
        val created = repo.createMessage(SD.messageCreation).futureValue
        val retrieved = repo.getMessage(created.uuid).futureValue
        retrieved should not be empty
        retrieved.value shouldBe created
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

    describe("deleting") {
      it("should delete a message") {
        val created = repo.createMessage(SD.messageCreation).futureValue
        val deletion = repo.deleteMessage(created.uuid).futureValue
        val retrieved = repo.getMessage(created.uuid).futureValue
        deletion shouldBe true
        retrieved shouldBe empty
      }
    }
  }

  describe("backlog item") {
    describe("creating") {
      it("should create a backlog item") {
        val created = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        created.summary shouldBe SD.backlogItemCreation.summary
        created.description shouldBe SD.backlogItemCreation.description
        created.`type` shouldBe SD.backlogItemCreation.`type`
      }
    }

    describe("updating") {
      it("should update a backlog item") {
        val original = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val updated = repo.updateBacklogItem(original.uuid, SD.backlogItemUpdate).futureValue
        updated should not be empty
        updated.value should not be original
        updated.value.uuid shouldBe original.uuid
        updated.value.summary shouldBe SD.backlogItemUpdate.summary.value
        updated.value.description shouldBe SD.backlogItemUpdate.description.value
        updated.value.`type` shouldBe SD.backlogItemUpdate.`type`.value
      }

      it("should throw an exception if a message is not found") {
        whenReady(repo.updateBacklogItem(UUID.randomUUID, SD.backlogItemUpdate).failed) { exception =>
          exception shouldBe BacklogItemNotFoundException()
        }
      }
    }

    describe("retrieving") {
      it("should retrieve a backlog-item") {
        val created = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val retrieved = repo.getBacklogItem(created.uuid).futureValue
        retrieved should not be empty
        retrieved.value shouldBe created
      }

      it("should return none if a backlog-item is not found") {
        val retrieved = repo.getBacklogItem(UUID.randomUUID).futureValue
        retrieved shouldBe empty
      }

      it("should retrieve a list of backlog-items") {
        val created1 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val created2 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val retrieved = repo.getBacklogItems.futureValue
        retrieved should not be empty
        retrieved shouldBe Seq(created1, created2)
      }
    }

    describe("deleting") {
      it("should delete a backlog-item") {
        val created = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val deletion = repo.deleteBacklogItem(created.uuid).futureValue
        val retrieved = repo.getBacklogItem(created.uuid).futureValue
        deletion shouldBe true
        retrieved shouldBe empty
      }
    }
  }

  describe("epoch") {
    describe("creating") {
      it("should create an epoch") {
        val created = repo.createEpoch(SD.epochCreation).futureValue
        created.name shouldBe SD.epochCreation.name
        created.totem shouldBe SD.epochCreation.totem
        created.question shouldBe SD.epochCreation.question
      }
    }

    describe("updating") {
      it("should update an epoch") {
        val original = repo.createEpoch(SD.epochCreation).futureValue
        val updated = repo.updateEpoch(original.uuid, SD.epochUpdate).futureValue
        updated should not be empty
        updated.value should not be original
        updated.value.uuid shouldBe original.uuid
        updated.value.name shouldBe SD.epochUpdate.name.value
        updated.value.totem shouldBe SD.epochUpdate.totem.value
        updated.value.question shouldBe SD.epochUpdate.question.value
      }

      it("should throw an exception if an epoch is not found") {
        whenReady(repo.updateEpoch(UUID.randomUUID, SD.epochUpdate).failed) { exception =>
          exception shouldBe EpochNotFoundException()
        }
      }
    }

    describe("retrieving") {
      it("should retrieve an epoch") {
        val created = repo.createEpoch(SD.epochCreation).futureValue
        val retrieved = repo.getEpoch(created.uuid).futureValue
        retrieved should not be empty
        retrieved.value shouldBe created
      }

      it("should return none if an epoch is not found") {
        val retrieved = repo.getEpoch(UUID.randomUUID).futureValue
        retrieved shouldBe empty
      }

      it("should retrieve a list of epochs") {
        val created1 = repo.createEpoch(SD.epochCreation).futureValue
        val created2 = repo.createEpoch(SD.epochCreation).futureValue
        val retrieved = repo.getEpochs.futureValue
        retrieved should not be empty
        retrieved shouldBe Seq(created1, created2)
      }
    }

    describe("deleting") {
      it("should delete a epoch") {
        val created = repo.createEpoch(SD.epochCreation).futureValue
        val deletion = repo.deleteEpoch(created.uuid).futureValue
        val retrieved = repo.getEpoch(created.uuid).futureValue
        deletion shouldBe true
        retrieved shouldBe empty
      }
    }
  }

  describe("year") {
    describe("creating") {
      it("should create a year") {
        val created = repo.createYear(SD.yearCreation).futureValue
        created.epochId shouldBe SD.yearCreation.epochId
        created.startDate shouldBe SD.yearCreation.startDate
        created.finishDate shouldBe SD.yearCreation.finishDate
      }
    }

    describe("updating") {
      it("should update a year") {
        val original = repo.createYear(SD.yearCreation).futureValue
        val updated = repo.updateYear(original.uuid, SD.yearUpdate).futureValue
        updated should not be empty
        updated.value should not be original
        updated.value.uuid shouldBe original.uuid
        updated.value.epochId shouldBe SD.yearUpdate.epochId.value
        updated.value.startDate shouldBe SD.yearUpdate.startDate.value
        updated.value.finishDate shouldBe SD.yearUpdate.finishDate.value
      }

      it("should throw an exception if a year is not found") {
        whenReady(repo.updateYear(UUID.randomUUID, SD.yearUpdate).failed) { exception =>
          exception shouldBe YearNotFoundException()
        }
      }
    }

    describe("retrieving") {
      it("should retrieve a year") {
        val created = repo.createYear(SD.yearCreation).futureValue
        val retrieved = repo.getYear(created.uuid).futureValue
        retrieved should not be empty
        retrieved.value shouldBe created
      }

      it("should return none if a year is not found") {
        val retrieved = repo.getYear(UUID.randomUUID).futureValue
        retrieved shouldBe empty
      }

      it("should retrieve a list of years") {
        val created1 = repo.createYear(SD.yearCreation).futureValue
        val created2 = repo.createYear(SD.yearCreation).futureValue
        val retrieved = repo.getYears.futureValue
        retrieved should not be empty
        retrieved shouldBe Seq(created1, created2)
      }
    }

    describe("deleting") {
      it("should delete a year") {
        val created = repo.createYear(SD.yearCreation).futureValue
        val deletion = repo.deleteYear(created.uuid).futureValue
        val retrieved = repo.getYear(created.uuid).futureValue
        deletion shouldBe true
        retrieved shouldBe empty
      }
    }
  }

  describe("theme") {
    describe("creating") {
      it("should create a theme") {
        val created = repo.createTheme(SD.themeCreation).futureValue
        created.yearId shouldBe SD.themeCreation.yearId
        created.name shouldBe SD.themeCreation.name
      }
    }

    describe("updating") {
      it("should update a theme") {
        val original = repo.createTheme(SD.themeCreation).futureValue
        val updated = repo.updateTheme(original.uuid, SD.themeUpdate).futureValue
        updated should not be empty
        updated.value should not be original
        updated.value.uuid shouldBe original.uuid
        updated.value.yearId shouldBe SD.themeUpdate.yearId.value
        updated.value.name shouldBe SD.themeUpdate.name.value
      }

      it("should throw an exception if a theme is not found") {
        whenReady(repo.updateTheme(UUID.randomUUID, SD.themeUpdate).failed) { exception =>
          exception shouldBe ThemeNotFoundException()
        }
      }
    }

    describe("retrieving") {
      it("should retrieve a theme") {
        val created = repo.createTheme(SD.themeCreation).futureValue
        val retrieved = repo.getTheme(created.uuid).futureValue
        retrieved should not be empty
        retrieved.value shouldBe created
      }

      it("should return none if a theme is not found") {
        val retrieved = repo.getTheme(UUID.randomUUID).futureValue
        retrieved shouldBe empty
      }

      it("should retrieve a list of themes") {
        val created1 = repo.createTheme(SD.themeCreation).futureValue
        val created2 = repo.createTheme(SD.themeCreation).futureValue
        val retrieved = repo.getThemes.futureValue
        retrieved should not be empty
        retrieved shouldBe Seq(created1, created2)
      }
    }

    describe("deleting") {
      it("should delete a theme") {
        val created = repo.createTheme(SD.themeCreation).futureValue
        val deletion = repo.deleteTheme(created.uuid).futureValue
        val retrieved = repo.getTheme(created.uuid).futureValue
        deletion shouldBe true
        retrieved shouldBe empty
      }
    }
  }

  describe("goal") {
    describe("creating") {
      it("should create a goal") {
        val backlogItem1 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val backlogItem2 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val backlogItems =  backlogItem1.uuid :: backlogItem2.uuid :: Nil
        val created = repo.createGoal(SD.goalCreation.copy(backlogItems = backlogItems)).futureValue
        created.themeId shouldBe SD.goalCreation.themeId
        created.backlogItems should contain theSameElementsAs backlogItems
        created.summary shouldBe SD.goalCreation.summary
        created.description shouldBe SD.goalCreation.description
        created.level shouldBe SD.goalCreation.level
        created.priority shouldBe SD.goalCreation.priority
        created.graduation shouldBe SD.goalCreation.graduation
        created.status shouldBe SD.goalCreation.status
      }
    }

    describe("updating") {
      it("should update a goal") {
        val backlogItem1 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val backlogItem2 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val backlogItem3 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val backlogItem4 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val originalBacklogItems =  backlogItem1.uuid :: backlogItem2.uuid :: Nil
        val newBacklogItems =  backlogItem3.uuid :: backlogItem4.uuid :: Nil
        val original = repo.createGoal(SD.goalCreation.copy(backlogItems = originalBacklogItems)).futureValue
        val updated = repo.updateGoal(original.uuid, SD.goalUpdate.copy(backlogItems = Some(newBacklogItems))).futureValue
        updated should not be empty
        updated.value should not be original
        updated.value.uuid shouldBe original.uuid
        updated.value.themeId shouldBe SD.goalUpdate.themeId.value
        updated.value.backlogItems should contain theSameElementsAs newBacklogItems
        updated.value.summary shouldBe SD.goalUpdate.summary.value
        updated.value.description shouldBe SD.goalUpdate.description.value
        updated.value.level shouldBe SD.goalUpdate.level.value
        updated.value.priority shouldBe SD.goalUpdate.priority.value
        updated.value.graduation shouldBe SD.goalUpdate.graduation.value
        updated.value.status shouldBe SD.goalUpdate.status.value
      }

      it("should throw an exception if a goal is not found") {
        whenReady(repo.updateGoal(UUID.randomUUID, SD.goalUpdate).failed) { exception =>
          exception shouldBe GoalNotFoundException()
        }
      }
    }

    describe("retrieving") {
      it("should retrieve a goal") {
        val backlogItem1 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val backlogItem2 = repo.createBacklogItem(SD.backlogItemCreation).futureValue
        val backlogItems =  backlogItem1.uuid :: backlogItem2.uuid :: Nil
        val created = repo.createGoal(SD.goalCreation.copy(backlogItems = backlogItems)).futureValue
        val retrieved = repo.getGoal(created.uuid).futureValue
        retrieved should not be empty
        retrieved.value shouldBe created
      }

      it("should return none if a goal is not found") {
        val retrieved = repo.getGoal(UUID.randomUUID).futureValue
        retrieved shouldBe empty
      }

      it("should retrieve a list of goals") {
        val created1 = repo.createGoal(SD.goalCreation).futureValue
        val created2 = repo.createGoal(SD.goalCreation).futureValue
        val retrieved = repo.getGoals.futureValue
        retrieved should not be empty
        retrieved shouldBe Seq(created1, created2)
      }
    }

    describe("deleting") {
      it("should delete a goal") {
        val created = repo.createGoal(SD.goalCreation).futureValue
        val deletion = repo.deleteGoal(created.uuid).futureValue
        val retrieved = repo.getGoal(created.uuid).futureValue
        deletion shouldBe true
        retrieved shouldBe empty
      }
    }
  }
}


















































