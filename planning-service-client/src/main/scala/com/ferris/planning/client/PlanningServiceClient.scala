package com.ferris.planning.client

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

import akka.http.scaladsl.model.Uri
import akka.http.scaladsl.model.Uri.Path._
import akka.http.scaladsl.model.Uri.Query
import akka.stream.ActorMaterializer
import com.ferris.microservice.resource.DeletionResult
import com.ferris.planning.contract.format.PlanningRestFormats
import com.ferris.planning.contract.resource.Resources.In._
import com.ferris.planning.contract.resource.Resources.Out._
import com.ferris.service.client.{HttpServer, ServiceClient}

import scala.concurrent.Future

class PlanningServiceClient(val server: HttpServer, implicit val mat: ActorMaterializer) extends ServiceClient with PlanningRestFormats {

  def this(server: HttpServer) = this(server, server.mat)

  private val apiPath = /("api")

  private val categoriesPath = "categories"
  private val skillsPath = "skills"
  private val practisedHoursPath = "practised-hours"
  private val incrementPath = "increment"
  private val relationshipsPath = "relationships"
  private val missionsPath = "missions"
  private val backlogItemsPath = "backlog-items"
  private val epochsPath = "epochs"
  private val yearsPath = "years"
  private val themesPath = "themes"
  private val goalsPath = "goals"
  private val threadsPath = "threads"
  private val weavesPath = "weaves"
  private val laserDonutsPath = "laser-donuts"
  private val portionsPath = "portions"
  private val todosPath = "todos"
  private val hobbiesPath = "hobbies"
  private val oneOffsPath = "one-offs"
  private val scheduledOneOffsPath = "scheduled-one-offs"
  private val pyramidPath = "pyramid"
  private val currentPath = "current"

  def createSkillCategory(creation: SkillCategoryCreation): Future[SkillCategoryView] =
    makePostRequest[SkillCategoryCreation, SkillCategoryView](Uri(path = apiPath / skillsPath / categoriesPath), creation)

  def createSkill(creation: SkillCreation): Future[SkillView] =
    makePostRequest[SkillCreation, SkillView](Uri(path = apiPath / skillsPath), creation)

  def createRelationship(creation: RelationshipCreation): Future[RelationshipView] =
    makePostRequest[RelationshipCreation, RelationshipView](Uri(path = apiPath / relationshipsPath), creation)

  def createMission(creation: MissionCreation): Future[MissionView] =
    makePostRequest[MissionCreation, MissionView](Uri(path = apiPath / missionsPath), creation)

  def createBacklogItem(creation: BacklogItemCreation): Future[BacklogItemView] =
    makePostRequest[BacklogItemCreation, BacklogItemView](Uri(path = apiPath / backlogItemsPath), creation)

  def createEpoch(creation: EpochCreation): Future[EpochView] =
    makePostRequest[EpochCreation, EpochView](Uri(path = apiPath / epochsPath), creation)

  def createYear(creation: YearCreation): Future[YearView] =
    makePostRequest[YearCreation, YearView](Uri(path = apiPath / yearsPath), creation)

  def createTheme(creation: ThemeCreation): Future[ThemeView] =
    makePostRequest[ThemeCreation, ThemeView](Uri(path = apiPath / themesPath), creation)

  def createGoal(creation: GoalCreation): Future[GoalView] =
    makePostRequest[GoalCreation, GoalView](Uri(path = apiPath / goalsPath), creation)

  def createThread(creation: ThreadCreation): Future[ThreadView] =
    makePostRequest[ThreadCreation, ThreadView](Uri(path = apiPath / threadsPath), creation)

  def createWeave(creation: WeaveCreation): Future[WeaveView] =
    makePostRequest[WeaveCreation, WeaveView](Uri(path = apiPath / weavesPath), creation)

  def createLaserDonut(creation: LaserDonutCreation): Future[LaserDonutView] =
    makePostRequest[LaserDonutCreation, LaserDonutView](Uri(path = apiPath / laserDonutsPath), creation)

  def createPortion(creation: PortionCreation): Future[PortionView] =
    makePostRequest[PortionCreation, PortionView](Uri(path = apiPath / portionsPath), creation)

  def createTodo(creation: TodoCreation): Future[TodoView] =
    makePostRequest[TodoCreation, TodoView](Uri(path = apiPath / todosPath), creation)

  def createHobby(creation: HobbyCreation): Future[HobbyView] =
    makePostRequest[HobbyCreation, HobbyView](Uri(path = apiPath / hobbiesPath), creation)

  def createOneOff(creation: OneOffCreation): Future[OneOffView] =
    makePostRequest[OneOffCreation, OneOffView](Uri(path = apiPath / oneOffsPath), creation)

  def createScheduledOneOff(creation: ScheduledOneOffCreation): Future[ScheduledOneOffView] =
    makePostRequest[ScheduledOneOffCreation, ScheduledOneOffView](Uri(path = apiPath / scheduledOneOffsPath), creation)

  def createPyramidOfImportance(creation: PyramidOfImportanceUpsert): Future[PyramidOfImportanceView] =
    makePostRequest[PyramidOfImportanceUpsert, PyramidOfImportanceView](Uri(path = apiPath / pyramidPath), creation)

  def updateSkillCategory(id: UUID, update: SkillCategoryUpdate): Future[SkillCategoryView] =
    makePutRequest[SkillCategoryUpdate, SkillCategoryView](Uri(path = apiPath / skillsPath / categoriesPath / id.toString), update)

  def updateSkill(id: UUID, update: SkillUpdate): Future[SkillView] =
    makePutRequest[SkillUpdate, SkillView](Uri(path = apiPath / skillsPath / id.toString), update)

  def updatePractisedHours(id: UUID, practisedHours: Long, time: LocalDateTime): Future[SkillView] =
    makePutRequest[PractisedHours, SkillView](Uri(path = apiPath / skillsPath / id.toString / practisedHoursPath / incrementPath), PractisedHours(practisedHours, time))

  def updateRelationship(id: UUID, update: RelationshipUpdate): Future[RelationshipView] =
    makePutRequest[RelationshipUpdate, RelationshipView](Uri(path = apiPath / relationshipsPath / id.toString), update)

  def updateMission(id: UUID, update: MissionUpdate): Future[MissionView] =
    makePutRequest[MissionUpdate, MissionView](Uri(path = apiPath / missionsPath / id.toString), update)

  def updateBacklogItem(id: UUID, update: BacklogItemUpdate): Future[BacklogItemView] =
    makePutRequest[BacklogItemUpdate, BacklogItemView](Uri(path = apiPath / backlogItemsPath / id.toString), update)

  def updateEpoch(id: UUID, update: EpochUpdate): Future[EpochView] =
    makePutRequest[EpochUpdate, EpochView](Uri(path = apiPath / epochsPath / id.toString), update)

  def updateYear(id: UUID, update: YearUpdate): Future[YearView] =
    makePutRequest[YearUpdate, YearView](Uri(path = apiPath / yearsPath / id.toString), update)

  def updateTheme(id: UUID, update: ThemeUpdate): Future[ThemeView] =
    makePutRequest[ThemeUpdate, ThemeView](Uri(path = apiPath / themesPath / id.toString), update)

  def updateGoal(id: UUID, update: GoalUpdate): Future[GoalView] =
    makePutRequest[GoalUpdate, GoalView](Uri(path = apiPath / goalsPath / id.toString), update)

  def updateThread(id: UUID, update: ThreadUpdate): Future[ThreadView] =
    makePutRequest[ThreadUpdate, ThreadView](Uri(path = apiPath / threadsPath / id.toString), update)

  def updateWeave(id: UUID, update: WeaveUpdate): Future[WeaveView] =
    makePutRequest[WeaveUpdate, WeaveView](Uri(path = apiPath / weavesPath / id.toString), update)

  def updateLaserDonut(id: UUID, update: LaserDonutUpdate): Future[LaserDonutView] =
    makePutRequest[LaserDonutUpdate, LaserDonutView](Uri(path = apiPath / laserDonutsPath / id.toString), update)

  def updatePortion(id: UUID, update: PortionUpdate): Future[PortionView] =
    makePutRequest[PortionUpdate, PortionView](Uri(path = apiPath / portionsPath / id.toString), update)

  def updateTodo(id: UUID, update: TodoUpdate): Future[TodoView] =
    makePutRequest[TodoUpdate, TodoView](Uri(path = apiPath / todosPath / id.toString), update)

  def updateHobby(id: UUID, update: HobbyUpdate): Future[HobbyView] =
    makePutRequest[HobbyUpdate, HobbyView](Uri(path = apiPath / hobbiesPath / id.toString), update)

  def updateOneOff(id: UUID, update: OneOffUpdate): Future[OneOffView] =
    makePutRequest[OneOffUpdate, OneOffView](Uri(path = apiPath / oneOffsPath / id.toString), update)

  def updateScheduledOneOff(id: UUID, update: ScheduledOneOffUpdate): Future[ScheduledOneOffView] =
    makePutRequest[ScheduledOneOffUpdate, ScheduledOneOffView](Uri(path = apiPath / scheduledOneOffsPath / id.toString), update)

  def skillCategory(id: UUID): Future[Option[SkillCategoryView]] =
    makeGetRequest[Option[SkillCategoryView]](Uri(path = apiPath / skillsPath / categoriesPath / id.toString))

  def skill(id: UUID): Future[Option[SkillView]] =
    makeGetRequest[Option[SkillView]](Uri(path = apiPath / skillsPath / id.toString))

  def relationship(id: UUID): Future[Option[RelationshipView]] =
    makeGetRequest[Option[RelationshipView]](Uri(path = apiPath / relationshipsPath / id.toString))

  def mission(id: UUID): Future[Option[MissionView]] =
    makeGetRequest[Option[MissionView]](Uri(path = apiPath / missionsPath / id.toString))

  def backlogItem(id: UUID): Future[Option[BacklogItemView]] =
    makeGetRequest[Option[BacklogItemView]](Uri(path = apiPath / backlogItemsPath / id.toString))

  def epoch(id: UUID): Future[Option[EpochView]] =
    makeGetRequest[Option[EpochView]](Uri(path = apiPath / epochsPath / id.toString))

  def year(id: UUID): Future[Option[YearView]] =
    makeGetRequest[Option[YearView]](Uri(path = apiPath / yearsPath / id.toString))

  def theme(id: UUID): Future[Option[ThemeView]] =
    makeGetRequest[Option[ThemeView]](Uri(path = apiPath / themesPath / id.toString))

  def goal(id: UUID): Future[Option[GoalView]] =
    makeGetRequest[Option[GoalView]](Uri(path = apiPath / goalsPath / id.toString))

  def thread(id: UUID): Future[Option[ThreadView]] =
    makeGetRequest[Option[ThreadView]](Uri(path = apiPath / threadsPath / id.toString))

  def weave(id: UUID): Future[Option[WeaveView]] =
    makeGetRequest[Option[WeaveView]](Uri(path = apiPath / weavesPath / id.toString))

  def laserDonut(id: UUID): Future[Option[LaserDonutView]] =
    makeGetRequest[Option[LaserDonutView]](Uri(path = apiPath / laserDonutsPath / id.toString))

  def currentLaserDonut: Future[Option[LaserDonutView]] =
    makeGetRequest[Option[LaserDonutView]](Uri(path = apiPath / laserDonutsPath / currentPath))

  def portion(id: UUID): Future[Option[PortionView]] =
    makeGetRequest[Option[PortionView]](Uri(path = apiPath / portionsPath / id.toString))

  def currentPortion: Future[Option[PortionView]] =
    makeGetRequest[Option[PortionView]](Uri(path = apiPath / portionsPath / currentPath))

  def todo(id: UUID): Future[Option[TodoView]] =
    makeGetRequest[Option[TodoView]](Uri(path = apiPath / todosPath / id.toString))

  def hobby(id: UUID): Future[Option[HobbyView]] =
    makeGetRequest[Option[HobbyView]](Uri(path = apiPath / hobbiesPath / id.toString))

  def oneOff(id: UUID): Future[Option[OneOffView]] =
    makeGetRequest[Option[OneOffView]](Uri(path = apiPath / oneOffsPath / id.toString))

  def scheduledOneOff(id: UUID): Future[Option[ScheduledOneOffView]] =
    makeGetRequest[Option[ScheduledOneOffView]](Uri(path = apiPath / scheduledOneOffsPath / id.toString))

  def pyramidOfImportance: Future[Option[PyramidOfImportanceView]] =
    makeGetRequest[Option[PyramidOfImportanceView]](Uri(path = apiPath / pyramidPath))

  def skillCategories: Future[List[SkillCategoryView]] =
    makeGetRequest[List[SkillCategoryView]](Uri(path = apiPath / skillsPath / categoriesPath))

  def skills: Future[List[SkillView]] =
    makeGetRequest[List[SkillView]](Uri(path = apiPath / skillsPath))

  def relationships: Future[List[RelationshipView]] =
    makeGetRequest[List[RelationshipView]](Uri(path = apiPath / relationshipsPath))

  def missions: Future[List[MissionView]] =
    makeGetRequest[List[MissionView]](Uri(path = apiPath / missionsPath))

  def backlogItems: Future[List[BacklogItemView]] =
    makeGetRequest[List[BacklogItemView]](Uri(path = apiPath / backlogItemsPath))

  def epochs: Future[List[EpochView]] =
    makeGetRequest[List[EpochView]](Uri(path = apiPath / epochsPath))

  def years: Future[List[YearView]] =
    makeGetRequest[List[YearView]](Uri(path = apiPath / yearsPath))

  def themes: Future[List[ThemeView]] =
    makeGetRequest[List[ThemeView]](Uri(path = apiPath / themesPath))

  def goals: Future[List[GoalView]] =
    makeGetRequest[List[GoalView]](Uri(path = apiPath / goalsPath))

  def threads: Future[List[ThreadView]] =
    makeGetRequest[List[ThreadView]](Uri(path = apiPath / threadsPath))

  def weaves: Future[List[WeaveView]] =
    makeGetRequest[List[WeaveView]](Uri(path = apiPath / weavesPath))

  def laserDonuts: Future[List[LaserDonutView]] =
    makeGetRequest[List[LaserDonutView]](Uri(path = apiPath / laserDonutsPath))

  def portions: Future[List[PortionView]] =
    makeGetRequest[List[PortionView]](Uri(path = apiPath / portionsPath))

  def todos: Future[List[TodoView]] =
    makeGetRequest[List[TodoView]](Uri(path = apiPath / todosPath))

  def hobbies: Future[List[HobbyView]] =
    makeGetRequest[List[HobbyView]](Uri(path = apiPath / hobbiesPath))

  def oneOffs: Future[List[OneOffView]] =
    makeGetRequest[List[OneOffView]](Uri(path = apiPath / oneOffsPath))

  def scheduledOneOffs(date: Option[LocalDate] = None): Future[List[ScheduledOneOffView]] =
    makeGetRequest[List[ScheduledOneOffView]](Uri(path = apiPath / scheduledOneOffsPath)
      .withQuery(Query(params = date.map(chosenDate => Map("date" -> chosenDate.toString)).getOrElse(Map.empty[String, String]))))

  def deleteSkillCategory(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / skillsPath / categoriesPath / id.toString))

  def deleteSkill(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / skillsPath / id.toString))

  def deleteRelationship(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / relationshipsPath / id.toString))

  def deleteMission(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / missionsPath / id.toString))

  def deleteBacklogItem(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / backlogItemsPath / id.toString))

  def deleteEpoch(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / epochsPath / id.toString))

  def deleteYear(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / yearsPath / id.toString))

  def deleteTheme(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / themesPath / id.toString))

  def deleteGoal(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / goalsPath / id.toString))

  def deleteThread(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / threadsPath / id.toString))

  def deleteWeave(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / weavesPath / id.toString))

  def deleteLaserDonut(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / laserDonutsPath / id.toString))

  def deletePortion(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / portionsPath / id.toString))

  def deleteTodo(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / todosPath / id.toString))

  def deleteHobby(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / hobbiesPath / id.toString))

  def deleteOneOff(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / oneOffsPath / id.toString))

  def deleteScheduledOneOff(id: UUID): Future[DeletionResult] =
    makeDeleteRequest[DeletionResult](Uri(path = apiPath / scheduledOneOffsPath / id.toString))
}
