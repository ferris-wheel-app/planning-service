package com.ferris.planning.route

import java.time.LocalDate

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{PathMatchers, Route}
import akka.stream.Materializer
import com.ferris.microservice.conversions.ParameterConversions
import com.ferris.microservice.directive.FerrisDirectives
import com.ferris.planning.contract.format.PlanningRestFormats
import com.ferris.planning.service.conversions.ExternalToCommand._
import com.ferris.planning.service.PlanningServiceComponent
import com.ferris.planning.service.conversions.ModelToView._
import com.ferris.planning.contract.resource.Resources.In._

import scala.concurrent.ExecutionContext

trait PlanningRoute extends FerrisDirectives with PlanningRestFormats with PlanningResponseMappings with ParameterConversions {
  this: PlanningServiceComponent =>

  implicit def routeEc: ExecutionContext
  implicit val materializer: Materializer

  private val categoriesPathSegment = "categories"
  private val skillsPathSegment = "skills"
  private val practisedHoursPathSegment = "practised-hours"
  private val incrementPathSegment = "increment"
  private val relationshipsPathSegment = "relationships"
  private val missionsPathSegment = "missions"
  private val backlogItemsPathSegment = "backlog-items"
  private val epochsPathSegment = "epochs"
  private val yearsPathSegment = "years"
  private val themesPathSegment = "themes"
  private val goalsPathSegment = "goals"
  private val threadsPathSegment = "threads"
  private val weavesPathSegment = "weaves"
  private val laserDonutsPathSegment = "laser-donuts"
  private val portionsPathSegment = "portions"
  private val todosPathSegment = "todos"
  private val hobbiesPathSegment = "hobbies"
  private val oneOffsPathSegment = "one-offs"
  private val scheduledOneOffsPathSegment = "scheduled-one-offs"
  private val pyramidPathSegment = "pyramid"
  private val currentPathSegment = "current"
  private val refreshPathSegment = "refresh"

  private val createSkillCategoryRoute = pathPrefix(skillsPathSegment / categoriesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[SkillCategoryCreation]) { creation =>
          onSuccess(planningService.createSkillCategory(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createSkillRoute = pathPrefix(skillsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[SkillCreation]) { creation =>
          onSuccess(planningService.createSkill(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createRelationshipRoute = pathPrefix(relationshipsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[RelationshipCreation]) { creation =>
          onSuccess(planningService.createRelationship(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createMissionRoute = pathPrefix(missionsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[MissionCreation]) { creation =>
          onSuccess(planningService.createMission(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createBacklogItemRoute = pathPrefix(backlogItemsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[BacklogItemCreation]) { creation =>
          onSuccess(planningService.createBacklogItem(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createEpochRoute = pathPrefix(epochsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[EpochCreation]) { creation =>
          onSuccess(planningService.createEpoch(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createYearRoute = pathPrefix(yearsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[YearCreation]) { creation =>
          onSuccess(planningService.createYear(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createThemesRoute = pathPrefix(themesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[ThemeCreation]) { creation =>
          onSuccess(planningService.createTheme(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createGoalRoute = pathPrefix(goalsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[GoalCreation]) { creation =>
          onSuccess(planningService.createGoal(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createThreadRoute = pathPrefix(threadsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[ThreadCreation]) { creation =>
          onSuccess(planningService.createThread(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createWeaveRoute = pathPrefix(weavesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[WeaveCreation]) { creation =>
          onSuccess(planningService.createWeave(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createLaserDonutRoute = pathPrefix(laserDonutsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[LaserDonutCreation]) { creation =>
          onSuccess(planningService.createLaserDonut(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createPortionRoute = pathPrefix(portionsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[PortionCreation]) { creation =>
          onSuccess(planningService.createPortion(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createTodoRoute = pathPrefix(todosPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[TodoCreation]) { creation =>
          onSuccess(planningService.createTodo(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createHobbyRoute = pathPrefix(hobbiesPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[HobbyCreation]) { creation =>
          onSuccess(planningService.createHobby(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createOneOffRoute = pathPrefix(oneOffsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[OneOffCreation]) { creation =>
          onSuccess(planningService.createOneOff(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createScheduledOneOffRoute = pathPrefix(scheduledOneOffsPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[ScheduledOneOffCreation]) { creation =>
          onSuccess(planningService.createScheduledOneOff(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val createPyramidRoute = pathPrefix(pyramidPathSegment) {
    pathEndOrSingleSlash {
      post {
        entity(as[PyramidOfImportanceUpsert]) { creation =>
          onSuccess(planningService.createPyramidOfImportance(creation.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateSkillCategoryRoute = pathPrefix(skillsPathSegment / categoriesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[SkillCategoryUpdate]) { update =>
          onSuccess(planningService.updateSkillCategory(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateSkillRoute = pathPrefix(skillsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[SkillUpdate]) { update =>
          onSuccess(planningService.updateSkill(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updatePractisedHoursRoute = pathPrefix(skillsPathSegment / PathMatchers.JavaUUID / practisedHoursPathSegment / incrementPathSegment) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[PractisedHours]) { update =>
          onSuccess(planningService.updatePractisedHours(id, update.value, update.time)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateRelationshipRoute = pathPrefix(relationshipsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[RelationshipUpdate]) { update =>
          onSuccess(planningService.updateRelationship(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateMissionRoute = pathPrefix(missionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[MissionUpdate]) { update =>
          onSuccess(planningService.updateMission(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateBacklogItemRoute = pathPrefix(backlogItemsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[BacklogItemUpdate]) { update =>
          onSuccess(planningService.updateBacklogItem(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateEpochRoute = pathPrefix(epochsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[EpochUpdate]) { update =>
          onSuccess(planningService.updateEpoch(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateYearRoute = pathPrefix(yearsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[YearUpdate]) { update =>
          onSuccess(planningService.updateYear(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateThemeRoute = pathPrefix(themesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[ThemeUpdate]) { update =>
          onSuccess(planningService.updateTheme(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[GoalUpdate]) { update =>
          onSuccess(planningService.updateGoal(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateThreadRoute = pathPrefix(threadsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[ThreadUpdate]) { update =>
          onSuccess(planningService.updateThread(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateWeaveRoute = pathPrefix(weavesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[WeaveUpdate]) { update =>
          onSuccess(planningService.updateWeave(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateLaserDonutRoute = pathPrefix(laserDonutsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[LaserDonutUpdate]) { update =>
          onSuccess(planningService.updateLaserDonut(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updatePortionsRoute = pathPrefix(laserDonutsPathSegment / PathMatchers.JavaUUID / portionsPathSegment) { laserDonutId =>
    pathEndOrSingleSlash {
      put {
        entity(as[ListUpdate]) { update =>
          onSuccess(planningService.updatePortions(laserDonutId, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.map(_.toView))
          }
        }
      }
    }
  }

  private val updatePortionRoute = pathPrefix(portionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[PortionUpdate]) { update =>
          onSuccess(planningService.updatePortion(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateTodosRoute = pathPrefix((portionsPathSegment | weavesPathSegment) / PathMatchers.JavaUUID / todosPathSegment) { portionId =>
    pathEndOrSingleSlash {
      put {
        entity(as[ListUpdate]) { update =>
          onSuccess(planningService.updateTodos(portionId, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.map(_.toView))
          }
        }
      }
    }
  }

  private val updateTodoRoute = pathPrefix(todosPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[TodoUpdate]) { update =>
          onSuccess(planningService.updateTodo(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateHobbyRoute = pathPrefix(hobbiesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[HobbyUpdate]) { update =>
          onSuccess(planningService.updateHobby(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateOneOffsRoute = pathPrefix(oneOffsPathSegment) {
    pathEndOrSingleSlash {
      put {
        entity(as[ListUpdate]) { update =>
          onSuccess(planningService.updateOneOffs(update.toCommand)) { response =>
            complete(StatusCodes.OK, response.map(_.toView))
          }
        }
      }
    }
  }

  private val updateOneOffRoute = pathPrefix(oneOffsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[OneOffUpdate]) { update =>
          onSuccess(planningService.updateOneOff(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val updateScheduledOneOffRoute = pathPrefix(scheduledOneOffsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      put {
        entity(as[ScheduledOneOffUpdate]) { update =>
          onSuccess(planningService.updateScheduledOneOff(id, update.toCommand)) { response =>
            complete(StatusCodes.OK, response.toView)
          }
        }
      }
    }
  }

  private val refreshPyramidRoute = pathPrefix(pyramidPathSegment / refreshPathSegment) {
    pathEndOrSingleSlash {
      put {
        onSuccess(planningService.refreshPyramidOfImportance())(outcome => complete(mapUpdate(outcome)))
      }
    }
  }

  private val refreshCurrentPortionRoute = pathPrefix(portionsPathSegment / currentPathSegment / refreshPathSegment) {
    pathEndOrSingleSlash {
      put {
        onSuccess(planningService.refreshPortion())(outcome => complete(mapUpdate(outcome)))
      }
    }
  }

  private val getSkillsCategoriesRoute = pathPrefix(skillsPathSegment / categoriesPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getSkillCategories) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getSkillsRoute = pathPrefix(skillsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getSkills) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getRelationshipsRoute = pathPrefix(relationshipsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getRelationships) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getMissionsRoute = pathPrefix(missionsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getMissions) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getBacklogItemsRoute = pathPrefix(backlogItemsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getBacklogItems) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getEpochsRoute = pathPrefix(epochsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getEpochs) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getYearsRoute = pathPrefix(yearsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getYears) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getThemesRoute = pathPrefix(themesPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getThemes) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getGoalsRoute = pathPrefix(goalsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getGoals) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getThreadsRoute = pathPrefix(threadsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getThreads) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getThreadsByGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID / threadsPathSegment) { goalId =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getThreads(goalId)) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getWeavesRoute = pathPrefix(weavesPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getWeaves) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getWeavesByGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID / weavesPathSegment) { goalId =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getWeaves(goalId)) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getLaserDonutsRoute = pathPrefix(laserDonutsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getLaserDonuts) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getLaserDonutsByGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID / laserDonutsPathSegment) { goalId =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getLaserDonuts(goalId)) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getPortionsRoute = pathPrefix(portionsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getPortions) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getPortionsByLaserDonutRoute = pathPrefix(laserDonutsPathSegment / PathMatchers.JavaUUID / portionsPathSegment) { laserDonutId =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getPortions(laserDonutId)) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getTodosRoute = pathPrefix(todosPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getTodos) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getTodosByParentRoute = pathPrefix((portionsPathSegment | weavesPathSegment) / PathMatchers.JavaUUID / todosPathSegment) { parentId =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getTodos(parentId)) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getHobbiesRoute = pathPrefix(hobbiesPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getHobbies) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getOneOffsRoute = pathPrefix(oneOffsPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getOneOffs) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getScheduledOneOffsRoute = pathPrefix(scheduledOneOffsPathSegment) {
    pathEndOrSingleSlash {
      get {
        parameters('date.as[LocalDate].?) { date =>
          onSuccess(planningService.getScheduledOneOffs(date)) { response =>
            complete(StatusCodes.OK, response.map(_.toView))
          }
        }
      }
    }
  }

  private val getHobbiesByGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID / hobbiesPathSegment) { goalId =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getHobbies(goalId)) { response =>
          complete(StatusCodes.OK, response.map(_.toView))
        }
      }
    }
  }

  private val getSkillCategoryRoute = pathPrefix(skillsPathSegment / categoriesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getSkillCategory(id))(outcome => complete(mapSkillCategory(outcome)))
      }
    }
  }

  private val getSkillRoute = pathPrefix(skillsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getSkill(id))(outcome => complete(mapSkill(outcome)))
      }
    }
  }

  private val getRelationshipRoute = pathPrefix(relationshipsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getRelationship(id))(outcome => complete(mapRelationship(outcome)))
      }
    }
  }

  private val getMissionRoute = pathPrefix(missionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getMission(id))(outcome => complete(mapMission(outcome)))
      }
    }
  }

  private val getBacklogItemRoute = pathPrefix(backlogItemsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getBacklogItem(id))(outcome => complete(mapBacklogItem(outcome)))
      }
    }
  }

  private val getEpochRoute = pathPrefix(epochsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getEpoch(id))(outcome => complete(mapEpoch(outcome)))
      }
    }
  }

  private val getYearRoute = pathPrefix(yearsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getYear(id))(outcome => complete(mapYear(outcome)))
      }
    }
  }

  private val getThemeRoute = pathPrefix(themesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getTheme(id))(outcome => complete(mapTheme(outcome)))
      }
    }
  }

  private val getGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getGoal(id))(outcome => complete(mapGoal(outcome)))
      }
    }
  }

  private val getThreadRoute = pathPrefix(threadsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getThread(id))(outcome => complete(mapThread(outcome)))
      }
    }
  }

  private val getWeaveRoute = pathPrefix(weavesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getWeave(id))(outcome => complete(mapWeave(outcome)))
      }
    }
  }

  private val getLaserDonutRoute = pathPrefix(laserDonutsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getLaserDonut(id))(outcome => complete(mapLaserDonut(outcome)))
      }
    }
  }

  private val getCurrentLaserDonutRoute = pathPrefix(laserDonutsPathSegment / currentPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getCurrentLaserDonut)(outcome => complete(mapLaserDonut(outcome)))
      }
    }
  }

  private val getPortionRoute = pathPrefix(portionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getPortion(id))(outcome => complete(mapPortion(outcome)))
      }
    }
  }

  private val getCurrentPortionRoute = pathPrefix(portionsPathSegment / currentPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getCurrentPortion)(outcome => complete(mapPortion(outcome)))
      }
    }
  }

  private val getTodoRoute = pathPrefix(todosPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getTodo(id))(outcome => complete(mapTodo(outcome)))
      }
    }
  }

  private val getHobbyRoute = pathPrefix(hobbiesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getHobby(id))(outcome => complete(mapHobby(outcome)))
      }
    }
  }

  private val getOneOffRoute = pathPrefix(oneOffsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getOneOff(id))(outcome => complete(mapOneOff(outcome)))
      }
    }
  }

  private val getScheduledOneOffRoute = pathPrefix(scheduledOneOffsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getScheduledOneOff(id))(outcome => complete(mapScheduledOneOff(outcome)))
      }
    }
  }

  private val getPyramidRoute = pathPrefix(pyramidPathSegment) {
    pathEndOrSingleSlash {
      get {
        onSuccess(planningService.getPyramidOfImportance)(outcome => complete(mapPyramid(outcome)))
      }
    }
  }

  private val deleteSkillCategoryRoute = pathPrefix(skillsPathSegment / categoriesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteSkillCategory(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteSkillRoute = pathPrefix(skillsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteSkill(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteRelationshipRoute = pathPrefix(relationshipsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteRelationship(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteMissionRoute = pathPrefix(missionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteMission(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteBacklogItemRoute = pathPrefix(backlogItemsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteBacklogItem(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteEpochRoute = pathPrefix(epochsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteEpoch(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteYearRoute = pathPrefix(yearsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteYear(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteThemeRoute = pathPrefix(themesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteTheme(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteGoalRoute = pathPrefix(goalsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteGoal(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteThreadRoute = pathPrefix(threadsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteThread(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteWeaveRoute = pathPrefix(weavesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteWeave(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteLaserDonutRoute = pathPrefix(laserDonutsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteLaserDonut(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deletePortionRoute = pathPrefix(portionsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deletePortion(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteTodoRoute = pathPrefix(todosPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteTodo(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteHobbyRoute = pathPrefix(hobbiesPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteHobby(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteOneOffRoute = pathPrefix(oneOffsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteOneOff(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  private val deleteScheduledOneOffRoute = pathPrefix(scheduledOneOffsPathSegment / PathMatchers.JavaUUID) { id =>
    pathEndOrSingleSlash {
      delete {
        onSuccess(planningService.deleteScheduledOneOff(id))(outcome => complete(mapDeletion(outcome)))
      }
    }
  }

  val planningRoute: Route = {
    createSkillCategoryRoute ~
    createSkillRoute ~
    createRelationshipRoute ~
    createMissionRoute ~
    createBacklogItemRoute ~
    createEpochRoute ~
    createYearRoute ~
    createThemesRoute ~
    createGoalRoute ~
    createThreadRoute ~
    createWeaveRoute ~
    createLaserDonutRoute ~
    createPortionRoute ~
    createTodoRoute ~
    createHobbyRoute ~
    createOneOffRoute ~
    createScheduledOneOffRoute ~
    createPyramidRoute ~
    updateSkillCategoryRoute ~
    updateSkillRoute ~
    updateRelationshipRoute ~
    updateMissionRoute ~
    updatePractisedHoursRoute ~
    updateBacklogItemRoute ~
    updateEpochRoute ~
    updateYearRoute ~
    updateThemeRoute ~
    updateGoalRoute ~
    updateThreadRoute ~
    updateWeaveRoute ~
    updateLaserDonutRoute ~
    updatePortionRoute ~
    updatePortionsRoute ~
    updateTodoRoute ~
    updateTodosRoute ~
    updateHobbyRoute ~
    updateOneOffRoute ~
    updateOneOffsRoute ~
    updateScheduledOneOffRoute ~
    refreshPyramidRoute ~
    refreshCurrentPortionRoute ~
    getSkillsCategoriesRoute ~
    getSkillsRoute ~
    getRelationshipsRoute ~
    getMissionsRoute ~
    getBacklogItemsRoute ~
    getEpochsRoute ~
    getYearsRoute ~
    getThemesRoute ~
    getGoalsRoute ~
    getThreadsRoute ~
    getThreadsByGoalRoute ~
    getWeavesRoute ~
    getWeavesByGoalRoute ~
    getLaserDonutsRoute ~
    getLaserDonutsByGoalRoute ~
    getPortionsRoute ~
    getPortionsByLaserDonutRoute ~
    getTodosRoute ~
    getTodosByParentRoute ~
    getHobbiesRoute ~
    getHobbiesByGoalRoute ~
    getOneOffsRoute ~
    getScheduledOneOffsRoute ~
    getSkillCategoryRoute ~
    getSkillRoute ~
    getRelationshipRoute ~
    getMissionRoute ~
    getBacklogItemRoute ~
    getEpochRoute ~
    getYearRoute ~
    getThemeRoute ~
    getGoalRoute ~
    getThreadRoute ~
    getWeaveRoute ~
    getLaserDonutRoute ~
    getCurrentLaserDonutRoute ~
    getPortionRoute ~
    getCurrentPortionRoute ~
    getTodoRoute ~
    getHobbyRoute ~
    getOneOffRoute ~
    getScheduledOneOffRoute ~
    getPyramidRoute ~
    deleteSkillCategoryRoute ~
    deleteSkillRoute ~
    deleteRelationshipRoute ~
    deleteMissionRoute ~
    deleteBacklogItemRoute ~
    deleteEpochRoute ~
    deleteYearRoute ~
    deleteThemeRoute ~
    deleteGoalRoute ~
    deleteThreadRoute ~
    deleteWeaveRoute ~
    deleteLaserDonutRoute ~
    deletePortionRoute ~
    deleteTodoRoute ~
    deleteHobbyRoute ~
    deleteOneOffRoute ~
    deleteScheduledOneOffRoute
  }
}
