package com.ferris.planning.scheduler

import java.time.LocalDateTime

import com.ferris.planning.config.PlanningServiceConfig
import org.scalatest.{FunSpec, Matchers}
import com.ferris.planning.sample.SampleData.domain._
import com.ferris.planning.utils.MockTimerComponent
import com.ferris.planning.utils.PlanningImplicits._
import org.mockito.Mockito._

class LifeSchedulerTest extends FunSpec with Matchers {

  def newContext(acceptableProgress: Int = 100): DefaultLifeSchedulerComponent with MockTimerComponent = {
    val config = PlanningServiceConfig(acceptableProgress)
    new DefaultLifeSchedulerComponent with MockTimerComponent {
      override val lifeScheduler: DefaultLifeScheduler = new DefaultLifeScheduler(config)
    }
  }

  describe("a life-scheduler") {
    it("should leave a pyramid unchanged, if a week has not passed since it was last updated") {
      val previousUpdate = LocalDateTime.now
      val nextUpdate = previousUpdate.plusDays(3)
      val pyramid = scheduledPyramid.copy(
        laserDonuts = scheduledLaserDonut :: scheduledLaserDonut :: Nil,
        lastUpdate = Some(previousUpdate)
      )
      val context = newContext()
      when(context.timer.now).thenReturn(nextUpdate.toLong)

      context.lifeScheduler.refreshPyramid(pyramid) shouldBe pyramid
    }

    it("should leave a pyramid unchanged, if there are no scheduled laser-donuts") {

    }

    it("should perform a shift, when there are completed laser-donuts") {

    }

    it("should perform a shift, when there are no completed laser-donuts, but there has been acceptable progress") {

    }

    it("should not perform a shift, if there are neither completed laser-donuts nor acceptable progress") {

    }

    it("should choose nothing as the next laser-donut, if there are no laser-donuts") {

    }

    it("should choose a planned laser-donut, if there are some that are present") {

    }

    it("should choose a laser-donut that was tackled the least recently") {

    }

    it("should choose a laser-donut that has the least percentage of its portion tackled") {

    }

    it("should leave the current portion unchanged, if a day has not passed since it was last updated") {

    }

    it("should choose nothing as the next portion, if there are no portions") {

    }

    it("should choose nothing as the next portion, if all portions are complete") {

    }

    it("should choose the first portion, if all the portions are planned") {

    }

    it("should choose the first portion that is in progress, if there are some that are present") {

    }
  }
}
