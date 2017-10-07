package ferris.planning.service

import java.util.UUID

import ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import ferris.planning.model.Model.Message
import ferris.planning.repo.PlanningRepositoryComponent
import ferris.planning.rest.Resources.Out.MessageView
import ferris.planning.rest.conversions.ModelToView._

import scala.concurrent.{ExecutionContext, Future}

trait PlanningServiceComponent {
  val planningService: PlanningService

  trait PlanningService {
    def createMessage(creation: CreateMessage)(implicit ex: ExecutionContext): Future[MessageView]
    def updateMessage(uuid: UUID, update: UpdateMessage)(implicit ex: ExecutionContext): Future[Boolean]
    def getMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[MessageView]]
    def deleteMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
  }
}

trait DefaultPlanningServiceComponent extends PlanningServiceComponent {
  this: PlanningRepositoryComponent =>

  override val planningService = new DefaultPlanningService

  class DefaultPlanningService extends PlanningService {
    def createMessage(creation: CreateMessage)(implicit ex: ExecutionContext): Future[MessageView] = {
      repo.createMessage(creation).map(_.toView)
    }

    def updateMessage(uuid: UUID, update: UpdateMessage)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.updateMessage(uuid, update)
    }

    def getMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[MessageView]] = {
      repo.getMessage(uuid).map(_.map(_.toView))
    }

    def deleteMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteMessage(uuid)
    }
  }
}
