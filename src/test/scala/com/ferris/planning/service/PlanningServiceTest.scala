package com.ferris.planning.service

import java.time.LocalDateTime
import java.util.UUID

import com.ferris.planning.command.Commands.UpdateSkill
import com.ferris.planning.sample.SampleData.{domain => SD}
import com.ferris.planning.service.exceptions.Exceptions._
import org.mockito.Matchers.{eq => eqTo}
import org.mockito.Mockito._
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.OptionValues._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

class PlanningServiceTest extends FunSpec with ScalaFutures with Matchers {

  implicit val defaultTimeout: PatienceConfig = PatienceConfig(scaled(15.seconds))

  def newServer = new DefaultPlanningServiceComponent with MockPlanningRepositoryComponent {
    override val planningService: DefaultPlanningService = new DefaultPlanningService()
  }

  describe("a planning service") {
    describe("handling skill categories") {
      it("should be able to create a skill-category") {
        val server = newServer
        when(server.repo.createSkillCategory(SD.skillCategoryCreation)).thenReturn(Future.successful(SD.skillCategory))
        whenReady(server.planningService.createSkillCategory(SD.skillCategoryCreation)) { result =>
          result shouldBe SD.skillCategory
          verify(server.repo, times(1)).createSkillCategory(SD.skillCategoryCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a skill-category") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.skillCategory
        when(server.repo.updateSkillCategory(eqTo(id), eqTo(SD.skillCategoryUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateSkillCategory(id, SD.skillCategoryUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateSkillCategory(eqTo(id), eqTo(SD.skillCategoryUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a skill-category is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = SkillCategoryNotFoundException()
        when(server.repo.updateSkillCategory(eqTo(id), eqTo(SD.skillCategoryUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateSkillCategory(id, SD.skillCategoryUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateSkillCategory(eqTo(id), eqTo(SD.skillCategoryUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a skill-category") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getSkillCategory(id)).thenReturn(Future.successful(Some(SD.skillCategory)))
        whenReady(server.planningService.getSkillCategory(id)) { result =>
          result shouldBe Some(SD.skillCategory)
          verify(server.repo, times(1)).getSkillCategory(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all skill-categories") {
        val server = newServer
        val skillCategories = Seq(SD.skillCategory, SD.skillCategory.copy(uuid = UUID.randomUUID))
        when(server.repo.getSkillCategories).thenReturn(Future.successful(skillCategories))
        whenReady(server.planningService.getSkillCategories) { result =>
          result shouldBe skillCategories
          verify(server.repo, times(1)).getSkillCategories
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a skill-category") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteSkillCategory(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteSkillCategory(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteSkillCategory(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling skills") {
      it("should be able to create a skill") {
        val server = newServer
        when(server.repo.createSkill(SD.skillCreation)).thenReturn(Future.successful(SD.skill))
        whenReady(server.planningService.createSkill(SD.skillCreation)) { result =>
          result shouldBe SD.skill
          verify(server.repo, times(1)).createSkill(SD.skillCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a skill") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.skill
        when(server.repo.updateSkill(eqTo(id), eqTo(SD.skillUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateSkill(id, SD.skillUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateSkill(eqTo(id), eqTo(SD.skillUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update the practised hours of a skill") {
        val server = newServer
        val id = UUID.randomUUID
        val practisedHours = 1000L
        val time = LocalDateTime.now
        val update = UpdateSkill(None, None, None, practisedHours = Some(1500L), lastPractise = Some(time))
        val updated = SD.skill.copy(practisedHours = 1500L)
        when(server.repo.getSkill(id)).thenReturn(Future.successful(Some(SD.skill)))
        when(server.repo.updateSkill(eqTo(id), eqTo(update))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updatePractisedHours(id, practisedHours, time)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).getSkill(id)
          verify(server.repo, times(1)).updateSkill(eqTo(id), eqTo(update))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a skill is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = SkillNotFoundException()
        when(server.repo.updateSkill(eqTo(id), eqTo(SD.skillUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateSkill(id, SD.skillUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateSkill(eqTo(id), eqTo(SD.skillUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a skill") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getSkill(id)).thenReturn(Future.successful(Some(SD.skill)))
        whenReady(server.planningService.getSkill(id)) { result =>
          result shouldBe Some(SD.skill)
          verify(server.repo, times(1)).getSkill(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all skills") {
        val server = newServer
        val skills = Seq(SD.skill, SD.skill.copy(uuid = UUID.randomUUID))
        when(server.repo.getSkills).thenReturn(Future.successful(skills))
        whenReady(server.planningService.getSkills) { result =>
          result shouldBe skills
          verify(server.repo, times(1)).getSkills
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a skill") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteSkill(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteSkill(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteSkill(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling relationships") {
      it("should be able to create a relationship") {
        val server = newServer
        when(server.repo.createRelationship(SD.relationshipCreation)).thenReturn(Future.successful(SD.relationship))
        whenReady(server.planningService.createRelationship(SD.relationshipCreation)) { result =>
          result shouldBe SD.relationship
          verify(server.repo, times(1)).createRelationship(SD.relationshipCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a relationship") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.relationship
        when(server.repo.updateRelationship(eqTo(id), eqTo(SD.relationshipUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateRelationship(id, SD.relationshipUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateRelationship(eqTo(id), eqTo(SD.relationshipUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a relationship is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = RelationshipNotFoundException()
        when(server.repo.updateRelationship(eqTo(id), eqTo(SD.relationshipUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateRelationship(id, SD.relationshipUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateRelationship(eqTo(id), eqTo(SD.relationshipUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a relationship") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getRelationship(id)).thenReturn(Future.successful(Some(SD.relationship)))
        whenReady(server.planningService.getRelationship(id)) { result =>
          result shouldBe Some(SD.relationship)
          verify(server.repo, times(1)).getRelationship(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all relationships") {
        val server = newServer
        val relationships = Seq(SD.relationship, SD.relationship.copy(uuid = UUID.randomUUID))
        when(server.repo.getRelationships).thenReturn(Future.successful(relationships))
        whenReady(server.planningService.getRelationships) { result =>
          result shouldBe relationships
          verify(server.repo, times(1)).getRelationships
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a relationship") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteRelationship(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteRelationship(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteRelationship(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling missions") {
      it("should be able to create a mission") {
        val server = newServer
        when(server.repo.createMission(SD.missionCreation)).thenReturn(Future.successful(SD.mission))
        whenReady(server.planningService.createMission(SD.missionCreation)) { result =>
          result shouldBe SD.mission
          verify(server.repo, times(1)).createMission(SD.missionCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a mission") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.mission
        when(server.repo.updateMission(eqTo(id), eqTo(SD.missionUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateMission(id, SD.missionUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateMission(eqTo(id), eqTo(SD.missionUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a mission is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = MissionNotFoundException()
        when(server.repo.updateMission(eqTo(id), eqTo(SD.missionUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateMission(id, SD.missionUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateMission(eqTo(id), eqTo(SD.missionUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a mission") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getMission(id)).thenReturn(Future.successful(Some(SD.mission)))
        whenReady(server.planningService.getMission(id)) { result =>
          result shouldBe Some(SD.mission)
          verify(server.repo, times(1)).getMission(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all missions") {
        val server = newServer
        val missions = Seq(SD.mission, SD.mission.copy(uuid = UUID.randomUUID))
        when(server.repo.getMissions).thenReturn(Future.successful(missions))
        whenReady(server.planningService.getMissions) { result =>
          result shouldBe missions
          verify(server.repo, times(1)).getMissions
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a mission") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteMission(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteMission(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteMission(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling backlog-items") {
      it("should be able to create a backlog-item") {
        val server = newServer
        when(server.repo.createBacklogItem(SD.backlogItemCreation)).thenReturn(Future.successful(SD.backlogItem))
        whenReady(server.planningService.createBacklogItem(SD.backlogItemCreation)) { result =>
          result shouldBe SD.backlogItem
          verify(server.repo, times(1)).createBacklogItem(SD.backlogItemCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a backlog-item") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.backlogItem
        when(server.repo.updateBacklogItem(eqTo(id), eqTo(SD.backlogItemUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateBacklogItem(id, SD.backlogItemUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateBacklogItem(eqTo(id), eqTo(SD.backlogItemUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a backlog-item is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = BacklogItemNotFoundException()
        when(server.repo.updateBacklogItem(eqTo(id), eqTo(SD.backlogItemUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateBacklogItem(id, SD.backlogItemUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateBacklogItem(eqTo(id), eqTo(SD.backlogItemUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a backlog-item") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getBacklogItem(id)).thenReturn(Future.successful(Some(SD.backlogItem)))
        whenReady(server.planningService.getBacklogItem(id)) { result =>
          result shouldBe Some(SD.backlogItem)
          verify(server.repo, times(1)).getBacklogItem(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all backlog-items") {
        val server = newServer
        val backlogItems = Seq(SD.backlogItem, SD.backlogItem.copy(uuid = UUID.randomUUID))
        when(server.repo.getBacklogItems).thenReturn(Future.successful(backlogItems))
        whenReady(server.planningService.getBacklogItems) { result =>
          result shouldBe backlogItems
          verify(server.repo, times(1)).getBacklogItems
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a backlog-item") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteBacklogItem(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteBacklogItem(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteBacklogItem(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling epochs") {
      it("should be able to create an epoch") {
        val server = newServer
        when(server.repo.createEpoch(SD.epochCreation)).thenReturn(Future.successful(SD.epoch))
        whenReady(server.planningService.createEpoch(SD.epochCreation)) { result =>
          result shouldBe SD.epoch
          verify(server.repo, times(1)).createEpoch(SD.epochCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update an epoch") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.epoch
        when(server.repo.updateEpoch(eqTo(id), eqTo(SD.epochUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateEpoch(id, SD.epochUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateEpoch(eqTo(id), eqTo(SD.epochUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when an epoch is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = EpochNotFoundException()
        when(server.repo.updateEpoch(eqTo(id), eqTo(SD.epochUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateEpoch(id, SD.epochUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateEpoch(eqTo(id), eqTo(SD.epochUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve an epoch") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getEpoch(id)).thenReturn(Future.successful(Some(SD.epoch)))
        whenReady(server.planningService.getEpoch(id)) { result =>
          result shouldBe Some(SD.epoch)
          verify(server.repo, times(1)).getEpoch(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all epochs") {
        val server = newServer
        val epochs = Seq(SD.epoch, SD.epoch.copy(uuid = UUID.randomUUID))
        when(server.repo.getEpochs).thenReturn(Future.successful(epochs))
        whenReady(server.planningService.getEpochs) { result =>
          result shouldBe epochs
          verify(server.repo, times(1)).getEpochs
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete an epoch") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteEpoch(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteEpoch(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteEpoch(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling years") {
      it("should be able to create a year") {
        val server = newServer
        when(server.repo.createYear(SD.yearCreation)).thenReturn(Future.successful(SD.year))
        whenReady(server.planningService.createYear(SD.yearCreation)) { result =>
          result shouldBe SD.year
          verify(server.repo, times(1)).createYear(SD.yearCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a year") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.year
        when(server.repo.updateYear(eqTo(id), eqTo(SD.yearUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateYear(id, SD.yearUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateYear(eqTo(id), eqTo(SD.yearUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a year is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = YearNotFoundException()
        when(server.repo.updateYear(eqTo(id), eqTo(SD.yearUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateYear(id, SD.yearUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateYear(eqTo(id), eqTo(SD.yearUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a year") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getYear(id)).thenReturn(Future.successful(Some(SD.year)))
        whenReady(server.planningService.getYear(id)) { result =>
          result shouldBe Some(SD.year)
          verify(server.repo, times(1)).getYear(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all years") {
        val server = newServer
        val years = Seq(SD.year, SD.year.copy(uuid = UUID.randomUUID))
        when(server.repo.getYears).thenReturn(Future.successful(years))
        whenReady(server.planningService.getYears) { result =>
          result shouldBe years
          verify(server.repo, times(1)).getYears
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a year") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteYear(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteYear(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteYear(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling themes") {
      it("should be able to create a theme") {
        val server = newServer
        when(server.repo.createTheme(SD.themeCreation)).thenReturn(Future.successful(SD.theme))
        whenReady(server.planningService.createTheme(SD.themeCreation)) { result =>
          result shouldBe SD.theme
          verify(server.repo, times(1)).createTheme(SD.themeCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a theme") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.theme
        when(server.repo.updateTheme(eqTo(id), eqTo(SD.themeUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateTheme(id, SD.themeUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateTheme(eqTo(id), eqTo(SD.themeUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a theme is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = ThemeNotFoundException()
        when(server.repo.updateTheme(eqTo(id), eqTo(SD.themeUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateTheme(id, SD.themeUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateTheme(eqTo(id), eqTo(SD.themeUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a theme") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getTheme(id)).thenReturn(Future.successful(Some(SD.theme)))
        whenReady(server.planningService.getTheme(id)) { result =>
          result shouldBe Some(SD.theme)
          verify(server.repo, times(1)).getTheme(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all themes") {
        val server = newServer
        val themes = Seq(SD.theme, SD.theme.copy(uuid = UUID.randomUUID))
        when(server.repo.getThemes).thenReturn(Future.successful(themes))
        whenReady(server.planningService.getThemes) { result =>
          result shouldBe themes
          verify(server.repo, times(1)).getThemes
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a theme") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteTheme(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteTheme(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteTheme(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling goals") {
      it("should be able to create a goal") {
        val server = newServer
        when(server.repo.createGoal(SD.goalCreation)).thenReturn(Future.successful(SD.goal))
        whenReady(server.planningService.createGoal(SD.goalCreation)) { result =>
          result shouldBe SD.goal
          verify(server.repo, times(1)).createGoal(SD.goalCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a goal") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.goal
        when(server.repo.updateGoal(eqTo(id), eqTo(SD.goalUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateGoal(id, SD.goalUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateGoal(eqTo(id), eqTo(SD.goalUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a goal is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = GoalNotFoundException()
        when(server.repo.updateGoal(eqTo(id), eqTo(SD.goalUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateGoal(id, SD.goalUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateGoal(eqTo(id), eqTo(SD.goalUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a goal") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getGoal(id)).thenReturn(Future.successful(Some(SD.goal)))
        whenReady(server.planningService.getGoal(id)) { result =>
          result shouldBe Some(SD.goal)
          verify(server.repo, times(1)).getGoal(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all goals") {
        val server = newServer
        val goals = Seq(SD.goal, SD.goal.copy(uuid = UUID.randomUUID))
        when(server.repo.getGoals).thenReturn(Future.successful(goals))
        whenReady(server.planningService.getGoals) { result =>
          result shouldBe goals
          verify(server.repo, times(1)).getGoals
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a goal") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteGoal(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteGoal(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteGoal(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling threads") {
      it("should be able to create a thread") {
        val server = newServer
        when(server.repo.createThread(SD.threadCreation)).thenReturn(Future.successful(SD.thread))
        whenReady(server.planningService.createThread(SD.threadCreation)) { result =>
          result shouldBe SD.thread
          verify(server.repo, times(1)).createThread(SD.threadCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a thread") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.thread
        when(server.repo.updateThread(eqTo(id), eqTo(SD.threadUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateThread(id, SD.threadUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateThread(eqTo(id), eqTo(SD.threadUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a thread is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = ThreadNotFoundException()
        when(server.repo.updateThread(eqTo(id), eqTo(SD.threadUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateThread(id, SD.threadUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateThread(eqTo(id), eqTo(SD.threadUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a thread") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getThread(id)).thenReturn(Future.successful(Some(SD.thread)))
        whenReady(server.planningService.getThread(id)) { result =>
          result shouldBe Some(SD.thread)
          verify(server.repo, times(1)).getThread(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all threads") {
        val server = newServer
        val threads = Seq(SD.thread, SD.thread.copy(uuid = UUID.randomUUID))
        when(server.repo.getThreads).thenReturn(Future.successful(threads))
        whenReady(server.planningService.getThreads) { result =>
          result shouldBe threads
          verify(server.repo, times(1)).getThreads
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve threads that belong to a specific goal") {
        val server = newServer
        val goalId = UUID.randomUUID
        val threads = Seq(SD.thread, SD.thread.copy(uuid = UUID.randomUUID))
        when(server.repo.getThreads(goalId)).thenReturn(Future.successful(threads))
        whenReady(server.planningService.getThreads(goalId)) { result =>
          result shouldBe threads
          verify(server.repo, times(1)).getThreads(goalId)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a thread") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteThread(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteThread(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteThread(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling weaves") {
      it("should be able to create a weave") {
        val server = newServer
        when(server.repo.createWeave(SD.weaveCreation)).thenReturn(Future.successful(SD.weave))
        whenReady(server.planningService.createWeave(SD.weaveCreation)) { result =>
          result shouldBe SD.weave
          verify(server.repo, times(1)).createWeave(SD.weaveCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a weave") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.weave
        when(server.repo.updateWeave(eqTo(id), eqTo(SD.weaveUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateWeave(id, SD.weaveUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateWeave(eqTo(id), eqTo(SD.weaveUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a weave is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = WeaveNotFoundException()
        when(server.repo.updateWeave(eqTo(id), eqTo(SD.weaveUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateWeave(id, SD.weaveUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateWeave(eqTo(id), eqTo(SD.weaveUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a weave") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getWeave(id)).thenReturn(Future.successful(Some(SD.weave)))
        whenReady(server.planningService.getWeave(id)) { result =>
          result shouldBe Some(SD.weave)
          verify(server.repo, times(1)).getWeave(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all weaves") {
        val server = newServer
        val weaves = Seq(SD.weave, SD.weave.copy(uuid = UUID.randomUUID))
        when(server.repo.getWeaves).thenReturn(Future.successful(weaves))
        whenReady(server.planningService.getWeaves) { result =>
          result shouldBe weaves
          verify(server.repo, times(1)).getWeaves
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve weaves that belong to a specific goal") {
        val server = newServer
        val goalId = UUID.randomUUID
        val weaves = Seq(SD.weave, SD.weave.copy(uuid = UUID.randomUUID))
        when(server.repo.getWeaves(goalId)).thenReturn(Future.successful(weaves))
        whenReady(server.planningService.getWeaves(goalId)) { result =>
          result shouldBe weaves
          verify(server.repo, times(1)).getWeaves(goalId)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a weave") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteWeave(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteWeave(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteWeave(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling laser-donuts") {
      it("should be able to create a laser-donut") {
        val server = newServer
        when(server.repo.createLaserDonut(SD.laserDonutCreation)).thenReturn(Future.successful(SD.laserDonut))
        whenReady(server.planningService.createLaserDonut(SD.laserDonutCreation)) { result =>
          result shouldBe SD.laserDonut
          verify(server.repo, times(1)).createLaserDonut(SD.laserDonutCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a laser-donut") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.laserDonut
        when(server.repo.updateLaserDonut(eqTo(id), eqTo(SD.laserDonutUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateLaserDonut(id, SD.laserDonutUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateLaserDonut(eqTo(id), eqTo(SD.laserDonutUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a laser-donut is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = LaserDonutNotFoundException()
        when(server.repo.updateLaserDonut(eqTo(id), eqTo(SD.laserDonutUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateLaserDonut(id, SD.laserDonutUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateLaserDonut(eqTo(id), eqTo(SD.laserDonutUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a laser-donut") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getLaserDonut(id)).thenReturn(Future.successful(Some(SD.laserDonut)))
        whenReady(server.planningService.getLaserDonut(id)) { result =>
          result shouldBe Some(SD.laserDonut)
          verify(server.repo, times(1)).getLaserDonut(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve the current laser-donut") {
        val server = newServer
        when(server.repo.getCurrentLaserDonut).thenReturn(Future.successful(Some(SD.laserDonut)))
        whenReady(server.planningService.getCurrentLaserDonut) { result =>
          result shouldBe Some(SD.laserDonut)
          verify(server.repo, times(1)).getCurrentLaserDonut
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all laser-donuts") {
        val server = newServer
        val laserDonuts = Seq(SD.laserDonut, SD.laserDonut.copy(uuid = UUID.randomUUID))
        when(server.repo.getLaserDonuts).thenReturn(Future.successful(laserDonuts))
        whenReady(server.planningService.getLaserDonuts) { result =>
          result shouldBe laserDonuts
          verify(server.repo, times(1)).getLaserDonuts
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve laser-donuts that belong to a specific goal") {
        val server = newServer
        val goalId = UUID.randomUUID
        val laserDonuts = Seq(SD.laserDonut, SD.laserDonut.copy(uuid = UUID.randomUUID))
        when(server.repo.getLaserDonuts(goalId)).thenReturn(Future.successful(laserDonuts))
        whenReady(server.planningService.getLaserDonuts(goalId)) { result =>
          result shouldBe laserDonuts
          verify(server.repo, times(1)).getLaserDonuts(goalId)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a laser-donut") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteLaserDonut(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteLaserDonut(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteLaserDonut(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling portions") {
      it("should be able to create a portion") {
        val server = newServer
        when(server.repo.createPortion(SD.portionCreation)).thenReturn(Future.successful(SD.portion))
        whenReady(server.planningService.createPortion(SD.portionCreation)) { result =>
          result shouldBe SD.portion
          verify(server.repo, times(1)).createPortion(SD.portionCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a portion") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.portion
        when(server.repo.updatePortion(eqTo(id), eqTo(SD.portionUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updatePortion(id, SD.portionUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updatePortion(eqTo(id), eqTo(SD.portionUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to refresh the current portion") {
        val server = newServer
        when(server.repo.refreshPortion()).thenReturn(Future.successful(true))
        whenReady(server.planningService.refreshPortion()) { result =>
          result shouldBe true
          verify(server.repo, times(1)).refreshPortion()
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a portion is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = PortionNotFoundException()
        when(server.repo.updatePortion(eqTo(id), eqTo(SD.portionUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updatePortion(id, SD.portionUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updatePortion(eqTo(id), eqTo(SD.portionUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a list of portions that belong to a specific laser-donut") {
        val server = newServer
        val laserDonutId = UUID.randomUUID
        val updated = SD.portion :: SD.portion :: Nil
        when(server.repo.updatePortions(eqTo(laserDonutId), eqTo(SD.listUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updatePortions(laserDonutId, SD.listUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updatePortions(eqTo(laserDonutId), eqTo(SD.listUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a list of portions is being updated") {
        val server = newServer
        val laserDonutId = UUID.randomUUID
        val expectedException = InvalidPortionsUpdateException("Wrong!")
        when(server.repo.updatePortions(eqTo(laserDonutId), eqTo(SD.listUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updatePortions(laserDonutId, SD.listUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updatePortions(eqTo(laserDonutId), eqTo(SD.listUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a portion") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getPortion(id)).thenReturn(Future.successful(Some(SD.portion)))
        whenReady(server.planningService.getPortion(id)) { result =>
          result shouldBe Some(SD.portion)
          verify(server.repo, times(1)).getPortion(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve the current portion") {
        val server = newServer
        when(server.repo.getCurrentPortion).thenReturn(Future.successful(Some(SD.portion)))
        whenReady(server.planningService.getCurrentPortion) { result =>
          result shouldBe Some(SD.portion)
          verify(server.repo, times(1)).getCurrentPortion
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all portions") {
        val server = newServer
        val portions = Seq(SD.portion, SD.portion.copy(uuid = UUID.randomUUID))
        when(server.repo.getPortions).thenReturn(Future.successful(portions))
        whenReady(server.planningService.getPortions) { result =>
          result shouldBe portions
          verify(server.repo, times(1)).getPortions
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve portions that belong to a specific laser-donut") {
        val server = newServer
        val laserDonutId = UUID.randomUUID
        val portions = Seq(SD.portion, SD.portion.copy(uuid = UUID.randomUUID))
        when(server.repo.getPortions(laserDonutId)).thenReturn(Future.successful(portions))
        whenReady(server.planningService.getPortions(laserDonutId)) { result =>
          result shouldBe portions
          verify(server.repo, times(1)).getPortions(laserDonutId)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a portion") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deletePortion(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deletePortion(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deletePortion(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling todos") {
      it("should be able to create a todo") {
        val server = newServer
        when(server.repo.createTodo(SD.todoCreation)).thenReturn(Future.successful(SD.todo))
        whenReady(server.planningService.createTodo(SD.todoCreation)) { result =>
          result shouldBe SD.todo
          verify(server.repo, times(1)).createTodo(SD.todoCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a todo") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.todo
        when(server.repo.updateTodo(eqTo(id), eqTo(SD.todoUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateTodo(id, SD.todoUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateTodo(eqTo(id), eqTo(SD.todoUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a todo is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = TodoNotFoundException()
        when(server.repo.updateTodo(eqTo(id), eqTo(SD.todoUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateTodo(id, SD.todoUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateTodo(eqTo(id), eqTo(SD.todoUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to reorder a list of todos that belong to a specific portion") {
        val server = newServer
        val portionId = UUID.randomUUID
        val updated = SD.todo :: SD.todo :: Nil
        when(server.repo.updateTodos(eqTo(portionId), eqTo(SD.listUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateTodos(portionId, SD.listUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateTodos(eqTo(portionId), eqTo(SD.listUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a list of todos is being updated") {
        val server = newServer
        val portionId = UUID.randomUUID
        val expectedException = InvalidTodosUpdateException("Wrong!")
        when(server.repo.updateTodos(eqTo(portionId), eqTo(SD.listUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateTodos(portionId, SD.listUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateTodos(eqTo(portionId), eqTo(SD.listUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a todo") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getTodo(id)).thenReturn(Future.successful(Some(SD.todo)))
        whenReady(server.planningService.getTodo(id)) { result =>
          result shouldBe Some(SD.todo)
          verify(server.repo, times(1)).getTodo(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all todos") {
        val server = newServer
        val todos = Seq(SD.todo, SD.todo.copy(uuid = UUID.randomUUID))
        when(server.repo.getTodos).thenReturn(Future.successful(todos))
        whenReady(server.planningService.getTodos) { result =>
          result shouldBe todos
          verify(server.repo, times(1)).getTodos
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve todos that belong to a specific portion") {
        val server = newServer
        val portionId = UUID.randomUUID
        val todos = Seq(SD.todo, SD.todo.copy(uuid = UUID.randomUUID))
        when(server.repo.getTodos(portionId)).thenReturn(Future.successful(todos))
        whenReady(server.planningService.getTodos(portionId)) { result =>
          result shouldBe todos
          verify(server.repo, times(1)).getTodos(portionId)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a todo") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteTodo(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteTodo(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteTodo(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling hobbies") {
      it("should be able to create a hobby") {
        val server = newServer
        when(server.repo.createHobby(SD.hobbyCreation)).thenReturn(Future.successful(SD.hobby))
        whenReady(server.planningService.createHobby(SD.hobbyCreation)) { result =>
          result shouldBe SD.hobby
          verify(server.repo, times(1)).createHobby(SD.hobbyCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a hobby") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.hobby
        when(server.repo.updateHobby(eqTo(id), eqTo(SD.hobbyUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateHobby(id, SD.hobbyUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateHobby(eqTo(id), eqTo(SD.hobbyUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a hobby is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = HobbyNotFoundException()
        when(server.repo.updateHobby(eqTo(id), eqTo(SD.hobbyUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateHobby(id, SD.hobbyUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateHobby(eqTo(id), eqTo(SD.hobbyUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a hobby") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getHobby(id)).thenReturn(Future.successful(Some(SD.hobby)))
        whenReady(server.planningService.getHobby(id)) { result =>
          result shouldBe Some(SD.hobby)
          verify(server.repo, times(1)).getHobby(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all hobbies") {
        val server = newServer
        val hobbies = Seq(SD.hobby, SD.hobby.copy(uuid = UUID.randomUUID))
        when(server.repo.getHobbies).thenReturn(Future.successful(hobbies))
        whenReady(server.planningService.getHobbies) { result =>
          result shouldBe hobbies
          verify(server.repo, times(1)).getHobbies
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve hobbies that belong to a specific goal") {
        val server = newServer
        val goalId = UUID.randomUUID
        val hobbies = Seq(SD.hobby, SD.hobby.copy(uuid = UUID.randomUUID))
        when(server.repo.getHobbies(goalId)).thenReturn(Future.successful(hobbies))
        whenReady(server.planningService.getHobbies(goalId)) { result =>
          result shouldBe hobbies
          verify(server.repo, times(1)).getHobbies(goalId)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a hobby") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteHobby(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteHobby(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteHobby(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling one-offs") {
      it("should be able to create a one-off") {
        val server = newServer
        when(server.repo.createOneOff(SD.oneOffCreation)).thenReturn(Future.successful(SD.oneOff))
        whenReady(server.planningService.createOneOff(SD.oneOffCreation)) { result =>
          result shouldBe SD.oneOff
          verify(server.repo, times(1)).createOneOff(SD.oneOffCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a one-off") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.oneOff
        when(server.repo.updateOneOff(eqTo(id), eqTo(SD.oneOffUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateOneOff(id, SD.oneOffUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateOneOff(eqTo(id), eqTo(SD.oneOffUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a one-off is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = OneOffNotFoundException()
        when(server.repo.updateOneOff(eqTo(id), eqTo(SD.oneOffUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateOneOff(id, SD.oneOffUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateOneOff(eqTo(id), eqTo(SD.oneOffUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to reorder a list of one-offs that belong to a specific portion") {
        val server = newServer
        val updated = SD.oneOff :: SD.oneOff :: Nil
        when(server.repo.updateOneOffs(eqTo(SD.listUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateOneOffs(SD.listUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateOneOffs(eqTo(SD.listUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a list of one-offs is being updated") {
        val server = newServer
        val expectedException = InvalidOneOffsUpdateException("Wrong!")
        when(server.repo.updateOneOffs(eqTo(SD.listUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateOneOffs(SD.listUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateOneOffs(eqTo(SD.listUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a one-off") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getOneOff(id)).thenReturn(Future.successful(Some(SD.oneOff)))
        whenReady(server.planningService.getOneOff(id)) { result =>
          result shouldBe Some(SD.oneOff)
          verify(server.repo, times(1)).getOneOff(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all one-offs") {
        val server = newServer
        val oneOffs = Seq(SD.oneOff, SD.oneOff.copy(uuid = UUID.randomUUID))
        when(server.repo.getOneOffs).thenReturn(Future.successful(oneOffs))
        whenReady(server.planningService.getOneOffs) { result =>
          result shouldBe oneOffs
          verify(server.repo, times(1)).getOneOffs
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a one-off") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteOneOff(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteOneOff(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteOneOff(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling scheduled-one-offs") {
      it("should be able to create a scheduled-one-off") {
        val server = newServer
        when(server.repo.createScheduledOneOff(SD.scheduledOneOffCreation)).thenReturn(Future.successful(SD.scheduledOneOff))
        whenReady(server.planningService.createScheduledOneOff(SD.scheduledOneOffCreation)) { result =>
          result shouldBe SD.scheduledOneOff
          verify(server.repo, times(1)).createScheduledOneOff(SD.scheduledOneOffCreation)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to update a scheduled-one-off") {
        val server = newServer
        val id = UUID.randomUUID
        val updated = SD.scheduledOneOff
        when(server.repo.updateScheduledOneOff(eqTo(id), eqTo(SD.scheduledOneOffUpdate))).thenReturn(Future.successful(updated))
        whenReady(server.planningService.updateScheduledOneOff(id, SD.scheduledOneOffUpdate)) { result =>
          result shouldBe updated
          verify(server.repo, times(1)).updateScheduledOneOff(eqTo(id), eqTo(SD.scheduledOneOffUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should return an error thrown by the repository when a scheduled-one-off is being updated") {
        val server = newServer
        val id = UUID.randomUUID
        val expectedException = ScheduledOneOffNotFoundException()
        when(server.repo.updateScheduledOneOff(eqTo(id), eqTo(SD.scheduledOneOffUpdate))).thenReturn(Future.failed(expectedException))
        whenReady(server.planningService.updateScheduledOneOff(id, SD.scheduledOneOffUpdate).failed) { exception =>
          exception shouldBe expectedException
          verify(server.repo, times(1)).updateScheduledOneOff(eqTo(id), eqTo(SD.scheduledOneOffUpdate))
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a scheduled-one-off") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.getScheduledOneOff(id)).thenReturn(Future.successful(Some(SD.scheduledOneOff)))
        whenReady(server.planningService.getScheduledOneOff(id)) { result =>
          result shouldBe Some(SD.scheduledOneOff)
          verify(server.repo, times(1)).getScheduledOneOff(id)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve all scheduled-one-offs") {
        val server = newServer
        val scheduledOneOffs = Seq(SD.scheduledOneOff, SD.scheduledOneOff.copy(uuid = UUID.randomUUID))
        when(server.repo.getScheduledOneOffs(None)).thenReturn(Future.successful(scheduledOneOffs))
        whenReady(server.planningService.getScheduledOneOffs(None)) { result =>
          result shouldBe scheduledOneOffs
          verify(server.repo, times(1)).getScheduledOneOffs(None)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to delete a scheduled-one-off") {
        val server = newServer
        val id = UUID.randomUUID
        when(server.repo.deleteScheduledOneOff(id)).thenReturn(Future.successful(true))
        whenReady(server.planningService.deleteScheduledOneOff(id)) { result =>
          result shouldBe true
          verify(server.repo, times(1)).deleteScheduledOneOff(id)
          verifyNoMoreInteractions(server.repo)
        }
      }
    }

    describe("handling a pyramid of importance") {
      it("should be able to create a pyramid") {
        val server = newServer
        when(server.repo.createPyramidOfImportance(SD.pyramidUpsert)).thenReturn(Future.successful(SD.pyramid))
        whenReady(server.planningService.createPyramidOfImportance(SD.pyramidUpsert)) { result =>
          result shouldBe SD.pyramid
          verify(server.repo, times(1)).createPyramidOfImportance(SD.pyramidUpsert)
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to refresh a pyramid") {
        val server = newServer
        when(server.repo.refreshPyramidOfImportance()).thenReturn(Future.successful(true))
        whenReady(server.planningService.refreshPyramidOfImportance()) { result =>
          result shouldBe true
          verify(server.repo, times(1)).refreshPyramidOfImportance()
          verifyNoMoreInteractions(server.repo)
        }
      }

      it("should be able to retrieve a pyramid") {
        val server = newServer
        when(server.repo.getPyramidOfImportance).thenReturn(Future.successful(Some(SD.pyramid)))
        whenReady(server.planningService.getPyramidOfImportance) { result =>
          result.value shouldBe SD.pyramid
          verify(server.repo, times(1)).getPyramidOfImportance
          verifyNoMoreInteractions(server.repo)
        }
      }
    }
  }
}
