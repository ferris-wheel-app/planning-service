package com.ferris.planning.repo

import java.util.UUID

import com.ferris.planning.command.Commands._
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
    def createMessage(creation: CreateMessage): Future[Message] = db.run(createMessageAction(creation)) map (row => row.asMessage)

    def createBacklogItem(creation: CreateBacklogItem): Future[BacklogItem] = {
      // db.run(createBacklogItemAction(creation))
      ???
    }

    def createEpoch(creation: CreateEpoch): Future[Epoch] = ???

    def createYear(creation: CreateYear): Future[Year] = ???

    def createTheme(creation: CreateTheme): Future[Theme] = ???

    def createGoal(creation: CreateGoal): Future[Goal] = ???

    def createThread(creation: CreateThread): Future[Thread] = ???

    def createWeave(creation: CreateWeave): Future[Weave] = ???

    def createLaserDonut(creation: CreateLaserDonut): Future[LaserDonut] = ???

    def createPortion(creation: CreatePortion): Future[Portion] = ???

    def createTodo(creation: CreateTodo): Future[Todo] = ???

    def createHobby(creation: CreateHobby): Future[Hobby] = ???

    // Update endpoints
    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean] = db.run(updateMessageAction(uuid, update)).map(_ > 0)

    def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem): Future[Boolean] = ???

    def updateEpoch(uuid: UUID, update: UpdateEpoch): Future[Boolean] = ???

    def updateYear(uuid: UUID, update: UpdateYear): Future[Boolean] = ???

    def updateTheme(uuid: UUID, update: UpdateTheme): Future[Boolean] = ???

    def updateGoal(uuid: UUID, update: UpdateGoal): Future[Boolean] = ???

    def updateThread(uuid: UUID, update: UpdateThread): Future[Boolean] = ???

    def updateWeave(uuid: UUID, update: UpdateWeave): Future[Boolean] = ???

    def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut): Future[Boolean] = ???

    def updatePortion(uuid: UUID, update: UpdatePortion): Future[Boolean] = ???

    def updateTodo(uuid: UUID, update: UpdateTodo): Future[Boolean] = ???

    def updateHobby(uuid: UUID, update: UpdateHobby): Future[Boolean] = ???

    // Get endpoints
    def getMessages: Future[Seq[Message]] = db.run(MessageTable.result.map(_.map(_.asMessage)))

    def getMessage(uuid: UUID): Future[Option[Message]] = db.run(getMessageAction(uuid).map(_.map(_.asMessage)))

    def getBacklogItems: Future[Seq[BacklogItem]] = db.run(BacklogItemTable.result.map(_.map(_.asBacklogItem)))

    def getBacklogItem(uuid: UUID): Future[Option[BacklogItem]] = db.run(getBacklogItemAction(uuid).map(_.map(_.asBacklogItem)))

    def getEpochs: Future[Seq[Epoch]] = db.run(EpochTable.result.map(_.map(_.asEpoch)))

    def getEpoch(uuid: UUID): Future[Option[Epoch]] = db.run(getEpochAction(uuid).map(_.map(_.asEpoch)))

    def getYears: Future[Seq[Year]] = db.run(YearTable.result.map(_.map(_.asYear)))

    def getYear(uuid: UUID): Future[Option[Year]] = db.run(getYearAction(uuid).map(_.map(_.asYear)))

    def getThemes: Future[Seq[Theme]] = db.run(ThemeTable.result.map(_.map(_.asTheme)))

    def getTheme(uuid: UUID): Future[Option[Theme]] = db.run(getThemeAction(uuid).map(_.map(_.asTheme)))

    def getGoals: Future[Seq[Message]] = {
      ???
    }

    def getGoal(uuid: UUID): Future[Option[Message]] =  {
      ???
    }

    def getThreads: Future[Seq[Thread]] = db.run(ThreadTable.result.map(_.map(_.asThread)))

    def getThread(uuid: UUID): Future[Option[Thread]] = db.run(getThreadAction(uuid).map(_.map(_.asThread)))

    def getWeaves: Future[Seq[Weave]] = db.run(WeaveTable.result.map(_.map(_.asWeave)))

    def getWeave(uuid: UUID): Future[Option[Weave]] = db.run(getWeaveAction(uuid).map(_.map(_.asWeave)))

    def getLaserDonuts: Future[Seq[LaserDonut]] = db.run(LaserDonutTable.result.map(_.map(_.asLaserDonut)))

    def getLaserDonut(uuid: UUID): Future[Option[LaserDonut]] = db.run(getLaserDonutAction(uuid).map(_.map(_.asLaserDonut)))

    def getPortions: Future[Seq[Portion]] = db.run(PortionTable.result.map(_.map(_.asPortion)))

    def getPortion(uuid: UUID): Future[Option[Portion]] = db.run(getPortionAction(uuid).map(_.map(_.asPortion)))

    def getTodos: Future[Seq[Todo]] = db.run(TodoTable.result.map(_.map(_.asTodo)))

    def getTodo(uuid: UUID): Future[Option[Todo]] = db.run(getTodoAction(uuid).map(_.map(_.asTodo)))

    def getHobbies: Future[Seq[Hobby]] = db.run(HobbyTable.result.map(_.map(_.asHobby)))

    def getHobby(uuid: UUID): Future[Option[Hobby]] = db.run(getHobbyAction(uuid).map(_.map(_.asHobby)))

    // Delete endpoints
    def deleteMessage(uuid: UUID): Future[Boolean] = db.run(deleteMessageAction(uuid)).map(_ > 0)

    def deleteBacklogItem(uuid: UUID): Future[Boolean] = db.run(deleteBacklogItemAction(uuid)).map(_ > 0)

    def deleteEpoch(uuid: UUID): Future[Boolean] = db.run(deleteEpochAction(uuid)).map(_ > 0)

    def deleteYear(uuid: UUID): Future[Boolean] = db.run(deleteYearAction(uuid)).map(_ > 0)

    def deleteTheme(uuid: UUID): Future[Boolean] = db.run(deleteMessageAction(uuid)).map(_ > 0)

    def deleteGoal(uuid: UUID): Future[Boolean] = db.run(deleteGoalAction(uuid)).map(_ > 0)

    def deleteThread(uuid: UUID): Future[Boolean] = db.run(deleteThreadAction(uuid)).map(_ > 0)

    def deleteWeave(uuid: UUID): Future[Boolean] = db.run(deleteWeaveAction(uuid)).map(_ > 0)

    def deleteLaserDonut(uuid: UUID): Future[Boolean] = db.run(deleteLaserDonutAction(uuid)).map(_ > 0)

    def deletePortion(uuid: UUID): Future[Boolean] = db.run(deletePortionAction(uuid)).map(_ > 0)

    def deleteTodo(uuid: UUID): Future[Boolean] = db.run(deleteTodoAction(uuid)).map(_ > 0)

    def deleteHobby(uuid: UUID): Future[Boolean] = db.run(deleteHobbyAction(uuid)).map(_ > 0)

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

    private def createEpochAction(creation: CreateEpoch) = {
      ???
    }

    private def createYearAction(creation: CreateYear) = {
      ???
    }

    private def createThemeAction(creation: CreateTheme) = {
      ???
    }

    private def createGoalAction(creation: CreateGoal) = {
      ???
    }

    private def createThreadAction(creation: CreateThread) = {
      ???
    }

    private def createWeaveAction(creation: CreateWeave) = {
      ???
    }

    private def createLaserDonutAction(creation: CreateLaserDonut) = {
      ???
    }

    private def createPortionAction(creation: CreatePortion) = {
      ???
    }

    private def createTodoAction(creation: Todo) = {
      ???
    }

    private def createHobbyAction(creation: Hobby) = {
      ???
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

    private def updateEpochAction(uuid: UUID, update: UpdateEpoch) = {
      ???
    }

    private def updateYearAction(uuid: UUID, update: UpdateYear) = {
      ???
    }

    private def updateThemeAction(uuid: UUID, update: UpdateTheme) = {
      ???
    }

    private def updateGoalAction(uuid: UUID, update: UpdateGoal) = {
      ???
    }

    private def updateThreadAction(uuid: UUID, update: UpdateThread) = {
      ???
    }

    private def updateWeaveAction(uuid: UUID, update: UpdateWeave) = {
      ???
    }

    private def updateLaserDonutAction(uuid: UUID, update: UpdateLaserDonut) = {
      ???
    }

    private def updatePortionAction(uuid: UUID, update: UpdatePortion) = {
      ???
    }

    private def updateTodoAction(uuid: UUID, update: UpdateTodo) = {
      ???
    }

    private def updateHobbyAction(uuid: UUID, update: UpdateHobby) = {
      ???
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




















































































































































