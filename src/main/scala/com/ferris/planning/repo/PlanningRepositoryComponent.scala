package com.ferris.planning.repo

import java.util.UUID

import com.ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import com.ferris.planning.db.conversions.TableConversions
import com.ferris.planning.db.TablesComponent
import com.ferris.planning.model.Model._

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

trait MySQLPlanningRepositoryComponent extends PlanningRepositoryComponent {
  this: TablesComponent =>

  lazy val tableConversions = new TableConversions(tables)
  import tableConversions.{ tables => T }
  import tableConversions.tables.profile.api._
  import tableConversions._

  implicit val repoEc: ExecutionContext
  override val repo = new MySQLPlanningRepository
  val db: tables.profile.api.Database

  class MySQLPlanningRepository extends PlanningRepository {

    def createMessage(creation: CreateMessage): Future[Message] = {
      val row = T.MessageRow(
        id = 0L,
        uuid = UUID.randomUUID().toString,
        sender = creation.sender,
        content = creation.content
      )
      val action = (T._MessageTable returning T._MessageTable.map(_.id) into ((message, id) => message.copy(id = id))) += row
      db.run(action) map (row => row.asMessage)
    }

    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean] = {
      val query = messageByUuid(uuid).map(message => (message.sender, message.content))
      val action = query.update(update.sender, update.content)
      db.run(action).map(_ > 0)
    }

    def getMessages: Future[Seq[Message]] =  {
      val action = T._MessageTable.result.map(_.map(_.asMessage))
      db.run(action)
    }

    def getMessage(uuid: UUID): Future[Option[Message]] =  {
      val action = messageByUuid(uuid).result.headOption.map(_.map(_.asMessage))
      db.run(action)
    }

    def deleteMessage(uuid: UUID): Future[Boolean] = {
      val action = messageByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    private def messageByUuid(uuid: UUID) = T._MessageTable.filter(_.uuid === uuid.toString)
  }
}
