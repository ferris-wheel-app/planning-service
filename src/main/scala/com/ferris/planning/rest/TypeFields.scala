package com.ferris.planning.rest

import com.ferris.planning.model.Model._

object TypeFields {

  sealed trait TypeResolver[T <: TypeEnum] {
    def withName(name: String): T
  }

  object BacklogItemType extends TypeResolver[BacklogItemTypes.BacklogItemType] {
    override def withName(name: String) = name match {
      case "idea" => BacklogItemTypes.Idea
      case "issue" => BacklogItemTypes.Issue
      case o => throw new IllegalArgumentException(s"Invalid backlog-item type: $o")
    }
  }

  object Status extends TypeResolver[Statuses.Status] {
    override def withName(name: String) = name match {
      case "unknown" => Statuses.Unknown
      case "not_reached" => Statuses.NotReached
      case "not_started" => Statuses.NotStarted
      case "incomplete" => Statuses.Incomplete
      case "complete" => Statuses.Complete
      case o => throw new IllegalArgumentException(s"Invalid status: $o")
    }
  }

  object GoalStatus extends TypeResolver[GoalStatuses.GoalStatus] {
    override def withName(name: String) = name match {
      case "not_achieved" => GoalStatuses.NotAchieved
      case "employed" => GoalStatuses.Employed
      case "unemployed" => GoalStatuses.Unemployed
    }
  }

  object GraduationType extends TypeResolver[GraduationTypes.GraduationType] {
    override def withName(name: String) = name match {
      case "abandoned" => GraduationTypes.Abandoned
      case "thread" => GraduationTypes.Thread
      case "weave" => GraduationTypes.Weave
      case "hobby" => GraduationTypes.Hobby
      case "goal" => GraduationTypes.Goal
    }
  }

  object DonutType extends TypeResolver[DonutTypes.DonutType] {
    override def withName(name: String) = name match {
      case "project_focused" => DonutTypes.ProjectFocused
      case "skill_focused" => DonutTypes.SkillFocused
    }
  }

  object WeaveType extends TypeResolver[WeaveTypes.WeaveType] {
    override def withName(name: String) = name match {
      case "priority" => WeaveTypes.Priority
      case "pdr" => WeaveTypes.PDR
      case "bau" => WeaveTypes.BAU
    }
  }

  object HobbyType extends TypeResolver[HobbyTypes.HobbyType] {
    override def withName(name: String) = name match {
      case "active" => HobbyTypes.Active
      case "passive" => HobbyTypes.Passive
    }
  }

  object HobbyFrequency extends TypeResolver[HobbyFrequencies.HobbyFrequency] {
    override def withName(name: String) = name match {
      case "one_off" => HobbyFrequencies.OneOff
      case "continuous" => HobbyFrequencies.Continuous
    }
  }
}
