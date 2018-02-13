package com.ferris.planning.service

import com.ferris.planning.repo.PlanningRepositoryComponent
import org.scalatest.mockito.MockitoSugar._

trait MockPlanningRepositoryComponent extends PlanningRepositoryComponent {

  override val repo: PlanningRepository = mock[PlanningRepository]
}
