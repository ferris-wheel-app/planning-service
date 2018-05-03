package com.ferris.planning.contract.resource

object TypeFields {

  object BacklogItemType {
    val idea = "idea"
    val issue = "issue"
    val values = Set(idea, issue)
  }

  object Status {
    val unknown = "unknown"
    val notReached = "not_reached"
    val notStarted = "not_started"
    val incomplete = "incomplete"
    val complete = "complete"
    val values = Set(unknown, notReached, notStarted, incomplete, complete)
  }

  object GoalStatus {
    val notAchieved = "not_achieved"
    val employed = "employed"
    val unemployed = "unemployed"
    val values = Set(notAchieved, employed, unemployed)
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
    val oneOff = "one_off"
    val continuous = "continuous"
    val values = Set(oneOff, continuous)
  }
}
