package ferris.planning.service

import ferris.planning.repo.PlanningRepositoryComponent

trait PlanningServiceComponent {
  val service: Service

  trait Service {

  }
}

trait DefaultPlanningServiceComponent extends PlanningServiceComponent {
  this: PlanningRepositoryComponent =>

  class DefaultService extends Service {

  }
}
