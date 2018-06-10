package com.ferris.planning.scheduler

import java.time.LocalDateTime

import com.ferris.planning.config.PlanningServiceConfig
import org.scalatest.{FunSpec, Matchers}
import com.ferris.planning.model.Model.Statuses._
import com.ferris.planning.sample.SampleData.domain._
import com.ferris.planning.utils.MockTimerComponent
import com.ferris.planning.utils.PlanningImplicits._
import org.mockito.Mockito._

class LifeSchedulerTest extends FunSpec with Matchers {

  type ScheduleContext = DefaultLifeSchedulerComponent with MockTimerComponent
  def newContext(acceptableProgress: Int = 100): ScheduleContext = {
    val config = PlanningServiceConfig(acceptableProgress)
    new DefaultLifeSchedulerComponent with MockTimerComponent {
      override val lifeScheduler: DefaultLifeScheduler = new DefaultLifeScheduler(config)
    }
  }

  describe("a life-scheduler") {
    it("should leave a pyramid unchanged, if a week has not passed since it was last updated") {
      val context = newContext()
      val previousUpdate = LocalDateTime.now
      val nextUpdate = previousUpdate.plusDays(3)
      val pyramid = scheduledPyramid.copy(
        laserDonuts = scheduledLaserDonut :: scheduledLaserDonut :: Nil,
        lastUpdate = Some(previousUpdate)
      )
      when(context.timer.now).thenReturn(nextUpdate.toLong)

      context.lifeScheduler.refreshPyramid(pyramid) shouldBe pyramid
    }

    it("should leave a pyramid unchanged, if there are no scheduled laser-donuts") {
      val context = newContext()
      val pyramid = scheduledPyramid.copy(
        laserDonuts = Nil,
        lastUpdate = None
      )

      context.lifeScheduler.refreshPyramid(pyramid) shouldBe pyramid
    }

    describe("performing a shift") {
      it("should perform a shift, when there are completed laser-donuts") {
        val context = newContext()
        val previousUpdate = LocalDateTime.now
        val nextUpdate = previousUpdate.plusDays(7)

        val d = scheduledLaserDonut
        val topTier = d.copy(id = 1, tier = 1, status = Planned) :: d.copy(id = 2, tier = 1, status = Complete) :: d.copy(id = 3, tier = 1, status = Complete) :: Nil
        val tier2 = d.copy(id = 4, tier = 2, status = Planned) :: d.copy(id = 5, tier = 2, status = Planned) :: d.copy(id = 6, tier = 2, status = Planned) :: Nil
        val tier3 = d.copy(id = 7, tier = 3, status = Planned) :: d.copy(id = 8, tier = 3, status = Planned) :: d.copy(id = 9, tier = 3, status = Planned) :: Nil
        val originalPyramid = scheduledPyramid.copy(
          laserDonuts = topTier ++ tier2 ++ tier3,
          lastUpdate = Some(previousUpdate)
        )
        when(context.timer.now).thenReturn(nextUpdate.toLong)

        val updatedPyramid = context.lifeScheduler.refreshPyramid(originalPyramid)
        val shiftedDonuts = updatedPyramid.laserDonuts
        val newTopTier = shiftedDonuts.filter(_.tier == 1)
        val newTier2 = shiftedDonuts.filter(_.tier == 2)
        val newTier3 = shiftedDonuts.filter(_.tier == 3)

        shiftedDonuts.size shouldBe originalPyramid.laserDonuts.size - 2

        newTopTier.size shouldBe 3
        newTopTier should contain(topTier.head)
        newTopTier.count(donut => tier2.map(_.copy(tier = 1)).contains(donut)) shouldBe 2

        newTier2.size shouldBe 3
        newTier2.count(donut => tier3.map(_.copy(tier = 2)).contains(donut)) shouldBe 2

        newTier3.size shouldBe 1
      }

      it("should perform a shift, when there are no completed laser-donuts, but there has been acceptable progress") {
        val context = newContext(60)
        val previousUpdate = LocalDateTime.now
        val nextUpdate = previousUpdate.plusDays(7)

        val todos = (1 to 6).map(_ => scheduledTodo.copy(status = Complete)) ++
          (1 to 2).map(_ => scheduledTodo.copy(status = InProgress)) ++
          (1 to 2).map(_ => scheduledTodo.copy(status = Planned))
        val portions = (1 to 12).map(_ => scheduledPortion.copy(todos = todos))
        val d = scheduledLaserDonut.copy(status = Planned, portions = portions)

        val topTier = d.copy(id = 1, tier = 1) :: d.copy(id = 2, tier = 1) :: d.copy(id = 3, tier = 1) :: Nil
        val tier2 = d.copy(id = 4, tier = 2) :: d.copy(id = 5, tier = 2) :: d.copy(id = 6, tier = 2) :: Nil
        val tier3 = d.copy(id = 7, tier = 3) :: d.copy(id = 8, tier = 3) :: d.copy(id = 9, tier = 3) :: Nil
        val originalPyramid = scheduledPyramid.copy(
          laserDonuts = topTier ++ tier2 ++ tier3,
          lastUpdate = Some(previousUpdate)
        )
        when(context.timer.now).thenReturn(nextUpdate.toLong)

        val updatedPyramid = context.lifeScheduler.refreshPyramid(originalPyramid)
        val shiftedDonuts = updatedPyramid.laserDonuts
        val newTopTier = shiftedDonuts.filter(_.tier == 1)
        val newTier2 = shiftedDonuts.filter(_.tier == 2)
        val newTier3 = shiftedDonuts.filter(_.tier == 3)

        shiftedDonuts.size shouldBe originalPyramid.laserDonuts.size

        newTopTier.size shouldBe 4
        newTopTier should contain(topTier.head)
        newTopTier.count(donut => tier2.map(_.copy(tier = 1)).contains(donut)) shouldBe 1

        newTier2.size shouldBe 3
        newTier2.count(donut => tier3.map(_.copy(tier = 2)).contains(donut)) shouldBe 1

        newTier3.size shouldBe 2
      }

      it("should not perform a shift, if there are neither completed laser-donuts nor acceptable progress") {
        val context = newContext(60)
        val previousUpdate = LocalDateTime.now
        val nextUpdate = previousUpdate.plusDays(7)

        val todos = (1 to 4).map(_ => scheduledTodo.copy(status = Complete)) ++
          (1 to 3).map(_ => scheduledTodo.copy(status = InProgress)) ++
          (1 to 3).map(_ => scheduledTodo.copy(status = Planned))
        val portions = (1 to 12).map(_ => scheduledPortion.copy(todos = todos))
        val d = scheduledLaserDonut.copy(status = Planned, portions = portions)

        val topTier = d.copy(id = 1, tier = 1) :: d.copy(id = 2, tier = 1) :: d.copy(id = 3, tier = 1) :: Nil
        val tier2 = d.copy(id = 4, tier = 2) :: d.copy(id = 5, tier = 2) :: d.copy(id = 6, tier = 2) :: Nil
        val tier3 = d.copy(id = 7, tier = 3) :: d.copy(id = 8, tier = 3) :: d.copy(id = 9, tier = 3) :: Nil
        val originalPyramid = scheduledPyramid.copy(
          laserDonuts = topTier ++ tier2 ++ tier3,
          lastUpdate = Some(previousUpdate)
        )
        when(context.timer.now).thenReturn(nextUpdate.toLong)

        context.lifeScheduler.refreshPyramid(originalPyramid) shouldBe originalPyramid
      }
    }

    describe("choosing the next laser-donut") {
      it("should choose nothing as the next laser-donut, if there are no laser-donuts") {

      }

      it("should choose a planned laser-donut, if there are some that are present") {

      }

      it("should choose a laser-donut that was tackled the least recently") {

      }

      it("should choose a laser-donut that has the least percentage of its portion tackled") {

      }
    }

    describe("choosing the next portion") {
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
}
