package com.ferris.planning.repo

import java.util.UUID

import com.ferris.planning.command.Commands._
import com.ferris.planning.db.conversions.TableConversions
import com.ferris.planning.db.TablesComponent
import com.ferris.planning.model.Model._
import com.ferris.planning.service.exceptions.Exceptions._

import scala.concurrent.{ExecutionContext, Future}

trait PlanningRepositoryComponent {

  val repo: PlanningRepository

  trait PlanningRepository {
    def createMessage(creation: CreateMessage): Future[Message]
    def createBacklogItem(creation: CreateBacklogItem): Future[BacklogItem]
    def createEpoch(creation: CreateEpoch): Future[Epoch]
    def createYear(creation: CreateYear): Future[Year]
    def createTheme(creation: CreateTheme): Future[Theme]
    def createGoal(creation: CreateGoal): Future[Goal]
    def createThread(creation: CreateThread): Future[Thread]
    def createWeave(creation: CreateWeave): Future[Weave]
    def createLaserDonut(creation: CreateLaserDonut): Future[LaserDonut]
    def createPortion(creation: CreatePortion): Future[Portion]
    def createTodo(creation: CreateTodo): Future[Todo]
    def createHobby(creation: CreateHobby): Future[Hobby]

    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean]
    def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem): Future[Boolean]
    def updateEpoch(uuid: UUID, update: UpdateEpoch): Future[Boolean]
    def updateYear(uuid: UUID, update: UpdateYear): Future[Boolean]
    def updateTheme(uuid: UUID, update: UpdateTheme): Future[Boolean]
    def updateGoal(uuid: UUID, update: UpdateGoal): Future[Boolean]
    def updateThread(uuid: UUID, update: UpdateThread): Future[Boolean]
    def updateWeave(uuid: UUID, update: UpdateWeave): Future[Boolean]
    def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut): Future[Boolean]
    def updatePortion(uuid: UUID, update: UpdatePortion): Future[Boolean]
    def updateTodo(uuid: UUID, update: UpdateTodo): Future[Boolean]
    def updateHobby(uuid: UUID, update: UpdateHobby): Future[Boolean]

    def getMessages: Future[Seq[Message]]
    def getBacklogItems: Future[Seq[BacklogItem]]
    def getEpochs: Future[Seq[Epoch]]
    def getYears: Future[Seq[Year]]
    def getThemes: Future[Seq[Theme]]
    def getGoals: Future[Seq[Goal]]
    def getThreads: Future[Seq[Thread]]
    def getWeaves: Future[Seq[Weave]]
    def getLaserDonuts: Future[Seq[LaserDonut]]
    def getPortions: Future[Seq[Portion]]
    def getTodos: Future[Seq[Todo]]
    def getHobbies: Future[Seq[Hobby]]

    def getMessage(uuid: UUID): Future[Option[Message]]
    def getBacklogItem(uuid: UUID): Future[Option[BacklogItem]]
    def getEpoch(uuid: UUID): Future[Option[Epoch]]
    def getYear(uuid: UUID): Future[Option[Year]]
    def getTheme(uuid: UUID): Future[Option[Theme]]
    def getGoal(uuid: UUID): Future[Option[Goal]]
    def getThread(uuid: UUID): Future[Option[Thread]]
    def getWeave(uuid: UUID): Future[Option[Weave]]
    def getLaserDonut(uuid: UUID): Future[Option[LaserDonut]]
    def getPortion(uuid: UUID): Future[Option[Portion]]
    def getTodo(uuid: UUID): Future[Option[Todo]]
    def getHobby(uuid: UUID): Future[Option[Hobby]]

    def deleteMessage(uuid: UUID): Future[Boolean]
    def deleteBacklogItem(uuid: UUID): Future[Boolean]
    def deleteEpoch(uuid: UUID): Future[Boolean]
    def deleteYear(uuid: UUID): Future[Boolean]
    def deleteTheme(uuid: UUID): Future[Boolean]
    def deleteGoal(uuid: UUID): Future[Boolean]
    def deleteThread(uuid: UUID): Future[Boolean]
    def deleteWeave(uuid: UUID): Future[Boolean]
    def deleteLaserDonut(uuid: UUID): Future[Boolean]
    def deletePortion(uuid: UUID): Future[Boolean]
    def deleteTodo(uuid: UUID): Future[Boolean]
    def deleteHobby(uuid: UUID): Future[Boolean]
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

    def createBacklogItem(creation: CreateBacklogItem): Future[BacklogItem] = db.run(createBacklogItemAction(creation)) map (row => row.asBacklogItem)

    def createEpoch(creation: CreateEpoch): Future[Epoch] = db.run(createEpochAction(creation)) map (row => row.asEpoch)

    def createYear(creation: CreateYear): Future[Year] = db.run(createYearAction(creation)) map (row => row.asYear)

    def createTheme(creation: CreateTheme): Future[Theme] = db.run(createThemeAction(creation)) map (row => row.asTheme)

    def createGoal(creation: CreateGoal): Future[Goal] = db.run(createGoalAction(creation)) map (row => row.asGoal)

    def createThread(creation: CreateThread): Future[Thread] = db.run(createThreadAction(creation)) map (row => row.asThread)

    def createWeave(creation: CreateWeave): Future[Weave] = db.run(createWeaveAction(creation)) map (row => row.asWeave)

    def createLaserDonut(creation: CreateLaserDonut): Future[LaserDonut] = db.run(createLaserDonutAction(creation)) map (row => row.asLaserDonut)

    def createPortion(creation: CreatePortion): Future[Portion] = db.run(createPortionAction(creation)) map (row => row.asPortion)

    def createTodo(creation: CreateTodo): Future[Todo] = db.run(createTodoAction(creation)) map (row => row.asTodo)

    def createHobby(creation: CreateHobby): Future[Hobby] = db.run(createHobbyAction(creation)) map (row => row.asHobby)

    // Update endpoints
    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Boolean] = db.run(updateMessageAction(uuid, update)).map(_ > 0)

    def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem): Future[Boolean] = db.run(updateBacklogItemAction(uuid, update)).map(_ > 0)

    def updateEpoch(uuid: UUID, update: UpdateEpoch): Future[Boolean] = db.run(updateEpochAction(uuid, update)).map(_ > 0)

    def updateYear(uuid: UUID, update: UpdateYear): Future[Boolean] = db.run(updateYearAction(uuid, update)).map(_ > 0)

    def updateTheme(uuid: UUID, update: UpdateTheme): Future[Boolean] = db.run(updateThemeAction(uuid, update)).map(_ > 0)

    def updateGoal(uuid: UUID, update: UpdateGoal): Future[Boolean] = ???

    def updateThread(uuid: UUID, update: UpdateThread): Future[Boolean] = db.run(updateThreadAction(uuid, update)).map(_ > 0)

    def updateWeave(uuid: UUID, update: UpdateWeave): Future[Boolean] = db.run(updateWeaveAction(uuid, update)).map(_ > 0)

    def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut): Future[Boolean] = db.run(updateLaserDonutAction(uuid, update)).map(_ > 0)

    def updatePortion(uuid: UUID, update: UpdatePortion): Future[Boolean] = db.run(updatePortionAction(uuid, update)).map(_ > 0)

    def updateTodo(uuid: UUID, update: UpdateTodo): Future[Boolean] = db.run(updateTodoAction(uuid, update)).map(_ > 0)

    def updateHobby(uuid: UUID, update: UpdateHobby): Future[Boolean] = db.run(updateHobbyAction(uuid, update)).map(_ > 0)

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

    def getGoals: Future[Seq[Goal]] = db.run(getGoalsAction.map(_.map(_.asGoal)))

    def getGoal(uuid: UUID): Future[Option[Goal]] = db.run(getGoalAction(uuid).map(_.map(_.asGoal)))

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

    def deleteTheme(uuid: UUID): Future[Boolean] = db.run(deleteThemeAction(uuid)).map(_ > 0)

    def deleteGoal(uuid: UUID): Future[Boolean] = ???

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
        uuid = UUID.randomUUID,
        sender = creation.sender,
        content = creation.content
      )
      (MessageTable returning MessageTable.map(_.id) into ((message, id) => message.copy(id = id))) += row
    }

    private def createBacklogItemAction(creation: CreateBacklogItem) = {
      val row = BacklogItemRow(
        id = 0L,
        uuid = UUID.randomUUID,
        summary = creation.summary,
        description = creation.description,
        `type` = creation.`type`.dbValue
      )
      (BacklogItemTable returning BacklogItemTable.map(_.id) into ((item, id) => item.copy(id = id))) += row
    }

    private def createEpochAction(creation: CreateEpoch) = {
      val row = EpochRow(
        id = 0L,
        uuid = UUID.randomUUID,
        name = creation.name,
        totem = creation.totem,
        question = creation.question
      )
      (EpochTable returning EpochTable.map(_.id) into ((epoch, id) => epoch.copy(id = id))) += row
    }

    private def createYearAction(creation: CreateYear) = {
      val row = YearRow(
        id = 0L,
        uuid = UUID.randomUUID,
        epochId = creation.epochId,
        startDate = creation.startDate,
        finishDate = creation.finishDate
      )
      (YearTable returning YearTable.map(_.id) into ((year, id) => year.copy(id = id))) += row
    }

    private def createThemeAction(creation: CreateTheme) = {
      val row = ThemeRow(
        id = 0L,
        uuid = UUID.randomUUID,
        yearId = creation.yearId,
        name = creation.name
      )
      (ThemeTable returning ThemeTable.map(_.id) into ((theme, id) => theme.copy(id = id))) += row
    }

    private def createGoalAction(creation: CreateGoal): DBIO[(GoalRow, Seq[BacklogItemRow])] = {
      insertGoalAction(creation).zip(getBacklogItemsAction(creation.backlogItems)).flatMap { case ((goal, backlogItems)) =>
        insertGoalBacklogItemsAction(goal.id, backlogItems.map(_.id)).andThen(DBIO.successful(goal).zip(DBIO.successful(backlogItems)))
      }.transactionally
    }

    private def insertGoalAction(creation: CreateGoal): DBIO[GoalRow] = {
      val row = GoalRow(
        id = 0L,
        uuid = UUID.randomUUID,
        themeId = creation.themeId,
        summary = creation.summary,
        description = creation.description,
        level = creation.level,
        priority = creation.priority,
        status = creation.status.dbValue,
        graduation = creation.graduation.dbValue
      )
      (GoalTable returning GoalTable.map(_.id) into ((goal, id) => goal.copy(id = id))) += row
    }

    private def insertGoalBacklogItemsAction(goalId: Long, backlogItemIds: Seq[Long]): DBIO[Seq[GoalBacklogItemRow]] = {
      DBIO.sequence(backlogItemIds.map(backlogItemId => insertGoalBacklogItemAction(goalId, backlogItemId)))
    }

    private def insertGoalBacklogItemAction(goalId: Long, backlogItemId: Long): DBIO[GoalBacklogItemRow] = {
      val row = GoalBacklogItemRow(
        id = 0L,
        goalId = goalId,
        backlogItemId = backlogItemId
      )
      (GoalBacklogItemTable returning GoalBacklogItemTable.map(_.id) into ((goalAndItem, id) => goalAndItem.copy(id = id))) += row
    }

    private def createThreadAction(creation: CreateThread) = {
      val row = ThreadRow(
        id = 0L,
        uuid = UUID.randomUUID,
        goalId = creation.goalId,
        summary = creation.summary,
        description = creation.description,
        status = creation.status.dbValue
      )
      (ThreadTable returning ThreadTable.map(_.id) into ((thread, id) => thread.copy(id = id))) += row
    }

    private def createWeaveAction(creation: CreateWeave) = {
      val row = WeaveRow(
        id = 0L,
        uuid = UUID.randomUUID,
        goalId = creation.goalId,
        summary = creation.summary,
        description = creation.description,
        status = creation.status.dbValue,
        `type` = creation.`type`.dbValue
      )
      (WeaveTable returning WeaveTable.map(_.id) into ((weave, id) => weave.copy(id = id))) += row
    }

    private def createLaserDonutAction(creation: CreateLaserDonut) = {
      val row = LaserDonutRow(
        id = 0L,
        uuid = UUID.randomUUID,
        goalId = creation.goalId,
        summary = creation.summary,
        description = creation.description,
        milestone = creation.milestone,
        order = creation.order,
        status = creation.status.dbValue,
        `type` = creation.`type`.dbValue
      )
      (LaserDonutTable returning LaserDonutTable.map(_.id) into ((laserDonut, id) => laserDonut.copy(id = id))) += row
    }

    private def createPortionAction(creation: CreatePortion) = {
      val row = PortionRow(
        id = 0L,
        uuid = UUID.randomUUID,
        laserDonutId = creation.laserDonutId,
        summary = creation.summary,
        order = creation.order,
        status = creation.status.dbValue
      )
      (PortionTable returning PortionTable.map(_.id) into ((portion, id) => portion.copy(id = id))) += row
    }

    private def createTodoAction(creation: CreateTodo) = {
      val row = TodoRow(
        id = 0L,
        uuid = UUID.randomUUID,
        portionId = creation.portionId,
        description = creation.description,
        order = creation.order,
        status = creation.status.dbValue
      )
      (TodoTable returning TodoTable.map(_.id) into ((todo, id) => todo.copy(id = id))) += row
    }

    private def createHobbyAction(creation: CreateHobby) = {
      val row = HobbyRow(
        id = 0L,
        uuid = UUID.randomUUID,
        goalId = creation.goalId,
        summary = creation.summary,
        description = creation.description,
        frequency = creation.frequency.dbValue,
        status = creation.status.dbValue,
        `type` = creation.`type`.dbValue
      )
      (HobbyTable returning HobbyTable.map(_.id) into ((hobby, id) => hobby.copy(id = id))) += row
    }

    // Update actions
    private def updateMessageAction(uuid: UUID, update: UpdateMessage) = {
      val query = messageByUuid(uuid).map(message => (message.sender, message.content))
      getMessageAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.sender.getOrElse(old.sender), update.content.getOrElse(old.content))
        } getOrElse DBIO.failed(MessageNotFoundException())
      }.transactionally
    }

    private def updateBacklogItemAction(uuid: UUID, update: UpdateBacklogItem) = {
      val query = backlogItemByUuid(uuid).map(item => (item.summary, item.description, item.`type`))
      getBacklogItemAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.summary.getOrElse(old.summary), update.description.getOrElse(old.description),
            update.`type`.map(_.dbValue).getOrElse(old.`type`))
        } getOrElse DBIO.failed(BacklogItemNotFoundException())
      }.transactionally
    }

    private def updateEpochAction(uuid: UUID, update: UpdateEpoch) = {
      val query = epochByUuid(uuid).map(epoch => (epoch.name, epoch.totem, epoch.question))
      getEpochAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.name.getOrElse(old.name), update.totem.getOrElse(old.totem), update.question.getOrElse(old.question))
        } getOrElse DBIO.failed(EpochNotFoundException())
      }.transactionally
    }

    private def updateYearAction(uuid: UUID, update: UpdateYear) = {
      val query = yearByUuid(uuid).map(epoch => (epoch.epochId, epoch.startDate, epoch.finishDate))
      getYearAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.epochId, old.epochId), UpdateDate.keepOrReplace(update.startDate,
            old.startDate), UpdateDate.keepOrReplace(update.finishDate, old.finishDate))
        } getOrElse DBIO.failed(YearNotFoundException())
      }.transactionally
    }

    private def updateThemeAction(uuid: UUID, update: UpdateTheme) = {
      val query = themeByUuid(uuid).map(theme => (theme.yearId, theme.name))
      getThemeAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.yearId, old.yearId), update.name.getOrElse(old.name))
        } getOrElse DBIO.failed(ThemeNotFoundException())
      }.transactionally
    }

    private def updateGoalAction(uuid: UUID, update: UpdateGoal) = {
      ???
    }

    private def updateThreadAction(uuid: UUID, update: UpdateThread) = {
      val query = threadByUuid(uuid).map(thread => (thread.goalId, thread.summary, thread.description, thread.status))
      getThreadAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), update.status.map(_.dbValue).getOrElse(old.status))
        } getOrElse DBIO.failed(ThreadNotFoundException())
      }.transactionally
    }

    private def updateWeaveAction(uuid: UUID, update: UpdateWeave) = {
      val query = weaveByUuid(uuid).map(weave => (weave.goalId, weave.summary, weave.description, weave.status, weave.`type`))
      getWeaveAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), update.status.map(_.dbValue).getOrElse(old.status),
            update.status.map(_.dbValue).getOrElse(old.`type`))
        } getOrElse DBIO.failed(WeaveNotFoundException())
      }.transactionally
    }

    private def updateLaserDonutAction(uuid: UUID, update: UpdateLaserDonut) = {
      val query = laserDonutByUuid(uuid).map(laserDonut => (laserDonut.goalId, laserDonut.summary, laserDonut.description, laserDonut.milestone, laserDonut.order, laserDonut.status, laserDonut.`type`))
      getLaserDonutAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), update.milestone.getOrElse(old.milestone), update.order.getOrElse(old.order),
            update.status.map(_.dbValue).getOrElse(old.status), update.status.map(_.dbValue).getOrElse(old.`type`))
        } getOrElse DBIO.failed(LaserDonutNotFoundException())
      }.transactionally
    }

    private def updatePortionAction(uuid: UUID, update: UpdatePortion) = {
      val query = portionByUuid(uuid).map(portion => (portion.laserDonutId, portion.summary, portion.order, portion.status))
      getPortionAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.laserDonutId, old.laserDonutId), update.summary.getOrElse(old.summary),
            update.order.getOrElse(old.order), update.status.map(_.dbValue).getOrElse(old.status))
        } getOrElse DBIO.failed(PortionNotFoundException())
      }.transactionally
    }

    private def updateTodoAction(uuid: UUID, update: UpdateTodo) = {
      val query = todoByUuid(uuid).map(todo => (todo.portionId, todo.description, todo.order, todo.status))
      getTodoAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.portionId, old.portionId), update.description.getOrElse(old.description),
            update.order.getOrElse(old.order), update.status.map(_.dbValue).getOrElse(old.status))
        } getOrElse DBIO.failed(TodoNotFoundException())
      }.transactionally
    }

    private def updateHobbyAction(uuid: UUID, update: UpdateHobby) = {
      val query = hobbyByUuid(uuid).map(hobby => (hobby.goalId, hobby.summary, hobby.description, hobby.frequency, hobby.status, hobby.`type`))
      getHobbyAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), update.frequency.map(_.dbValue).getOrElse(old.frequency),
            update.status.map(_.dbValue).getOrElse(old.status), update.`type`.map(_.dbValue).getOrElse(old.`type`))
        } getOrElse DBIO.failed(HobbyNotFoundException())
      }.transactionally
    }

    // Get actions
    private def getMessageAction(uuid: UUID) = messageByUuid(uuid).result.headOption

    private def getBacklogItemsAction(uuids: Seq[UUID]) = backlogItemsByUuid(uuids).result

    private def getBacklogItemAction(uuid: UUID) = backlogItemByUuid(uuid).result.headOption

    private def getEpochAction(uuid: UUID) = epochByUuid(uuid).result.headOption

    private def getYearAction(uuid: UUID) = yearByUuid(uuid).result.headOption

    private def getThemeAction(uuid: UUID) = themeByUuid(uuid).result.headOption

    private def getGoalsAction: DBIO[Seq[(GoalRow, Seq[BacklogItemRow])]] = goalsWithBacklogItems.result.map(groupByGoal)

    private def getGoalAction(uuid: UUID): DBIO[Option[(GoalRow, Seq[BacklogItemRow])]] = goalWithBacklogItemsByUuid(uuid).result.map(groupByGoal(_).headOption)

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

    private def deleteGoalAction(uuid: UUID) = {
//      val pairsQuery = goalWithBacklogItemsByUuid(uuid)
//        .map { case (goal, backlogItem) => (goal.id, backlogItem.id) }
//
//      pairsQuery.map { case (goalId, backlogItemId) =>
//          GoalBacklogItemTable.filter(pair => pair.goalId === goalId && pair.backlogItemId === backlogItemId)
//        }
//      DBIO.sequence(Seq(pairsQuery.delete, goalByUuid(uuid).delete)).transactionally
      ???
    }

    private def deleteThreadAction(uuid: UUID) = threadByUuid(uuid).delete

    private def deleteWeaveAction(uuid: UUID) = weaveByUuid(uuid).delete

    private def deleteLaserDonutAction(uuid: UUID) = laserDonutByUuid(uuid).delete

    private def deletePortionAction(uuid: UUID) = portionByUuid(uuid).delete

    private def deleteTodoAction(uuid: UUID) = todoByUuid(uuid).delete

    private def deleteHobbyAction(uuid: UUID) = hobbyByUuid(uuid).delete

    // Queries
    private def messageByUuid(uuid: UUID) = MessageTable.filter(_.uuid === uuid.toString)

    private def backlogItemsByUuid(uuids: Seq[UUID]) = BacklogItemTable.filter(_.uuid inSet uuids.map(_.toString))

    private def backlogItemByUuid(uuid: UUID) = BacklogItemTable.filter(_.uuid === uuid.toString)

    private def epochByUuid(uuid: UUID) = EpochTable.filter(_.uuid === uuid.toString)

    private def yearByUuid(uuid: UUID) = YearTable.filter(_.uuid === uuid.toString)

    private def themeByUuid(uuid: UUID) = ThemeTable.filter(_.uuid === uuid.toString)

    private def goalByUuid(uuid: UUID) = GoalTable.filter(_.uuid === uuid.toString)

    private def goalWithBacklogItemsByUuid(uuid: UUID) = goalsWithBacklogItems.filter { case (goal, _) => goal.uuid === uuid.toString }

    private def goalsWithBacklogItems = {
      GoalTable
        .join(GoalBacklogItemTable)
        .on(_.id === _.goalId)
        .join(BacklogItemTable)
        .on { case ((_, goalBacklogItem), backlogItem) => goalBacklogItem.backlogItemId === backlogItem.id }
        .map { case ((goal, _), backlogItem) => (goal, backlogItem) }
    }

    private def groupByGoal(goalBacklogItems: Seq[(GoalRow, BacklogItemRow)]): Seq[(GoalRow, Seq[BacklogItemRow])] = {
      goalBacklogItems.groupBy { case (goal, _) => goal }.map { case (goal, pairs) => (goal, pairs.map(_._2)) }.toSeq
    }

    private def threadByUuid(uuid: UUID) = ThreadTable.filter(_.uuid === uuid.toString)

    private def weaveByUuid(uuid: UUID) = WeaveTable.filter(_.uuid === uuid.toString)

    private def laserDonutByUuid(uuid: UUID) = LaserDonutTable.filter(_.uuid === uuid.toString)

    private def portionByUuid(uuid: UUID) = PortionTable.filter(_.uuid === uuid.toString)

    private def todoByUuid(uuid: UUID) = TodoTable.filter(_.uuid === uuid.toString)

    private def hobbyByUuid(uuid: UUID) = HobbyTable.filter(_.uuid === uuid.toString)
  }
}
