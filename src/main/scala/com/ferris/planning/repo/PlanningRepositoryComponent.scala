package com.ferris.planning.repo

import java.sql.{Date, Timestamp}
import java.time.LocalDate
import java.util.UUID

import cats.data._
import cats.implicits._
import com.ferris.planning.command.Commands._
import com.ferris.planning.config.{DefaultPlanningServiceConfig, PlanningServiceConfig}
import com.ferris.planning.db.conversions.DomainConversions
import com.ferris.planning.db.TablesComponent
import com.ferris.planning.model.Model._
import com.ferris.planning.scheduler.LifeSchedulerComponent
import com.ferris.planning.service.exceptions.Exceptions.{InvalidOneOffsUpdateException, _}
import com.ferris.utils.FerrisImplicits._
import com.ferris.utils.TimerComponent
import com.rms.miu.slickcats.DBIOInstances._
import slick.dbio.Effect.Write
import slick.lifted.QueryBase
import slick.sql.FixedSqlAction

import scala.concurrent.{ExecutionContext, Future}

trait PlanningRepositoryComponent {

  val repo: PlanningRepository

  trait PlanningRepository {
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
    def createOneOff(creation: CreateOneOff): Future[OneOff]
    def createScheduledOneOff(creation: CreateScheduledOneOff): Future[ScheduledOneOff]
    def createPyramidOfImportance(pyramid: UpsertPyramidOfImportance): Future[PyramidOfImportance]

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
    def updateTodos(parentId: UUID, update: UpdateList): Future[Seq[Todo]]
    def updateHobby(uuid: UUID, update: UpdateHobby): Future[Hobby]
    def updateOneOff(uuid: UUID, update: UpdateOneOff): Future[OneOff]
    def updateOneOffs(update: UpdateList): Future[Seq[OneOff]]
    def updateScheduledOneOff(uuid: UUID, update: UpdateScheduledOneOff): Future[ScheduledOneOff]
    def refreshPyramidOfImportance(): Future[Boolean]
    def refreshPortion(): Future[Boolean]

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
    def getTodos(parentId: UUID): Future[Seq[Todo]]
    def getHobbies: Future[Seq[Hobby]]
    def getHobbies(goalId: UUID): Future[Seq[Hobby]]
    def getOneOffs: Future[Seq[OneOff]]
    def getScheduledOneOffs(date: Option[LocalDate]): Future[Seq[ScheduledOneOff]]

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
    def getOneOff(uuid: UUID): Future[Option[OneOff]]
    def getScheduledOneOff(uuid: UUID): Future[Option[ScheduledOneOff]]
    def getPyramidOfImportance: Future[Option[PyramidOfImportance]]

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
    def deleteOneOff(uuid: UUID): Future[Boolean]
    def deleteScheduledOneOff(uuid: UUID): Future[Boolean]
    def deletePyramidOfImportance(): Future[Boolean]
  }
}

trait SqlPlanningRepositoryComponent extends PlanningRepositoryComponent {
  this: TablesComponent with TimerComponent with LifeSchedulerComponent =>

  lazy val tableConversions = new DomainConversions(tables)
  import tableConversions.tables._
  import tableConversions.tables.profile.api._
  import tableConversions._

  implicit val repoEc: ExecutionContext
  override val repo = new SqlPlanningRepository(DefaultPlanningServiceConfig.apply)
  val db: tables.profile.api.Database

  class SqlPlanningRepository(config: PlanningServiceConfig) extends PlanningRepository {

    // Create endpoints
    override def createBacklogItem(creation: CreateBacklogItem): Future[BacklogItem] = {
      val row = BacklogItemRow(
        id = 0L,
        uuid = UUID.randomUUID,
        summary = creation.summary,
        description = creation.description,
        `type` = creation.`type`.dbValue,
        createdOn = timer.timestampOfNow,
        lastModified = None
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
        question = creation.question,
        createdOn = timer.timestampOfNow,
        lastModified = None
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
        finishDate = Date.valueOf(creation.startDate.plusYears(1)),
        createdOn = timer.timestampOfNow,
        lastModified = None
      )
      val action = (YearTable returning YearTable.map(_.id) into ((year, id) => year.copy(id = id))) += row
      db.run(action) map (row => row.asYear)
    }

    override def createTheme(creation: CreateTheme): Future[Theme] = {
      val row = ThemeRow(
        id = 0L,
        uuid = UUID.randomUUID,
        yearId = creation.yearId,
        name = creation.name,
        createdOn = timer.timestampOfNow,
        lastModified = None
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
          status = creation.status.dbValue,
          graduation = creation.graduation.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None
        )
        (GoalTable returning GoalTable.map(_.id) into ((goal, id) => goal.copy(id = id))) += row
      }

      val action = for {
        goal <- insertGoalAction(creation)
        backlogItems <- getBacklogItemsAction(creation.backlogItems)
        _ <- insertGoalBacklogItemsAction(goal.id, backlogItems.map(_.id))
        goalSkills <- insertGoalSkillsAction(goal.id, creation.associatedSkills)
      } yield {
        (goal, backlogItems, goalSkills.zip(creation.associatedSkills).map {
          case (goalSkill, associatedSkill) => (goalSkill, associatedSkill.skillId)
        }).asGoal
      }
      db.run(action)
    }

    override def createThread(creation: CreateThread): Future[Thread] = {
      def insertThreadAction(creation: CreateThread): DBIO[ThreadRow] = {
        val row = ThreadRow(
          id = 0L,
          uuid = UUID.randomUUID,
          goalId = creation.goalId,
          summary = creation.summary,
          description = creation.description,
          performance = creation.performance.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (ThreadTable returning ThreadTable.map(_.id) into ((thread, id) => thread.copy(id = id))) += row
      }

      val action = for {
        thread <- insertThreadAction(creation)
        threadSkills <- insertThreadSkillsAction(thread.id, creation.associatedSkills)
      } yield {
        (thread, threadSkills.zip(creation.associatedSkills).map {
          case (threadSkill, associatedSkill) => (threadSkill, associatedSkill.skillId)
        }).asThread
      }
      db.run(action)
    }

    override def createWeave(creation: CreateWeave): Future[Weave] = {
      def insertWeaveAction(creation: CreateWeave): DBIO[WeaveRow] = {
        val row = WeaveRow(
          id = 0L,
          uuid = UUID.randomUUID,
          goalId = creation.goalId,
          summary = creation.summary,
          description = creation.description,
          status = creation.status.dbValue,
          `type` = creation.`type`.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (WeaveTable returning WeaveTable.map(_.id) into ((weave, id) => weave.copy(id = id))) += row
      }
      val action = for {
        weave <- insertWeaveAction(creation)
        weaveSkills <- insertWeaveSkillsAction(weave.id, creation.associatedSkills)
      } yield {
        (weave, weaveSkills.zip(creation.associatedSkills).map {
          case (weaveSkill, associatedSkill) => (weaveSkill, associatedSkill.skillId)
        }).asWeave
      }
      db.run(action)
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
          `type` = creation.`type`.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
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
          status = creation.status.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (PortionTable returning PortionTable.map(_.id) into ((portion, id) => portion.copy(id = id))) += row
      }.transactionally
      db.run(action) map (row => row.asPortion)
    }

    override def createTodo(creation: CreateTodo): Future[Todo] = {
      def insertTodoAction(creation: CreateTodo, existingTodos: Seq[TodoRow]): DBIO[TodoRow] = {
        val row = TodoRow(
          id = 0L,
          uuid = UUID.randomUUID,
          parentId = creation.parentId,
          description = creation.description,
          order = existingTodos.lastOption.map(_.order + 1).getOrElse(1),
          isDone = false,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (TodoTable returning TodoTable.map(_.id) into ((todo, id) => todo.copy(id = id))) += row
      }
      val action = for {
        existingTodos <- todosByParentId(creation.parentId).result
        todo <- insertTodoAction(creation, existingTodos)
        todoSkills <- insertTodoSkillsAction(todo.id, creation.associatedSkills)
      } yield {
        (todo, todoSkills.zip(creation.associatedSkills).map {
          case (todoSkill, associatedSkill) => (todoSkill, associatedSkill.skillId)
        }).asTodo
      }
      db.run(action)
    }

    override def createHobby(creation: CreateHobby): Future[Hobby] = {
      def insertHobbyAction(creation: CreateHobby): DBIO[HobbyRow] = {
        val row = HobbyRow(
          id = 0L,
          uuid = UUID.randomUUID,
          goalId = creation.goalId,
          summary = creation.summary,
          description = creation.description,
          frequency = creation.frequency.dbValue,
          `type` = creation.`type`.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (HobbyTable returning HobbyTable.map(_.id) into ((hobby, id) => hobby.copy(id = id))) += row
      }
      val action = for {
        hobby <- insertHobbyAction(creation)
        hobbySkills <- insertHobbySkillsAction(hobby.id, creation.associatedSkills)
      } yield {
        (hobby, hobbySkills.zip(creation.associatedSkills).map {
          case (hobbySkill, associatedSkill) => (hobbySkill, associatedSkill.skillId)
        }).asHobby
      }
      db.run(action)
    }

    override def createOneOff(creation: CreateOneOff): Future[OneOff] = {
      def insertOneOffAction(creation: CreateOneOff, existingOneOffs: Seq[OneOffRow]): DBIO[OneOffRow] = {
        val row = OneOffRow(
          id = 0L,
          uuid = UUID.randomUUID,
          goalId = creation.goalId,
          description = creation.description,
          estimate = creation.estimate,
          order = existingOneOffs.lastOption.map(_.order + 1).getOrElse(1),
          status = creation.status.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (OneOffTable returning OneOffTable.map(_.id) into ((oneOff, id) => oneOff.copy(id = id))) += row
      }
      val action = for {
        existingOneOffs <- OneOffTable.result
        oneOff <- insertOneOffAction(creation, existingOneOffs)
        oneOffSkills <- insertOneOffSkillsAction(oneOff.id, creation.associatedSkills)
      } yield {
        (oneOff, oneOffSkills.zip(creation.associatedSkills).map {
          case (oneOffSkill, associatedSkill) => (oneOffSkill, associatedSkill.skillId)
        }).asOneOff
      }
      db.run(action)
    }

    override def createScheduledOneOff(creation: CreateScheduledOneOff): Future[ScheduledOneOff] = {
      def insertScheduledOneOffAction(creation: CreateScheduledOneOff): DBIO[ScheduledOneOffRow] = {
        val row = ScheduledOneOffRow(
          id = 0L,
          uuid = UUID.randomUUID,
          occursOn = creation.occursOn.toTimestamp,
          goalId = creation.goalId,
          description = creation.description,
          estimate = creation.estimate,
          status = creation.status.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (ScheduledOneOffTable returning ScheduledOneOffTable.map(_.id) into ((oneOff, id) => oneOff.copy(id = id))) += row
      }
      val action = for {
        oneOff <- insertScheduledOneOffAction(creation)
        oneOffSkills <- insertScheduledOneOffSkillsAction(oneOff.id, creation.associatedSkills)
      } yield {
        (oneOff, oneOffSkills.zip(creation.associatedSkills).map {
          case (oneOffSkill, associatedSkill) => (oneOffSkill, associatedSkill.skillId)
        }).asScheduledOneOff
      }
      db.run(action)
    }

    override def createPyramidOfImportance(pyramid: UpsertPyramidOfImportance): Future[PyramidOfImportance] = {
      val insertions = for {
        (tier, tierNumber) <- pyramid.tiers.zipWithIndex
        laserDonutUuid <- tier.laserDonuts
      } yield {
        getLaserDonutAction(laserDonutUuid).flatMap {
          case Some(laserDonut) =>
            val row = ScheduledLaserDonutRow(
              id = 0L,
              laserDonutId = laserDonut.id,
              tier = tierNumber + 1,
              isCurrent = false
            )
            ((ScheduledLaserDonutTable returning ScheduledLaserDonutTable.map(_.id) into ((row, id) => row.copy(id = id))) += row).map((_, laserDonut))
          case None => DBIO.failed(LaserDonutNotFoundException(s"no laser-donut with the UUID $laserDonutUuid exists"))
        }
      }
      val action = DBIO.sequence(insertions)
      db.run(action).map(_.asPyramid)
    }

    // Update endpoints
    override def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem): Future[BacklogItem] = {
      val query = backlogItemByUuid(uuid).map(item => (item.summary, item.description, item.`type`, item.lastModified))
      val action = getBacklogItemAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.summary.getOrElse(old.summary), update.description.getOrElse(old.description),
            UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`), Some(timer.timestampOfNow))
            .andThen(getBacklogItemAction(uuid).map(_.head))
        } getOrElse DBIO.failed(BacklogItemNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asBacklogItem)
    }

    override def updateEpoch(uuid: UUID, update: UpdateEpoch): Future[Epoch] = {
      val query = epochByUuid(uuid).map(epoch => (epoch.name, epoch.totem, epoch.question, epoch.lastModified))
      val action = getEpochAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.name.getOrElse(old.name), update.totem.getOrElse(old.totem), update.question.getOrElse(old.question),
            Some(timer.timestampOfNow))
            .andThen(getEpochAction(uuid).map(_.head))
        } getOrElse DBIO.failed(EpochNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asEpoch)
    }

    override def updateYear(uuid: UUID, update: UpdateYear): Future[Year] = {
      val query = yearByUuid(uuid).map(epoch => (epoch.epochId, epoch.startDate, epoch.finishDate, epoch.lastModified))
      val action = getYearAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.epochId, old.epochId), UpdateDate.keepOrReplace(update.startDate,
            old.startDate), UpdateDate.keepOrReplace(update.startDate.map(_.plusYears(1)), old.finishDate), Some(timer.timestampOfNow))
            .andThen(getYearAction(uuid).map(_.head))
        } getOrElse DBIO.failed(YearNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asYear)
    }

    override def updateTheme(uuid: UUID, update: UpdateTheme): Future[Theme] = {
      val query = themeByUuid(uuid).map(theme => (theme.yearId, theme.name, theme.lastModified))
      val action = getThemeAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.yearId, old.yearId), update.name.getOrElse(old.name), Some(timer.timestampOfNow))
            .andThen(getThemeAction(uuid).map(_.head))
        } getOrElse DBIO.failed(ThemeNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asTheme)
    }

    override def updateGoal(uuid: UUID, update: UpdateGoal): Future[Goal] = {
      def updateGoal(uuid: UUID, update: UpdateGoal, old: GoalRow): DBIO[Int] = {
        goalByUuid(uuid).map(goal => (goal.themeId, goal.summary, goal.description, goal.status,
          goal.graduation, goal.lastModified))
          .update(UpdateId.keepOrReplace(update.themeId, old.themeId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.status, old.status),
            UpdateTypeEnum.keepOrReplace(update.graduation, old.graduation), Some(timer.timestampOfNow))
      }

      def updateBacklogItems(goal: GoalRow) = update.backlogItems match {
        case Some(backlogItems) =>
          for {
            _ <- GoalBacklogItemTable.filter(_.goalId === goal.id).delete
            newBacklogItems <- getBacklogItemsAction(backlogItems)
            result <- insertGoalBacklogItemsAction(goal.id, newBacklogItems.map(_.id))
          } yield result
        case None => DBIO.successful(Seq.empty[GoalBacklogItemRow])
      }

      def updateAssociatedSkills(goal: GoalRow) = update.associatedSkills match {
        case Some(associatedSkills) =>
          for {
            _ <- GoalSkillTable.filter(_.goalId === goal.id).delete
            result <- insertGoalSkillsAction(goal.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(GoalSkillRow, UUID)])
      }

      val action = (for {
        goalAndBacklog <- getGoalAction(uuid).map(_.getOrElse(throw GoalNotFoundException()))
        (old, _, _) = goalAndBacklog
        _ <- updateGoal(uuid, update, old)
        _ <- updateBacklogItems(old)
        _ <- updateAssociatedSkills(old)
        updatedGoal <- getGoalAction(uuid).map(_.head)
      } yield updatedGoal.asGoal).transactionally

      db.run(action)
    }

    override def updateThread(uuid: UUID, update: UpdateThread): Future[Thread] = {
      def updateThread(uuid: UUID, update: UpdateThread, old: ThreadRow) = {
        val (lastModified, lastPerformed) = getUpdateTimes(
          contentUpdate = update.goalId :: update.summary :: update.description :: Nil,
          statusUpdate = update.performance :: Nil
        )
        val query = threadByUuid(uuid).map(thread => (thread.goalId, thread.summary, thread.description, thread.performance,
          thread.lastModified, thread.lastPerformed))
        query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
          update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.performance, old.performance),
          lastModified, lastPerformed)
          .andThen(getThreadAction(uuid).map(_.head))
      }

      def updateAssociatedSkills(thread: ThreadRow) = update.associatedSkills match {
        case Some(associatedSkills) =>
          for {
            _ <- ThreadSkillTable.filter(_.threadId === thread.id).delete
            result <- insertThreadSkillsAction(thread.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(ThreadSkillRow, UUID)])
      }

      val action = (for {
        threadAndSkills <- getThreadAction(uuid).map(_.getOrElse(throw ThreadNotFoundException()))
        (old, _) = threadAndSkills
        _ <- updateThread(uuid, update, old)
        _ <- updateAssociatedSkills(old)
        updatedThread <- getThreadAction(uuid).map(_.head)
      } yield updatedThread.asThread).transactionally

      db.run(action)
    }

    override def updateWeave(uuid: UUID, update: UpdateWeave): Future[Weave] = {
      val (lastModified, lastPerformed) = getUpdateTimes(
        contentUpdate = update.goalId :: update.summary :: update.description :: update.`type` :: Nil,
        statusUpdate = update.status :: Nil
      )
      val query = weaveByUuid(uuid).map(weave => (weave.goalId, weave.summary, weave.description, weave.status, weave.`type`,
        weave.lastModified, weave.lastPerformed))
      val action = getWeaveAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.status, old.status),
            UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`), lastModified, lastPerformed)
            .andThen(getWeaveAction(uuid).map(_.head))
        } getOrElse DBIO.failed(WeaveNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asWeave)
    }

    override def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut): Future[LaserDonut] = {
      val (lastModified, lastPerformed) = getUpdateTimes(
        contentUpdate = update.goalId :: update.summary :: update.description :: update.milestone :: update.`type` :: Nil,
        statusUpdate = update.status :: Nil
      )
      val query = laserDonutByUuid(uuid).map(laserDonut => (laserDonut.goalId, laserDonut.summary, laserDonut.description,
        laserDonut.milestone, laserDonut.status, laserDonut.`type`, laserDonut.lastModified, laserDonut.lastPerformed))
      val action = getLaserDonutAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateId.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), update.milestone.getOrElse(old.milestone),
            UpdateTypeEnum.keepOrReplace(update.status, old.status), UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`),
            lastModified, lastPerformed)
            .andThen(getLaserDonutAction(uuid).map(_.head))
        } getOrElse DBIO.failed(LaserDonutNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asLaserDonut)
    }

    override def updatePortion(uuid: UUID, update: UpdatePortion): Future[Portion] = {
      def updateLaserDonutStatus(uuid: UUID) = {
        for {
          portions <- portionsByParentId(uuid).result
          update <- LaserDonutTable.filter(_.uuid === uuid.toString).map(_.status)
            .update(getStatusSummary(portions.map(portion => Statuses.withName(portion.status))).dbValue).map(_ > 0)
        } yield update
      }

      val (lastModified, lastPerformed) = getUpdateTimes(
        contentUpdate = update.laserDonutId :: update.summary :: Nil,
        statusUpdate = update.status :: Nil
      )
      val query = portionByUuid(uuid).map(portion => (portion.laserDonutId, portion.summary, portion.status,
        portion.lastModified, portion.lastPerformed))
      val action = getPortionAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          for {
            _ <- query.update(UpdateId.keepOrReplace(update.laserDonutId, old.laserDonutId), update.summary.getOrElse(old.summary),
              UpdateTypeEnum.keepOrReplace(update.status, old.status), lastModified, lastPerformed)
            updatedPortion <- getPortionAction(uuid).map(_.head)
            _ <- updateLaserDonutStatus(UUID.fromString(updatedPortion.laserDonutId))
          } yield updatedPortion
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
      def updatePortionStatus(uuid: UUID) = {
        for {
          todos <- todosByParentId(uuid).result
          update <- PortionTable.filter(_.uuid === uuid.toString).map(_.status)
            .update(getOutcomeSummary(todos.map(_.isDone: Boolean)).dbValue).map(_ > 0)
        } yield update
      }

      val (lastModified, lastPerformed) = getUpdateTimes(
        contentUpdate = update.parentId :: update.description :: Nil,
        statusUpdate = update.isDone :: Nil
      )
      val query = todoByUuid(uuid).map(todo => (todo.parentId, todo.description, todo.isDone, todo.lastModified, todo.lastPerformed))
      val action = getTodoAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          for {
            _ <- query.update(UpdateId.keepOrReplace(update.parentId, old.parentId), update.description.getOrElse(old.description),
              UpdateBoolean.keepOrReplace(update.isDone, old.isDone), lastModified, lastPerformed)
            updatedTodo <- getTodoAction(uuid).map(_.head)
            _ <- updatePortionStatus(UUID.fromString(updatedTodo.parentId))
          } yield updatedTodo
        } getOrElse DBIO.failed(TodoNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asTodo)
    }

    override def updateTodos(parentId: UUID, update: UpdateList): Future[Seq[Todo]] = {
      def getTodosAction(uuids: Seq[String]) = {
        TodoTable.filter(_.uuid inSet uuids).sortBy(_.order).result
      }
      val action = todosByParentId(parentId).result.flatMap { todos =>
        if (todos.size <= update.reordered.size) {
          val todoIds = todos.map(_.uuid)
          update.reordered.filterNot(id => todoIds.contains(id.toString)) match {
            case Nil => DBIO.sequence(update.reordered.zipWithIndex.map { case (uuid, index) =>
              todoByUuid(uuid).map(_.order).update(index + 1)
            }).andThen(getTodosAction(todoIds))
            case outliers => DBIO.failed(
              InvalidTodosUpdateException(s"the todos (${outliers.mkString(", ")}) do not belong to the portion $parentId")
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
      val (lastModified, lastPerformed) = getUpdateTimes(
        contentUpdate = update.goalId :: update.summary :: update.description :: update.frequency :: update.`type` :: Nil,
        statusUpdate = Nil
      )
      val query = hobbyByUuid(uuid).map(hobby => (hobby.goalId, hobby.summary, hobby.description, hobby.frequency,
        hobby.`type`, hobby.lastModified, hobby.lastPerformed))
      val action = getHobbyAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
            update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.frequency, old.frequency),
            UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`), lastModified, lastPerformed)
            .andThen(getHobbyAction(uuid).map(_.head))
        } getOrElse DBIO.failed(HobbyNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asHobby)
    }

    override def updateOneOff(uuid: UUID, update: UpdateOneOff): Future[OneOff] = {
      val (lastModified, lastPerformed) = getUpdateTimes(
        contentUpdate = update.goalId :: update.description :: update.estimate :: update.status :: Nil,
        statusUpdate = Nil
      )
      val query = oneOffByUuid(uuid).map(oneOff => (oneOff.goalId, oneOff.description, oneOff.estimate, oneOff.status,
        oneOff.lastModified, oneOff.lastPerformed))
      val action = getOneOffAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.description.getOrElse(old.description),
            update.estimate.getOrElse(old.estimate), UpdateTypeEnum.keepOrReplace(update.status, old.status), lastModified, lastPerformed)
            .andThen(getOneOffAction(uuid).map(_.head))
        } getOrElse DBIO.failed(OneOffNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asOneOff)
    }

    override def updateOneOffs(update: UpdateList): Future[Seq[OneOff]] = {
      def getSortedOneOffs(uuids: Seq[String]): DBIO[Seq[OneOffRow]] =
        OneOffTable.filter(_.uuid inSet uuids).sortBy(_.order).result

      def updateOrdering(oneOffIds: Seq[String]): DBIO[Seq[OneOffRow]] =
        update.reordered.filterNot(id => oneOffIds.contains(id.toString)) match {
          case Nil => DBIO.sequence(update.reordered.zipWithIndex.map { case (uuid, index) =>
            oneOffByUuid(uuid).map(_.order).update(index + 1)
          }).andThen(getSortedOneOffs(oneOffIds))
          case outliers => DBIO.failed(
            InvalidOneOffsUpdateException(s"the one-offs (${outliers.mkString(", ")}) do not exist")
          )
        }

      val action = OneOffTable.result.flatMap { oneOffs =>
        if (oneOffs.size == update.reordered.size) {
          updateOrdering(oneOffs.map(_.uuid))
        }
        else DBIO.failed(
          InvalidOneOffsUpdateException("the length of the update list should be the same as the total number of one-offs")
        )
      }

      db.run(action).map(_.map(_.asOneOff))
    }

    override def updateScheduledOneOff(uuid: UUID, update: UpdateScheduledOneOff): Future[ScheduledOneOff] = {
      val (lastModified, lastPerformed) = getUpdateTimes(
        contentUpdate = update.goalId :: update.occursOn :: update.description :: update.estimate :: update.status :: Nil,
        statusUpdate = Nil
      )
      val query = scheduledOneOffByUuid(uuid).map(oneOff => (oneOff.goalId, oneOff.occursOn, oneOff.description, oneOff.estimate, oneOff.status,
        oneOff.lastModified, oneOff.lastPerformed))
      val action = getScheduledOneOffAction(uuid).flatMap { maybeObj =>
        maybeObj.map { (old: ScheduledOneOffRow) =>
          query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), UpdateDateTime.keepOrReplace(update.occursOn, old.occursOn),
            update.description.getOrElse(old.description), update.estimate.getOrElse(old.estimate), UpdateTypeEnum.keepOrReplace(update.status, old.status),
            lastModified, lastPerformed).andThen(getScheduledOneOffAction(uuid).map(_.head))
        } getOrElse DBIO.failed(ScheduledOneOffNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asScheduledOneOff)
    }

    override def refreshPyramidOfImportance(): Future[Boolean] = {
      def getScheduledLaserDonuts = {
        (for {
          scheduledLaserDonut <- ScheduledLaserDonutTable
          laserDonut <- LaserDonutTable if laserDonut.id === scheduledLaserDonut.laserDonutId
          portion <- PortionTable if portion.laserDonutId === laserDonut.uuid
          todo <- TodoTable if todo.parentId === portion.uuid
        } yield {
          (scheduledLaserDonut, laserDonut, portion, todo)
        }).result.map(_.asScheduledLaserDonuts.sortBy(_.id))
      }

      def scheduledLaserDonutRow(scheduledLaserDonut: ScheduledLaserDonut, isCurrent: Boolean): ScheduledLaserDonutRow = {
        ScheduledLaserDonutRow(
          id = 0L,
          laserDonutId = scheduledLaserDonut.id,
          tier = scheduledLaserDonut.tier,
          isCurrent = isCurrent
        )
      }

      def scheduledPyramid(laserDonuts: Seq[ScheduledLaserDonut], currentActivity: Option[CurrentActivityRow]) = {
        ScheduledPyramid(laserDonuts,
          currentActivity.map(_.currentLaserDonut),
          currentActivity.map(_.currentPortion),
          currentActivity.map(_.lastWeeklyUpdate.toLocalDateTime)
        )
      }

      def insertScheduledLaserDonuts(scheduledLaserDonutsRows: Seq[ScheduledLaserDonutRow]): DBIO[Int] = {
        (ScheduledLaserDonutTable ++= scheduledLaserDonutsRows).map(_.getOrElse(0))
      }

      val action: DBIO[Boolean] = (for {
        currentActivity <- CurrentActivityTable.result.headOption
        originalSchedule <- getScheduledLaserDonuts
        _ <- ScheduledLaserDonutTable.delete
        currentPyramid = scheduledPyramid(originalSchedule, currentActivity)
        refreshedPyramid = lifeScheduler.refreshPyramid(currentPyramid)
        nextLaserDonutId = refreshedPyramid.currentLaserDonut
        nextPortionId = refreshedPyramid.currentPortion
        scheduledLaserDonutRows = refreshedPyramid.laserDonuts.map(donut => scheduledLaserDonutRow(donut, nextLaserDonutId.contains(donut.id)))
        laserDonutsInsertion <- insertScheduledLaserDonuts(scheduledLaserDonutRows)
        currentActivityUpdate <- currentActivityUpdate(nextLaserDonutId, nextPortionId)
      } yield {
        (laserDonutsInsertion :: currentActivityUpdate :: Nil).forall(_ > 0)
      }).transactionally

      db.run(action)
    }

    override def refreshPortion(): Future[Boolean] = {
      def getScheduledPortions = (for {
        currentActivity <- CurrentActivityTable
        currentLaserDonut <- LaserDonutTable if currentLaserDonut.id === currentActivity.currentLaserDonut
        portion <- PortionTable if portion.laserDonutId === currentLaserDonut.uuid
        todo <- TodoTable if todo.parentId === portion.uuid
      } yield {
        (portion, todo)
      }).result.map(_.asScheduledPortions.sortBy(_.id))

      def getCurrentScheduledPortion = (for {
        currentActivity <- CurrentActivityTable
        currentPortion <- PortionTable if currentPortion.id === currentActivity.currentPortion
        todo <- TodoTable if todo.parentId === currentPortion.uuid
      } yield {
        (currentPortion, todo)
      }).result.map(_.asScheduledPortions.headOption)

      val action: DBIO[Boolean] = (for {
        scheduledPortions <- getScheduledPortions
        currentActivity <- CurrentActivityTable.result.headOption
        currentPortion <- getCurrentScheduledPortion
        nextPortion = lifeScheduler.decideNextPortion(scheduledPortions, currentPortion, currentActivity.map(_.lastDailyUpdate.toLocalDateTime))
        update <- currentActivityUpdate(currentLaserDonutId = None, currentPortionId = nextPortion.map(_.id))
      } yield update > 0).transactionally

      db.run(action)
    }

    private def currentActivityUpdate(currentLaserDonutId: Option[Long], currentPortionId: Option[Long]): DBIO[Int] = {
      (currentLaserDonutId, currentPortionId) match {
        case (Some(laserDonutId), Some(portionId)) => CurrentActivityTable.filter(_.id === 1L).map(activity => (activity.currentLaserDonut, activity.currentPortion, activity.lastWeeklyUpdate))
          .update((laserDonutId, portionId, timer.timestampOfNow))
        case (Some(laserDonutId), None) => CurrentActivityTable.filter(_.id === 1L).map(activity => (activity.currentLaserDonut, activity.lastWeeklyUpdate))
          .update((laserDonutId, timer.timestampOfNow))
        case (None, Some(portionId)) => CurrentActivityTable.filter(_.id === 1L).map(activity => (activity.currentPortion, activity.lastDailyUpdate))
          .update((portionId, timer.timestampOfNow))
        case _ => DBIO.successful(0)
      }
    }

    // Get endpoints
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

    override def getCurrentLaserDonut: Future[Option[LaserDonut]] = {
      val action = (for {
        currentActivity <- CurrentActivityTable
        laserDonutRow <- LaserDonutTable if laserDonutRow.id === currentActivity.currentLaserDonut
      } yield laserDonutRow).result.headOption
      db.run(action).map(_.map(_.asLaserDonut))
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

    override def getCurrentPortion: Future[Option[Portion]] = {
      val action = (for {
        currentActivity <- CurrentActivityTable
        portionRow <- PortionTable if portionRow.id === currentActivity.currentPortion
      } yield portionRow).result.headOption
      db.run(action).map(_.map(_.asPortion))
    }

    override def getTodos: Future[Seq[Todo]] = {
      db.run(TodoTable.result.map(_.map(_.asTodo)))
    }

    override def getTodos(parentId: UUID): Future[Seq[Todo]] = {
      db.run(todosByParentId(parentId).result.map(_.map(_.asTodo)))
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

    override def getOneOffs: Future[Seq[OneOff]] = {
      db.run(OneOffTable.sortBy(_.order).result.map(_.map(_.asOneOff)))
    }

    override def getOneOff(uuid: UUID): Future[Option[OneOff]] = {
      db.run(getOneOffAction(uuid).map(_.map(_.asOneOff)))
    }

    override def getScheduledOneOffs(date: Option[LocalDate]): Future[Seq[ScheduledOneOff]] = {
      val retrieval = date.map { chosenDate =>
        ScheduledOneOffTable.result
          .map(_.filter(_.occursOn.toLocalDateTime.toLocalDate == chosenDate))
      }.getOrElse(ScheduledOneOffTable.result)
      val action = retrieval.map(_.map(_.asScheduledOneOff))
      db.run(action)
    }

    override def getScheduledOneOff(uuid: UUID): Future[Option[ScheduledOneOff]] = {
      db.run(getScheduledOneOffAction(uuid).map(_.map(_.asScheduledOneOff)))
    }

    override def getPyramidOfImportance: Future[Option[PyramidOfImportance]] = {
      db.run(getPyramidOfImportanceAction)
    }

    // Delete endpoints
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

    override def deleteOneOff(uuid: UUID): Future[Boolean] = {
      val action = oneOffByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteScheduledOneOff(uuid: UUID): Future[Boolean] = {
      val action = scheduledOneOffByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deletePyramidOfImportance(): Future[Boolean] = {
      val action = ScheduledLaserDonutTable.delete
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

    private def insertGoalSkillsAction(goalId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[GoalSkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        goalSkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          GoalSkillRow(
            goalId = goalId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- GoalSkillTable ++= goalSkills
      } yield goalSkills
    }

    private def insertThreadSkillsAction(threadId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[ThreadSkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        threadSkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          ThreadSkillRow(
            threadId = threadId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- ThreadSkillTable ++= threadSkills
      } yield threadSkills
    }

    private def insertWeaveSkillsAction(weaveId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[WeaveSkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        weaveSkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          WeaveSkillRow(
            weaveId = weaveId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- WeaveSkillTable ++= weaveSkills
      } yield weaveSkills
    }

    private def insertTodoSkillsAction(todoId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[TodoSkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        todoSkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          TodoSkillRow(
            todoId = todoId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- TodoSkillTable ++= todoSkills
      } yield todoSkills
    }

    private def insertHobbySkillsAction(hobbyId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[HobbySkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        hobbySkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          HobbySkillRow(
            hobbyId = hobbyId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- HobbySkillTable ++= hobbySkills
      } yield hobbySkills
    }

    private def insertOneOffSkillsAction(oneOffId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[OneOffSkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        oneOffSkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          OneOffSkillRow(
            oneOffId = oneOffId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- OneOffSkillTable ++= oneOffSkills
      } yield oneOffSkills
    }

    private def insertScheduledOneOffSkillsAction(oneOffId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[ScheduledOneOffSkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        oneOffSkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          ScheduledOneOffSkillRow(
            scheduledOneOffId = oneOffId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- ScheduledOneOffSkillTable ++= oneOffSkills
      } yield oneOffSkills
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

    private def getGoalsAction: DBIO[Seq[(GoalRow, Seq[BacklogItemRow], Seq[(GoalSkillRow, UUID)])]] = {
      goalsWithExtras.map(groupByGoal(_))
    }

    private def getGoalAction(uuid: UUID): DBIO[Option[(GoalRow, Seq[BacklogItemRow], Seq[(GoalSkillRow, UUID)])]] = {
      goalWithExtrasByUuid(uuid).map(groupByGoal(_).headOption)
    }

    private def getThreadAction(uuid: UUID) = {
      threadWithExtrasByUuid(uuid).map(groupByThread(_).headOption)
    }

    private def getWeaveAction(uuid: UUID) = {
      weaveWithExtrasByUuid(uuid).map(groupByThread(_).headOption)
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

    private def getOneOffAction(uuid: UUID) = {
      oneOffByUuid(uuid).result.headOption
    }

    private def getScheduledOneOffAction(uuid: UUID) = {
      scheduledOneOffByUuid(uuid).result.headOption
    }

    private def getSkillsAction(uuids: Seq[UUID]) = {
      skillsByUuid(uuids).result
    }

    private def getPyramidOfImportanceAction = {
      (for {
        pyramidRow <- ScheduledLaserDonutTable
        laserDonutRow <- LaserDonutTable if laserDonutRow.id === pyramidRow.laserDonutId
      } yield (pyramidRow, laserDonutRow)).result.map(rows => if (rows.nonEmpty) Some(rows.asPyramid) else None)
    }

    // Queries
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

    private def goalWithExtrasByUuid(uuid: UUID) = {
      goalsWithExtras.map(_.filter { case (goal, _, _) => goal.uuid === uuid.toString })
    }

    private def goalsWithExtras = {
      GoalTable
        .joinLeft(GoalBacklogItemTable)
        .on(_.id === _.goalId)
        .joinLeft(BacklogItemTable)
        .on { case ((_, goalBacklogItem), backlogItem) => goalBacklogItem.map(_.backlogItemId).getOrElse(-1L) === backlogItem.id }
        .joinLeft(GoalSkillTable)
        .on(_._1._1.id === _.skillId)
        .joinLeft(SkillTable)
        .on { case ((((_, _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .map { case ((((goal, _), backlogItem), skillLink), skill) => (goal, backlogItem, skillLink, skill) }
        .result.map {
          _.collect {
            case (goal, backlogItem, skillLink, skill) =>
              val skillTuple = (skillLink, skill) match {
                case (Some(link), Some(skl)) => Some((link, skl))
                case _ => None
              }
              (goal, backlogItem, skillTuple)
            }
          }
    }

    private def groupByGoal(goalsWithExtras: Seq[(GoalRow, Option[BacklogItemRow], Option[(GoalSkillRow, SkillRow)])]): Seq[(GoalRow, Seq[BacklogItemRow], Seq[(GoalSkillRow, UUID)])] = {
      goalsWithExtras.groupBy { case (goal, _, _) => goal }
        .map { case (goal, links) =>
          (goal, links.flatMap(_._2), links.flatMap(_._3.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))))
        }.toSeq
    }

    private def threadByUuid(uuid: UUID) = {
      ThreadTable.filter(_.uuid === uuid.toString)
    }

    private def threadsByParentId(goalId: UUID) = {
      ThreadTable.filter(_.goalId === goalId.toString)
    }

    private def threadWithExtrasByUuid(uuid: UUID) = {
      threadsWithExtras.map(_.filter { case (thread, _) => thread.uuid == uuid.toString })
    }

    private def threadsWithExtras = {
      ThreadTable
        .joinLeft(ThreadSkillTable)
        .on(_.id === _.threadId)
        .joinLeft(SkillTable)
        .on { case ((_, skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .map { case ((thread, skillLink), skill) => (thread, skillLink, skill) }
        .result.map {
        _.collect {
          case (thread, skillLink, skill) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (thread, skillTuple)
        }
      }
    }

    private def groupByThread(threadsWithExtras: Seq[(ThreadRow, Option[(ThreadSkillRow, SkillRow)])]): Seq[(ThreadRow, Seq[(ThreadSkillRow, UUID)])] = {
      threadsWithExtras.groupBy { case (thread, _) => thread }
        .map { case (thread, links) =>
          (thread, links.flatMap(_._2.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))))
        }.toSeq
    }

    private def weaveByUuid(uuid: UUID) = {
      WeaveTable.filter(_.uuid === uuid.toString)
    }

    private def weavesByParentId(goalId: UUID) = {
      WeaveTable.filter(_.goalId === goalId.toString)
    }

    private def weaveWithExtrasByUuid(uuid: UUID) = {
      weavesWithExtras.map(_.filter { case (weave, _) => weave.uuid == uuid.toString })
    }

    private def weavesWithExtras = {
      WeaveTable
        .joinLeft(WeaveSkillTable)
        .on(_.id === _.weaveId)
        .joinLeft(SkillTable)
        .on { case ((_, skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .map { case ((weave, skillLink), skill) => (weave, skillLink, skill) }
        .result.map {
        _.collect {
          case (weave, skillLink, skill) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (weave, skillTuple)
        }
      }
    }

    private def groupByWeave(weavesWithExtras: Seq[(WeaveRow, Option[(WeaveSkillRow, SkillRow)])]): Seq[(WeaveRow, Seq[(WeaveSkillRow, UUID)])] = {
      weavesWithExtras.groupBy { case (weave, _) => weave }
        .map { case (weave, links) =>
          (weave, links.flatMap(_._2.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))))
        }.toSeq
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

    private def todosByParentId(parentId: UUID) = {
      TodoTable.filter(_.parentId === parentId.toString).sortBy(_.order)
    }

    private def hobbyByUuid(uuid: UUID) = {
      HobbyTable.filter(_.uuid === uuid.toString)
    }

    private def hobbiesByParentId(goalId: UUID) = {
      HobbyTable.filter(_.goalId === goalId.toString)
    }

    private def oneOffByUuid(uuid: UUID) = {
      OneOffTable.filter(_.uuid === uuid.toString)
    }

    private def scheduledOneOffByUuid(uuid: UUID) = {
      ScheduledOneOffTable.filter(_.uuid === uuid.toString)
    }

    private def skillsByUuid(uuids: Seq[UUID]) = {
      SkillTable.filter(_.uuid inSet uuids.map(_.toString))
    }

    private def getUpdateTimes(contentUpdate: Seq[Option[Any]], statusUpdate: Seq[Option[Any]]): (Option[Timestamp], Option[Timestamp]) = {
      val now = timer.timestampOfNow
      val lastModified = if (contentUpdate.exists(_.nonEmpty)) Some(now) else None
      val lastPerformed = if (statusUpdate.exists(_.nonEmpty)) Some(now) else None
      (lastModified, lastPerformed)
    }

    private def getStatusSummary(statuses: Seq[Statuses.Status]): Statuses.Status = {
      if (statuses.forall(_ == Statuses.Complete)) Statuses.Complete
      else if (statuses.contains(Statuses.InProgress) || statuses.contains(Statuses.Complete)) Statuses.InProgress
      else Statuses.Planned
    }

    private def getOutcomeSummary(outcomes: Seq[Boolean]): Statuses.Status = {
      if (outcomes.forall(_ == true)) Statuses.Complete
      else if (outcomes.forall(_ == false)) Statuses.Planned
      else Statuses.InProgress
    }
  }
}
