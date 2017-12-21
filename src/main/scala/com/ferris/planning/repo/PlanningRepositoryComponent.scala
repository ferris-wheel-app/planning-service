package com.ferris.planning.repo

import java.util.UUID

import com.ferris.planning.command.Commands.{CreateBacklogItem, CreateMessage, UpdateBacklogItem, UpdateMessage}
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
  import tableConversions.tables._
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

    def createBacklogItem(creation: CreateBacklogItem): Future[BacklogItem] = {
      db.run(createBacklogItemAction(creation))
    }

    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean] = {
      db.run(updateMessageAction(uuid, update)).map(_ > 0)
    }

    def getMessages: Future[Seq[Message]] = {
      db.run(MessageTable.result.map(_.map(_.asMessage)))
    }

    def getMessage(uuid: UUID): Future[Option[Message]] =  {
      db.run(getMessageAction(uuid).map(_.map(_.asMessage)))
    }

    def deleteMessage(uuid: UUID): Future[Boolean] = {
      db.run(deleteMessageAction(uuid)).map(_ > 0)
    }

    // Actions
    private def createMessageAction(creation: CreateMessage) = {
      val row = MessageRow(
        id = 0L,
        uuid = UUID.randomUUID().toString,
        sender = creation.sender,
        content = creation.content
      )
      (MessageTable returning MessageTable.map(_.id) into ((message, id) => message.copy(id = id))) += row
    }

    private def createBacklogItemAction(creation: CreateBacklogItem) = {
      val row = BacklogItemRow(
        id = 0L,
        uuid = UUID.randomUUID().toString,
        summary = creation.summary,
        description = creation.description,
        `type` = creation.`type`.dbValue
      )
      (BacklogItemTable returning BacklogItemTable.map(_.id) into ((item, id) => item.copy(id = id))) += row
    }

    private def updateMessageAction(uuid: UUID, update: UpdateMessage) = {
      val query = messageByUuid(uuid).map(message => (message.sender, message.content))
      getMessageAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.sender.getOrElse(old.sender), update.content.getOrElse(old.content))
        } getOrElse DBIO.failed(MessageNotFoundException())
      }
    }

    private def updateBacklogItemAction(uuid: UUID, update: UpdateBacklogItem) = {
      val query = backlogItemByUuid(uuid).map(item => (item.summary, item.description, item.`type`))
      getBacklogItemAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.summary.getOrElse(old.summary), update.description.getOrElse(old.description), update.`type`.map(_.dbValue).getOrElse(old.`type`))
        } getOrElse DBIO.failed(MessageNotFoundException())
      }
    }

    private def getMessageAction(uuid: UUID) = messageByUuid(uuid).result.headOption

    private def getBacklogItemAction(uuid: UUID) = backlogItemByUuid(uuid).result.headOption

    private def deleteMessageAction(uuid: UUID) = messageByUuid(uuid).delete

    private def deleteBacklogItemAction(uuid: UUID) = backlogItemByUuid(uuid).delete

    // Queries
    private def messageByUuid(uuid: UUID) = MessageTable.filter(_.uuid === uuid.toString)

    private def backlogItemByUuid(uuid: UUID) = BacklogItemTable.filter(_.uuid === uuid.toString)
  }
}
