package ferris.planning.repo

import ferris.planning.db.TablesComponent

import scala.concurrent.ExecutionContext

trait PlanningRepositoryComponent {
  val repo: PlanningRepository

  trait PlanningRepository {
    def createMessage(message: Message)
  }
}

trait H2PlanningRepositoryComponent extends PlanningRepositoryComponent {
  this: TablesComponent =>

  implicit val repoEc: ExecutionContext

  override val repo = new H2PlanningRepository

  class H2PlanningRepository extends PlanningRepository {

  }
}

