package com.ferris.planning.contract.validation

import java.util.UUID

import com.ferris.microservice.exceptions.ApiExceptions.{InvalidFieldException, InvalidFieldPayload}
import com.ferris.planning.contract.resource.Resources.In.{PyramidOfImportanceUpsert, TierUpsert}
import org.scalatest.{Assertions, FunSpec, Matchers}
import com.ferris.planning.contract.sample.{SampleData => SD}

class InputValidatorsTest extends FunSpec with Matchers with Assertions {

  describe("validating a backlog-item creation") {
    it("should allow the creation of a valid object") {
      SD.backlogItemCreation.copy(
        `type` = SD.backlogItemCreation.`type`
      )
    }

    it("should throw an exception if the type is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.backlogItemCreation.copy(
          `type` = "itch"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Type", Some(InvalidFieldPayload("type")))
      caught.message shouldBe expected.message
    }
  }

  describe("validating a goal creation") {
    it("should allow the creation of a valid object") {
      SD.goalCreation.copy(
        backlogItems = UUID.randomUUID :: UUID.randomUUID :: Nil,
        graduation = SD.goalCreation.graduation,
        status = SD.goalCreation.status
      )
    }

    it("should throw an exception if there are more than 10 backlog-items") {
      val caught = intercept[InvalidFieldException] {
        SD.goalCreation.copy(
          backlogItems = (1 to 11).map(_ => UUID.randomUUID)
        )
      }
      val expected = InvalidFieldException("InvalidField", "Backlog Items must be up to a list of 10 or fewer", Some(InvalidFieldPayload("backlogItems")))
      caught shouldBe expected
    }

    it("should throw an exception if there are duplicated backlog-items") {
      val duplicatedId = UUID.randomUUID
      val caught = intercept[InvalidFieldException] {
        SD.goalCreation.copy(
          backlogItems = duplicatedId :: duplicatedId :: UUID.randomUUID :: Nil
        )
      }
      val expected = InvalidFieldException("InvalidField", "Backlog Items cannot contain duplicate entries", Some(InvalidFieldPayload("backlogItems")))
      caught shouldBe expected
    }

    it("should throw an exception if the graduation is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.goalCreation.copy(
          graduation = "masters"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Graduation", Some(InvalidFieldPayload("graduation")))
      caught shouldBe expected
    }

    it("should throw an exception if the status is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.goalCreation.copy(
          status = "dead"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Status", Some(InvalidFieldPayload("status")))
      caught shouldBe expected
    }
  }

  describe("validating a thread creation") {
    it("should allow the creation of a valid object") {
      SD.threadCreation.copy(
        status = SD.threadCreation.status
      )
    }

    it("should throw an exception if the status is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.threadCreation.copy(
          status = "dead"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Status", Some(InvalidFieldPayload("status")))
      caught shouldBe expected
    }
  }

  describe("validating a weave creation") {
    it("should allow the creation of a valid object") {
      SD.weaveCreation.copy(
        `type` = SD.weaveCreation.`type`,
        status = SD.weaveCreation.status
      )
    }

    it("should throw an exception if the type is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.weaveCreation.copy(
          `type` = "straight"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Type", Some(InvalidFieldPayload("type")))
      caught shouldBe expected
    }

    it("should throw an exception if the status is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.weaveCreation.copy(
          status = "dead"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Status", Some(InvalidFieldPayload("status")))
      caught shouldBe expected
    }
  }

  describe("validating a laser-donut creation") {
    it("should allow the creation of a valid object") {
      SD.laserDonutCreation.copy(
        `type` = SD.laserDonutCreation.`type`,
        status = SD.laserDonutCreation.status
      )
    }

    it("should throw an exception if the type is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.laserDonutCreation.copy(
          `type` = "round"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Type", Some(InvalidFieldPayload("type")))
      caught shouldBe expected
    }

    it("should throw an exception if the status is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.laserDonutCreation.copy(
          status = "dead"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Status", Some(InvalidFieldPayload("status")))
      caught shouldBe expected
    }
  }

  describe("validating a portion creation") {
    it("should allow the creation of a valid object") {
      SD.portionCreation.copy(
        status = SD.portionCreation.status
      )
    }

    it("should throw an exception if the status is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.laserDonutCreation.copy(
          status = "dead"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Status", Some(InvalidFieldPayload("status")))
      caught shouldBe expected
    }
  }

  describe("validating a todo creation") {
    it("should allow the creation of a valid object") {
      SD.todoCreation.copy(
        status = SD.todoCreation.status
      )
    }

    it("should throw an exception if the status is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.todoCreation.copy(
          status = "dead"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Status", Some(InvalidFieldPayload("status")))
      caught shouldBe expected
    }
  }

  describe("validating a hobby creation") {
    it("should allow the creation of a valid object") {
      SD.hobbyCreation.copy(
        frequency = SD.hobbyCreation.frequency,
        `type` = SD.hobbyCreation.`type`,
        status = SD.hobbyCreation.status
      )
    }

    it("should throw an exception if the frequency is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.hobbyCreation.copy(
          frequency = "every weekend"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Frequency", Some(InvalidFieldPayload("frequency")))
      caught shouldBe expected
    }

    it("should throw an exception if the type is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.hobbyCreation.copy(
          `type` = "anti-social"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Type", Some(InvalidFieldPayload("type")))
      caught shouldBe expected
    }

    it("should throw an exception if the status is invalid") {
      val caught = intercept[InvalidFieldException] {
        SD.hobbyCreation.copy(
          status = "dead"
        )
      }
      val expected = InvalidFieldException("InvalidField", "Invalid Status", Some(InvalidFieldPayload("status")))
      caught shouldBe expected
    }
  }

  describe("validating a tier creation") {
    it("should allow the creation of a valid object") {
      TierUpsert(laserDonuts = UUID.randomUUID :: UUID.randomUUID :: Nil)
    }

    it("should throw an exception if there are more than 5 laser-donuts") {
      val caught = intercept[InvalidFieldException] {
        TierUpsert(laserDonuts = (1 to 6).map(_ => UUID.randomUUID))
      }
      val expected = InvalidFieldException("InvalidField", "Laser Donuts must be up to a list of 5 or fewer", Some(InvalidFieldPayload("laserDonuts")))
      caught shouldBe expected
    }

    it("should throw an exception if there are duplicated laser-donuts") {
      val duplicatedId = UUID.randomUUID
      val caught = intercept[InvalidFieldException] {
        TierUpsert(laserDonuts = duplicatedId :: duplicatedId :: UUID.randomUUID :: Nil)
      }
      val expected = InvalidFieldException("InvalidField", "Laser Donuts cannot contain duplicate entries", Some(InvalidFieldPayload("laserDonuts")))
      caught shouldBe expected
    }
  }

  describe("validating a pyramid creation") {
    it("should allow the creation of a valid object") {
      PyramidOfImportanceUpsert(
        tiers = List(
          TierUpsert(laserDonuts = (1 to 5).map(_ => UUID.randomUUID)),
          TierUpsert(laserDonuts = (1 to 5).map(_ => UUID.randomUUID)),
          TierUpsert(laserDonuts = (1 to 5).map(_ => UUID.randomUUID))
        )
      )
    }

    it("should throw an exception if there are less than 5 laser-donuts on the top tier") {
      val caught = intercept[InvalidFieldException] {
        PyramidOfImportanceUpsert(
          tiers = List(
            TierUpsert(laserDonuts = (1 to 2).map(_ => UUID.randomUUID)),
            TierUpsert(laserDonuts = (1 to 5).map(_ => UUID.randomUUID)),
            TierUpsert(laserDonuts = (1 to 5).map(_ => UUID.randomUUID))
          )
        )
      }
      val expected = InvalidFieldException("InvalidField", "There have to be a minimum of 5 laser-donuts on the top tier of the pyramid", Some(InvalidFieldPayload("tiers")))
      caught shouldBe expected
    }
  }
}




































