package com.ferris.planning.repo

import java.sql.{Date, Timestamp}
import java.time.LocalDate
import java.util.UUID

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
import slick.dbio.DBIOAction
import slick.dbio.Effect.Read

import scala.concurrent.{ExecutionContext, Future}

trait PlanningRepositoryComponent {

  val repo: PlanningRepository

  trait PlanningRepository {
    def createRelationship(creation: CreateRelationship): Future[Relationship]
    def createMission(creation: CreateMission): Future[Mission]
    def createSkillCategory(creation: CreateSkillCategory): Future[SkillCategory]
    def createSkill(creation: CreateSkill): Future[Skill]
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

    def updateRelationship(uuid: UUID, update: UpdateRelationship): Future[Relationship]
    def updateMission(uuid: UUID, update: UpdateMission): Future[Mission]
    def updateSkillCategory(uuid: UUID, update: UpdateSkillCategory): Future[SkillCategory]
    def updateSkill(uuid: UUID, update: UpdateSkill): Future[Skill]
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

    def getRelationships: Future[Seq[Relationship]]
    def getMissions: Future[Seq[Mission]]
    def getSkillCategories: Future[Seq[SkillCategory]]
    def getSkills: Future[Seq[Skill]]
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

    def getRelationship(uuid: UUID): Future[Option[Relationship]]
    def getMission(uuid: UUID): Future[Option[Mission]]
    def getSkillCategory(uuid: UUID): Future[Option[SkillCategory]]
    def getSkill(uuid: UUID): Future[Option[Skill]]
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

    def deleteRelationship(uuid: UUID): Future[Boolean]
    def deleteMission(uuid: UUID): Future[Boolean]
    def deleteSkillCategory(uuid: UUID): Future[Boolean]
    def deleteSkill(uuid: UUID): Future[Boolean]
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

  implicit val timestampOrdering: Ordering[Timestamp] = Ordering.by(_.toString)
  implicit val dateOrdering: Ordering[Date] = Ordering.by(_.toString)

  class SqlPlanningRepository(config: PlanningServiceConfig) extends PlanningRepository {

    // Create endpoints
    override def createSkillCategory(creation: CreateSkillCategory): Future[SkillCategory] = {
      val action = (for {
        parentCategory <- creation.parentCategory.map(getSkillCategoryAction).getOrElse(DBIO.successful(None))
        row = SkillCategoryRow(
          id = 0L,
          uuid = UUID.randomUUID,
          name = creation.name,
          parentCategory = parentCategory.map(_._1.id),
          createdOn = timer.timestampOfNow,
          lastModified = None
        )
        category <- (SkillCategoryTable returning SkillCategoryTable.map(_.id) into ((item, id) => item.copy(id = id))) += row
      } yield (category, parentCategory.map(_._1.uuid)).asSkillCategory).transactionally
      db.run(action)
    }

    override def createSkill(creation: CreateSkill): Future[Skill] = {
      val action = (for {
        parentCategory <- getSkillCategoryAction(creation.parentCategory).map(_.getOrElse(throw SkillCategoryNotFoundException()))
        row = SkillRow(
          id = 0L,
          uuid = UUID.randomUUID,
          name = creation.name,
          parentCategory = parentCategory._1.id,
          proficiency = creation.proficiency.dbValue,
          practisedHours = creation.practisedHours,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastApplied = None
        )
        category <- (SkillTable returning SkillTable.map(_.id) into ((item, id) => item.copy(id = id))) += row
      } yield (category, parentCategory._1.uuid).asSkill).transactionally
      db.run(action)
    }

    override def createRelationship(creation: CreateRelationship): Future[Relationship] = {
      val row = RelationshipRow(
        id = 0L,
        uuid = UUID.randomUUID,
        name = creation.name,
        category = creation.category.dbValue,
        traits = creation.traits.mkString(","),
        likes = creation.likes.mkString(","),
        dislikes = creation.dislikes.mkString(","),
        hobbies = creation.hobbies.mkString(","),
        createdOn = timer.timestampOfNow,
        lastModified = None,
        lastMeet = None
      )
      val action = (RelationshipTable returning RelationshipTable.map(_.id) into ((item, id) => item.copy(id = id))) += row
      db.run(action) map (row => row.asRelationship)
    }

    override def createMission(creation: CreateMission): Future[Mission] = {
      val row = MissionRow(
        id = 0L,
        uuid = UUID.randomUUID,
        name = creation.name,
        description = creation.description,
        createdOn = timer.timestampOfNow,
        lastModified = None
      )
      val action = (MissionTable returning MissionTable.map(_.id) into ((item, id) => item.copy(id = id))) += row
      db.run(action) map (row => row.asMission)
    }

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
      def insertEpochAction(creation: CreateEpoch): DBIO[EpochRow] = {
        val row = EpochRow(
          id = 0L,
          uuid = UUID.randomUUID,
          name = creation.name,
          totem = creation.totem,
          question = creation.question,
          createdOn = timer.timestampOfNow,
          lastModified = None
        )
        (EpochTable returning EpochTable.map(_.id) into ((epoch, id) => epoch.copy(id = id))) += row
      }

      val action = for {
        epoch <- insertEpochAction(creation)
        epochMissions <- insertEpochMissionsAction(epoch.id, creation.associatedMissions)
      } yield {
        (epoch, epochMissions.zip(creation.associatedMissions).map {
          case (epochMission, associatedMission) => (epochMission, associatedMission.missionId)
        }).asEpoch
      }
      db.run(action)
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
          safetyNet = creation.valueDimensions.helpsSafetyNet,
          worldView = creation.valueDimensions.expandsWorldView,
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
        goalMissions <- insertGoalMissionsAction(goal.id, creation.valueDimensions.associatedMissions)
        goalSkills <- insertGoalSkillsAction(goal.id, creation.valueDimensions.associatedSkills)
        goalRelationships <- insertGoalRelationshipsAction(goal.id, creation.valueDimensions.relationships)
      } yield {
        (goal, backlogItems, goalMissions.zip(creation.valueDimensions.associatedMissions).map {
          case (_, associatedMission) => associatedMission
        }, goalSkills.zip(creation.valueDimensions.associatedSkills).map {
          case (goalSkill, associatedSkill) => (goalSkill, associatedSkill.skillId)
        }, goalRelationships.zip(creation.valueDimensions.relationships).map {
          case (_, associatedRelationship) => associatedRelationship
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
          safetyNet = creation.valueDimensions.helpsSafetyNet,
          worldView = creation.valueDimensions.expandsWorldView,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (ThreadTable returning ThreadTable.map(_.id) into ((thread, id) => thread.copy(id = id))) += row
      }

      val action = for {
        thread <- insertThreadAction(creation)
        threadMissions <- insertThreadMissionsAction(thread.id, creation.valueDimensions.associatedMissions)
        threadSkills <- insertThreadSkillsAction(thread.id, creation.valueDimensions.associatedSkills)
        threadRelationships <- insertThreadRelationshipsAction(thread.id, creation.valueDimensions.relationships)
      } yield {
        (thread, threadMissions.zip(creation.valueDimensions.associatedMissions).map {
          case (_, associatedMission) => associatedMission
        }, threadSkills.zip(creation.valueDimensions.associatedSkills).map {
          case (threadSkill, associatedSkill) => (threadSkill, associatedSkill.skillId)
        }, threadRelationships.zip(creation.valueDimensions.relationships).map {
          case (_, associatedRelationship) => associatedRelationship
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
          safetyNet = creation.valueDimensions.helpsSafetyNet,
          worldView = creation.valueDimensions.expandsWorldView,
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
        weaveMissions <- insertWeaveMissionsAction(weave.id, creation.valueDimensions.associatedMissions)
        weaveSkills <- insertWeaveSkillsAction(weave.id, creation.valueDimensions.associatedSkills)
        weaveRelationships <- insertWeaveRelationshipsAction(weave.id, creation.valueDimensions.relationships)
      } yield {
        (weave, weaveMissions.zip(creation.valueDimensions.associatedMissions).map {
          case (_, associatedMission) => associatedMission
        }, weaveSkills.zip(creation.valueDimensions.associatedSkills).map {
          case (weaveSkill, associatedSkill) => (weaveSkill, associatedSkill.skillId)
        }, weaveRelationships.zip(creation.valueDimensions.relationships).map {
          case (_, associatedRelationship) => associatedRelationship
        }).asWeave
      }
      db.run(action)
    }

    override def createLaserDonut(creation: CreateLaserDonut): Future[LaserDonut] = {
      def insertLaserDonutAction(creation: CreateLaserDonut, existingLaserDonuts: Seq[LaserDonutRow]): DBIO[LaserDonutRow] = {
        val row = LaserDonutRow(
          id = 0L,
          uuid = UUID.randomUUID,
          goalId = creation.goalId,
          summary = creation.summary,
          description = creation.description,
          safetyNet = creation.valueDimensions.helpsSafetyNet,
          worldView = creation.valueDimensions.expandsWorldView,
          milestone = creation.milestone,
          order = existingLaserDonuts.lastOption.map(_.order + 1).getOrElse(1),
          status = creation.status.dbValue,
          `type` = creation.`type`.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (LaserDonutTable returning LaserDonutTable.map(_.id) into ((laserDonut, id) => laserDonut.copy(id = id))) += row
      }
      val action = for {
        existingLaserDonuts <- laserDonutsByParentId(creation.goalId)
        laserDonut <- insertLaserDonutAction(creation, existingLaserDonuts.map(_._1))
        laserDonutMissions <- insertLaserDonutMissionsAction(laserDonut.id, creation.valueDimensions.associatedMissions)
        laserDonutSkills <- insertLaserDonutSkillsAction(laserDonut.id, creation.valueDimensions.associatedSkills)
        laserDonutRelationships <- insertLaserDonutRelationshipsAction(laserDonut.id, creation.valueDimensions.relationships)
      } yield {
        (laserDonut, laserDonutMissions.zip(creation.valueDimensions.associatedMissions).map {
          case (_, associatedMission) => associatedMission
        }, laserDonutSkills.zip(creation.valueDimensions.associatedSkills).map {
          case (laserDonutSkill, associatedSkill) => (laserDonutSkill, associatedSkill.skillId)
        }, laserDonutRelationships.zip(creation.valueDimensions.relationships).map {
          case (_, associatedRelationship) => associatedRelationship
        }).asLaserDonut
      }
      db.run(action)
    }

    override def createPortion(creation: CreatePortion): Future[Portion] = {
      def insertPortionAction(creation: CreatePortion, existingPortions: Seq[PortionRow]): DBIO[PortionRow] = {
        val row = PortionRow(
          id = 0L,
          uuid = UUID.randomUUID,
          laserDonutId = creation.laserDonutId,
          summary = creation.summary,
          safetyNet = creation.valueDimensions.helpsSafetyNet,
          worldView = creation.valueDimensions.expandsWorldView,
          order = existingPortions.sortBy(_.order).lastOption.map(_.order + 1).getOrElse(1),
          status = creation.status.dbValue,
          createdOn = timer.timestampOfNow,
          lastModified = None,
          lastPerformed = None
        )
        (PortionTable returning PortionTable.map(_.id) into ((portion, id) => portion.copy(id = id))) += row
      }
      val action = for {
        existingPortions <- getPortionsByParent(creation.laserDonutId).map(_.map(_._1))
        portion <- insertPortionAction(creation, existingPortions)
        portionMissions <- insertPortionMissionsAction(portion.id, creation.valueDimensions.associatedMissions)
        portionSkills <- insertPortionSkillsAction(portion.id, creation.valueDimensions.associatedSkills)
        portionRelationships <- insertPortionRelationshipsAction(portion.id, creation.valueDimensions.relationships)
      } yield {
        (portion, portionMissions.zip(creation.valueDimensions.associatedMissions).map {
          case (_, associatedMission) => associatedMission
        }, portionSkills.zip(creation.valueDimensions.associatedSkills).map {
          case (portionSkill, associatedSkill) => (portionSkill, associatedSkill.skillId)
        }, portionRelationships.zip(creation.valueDimensions.relationships).map {
          case (_, associatedRelationship) => associatedRelationship
        }).asPortion
      }
      db.run(action)
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
        existingTodos <- getTodosByParent(creation.parentId)
        todo <- insertTodoAction(creation, existingTodos)
      } yield todo.asTodo
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
          safetyNet = creation.valueDimensions.helpsSafetyNet,
          worldView = creation.valueDimensions.expandsWorldView,
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
        hobbyMissions <- insertHobbyMissionsAction(hobby.id, creation.valueDimensions.associatedMissions)
        hobbySkills <- insertHobbySkillsAction(hobby.id, creation.valueDimensions.associatedSkills)
        hobbyRelationships <- insertHobbyRelationshipsAction(hobby.id, creation.valueDimensions.relationships)
      } yield {
        (hobby, hobbyMissions.zip(creation.valueDimensions.associatedMissions).map {
          case (_, associatedMission) => associatedMission
        }, hobbySkills.zip(creation.valueDimensions.associatedSkills).map {
          case (hobbySkill, associatedSkill) => (hobbySkill, associatedSkill.skillId)
        }, hobbyRelationships.zip(creation.valueDimensions.relationships).map {
          case (_, associatedRelationship) => associatedRelationship
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
          safetyNet = creation.valueDimensions.helpsSafetyNet,
          worldView = creation.valueDimensions.expandsWorldView,
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
        oneOffMissions <- insertOneOffMissionsAction(oneOff.id, creation.valueDimensions.associatedMissions)
        oneOffSkills <- insertOneOffSkillsAction(oneOff.id, creation.valueDimensions.associatedSkills)
        oneOffRelationships <- insertOneOffRelationshipsAction(oneOff.id, creation.valueDimensions.relationships)
      } yield {
        (oneOff, oneOffMissions.zip(creation.valueDimensions.associatedMissions).map {
          case (_, associatedMission) => associatedMission
        }, oneOffSkills.zip(creation.valueDimensions.associatedSkills).map {
          case (oneOffSkill, associatedSkill) => (oneOffSkill, associatedSkill.skillId)
        }, oneOffRelationships.zip(creation.valueDimensions.relationships).map {
          case (_, associatedRelationship) => associatedRelationship
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
          safetyNet = creation.valueDimensions.helpsSafetyNet,
          worldView = creation.valueDimensions.expandsWorldView,
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
        oneOffMissions <- insertOneOffMissionsAction(oneOff.id, creation.valueDimensions.associatedMissions)
        oneOffSkills <- insertScheduledOneOffSkillsAction(oneOff.id, creation.valueDimensions.associatedSkills)
        oneOffRelationships <- insertOneOffRelationshipsAction(oneOff.id, creation.valueDimensions.relationships)
      } yield {
        (oneOff, oneOffMissions.zip(creation.valueDimensions.associatedMissions).map {
          case (_, associatedMission) => associatedMission
        }, oneOffSkills.zip(creation.valueDimensions.associatedSkills).map {
          case (oneOffSkill, associatedSkill) => (oneOffSkill, associatedSkill.skillId)
        }, oneOffRelationships.zip(creation.valueDimensions.relationships).map {
          case (_, associatedRelationship) => associatedRelationship
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
              laserDonutId = laserDonut._1.id,
              tier = tierNumber + 1,
              isCurrent = false
            )
            ((ScheduledLaserDonutTable returning ScheduledLaserDonutTable.map(_.id) into ((row, id) => row.copy(id = id))) += row).map((_, laserDonut._1))
          case None => DBIO.failed(LaserDonutNotFoundException(s"no laser-donut with the UUID $laserDonutUuid exists"))
        }
      }
      val action = DBIO.sequence(insertions)
      db.run(action).map(_.asPyramid)
    }

    // Update endpoints
    override def updateSkillCategory(uuid: UUID, update: UpdateSkillCategory): Future[SkillCategory] = {
      def updateSkillCategory(uuid: UUID, update: UpdateSkillCategory, old: SkillCategoryRow, newParentId: Option[Long]): DBIO[Int] = {
        skillCategoryByUuid(uuid).map(category => (category.name, category.parentCategory))
          .update(update.name.getOrElse(old.name), newParentId)
      }

      val action = (for {
        oldAndParent <- getSkillCategoryAction(uuid).map(_.getOrElse(throw SkillCategoryNotFoundException()))
        (old, _) = oldAndParent
        newParent <- getParentCategory(update.parentCategory)
        newParentId = newParent.map(_._1.id).orElse(old.parentCategory)
        _ <- updateSkillCategory(uuid, update, old, newParentId)
        updatedCategory <- getSkillCategoryAction(uuid).map(_.head)
      } yield updatedCategory.asSkillCategory).transactionally

      db.run(action)
    }

    override def updateSkill(uuid: UUID, update: UpdateSkill): Future[Skill] = {
      def updateSkill(uuid: UUID, update: UpdateSkill, old: SkillRow, newParentId: Long): DBIO[Int] = {
        skillByUuid(uuid).map(skill => (skill.name, skill.parentCategory, skill.proficiency, skill.practisedHours, skill.lastModified, skill.lastApplied))
          .update(update.name.getOrElse(old.name), newParentId,
            UpdateTypeEnum.keepOrReplace(update.proficiency, old.proficiency), update.practisedHours.getOrElse(old.practisedHours),
            Some(timer.timestampOfNow), UpdateDateTime.keepOrReplace(update.lastPractise, old.lastApplied))
      }

      val action = (for {
        oldAndParent <- getSkillAction(uuid).map(_.getOrElse(throw SkillNotFoundException()))
        (old, _) = oldAndParent
        newParent <- getParentCategory(update.parentCategory)
        newParentId = newParent.map(_._1.id).getOrElse(old.parentCategory)
        _ <- updateSkill(uuid, update, old, newParentId)
        updatedSkill <- getSkillAction(uuid).map(_.head)
      } yield updatedSkill.asSkill).transactionally

      db.run(action)
    }

    private def getParentCategory(categoryId: Option[UUID]) = {
      categoryId match {
        case Some(uuid) => getSkillCategoryAction(uuid)
        case None => DBIO.successful(None)
      }
    }

    override def updateRelationship(uuid: UUID, update: UpdateRelationship): Future[Relationship] = {
      val query = relationshipByUuid(uuid).map(relationship => (relationship.name, relationship.category,
        relationship.traits, relationship.likes, relationship.dislikes, relationship.hobbies, relationship.lastModified,
        relationship.lastMeet))
      val action = getRelationshipAction(uuid).flatMap { maybeObj =>
        maybeObj map { old =>
          query.update(update.name.getOrElse(old.name),
            UpdateTypeEnum.keepOrReplace(update.category, old.category),
            update.traits.map(_.mkString(",")).getOrElse(old.traits),
            update.likes.map(_.mkString(",")).getOrElse(old.likes),
            update.dislikes.map(_.mkString(",")).getOrElse(old.dislikes),
            update.hobbies.map(_.mkString(",")).getOrElse(old.hobbies),
            Some(timer.timestampOfNow),
            UpdateDate.keepOrReplace(update.lastMeet, old.lastMeet))
            .andThen(getRelationshipAction(uuid).map(_.head))
        } getOrElse DBIO.failed(RelationshipNotFoundException())
      }.transactionally
      db.run(action).map(row => row.asRelationship)
    }

    override def updateMission(uuid: UUID, update: UpdateMission): Future[Mission] = ???

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
      def updateEpoch(uuid: UUID, update: UpdateEpoch, old: EpochRow): DBIO[Int] = {
        epochByUuid(uuid).map(epoch => (epoch.name, epoch.totem, epoch.question, epoch.lastModified))
          .update(update.name.getOrElse(old.name), update.totem.getOrElse(old.totem), update.question.getOrElse(old.question),
            Some(timer.timestampOfNow))
      }

      def updateAssociatedMissions(epoch: EpochRow) = update.associatedMissions match {
        case Some(associatedMissions) =>
          for {
            _ <- EpochMissionTable.filter(_.epochId === epoch.id).delete
            result <- insertEpochMissionsAction(epoch.id, associatedMissions)
          } yield result.zip(associatedMissions.map(_.missionId))
        case None => DBIO.successful(Seq.empty[(EpochMissionRow, UUID)])
      }

      val action = (for {
        oldAndMissions <- getEpochAction(uuid).map(_.getOrElse(throw EpochNotFoundException()))
        (old, _) = oldAndMissions
        _ <- updateEpoch(uuid, update, old)
        _ <- updateAssociatedMissions(old)
        updatedEpoch <- getEpochAction(uuid).map(_.head)
      } yield updatedEpoch.asEpoch).transactionally

      db.run(action)
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

      def updateAssociatedSkills(goal: GoalRow) = update.valueDimensions.flatMap(_.associatedSkills) match {
        case Some(associatedSkills) =>
          for {
            _ <- GoalSkillTable.filter(_.goalId === goal.id).delete
            result <- insertGoalSkillsAction(goal.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(GoalSkillRow, UUID)])
      }

      def updateRelationships(goal: GoalRow) = update.valueDimensions.flatMap(_.relationships) match {
        case Some(relationships) =>
          for {
            _ <- GoalRelationshipTable.filter(_.goalId === goal.id).delete
            result <- insertGoalRelationshipsAction(goal.id, relationships)
          } yield result.zip(relationships)
        case None => DBIO.successful(Seq.empty[(GoalRelationshipRow, UUID)])
      }

      def updateAssociatedMissions(goal: GoalRow) = update.valueDimensions.flatMap(_.associatedMissions) match {
        case Some(associatedMissions) =>
          for {
            _ <- GoalMissionTable.filter(_.goalId === goal.id).delete
            result <- insertGoalMissionsAction(goal.id, associatedMissions)
          } yield result.zip(associatedMissions)
        case None => DBIO.successful(Seq.empty[(GoalMissionRow, UUID)])
      }

      val action = (for {
        goalAndBacklog <- getGoalAction(uuid).map(_.getOrElse(throw GoalNotFoundException()))
        (old, _, _, _, _) = goalAndBacklog
        _ <- updateGoal(uuid, update, old)
        _ <- updateBacklogItems(old)
        _ <- updateAssociatedSkills(old)
        _ <- updateRelationships(old)
        _ <- updateAssociatedMissions(old)
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
      }

      def updateAssociatedSkills(thread: ThreadRow) = update.valueDimensions.flatMap(_.associatedSkills) match {
        case Some(associatedSkills) =>
          for {
            _ <- ThreadSkillTable.filter(_.threadId === thread.id).delete
            result <- insertThreadSkillsAction(thread.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(ThreadSkillRow, UUID)])
      }

      def updateRelationships(thread: ThreadRow) = update.valueDimensions.flatMap(_.relationships) match {
        case Some(relationships) =>
          for {
            _ <- ThreadRelationshipTable.filter(_.threadId === thread.id).delete
            result <- insertThreadRelationshipsAction(thread.id, relationships)
          } yield result.zip(relationships)
        case None => DBIO.successful(Seq.empty[(ThreadRelationshipRow, UUID)])
      }

      def updateAssociatedMissions(thread: ThreadRow) = update.valueDimensions.flatMap(_.associatedMissions) match {
        case Some(associatedMissions) =>
          for {
            _ <- ThreadMissionTable.filter(_.threadId === thread.id).delete
            result <- insertThreadMissionsAction(thread.id, associatedMissions)
          } yield result.zip(associatedMissions)
        case None => DBIO.successful(Seq.empty[(ThreadMissionRow, UUID)])
      }

      val action = (for {
        threadAndSkills <- getThreadAction(uuid).map(_.getOrElse(throw ThreadNotFoundException()))
        (old, _, _, _) = threadAndSkills
        _ <- updateThread(uuid, update, old)
        _ <- updateAssociatedSkills(old)
        _ <- updateRelationships(old)
        _ <- updateAssociatedMissions(old)
        updatedThread <- getThreadAction(uuid).map(_.head)
      } yield updatedThread.asThread).transactionally

      db.run(action)
    }

    override def updateWeave(uuid: UUID, update: UpdateWeave): Future[Weave] = {
      def updateWeave(uuid: UUID, update: UpdateWeave, old: WeaveRow) = {
        val (lastModified, lastPerformed) = getUpdateTimes(
          contentUpdate = update.goalId :: update.summary :: update.description :: update.`type` :: Nil,
          statusUpdate = update.status :: Nil
        )
        val query = weaveByUuid(uuid).map(weave => (weave.goalId, weave.summary, weave.description, weave.status, weave.`type`,
          weave.lastModified, weave.lastPerformed))
        query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
          update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.status, old.status),
          UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`), lastModified, lastPerformed)
      }

      def updateAssociatedSkills(weave: WeaveRow) = update.valueDimensions.flatMap(_.associatedSkills) match {
        case Some(associatedSkills) =>
          for {
            _ <- WeaveSkillTable.filter(_.weaveId === weave.id).delete
            result <- insertWeaveSkillsAction(weave.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(WeaveSkillRow, UUID)])
      }

      def updateRelationships(weave: WeaveRow) = update.valueDimensions.flatMap(_.relationships) match {
        case Some(relationships) =>
          for {
            _ <- WeaveRelationshipTable.filter(_.weaveId === weave.id).delete
            result <- insertWeaveRelationshipsAction(weave.id, relationships)
          } yield result.zip(relationships)
        case None => DBIO.successful(Seq.empty[(WeaveRelationshipRow, UUID)])
      }

      def updateAssociatedMissions(weave: WeaveRow) = update.valueDimensions.flatMap(_.associatedMissions) match {
        case Some(associatedMissions) =>
          for {
            _ <- WeaveMissionTable.filter(_.weaveId === weave.id).delete
            result <- insertWeaveMissionsAction(weave.id, associatedMissions)
          } yield result.zip(associatedMissions)
        case None => DBIO.successful(Seq.empty[(WeaveMissionRow, UUID)])
      }

      val action = (for {
        weaveAndSkills <- getWeaveAction(uuid).map(_.getOrElse(throw WeaveNotFoundException()))
        (old, _, _, _) = weaveAndSkills
        _ <- updateWeave(uuid, update, old)
        _ <- updateAssociatedSkills(old)
        _ <- updateRelationships(old)
        _ <- updateAssociatedMissions(old)
        updatedWeave <- getWeaveAction(uuid).map(_.head)
      } yield updatedWeave.asWeave).transactionally

      db.run(action)
    }

    override def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut): Future[LaserDonut] = {
      def updateLaserDonut(uUID: UUID, update: UpdateLaserDonut, old: LaserDonutRow) = {
        val (lastModified, lastPerformed) = getUpdateTimes(
          contentUpdate = update.goalId :: update.summary :: update.description :: update.milestone :: update.`type` :: Nil,
          statusUpdate = update.status :: Nil
        )
        val query = laserDonutByUuid(uuid).map(laserDonut => (laserDonut.goalId, laserDonut.summary, laserDonut.description,
          laserDonut.milestone, laserDonut.status, laserDonut.`type`, laserDonut.lastModified, laserDonut.lastPerformed))
        query.update(UpdateId.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
          update.description.getOrElse(old.description), update.milestone.getOrElse(old.milestone),
          UpdateTypeEnum.keepOrReplace(update.status, old.status), UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`),
          lastModified, lastPerformed)
      }

      def updateAssociatedSkills(laserDonut: LaserDonutRow) = update.valueDimensions.flatMap(_.associatedSkills) match {
        case Some(associatedSkills) =>
          for {
            _ <- LaserDonutSkillTable.filter(_.laserDonutId === laserDonut.id).delete
            result <- insertLaserDonutSkillsAction(laserDonut.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(LaserDonutSkillRow, UUID)])
      }

      def updateRelationships(laserDonut: LaserDonutRow) = update.valueDimensions.flatMap(_.relationships) match {
        case Some(relationships) =>
          for {
            _ <- LaserDonutRelationshipTable.filter(_.laserDonutId === laserDonut.id).delete
            result <- insertLaserDonutRelationshipsAction(laserDonut.id, relationships)
          } yield result.zip(relationships)
        case None => DBIO.successful(Seq.empty[(LaserDonutRelationshipRow, UUID)])
      }

      def updateAssociatedMissions(laserDonut: LaserDonutRow) = update.valueDimensions.flatMap(_.associatedMissions) match {
        case Some(associatedMissions) =>
          for {
            _ <- LaserDonutMissionTable.filter(_.laserDonutId === laserDonut.id).delete
            result <- insertLaserDonutMissionsAction(laserDonut.id, associatedMissions)
          } yield result.zip(associatedMissions)
        case None => DBIO.successful(Seq.empty[(LaserDonutMissionRow, UUID)])
      }

      val action = (for {
        laserDonutAndSkills <- getLaserDonutAction(uuid).map(_.getOrElse(throw LaserDonutNotFoundException()))
        (old, _, _, _) = laserDonutAndSkills
        _ <- updateLaserDonut(uuid, update, old)
        _ <- updateAssociatedSkills(old)
        _ <- updateRelationships(old)
        _ <- updateAssociatedMissions(old)
        updatedLaserDonut <- getLaserDonutAction(uuid).map(_.head)
      } yield updatedLaserDonut.asLaserDonut).transactionally

      db.run(action)
    }

    override def updatePortion(uuid: UUID, update: UpdatePortion): Future[Portion] = {
      def updatePortion(uuid: UUID, update: UpdatePortion, old: PortionRow) = {
        val (lastModified, lastPerformed) = getUpdateTimes(
          contentUpdate = update.laserDonutId :: update.summary :: Nil,
          statusUpdate = update.status :: Nil
        )
        val query = portionByUuid(uuid).map(portion => (portion.laserDonutId, portion.summary, portion.status, portion.lastModified, portion.lastPerformed))
        for {
          _ <- query.update(UpdateId.keepOrReplace(update.laserDonutId, old.laserDonutId), update.summary.getOrElse(old.summary),
            UpdateTypeEnum.keepOrReplace(update.status, old.status), lastModified, lastPerformed)
          updatedPortion <- getPortionAction(uuid).map(_.head)
          _ <- updateLaserDonutStatus(UUID.fromString(updatedPortion._1.laserDonutId))
        } yield updatedPortion
      }

      def updateLaserDonutStatus(uuid: UUID) = {
        for {
          portions <- portionsByParentId(uuid)
          update <- LaserDonutTable.filter(_.uuid === uuid.toString).map(_.status)
            .update(getStatusSummary(portions.map(portion => Statuses.withName(portion._1.status))).dbValue).map(_ > 0)
        } yield update
      }

      def updateAssociatedSkills(portion: PortionRow) = update.valueDimensions.flatMap(_.associatedSkills) match {
        case Some(associatedSkills) =>
          for {
            _ <- PortionSkillTable.filter(_.portionId === portion.id).delete
            result <- insertPortionSkillsAction(portion.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(PortionSkillRow, UUID)])
      }

      def updateRelationships(portion: PortionRow) = update.valueDimensions.flatMap(_.relationships) match {
        case Some(relationships) =>
          for {
            _ <- PortionRelationshipTable.filter(_.portionId === portion.id).delete
            result <- insertPortionRelationshipsAction(portion.id, relationships)
          } yield result.zip(relationships)
        case None => DBIO.successful(Seq.empty[(PortionRelationshipRow, UUID)])
      }

      def updateAssociatedMissions(portion: PortionRow) = update.valueDimensions.flatMap(_.associatedMissions) match {
        case Some(associatedMissions) =>
          for {
            _ <- PortionMissionTable.filter(_.portionId === portion.id).delete
            result <- insertPortionMissionsAction(portion.id, associatedMissions)
          } yield result.zip(associatedMissions)
        case None => DBIO.successful(Seq.empty[(PortionMissionRow, UUID)])
      }

      val action = (for {
        portionAndSkills <- getPortionAction(uuid).map(_.getOrElse(throw PortionNotFoundException()))
        (old, _, _, _) = portionAndSkills
        _ <- updatePortion(uuid, update, old)
        _ <- updateAssociatedSkills(old)
        _ <- updateRelationships(old)
        _ <- updateAssociatedMissions(old)
        updatedPortion <- getPortionAction(uuid).map(_.head)
      } yield updatedPortion.asPortion).transactionally

      db.run(action)
    }

    override def updatePortions(laserDonutId: UUID, update: UpdateList): Future[Seq[Portion]] = {
      def getSortedPortions(uuids: Seq[UUID]) = {
        portionsWithExtrasByUuid(uuids).map(groupByPortion(_)).map(_.sortBy(_._1.order))
      }

      val action = portionsByParentId(laserDonutId).flatMap { portions =>
        if (portions.size <= update.reordered.size) {
          val portionIds = portions.map(_._1.uuid)
          update.reordered.filterNot(id => portionIds.contains(id.toString)) match {
            case Nil => DBIO.sequence(update.reordered.zipWithIndex.map { case (uuid, index) =>
              portionByUuid(uuid).map(_.order).update(index + 1)
            }).andThen(getSortedPortions(portionIds.map(UUID.fromString)))
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
            .update(getOutcomeSummary(todos.map(todo => todo.isDone: Boolean)).dbValue).map(_ > 0)
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
      val action = getTodosByParent(parentId).flatMap { todos =>
        if (todos.size <= update.reordered.size) {
          val todoIds = todos.map(_.uuid).map(UUID.fromString)
          update.reordered.filterNot(id => todoIds.contains(id)) match {
            case Nil => DBIO.sequence(update.reordered.zipWithIndex.map { case (uuid, index) =>
              todoByUuid(uuid).map(_.order).update(index + 1)
            }).andThen(getTodosAction(todoIds).map(_.map(_.asTodo)))
            case outliers => DBIO.failed(
              InvalidTodosUpdateException(s"the todos (${outliers.mkString(", ")}) do not belong to the portion $parentId")
            )
          }
        }
        else DBIO.failed(
          InvalidTodosUpdateException("the length of the update list should be the same as the number of todos for the portion")
        )
      }.transactionally
      db.run(action)
    }

    override def updateHobby(uuid: UUID, update: UpdateHobby): Future[Hobby] = {
      def updateHobby(uuid: UUID, update: UpdateHobby, old: HobbyRow) = {
        val (lastModified, lastPerformed) = getUpdateTimes(
          contentUpdate = update.goalId :: update.summary :: update.description :: update.frequency :: update.`type` :: Nil,
          statusUpdate = Nil
        )
        val query = hobbyByUuid(uuid).map(hobby => (hobby.goalId, hobby.summary, hobby.description, hobby.frequency,
          hobby.`type`, hobby.lastModified, hobby.lastPerformed))
        query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.summary.getOrElse(old.summary),
          update.description.getOrElse(old.description), UpdateTypeEnum.keepOrReplace(update.frequency, old.frequency),
          UpdateTypeEnum.keepOrReplace(update.`type`, old.`type`), lastModified, lastPerformed)
      }

      def updateAssociatedSkills(hobby: HobbyRow) = update.valueDimensions.flatMap(_.associatedSkills) match {
        case Some(associatedSkills) =>
          for {
            _ <- HobbySkillTable.filter(_.hobbyId === hobby.id).delete
            result <- insertHobbySkillsAction(hobby.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(HobbySkillRow, UUID)])
      }

      def updateRelationships(hobby: HobbyRow) = update.valueDimensions.flatMap(_.relationships) match {
        case Some(relationships) =>
          for {
            _ <- HobbyRelationshipTable.filter(_.hobbyId === hobby.id).delete
            result <- insertHobbyRelationshipsAction(hobby.id, relationships)
          } yield result.zip(relationships)
        case None => DBIO.successful(Seq.empty[(HobbyRelationshipRow, UUID)])
      }

      def updateAssociatedMissions(hobby: HobbyRow) = update.valueDimensions.flatMap(_.associatedMissions) match {
        case Some(associatedMissions) =>
          for {
            _ <- HobbyMissionTable.filter(_.hobbyId === hobby.id).delete
            result <- insertHobbyMissionsAction(hobby.id, associatedMissions)
          } yield result.zip(associatedMissions)
        case None => DBIO.successful(Seq.empty[(HobbyMissionRow, UUID)])
      }

      val action = (for {
        hobbyAndSkills <- getHobbyAction(uuid).map(_.getOrElse(throw HobbyNotFoundException()))
        (old, _, _, _) = hobbyAndSkills
        _ <- updateHobby(uuid, update, old)
        _ <- updateAssociatedSkills(old)
        _ <- updateRelationships(old)
        _ <- updateAssociatedMissions(old)
        updatedHobby <- getHobbyAction(uuid).map(_.head)
      } yield updatedHobby.asHobby).transactionally

      db.run(action)
    }

    override def updateOneOff(uuid: UUID, update: UpdateOneOff): Future[OneOff] = {
      def updateOneOff(uuid: UUID, update: UpdateOneOff, old: OneOffRow) = {
        val (lastModified, lastPerformed) = getUpdateTimes(
          contentUpdate = update.goalId :: update.description :: update.estimate :: update.status :: Nil,
          statusUpdate = Nil
        )
        val query = oneOffByUuid(uuid).map(oneOff => (oneOff.goalId, oneOff.description, oneOff.estimate, oneOff.status,
          oneOff.lastModified, oneOff.lastPerformed))
        query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), update.description.getOrElse(old.description),
          update.estimate.getOrElse(old.estimate), UpdateTypeEnum.keepOrReplace(update.status, old.status), lastModified, lastPerformed)
      }

      def updateAssociatedSkills(oneOff: OneOffRow) = update.valueDimensions.flatMap(_.associatedSkills) match {
        case Some(associatedSkills) =>
          for {
            _ <- OneOffSkillTable.filter(_.oneOffId === oneOff.id).delete
            result <- insertOneOffSkillsAction(oneOff.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(OneOffSkillRow, UUID)])
      }

      def updateRelationships(oneOff: OneOffRow) = update.valueDimensions.flatMap(_.relationships) match {
        case Some(relationships) =>
          for {
            _ <- OneOffRelationshipTable.filter(_.oneOffId === oneOff.id).delete
            result <- insertOneOffRelationshipsAction(oneOff.id, relationships)
          } yield result.zip(relationships)
        case None => DBIO.successful(Seq.empty[(OneOffRelationshipRow, UUID)])
      }

      def updateAssociatedMissions(oneOff: OneOffRow) = update.valueDimensions.flatMap(_.associatedMissions) match {
        case Some(associatedMissions) =>
          for {
            _ <- OneOffMissionTable.filter(_.oneOffId === oneOff.id).delete
            result <- insertOneOffMissionsAction(oneOff.id, associatedMissions)
          } yield result.zip(associatedMissions)
        case None => DBIO.successful(Seq.empty[(OneOffMissionRow, UUID)])
      }

      val action = (for {
        oneOffAndSkills <- getOneOffAction(uuid).map(_.getOrElse(throw OneOffNotFoundException()))
        (old, _, _, _) = oneOffAndSkills
        _ <- updateOneOff(uuid, update, old)
        _ <- updateAssociatedSkills(old)
        _ <- updateRelationships(old)
        _ <- updateAssociatedMissions(old)
        updatedOneOff <- getOneOffAction(uuid).map(_.head)
      } yield updatedOneOff.asOneOff).transactionally

      db.run(action)
    }

    override def updateOneOffs(update: UpdateList): Future[Seq[OneOff]] = {
      def updateOrdering(oneOffIds: Seq[String]) =
        update.reordered.filterNot(id => oneOffIds.contains(id.toString)) match {
          case Nil => DBIO.sequence(update.reordered.zipWithIndex.map { case (uuid, index) =>
            oneOffByUuid(uuid).map(_.order).update(index + 1)
          }).andThen(getOneOffsAction(oneOffIds.map(UUID.fromString)).map(_.map(_.asOneOff)))
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

      db.run(action)
    }

    override def updateScheduledOneOff(uuid: UUID, update: UpdateScheduledOneOff): Future[ScheduledOneOff] = {
      def updateScheduledOneOff(uuid: UUID, update: UpdateScheduledOneOff, old: ScheduledOneOffRow) = {
        val (lastModified, lastPerformed) = getUpdateTimes(
          contentUpdate = update.goalId :: update.occursOn :: update.description :: update.estimate :: update.status :: Nil,
          statusUpdate = Nil
        )
        val query = scheduledOneOffByUuid(uuid).map(oneOff => (oneOff.goalId, oneOff.occursOn, oneOff.description, oneOff.estimate, oneOff.status,
          oneOff.lastModified, oneOff.lastPerformed))
        query.update(UpdateIdOption.keepOrReplace(update.goalId, old.goalId), UpdateDateTime.keepOrReplace(update.occursOn, old.occursOn),
          update.description.getOrElse(old.description), update.estimate.getOrElse(old.estimate), UpdateTypeEnum.keepOrReplace(update.status, old.status),
          lastModified, lastPerformed)
      }

      def updateAssociatedSkills(oneOff: ScheduledOneOffRow) = update.valueDimensions.flatMap(_.associatedSkills) match {
        case Some(associatedSkills) =>
          for {
            _ <- ScheduledOneOffSkillTable.filter(_.scheduledOneOffId === oneOff.id).delete
            result <- insertScheduledOneOffSkillsAction(oneOff.id, associatedSkills)
          } yield result.zip(associatedSkills.map(_.skillId))
        case None => DBIO.successful(Seq.empty[(ScheduledOneOffSkillRow, UUID)])
      }

      def updateRelationships(oneOff: ScheduledOneOffRow) = update.valueDimensions.flatMap(_.relationships) match {
        case Some(relationships) =>
          for {
            _ <- ScheduledOneOffRelationshipTable.filter(_.scheduledOneOffId === oneOff.id).delete
            result <- insertScheduledOneOffRelationshipsAction(oneOff.id, relationships)
          } yield result.zip(relationships)
        case None => DBIO.successful(Seq.empty[(ScheduledOneOffRelationshipRow, UUID)])
      }

      def updateAssociatedMissions(oneOff: ScheduledOneOffRow) = update.valueDimensions.flatMap(_.associatedMissions) match {
        case Some(associatedMissions) =>
          for {
            _ <- ScheduledOneOffMissionTable.filter(_.scheduledOneOffId === oneOff.id).delete
            result <- insertScheduledOneOffMissionsAction(oneOff.id, associatedMissions)
          } yield result.zip(associatedMissions)
        case None => DBIO.successful(Seq.empty[(ScheduledOneOffMissionRow, UUID)])
      }

      val action = (for {
        scheduledOneOffAndSkills <- getScheduledOneOffAction(uuid).map(_.getOrElse(throw ScheduledOneOffNotFoundException()))
        (old, _, _, _) = scheduledOneOffAndSkills
        _ <- updateScheduledOneOff(uuid, update, old)
        _ <- updateAssociatedSkills(old)
        _ <- updateRelationships(old)
        _ <- updateAssociatedMissions(old)
        updatedOneOff <- getScheduledOneOffAction(uuid).map(_.head)
      } yield updatedOneOff.asScheduledOneOff).transactionally

      db.run(action)
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
    override def getSkillCategories: Future[Seq[SkillCategory]] = {
      val action = for {
        skillCategories <- SkillCategoryTable.result
        withParents <- DBIO.sequence(skillCategories.map(item => getSkillCategoryAction(item.id)))
      } yield withParents.collect {
        case (Some(cat), parentId) => (cat, parentId)
      }.map(_.asSkillCategory)
      db.run(action)
    }

    override def getSkillCategory(uuid: UUID): Future[Option[SkillCategory]] = {
      db.run(getSkillCategoryAction(uuid).map(_.map(_.asSkillCategory)))
    }

    override def getSkills: Future[Seq[Skill]] = {
      val action = for {
        skills <- SkillTable.result
        withParents <- DBIO.sequence(skills.map(item => getSkillAction(item.id)))
      } yield withParents.flatten.map(_.asSkill)
      db.run(action)
    }

    override def getSkill(uuid: UUID): Future[Option[Skill]] = {
      db.run(getSkillAction(uuid).map(_.map(_.asSkill)))
    }

    override def getRelationships: Future[Seq[Relationship]] = {
      db.run(RelationshipTable.result.map(_.sortBy(_.createdOn).map(_.asRelationship)))
    }

    override def getRelationship(uuid: UUID): Future[Option[Relationship]] = {
      db.run(getRelationshipAction(uuid).map(_.map(_.asRelationship)))
    }

    override def getMissions: Future[Seq[Mission]] = {
      db.run(MissionTable.result.map(_.sortBy(_.createdOn).map(_.asRelationship)))
    }

    override def getMission(uuid: UUID): Future[Option[Mission]] = {
      db.run(getMissionAction(uuid).map(_.map(_.asMission)))
    }

    override def getBacklogItems: Future[Seq[BacklogItem]] = {
      db.run(BacklogItemTable.result.map(_.sortBy(_.createdOn).map(_.asBacklogItem)))
    }

    override def getBacklogItem(uuid: UUID): Future[Option[BacklogItem]] = {
      db.run(getBacklogItemAction(uuid).map(_.map(_.asBacklogItem)))
    }

    override def getEpochs: Future[Seq[Epoch]] = {
      db.run(getEpochsAction.map(_.sortBy(_.createdOn).map(_.asEpoch)))
    }

    override def getEpoch(uuid: UUID): Future[Option[Epoch]] = {
      db.run(getEpochAction(uuid).map(_.map(_.asEpoch)))
    }

    override def getYears: Future[Seq[Year]] = {
      db.run(YearTable.result.map(_.sortBy(_.startDate).map(_.asYear)))
    }

    override def getYear(uuid: UUID): Future[Option[Year]] = {
      db.run(getYearAction(uuid).map(_.map(_.asYear)))
    }

    override def getThemes: Future[Seq[Theme]] = {
      db.run(ThemeTable.result.map(_.sortBy(_.createdOn).map(_.asTheme)))
    }

    override def getTheme(uuid: UUID): Future[Option[Theme]] = {
      db.run(getThemeAction(uuid).map(_.map(_.asTheme)))
    }

    override def getGoals: Future[Seq[Goal]] = {
      db.run(getGoalsAction.map(_.sortBy(_._1.createdOn).map(_.asGoal)))
    }

    override def getGoal(uuid: UUID): Future[Option[Goal]] = {
      db.run(getGoalAction(uuid).map(_.map(_.asGoal)))
    }

    override def getThreads: Future[Seq[Thread]] = {
      db.run(getThreadsAction.map(_.sortBy(_._1.createdOn).map(_.asThread)))
    }

    override def getThreads(goalId: UUID): Future[Seq[Thread]] = {
      db.run(getThreadsByParent(goalId).map(_.sortBy(_._1.createdOn).map(_.asThread)))
    }

    override def getThread(uuid: UUID): Future[Option[Thread]] = {
      db.run(getThreadAction(uuid).map(_.map(_.asThread)))
    }

    override def getWeaves: Future[Seq[Weave]] = {
      db.run(getWeavesAction.map(_.sortBy(_._1.createdOn).map(_.asWeave)))
    }

    override def getWeaves(goalId: UUID): Future[Seq[Weave]] = {
      db.run(getWeavesByParent(goalId).map(_.sortBy(_._1.createdOn).map(_.asWeave)))
    }

    override def getWeave(uuid: UUID): Future[Option[Weave]] = {
      db.run(getWeaveAction(uuid).map(_.map(_.asWeave)))
    }

    override def getLaserDonuts: Future[Seq[LaserDonut]] = {
      db.run(getLaserDonutsAction.map(_.sortBy(_._1.createdOn).map(_.asLaserDonut)))
    }

    override def getLaserDonuts(goalId: UUID): Future[Seq[LaserDonut]] = {
      db.run(getLaserDonutsByParent(goalId).map(_.sortBy(_._1.createdOn).map(_.asLaserDonut)))
    }

    override def getLaserDonut(uuid: UUID): Future[Option[LaserDonut]] = {
      db.run(getLaserDonutAction(uuid).map(_.map(_.asLaserDonut)))
    }

    override def getCurrentLaserDonut: Future[Option[LaserDonut]] = {
      val action = (for {
        currentActivity <- CurrentActivityTable.result
        laserDonuts <- getLaserDonutsAction(currentActivity.map(_.currentLaserDonut))
      } yield laserDonuts.headOption).map(_.map(_.asLaserDonut))
      db.run(action)
    }

    override def getPortions: Future[Seq[Portion]] = {
      db.run(getPortionsAction.map(_.map(_.asPortion)))
    }

    override def getPortions(laserDonutId: UUID): Future[Seq[Portion]] = {
      db.run(getPortionsByParent(laserDonutId).map(_.map(_.asPortion)))
    }

    override def getPortion(uuid: UUID): Future[Option[Portion]] = {
      db.run(getPortionAction(uuid).map(_.map(_.asPortion)))
    }

    override def getCurrentPortion: Future[Option[Portion]] = {
      val currentPortionRow = (for {
        currentActivity <- CurrentActivityTable
        portionRow <- PortionTable if portionRow.id === currentActivity.currentPortion
      } yield portionRow).result.headOption

      val action = for {
        portionRow <- currentPortionRow
        currentPortion <- portionRow.map(row => getPortionAction(UUID.fromString(row.uuid))).getOrElse(DBIOAction.successful(None))
      } yield currentPortion

      db.run(action).map(_.map(_.asPortion))
    }

    override def getTodos: Future[Seq[Todo]] = {
      db.run(getTodosAction.map(_.map(_.asTodo)))
    }

    override def getTodos(parentId: UUID): Future[Seq[Todo]] = {
      db.run(getTodosByParent(parentId).map(_.map(_.asTodo)))
    }

    override def getTodo(uuid: UUID): Future[Option[Todo]] = {
      db.run(getTodoAction(uuid).map(_.map(_.asTodo)))
    }

    override def getHobbies: Future[Seq[Hobby]] = {
      db.run(getHobbiesAction.map(_.sortBy(_._1.createdOn).map(_.asHobby)))
    }

    override def getHobbies(goalId: UUID): Future[Seq[Hobby]] = {
      db.run(getHobbiesByParent(goalId).map(_.sortBy(_._1.createdOn).map(_.asHobby)))
    }

    override def getHobby(uuid: UUID): Future[Option[Hobby]] = {
      db.run(getHobbyAction(uuid).map(_.map(_.asHobby)))
    }

    override def getOneOffs: Future[Seq[OneOff]] = {
      db.run(getOneOffsAction.map(_.map(_.asOneOff)))
    }

    override def getOneOff(uuid: UUID): Future[Option[OneOff]] = {
      db.run(getOneOffAction(uuid).map(_.map(_.asOneOff)))
    }

    override def getScheduledOneOffs(date: Option[LocalDate]): Future[Seq[ScheduledOneOff]] = {
      val retrieval = date.map { chosenDate =>
        getScheduledOneOffsAction
          .map(_.filter(_._1.occursOn.toLocalDateTime.toLocalDate == chosenDate))
      }.getOrElse(getScheduledOneOffsAction)
      val action = retrieval.map(_.sortBy(_._1.occursOn).map(_.asScheduledOneOff))
      db.run(action)
    }

    override def getScheduledOneOff(uuid: UUID): Future[Option[ScheduledOneOff]] = {
      db.run(getScheduledOneOffAction(uuid).map(_.map(_.asScheduledOneOff)))
    }

    override def getPyramidOfImportance: Future[Option[PyramidOfImportance]] = {
      db.run(getPyramidOfImportanceAction)
    }

    // Delete endpoints
    override def deleteSkillCategory(uuid: UUID): Future[Boolean] = {
      val action = (for {
        skillCategory <- getSkillCategoryAction(uuid).map(_.getOrElse(throw SkillCategoryNotFoundException()))
        _ <- SkillTable.filter(_.parentCategory === skillCategory._1.id).delete
        result <- SkillCategoryTable.filter(_.id === skillCategory._1.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteSkill(uuid: UUID): Future[Boolean] = {
      val action = (for {
        skill <- getSkillAction(uuid).map(_.getOrElse(throw SkillNotFoundException()))
        _ <- GoalSkillTable.filter(_.skillId === skill._1.id).delete
        _ <- ThreadSkillTable.filter(_.skillId === skill._1.id).delete
        _ <- WeaveSkillTable.filter(_.skillId === skill._1.id).delete
        _ <- LaserDonutSkillTable.filter(_.skillId === skill._1.id).delete
        _ <- PortionSkillTable.filter(_.skillId === skill._1.id).delete
        _ <- HobbySkillTable.filter(_.skillId === skill._1.id).delete
        _ <- OneOffSkillTable.filter(_.skillId === skill._1.id).delete
        _ <- ScheduledOneOffSkillTable.filter(_.skillId === skill._1.id).delete
        result <- SkillTable.filter(_.id === skill._1.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteRelationship(uuid: UUID): Future[Boolean] = {
      val action = (for {
        relationship <- getRelationshipAction(uuid).map(_.getOrElse(throw RelationshipNotFoundException()))
        _ <- GoalRelationshipTable.filter(_.relationshipId === relationship.id).delete
        _ <- ThreadRelationshipTable.filter(_.relationshipId === relationship.id).delete
        _ <- WeaveRelationshipTable.filter(_.relationshipId === relationship.id).delete
        _ <- LaserDonutRelationshipTable.filter(_.relationshipId === relationship.id).delete
        _ <- PortionRelationshipTable.filter(_.relationshipId === relationship.id).delete
        _ <- HobbyRelationshipTable.filter(_.relationshipId === relationship.id).delete
        _ <- OneOffRelationshipTable.filter(_.relationshipId === relationship.id).delete
        _ <- ScheduledOneOffRelationshipTable.filter(_.relationshipId === relationship.id).delete
        result <- RelationshipTable.filter(_.id === relationship.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteMission(uuid: UUID): Future[Boolean] = {
      val action = (for {
        mission <- getMissionAction(uuid).map(_.getOrElse(throw MissionNotFoundException()))
        _ <- EpochMissionTable.filter(_.missionId === mission.id).delete
        _ <- GoalMissionTable.filter(_.missionId === mission.id).delete
        _ <- ThreadMissionTable.filter(_.missionId === mission.id).delete
        _ <- WeaveMissionTable.filter(_.missionId === mission.id).delete
        _ <- LaserDonutMissionTable.filter(_.missionId === mission.id).delete
        _ <- PortionMissionTable.filter(_.missionId === mission.id).delete
        _ <- HobbyRMissionTable.filter(_.missionId === mission.id).delete
        _ <- OneOffMissionTable.filter(_.missionId === mission.id).delete
        _ <- ScheduledOneOffMissionTable.filter(_.missionId === mission.id).delete
        result <- MissionTable.filter(_.id === mission.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteBacklogItem(uuid: UUID): Future[Boolean] = {
      val action = backlogItemByUuid(uuid).delete
      db.run(action).map(_ > 0)
    }

    override def deleteEpoch(uuid: UUID): Future[Boolean] = {
      val action = (for {
        epochAndMissions <- getEpochAction(uuid).map(_.getOrElse(throw EpochNotFoundException()))
        (epoch, _) = epochAndMissions
        _ <- EpochMissionTable.filter(_.epochId === epoch.id).delete
        result <- EpochTable.filter(_.id === epoch.id).delete
      } yield result).transactionally
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
        maybeObj map { case (goal, _, _, _, _) =>
          for {
            _ <- GoalBacklogItemTable.filter(_.goalId === goal.id).delete
            _ <- GoalSkillTable.filter(_.goalId === goal.id).delete
            _ <- GoalRelationshipTable.filter(_.goalId === goal.id).delete
            _ <- GoalMissionTable.filter(_.goalId === goal.id).delete
            result <- GoalTable.filter(_.id === goal.id).delete
          } yield result
        } getOrElse DBIO.failed(GoalNotFoundException())
      }.transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteThread(uuid: UUID): Future[Boolean] = {
      val action = (for {
        threadAndSkills <- getThreadAction(uuid).map(_.getOrElse(throw ThreadNotFoundException()))
        (thread, _, _, _) = threadAndSkills
        _ <- ThreadSkillTable.filter(_.threadId === thread.id).delete
        _ <- ThreadRelationshipTable.filter(_.threadId === thread.id).delete
        _ <- ThreadMissionTable.filter(_.threadId === thread.id).delete
        result <- ThreadTable.filter(_.id === thread.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteWeave(uuid: UUID): Future[Boolean] = {
      val action = (for {
        weaveAndSkills <- getWeaveAction(uuid).map(_.getOrElse(throw WeaveNotFoundException()))
        (weave, _, _, _) = weaveAndSkills
        _ <- WeaveSkillTable.filter(_.weaveId === weave.id).delete
        _ <- WeaveRelationshipTable.filter(_.weaveId === weave.id).delete
        _ <- WeaveMissionTable.filter(_.weaveId === weave.id).delete
        result <- WeaveTable.filter(_.id === weave.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteLaserDonut(uuid: UUID): Future[Boolean] = {
      val action = (for {
        laserDonutAndSkills <- getLaserDonutAction(uuid).map(_.getOrElse(throw LaserDonutNotFoundException()))
        (laserDonut, _, _, _) = laserDonutAndSkills
        _ <- LaserDonutSkillTable.filter(_.laserDonutId === laserDonut.id).delete
        _ <- LaserDonutRelationshipTable.filter(_.laserDonutId === laserDonut.id).delete
        _ <- LaserDonutMissionTable.filter(_.laserDonutId === laserDonut.id).delete
        result <- LaserDonutTable.filter(_.id === laserDonut.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deletePortion(uuid: UUID): Future[Boolean] = {
      val action = (for {
        portionAndSkills <- getPortionAction(uuid).map(_.getOrElse(throw PortionNotFoundException()))
        (portion, _, _, _) = portionAndSkills
        _ <- PortionSkillTable.filter(_.portionId === portion.id).delete
        _ <- PortionRelationshipTable.filter(_.portionId === portion.id).delete
        _ <- PortionMissionTable.filter(_.portionId === portion.id).delete
        result <- PortionTable.filter(_.id === portion.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteTodo(uuid: UUID): Future[Boolean] = {
      val action = (for {
        todo <- getTodoAction(uuid).map(_.getOrElse(throw TodoNotFoundException()))
        result <- TodoTable.filter(_.id === todo.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteHobby(uuid: UUID): Future[Boolean] = {
      val action = (for {
        hobbyAndSkills <- getHobbyAction(uuid).map(_.getOrElse(throw HobbyNotFoundException()))
        (hobby, _, _, _) = hobbyAndSkills
        _ <- HobbySkillTable.filter(_.hobbyId === hobby.id).delete
        _ <- HobbyRelationshipTable.filter(_.hobbyId === hobby.id).delete
        _ <- HobbyMissionTable.filter(_.hobbyId === hobby.id).delete
        result <- HobbyTable.filter(_.id === hobby.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteOneOff(uuid: UUID): Future[Boolean] = {
      val action = (for {
        oneOffAndSkills <- getOneOffAction(uuid).map(_.getOrElse(throw OneOffNotFoundException()))
        (oneOff, _, _, _) = oneOffAndSkills
        _ <- OneOffSkillTable.filter(_.oneOffId === oneOff.id).delete
        _ <- OneOffRelationshipTable.filter(_.oneOffId === oneOff.id).delete
        _ <- OneOffMissionTable.filter(_.oneOffId === oneOff.id).delete
        result <- OneOffTable.filter(_.id === oneOff.id).delete
      } yield result).transactionally
      db.run(action).map(_ > 0)
    }

    override def deleteScheduledOneOff(uuid: UUID): Future[Boolean] = {
      val action = (for {
        scheduledOneOffAndSkills <- getScheduledOneOffAction(uuid).map(_.getOrElse(throw ScheduledOneOffNotFoundException()))
        (scheduledOneOff, _, _, _) = scheduledOneOffAndSkills
        _ <- ScheduledOneOffSkillTable.filter(_.scheduledOneOffId === scheduledOneOff.id).delete
        _ <- ScheduledOneOffRelationshipTable.filter(_.scheduledOneOffId === scheduledOneOff.id).delete
        _ <- ScheduledOneOffMissionTable.filter(_.scheduledOneOffId === scheduledOneOff.id).delete
        result <- ScheduledOneOffTable.filter(_.id === scheduledOneOff.id).delete
      } yield result).transactionally
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

    private def insertLaserDonutSkillsAction(laserDonutId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[LaserDonutSkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        laserDonutSkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          LaserDonutSkillRow(
            laserDonutId = laserDonutId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- LaserDonutSkillTable ++= laserDonutSkills
      } yield laserDonutSkills
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

    private def insertPortionSkillsAction(portionId: Long, associatedSkills: Seq[AssociatedSkill]): DBIO[Seq[PortionSkillRow]] = {
      for {
        skills <- getSkillsAction(associatedSkills.map(_.skillId))
        portionSkills = skills.zip(associatedSkills).map { case (skill, associatedSkill) =>
          PortionSkillRow(
            portionId = portionId,
            skillId = skill.id,
            relevance = associatedSkill.relevance.dbValue,
            level = associatedSkill.level.dbValue
          )
        }
        _ <- PortionSkillTable ++= portionSkills
      } yield portionSkills
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

    private def insertGoalRelationshipsAction(goalId: Long, relationshipIds: Seq[UUID]): DBIO[Seq[GoalRelationshipRow]] = {
      for {
        relationships <- getRelationshipsAction(relationshipIds)
        goalRelationships = relationships.map { relationship =>
          GoalRelationshipRow(
            goalId = goalId,
            relationshipId = relationship.id
          )
        }
        _ <- GoalRelationshipTable ++= goalRelationships
      } yield goalRelationships
    }

    private def insertThreadRelationshipsAction(threadId: Long, relationshipIds: Seq[UUID]): DBIO[Seq[ThreadRelationshipRow]] = {
      for {
        relationships <- getRelationshipsAction(relationshipIds)
        threadRelationships = relationships.map { relationship =>
          ThreadRelationshipRow(
            threadId = threadId,
            relationshipId = relationship.id
          )
        }
        _ <- ThreadRelationshipTable ++= threadRelationships
      } yield threadRelationships
    }

    private def insertWeaveRelationshipsAction(weaveId: Long, relationshipIds: Seq[UUID]): DBIO[Seq[WeaveRelationshipRow]] = {
      for {
        relationships <- getRelationshipsAction(relationshipIds)
        weaveRelationships = relationships.map { relationship =>
          WeaveRelationshipRow(
            weaveId = weaveId,
            relationshipId = relationship.id
          )
        }
        _ <- WeaveRelationshipTable ++= weaveRelationships
      } yield weaveRelationships
    }

    private def insertLaserDonutRelationshipsAction(laserDonutId: Long, relationshipIds: Seq[UUID]): DBIO[Seq[LaserDonutRelationshipRow]] = {
      for {
        relationships <- getRelationshipsAction(relationshipIds)
        laserDonutRelationships = relationships.map { relationship =>
          LaserDonutRelationshipRow(
            laserDonutId = laserDonutId,
            relationshipId = relationship.id
          )
        }
        _ <- LaserDonutRelationshipTable ++= laserDonutRelationships
      } yield laserDonutRelationships
    }

    private def insertPortionRelationshipsAction(portionId: Long, relationshipIds: Seq[UUID]): DBIO[Seq[PortionRelationshipRow]] = {
      for {
        relationships <- getRelationshipsAction(relationshipIds)
        portionRelationships = relationships.map { relationship =>
          PortionRelationshipRow(
            portionId = portionId,
            relationshipId = relationship.id
          )
        }
        _ <- PortionRelationshipTable ++= portionRelationships
      } yield portionRelationships
    }

    private def insertHobbyRelationshipsAction(hobbyId: Long, relationshipIds: Seq[UUID]): DBIO[Seq[HobbyRelationshipRow]] = {
      for {
        relationships <- getRelationshipsAction(relationshipIds)
        hobbyRelationships = relationships.map { relationship =>
          HobbyRelationshipRow(
            hobbyId = hobbyId,
            relationshipId = relationship.id
          )
        }
        _ <- HobbyRelationshipTable ++= hobbyRelationships
      } yield hobbyRelationships
    }

    private def insertOneOffRelationshipsAction(oneOffId: Long, relationshipIds: Seq[UUID]): DBIO[Seq[OneOffRelationshipRow]] = {
      for {
        relationships <- getRelationshipsAction(relationshipIds)
        oneOffRelationships = relationships.map { relationship =>
          OneOffRelationshipRow(
            oneOffId = oneOffId,
            relationshipId = relationship.id
          )
        }
        _ <- OneOffRelationshipTable ++= oneOffRelationships
      } yield oneOffRelationships
    }

    private def insertScheduledOneOffRelationshipsAction(scheduledOneOffId: Long, relationshipIds: Seq[UUID]): DBIO[Seq[ScheduledOneOffRelationshipRow]] = {
      for {
        relationships <- getRelationshipsAction(relationshipIds)
        scheduledOneOffRelationships = relationships.map { relationship =>
          ScheduledOneOffRelationshipRow(
            scheduledOneOffId = scheduledOneOffId,
            relationshipId = relationship.id
          )
        }
        _ <- ScheduledOneOffRelationshipTable ++= scheduledOneOffRelationships
      } yield scheduledOneOffRelationships
    }

    private def insertEpochMissionsAction(epochId: Long, associatedMissions: Seq[AssociatedMission]): DBIO[Seq[EpochMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions.map(_.missionId))
        epochMissions = missions.zip(associatedMissions).map { case (mission, associatedMission) =>
          EpochMissionRow(
            epochId = epochId,
            missionId = mission.id,
            level = associatedMission.level.dbValue
          )
        }
        _ <- EpochMissionTable ++= epochMissions
      } yield epochMissions
    }

    private def insertGoalMissionsAction(goalId: Long, associatedMissions: Seq[UUID]): DBIO[Seq[GoalMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions)
        goalMissions = missions.map { mission =>
          GoalMissionRow(
            goalId = goalId,
            missionId = mission.id
          )
        }
        _ <- GoalMissionTable ++= goalMissions
      } yield goalMissions
    }

    private def insertThreadMissionsAction(threadId: Long, associatedMissions: Seq[UUID]): DBIO[Seq[ThreadMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions)
        threadMissions = missions.map { mission =>
          ThreadMissionRow(
            threadId = threadId,
            missionId = mission.id
          )
        }
        _ <- ThreadMissionTable ++= threadMissions
      } yield threadMissions
    }

    private def insertWeaveMissionsAction(weaveId: Long, associatedMissions: Seq[UUID]): DBIO[Seq[WeaveMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions)
        weaveMissions = missions.map { mission =>
          WeaveMissionRow(
            weaveId = weaveId,
            missionId = mission.id
          )
        }
        _ <- WeaveMissionTable ++= weaveMissions
      } yield weaveMissions
    }

    private def insertLaserDonutMissionsAction(laserDonutId: Long, associatedMissions: Seq[UUID]): DBIO[Seq[LaserDonutMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions)
        laserDonutMissions = missions.map { mission =>
          LaserDonutMissionRow(
            laserDonutId = laserDonutId,
            missionId = mission.id
          )
        }
        _ <- LaserDonutMissionTable ++= laserDonutMissions
      } yield laserDonutMissions
    }

    private def insertPortionMissionsAction(portionId: Long, associatedMissions: Seq[UUID]): DBIO[Seq[PortionMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions)
        portionMissions = missions.map { mission =>
          PortionMissionRow(
            portionId = portionId,
            missionId = mission.id
          )
        }
        _ <- PortionMissionTable ++= portionMissions
      } yield portionMissions
    }

    private def insertHobbyMissionsAction(hobbyId: Long, associatedMissions: Seq[UUID]): DBIO[Seq[HobbyMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions)
        hobbyMissions = missions.map { mission =>
          HobbyMissionRow(
            hobbyId = hobbyId,
            missionId = mission.id
          )
        }
        _ <- HobbyMissionTable ++= hobbyMissions
      } yield hobbyMissions
    }

    private def insertOneOffMissionsAction(oneOffId: Long, associatedMissions: Seq[UUID]): DBIO[Seq[OneOffMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions)
        oneOffMissions = missions.map { mission =>
          OneOffMissionRow(
            oneOffId = oneOffId,
            missionId = mission.id
          )
        }
        _ <- OneOffMissionTable ++= oneOffMissions
      } yield oneOffMissions
    }

    private def insertScheduledOneOffMissionsAction(scheduledOneOffId: Long, associatedMissions: Seq[UUID]): DBIO[Seq[ScheduledOneOffMissionRow]] = {
      for {
        missions <- getMissionsAction(associatedMissions)
        scheduledOneOffMissions = missions.map { case mission =>
          ScheduledOneOffMissionRow(
            scheduledOneOffId = scheduledOneOffId,
            missionId = mission.id
          )
        }
        _ <- ScheduledOneOffMissionTable ++= scheduledOneOffMissions
      } yield scheduledOneOffMissions
    }

    private def getSkillCategoryAction(uuid: UUID) = {
      for {
        category <- skillCategoryByUuid(uuid).result.headOption
        parent <- SkillCategoryTable.filter(_.id inSet category.toSeq.flatMap(_.parentCategory)).result.headOption
      } yield (category, parent.map(_.uuid)) match {
        case (Some(cat), parentId) => Some((cat, parentId))
        case _ => None
      }
    }

    private def getSkillCategoryAction(id: Long) = {
      for {
        category <- skillCategoryById(id).result.headOption
        parent <- SkillCategoryTable.filter(_.id inSet category.toSeq.flatMap(_.parentCategory)).result.headOption
      } yield (category, parent.map(_.uuid))
    }

    private def getSkillAction(uuid: UUID) = {
      for {
        skill <- skillByUuid(uuid).result.headOption
        category <- SkillCategoryTable.filter(_.id inSet skill.toSeq.map(_.parentCategory)).result.headOption
      } yield (skill, category) match {
        case (Some(skl), Some(cat)) => Some((skl, cat.uuid))
        case _ => None
      }
    }

    private def getSkillAction(id: Long) = {
      for {
        skill <- skillById(id).result.headOption
        category <- SkillCategoryTable.filter(_.id inSet skill.toSeq.map(_.parentCategory)).result.headOption
      } yield (skill, category) match {
        case (Some(skl), Some(cat)) => Some((skl, cat.uuid))
        case _ => None
      }
    }

    private def getRelationshipAction(uuid: UUID) = {
      relationshipByUuid(uuid).result.headOption
    }

    private def getMissionAction(uuid: UUID) = {
      missionByUuid(uuid).result.headOption
    }

    private def getBacklogItemsAction(uuids: Seq[UUID]) = {
      backlogItemsByUuid(uuids).result
    }

    private def getBacklogItemAction(uuid: UUID) = {
      backlogItemByUuid(uuid).result.headOption
    }

    private def getEpochsAction = {
      epochWithExtrasByUuid(uuid).map(groupByEpoch(_))
    }

    private def getEpochAction(uuid: UUID) = {
      epochWithExtrasByUuid(uuid).map(groupByEpoch(_).headOption)
    }

    private def getYearAction(uuid: UUID) = {
      yearByUuid(uuid).result.headOption
    }

    private def getThemeAction(uuid: UUID) = {
      themeByUuid(uuid).result.headOption
    }

    private def getGoalsAction: DBIO[Seq[(GoalRow, Seq[BacklogItemRow], Seq[UUID], Seq[(GoalSkillRow, UUID)], Seq[UUID])]] = {
      goalsWithExtras.map(groupByGoal(_))
    }

    private def getGoalAction(uuid: UUID): DBIO[Option[(GoalRow, Seq[BacklogItemRow], Seq[UUID], Seq[(GoalSkillRow, UUID)], Seq[UUID])]] = {
      goalWithExtrasByUuid(uuid).map(groupByGoal(_).headOption)
    }

    private def getThreadsAction = {
      threadsWithExtras.map(groupByThread(_))
    }

    private def getThreadAction(uuid: UUID) = {
      threadWithExtrasByUuid(uuid).map(groupByThread(_).headOption)
    }

    private def getThreadsByParent(parentId: UUID) = {
      threadsByParentId(parentId).map(groupByThread(_))
    }

    private def getWeavesAction = {
      weavesWithExtras.map(groupByWeave(_))
    }

    private def getWeavesByParent(parentId: UUID) = {
      weavesByParentId(parentId).map(groupByWeave(_))
    }

    private def getWeaveAction(uuid: UUID) = {
      weaveWithExtrasByUuid(uuid).map(groupByWeave(_).headOption)
    }

    private def getLaserDonutsAction = {
      laserDonutsWithExtras.map(groupByLaserDonut(_))
    }

    private def getLaserDonutsAction(ids: Seq[Long]) = {
      laserDonutWithExtrasByIds(ids).map(groupByLaserDonut(_))
    }

    private def getLaserDonutAction(uuid: UUID) = {
      laserDonutWithExtrasByUuid(uuid).map(groupByLaserDonut(_).headOption)
    }

    private def getLaserDonutAction(id: Long) = {
      laserDonutWithExtrasById(id).map(groupByLaserDonut(_).headOption)
    }

    private def getLaserDonutsByParent(parentId: UUID) = {
      laserDonutsByParentId(parentId).map(groupByLaserDonut(_))
    }

    private def getPortionsAction = {
      portionsWithExtras.map(groupByPortion).map(_.sortBy(_._1.order))
    }

    private def getPortionsAction(uuids: Seq[UUID]) = {
      portionsWithExtrasByUuid(uuids).map(groupByPortion(_)).map(_.sortBy(_._1.order))
    }

    private def getPortionsByParent(parentId: UUID) = {
      portionsByParentId(parentId).map(groupByPortion).map(_.sortBy(_._1.order))
    }

    private def getPortionAction(uuid: UUID) = {
      portionWithExtrasByUuid(uuid).map(groupByPortion(_).headOption)
    }

    private def getTodosAction = {
      TodoTable.result
    }

    private def getTodosAction(uuids: Seq[UUID]) = {
      TodoTable.filter(_.uuid inSet uuids).result
    }

    private def getTodoAction(uuid: UUID) = {
      todoByUuid(uuid).result.headOption
    }

    private def getTodosByParent(parentId: UUID) = {
      todosByParentId(parentId).result
    }

    private def getHobbiesAction = {
      hobbiesWithExtras.map(groupByHobby)
    }

    private def getHobbiesByParent(parentId: UUID) = {
      hobbiesByParentId(parentId).map(groupByHobby)
    }

    private def getHobbyAction(uuid: UUID) = {
      hobbyWithExtrasByUuid(uuid).map(groupByHobby(_).headOption)
    }

    private def getOneOffsAction = {
      oneOffsWithExtras.map(groupByOneOff).map(_.sortBy(_._1.order))
    }

    private def getOneOffsAction(uuids: Seq[UUID]) = {
      oneOffsWithExtrasByUuid(uuids).map(groupByOneOff).map(_.sortBy(_._1.order))
    }

    private def getOneOffAction(uuid: UUID) = {
      oneOffWithExtrasByUuid(uuid).map(groupByOneOff(_).headOption)
    }

    private def getScheduledOneOffsAction = {
      scheduledOneOffsWithExtras.map(groupByScheduledOneOff)
    }

    private def getScheduledOneOffsAction(uuids: Seq[UUID]) = {
      scheduledOneOffsWithExtrasByUuid(uuids).map(groupByScheduledOneOff)
    }

    private def getScheduledOneOffAction(uuid: UUID) = {
      scheduledOneOffWithExtrasByUuid(uuid).map(groupByScheduledOneOff(_).headOption)
    }

    private def getSkillsAction(uuids: Seq[UUID]) = {
      skillsByUuid(uuids).result
    }

    private def getRelationshipsAction(uuids: Seq[UUID]) = {
      relationshipsByUuid(uuids).result
    }

    private def getMissionsAction(uuids: Seq[UUID]) = {
      missionsByUuid(uuids).result
    }

    private def getPyramidOfImportanceAction = {
      (for {
        pyramidRow <- ScheduledLaserDonutTable
        laserDonutRow <- LaserDonutTable if laserDonutRow.id === pyramidRow.laserDonutId
      } yield (pyramidRow, laserDonutRow)).result.map(rows => if (rows.nonEmpty) Some(rows.asPyramid) else None)
    }

    // Queries
    private def skillCategoryByUuid(uuid: UUID) = {
      SkillCategoryTable.filter(_.uuid === uuid.toString)
    }

    private def skillCategoryById(id: Long) = {
      SkillCategoryTable.filter(_.id === id)
    }

    private def skillByUuid(uuid: UUID) = {
      SkillTable.filter(_.uuid === uuid.toString)
    }

    private def skillById(id: Long) = {
      SkillTable.filter(_.id === id)
    }

    private def skillsByUuid(uuids: Seq[UUID]) = {
      SkillTable.filter(_.uuid inSet uuids.map(_.toString))
    }

    private def relationshipByUuid(uuid: UUID) = {
      RelationshipTable.filter(_.uuid === uuid.toString)
    }

    private def relationshipById(id: Long) = {
      RelationshipTable.filter(_.id === id)
    }

    private def relationshipsByUuid(uuids: Seq[UUID]) = {
      RelationshipTable.filter(_.uuid inSet uuids.map(_.toString))
    }

    private def missionByUuid(uuid: UUID) = {
      MissionTable.filter(_.uuid === uuid.toString)
    }

    private def missionById(id: Long) = {
      MissionTable.filter(_.id === id)
    }

    private def missionsByUuid(uuids: Seq[UUID]) = {
      MissionTable.filter(_.uuid inSet uuids.map(_.toString))
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

    private def epochWithExtrasByUuid(uuid: UUID) = {
      epochsWithExtras.map(_.filter { case (epoch, _) => epoch.uuid === uuid.toString })
    }

    private def epochsWithExtras = {
      EpochTable
        .joinLeft(EpochMissionTable)
        .on(_.id === _.epochId)
        .joinLeft(MissionTable)
        .on { case ((_, epochMission), mission) => epochMission.map(_.missionId).getOrElse(-1L) === mission.id }
        .map { case ((epoch, missionLink), mission) => (epoch, missionLink, mission) }
        .result.map {
        _.collect {
          case (epoch, missionLink, mission) =>
            val missionTuple = (missionLink, mission) match {
              case (Some(link), Some(msn)) => Some((link, msn))
              case _ => None
            }
            (epoch, missionTuple)
        }
      }
    }

    private def groupByEpoch(epochsWithExtras: Seq[(EpochRow, Option[(EpochMissionRow, MissionRow)])]): Seq[(EpochRow, Seq[(EpochMissionRow, UUID)])] = {
      epochsWithExtras.groupBy { case (epoch, _) => epoch }
        .map { case (epoch, links) =>
          (epoch, links.flatMap(_._2.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))))
        }.toSeq
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
      goalsWithExtras.map(_.filter { case (goal, _, _, _, _) => goal.uuid === uuid.toString })
    }

    private def goalsWithExtras = {
      GoalTable
        .joinLeft(GoalBacklogItemTable)
        .on(_.id === _.goalId)
        .joinLeft(BacklogItemTable)
        .on { case ((_, goalBacklogItem), backlogItem) => goalBacklogItem.map(_.backlogItemId).getOrElse(-1L) === backlogItem.id }
        .joinLeft(GoalMissionTable)
        .on(_._1._1.id === _.goalId)
        .joinLeft(MissionTable)
        .on { case ((((_, _), _), missionLink), mission) => missionLink.map(_.missionId).getOrElse(-1L) === mission.id }
        .joinLeft(GoalSkillTable)
        .on(_._1._1._1._1.id === _.goalId)
        .joinLeft(SkillTable)
        .on { case ((((((_, _), _), _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .joinLeft(GoalRelationshipTable)
        .on(_._1._1._1._1._1._1.id === _.goalId)
        .joinLeft(RelationshipTable)
        .on { case (((((((_, _), _), _), _), _), relationshipLink), relationship) => relationshipLink.map(_.relationshipId).getOrElse(-1L) === relationship.id }
        .map { case ((((((((goal, _), backlogItem), _), mission), skillLink), skill), _), relationship) => (goal, backlogItem, mission, skillLink, skill, relationship) }
        .result.map {
          _.collect {
            case (goal, backlogItem, mission, skillLink, skill, relationship) =>
              val skillTuple = (skillLink, skill) match {
                case (Some(link), Some(skl)) => Some((link, skl))
                case _ => None
              }
              (goal, backlogItem, mission, skillTuple, relationship)
            }
          }
    }

    private def groupByGoal(goalsWithExtras: Seq[(GoalRow, Option[BacklogItemRow], Option[MissionRow],
      Option[(GoalSkillRow, SkillRow)], Option[RelationshipRow])]): Seq[(GoalRow, Seq[BacklogItemRow],
      Seq[UUID], Seq[(GoalSkillRow, UUID)], Seq[UUID])] = {
      goalsWithExtras.groupBy { case (goal, _, _, _, _) => goal }
        .map { case (goal, links) =>
          (goal, links.flatMap(_._2), links.flatMap(_._3.map(x => UUID.fromString(x.uuid))),
            links.flatMap(_._4.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))),
            links.flatMap(_._5.map(x => UUID.fromString(x.uuid))))
        }.toSeq
    }

    private def threadByUuid(uuid: UUID) = {
      ThreadTable.filter(_.uuid === uuid.toString)
    }

    private def threadsByParentId(goalId: UUID) = {
      threadsWithExtras.map(_.filter(_._1.goalId.contains(goalId.toString)))
    }

    private def threadWithExtrasByUuid(uuid: UUID) = {
      threadsWithExtras.map(_.filter { case (thread, _, _, _) => thread.uuid == uuid.toString })
    }

    private def threadsWithExtras = {
      ThreadTable
        .joinLeft(ThreadMissionTable)
        .on(_.id === _.threadId)
        .joinLeft(MissionTable)
        .on { case ((_, missionLink), mission) => missionLink.map(_.missionId).getOrElse(-1L) === mission.id }
        .joinLeft(ThreadSkillTable)
        .on(_._1._1.id === _.threadId)
        .joinLeft(SkillTable)
        .on { case ((((_, _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .joinLeft(ThreadRelationshipTable)
        .on(_._1._1._1._1.id === _.threadId)
        .joinLeft(RelationshipTable)
        .on { case (((((_, _), _), _), relationshipLink), relationship) => relationshipLink.map(_.relationshipId).getOrElse(-1L) === relationship.id }
        .map { case ((((((thread, _), mission), skillLink), skill), _), relationship) => (thread, mission, skillLink, skill, relationship) }
        .result.map {
        _.collect {
          case (thread, mission, skillLink, skill, relationship) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (thread, mission, skillTuple, relationship)
        }
      }
    }

    private def groupByThread(threadsWithExtras: Seq[(ThreadRow, Option[MissionRow], Option[(ThreadSkillRow, SkillRow)],
      Option[RelationshipRow])]): Seq[(ThreadRow, Seq[UUID], Seq[(ThreadSkillRow, UUID)], Seq[UUID])] = {
      threadsWithExtras.groupBy { case (thread, _, _, _) => thread }
        .map { case (thread, links) =>
          (thread, links.flatMap(_._2.map(x => UUID.fromString(x.uuid))),
            links.flatMap(_._3.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))),
            links.flatMap(_._4.map(x => UUID.fromString(x.uuid))))
        }.toSeq
    }

    private def weaveByUuid(uuid: UUID) = {
      WeaveTable.filter(_.uuid === uuid.toString)
    }

    private def weavesByParentId(goalId: UUID) = {
      weavesWithExtras.map(_.filter(_._1.goalId.contains(goalId.toString)))
    }

    private def weaveWithExtrasByUuid(uuid: UUID) = {
      weavesWithExtras.map(_.filter { case (weave, _, _, _) => weave.uuid == uuid.toString })
    }

    private def weavesWithExtras = {
      WeaveTable
        .joinLeft(WeaveMissionTable)
        .on(_.id === _.weaveId)
        .joinLeft(MissionTable)
        .on { case ((_, missionLink), mission) => missionLink.map(_.missionId).getOrElse(-1L) === mission.id }
        .joinLeft(WeaveSkillTable)
        .on(_._1._1.id === _.weaveId)
        .joinLeft(SkillTable)
        .on { case ((((_, _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .joinLeft(WeaveRelationshipTable)
        .on(_._1._1._1._1.id === _.weaveId)
        .joinLeft(RelationshipTable)
        .on { case (((((_, _), _), _), relationshipLink), relationship) => relationshipLink.map(_.relationshipId).getOrElse(-1L) === relationship.id }
        .map { case ((((((weave, _), mission), skillLink), skill), _), relationship) => (weave, mission, skillLink, skill, relationship) }
        .result.map {
        _.collect {
          case (weave, mission, skillLink, skill, relationship) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (weave, mission, skillTuple, relationship)
        }
      }
    }

    private def groupByWeave(weavesWithExtras: Seq[(WeaveRow, Option[MissionRow], Option[(WeaveSkillRow, SkillRow)],
      Option[RelationshipRow])]): Seq[(WeaveRow, Seq[UUID], Seq[(WeaveSkillRow, UUID)], Seq[UUID])] = {
      weavesWithExtras.groupBy { case (weave, _, _, _) => weave }
        .map { case (weave, links) =>
          (weave, links.flatMap(_._2.map(x => UUID.fromString(x.uuid))),
            links.flatMap(_._3.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))),
            links.flatMap(_._4.map(x => UUID.fromString(x.uuid))))
        }.toSeq
    }

    private def laserDonutByUuid(uuid: UUID) = {
      LaserDonutTable.filter(_.uuid === uuid.toString)
    }

    private def laserDonutsByParentId(goalId: UUID) = {
      laserDonutsWithExtras.map(_.filter { case (laserDonut, _, _, _) => laserDonut.goalId == goalId.toString }.sortBy(_._1.order))
    }

    private def laserDonutWithExtrasByUuid(uuid: UUID) = {
      laserDonutsWithExtras.map(_.filter { case (laserDonut, _, _, _) => laserDonut.uuid == uuid.toString })
    }

    private def laserDonutWithExtrasById(id: Long) = {
      laserDonutsWithExtras.map(_.filter { case (laserDonut, _, _, _) => laserDonut.id == id })
    }

    private def laserDonutWithExtrasByIds(ids: Seq[Long]) = {
      laserDonutsWithExtras.map(_.filter { case (laserDonut, _, _, _) => ids.contains(laserDonut.id) })
    }

    private def laserDonutsWithExtras = {
      LaserDonutTable
        .joinLeft(LaserDonutMissionTable)
        .on(_.id === _.laserDonutId)
        .joinLeft(MissionTable)
        .on { case ((_, laserDonutLink), mission) => laserDonutLink.map(_.missionId).getOrElse(-1L) === mission.id }
        .joinLeft(LaserDonutSkillTable)
        .on(_._1._1.id === _.laserDonutId)
        .joinLeft(SkillTable)
        .on { case ((((_, _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .joinLeft(LaserDonutRelationshipTable)
        .on(_._1._1._1._1.id === _.laserDonutId)
        .joinLeft(RelationshipTable)
        .on { case (((((_, _), _), _), relationshipLink), relationship) => relationshipLink.map(_.relationshipId).getOrElse(-1L) === relationship.id }
        .map { case ((((((laserDonut, _), mission), skillLink), skill), _), relationship) => (laserDonut, mission, skillLink, skill, relationship) }
        .result.map {
        _.collect {
          case (laserDonut, mission, skillLink, skill, relationship) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (laserDonut, mission, skillTuple, relationship)
        }
      }
    }

    private def groupByLaserDonut(laserDonutsWithExtras: Seq[(LaserDonutRow, Option[MissionRow], Option[(LaserDonutSkillRow, SkillRow)],
      Option[RelationshipRow])]): Seq[(LaserDonutRow, Seq[UUID], Seq[(LaserDonutSkillRow, UUID)], Seq[UUID])] = {
      laserDonutsWithExtras.groupBy { case (laserDonut, _, _, _) => laserDonut }
        .map { case (laserDonut, links) =>
          (laserDonut, links.flatMap(_._2.map(x => UUID.fromString(x.uuid))),
            links.flatMap(_._3.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))),
            links.flatMap(_._4.map(x => UUID.fromString(x.uuid))))
        }.toSeq
    }

    private def portionByUuid(uuid: UUID) = {
      PortionTable.filter(_.uuid === uuid.toString)
    }

    private def todosByParentId(parentId: UUID) = {
      TodoTable.filter(_.parentId === parentId.toString).sortBy(_.order)
    }

    private def todoByUuid(uuid: UUID) = {
      TodoTable.filter(_.uuid === uuid.toString)
    }

    private def portionWithExtrasByUuid(uuid: UUID) = {
      portionsWithExtrasByUuid(Seq(uuid))
    }

    private def portionsWithExtrasByUuid(uuids: Seq[UUID]) = {
      portionsWithExtras.map(_.filter { case (portion, _, _, _) => uuids.map(_.toString).contains(portion.uuid) })
    }

    private def portionsByParentId(parentId: UUID) = {
      portionsWithExtras.map(_.filter(_._1.laserDonutId.contains(parentId.toString)).sortBy(_._1.order))
    }

    private def portionsWithExtras = {
      PortionTable
        .joinLeft(PortionMissionTable)
        .on(_.id === _.portionId)
        .joinLeft(MissionTable)
        .on { case ((_, portionLink), mission) => portionLink.map(_.missionId).getOrElse(-1L) === mission.id }
        .joinLeft(PortionSkillTable)
        .on(_._1._1.id === _.portionId)
        .joinLeft(SkillTable)
        .on { case ((((_, _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .joinLeft(PortionRelationshipTable)
        .on(_._1._1._1._1.id === _.portionId)
        .joinLeft(RelationshipTable)
        .on { case (((((_, _), _), _), relationshipLink), relationship) => relationshipLink.map(_.relationshipId).getOrElse(-1L) === relationship.id }
        .map { case ((((((portion, _), mission), skillLink), skill), _), relationship) => (portion, mission, skillLink, skill, relationship) }
        .result.map {
        _.collect {
          case (portion, mission, skillLink, skill, relationship) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (portion, mission, skillTuple, relationship)
        }
      }
    }

    private def groupByPortion(portionsWithExtras: Seq[(PortionRow, Option[MissionRow], Option[(PortionSkillRow, SkillRow)],
      Option[RelationshipRow])]): Seq[(PortionRow, Seq[UUID], Seq[(PortionSkillRow, UUID)], Seq[UUID])] = {
      portionsWithExtras.groupBy { case (portion, _, _, _) => portion }
        .map { case (portion, links) =>
          (portion, links.flatMap(_._2.map(x => UUID.fromString(x.uuid))),
            links.flatMap(_._3.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))),
            links.flatMap(_._4.map(x => UUID.fromString(x.uuid))))
        }.toSeq
    }

    private def hobbyByUuid(uuid: UUID) = {
      HobbyTable.filter(_.uuid === uuid.toString)
    }

    private def hobbiesByParentId(goalId: UUID) = {
      hobbiesWithExtras.map(_.filter(_._1.goalId.contains(goalId.toString)))
    }

    private def hobbyWithExtrasByUuid(uuid: UUID) = {
      hobbiesWithExtras.map(_.filter { case (todo, _, _, _) => todo.uuid == uuid.toString })
    }

    private def hobbiesWithExtras = {
      HobbyTable
        .joinLeft(HobbyMissionTable)
        .on(_.id === _.hobbyId)
        .joinLeft(MissionTable)
        .on { case ((_, hobbyLink), mission) => hobbyLink.map(_.missionId).getOrElse(-1L) === mission.id }
        .joinLeft(HobbySkillTable)
        .on(_._1._1.id === _.hobbyId)
        .joinLeft(SkillTable)
        .on { case ((((_, _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .joinLeft(HobbyRelationshipTable)
        .on(_._1._1._1._1.id === _.hobbyId)
        .joinLeft(RelationshipTable)
        .on { case (((((_, _), _), _), relationshipLink), relationship) => relationshipLink.map(_.relationshipId).getOrElse(-1L) === relationship.id }
        .map { case ((((((hobby, _), mission), skillLink), skill), _), relationship) => (hobby, mission, skillLink, skill, relationship) }
        .result.map {
        _.collect {
          case (hobby, mission, skillLink, skill, relationship) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (hobby, mission, skillTuple, relationship)
        }
      }
    }

    private def groupByHobby(hobbiesWithExtras: Seq[(HobbyRow, Option[MissionRow], Option[(HobbySkillRow, SkillRow)],
      Option[RelationshipRow])]): Seq[(HobbyRow, Seq[UUID], Seq[(HobbySkillRow, UUID)], Seq[UUID])] = {
      hobbiesWithExtras.groupBy { case (hobby, _, _, _) => hobby }
        .map { case (hobby, links) =>
          (hobby, links.flatMap(_._2.map(x => UUID.fromString(x.uuid))),
            links.flatMap(_._3.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))),
            links.flatMap(_._4.map(x => UUID.fromString(x.uuid))))
        }.toSeq
    }

    private def oneOffByUuid(uuid: UUID) = {
      OneOffTable.filter(_.uuid === uuid.toString)
    }

    private def oneOffWithExtrasByUuid(uuid: UUID) = {
      oneOffsWithExtras.map(_.filter { case (oneOff, _, _, _) => oneOff.uuid == uuid.toString })
    }

    private def oneOffsWithExtrasByUuid(uuids: Seq[UUID]) = {
      oneOffsWithExtras.map(_.filter { case (oneOff, _, _, _) => uuids.contains(oneOff.uuid) })
    }

    private def oneOffsWithExtras = {
      OneOffTable
        .joinLeft(OneOffMissionTable)
        .on(_.id === _.oneOffId)
        .joinLeft(MissionTable)
        .on { case ((_, oneOffLink), mission) => oneOffLink.map(_.missionId).getOrElse(-1L) === mission.id }
        .joinLeft(OneOffSkillTable)
        .on(_._1._1.id === _.oneOffId)
        .joinLeft(SkillTable)
        .on { case ((((_, _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .joinLeft(OneOffRelationshipTable)
        .on(_._1._1._1._1.id === _.oneOffId)
        .joinLeft(RelationshipTable)
        .on { case (((((_, _), _), _), relationshipLink), relationship) => relationshipLink.map(_.relationshipId).getOrElse(-1L) === relationship.id }
        .map { case ((((((oneOff, _), mission), skillLink), skill), _), relationship) => (oneOff, mission, skillLink, skill, relationship) }
        .result.map {
        _.collect {
          case (oneOff, mission, skillLink, skill, relationship) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (oneOff, mission, skillTuple, relationship)
        }
      }
    }

    private def groupByOneOff(oneOffsWithExtras: Seq[(OneOffRow, Option[MissionRow], Option[(OneOffSkillRow, SkillRow)],
      Option[RelationshipRow])]): Seq[(OneOffRow, Seq[UUID], Seq[(OneOffSkillRow, UUID)], Seq[UUID])] = {
      oneOffsWithExtras.groupBy { case (oneOff, _, _, _) => oneOff }
        .map { case (oneOff, links) =>
          (oneOff, links.flatMap(_._2.map(x => UUID.fromString(x.uuid))),
            links.flatMap(_._3.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))),
            links.flatMap(_._4.map(x => UUID.fromString(x.uuid))))
        }.toSeq
    }

    private def scheduledOneOffByUuid(uuid: UUID) = {
      ScheduledOneOffTable.filter(_.uuid === uuid.toString)
    }

    private def scheduledOneOffWithExtrasByUuid(uuid: UUID) = {
      scheduledOneOffsWithExtras.map(_.filter { case (oneOff, _, _, _) => oneOff.uuid == uuid.toString })
    }

    private def scheduledOneOffsWithExtrasByUuid(uuids: Seq[UUID]) = {
      scheduledOneOffsWithExtras.map(_.filter { case (oneOff, _, _, _) => uuids.contains(oneOff.uuid) })
    }

    private def scheduledOneOffsWithExtras = {
      ScheduledOneOffTable
        .joinLeft(ScheduledOneOffMissionTable)
        .on(_.id === _.scheduledOneOffId)
        .joinLeft(MissionTable)
        .on { case ((_, oneOffLink), mission) => oneOffLink.map(_.missionId).getOrElse(-1L) === mission.id }
        .joinLeft(ScheduledOneOffSkillTable)
        .on(_._1._1.id === _.scheduledOneOffId)
        .joinLeft(SkillTable)
        .on { case ((((_, _), _), skillLink), skill) => skillLink.map(_.skillId).getOrElse(-1L) === skill.id }
        .joinLeft(ScheduledOneOffRelationshipTable)
        .on(_._1._1._1._1.id === _.scheduledOneOffId)
        .joinLeft(RelationshipTable)
        .on { case (((((_, _), _), _), relationshipLink), relationship) => relationshipLink.map(_.relationshipId).getOrElse(-1L) === relationship.id }
        .map { case ((((((oneOff, _), mission), skillLink), skill), _), relationship) => (oneOff, mission, skillLink, skill, relationship) }
        .result.map {
        _.collect {
          case (oneOff, mission, skillLink, skill, relationship) =>
            val skillTuple = (skillLink, skill) match {
              case (Some(link), Some(skl)) => Some((link, skl))
              case _ => None
            }
            (oneOff, mission, skillTuple, relationship)
        }
      }
    }

    private def groupByScheduledOneOff(oneOffsWithExtras: Seq[(ScheduledOneOffRow, Option[MissionRow], Option[(ScheduledOneOffSkillRow, SkillRow)],
      Option[RelationshipRow])]): Seq[(ScheduledOneOffRow, Seq[UUID], Seq[(ScheduledOneOffSkillRow, UUID)], Seq[UUID])] = {
      oneOffsWithExtras.groupBy { case (oneOff, _, _, _) => oneOff }
        .map { case (oneOff, links) =>
          (oneOff, links.flatMap(_._2.map(x => UUID.fromString(x.uuid))),
            links.flatMap(_._3.map(tuple => (tuple._1, UUID.fromString(tuple._2.uuid)))),
            links.flatMap(_._4.map(x => UUID.fromString(x.uuid))))
        }.toSeq
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
