package com.ferris.planning.scheduler

import org.scalatest.mockito.MockitoSugar.mock

trait MockLifeSchedulerComponent extends LifeSchedulerComponent {

  override val lifeScheduler: LifeScheduler = mock[LifeScheduler]
}
