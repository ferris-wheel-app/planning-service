package com.ferris.planning.service

import java.util.UUID

import com.ferris.planning.command.Commands._
import com.ferris.planning.model.Model._
import com.ferris.planning.repo.PlanningRepositoryComponent

import scala.concurrent.{ExecutionContext, Future}

trait PlanningServiceComponent {
  val planningService: PlanningService

  trait PlanningService {
    def createMessage(creation: CreateMessage)(implicit ex: ExecutionContext): Future[Message]
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

    def updateMessage(uuid: UUID, update: UpdateMessage)(implicit ex: ExecutionContext): Future[Option[Message]]
    def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem)(implicit ex: ExecutionContext): Future[Option[BacklogItem]]
    def updateEpoch(uuid: UUID, update: UpdateEpoch)(implicit ex: ExecutionContext): Future[Option[Epoch]]
    def updateYear(uuid: UUID, update: UpdateYear)(implicit ex: ExecutionContext): Future[Option[Year]]
    def updateTheme(uuid: UUID, update: UpdateTheme)(implicit ex: ExecutionContext): Future[Option[Theme]]
    def updateGoal(uuid: UUID, update: UpdateGoal)(implicit ex: ExecutionContext): Future[Option[Goal]]
    def updateThread(uuid: UUID, update: UpdateThread)(implicit ex: ExecutionContext): Future[Option[Thread]]
    def updateWeave(uuid: UUID, update: UpdateWeave)(implicit ex: ExecutionContext): Future[Option[Weave]]
    def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut)(implicit ex: ExecutionContext): Future[Option[LaserDonut]]
    def updatePortion(uuid: UUID, update: UpdatePortion)(implicit ex: ExecutionContext): Future[Option[Portion]]
    def updateTodo(uuid: UUID, update: UpdateTodo)(implicit ex: ExecutionContext): Future[Option[Todo]]
    def updateHobby(uuid: UUID, update: UpdateHobby)(implicit ex: ExecutionContext): Future[Option[Hobby]]

    def getMessages(implicit ex: ExecutionContext): Future[Seq[Message]]
    def getBacklogItems(implicit ex: ExecutionContext): Future[Seq[BacklogItem]]
    def getEpochs(implicit ex: ExecutionContext): Future[Seq[Epoch]]
    def getYears(implicit ex: ExecutionContext): Future[Seq[Year]]
    def getThemes(implicit ex: ExecutionContext): Future[Seq[Theme]]
    def getGoals(implicit ex: ExecutionContext): Future[Seq[Goal]]
    def getThreads(implicit ex: ExecutionContext): Future[Seq[Thread]]
    def getWeaves(implicit ex: ExecutionContext): Future[Seq[Weave]]
    def getLaserDonuts(implicit ex: ExecutionContext): Future[Seq[LaserDonut]]
    def getPortions(implicit ex: ExecutionContext): Future[Seq[Portion]]
    def getTodos(implicit ex: ExecutionContext): Future[Seq[Todo]]
    def getHobbies(implicit ex: ExecutionContext): Future[Seq[Hobby]]

    def getMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Message]]
    def getBacklogItem(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[BacklogItem]]
    def getEpoch(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Epoch]]
    def getYear(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Year]]
    def getTheme(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Theme]]
    def getGoal(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Goal]]
    def getThread(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Thread]]
    def getWeave(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Weave]]
    def getLaserDonut(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[LaserDonut]]
    def getPortion(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Portion]]
    def getTodo(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Todo]]
    def getHobby(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Hobby]]

    def deleteMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean]
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

  }
}

trait DefaultPlanningServiceComponent extends PlanningServiceComponent {
  this: PlanningRepositoryComponent =>

  override val planningService = new DefaultPlanningService

  class DefaultPlanningService extends PlanningService {
    override def createMessage(creation: CreateMessage)(implicit ex: ExecutionContext): Future[Message] = {
      repo.createMessage(creation)
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

    override def createHobby(creation: CreateHobby)(implicit ex: ExecutionContext): Unit = {
      repo.createHobby(creation)
    }

    override def updateMessage(uuid: UUID, update: UpdateMessage)(implicit ex: ExecutionContext): Future[Option[Message]] = {
      repo.updateMessage(uuid, update)
    }

    override def updateBacklogItem(uuid: UUID, update: UpdateBacklogItem)(implicit ex: ExecutionContext): Future[Option[BacklogItem]] = {
      repo.updateBacklogItem(uuid, update)
    }

    override def updateEpoch(uuid: UUID, update: UpdateEpoch)(implicit ex: ExecutionContext): Future[Option[Epoch]] = {
      repo.updateEpoch(uuid, update)
    }

    override def updateYear(uuid: UUID, update: UpdateYear)(implicit ex: ExecutionContext): Future[Option[Year]] = {
      repo.updateYear(uuid, update)
    }

    override def updateGoal(uuid: UUID, update: UpdateGoal)(implicit ex: ExecutionContext): Future[Option[Goal]] = {
      repo.updateGoal(uuid, update)
    }

    override def updateThread(uuid: UUID, update: UpdateThread)(implicit ex: ExecutionContext): Future[Option[Thread]] = {
      repo.updateThread(uuid, update)
    }

    override def updateWeave(uuid: UUID, update: UpdateWeave)(implicit ex: ExecutionContext): Future[Option[Weave]] = {
      repo.updateWeave(uuid, update)
    }

    override def updateLaserDonut(uuid: UUID, update: UpdateLaserDonut)(implicit ex: ExecutionContext): Future[Option[LaserDonut]] = {
      repo.updateLaserDonut(uuid, update)
    }

    override def updatePortion(uuid: UUID, update: UpdatePortion)(implicit ex: ExecutionContext): Future[Option[Portion]] = {
      repo.updatePortion(uuid, update)
    }

    override def updateTodo(uuid: UUID, update: UpdateTodo)(implicit ex: ExecutionContext): Future[Option[Todo]] = {
      repo.updateTodo(uuid, update)
    }

    override def upda

    override def getMessages(implicit ex: ExecutionContext): Future[Seq[Message]] = {
      repo.getMessages
    }

    override def getMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Option[Message]] = {
      repo.getMessage(uuid)
    }

    override def deleteMessage(uuid: UUID)(implicit ex: ExecutionContext): Future[Boolean] = {
      repo.deleteMessage(uuid)
    }
  }
}












































