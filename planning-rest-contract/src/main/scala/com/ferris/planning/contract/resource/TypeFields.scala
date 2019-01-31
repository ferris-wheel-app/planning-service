package com.ferris.planning.contract.resource

object TypeFields {

  object BacklogItemType {
    val idea = "idea"
    val issue = "issue"
    val values = Set(idea, issue)
  }

  object Status {
    val planned = "planned"
    val inProgress = "in_progress"
    val complete = "complete"
    val values = Set(planned, inProgress, complete)
  }

  object GoalStatus {
    val notAchieved = "not_achieved"
    val employed = "employed"
    val unemployed = "unemployed"
    val values = Set(notAchieved, employed, unemployed)
  }

  object ThreadPerformance {
    val poor = "poor"
    val slipping = "slipping"
    val improving = "improving"
    val onTrack = "on_track"
    val values = Set(poor, slipping, improving, onTrack)
  }

  object GraduationType {
    val abandoned = "abandoned"
    val thread = "thread"
    val weave = "weave"
    val hobby = "hobby"
    val goal = "goal"
    val values = Set(abandoned, thread, weave, hobby, goal)
  }

  object DonutType {
    val projectFocused = "project_focused"
    val skillFocused = "skill_focused"
    val values = Set(projectFocused, skillFocused)
  }

  object WeaveType {
    val priority = "priority"
    val pdr = "pdr"
    val bau = "bau"
    val values = Set(priority, pdr, bau)
  }

  object HobbyType {
    val active = "active"
    val passive = "passive"
    val values = Set(active, passive)
  }

  object HobbyFrequency {
    val frequent = "frequent"
    val scattered = "scattered"
    val rare = "rare"
    val unexplored = "unexplored"
    val values = Set(frequent, scattered, rare, unexplored)
  }

  object Proficiency {
    val zero = "zero"
    val basic = "basic"
    val novice = "novice"
    val intermediate = "intermediate"
    val advanced = "advanced"
    val expert = "expert"
    val values = Set(zero, basic, novice, intermediate, advanced, expert)
  }

  object SkillLevel {
    val basic = "basic"
    val novice = "novice"
    val intermediate = "intermediate"
    val advanced = "advanced"
    val expert = "expert"
    val values = Set(basic, novice, intermediate, advanced, expert)
  }

  object SkillRelevance {
    val needed = "needed"
    val toBeAcquired = "to_be_acquired"
    val maintenance = "maintenance"
    val values = Set(needed, toBeAcquired, maintenance)
  }

  object RelationshipCategory {
    val family = "family"
    val friends = "friends"
    val work = "work"
    val romantic = "romantic"
    val mentorship = "mentorship"
    val values = Set(family, friends, work, romantic, mentorship)
  }

  object MissionLevel {
    val major = "major"
    val minor = "minor"
    val values = Set(major, minor)
  }
}
