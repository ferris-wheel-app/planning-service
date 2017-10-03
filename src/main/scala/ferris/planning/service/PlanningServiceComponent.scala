package ferris.planning.service

import java.util.UUID

import ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import ferris.planning.model.Model.Message
import ferris.planning.repo.PlanningRepositoryComponent

import scala.concurrent.{ExecutionContext, Future}

trait PlanningServiceComponent {
  val planningService: PlanningService

  trait PlanningService {
    def createMessage(creation: CreateMessage)(implicit ex: ExecutionContext): Future[Message]
    def updateMessage(uuid: UUID, update: UpdateMessage)(implicit ex: ExecutionContext): Future[Boolean]
    def getMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Message]
    def deleteMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
  }
}

trait DefaultPlanningServiceComponent extends PlanningServiceComponent {
  this: PlanningRepositoryComponent =>

  override val planningService = new DefaultPlanningService

  class DefaultPlanningService extends PlanningService {
    def createMessage(creation: CreateMessage)(implicit ex: ExecutionContext): Future[Message] = {
      ???
    }

    def updateMessage(uuid: UUID, update: UpdateMessage)(implicit ex: ExecutionContext): Future[Boolean] = {
      ???
    }

    def getMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Message] = {
      ???
    }

    def deleteMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      ???
    }
  }
}
