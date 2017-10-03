package ferris.planning.repo

import java.util.UUID

import ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import ferris.planning.db.TablesComponent
import ferris.planning.model.Model.Message

import scala.concurrent.{ExecutionContext, Future}

trait PlanningRepositoryComponent {
  val repo: PlanningRepository

  trait PlanningRepository {
    def createMessage(creation: CreateMessage): Future[Message]
    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean]
    def getMessage(uuid: UUID): Future[Message]
    def deleteMessage(uuid: UUID): Future[Boolean]
  }
}

trait H2PlanningRepositoryComponent extends PlanningRepositoryComponent {
  this: TablesComponent =>

  implicit val repoEc: ExecutionContext

  override val repo = new H2PlanningRepository

  class H2PlanningRepository extends PlanningRepository {
    def createMessage(creation: CreateMessage): Future[Message] = ???
    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean] = ???
    def getMessage(uuid: UUID): Future[Message] = ???
    def deleteMessage(uuid: UUID): Future[Boolean] = ???
  }
}

