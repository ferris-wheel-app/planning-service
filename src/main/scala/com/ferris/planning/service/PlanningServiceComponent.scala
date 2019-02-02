package com.ferris.planning.service

import java.time.LocalDate
import java.util.UUID

import com.ferris.planning.command.Commands._
import com.ferris.planning.model.Model._
import com.ferris.planning.repo.PlanningRepositoryComponent

import scala.concurrent.{ExecutionContext, Future}

trait PlanningServiceComponent {
  val planningService: PlanningService

  trait PlanningService {
    def createRelationship(creation: CreateRelationship)(implicit ex: ExecutionContext): Future[Relationship]
    def createMission(creation: CreateMission)(implicit ex: ExecutionContext): Future[Mission]
    def createSkillCategory(creation: CreateSkillCategory)(implicit ex: ExecutionContext): Future[SkillCategory]
    def createSkill(creation: CreateSkill)(implicit ex: ExecutionContext): Future[Skill]
    def createBacklogItem(creation: CreateBacklogItem)(implicit ex: ExecutionContext): Future[BacklogItem]
    def createEpoch(creation: CreateEpoch)(implicit ex: ExecutionContext): Future[Epoch]
    def createYear(creation: CreateYear)(implicit ex: ExecutionContext): Future[Year]
    def createTheme(creation: CreateTheme)(implicit ex: ExecutionContext): Future[Theme]
    def createGoal(creation: CreateGoal)(implicit ex: ExecutionContext): Future[Goal]
    def createThread(creation: CreateThread)(implicit ex: ExecutionContext): Future[Thread]
    def createWeave(creation: CreateWeave)(implicit ex: ExecutionContext): Future[Weave]
    def createLaserDonut(creation: CreateLaserDonut)(implicit ex: ExecutionContext): Future[LaserDonut]
    def createPortion(creation: CreatePortion)(implicit ex: ExecutionContext): Future[Portion]
    def createTodo(creation: CreateTodo)(implicit ex: ExecutionContext): Future[Todo]
    def createHobby(creation: CreateHobby)(implicit ex: ExecutionContext): Future[Hobby]
    def createOneOff(creation: CreateOneOff)(implicit ex: ExecutionContext): Future[OneOff]
    def createScheduledOneOff(creation: CreateScheduledOneOff)(implicit ex: ExecutionContext): Future[ScheduledOneOff]
    def createPyramidOfImportance(pyramid: UpsertPyramidOfImportance)(implicit ex: ExecutionContext): Future[PyramidOfImportance]

    def updateRelationship(uuid: UUID, update: UpdateRelationship)(implicit ex: ExecutionContext): Future[Relationship]
    def updateMission(uuid: UUID, update: UpdateMission)(implicit ex: ExecutionContext): Future[Mission]
    def updateSkillCategory(uuid: UUID, update: UpdateSkillCategory)(implicit ex: ExecutionContext): Future[SkillCategory]
    def updateSkill(uuid: UUID, update: UpdateSkill)(implicit ex: ExecutionContext): Future[Skill]
    def updatePractisedHours(uuid: UUID, practisedHours: Long)(implicit ex: ExecutionContext): Future[Skill]
    def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem)(implicit ex: ExecutionContext): Future[BacklogItem]
    def updateEpoch(uuid: UUID, update: UpdateEpoch)(implicit ex: ExecutionContext): Future[Epoch]
    def updateYear(uuid: UUID, update: UpdateYear)(implicit ex: ExecutionContext): Future[Year]
    def updateTheme(uuid: UUID, update: UpdateTheme)(implicit ex: ExecutionContext): Future[Theme]
    def updateGoal(uuid: UUID, update: UpdateGoal)(implicit ex: ExecutionContext): Future[Goal]
    def updateThread(uuid: UUID, update: UpdateThread)(implicit ex: ExecutionContext): Future[Thread]
    def updateWeave(uuid: UUID, update: UpdateWeave)(implicit ex: ExecutionContext): Future[Weave]
    def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut)(implicit ex: ExecutionContext): Future[LaserDonut]
    def updatePortion(uuid: UUID, update: UpdatePortion)(implicit ex: ExecutionContext): Future[Portion]
    def updatePortions(laserDonutId: UUID, update: UpdateList)(implicit ex: ExecutionContext): Future[Seq[Portion]]
    def updateTodo(uuid: UUID, update: UpdateTodo)(implicit ex: ExecutionContext): Future[Todo]
    def updateTodos(parentId: UUID, update: UpdateList)(implicit ex: ExecutionContext): Future[Seq[Todo]]
    def updateHobby(uuid: UUID, update: UpdateHobby)(implicit ex: ExecutionContext): Future[Hobby]
    def updateOneOff(uuid: UUID, update: UpdateOneOff)(implicit ex: ExecutionContext): Future[OneOff]
    def updateOneOffs(update: UpdateList)(implicit ex: ExecutionContext): Future[Seq[OneOff]]
    def updateScheduledOneOff(uuid: UUID, update: UpdateScheduledOneOff)(implicit ex: ExecutionContext): Future[ScheduledOneOff]
    def refreshPyramidOfImportance()(implicit ex: ExecutionContext): Future[Boolean]
    def refreshPortion()(implicit ex: ExecutionContext): Future[Boolean]

    def getRelationships(implicit ex: ExecutionContext): Future[Seq[Relationship]]
    def getMissions(implicit ex: ExecutionContext): Future[Seq[Mission]]
    def getSkillCategories(implicit ex: ExecutionContext): Future[Seq[SkillCategory]]
    def getSkills(implicit ex: ExecutionContext): Future[Seq[Skill]]
    def getBacklogItems(implicit ex: ExecutionContext): Future[Seq[BacklogItem]]
    def getEpochs(implicit ex: ExecutionContext): Future[Seq[Epoch]]
    def getYears(implicit ex: ExecutionContext): Future[Seq[Year]]
    def getThemes(implicit ex: ExecutionContext): Future[Seq[Theme]]
    def getGoals(implicit ex: ExecutionContext): Future[Seq[Goal]]
    def getThreads()(implicit ex: ExecutionContext): Future[Seq[Thread]]
    def getThreads(goalId: UUID)(implicit ex: ExecutionContext): Future[Seq[Thread]]
    def getWeaves()(implicit ex: ExecutionContext): Future[Seq[Weave]]
    def getWeaves(goalId: UUID)(implicit ex: ExecutionContext): Future[Seq[Weave]]
    def getLaserDonuts()(implicit ex: ExecutionContext): Future[Seq[LaserDonut]]
    def getLaserDonuts(goalId: UUID)(implicit ex: ExecutionContext): Future[Seq[LaserDonut]]
    def getPortions()(implicit ex: ExecutionContext): Future[Seq[Portion]]
    def getPortions(laserDonutId: UUID)(implicit ex: ExecutionContext): Future[Seq[Portion]]
    def getTodos()(implicit ex: ExecutionContext): Future[Seq[Todo]]
    def getTodos(parentId: UUID)(implicit ex: ExecutionContext): Future[Seq[Todo]]
    def getHobbies()(implicit ex: ExecutionContext): Future[Seq[Hobby]]
    def getHobbies(goalId: UUID)(implicit ex: ExecutionContext): Future[Seq[Hobby]]
    def getOneOffs()(implicit ex: ExecutionContext): Future[Seq[OneOff]]
    def getScheduledOneOffs(date: Option[LocalDate])(implicit ex: ExecutionContext): Future[Seq[ScheduledOneOff]]

    def getRelationship(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Relationship]]
    def getMission(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Mission]]
    def getSkillCategory(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[SkillCategory]]
    def getSkill(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Skill]]
    def getBacklogItem(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[BacklogItem]]
    def getEpoch(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Epoch]]
    def getYear(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Year]]
    def getTheme(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Theme]]
    def getGoal(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Goal]]
    def getThread(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Thread]]
    def getWeave(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Weave]]
    def getLaserDonut(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[LaserDonut]]
    def getCurrentLaserDonut(implicit ex: ExecutionContext): Future[Option[LaserDonut]]
    def getPortion(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Portion]]
    def getCurrentPortion(implicit ex: ExecutionContext): Future[Option[Portion]]
    def getTodo(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Todo]]
    def getHobby(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Hobby]]
    def getOneOff(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[OneOff]]
    def getScheduledOneOff(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[ScheduledOneOff]]
    def getPyramidOfImportance(implicit ex: ExecutionContext): Future[Option[PyramidOfImportance]]

    def deleteSkillCategory(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteRelationship(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteMission(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteSkill(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteBacklogItem(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteEpoch(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteYear(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteTheme(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteGoal(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteThread(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteWeave(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteLaserDonut(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deletePortion(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteTodo(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteHobby(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteOneOff(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
    def deleteScheduledOneOff(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
  }
}

trait DefaultPlanningServiceComponent extends PlanningServiceComponent {
  this: PlanningRepositoryComponent =>

  override val planningService = new DefaultPlanningService

  class DefaultPlanningService extends PlanningService {

    override def createSkillCategory(creation: CreateSkillCategory)(implicit ex: ExecutionContext): Future[SkillCategory] = {
      repo.createSkillCategory(creation)
    }

    override def createSkill(creation: CreateSkill)(implicit ex: ExecutionContext): Future[Skill] = {
      repo.createSkill(creation)
    }

    override def createRelationship(creation: CreateRelationship)(implicit ex: ExecutionContext): Future[Relationship] = {
      repo.createRelationship(creation)
    }

    override def createMission(creation: CreateMission)(implicit ex: ExecutionContext): Future[Mission] = {
      repo.createMission(creation)
    }

    override def createBacklogItem(creation: CreateBacklogItem)(implicit ex: ExecutionContext): Future[BacklogItem] = {
      repo.createBacklogItem(creation)
    }

    override def createEpoch(creation: CreateEpoch)(implicit ex: ExecutionContext): Future[Epoch] = {
      repo.createEpoch(creation)
    }

    override def createYear(creation: CreateYear)(implicit ex: ExecutionContext): Future[Year] = {
      repo.createYear(creation)
    }

    override def createTheme(creation: CreateTheme)(implicit ex: ExecutionContext): Future[Theme] = {
      repo.createTheme(creation)
    }

    override def createGoal(creation: CreateGoal)(implicit ex: ExecutionContext): Future[Goal] = {
      repo.createGoal(creation)
    }

    override def createThread(creation: CreateThread)(implicit ex: ExecutionContext): Future[Thread] = {
      repo.createThread(creation)
    }

    override def createWeave(creation: CreateWeave)(implicit ex: ExecutionContext): Future[Weave] = {
      repo.createWeave(creation)
    }

    override def createLaserDonut(creation: CreateLaserDonut)(implicit ex: ExecutionContext): Future[LaserDonut] = {
      repo.createLaserDonut(creation)
    }

    override def createPortion(creation: CreatePortion)(implicit ex: ExecutionContext): Future[Portion] = {
      repo.createPortion(creation)
    }

    override def createTodo(creation: CreateTodo)(implicit ex: ExecutionContext): Future[Todo] = {
      repo.createTodo(creation)
    }

    override def createHobby(creation: CreateHobby)(implicit ex: ExecutionContext): Future[Hobby] = {
      repo.createHobby(creation)
    }

    override def createOneOff(creation: CreateOneOff)(implicit ex: ExecutionContext): Future[OneOff] = {
      repo.createOneOff(creation)
    }

    override def createScheduledOneOff(creation: CreateScheduledOneOff)(implicit ex: ExecutionContext): Future[ScheduledOneOff] = {
      repo.createScheduledOneOff(creation)
    }

    override def createPyramidOfImportance(creation: UpsertPyramidOfImportance)(implicit ex: ExecutionContext): Future[PyramidOfImportance] = {
      repo.createPyramidOfImportance(creation)
    }

    override def updateSkillCategory(uuid: UUID, update: UpdateSkillCategory)(implicit ex: ExecutionContext): Future[SkillCategory] = {
      repo.updateSkillCategory(uuid, update)
    }

    override def updateSkill(uuid: UUID, update: UpdateSkill)(implicit ex: ExecutionContext): Future[Skill] = {
      repo.updateSkill(uuid, update)
    }

    override def updateRelationship(uuid: UUID, update: UpdateRelationship)(implicit ex: ExecutionContext): Future[Relationship] = {
      repo.updateRelationship(uuid, update)
    }

    override def updateMission(uuid: UUID, update: UpdateMission)(implicit ex: ExecutionContext): Future[Mission] = {
      repo.updateMission(uuid, update)
    }

    override def updatePractisedHours(uuid: UUID, practisedHours: Long)(implicit ex: ExecutionContext): Future[Skill] = {
      for {
        skill <- repo.getSkill(uuid)
        update = UpdateSkill(None, None, None, practisedHours = skill.map(_.practisedHours + practisedHours))
        updatedSkill <- repo.updateSkill(uuid, update)
      } yield updatedSkill
    }

    override def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem)(implicit ex: ExecutionContext): Future[BacklogItem] = {
      repo.updateBacklogItem(uuid, update)
    }

    override def updateEpoch(uuid: UUID, update: UpdateEpoch)(implicit ex: ExecutionContext): Future[Epoch] = {
      repo.updateEpoch(uuid, update)
    }

    override def updateYear(uuid: UUID, update: UpdateYear)(implicit ex: ExecutionContext): Future[Year] = {
      repo.updateYear(uuid, update)
    }

    override def updateTheme(uuid: UUID, update: UpdateTheme)(implicit ex: ExecutionContext): Future[Theme] = {
      repo.updateTheme(uuid, update)
    }

    override def updateGoal(uuid: UUID, update: UpdateGoal)(implicit ex: ExecutionContext): Future[Goal] = {
      repo.updateGoal(uuid, update)
    }

    override def updateThread(uuid: UUID, update: UpdateThread)(implicit ex: ExecutionContext): Future[Thread] = {
      repo.updateThread(uuid, update)
    }

    override def updateWeave(uuid: UUID, update: UpdateWeave)(implicit ex: ExecutionContext): Future[Weave] = {
      repo.updateWeave(uuid, update)
    }

    override def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut)(implicit ex: ExecutionContext): Future[LaserDonut] = {
      repo.updateLaserDonut(uuid, update)
    }

    override def updatePortion(uuid: UUID, update: UpdatePortion)(implicit ex: ExecutionContext): Future[Portion] = {
      repo.updatePortion(uuid, update)
    }

    override def updatePortions(laserDonutId: UUID, update: UpdateList)(implicit ex: ExecutionContext): Future[Seq[Portion]] = {
      repo.updatePortions(laserDonutId, update)
    }

    override def updateTodo(uuid: UUID, update: UpdateTodo)(implicit ex: ExecutionContext): Future[Todo] = {
      repo.updateTodo(uuid, update)
    }

    override def updateTodos(parentId: UUID, update: UpdateList)(implicit ex: ExecutionContext): Future[Seq[Todo]] = {
      repo.updateTodos(parentId, update)
    }

    override def updateHobby(uuid: UUID, update: UpdateHobby)(implicit ex: ExecutionContext): Future[Hobby] = {
      repo.updateHobby(uuid, update)
    }

    override def updateOneOff(uuid: UUID, update: UpdateOneOff)(implicit ex: ExecutionContext): Future[OneOff] = {
      repo.updateOneOff(uuid, update)
    }

    override def updateOneOffs(update: UpdateList)(implicit ex: ExecutionContext): Future[Seq[OneOff]] = {
      repo.updateOneOffs(update)
    }

    override def updateScheduledOneOff(uuid: UUID, update: UpdateScheduledOneOff)(implicit ex: ExecutionContext): Future[ScheduledOneOff] = {
      repo.updateScheduledOneOff(uuid, update)
    }

    override def refreshPyramidOfImportance()(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.refreshPyramidOfImportance()
    }

    override def refreshPortion()(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.refreshPortion()
    }

    override def getSkillCategories(implicit ex: ExecutionContext): Future[Seq[SkillCategory]] = {
      repo.getSkillCategories
    }

    override def getSkills(implicit ex: ExecutionContext): Future[Seq[Skill]] = {
      repo.getSkills
    }

    override def getRelationships(implicit ex: ExecutionContext): Future[Seq[Relationship]] = {
      repo.getRelationships
    }

    override def getMissions(implicit ex: ExecutionContext): Future[Seq[Mission]] = {
      repo.getMissions
    }

    override def getBacklogItems(implicit ex: ExecutionContext): Future[Seq[BacklogItem]] = {
      repo.getBacklogItems
    }

    override def getEpochs(implicit ex: ExecutionContext): Future[Seq[Epoch]] = {
      repo.getEpochs
    }

    override def getYears(implicit ex: ExecutionContext): Future[Seq[Year]] = {
      repo.getYears
    }

    override def getThemes(implicit ex: ExecutionContext): Future[Seq[Theme]] = {
      repo.getThemes
    }

    override def getGoals(implicit ex: ExecutionContext): Future[Seq[Goal]] = {
      repo.getGoals
    }

    override def getThreads()(implicit ex: ExecutionContext): Future[Seq[Thread]] = {
      repo.getThreads
    }

    override def getThreads(goalId: UUID)(implicit ex: ExecutionContext): Future[Seq[Thread]] = {
      repo.getThreads(goalId)
    }

    override def getWeaves()(implicit ex: ExecutionContext): Future[Seq[Weave]] = {
      repo.getWeaves
    }

    override def getWeaves(goalId: UUID)(implicit ex: ExecutionContext): Future[Seq[Weave]] = {
      repo.getWeaves(goalId)
    }

    override def getLaserDonuts()(implicit ex: ExecutionContext): Future[Seq[LaserDonut]] = {
      repo.getLaserDonuts
    }

    override def getLaserDonuts(goalId: UUID)(implicit ex: ExecutionContext): Future[Seq[LaserDonut]] = {
      repo.getLaserDonuts(goalId)
    }

    override def getPortions()(implicit ex: ExecutionContext): Future[Seq[Portion]] = {
      repo.getPortions
    }

    override def getPortions(laserDonutId: UUID)(implicit ex: ExecutionContext): Future[Seq[Portion]] = {
      repo.getPortions(laserDonutId)
    }

    override def getTodos()(implicit ex: ExecutionContext): Future[Seq[Todo]] = {
      repo.getTodos
    }

    override def getTodos(parentId: UUID)(implicit ex: ExecutionContext): Future[Seq[Todo]] = {
      repo.getTodos(parentId)
    }

    override def getHobbies()(implicit ex: ExecutionContext): Future[Seq[Hobby]] = {
      repo.getHobbies
    }

    override def getHobbies(goalId: UUID)(implicit ex: ExecutionContext): Future[Seq[Hobby]] = {
      repo.getHobbies(goalId)
    }

    override def getOneOffs()(implicit ex: ExecutionContext): Future[Seq[OneOff]] = {
      repo.getOneOffs
    }

    override def getScheduledOneOffs(date: Option[LocalDate])(implicit ex: ExecutionContext): Future[Seq[ScheduledOneOff]] = {
      repo.getScheduledOneOffs(date)
    }

    override def getSkillCategory(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[SkillCategory]] = {
      repo.getSkillCategory(uuid)
    }

    override def getSkill(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Skill]] = {
      repo.getSkill(uuid)
    }

    override def getRelationship(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Relationship]] = {
      repo.getRelationship(uuid)
    }

    override def getMission(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Mission]] = {
      repo.getMission(uuid)
    }

    override def getBacklogItem(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[BacklogItem]] = {
      repo.getBacklogItem(uuid)
    }

    override def getEpoch(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Epoch]] = {
      repo.getEpoch(uuid)
    }

    override def getYear(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Year]] = {
      repo.getYear(uuid)
    }

    override def getTheme(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Theme]] = {
      repo.getTheme(uuid)
    }

    override def getGoal(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Goal]] = {
      repo.getGoal(uuid)
    }

    override def getThread(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Thread]] = {
      repo.getThread(uuid)
    }

    override def getWeave(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Weave]] = {
      repo.getWeave(uuid)
    }

    override def getLaserDonut(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[LaserDonut]] = {
      repo.getLaserDonut(uuid)
    }

    override def getCurrentLaserDonut(implicit ex: ExecutionContext): Future[Option[LaserDonut]] = {
      repo.getCurrentLaserDonut
    }

    override def getPortion(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Portion]] = {
      repo.getPortion(uuid)
    }

    override def getCurrentPortion(implicit ex: ExecutionContext): Future[Option[Portion]] = {
      repo.getCurrentPortion
    }

    override def getTodo(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Todo]] = {
      repo.getTodo(uuid)
    }

    override def getHobby(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Hobby]] = {
      repo.getHobby(uuid)
    }

    override def getOneOff(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[OneOff]] = {
      repo.getOneOff(uuid)
    }

    override def getScheduledOneOff(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[ScheduledOneOff]] = {
      repo.getScheduledOneOff(uuid)
    }

    override def getPyramidOfImportance(implicit ex: ExecutionContext): Future[Option[PyramidOfImportance]] = {
      repo.getPyramidOfImportance
    }

    override def deleteSkillCategory(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteSkillCategory(uuid)
    }

    override def deleteSkill(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteSkill(uuid)
    }

    override def deleteRelationship(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteRelationship(uuid)
    }

    override def deleteMission(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteMission(uuid)
    }

    override def deleteBacklogItem(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteBacklogItem(uuid)
    }

    override def deleteEpoch(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteEpoch(uuid)
    }

    override def deleteYear(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteYear(uuid)
    }

    override def deleteTheme(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteTheme(uuid)
    }

    override def deleteGoal(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteGoal(uuid)
    }

    override def deleteThread(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteThread(uuid)
    }

    override def deleteWeave(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteWeave(uuid)
    }

    override def deleteLaserDonut(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteLaserDonut(uuid)
    }

    override def deletePortion(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deletePortion(uuid)
    }

    override def deleteTodo(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteTodo(uuid)
    }

    override def deleteHobby(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteHobby(uuid)
    }

    override def deleteOneOff(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteOneOff(uuid)
    }

    override def deleteScheduledOneOff(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteScheduledOneOff(uuid)
    }
  }
}
