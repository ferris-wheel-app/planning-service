package ferris.planning.repo

import java.util.UUID

import ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import ferris.planning.db.conversions.TableConversions
import ferris.planning.db.TablesComponent
import ferris.planning.model.Model.Message

import scala.concurrent.{ExecutionContext, Future}

trait PlanningRepositoryComponent {
  val repo: PlanningRepository

  trait PlanningRepository {
    def createMessage(creation: CreateMessage): Future[Message]
    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean]
    def getMessages: Future[Seq[Message]]
    def getMessage(uuid: UUID): Future[Option[Message]]
    def deleteMessage(uuid: UUID): Future[Boolean]
  }
}

trait H2PlanningRepositoryComponent extends PlanningRepositoryComponent {
  this: TablesComponent =>

  lazy val tableConversions = new TableConversions(tables)
  import tableConversions.tables._
  import tableConversions.tables.profile.api._
  import tableConversions._

  implicit val repoEc: ExecutionContext
  override val repo = new H2PlanningRepository
  val db: tables.profile.api.Database


  class H2PlanningRepository extends PlanningRepository {

    def createMessage(creation: CreateMessage): Future[Message] = {
      val row = MessageRow(
        uuid = UUID.randomUUID().toString,
        sender = creation.sender,
        content = creation.content
      )
      val action = (messages returning messages.map(_.id) into ((message, id) => message.copy(id = id))) += row
      db.run(action) map (row => row.asMessage)
    }

    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean] = {
      val query = messageQ(uuid).map(message => (message.sender, message.content))
      val action = query.update(update.sender, update.content)
      db.run(action).map(_ > 0)
    }

    def getMessages: Future[Seq[Message]] =  {
      val action = messages.result.map(_.map(_.asMessage))
      db.run(action)
    }

    def getMessage(uuid: UUID): Future[Option[Message]] =  {
      val action = messageQ(uuid).result.headOption.map(_.map(_.asMessage))
      db.run(action)
    }

    def deleteMessage(uuid: UUID): Future[Boolean] = {
      val action = messageQ(uuid).delete
      db.run(action).map(_ > 0)
    }

    private def messageQ(uuid: UUID) = messages.filter(_.uuid === uuid.toString)
  }
}

