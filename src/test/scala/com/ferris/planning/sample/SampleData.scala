package com.ferris.planning.sample

import java.util.UUID

import akka.http.scaladsl.model.DateTime
import com.ferris.planning.command.Commands._
import com.ferris.planning.model.Model._
import com.ferris.planning.rest.Resources.In._
import com.ferris.planning.rest.Resources.Out._
import com.ferris.planning.rest.TypeFields._

object SampleData {

  private val fullYear = 365 * 24 * 60 * 60
  private val currentYear = DateTime.now
  private val nextYear = currentYear.plus(fullYear)

  object domain {
    val messageCreation = CreateMessage(
      sender = "Dave",
      content = "Open the pod bay doors, HAL."
    )

    val messageUpdate = UpdateMessage(
      sender = Some("Dave"),
      content = Some("Open the pod bay doors, HAL. Please?")
    )

    val message = Message(
      uuid = UUID.randomUUID(),
      sender = "Dave",
      content = "Open the pod bay doors, HAL."
    )

    val backlogItemCreation = CreateBacklogItem(
      summary = "I need to get my shit together",
      description = "I need to get my shit together",
      `type` = BacklogItemTypes.Issue
    )

    val backlogItemUpdate = UpdateBacklogItem(
      summary = Some("I want to be the best of the best at programming"),
      description = Some("I want to be the best of the best at programming"),
      `type` = Some(BacklogItemTypes.Issue)
    )

    val backlogItem = BacklogItem(
      uuid = UUID.randomUUID,
      summary = "I need to get my shit together",
      description = "I need to get my shit together",
      `type` = BacklogItemTypes.Issue
    )

    val epochCreation = CreateEpoch(
      name = "Messinaissance",
      totem = "Hero",
      question = "Am I capable of becoming an Übermensch?"
    )

    val epochUpdate = UpdateEpoch(
      name = Some("Messinaissance"),
      totem = Some("Hero"),
      question = Some("Am I capable of becoming an Übermensch?")
    )

    val epoch = Epoch(
      uuid = UUID.randomUUID,
      name = "Messinaissance",
      totem = "Hero",
      question = "Am I capable of becoming an Übermensch?"
    )

    val yearCreation = CreateYear(
      epochId = UUID.randomUUID,
      startDate = currentYear,
      finishDate = nextYear
    )

    val yearUpdate = UpdateYear(
      epochId = Some(UUID.randomUUID),
      startDate = Some(currentYear),
      finishDate = Some(nextYear)
    )

    val year = Year(
      uuid = UUID.randomUUID,
      epochId = UUID.randomUUID,
      startDate = currentYear,
      finishDate = nextYear
    )

    val themeCreation = CreateTheme(
      yearId = UUID.randomUUID,
      name = "Career Capital"
    )

    val themeUpdate = UpdateTheme(
      yearId = Some(UUID.randomUUID),
      name = Some("Career Capital")
    )

    val theme = Theme(
      uuid = UUID.randomUUID,
      yearId = UUID.randomUUID,
      name = "Career Capital"
    )

    val goalCreation = CreateGoal(
      themeId = UUID.randomUUID,
      backlogItems = UUID.randomUUID :: UUID.randomUUID :: Nil,
      summary = "Master at least one foreign language",
      description = "Learn French, Italian, and Korean",
      level = 1,
      priority = false,
      graduation = GraduationTypes.Hobby,
      status = GoalStatuses.NotAchieved
    )

    val goalUpdate = UpdateGoal(
      themeId = Some(UUID.randomUUID),
      backlogItems = Some(UUID.randomUUID :: UUID.randomUUID :: Nil),
      summary = Some("Master at least one foreign language"),
      description = Some("Learn French, Italian, and Korean"),
      level = Some(2),
      priority = Some(false),
      graduation = Some(GraduationTypes.Hobby),
      status = Some(GoalStatuses.Employed)
    )

    val goal = Goal(
      uuid = UUID.randomUUID,
      themeId = UUID.randomUUID,
      backlogItems = UUID.randomUUID :: UUID.randomUUID :: Nil,
      summary = "Master at least one foreign language",
      description = "Learn French, Italian, and Korean",
      level = 1,
      priority = false,
      graduation = GraduationTypes.Hobby,
      status = GoalStatuses.NotAchieved
    )

    val threadCreation = CreateThread(
      goalId = Some(UUID.randomUUID),
      summary = "Go for a run",
      description = "Go for a run",
      status = Statuses.NotStarted
    )

    val threadUpdate = UpdateThread(
      goalId = Some(UUID.randomUUID),
      summary = Some("Go for a run"),
      description = Some("Go for a run"),
      status = Some(Statuses.NotStarted)
    )

    val thread = Thread(
      uuid = UUID.randomUUID,
      goalId = Some(UUID.randomUUID),
      summary = "Go for a run",
      description = "Go for a run",
      status = Statuses.NotStarted
    )

    val weaveCreation = CreateWeave(
      goalId = Some(UUID.randomUUID),
      summary = "Organise a tech lecture",
      description = "Create a presentation about Kafka",
      `type` = WeaveTypes.PDR,
      status = Statuses.NotStarted
    )

    val weaveUpdate = UpdateWeave(
      goalId = Some(UUID.randomUUID),
      summary = Some("Organise a tech lecture"),
      description = Some("Create a presentation about Kafka"),
      `type` = Some(WeaveTypes.PDR),
      status = Some(Statuses.Complete)
    )

    val weave = Weave(
      uuid = UUID.randomUUID,
      goalId = Some(UUID.randomUUID),
      summary = "Organise a tech lecture",
      description = "Create a presentation about Kafka",
      `type` = WeaveTypes.PDR,
      status = Statuses.NotStarted
    )

    val laserDonutCreation = CreateLaserDonut(
      goalId = UUID.randomUUID,
      summary = "Implement initial microservices",
      description = "Implement planning-service, timetable-service, and history-service, in a microservices-based architecture",
      milestone = "A basic working prototype",
      order = 1,
      `type` = DonutTypes.ProjectFocused,
      status = Statuses.Incomplete
    )

    val laserDonutUpdate = UpdateLaserDonut(
      goalId = Some(UUID.randomUUID),
      summary = Some("Implement initial microservices"),
      description = Some("Implement planning-service, timetable-service, and history-service, in a microservices-based architecture"),
      milestone = Some("A basic working prototype"),
      order = Some(1),
      `type` = Some(DonutTypes.ProjectFocused),
      status = Some(Statuses.Incomplete)
    )

    val laserDonut = LaserDonut(
      uuid = UUID.randomUUID,
      goalId = UUID.randomUUID,
      summary = "Implement initial microservices",
      description = "Implement planning-service, timetable-service, and history-service, in a microservices-based architecture",
      milestone = "A basic working prototype",
      order = 1,
      `type` = DonutTypes.ProjectFocused,
      status = Statuses.Incomplete
    )

    val portionCreation = CreatePortion(
      laserDonutId = UUID.randomUUID,
      summary = "Write tests",
      order = 3,
      status = Statuses.Incomplete
    )

    val portionUpdate = UpdatePortion(
      laserDonutId = Some(UUID.randomUUID),
      summary = Some("Write tests"),
      order = Some(3),
      status = Some(Statuses.Incomplete)
    )

    val portion = Portion(
      uuid = UUID.randomUUID,
      laserDonutId = UUID.randomUUID,
      summary = "Write tests",
      order = 13,
      status = Statuses.Incomplete
    )

    val todoCreation = CreateTodo(
      portionId = UUID.randomUUID,
      description = "Create sample data for tests",
      order = 4,
      status = Statuses.Complete
    )

    val todoUpdate = UpdateTodo(
      portionId = Some(UUID.randomUUID),
      description = Some("Create sample data for tests"),
      order = Some(4),
      status = Some(Statuses.Complete)
    )

    val todo = Todo(
      uuid = UUID.randomUUID,
      portionId = UUID.randomUUID,
      description = "Create sample data for tests",
      order = 4,
      status = Statuses.Complete
    )

    val hobbyCreation = CreateHobby(
      goalId = Some(UUID.randomUUID),
      summary = "Yoga",
      description = "Train in Acro-Yoga",
      frequency = HobbyFrequencies.Continuous,
      `type` = HobbyTypes.Active,
      status = Statuses.NotReached
    )

    val hobbyUpdate = UpdateHobby(
      goalId = Some(UUID.randomUUID),
      summary = Some("Yoga"),
      description = Some("Train in Acro-Yoga"),
      frequency = Some(HobbyFrequencies.Continuous),
      `type` = Some(HobbyTypes.Active),
      status = Some(Statuses.NotReached)
    )

    val hobby = Hobby(
      uuid = UUID.randomUUID,
      goalId = Some(UUID.randomUUID),
      summary = "Yoga",
      description = "Train in Acro-Yoga",
      frequency = HobbyFrequencies.Continuous,
      `type` = HobbyTypes.Active,
      status = Statuses.NotReached
    )
  }

  object rest {
    val messageCreation = MessageCreation(
      sender = domain.messageCreation.sender,
      content = domain.messageCreation.content
    )

    val messageUpdate = MessageUpdate(
      sender = domain.messageUpdate.sender,
      content = domain.messageUpdate.content
    )

    val message = MessageView(
      uuid = domain.message.uuid,
      sender = domain.message.sender,
      content = domain.message.content
    )

    val backlogItemCreation = BacklogItemCreation(
      summary = domain.backlogItemCreation.summary,
      description = domain.backlogItemCreation.description,
      `type` = BacklogItemType.toString(domain.backlogItemCreation.`type`)
    )

    val backlogItemUpdate = BacklogItemUpdate(
      summary = domain.backlogItemUpdate.summary,
      description = domain.backlogItemUpdate.description,
      `type` = domain.backlogItemUpdate.`type`.map(BacklogItemType.toString)
    )

    val backlogItem = BacklogItemView(
      uuid = domain.backlogItem.uuid,
      summary = domain.backlogItem.summary,
      description = domain.backlogItem.description,
      `type` = BacklogItemType.toString(domain.backlogItem.`type`)
    )

    val yearCreation = YearCreation(
      epochId = domain.yearCreation.epochId,
      startDate = domain.yearCreation.startDate,
      finishDate = domain.yearCreation.finishDate
    )

    val yearUpdate = YearUpdate(
      epochId = domain.yearUpdate.epochId,
      startDate = domain.yearUpdate.startDate,
      finishDate = domain.yearUpdate.finishDate
    )

    val year = YearView(
      uuid = domain.year.uuid,
      epochId = domain.year.epochId,
      startDate = domain.year.startDate,
      finishDate = domain.year.finishDate
    )

    val themeCreation = ThemeCreation(
      yearId = domain.themeCreation.yearId,
      name = domain.themeCreation.name
    )

    val themeUpdate = ThemeUpdate(
      yearId = domain.themeUpdate.yearId,
      name = domain.themeUpdate.name
    )

    val theme = ThemeView(
      uuid = domain.theme.uuid,
      yearId = domain.theme.yearId,
      name = domain.theme.name
    )

    val goalCreation = GoalCreation(
      themeId = domain.goalCreation.themeId,
      backlogItems = domain.goalCreation.backlogItems,
      summary = domain.goalCreation.summary,
      description = domain.goalCreation.description,
      level = domain.goalCreation.level,
      priority = domain.goalCreation.priority,
      graduation = GraduationType.toString(domain.goalCreation.graduation),
      status = GoalStatus.toString(domain.goalCreation.status)
    )

    val goalUpdate = GoalUpdate(
      themeId = domain.goalUpdate.themeId,
      backlogItems = domain.goalUpdate.backlogItems,
      summary = domain.goalUpdate.summary,
      description = domain.goalUpdate.description,
      level = domain.goalUpdate.level,
      priority = domain.goalUpdate.priority,
      graduation = domain.goalUpdate.graduation.map(GraduationType.toString),
      status = domain.goalUpdate.status.map(GoalStatus.toString)
    )

    val goal = GoalView(
      uuid = domain.goal.uuid,
      themeId = domain.goal.themeId,
      backlogItems = domain.goal.backlogItems,
      summary = domain.goal.summary,
      description = domain.goal.description,
      level = domain.goal.level,
      priority = domain.goal.priority,
      graduation = GraduationType.toString(domain.goal.graduation),
      status = GoalStatus.toString(domain.goal.status)
    )

    val threadCreation = ThreadCreation(
      goalId = domain.threadCreation.goalId,
      summary = domain.threadCreation.summary,
      description = domain.threadCreation.description,
      status = Status.toString(domain.threadCreation.status)
    )

    val threadUpdate = ThreadUpdate(
      goalId = domain.threadUpdate.goalId,
      summary = domain.threadUpdate.summary,
      description = domain.threadUpdate.description,
      status = domain.threadUpdate.status.map(Status.toString)
    )

    val thread = ThreadView(
      uuid = UUID.randomUUID,
      goalId = domain.thread.goalId,
      summary = domain.thread.summary,
      description = domain.thread.description,
      status = Status.toString(domain.thread.status)
    )

    val weaveCreation = WeaveCreation(
      goalId = domain.weaveCreation.goalId,
      summary = domain.weaveCreation.summary,
      description = domain.weaveCreation.description,
      `type` = WeaveType.toString(domain.weaveCreation.`type`),
      status = Status.toString(domain.weaveCreation.status)
    )

    val weaveUpdate = WeaveUpdate(
      goalId = domain.weaveUpdate.goalId,
      summary = domain.weaveUpdate.summary,
      description = domain.weaveUpdate.description,
      `type` = domain.weaveUpdate.`type`.map(WeaveType.toString),
      status = domain.weaveUpdate.status.map(Status.toString)
    )

    val weave = WeaveView(
      uuid = domain.weave.uuid,
      goalId = domain.weave.goalId,
      summary = domain.weave.summary,
      description = domain.weave.description,
      `type` = WeaveType.toString(domain.weave.`type`),
      status = Status.toString(domain.weave.status)
    )

    val laserDonutCreation = LaserDonutCreation(
      goalId = domain.laserDonutCreation.goalId,
      summary = domain.laserDonutCreation.summary,
      description = domain.laserDonutCreation.description,
      milestone = domain.laserDonutCreation.milestone,
      order = domain.laserDonutCreation.order,
      `type` = DonutType.toString(domain.laserDonutCreation.`type`),
      status = Status.toString(domain.laserDonutCreation.status)
    )

    val laserDonutUpdate = LaserDonutUpdate(
      goalId = domain.laserDonutUpdate.goalId,
      summary = domain.laserDonutUpdate.summary,
      description = domain.laserDonutUpdate.description,
      milestone = domain.laserDonutUpdate.milestone,
      order = domain.laserDonutUpdate.order,
      `type` = domain.laserDonutUpdate.`type`.map(DonutType.toString),
      status = domain.laserDonutUpdate.status.map(Status.toString)
    )

    val laserDonut = LaserDonutView(
      uuid = domain.laserDonut.uuid,
      goalId = domain.laserDonut.goalId,
      summary = domain.laserDonut.summary,
      description = domain.laserDonut.description,
      milestone = domain.laserDonut.milestone,
      order = domain.laserDonut.order,
      `type` = DonutType.toString(domain.laserDonut.`type`),
      status = Status.toString(domain.laserDonut.status)
    )

    val portionCreation = PortionCreation(
      laserDonutId = domain.portionCreation.laserDonutId,
      summary = domain.portionCreation.summary,
      order = domain.portionCreation.order,
      status = Status.toString(domain.portionCreation.status)
    )

    val portionUpdate = PortionUpdate(
      laserDonutId = domain.portionUpdate.laserDonutId,
      summary = domain.portionUpdate.summary,
      order = domain.portionUpdate.order,
      status = domain.portionUpdate.status.map(Status.toString)
    )

    val portion = PortionView(
      uuid = domain.portion.uuid,
      laserDonutId = domain.portion.laserDonutId,
      summary = domain.portion.summary,
      order = domain.portion.order,
      status = Status.toString(domain.portion.status)
    )

    val todoCreation = TodoCreation(
      portionId = domain.todoCreation.portionId,
      description = domain.todoCreation.description,
      order = domain.todoCreation.order,
      status = Status.toString(domain.todoCreation.status)
    )

    val todoUpdate = TodoUpdate(
      portionId = domain.todoUpdate.portionId,
      description = domain.todoUpdate.description,
      order = domain.todoUpdate.order,
      status = domain.todoUpdate.status.map(Status.toString)
    )

    val todo = TodoView(
      uuid = domain.todo.uuid,
      portionId = domain.todo.portionId,
      description = domain.todo.description,
      order = domain.todo.order,
      status = Status.toString(domain.todo.status)
    )

    val hobbyCreation = HobbyCreation(
      goalId = domain.hobbyCreation.goalId,
      summary = domain.hobbyCreation.summary,
      description = domain.hobbyCreation.description,
      frequency = HobbyFrequency.toString(domain.hobbyCreation.frequency),
      `type` = HobbyType.toString(domain.hobbyCreation.`type`),
      status = Status.toString(domain.hobbyCreation.status)
    )

    val hobbyUpdate = HobbyUpdate(
      goalId = domain.hobbyUpdate.goalId,
      summary = domain.hobbyUpdate.summary,
      description = domain.hobbyUpdate.description,
      frequency = domain.hobbyUpdate.frequency.map(HobbyFrequency.toString),
      `type` = domain.hobbyUpdate.`type`.map(HobbyType.toString),
      status = domain.hobbyUpdate.status.map(Status.toString)
    )

    val hobby = HobbyView(
      uuid = domain.hobby.uuid,
      goalId = domain.hobby.goalId,
      summary = domain.hobby.summary,
      description = domain.hobby.description,
      frequency = HobbyFrequency.toString(domain.hobby.frequency),
      `type` = HobbyType.toString(domain.hobby.`type`),
      status = Status.toString(domain.hobby.status)
    )
  }
}
