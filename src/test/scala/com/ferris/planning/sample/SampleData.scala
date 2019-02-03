package com.ferris.planning.sample

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

import com.ferris.planning.command.Commands._
import com.ferris.planning.contract.resource.Resources.In._
import com.ferris.planning.contract.resource.Resources.Out._
import com.ferris.planning.model.Model._
import com.ferris.planning.service.conversions.TypeResolvers._

object SampleData {

  private val currentYear = LocalDate.now
  private val nextYear = currentYear.plusYears(1)
  private val scheduledDateTime = LocalDateTime.of(2018, 12, 3, 17, 16, 20)

  object domain {
    val skillCategoryCreation = CreateSkillCategory(
      name = "Functional Programming",
      parentCategory = Some(UUID.randomUUID)
    )

    val skillCategoryUpdate = UpdateSkillCategory(
      name = Some("Front-end development"),
      parentCategory = Some(UUID.randomUUID)
    )

    val skillCategory = SkillCategory(
      uuid = UUID.randomUUID,
      name = "Functional Programming",
      parentCategory = Some(UUID.randomUUID),
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now)
    )

    val skillCreation = CreateSkill(
      name = "Cats",
      parentCategory = UUID.randomUUID,
      proficiency = Proficiencies.Intermediate,
      practisedHours = 500L
    )

    val skillUpdate = UpdateSkill(
      name = Some("Cats"),
      parentCategory = Some(UUID.randomUUID),
      proficiency = Some(Proficiencies.Intermediate),
      practisedHours = Some(500L),
      lastPractise = Some(LocalDateTime.now)
    )

    val skill = Skill(
      uuid = UUID.randomUUID,
      name = "Cats",
      parentCategory = UUID.randomUUID,
      proficiency = Proficiencies.Intermediate,
      practisedHours = 500L,
      lastApplied = Some(LocalDateTime.now),
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now)
    )

    val associatedSkill = AssociatedSkill(
      skillId = UUID.randomUUID,
      relevance = SkillRelevances.Maintenance,
      level = Proficiencies.Intermediate
    )

    val relationshipCreation = CreateRelationship(
      name = "Daniel Larusso",
      category = RelationshipCategories.Friends,
      traits = "focused" :: "disciplined" :: Nil,
      likes = "karate" :: "cars" :: Nil,
      dislikes = "bullies" :: Nil,
      hobbies = "karate" :: Nil,
      lastMeet = Some(LocalDate.now)
    )

    val relationshipUpdate = UpdateRelationship(
      name = Some("Daniel Larusso"),
      category = Some(RelationshipCategories.Friends),
      traits = Some("focused" :: "disciplined" :: Nil),
      likes = Some("karate" :: "cars" :: Nil),
      dislikes = Some("bullies" :: Nil),
      hobbies = Some("karate" :: Nil),
      lastMeet = Some(LocalDate.now)
    )

    val relationship = Relationship(
      uuid = UUID.randomUUID,
      name = "Daniel Larusso",
      category = RelationshipCategories.Friends,
      traits = "focused" :: "disciplined" :: Nil,
      likes = "karate" :: "cars" :: Nil,
      dislikes = "bullies" :: Nil,
      hobbies = "karate" :: Nil,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastMeet = Some(LocalDate.now)
    )

    val missionCreation = CreateMission(
      name = "Messinaisance",
      description = "Become a better person"
    )

    val missionUpdate = UpdateMission(
      name = Some("Ferris Wheel"),
      description = Some("Create a positive feedback loop")
    )

    val mission = Mission(
      uuid = UUID.randomUUID,
      name = "Messinaisance",
      description = "Create a positive feedback loop",
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now)
    )

    val associatedMission = AssociatedMission(
      missionId = UUID.randomUUID,
      level = MissionLevels.Major
    )

    val valueDimensions = ValueDimensions(
      associatedMissions = UUID.randomUUID :: Nil,
      associatedSkills = associatedSkill :: Nil,
      relationships = UUID.randomUUID :: Nil,
      helpsSafetyNet = true,
      expandsWorldView = true
    )

    val valueDimensionsUpdate = UpdateValueDimensions(
      associatedMissions = Some(UUID.randomUUID :: Nil),
      associatedSkills = Some(associatedSkill :: Nil),
      relationships = Some(UUID.randomUUID :: Nil),
      helpsSafetyNet = Some(true),
      expandsWorldView = Some(true)
    )

    val backlogItemCreation = CreateBacklogItem(
      summary = "I need to get my shit together",
      description = "I need to get my shit together",
      `type` = BacklogItemTypes.Issue
    )

    val backlogItemUpdate = UpdateBacklogItem(
      summary = Some("I want to be the best of the best at programming"),
      description = Some("I want to be the best of the best at programming"),
      `type` = Some(BacklogItemTypes.Idea)
    )

    val backlogItem = BacklogItem(
      uuid = UUID.randomUUID,
      summary = "I need to get my shit together",
      description = "I need to get my shit together",
      `type` = BacklogItemTypes.Issue,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now)
    )

    val epochCreation = CreateEpoch(
      name = "Messinaissance",
      totem = "Hero",
      question = "Am I capable of becoming an Übermensch?",
      associatedMissions = associatedMission :: Nil
    )

    val epochUpdate = UpdateEpoch(
      name = Some("Wakanda"),
      totem = Some("Leader"),
      question = Some("Is Africa capable of achieving full development?"),
      associatedMissions = Some(associatedMission :: Nil)
    )

    val epoch = Epoch(
      uuid = UUID.randomUUID,
      name = "Messinaissance",
      totem = "Hero",
      question = "Am I capable of becoming an Übermensch?",
      associatedMissions = associatedMission :: Nil,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now)
    )

    val yearCreation = CreateYear(
      epochId = UUID.randomUUID,
      startDate = currentYear
    )

    val yearUpdate = UpdateYear(
      epochId = Some(UUID.randomUUID),
      startDate = Some(currentYear.plusYears(2))
    )

    val year = Year(
      uuid = UUID.randomUUID,
      epochId = UUID.randomUUID,
      startDate = currentYear,
      finishDate = nextYear,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now)
    )

    val themeCreation = CreateTheme(
      yearId = UUID.randomUUID,
      name = "Career Capital"
    )

    val themeUpdate = UpdateTheme(
      yearId = Some(UUID.randomUUID),
      name = Some("Mission")
    )

    val theme = Theme(
      uuid = UUID.randomUUID,
      yearId = UUID.randomUUID,
      name = "Career Capital",
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now)
    )

    val goalCreation = CreateGoal(
      themeId = UUID.randomUUID,
      backlogItems = Nil,
      summary = "Master at least one foreign language",
      description = "Learn French, Italian, and Korean",
      valueDimensions = valueDimensions,
      graduation = GraduationTypes.Hobby,
      status = GoalStatuses.NotAchieved
    )

    val goalUpdate = UpdateGoal(
      themeId = Some(UUID.randomUUID),
      backlogItems = Some(Nil),
      summary = Some("Learn to play an instrument"),
      description = Some("Learn to play the piano, the guitar, and the saxophone"),
      valueDimensions = Some(valueDimensionsUpdate),
      graduation = Some(GraduationTypes.Abandoned),
      status = Some(GoalStatuses.Employed)
    )

    val goal = Goal(
      uuid = UUID.randomUUID,
      themeId = UUID.randomUUID,
      backlogItems = UUID.randomUUID :: UUID.randomUUID :: Nil,
      summary = "Master at least one foreign language",
      description = "Learn French, Italian, and Korean",
      valueDimensions = valueDimensions,
      graduation = GraduationTypes.Hobby,
      status = GoalStatuses.NotAchieved,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now)
    )

    val threadCreation = CreateThread(
      goalId = Some(UUID.randomUUID),
      summary = "Go for a run",
      description = "Go for a run",
      valueDimensions = valueDimensions,
      performance = ThreadPerformances.Improving
    )

    val threadUpdate = UpdateThread(
      goalId = Some(UUID.randomUUID),
      summary = Some("Sleep"),
      description = Some("Sleep for 8 hours"),
      valueDimensions = Some(valueDimensionsUpdate),
      performance = Some(ThreadPerformances.OnTrack)
    )

    val thread = Thread(
      uuid = UUID.randomUUID,
      goalId = Some(UUID.randomUUID),
      summary = "Go for a run",
      description = "Go for a run",
      valueDimensions = valueDimensions,
      performance = ThreadPerformances.OnTrack,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastPerformed = Some(LocalDateTime.now)
    )

    val weaveCreation = CreateWeave(
      goalId = Some(UUID.randomUUID),
      summary = "Organise a tech lecture",
      description = "Create a presentation about Kafka",
      valueDimensions = valueDimensions,
      `type` = WeaveTypes.PDR,
      status = Statuses.Planned
    )

    val weaveUpdate = UpdateWeave(
      goalId = Some(UUID.randomUUID),
      summary = Some("Apply your new-found Go knowledge"),
      description = Some("Create a snuffleupagus"),
      valueDimensions = Some(valueDimensionsUpdate),
      `type` = Some(WeaveTypes.BAU),
      status = Some(Statuses.Complete)
    )

    val weave = Weave(
      uuid = UUID.randomUUID,
      goalId = Some(UUID.randomUUID),
      summary = "Organise a tech lecture",
      description = "Create a presentation about Kafka",
      valueDimensions = valueDimensions,
      `type` = WeaveTypes.PDR,
      status = Statuses.Planned,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastPerformed = Some(LocalDateTime.now)
    )

    val laserDonutCreation = CreateLaserDonut(
      goalId = UUID.randomUUID,
      summary = "Implement initial microservices",
      description = "Implement planning-service, timetable-service, and history-service, in a microservices-based architecture",
      valueDimensions = valueDimensions,
      milestone = "A deployed backend service",
      `type` = DonutTypes.ProjectFocused,
      status = Statuses.InProgress
    )

    val laserDonutUpdate = UpdateLaserDonut(
      goalId = Some(UUID.randomUUID),
      summary = Some("Create the front-end"),
      description = Some("Use React"),
      valueDimensions = Some(valueDimensionsUpdate),
      milestone = Some("A basic working prototype"),
      `type` = Some(DonutTypes.ProjectFocused),
      status = Some(Statuses.InProgress)
    )

    val laserDonut = LaserDonut(
      uuid = UUID.randomUUID,
      goalId = UUID.randomUUID,
      summary = "Implement initial microservices",
      description = "Implement planning-service, timetable-service, and history-service, in a microservices-based architecture",
      valueDimensions = valueDimensions,
      milestone = "A basic working prototype",
      order = 1,
      `type` = DonutTypes.SkillFocused,
      status = Statuses.Planned,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastPerformed = Some(LocalDateTime.now)
    )

    val portionCreation = CreatePortion(
      laserDonutId = UUID.randomUUID,
      summary = "Write tests",
      status = Statuses.InProgress,
      valueDimensions = valueDimensions
    )

    val portionUpdate = UpdatePortion(
      laserDonutId = Some(UUID.randomUUID),
      summary = Some("Split into sub-projects"),
      status = Some(Statuses.InProgress),
      valueDimensions = Some(valueDimensionsUpdate)
    )

    val portion = Portion(
      uuid = UUID.randomUUID,
      laserDonutId = UUID.randomUUID,
      summary = "Write tests",
      order = 13,
      status = Statuses.InProgress,
      valueDimensions = valueDimensions,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastPerformed = Some(LocalDateTime.now)
    )

    val todoCreation = CreateTodo(
      parentId = UUID.randomUUID,
      description = "Create sample data for tests"
    )

    val todoUpdate = UpdateTodo(
      parentId = Some(UUID.randomUUID),
      description = Some("Create repository tests"),
      isDone = Some(true)
    )

    val todo = Todo(
      uuid = UUID.randomUUID,
      parentId = UUID.randomUUID,
      description = "Create sample data for tests",
      order = 4,
      isDone = true,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastPerformed = Some(LocalDateTime.now)
    )

    val hobbyCreation = CreateHobby(
      goalId = Some(UUID.randomUUID),
      summary = "Yoga",
      description = "Train in Acro-Yoga",
      valueDimensions = valueDimensions,
      frequency = HobbyFrequencies.Frequent,
      `type` = HobbyTypes.Active
    )

    val hobbyUpdate = UpdateHobby(
      goalId = Some(UUID.randomUUID),
      summary = Some("Play ping-pong"),
      description = Some("Table tennis"),
      valueDimensions = Some(valueDimensionsUpdate),
      frequency = Some(HobbyFrequencies.Frequent),
      `type` = Some(HobbyTypes.Active)
    )

    val hobby = Hobby(
      uuid = UUID.randomUUID,
      goalId = Some(UUID.randomUUID),
      summary = "Yoga",
      description = "Train in Acro-Yoga",
      valueDimensions = valueDimensions,
      frequency = HobbyFrequencies.Frequent,
      `type` = HobbyTypes.Active,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastPerformed = Some(LocalDateTime.now)
    )

    val oneOffCreation = CreateOneOff(
      goalId = Some(UUID.randomUUID),
      description = "Get window fixed",
      valueDimensions = valueDimensions,
      estimate = 14400000L,
      status = Statuses.Planned
    )

    val oneOffUpdate = UpdateOneOff(
      goalId = Some(UUID.randomUUID),
      description = Some("Get doors fixed"),
      valueDimensions = Some(valueDimensionsUpdate),
      estimate = Some(14400000L),
      status = Some(Statuses.Planned)
    )

    val oneOff = OneOff(
      uuid = UUID.randomUUID,
      goalId = Some(UUID.randomUUID),
      description = "Get window fixed",
      valueDimensions = valueDimensions,
      estimate = 14400000L,
      order = 5,
      status = Statuses.Planned,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastPerformed = Some(LocalDateTime.now)
    )

    val scheduledOneOffCreation = CreateScheduledOneOff(
      occursOn = scheduledDateTime,
      goalId = Some(UUID.randomUUID),
      description = "Get window fixed",
      valueDimensions = valueDimensions,
      estimate = 14400000L,
      status = Statuses.Planned
    )

    val scheduledOneOffUpdate = UpdateScheduledOneOff(
      occursOn = Some(scheduledDateTime),
      goalId = Some(UUID.randomUUID),
      description = Some("Get window fixed"),
      valueDimensions = Some(valueDimensionsUpdate),
      estimate = Some(14400000L),
      status = Some(Statuses.Planned)
    )

    val scheduledOneOff = ScheduledOneOff(
      occursOn = LocalDateTime.now,
      uuid = UUID.randomUUID,
      goalId = Some(UUID.randomUUID),
      description = "Get window fixed",
      valueDimensions = valueDimensions,
      estimate = 14400000L,
      status = Statuses.Planned,
      createdOn = LocalDateTime.now,
      lastModified = Some(LocalDateTime.now),
      lastPerformed = Some(LocalDateTime.now)
    )

    val listUpdate = UpdateList(
      reordered = UUID.randomUUID :: UUID.randomUUID :: Nil
    )

    val tierUpsert = UpsertTier(
      laserDonuts = (1 to 5).map(_ => UUID.randomUUID)
    )

    val tier = Tier(
      laserDonuts = UUID.randomUUID :: UUID.randomUUID :: Nil
    )

    val pyramidUpsert = UpsertPyramidOfImportance(
      tiers = (1 to 5).map(_ => tierUpsert)
    )

    val pyramid = PyramidOfImportance(
      tiers = tier :: Nil,
      currentLaserDonut = Some(UUID.randomUUID)
    )

    val scheduledTodo = ScheduledTodo(
      uuid = UUID.randomUUID,
      order = 1,
      isDone = false
    )

    val scheduledPortion = ScheduledPortion(
      id = 1,
      uuid = UUID.randomUUID,
      todos = scheduledTodo :: Nil,
      order = 1,
      status = Statuses.Planned
    )

    val scheduledLaserDonut = ScheduledLaserDonut(
      id = 1,
      uuid = UUID.randomUUID,
      portions = scheduledPortion :: Nil,
      tier = 1,
      status = Statuses.Planned,
      lastPerformed = Some(LocalDateTime.now)
    )

    val scheduledPyramid = ScheduledPyramid(
      laserDonuts = scheduledLaserDonut :: Nil,
      currentLaserDonut = Some(2),
      currentPortion = Some(3),
      lastUpdate = Some(LocalDateTime.now)
    )
  }

  object rest {
    val skillCategoryCreation = SkillCategoryCreation(
      name = domain.skillCategoryCreation.name,
      parentCategory = domain.skillCategoryCreation.parentCategory
    )

    val skillCategoryUpdate = SkillCategoryUpdate(
      name = domain.skillCategoryUpdate.name,
      parentCategory = domain.skillCategoryUpdate.parentCategory
    )

    val skillCategory = SkillCategoryView(
      uuid = domain.skillCategory.uuid,
      name = domain.skillCategory.name,
      parentCategory = domain.skillCategory.parentCategory
    )

    val skillCreation = SkillCreation(
      name = domain.skillCreation.name,
      parentCategory = domain.skillCreation.parentCategory,
      proficiency = Proficiency.toString(domain.skillCreation.proficiency),
      practisedHours = domain.skillCreation.practisedHours
    )

    val skillUpdate = SkillUpdate(
      name = domain.skillUpdate.name,
      parentCategory = domain.skillUpdate.parentCategory,
      proficiency = domain.skillUpdate.proficiency.map(Proficiency.toString),
      practisedHours = domain.skillUpdate.practisedHours,
      lastPractise = domain.skillUpdate.lastPractise
    )

    val skill = SkillView(
      uuid = domain.skill.uuid,
      name = domain.skill.name,
      parentCategory = domain.skill.parentCategory,
      proficiency = Proficiency.toString(domain.skill.proficiency),
      practisedHours = domain.skill.practisedHours,
      createdOn = domain.skill.createdOn,
      lastModified = domain.skill.lastModified,
      lastApplied = domain.skill.lastApplied
    )

    val practisedHours = PractisedHours(
      value = 1000L,
      time = LocalDateTime.now
    )

    val associatedSkillInsertion = AssociatedSkillInsertion(
      skillId = domain.associatedSkill.skillId,
      relevance = SkillRelevance.toString(domain.associatedSkill.relevance),
      level = SkillLevel.toString(domain.associatedSkill.level)
    )

    val associatedSkill = AssociatedSkillView(
      skillId = domain.associatedSkill.skillId,
      relevance = SkillRelevance.toString(domain.associatedSkill.relevance),
      level = SkillLevel.toString(domain.associatedSkill.level)
    )

    val relationshipCreation = RelationshipCreation(
      name = domain.relationshipCreation.name,
      category = RelationshipCategory.toString(domain.relationshipCreation.category),
      traits = domain.relationshipCreation.traits,
      likes = domain.relationshipCreation.likes,
      dislikes = domain.relationshipCreation.dislikes,
      hobbies = domain.relationshipCreation.hobbies,
      lastMeet = domain.relationshipCreation.lastMeet
    )

    val relationshipUpdate = RelationshipUpdate(
      name = domain.relationshipUpdate.name,
      category = domain.relationshipUpdate.category.map(RelationshipCategory.toString),
      traits = domain.relationshipUpdate.traits,
      likes = domain.relationshipUpdate.likes,
      dislikes = domain.relationshipUpdate.dislikes,
      hobbies = domain.relationshipUpdate.hobbies,
      lastMeet = domain.relationshipUpdate.lastMeet
    )

    val relationship = RelationshipView(
      uuid = domain.relationship.uuid,
      name = domain.relationship.name,
      category = RelationshipCategory.toString(domain.relationship.category),
      traits = domain.relationship.traits,
      likes = domain.relationship.likes,
      dislikes = domain.relationship.dislikes,
      hobbies = domain.relationship.hobbies,
      createdOn = domain.relationship.createdOn,
      lastModified = domain.relationship.lastModified,
      lastMeet = domain.relationship.lastMeet
    )

    val missionCreation = MissionCreation(
      name = domain.missionCreation.name,
      description = domain.missionCreation.description
    )

    val missionUpdate = MissionUpdate(
      name = domain.missionUpdate.name,
      description = domain.missionUpdate.description
    )

    val mission = MissionView(
      uuid = domain.mission.uuid,
      name = domain.mission.name,
      description = domain.mission.description,
      createdOn = domain.mission.createdOn,
      lastModified = domain.mission.lastModified
    )

    val associatedMissionInsertion = AssociatedMissionInsertion(
      missionId = domain.associatedMission.missionId,
      level = MissionLevel.toString(domain.associatedMission.level)
    )

    val associatedMission = AssociatedMissionView(
      missionId = domain.associatedMission.missionId,
      level = MissionLevel.toString(domain.associatedMission.level)
    )

    val valueDimensionsCreation = ValueDimensionsCreation(
      associatedMissions = domain.valueDimensions.associatedMissions,
      associatedSkills = associatedSkillInsertion :: Nil,
      relationships = domain.valueDimensions.relationships,
      helpsSafetyNet = domain.valueDimensions.helpsSafetyNet,
      expandsWorldView = domain.valueDimensions.expandsWorldView
    )

    val valueDimensionsUpdate = ValueDimensionsUpdate(
      associatedMissions = domain.valueDimensionsUpdate.associatedMissions,
      associatedSkills = Some(associatedSkillInsertion :: Nil),
      relationships = domain.valueDimensionsUpdate.relationships,
      helpsSafetyNet = domain.valueDimensionsUpdate.helpsSafetyNet,
      expandsWorldView = domain.valueDimensionsUpdate.expandsWorldView
    )

    val valueDimensions = ValueDimensionsView(
      associatedMissions = domain.valueDimensions.associatedMissions,
      associatedSkills = associatedSkill :: Nil,
      relationships = domain.valueDimensions.relationships,
      helpsSafetyNet = domain.valueDimensions.helpsSafetyNet,
      expandsWorldView = domain.valueDimensions.expandsWorldView
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
      `type` = BacklogItemType.toString(domain.backlogItem.`type`),
      createdOn = domain.backlogItem.createdOn,
      lastModified = domain.backlogItem.lastModified
    )

    val epochCreation = EpochCreation(
      name = domain.epochCreation.name,
      totem = domain.epochCreation.totem,
      question = domain.epochCreation.question,
      associatedMissions = associatedMissionInsertion :: Nil
    )

    val epochUpdate = EpochUpdate(
      name = domain.epochUpdate.name,
      totem = domain.epochUpdate.totem,
      question = domain.epochUpdate.question,
      associatedMissions = Some(associatedMissionInsertion :: Nil)
    )

    val epoch = EpochView(
      uuid = domain.epoch.uuid,
      name = domain.epoch.name,
      totem = domain.epoch.totem,
      question = domain.epoch.question,
      associatedMissions = associatedMission :: Nil,
      createdOn = domain.epoch.createdOn,
      lastModified = domain.epoch.lastModified
    )

    val yearCreation = YearCreation(
      epochId = domain.yearCreation.epochId,
      startDate = domain.yearCreation.startDate
    )

    val yearUpdate = YearUpdate(
      epochId = domain.yearUpdate.epochId,
      startDate = domain.yearUpdate.startDate
    )

    val year = YearView(
      uuid = domain.year.uuid,
      epochId = domain.year.epochId,
      startDate = domain.year.startDate,
      finishDate = domain.year.finishDate,
      createdOn = domain.year.createdOn,
      lastModified = domain.year.lastModified
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
      name = domain.theme.name,
      createdOn = domain.theme.createdOn,
      lastModified = domain.theme.lastModified
    )

    val goalCreation = GoalCreation(
      themeId = domain.goalCreation.themeId,
      backlogItems = domain.goalCreation.backlogItems,
      summary = domain.goalCreation.summary,
      description = domain.goalCreation.description,
      valueDimensions = valueDimensionsCreation,
      graduation = GraduationType.toString(domain.goalCreation.graduation),
      status = GoalStatus.toString(domain.goalCreation.status)
    )

    val goalUpdate = GoalUpdate(
      themeId = domain.goalUpdate.themeId,
      backlogItems = domain.goalUpdate.backlogItems,
      summary = domain.goalUpdate.summary,
      description = domain.goalUpdate.description,
      valueDimensions = Some(valueDimensionsUpdate),
      graduation = domain.goalUpdate.graduation.map(GraduationType.toString),
      status = domain.goalUpdate.status.map(GoalStatus.toString)
    )

    val goal = GoalView(
      uuid = domain.goal.uuid,
      themeId = domain.goal.themeId,
      backlogItems = domain.goal.backlogItems,
      summary = domain.goal.summary,
      description = domain.goal.description,
      valueDimensions = valueDimensions,
      graduation = GraduationType.toString(domain.goal.graduation),
      status = GoalStatus.toString(domain.goal.status),
      createdOn = domain.goal.createdOn,
      lastModified = domain.goal.lastModified
    )

    val threadCreation = ThreadCreation(
      goalId = domain.threadCreation.goalId,
      summary = domain.threadCreation.summary,
      description = domain.threadCreation.description,
      valueDimensions = valueDimensionsCreation,
      performance = ThreadPerformance.toString(domain.threadCreation.performance)
    )

    val threadUpdate = ThreadUpdate(
      goalId = domain.threadUpdate.goalId,
      summary = domain.threadUpdate.summary,
      description = domain.threadUpdate.description,
      valueDimensions = Some(valueDimensionsUpdate),
      performance = domain.threadUpdate.performance.map(ThreadPerformance.toString)
    )

    val thread = ThreadView(
      uuid = domain.thread.uuid,
      goalId = domain.thread.goalId,
      summary = domain.thread.summary,
      description = domain.thread.description,
      valueDimensions = valueDimensions,
      performance = ThreadPerformance.toString(domain.thread.performance),
      createdOn = domain.thread.createdOn,
      lastModified = domain.thread.lastModified,
      lastPerformed = domain.thread.lastPerformed
    )

    val weaveCreation = WeaveCreation(
      goalId = domain.weaveCreation.goalId,
      summary = domain.weaveCreation.summary,
      description = domain.weaveCreation.description,
      valueDimensions = valueDimensionsCreation,
      `type` = WeaveType.toString(domain.weaveCreation.`type`),
      status = Status.toString(domain.weaveCreation.status)
    )

    val weaveUpdate = WeaveUpdate(
      goalId = domain.weaveUpdate.goalId,
      summary = domain.weaveUpdate.summary,
      description = domain.weaveUpdate.description,
      valueDimensions = Some(valueDimensionsUpdate),
      `type` = domain.weaveUpdate.`type`.map(WeaveType.toString),
      status = domain.weaveUpdate.status.map(Status.toString)
    )

    val weave = WeaveView(
      uuid = domain.weave.uuid,
      goalId = domain.weave.goalId,
      summary = domain.weave.summary,
      description = domain.weave.description,
      valueDimensions = valueDimensions,
      `type` = WeaveType.toString(domain.weave.`type`),
      status = Status.toString(domain.weave.status),
      createdOn = domain.weave.createdOn,
      lastModified = domain.weave.lastModified,
      lastPerformed = domain.weave.lastPerformed
    )

    val laserDonutCreation = LaserDonutCreation(
      goalId = domain.laserDonutCreation.goalId,
      summary = domain.laserDonutCreation.summary,
      description = domain.laserDonutCreation.description,
      valueDimensions = valueDimensionsCreation,
      milestone = domain.laserDonutCreation.milestone,
      `type` = DonutType.toString(domain.laserDonutCreation.`type`),
      status = Status.toString(domain.laserDonutCreation.status)
    )

    val laserDonutUpdate = LaserDonutUpdate(
      goalId = domain.laserDonutUpdate.goalId,
      summary = domain.laserDonutUpdate.summary,
      description = domain.laserDonutUpdate.description,
      valueDimensions = Some(valueDimensionsUpdate),
      milestone = domain.laserDonutUpdate.milestone,
      `type` = domain.laserDonutUpdate.`type`.map(DonutType.toString),
      status = domain.laserDonutUpdate.status.map(Status.toString)
    )

    val laserDonut = LaserDonutView(
      uuid = domain.laserDonut.uuid,
      goalId = domain.laserDonut.goalId,
      summary = domain.laserDonut.summary,
      description = domain.laserDonut.description,
      valueDimensions = valueDimensions,
      milestone = domain.laserDonut.milestone,
      order = domain.laserDonut.order,
      `type` = DonutType.toString(domain.laserDonut.`type`),
      status = Status.toString(domain.laserDonut.status),
      createdOn = domain.laserDonut.createdOn,
      lastModified = domain.laserDonut.lastModified,
      lastPerformed = domain.laserDonut.lastPerformed
    )

    val portionCreation = PortionCreation(
      laserDonutId = domain.portionCreation.laserDonutId,
      summary = domain.portionCreation.summary,
      status = Status.toString(domain.portionCreation.status),
      valueDimensions = valueDimensionsCreation
    )

    val portionUpdate = PortionUpdate(
      laserDonutId = domain.portionUpdate.laserDonutId,
      summary = domain.portionUpdate.summary,
      status = domain.portionUpdate.status.map(Status.toString),
      valueDimensions = Some(valueDimensionsUpdate)
    )

    val portion = PortionView(
      uuid = domain.portion.uuid,
      laserDonutId = domain.portion.laserDonutId,
      summary = domain.portion.summary,
      order = domain.portion.order,
      status = Status.toString(domain.portion.status),
      valueDimensions = valueDimensions,
      createdOn = domain.portion.createdOn,
      lastModified = domain.portion.lastModified,
      lastPerformed = domain.portion.lastPerformed
    )

    val todoCreation = TodoCreation(
      parentId = domain.todoCreation.parentId,
      description = domain.todoCreation.description
    )

    val todoUpdate = TodoUpdate(
      parentId = domain.todoUpdate.parentId,
      description = domain.todoUpdate.description,
      isDone = domain.todoUpdate.isDone
    )

    val todo = TodoView(
      uuid = domain.todo.uuid,
      parentId = domain.todo.parentId,
      description = domain.todo.description,
      order = domain.todo.order,
      isDone = domain.todo.isDone,
      createdOn = domain.todo.createdOn,
      lastModified = domain.todo.lastModified,
      lastPerformed = domain.todo.lastPerformed
    )

    val hobbyCreation = HobbyCreation(
      goalId = domain.hobbyCreation.goalId,
      summary = domain.hobbyCreation.summary,
      description = domain.hobbyCreation.description,
      valueDimensions = valueDimensionsCreation,
      frequency = HobbyFrequency.toString(domain.hobbyCreation.frequency),
      `type` = HobbyType.toString(domain.hobbyCreation.`type`)
    )

    val hobbyUpdate = HobbyUpdate(
      goalId = domain.hobbyUpdate.goalId,
      summary = domain.hobbyUpdate.summary,
      description = domain.hobbyUpdate.description,
      valueDimensions = Some(valueDimensionsUpdate),
      frequency = domain.hobbyUpdate.frequency.map(HobbyFrequency.toString),
      `type` = domain.hobbyUpdate.`type`.map(HobbyType.toString)
    )

    val hobby = HobbyView(
      uuid = domain.hobby.uuid,
      goalId = domain.hobby.goalId,
      summary = domain.hobby.summary,
      description = domain.hobby.description,
      valueDimensions = valueDimensions,
      frequency = HobbyFrequency.toString(domain.hobby.frequency),
      `type` = HobbyType.toString(domain.hobby.`type`),
      createdOn = domain.hobby.createdOn,
      lastModified = domain.hobby.lastModified,
      lastPerformed = domain.hobby.lastPerformed
    )

    val oneOffCreation = OneOffCreation(
      goalId = domain.oneOffCreation.goalId,
      description = domain.oneOffCreation.description,
      valueDimensions = valueDimensionsCreation,
      estimate = domain.oneOffCreation.estimate,
      status = Status.toString(domain.oneOffCreation.status)
    )

    val oneOffUpdate = OneOffUpdate(
      goalId = domain.oneOffUpdate.goalId,
      description = domain.oneOffUpdate.description,
      valueDimensions = Some(valueDimensionsUpdate),
      estimate = domain.oneOffUpdate.estimate,
      status = domain.oneOffUpdate.status.map(Status.toString)
    )

    val oneOff = OneOffView(
      uuid = domain.oneOff.uuid,
      goalId = domain.oneOff.goalId,
      description = domain.oneOff.description,
      valueDimensions = valueDimensions,
      estimate = domain.oneOff.estimate,
      order = 5,
      status = Status.toString(domain.oneOff.status),
      createdOn = domain.oneOff.createdOn,
      lastModified = domain.oneOff.lastModified,
      lastPerformed = domain.oneOff.lastPerformed
    )

    val scheduledOneOffCreation = ScheduledOneOffCreation(
      occursOn = domain.scheduledOneOffCreation.occursOn,
      goalId = domain.scheduledOneOffCreation.goalId,
      description = domain.scheduledOneOffCreation.description,
      valueDimensions = valueDimensionsCreation,
      estimate = domain.scheduledOneOffCreation.estimate,
      status = Status.toString(domain.scheduledOneOffCreation.status)
    )

    val scheduledOneOffUpdate = ScheduledOneOffUpdate(
      occursOn = domain.scheduledOneOffUpdate.occursOn,
      goalId = domain.scheduledOneOffUpdate.goalId,
      description = domain.scheduledOneOffUpdate.description,
      valueDimensions = Some(valueDimensionsUpdate),
      estimate = domain.scheduledOneOffUpdate.estimate,
      status = domain.scheduledOneOffUpdate.status.map(Status.toString)
    )

    val scheduledOneOff = ScheduledOneOffView(
      uuid = domain.scheduledOneOff.uuid,
      occursOn = domain.scheduledOneOff.occursOn,
      goalId = domain.scheduledOneOff.goalId,
      description = domain.scheduledOneOff.description,
      valueDimensions = valueDimensions,
      estimate = domain.scheduledOneOff.estimate,
      status = Status.toString(domain.scheduledOneOff.status),
      createdOn = domain.scheduledOneOff.createdOn,
      lastModified = domain.scheduledOneOff.lastModified,
      lastPerformed = domain.scheduledOneOff.lastPerformed
    )

    val listUpdate = ListUpdate(
      reordered = domain.listUpdate.reordered
    )

    val tierUpsert = TierUpsert(
      laserDonuts = domain.tierUpsert.laserDonuts
    )

    val tier = TierView(
      laserDonuts = domain.tier.laserDonuts
    )

    val pyramidUpsert = PyramidOfImportanceUpsert(
      tiers = domain.pyramidUpsert.tiers.map(tier => TierUpsert(tier.laserDonuts))
    )

    val pyramid = PyramidOfImportanceView(
      tiers = domain.pyramid.tiers.map(tier => TierView(tier.laserDonuts))
    )
  }
}
