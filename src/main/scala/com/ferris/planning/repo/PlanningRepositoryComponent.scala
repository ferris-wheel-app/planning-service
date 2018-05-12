package com.ferris.planning.repo

import java.sql.Date
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
    def createPyramidOfImportance(pyramid: UpsertPyramidOfImportance): Future[PyramidOfImportance]

    def updateMessage(uuid: UUID, update: UpdateMessage): Future[Message]
    def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem): Future[BacklogItem]
    def updateEpoch(uuid: UUID, update: UpdateEpoch): Future[Epoch]
    def updateYear(uuid: UUID, update: UpdateYear): Future[Year]
    def updateTheme(uuid: UUID, update: UpdateTheme): Future[Theme]
    def updateGoal(uuid: UUID, update: UpdateGoal): Future[Goal]
    def updateThread(uuid: UUID, update: UpdateThread): Future[Thread]
    def updateWeave(uuid: UUID, update: UpdateWeave): Future[Weave]
    def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut): Future[LaserDonut]
    def updatePortion(uuid: UUID, update: UpdatePortion): Future[Portion]
    def updatePortions(laserDonutId: UUID, update: UpdateList): Future[Seq[Portion]]
    def updateTodo(uuid: UUID, update: UpdateTodo): Future[Todo]
    def updateTodos(portionId: UUID, update: UpdateList): Future[Seq[Todo]]
    def updateHobby(uuid: UUID, update: UpdateHobby): Future[Hobby]
    def refreshPyramidOfImportance(): Future[PyramidOfImportance]

    def getMessages: Future[Seq[Message]]
    def getBacklogItems: Future[Seq[BacklogItem]]
    def getEpochs: Future[Seq[Epoch]]
    def getYears: Future[Seq[Year]]
    def getThemes: Future[Seq[Theme]]
    def getGoals: Future[Seq[Goal]]
    def getThreads: Future[Seq[Thread]]
    def getThreads(goalId: UUID): Future[Seq[Thread]]
    def getWeaves: Future[Seq[Weave]]
    def getWeaves(goalId: UUID): Future[Seq[Weave]]
    def getLaserDonuts: Future[Seq[LaserDonut]]
    def getLaserDonuts(goalId: UUID): Future[Seq[LaserDonut]]
    def getPortions: Future[Seq[Portion]]
    def getPortions(laserDonutId: UUID): Future[Seq[Portion]]
    def getTodos: Future[Seq[Todo]]
    def getTodos(portionId: UUID): Future[Seq[Todo]]
    def getHobbies: Future[Seq[Hobby]]
    def getHobbies(goalId: UUID): Future[Seq[Hobby]]

    def getMessage(uuid: UUID): Future[Option[Message]]
    def getBacklogItem(uuid: UUID): Future[Option[BacklogItem]]
    def getEpoch(uuid: UUID): Future[Option[Epoch]]
    def getYear(uuid: UUID): Future[Option[Year]]
    def getTheme(uuid: UUID): Future[Option[Theme]]
    def getGoal(uuid: UUID): Future[Option[Goal]]
    def getThread(uuid: UUID): Future[Option[Thread]]
    def getWeave(uuid: UUID): Future[Option[Weave]]
    def getLaserDonut(uuid: UUID): Future[Option[LaserDonut]]
    def getCurrentLaserDonut: Future[Option[LaserDonut]]
    def getPortion(uuid: UUID): Future[Option[Portion]]
    def getCurrentPortion: Future[Option[Portion]]
    def getTodo(uuid: UUID): Future[Option[Todo]]
    def getHobby(uuid: UUID): Future[Option[Hobby]]
    def getPyramidOfImportance: Future[PyramidOfImportance]

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

trait SqlPlanningRepositoryComponent extends PlanningRepositoryComponent {
  this: TablesComponent =>

  lazy val tableConversions = new TableConversions(tables)
  import tableConversions.tables._
  import tableConversions.tables.profile.api._
  import tableConversions._

  implicit val repoEc: ExecutionContext
  override val repo = new SqlPlanningRepository
  val db: tables.profile.api.Database

  class SqlPlanningRepository extends PlanningRepository {

    // Create endpoints
    override def createMessage(creation: CreateMessage): Future[Message] = {
      val row = MessageRow(
        id = 0L,
        uuid = UUID.randomUUID,
        sender = creation.sender,
        content = creation.content
      )
      val action = (MessageTable returning MessageTable.map(_.id) into ((message, id) => message.copy(id = id))) += row
      db.run(action) map (row => row.asMessage)
    }

    override def createBacklogItem(creation: CreateBacklogItem): Future[BacklogItem] = {
      val row = BacklogItemRow(
        id = 0L,
        uuid = UUID.randomUUID,
        summary = creation.summary,
        description = creation.description,
        `type` = creation.`type`.dbValue
      )
      val action = (BacklogItemTable returning BacklogItemTable.map(_.id) into ((item, id) => item.copy(id = id))) += row
      db.run(action) map (row => row.asBacklogItem)
    }

    override def createEpoch(creation: CreateEpoch): Future[Epoch] = {
      val row = EpochRow(
        id = 0L,
        uuid = UUID.randomUUID,
        name = creation.name,
        totem = creation.totem,
        question = creation.question
      )
      val action = (EpochTable returning EpochTable.map(_.id) into ((epoch, id) => epoch.copy(id = id))) += row
      db.run(action) map (row => row.asEpoch)
    }

    override def createYear(creation: CreateYear): Future[Year] = {
      val row = YearRow(
        id = 0L,
        uuid = UUID.randomUUID,
        epochId = creation.epochId,
        startDate = Date.valueOf(creation.startDate),
        finishDate = Date.valueOf(creation.startDate.plusYears(1))
      )
      val action = (YearTable returning YearTable.map(_.id) into ((year, id) => year.copy(id = id))) += row
      db.run(action) map (row => row.asYear)
    }

    override def createTheme(creation: CreateTheme): Future[Theme] = {
      val row = ThemeRow(
        id = 0L,
        uuid = UUID.randomUUID,
        yearId = creation.yearId,
        name = creation.name
      )
      val action = (ThemeTable returning ThemeTable.map(_.id) into ((theme, id) => theme.copy(id = id))) += row
      db.run(action) map (row => row.asTheme)
    }

    override def createGoal(creation: CreateGoal): Future[Goal] = {
      def insertGoalAction(creation: CreateGoal): DBIO[GoalRow] = {
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

      val action = insertGoalAction(creation).zip(getBacklogItemsAction(creation.backlogItems)).flatMap { case ((goal, backlogItems)) =>
        insertGoalBacklogItemsAction(goal.id, backlogItems.map(_.id)).andThen(DBIO.successful(goal).zip(DBIO.successful(backlogItems)))
      }.transactionally
      db.run(action) map (row => row.asGoal)
    }

    override def createThread(creation: CreateThread): Future[Thread] = {
      val row = ThreadRow(
        id = 0L,
        uuid = UUID.randomUUID,
        goalId = creation.goalId,
        summary = creation.summary,
        description = creation.description,
        status = creation.status.dbValue
      )
      val action = (ThreadTable returning ThreadTable.map(_.id) into ((thread, id) => thread.copy(id = id))) += row
      db.run(action) map (row => row.asThread)
    }

    override def createWeave(creation: CreateWeave): Future[Weave] = {
      val row = WeaveRow(
        id = 0L,
        uuid = UUID.randomUUID,
        goalId = creation.goalId,
        summary = creation.summary,
        description = creation.description,
        status = creation.status.dbValue,
        `type` = creation.`type`.dbValue
      )
      val action = (WeaveTable returning WeaveTable.map(_.id) into ((weave, id) => weave.copy(id = id))) += row
      db.run(action) map (row => row.asWeave)
    }

    override def createLaserDonut(creation: CreateLaserDonut): Future[LaserDonut] = {
      val action = laserDonutsByParentId(creation.goalId).result.flatMap { existingLaserDonuts =>
        val row = LaserDonutRow(
          id = 0L,
          uuid = UUID.randomUUID,
          goalId = creation.goalId,
          summary = creation.summary,
          description = creation.description,
          milestone = creation.milestone,
          order = existingLaserDonuts.lastOption.map(_.order + 1).getOrElse(1),
          status = creation.status.dbValue,
          `type` = creation.`type`.dbValue
        )
        (LaserDonutTable returning LaserDonutTable.map(_.id) into ((laserDonut, id) => laserDonut.copy(id = id))) += row
      }.transactionally
      db.run(action) map (row => row.asLaserDonut)
    }

    override def createPortion(creation: CreatePortion): Future[Portion] = {
      val action = portionsByParentId(creation.laserDonutId).result.flatMap { existingPortions =>
        val row = PortionRow(
          id = 0L,
          uuid = UUID.randomUUID,
          laserDonutId = creation.laserDonutId,
          summary = creation.summary,
          order = existingPortions.lastOption.map(_.order + 1).getOrElse(1),
          status = creation.status.dbValue
        )
        (PortionTable returning PortionTable.map(_.id) into ((portion, id) => portion.copy(id = id))) += row
      }.transactionally
      db.run(action) map (row => row.asPortion)
    }

    override def createTodo(creation: CreateTodo): Future[Todo] = {
      val action = todosByParentId(creation.portionId).result.flatMap { existingTodos =>
        val row = TodoRow(
          id = 0L,
          uuid = UUID.randomUUID,
          portionId = creation.portionId,
          description = creation.description,
          order = existingTodos.lastOption.map(_.order + 1).getOrElse(1),
          status = creation.status.dbValue
        )
        (TodoTable returning TodoTable.map(_.id) into ((todo, id) => todo.copy(id = id))) += row
      }.transactionally
      db.run(action) map (row => row.asTodo)
    }

    override def createHobby(creation: CreateHobby): Future[Hobby] = {
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
      val action = (HobbyTable returning HobbyTable.map(_.id) into ((hobby, id) => hobby.copy(id = id))) += row
      db.run(action) map (row => row.asHobby)
    }

    override def createPyramidOfImportance(pyramid: UpsertPyramidOfImportance): Future[PyramidOfImportance] = {
      val insertions = for {
        (tier, tierNumber) <- pyramid.tiers.zipWithIndex
        laserDonutUuid <- tier.laserDonuts
      } yield {
        getLaserDonutAction(laserDonutUuid).flatMap {
          case Some(laserDonut) =>
            val row = PyramidOfImportanceRow(
              id = 0L,
              laserDonutId = laserDonut.id,
              tier = tierNumber,
              current = false
            )
            ((PyramidOfImportanceTable returning PyramidOfImportanceTable.map(_.id) into ((row, id) => row.copy(id = id))) += row).map((_, laserDonut))
          case None => DBIO.failed(LaserDonutNotFoundException(s"no laser-donut with the UUID $laserDonutUuid exists"))
        }
      }
      val action = DBIO.sequence(insertions)
      db.run(action).map(_.asPyramid)
    }

    // Update endpoints
    override def updateMessage(uuid: UUID, update: UpdateMessage): Future[Message] = {
      val query = messageByUuid(uuid).map(message => (message.sender, message.content))
      val action = getMessageAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.sender.getOrElse(old.sender), update.content.getOrElse(old.content))
            .andThen(getMessageAction(uuid).map(_.head))
        } getOrElse DBIO.failed(MessageNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asMessage)
    }

    override def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem): Future[BacklogItem] = {
      val query = backlogItemByUuid(uuid).map(item => (item.summary, item.description, item.`type`))
      val action = getBacklogItemAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.summary.getOrElse(old.summary), update.description.getOrElse(old.description),
            UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`))
            .andThen(getBacklogItemAction(uuid).map(_.head))
        } getOrElse DBIO.failed(BacklogItemNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asBacklogItem)
    }

    override def updateEpoch(uuid: UUID, update: UpdateEpoch): Future[Epoch] = {
      val query = epochByUuid(uuid).map(epoch => (epoch.name, epoch.totem, epoch.question))
      val action = getEpochAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.name.getOrElse(old.name), update.totem.getOrElse(old.totem), update.question.getOrElse(old.question))
            .andThen(getEpochAction(uuid).map(_.head))
        } getOrElse DBIO.failed(EpochNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asEpoch)
    }

    override def updateYear(uuid: UUID, update: UpdateYear): Future[Year] = {
      val query = yearByUuid(uuid).map(epoch => (epoch.epochId, epoch.startDate, epoch.finishDate))
      val action = getYearAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.epochId, old.epochId), UpdateDate.keepOrReplace(update.startDate,
            old.startDate), UpdateDate.keepOrReplace(update.startDate.map(_.plusYears(1)), old.finishDate))
            .andThen(getYearAction(uuid).map(_.head))
        } getOrElse DBIO.failed(YearNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asYear)
    }

    override def updateTheme(uuid: UUID, update: UpdateTheme): Future[Theme] = {
      val query = themeByUuid(uuid).map(theme => (theme.yearId, theme.name))
      val action = getThemeAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.yearId, old.yearId), update.name.getOrElse(old.name))
            .andThen(getThemeAction(uuid).map(_.head))
        } getOrElse DBIO.failed(ThemeNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asTheme)
    }

    override def updateGoal(uuid: UUID, update: UpdateGoal): Future[Goal] = {
      def updateGoal(uuid: UUID, update: UpdateGoal, old: GoalRow) = {
        goalByUuid(uuid).map(goal => (goal.themeId, goal.summary, goal.description, goal.level, goal.priority, goal.status, goal.graduation))
          .update(UpdateId.keepOrReplace(update.themeId, old.themeId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), update.level.getOrElse(old.level),
            UpdateBoolean.keepOrReplace(update.priority, old.priority), UpdateTypeEnum.keepOrReplace(update.status, old.status),
            UpdateTypeEnum.keepOrReplace(update.graduation, old.graduation))
      }

      val action = getGoalAction(uuid).flatMap { maybeObj =>
        maybeObj map { case (old, _) =>
          updateGoal(uuid, update, old)
            .flatMap { _ =>
              update.backlogItems match {
                case Some(backlogItems) =>
                  for {
                    _ <- GoalBacklogItemTable.filter(_.goalId === old.id).delete
                    newBacklogItems <- getBacklogItemsAction(backlogItems)
                    result <- insertGoalBacklogItemsAction(old.id, newBacklogItems.map(_.id)).andThen(getGoalAction(uuid).map(_.head))
                  } yield result
                case None => getGoalAction(uuid).map(_.head)
              }
            }
        } getOrElse DBIO.failed(GoalNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asGoal)
    }

    override def updateThread(uuid: UUID, update: UpdateThread): Future[Thread] = {
      val query = threadByUuid(uuid).map(thread => (thread.goalId, thread.summary, thread.description, thread.status))
      val action = getThreadAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.status, old.status))
            .andThen(getThreadAction(uuid).map(_.head))
        } getOrElse DBIO.failed(ThreadNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asThread)
    }

    override def updateWeave(uuid: UUID, update: UpdateWeave): Future[Weave] = {
      val query = weaveByUuid(uuid).map(weave => (weave.goalId, weave.summary, weave.description, weave.status, weave.`type`))
      val action = getWeaveAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.status, old.status),
            UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`))
            .andThen(getWeaveAction(uuid).map(_.head))
        } getOrElse DBIO.failed(WeaveNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asWeave)
    }

    override def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut): Future[LaserDonut] = {
      val query = laserDonutByUuid(uuid).map(laserDonut => (laserDonut.goalId, laserDonut.summary, laserDonut.description, laserDonut.milestone, laserDonut.status, laserDonut.`type`))
      val action = getLaserDonutAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), update.milestone.getOrElse(old.milestone),
            UpdateTypeEnum.keepOrReplace(update.status, old.status), UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`))
            .andThen(getLaserDonutAction(uuid).map(_.head))
        } getOrElse DBIO.failed(LaserDonutNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asLaserDonut)
    }

    override def updatePortion(uuid: UUID, update: UpdatePortion): Future[Portion] = {
      val query = portionByUuid(uuid).map(portion => (portion.laserDonutId, portion.summary, portion.status))
      val action = getPortionAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.laserDonutId, old.laserDonutId), update.summary.getOrElse(old.summary),
            UpdateTypeEnum.keepOrReplace(update.status, old.status))
            .andThen(getPortionAction(uuid).map(_.head))
        } getOrElse DBIO.failed(PortionNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asPortion)
    }

    override def updatePortions(laserDonutId: UUID, update: UpdateList): Future[Seq[Portion]] = {
      def getPortionsAction(uuids: Seq[String]) = {
        PortionTable.filter(_.uuid inSet uuids).sortBy(_.order).result
      }
      val action = portionsByParentId(laserDonutId).result.flatMap { portions =>
        if (portions.size <= update.reordered.size) {
          val portionIds = portions.map(_.uuid)
          update.reordered.filterNot(id => portionIds.contains(id.toString)) match {
            case Nil => DBIO.sequence(update.reordered.zipWithIndex.map { case (uuid, index) =>
              portionByUuid(uuid).map(_.order).update(index + 1)
            }).andThen(getPortionsAction(portionIds))
            case outliers => DBIO.failed(
              InvalidPortionsUpdateException(s"the portions (${outliers.mkString(", ")}) do not belong to the laser-donut $laserDonutId")
            )
          }
        }
        else DBIO.failed(
          InvalidPortionsUpdateException("the length of the update list should be the same as the number of portions for the laser-donut")
        )
      }.transactionally
      db.run(action).map(_.map(_.asPortion))
    }

    override def updateTodo(uuid: UUID, update: UpdateTodo): Future[Todo] = {
      val query = todoByUuid(uuid).map(todo => (todo.portionId, todo.description, todo.status))
      val action = getTodoAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.portionId, old.portionId), update.description.getOrElse(old.description),
            UpdateTypeEnum.keepOrReplace(update.status, old.status))
            .andThen(getTodoAction(uuid).map(_.head))
        } getOrElse DBIO.failed(TodoNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asTodo)
    }

    override def updateTodos(portionId: UUID, update: UpdateList): Future[Seq[Todo]] = {
      def getTodosAction(uuids: Seq[String]) = {
        TodoTable.filter(_.uuid inSet uuids).sortBy(_.order).result
      }
      val action = todosByParentId(portionId).result.flatMap { todos =>
        if (todos.size <= update.reordered.size) {
          val todoIds = todos.map(_.uuid)
          update.reordered.filterNot(id => todoIds.contains(id.toString)) match {
            case Nil => DBIO.sequence(update.reordered.zipWithIndex.map { case (uuid, index) =>
              todoByUuid(uuid).map(_.order).update(index + 1)
            }).andThen(getTodosAction(todoIds))
            case outliers => DBIO.failed(
              InvalidTodosUpdateException(s"the todos (${outliers.mkString(", ")}) do not belong to the portion $portionId")
            )
          }
        }
        else DBIO.failed(
          InvalidTodosUpdateException("the length of the update list should be the same as the number of todos for the portion")
        )
      }.transactionally
      db.run(action).map(_.map(_.asTodo))
    }

    override def updateHobby(uuid: UUID, update: UpdateHobby): Future[Hobby] = {
      val query = hobbyByUuid(uuid).map(hobby => (hobby.goalId, hobby.summary, hobby.description, hobby.frequency, hobby.status, hobby.`type`))
      val action = getHobbyAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.frequency, old.frequency),
            UpdateTypeEnum.keepOrReplace(update.status, old.status), UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`))
            .andThen(getHobbyAction(uuid).map(_.head))
        } getOrElse DBIO.failed(HobbyNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asHobby)
    }

    // Get endpoints
    override def getMessages: Future[Seq[Message]] = {
      db.run(MessageTable.result.map(_.map(_.asMessage)))
    }

    override def getMessage(uuid: UUID): Future[Option[Message]] = {
      db.run(getMessageAction(uuid).map(_.map(_.asMessage)))
    }

    override def getBacklogItems: Future[Seq[BacklogItem]] = {
      db.run(BacklogItemTable.result.map(_.map(_.asBacklogItem)))
    }

    override def getBacklogItem(uuid: UUID): Future[Option[BacklogItem]] = {
      db.run(getBacklogItemAction(uuid).map(_.map(_.asBacklogItem)))
    }

    override def getEpochs: Future[Seq[Epoch]] = {
      db.run(EpochTable.result.map(_.map(_.asEpoch)))
    }

    override def getEpoch(uuid: UUID): Future[Option[Epoch]] = {
      db.run(getEpochAction(uuid).map(_.map(_.asEpoch)))
    }

    override def getYears: Future[Seq[Year]] = {
      db.run(YearTable.result.map(_.map(_.asYear)))
    }

    override def getYear(uuid: UUID): Future[Option[Year]] = {
      db.run(getYearAction(uuid).map(_.map(_.asYear)))
    }

    override def getThemes: Future[Seq[Theme]] = {
      db.run(ThemeTable.result.map(_.map(_.asTheme)))
    }

    override def getTheme(uuid: UUID): Future[Option[Theme]] = {
      db.run(getThemeAction(uuid).map(_.map(_.asTheme)))
    }

    override def getGoals: Future[Seq[Goal]] = {
      db.run(getGoalsAction.map(_.map(_.asGoal)))
    }

    override def getGoal(uuid: UUID): Future[Option[Goal]] = {
      db.run(getGoalAction(uuid).map(_.map(_.asGoal)))
    }

    override def getThreads: Future[Seq[Thread]] = {
      db.run(ThreadTable.result.map(_.map(_.asThread)))
    }

    override def getThreads(goalId: UUID): Future[Seq[Thread]] = {
      db.run(threadsByParentId(goalId).result.map(_.map(_.asThread)))
    }

    override def getThread(uuid: UUID): Future[Option[Thread]] = {
      db.run(getThreadAction(uuid).map(_.map(_.asThread)))
    }

    override def getWeaves: Future[Seq[Weave]] = {
      db.run(WeaveTable.result.map(_.map(_.asWeave)))
    }

    override def getWeaves(goalId: UUID): Future[Seq[Weave]] = {
      db.run(weavesByParentId(goalId).result.map(_.map(_.asWeave)))
    }

    override def getWeave(uuid: UUID): Future[Option[Weave]] = {
      db.run(getWeaveAction(uuid).map(_.map(_.asWeave)))
    }

    override def getLaserDonuts: Future[Seq[LaserDonut]] = {
      db.run(LaserDonutTable.result.map(_.map(_.asLaserDonut)))
    }

    override def getLaserDonuts(goalId: UUID): Future[Seq[LaserDonut]] = {
      db.run(laserDonutsByParentId(goalId).result.map(_.map(_.asLaserDonut)))
    }

    override def getLaserDonut(uuid: UUID): Future[Option[LaserDonut]] = {
      db.run(getLaserDonutAction(uuid).map(_.map(_.asLaserDonut)))
    }

    override def getPortions: Future[Seq[Portion]] = {
      db.run(PortionTable.result.map(_.map(_.asPortion)))
    }

    override def getPortions(laserDonutId: UUID): Future[Seq[Portion]] = {
      db.run(portionsByParentId(laserDonutId).result.map(_.map(_.asPortion)))
    }

    override def getPortion(uuid: UUID): Future[Option[Portion]] = {
      db.run(getPortionAction(uuid).map(_.map(_.asPortion)))
    }

    override def getTodos: Future[Seq[Todo]] = {
      db.run(TodoTable.result.map(_.map(_.asTodo)))
    }

    override def getTodos(portionId: UUID): Future[Seq[Todo]] = {
      db.run(todosByParentId(portionId).result.map(_.map(_.asTodo)))
    }

    override def getTodo(uuid: UUID): Future[Option[Todo]] = {
      db.run(getTodoAction(uuid).map(_.map(_.asTodo)))
    }

    override def getHobbies: Future[Seq[Hobby]] = {
      db.run(HobbyTable.result.map(_.map(_.asHobby)))
    }

    override def getHobbies(goalId: UUID): Future[Seq[Hobby]] = {
      db.run(hobbiesByParentId(goalId).result.map(_.map(_.asHobby)))
    }

    override def getHobby(uuid: UUID): Future[Option[Hobby]] = {
      db.run(getHobbyAction(uuid).map(_.map(_.asHobby)))
    }

    override def getPyramidOfImportance: Future[PyramidOfImportance] = {
      db.run(getPyramidOfImportanceAction)
    }

    // Delete endpoints
    override def deleteMessage(uuid: UUID): Future[Boolean] = {
      val action = messageByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteBacklogItem(uuid: UUID): Future[Boolean] = {
      val action = backlogItemByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteEpoch(uuid: UUID): Future[Boolean] = {
      val action = epochByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteYear(uuid: UUID): Future[Boolean] = {
      val action = yearByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteTheme(uuid: UUID): Future[Boolean] = {
      val action = themeByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteGoal(uuid: UUID): Future[Boolean] = {
      val action = getGoalAction(uuid).flatMap { maybeObj =>
        maybeObj map { case (goal, _) =>
          for {
            _ <- GoalBacklogItemTable.filter(_.goalId === goal.id).delete
            result <- GoalTable.filter(_.id === goal.id).delete
          } yield result
        } getOrElse DBIO.failed(GoalNotFoundException())
      }.transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteThread(uuid: UUID): Future[Boolean] = {
      val action = threadByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteWeave(uuid: UUID): Future[Boolean] = {
      val action = weaveByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteLaserDonut(uuid: UUID): Future[Boolean] = {
      val action = laserDonutByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deletePortion(uuid: UUID): Future[Boolean] = {
      val action = portionByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteTodo(uuid: UUID): Future[Boolean] = {
      val action = todoByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteHobby(uuid: UUID): Future[Boolean] = {
      val action = hobbyByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    // Actions
    private def insertGoalBacklogItemsAction(goalId: Long, backlogItemIds: Seq[Long]): DBIO[Seq[GoalBacklogItemRow]] = {
      val rows = backlogItemIds.map { backlogItemId =>
        GoalBacklogItemRow(
          id = 0L,
          goalId = goalId,
          backlogItemId = backlogItemId
        )
      }
      (GoalBacklogItemTable returning GoalBacklogItemTable.map(_.id) into ((goalAndItem, id) => goalAndItem.copy(id = id))) ++= rows
    }

    private def getMessageAction(uuid: UUID) = {
      messageByUuid(uuid).result.headOption
    }

    private def getBacklogItemsAction(uuids: Seq[UUID]) = {
      backlogItemsByUuid(uuids).result
    }

    private def getBacklogItemAction(uuid: UUID) = {
      backlogItemByUuid(uuid).result.headOption
    }

    private def getEpochAction(uuid: UUID) = {
      epochByUuid(uuid).result.headOption
    }

    private def getYearAction(uuid: UUID) = {
      yearByUuid(uuid).result.headOption
    }

    private def getThemeAction(uuid: UUID) = {
      themeByUuid(uuid).result.headOption
    }

    private def getGoalsAction: DBIO[Seq[(GoalRow, Seq[BacklogItemRow])]] = {
      goalsWithBacklogItems.result.map(groupByGoal)
    }

    private def getGoalAction(uuid: UUID): DBIO[Option[(GoalRow, Seq[BacklogItemRow])]] = {
      goalWithBacklogItemsByUuid(uuid).result.map(groupByGoal(_).headOption)
    }

    private def getThreadAction(uuid: UUID) = {
      threadByUuid(uuid).result.headOption
    }

    private def getWeaveAction(uuid: UUID) = {
      weaveByUuid(uuid).result.headOption
    }

    private def getLaserDonutAction(uuid: UUID) = {
      laserDonutByUuid(uuid).result.headOption
    }

    private def getPortionAction(uuid: UUID) = {
      portionByUuid(uuid).result.headOption
    }

    private def getTodoAction(uuid: UUID) = {
      todoByUuid(uuid).result.headOption
    }

    private def getHobbyAction(uuid: UUID) = {
      hobbyByUuid(uuid).result.headOption
    }

    private def getPyramidOfImportanceAction = {
      (for {
        pyramidRow <- PyramidOfImportanceTable
        laserDonutRow <- LaserDonutTable if laserDonutRow.id === pyramidRow.laserDonutId
      } yield (pyramidRow, laserDonutRow)).result.map(_.asPyramid)
    }

    // Queries
    private def messageByUuid(uuid: UUID) = {
      MessageTable.filter(_.uuid === uuid.toString)
    }

    private def backlogItemsByUuid(uuids: Seq[UUID]) = {
      BacklogItemTable.filter(_.uuid inSet uuids.map(_.toString))
    }

    private def backlogItemByUuid(uuid: UUID) = {
      BacklogItemTable.filter(_.uuid === uuid.toString)
    }

    private def epochByUuid(uuid: UUID) = {
      EpochTable.filter(_.uuid === uuid.toString)
    }

    private def yearByUuid(uuid: UUID) = {
      YearTable.filter(_.uuid === uuid.toString)
    }

    private def themeByUuid(uuid: UUID) = {
      ThemeTable.filter(_.uuid === uuid.toString)
    }

    private def goalByUuid(uuid: UUID) = {
      GoalTable.filter(_.uuid === uuid.toString)
    }

    private def goalWithBacklogItemsByUuid(uuid: UUID) = {
      goalsWithBacklogItems.filter { case (goal, _) => goal.uuid === uuid.toString }
    }

    private def goalsWithBacklogItems = {
      GoalTable
        .joinLeft(GoalBacklogItemTable)
        .on(_.id === _.goalId)
        .joinLeft(BacklogItemTable)
        .on { case ((_, goalBacklogItem), backlogItem) => goalBacklogItem.map(_.backlogItemId).getOrElse(-1L) === backlogItem.id }
        .map { case ((goal, _), backlogItem) => (goal, backlogItem) }
    }

    private def groupByGoal(goalBacklogItems: Seq[(GoalRow, Option[BacklogItemRow])]): Seq[(GoalRow, Seq[BacklogItemRow])] = {
      goalBacklogItems.groupBy { case (goal, _) => goal }.map { case (goal, pairs) => (goal, pairs.flatMap(_._2)) }.toSeq
    }

    private def threadByUuid(uuid: UUID) = {
      ThreadTable.filter(_.uuid === uuid.toString)
    }

    private def threadsByParentId(goalId: UUID) = {
      ThreadTable.filter(_.goalId === goalId.toString)
    }

    private def weaveByUuid(uuid: UUID) = {
      WeaveTable.filter(_.uuid === uuid.toString)
    }

    private def weavesByParentId(goalId: UUID) = {
      WeaveTable.filter(_.goalId === goalId.toString)
    }

    private def laserDonutByUuid(uuid: UUID) = {
      LaserDonutTable.filter(_.uuid === uuid.toString)
    }

    private def laserDonutsByParentId(goalId: UUID) = {
      LaserDonutTable.filter(_.goalId === goalId.toString).sortBy(_.order)
    }

    private def portionByUuid(uuid: UUID) = {
      PortionTable.filter(_.uuid === uuid.toString)
    }

    private def portionsByParentId(laserDonutId: UUID) = {
      PortionTable.filter(_.laserDonutId === laserDonutId.toString).sortBy(_.order)
    }

    private def todoByUuid(uuid: UUID) = {
      TodoTable.filter(_.uuid === uuid.toString)
    }

    private def todosByParentId(portionId: UUID) = {
      TodoTable.filter(_.portionId === portionId.toString).sortBy(_.order)
    }

    private def hobbyByUuid(uuid: UUID) = {
      HobbyTable.filter(_.uuid === uuid.toString)
    }

    private def hobbiesByParentId(goalId: UUID) = {
      HobbyTable.filter(_.goalId === goalId.toString)
    }
  }
}
