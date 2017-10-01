package ferris.planning.service

import ferris.planning.repo.PlanningRepositoryComponent

trait PlanningServiceComponent {
  val planningService: PlanningService

  trait PlanningService {

  }
}

trait DefaultPlanningServiceComponent extends PlanningServiceComponent {
  this: PlanningRepositoryComponent =>

  override val planningService = new DefaultPlanningService

  class DefaultPlanningService extends PlanningService {

  }
}
