package ferris.planning.repo

import ferris.planning.db.TablesComponent

trait PlanningRepositoryComponent {
  val repo: Repository

  trait Repository {

  }
}

trait H2PlanningRepositoryComponent extends PlanningRepositoryComponent {
  this: TablesComponent =>

  override val repo = new H2Repository

  class H2Repository extends Repository {

  }
}

