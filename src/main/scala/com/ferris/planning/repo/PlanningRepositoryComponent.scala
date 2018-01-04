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

    // Create endpoints
    def createMessage(creation: CreateMessage): Future[Message] = {
      db.run(createMessageAction(creation)) map (row => row.asMessage)
    }

    def createBacklogItem(creation: CreateBacklogItem): Future[BacklogItem] = {
      // db.run(createBacklogItemAction(creation))
      ???
    }

    // Update endpoints
    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean] = {
      db.run(updateMessageAction(uuid, update)).map(_ > 0)
    }

    // Get endpoints
    def getMessages: Future[Seq[Message]] = {
      db.run(MessageTable.result.map(_.map(_.asMessage)))
    }

    def getMessage(uuid: UUID): Future[Option[Message]] =  {
      db.run(getMessageAction(uuid).map(_.map(_.asMessage)))
    }

    // Delete endpoints
    def deleteMessage(uuid: UUID): Future[Boolean] = {
      db.run(deleteMessageAction(uuid)).map(_ > 0)
    }

    // Create actions
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

    // Update actions
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

    // Get actions
    private def getMessageAction(uuid: UUID) = messageByUuid(uuid).result.headOption

    private def getBacklogItemAction(uuid: UUID) = backlogItemByUuid(uuid).result.headOption

    private def getEpochAction(uuid: UUID) = epochByUuid(uuid).result.headOption

    private def getYearAction(uuid: UUID) = yearByUuid(uuid).result.headOption

    private def getThemeAction(uuid: UUID) = themeByUuid(uuid).result.headOption

    private def getGoalAction(uuid: UUID) = goalByUuid(uuid).result.headOption

    private def getThreadAction(uuid: UUID) = threadByUuid(uuid).result.headOption

    private def getWeaveAction(uuid: UUID) = weaveByUuid(uuid).result.headOption

    private def getLaserDonutAction(uuid: UUID) = laserDonutByUuid(uuid).result.headOption

    private def getPortionAction(uuid: UUID) = portionByUuid(uuid).result.headOption

    private def getTodoAction(uuid: UUID) = todoByUuid(uuid).result.headOption

    private def getHobbyAction(uuid: UUID) = hobbyByUuid(uuid).result.headOption

    // Delete actions
    private def deleteMessageAction(uuid: UUID) = messageByUuid(uuid).delete

    private def deleteBacklogItemAction(uuid: UUID) = backlogItemByUuid(uuid).delete

    private def deleteEpochAction(uuid: UUID) = epochByUuid(uuid).delete

    private def deleteYearAction(uuid: UUID) = yearByUuid(uuid).delete

    private def deleteThemeAction(uuid: UUID) = themeByUuid(uuid).delete

    private def deleteGoalAction(uuid: UUID) = goalByUuid(uuid).delete

    private def deleteThreadAction(uuid: UUID) = threadByUuid(uuid).delete

    private def deleteWeaveAction(uuid: UUID) = weaveByUuid(uuid).delete

    private def deleteLaserDonutAction(uuid: UUID) = laserDonutByUuid(uuid).delete

    private def deletePortionAction(uuid: UUID) = portionByUuid(uuid).delete

    private def deleteTodoAction(uuid: UUID) = todoByUuid(uuid).delete

    private def deleteHobbyAction(uuid: UUID) = hobbyByUuid(uuid).delete

    // Queries
    private def messageByUuid(uuid: UUID) = MessageTable.filter(_.uuid === uuid.toString)

    private def backlogItemByUuid(uuid: UUID) = BacklogItemTable.filter(_.uuid === uuid.toString)

    private def epochByUuid(uuid: UUID) = EpochTable.filter(_.uuid === uuid.toString)

    private def yearByUuid(uuid: UUID) = YearTable.filter(_.uuid === uuid.toString)

    private def themeByUuid(uuid: UUID) = ThemeTable.filter(_.uuid === uuid.toString)

    private def goalByUuid(uuid: UUID) = GoalTable.filter(_.uuid === uuid.toString)

    private def threadByUuid(uuid: UUID) = ThreadTable.filter(_.uuid === uuid.toString)

    private def weaveByUuid(uuid: UUID) = WeaveTable.filter(_.uuid === uuid.toString)

    private def laserDonutByUuid(uuid: UUID) = LaserDonutTable.filter(_.uuid === uuid.toString)

    private def portionByUuid(uuid: UUID) = PortionTable.filter(_.uuid === uuid.toString)

    private def todoByUuid(uuid: UUID) = TodoTable.filter(_.uuid === uuid.toString)

    private def hobbyByUuid(uuid: UUID) = HobbyTable.filter(_.uuid === uuid.toString)
  }
}




















































































































































