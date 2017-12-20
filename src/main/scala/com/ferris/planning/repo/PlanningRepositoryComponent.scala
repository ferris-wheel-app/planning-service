package com.ferris.planning.repo

import java.util.UUID

import com.ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import com.ferris.planning.db.conversions.TableConversions
import com.ferris.planning.db.TablesComponent
import com.ferris.planning.model.Model._
import com.ferris.planning.service.exceptions.Exceptions.MessageNotFoundException

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

    // Endpoints
    def createMessage(creation: CreateMessage): Future[Message] = {
      db.run(createMessageAction(creation)) map (row => row.asMessage)
    }

    def create

    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean] = {
      db.run(updateMessageAction(uuid, update)).map(_ > 0)
    }

    def getMessages: Future[Seq[Message]] = {
      db.run(getMessagesAction.map(_.map(_.asMessage)))
    }

    def getMessage(uuid: UUID): Future[Option[Message]] =  {
      db.run(getMessageAction(uuid).map(_.map(_.asMessage)))
    }

    def deleteMessage(uuid: UUID): Future[Boolean] = {
      db.run(deleteMessageAction(uuid)).map(_ > 0)
    }

    // Actions
    private def createMessageAction(creation: CreateMessage) = {
      val row = T.MessageRow(
        id = 0L,
        uuid = UUID.randomUUID().toString,
        sender = creation.sender,
        content = creation.content
      )
      insertMessageAction(row)
    }

    private def insertMessageAction(row: T.MessageRow) = {
      (T._MessageTable returning T._MessageTable.map(_.id) into ((message, id) => message.copy(id = id))) += row
    }

    private def updateMessageAction(uuid: UUID, update: UpdateMessage) = {
      val query = messageByUuid(uuid).map(message => (message.sender, message.content))
      getMessageAction(uuid).flatMap { maybeMessage =>
        maybeMessage map { old =>
          query.update(update.sender.getOrElse(old.sender), update.content.getOrElse(old.content))
        } getOrElse DBIO.failed(MessageNotFoundException())
      }
    }

    private def getMessagesAction = T._MessageTable.result

    private def getMessageAction(uuid: UUID) = messageByUuid(uuid).result.headOption

    private def deleteMessageAction(uuid: UUID) = messageByUuid(uuid).delete

    // Queries
    private def messageByUuid(uuid: UUID) = T._MessageTable.filter(_.uuid === uuid.toString)
  }
}
