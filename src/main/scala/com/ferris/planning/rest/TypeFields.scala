package com.ferris.planning.rest

import com.ferris.planning.model.Model.BacklogItemTypes.BacklogItemType
import com.ferris.planning.model.Model.DonutTypes.DonutType
import com.ferris.planning.model.Model.GoalStatuses.GoalStatus
import com.ferris.planning.model.Model.GraduationTypes.GraduationType
import com.ferris.planning.model.Model.HobbyFrequencies.HobbyFrequency
import com.ferris.planning.model.Model.HobbyTypes.HobbyType
import com.ferris.planning.model.Model.Statuses.Status
import com.ferris.planning.model.Model.WeaveTypes.WeaveType
import com.ferris.planning.model.Model._

object TypeFields {

  sealed trait TypeResolver[T <: TypeEnum] {
    def withName(name: String): T
    def withType(`type`: T): String
  }

  object BacklogItemType extends TypeResolver[BacklogItemTypes.BacklogItemType] {
    val idea = "idea"
    val issue = "issue"

    override def withName(name: String): BacklogItemType = name match {
      case idea.toString => BacklogItemTypes.Idea
      case issue.toString => BacklogItemTypes.Issue
      case o => throw new IllegalArgumentException(s"Invalid backlog-item type: $o")
    }

    override def withType(`type`: BacklogItemType): String = `type` match {
      case BacklogItemTypes.Idea => idea
      case BacklogItemTypes.Issue => issue
      case o => throw new IllegalArgumentException(s"Invalid backlog-item type: $o")
    }
  }

  object Status extends TypeResolver[Statuses.Status] {
    val unknown = "unknown"
    val notReached = "not_reached"
    val notStarted = "not_started"
    val incomplete = "incomplete"
    val complete = "complete"

    override def withName(name: String): Status = name match {
      case unknown.toString => Statuses.Unknown
      case notReached.toString => Statuses.NotReached
      case notStarted.toString => Statuses.NotStarted
      case incomplete.toString => Statuses.Incomplete
      case complete.toString => Statuses.Complete
      case o => throw new IllegalArgumentException(s"Invalid status: $o")
    }

    override def withType(`type`: Status): String = `type` match {
      case Statuses.Unknown => unknown
      case Statuses.NotReached => notReached
      case Statuses.NotStarted => notStarted
      case Statuses.Incomplete => incomplete
      case Statuses.Complete => complete
      case o => throw new IllegalArgumentException(s"Invalid status: $o")
    }
  }

  object GoalStatus extends TypeResolver[GoalStatuses.GoalStatus] {
    val notAchieved = "not_achieved"
    val employed = "employed"
    val unemployed = "unemployed"

    override def withName(name: String): GoalStatus = name match {
      case notAchieved.toString => GoalStatuses.NotAchieved
      case employed.toString => GoalStatuses.Employed
      case unemployed.toString => GoalStatuses.Unemployed
      case o => throw new IllegalArgumentException(s"Invalid goal status: $o")
    }

    override def withType(`type`: GoalStatus): String = `type` match {
      case GoalStatuses.NotAchieved => notAchieved
      case GoalStatuses.Employed => employed
      case GoalStatuses.Unemployed => unemployed
      case o => throw new IllegalArgumentException(s"Invalid goal status: $o")
    }
  }

  object GraduationType extends TypeResolver[GraduationTypes.GraduationType] {
    val abandoned = "abandoned"
    val thread = "thread"
    val weave = "weave"
    val hobby = "hobby"
    val goal = "goal"

    override def withName(name: String): GraduationType = name match {
      case abandoned.toString => GraduationTypes.Abandoned
      case thread.toString => GraduationTypes.Thread
      case weave.toString => GraduationTypes.Weave
      case hobby.toString => GraduationTypes.Hobby
      case goal.toString => GraduationTypes.Goal
      case o => throw new IllegalArgumentException(s"Invalid graduation type: $o")
    }

    override def withType(`type`: GraduationType): String = `type` match {
      case GraduationTypes.Abandoned => abandoned
      case GraduationTypes.Thread => thread
      case GraduationTypes.Weave => weave
      case GraduationTypes.Hobby => hobby
      case GraduationTypes.Goal => goal
      case o => throw new IllegalArgumentException(s"Invalid graduation type: $o")
    }
  }

  object DonutType extends TypeResolver[DonutTypes.DonutType] {
    val projectFocused = "project_focused"
    val skillFocused = "skill_focused"

    override def withName(name: String): DonutType = name match {
      case projectFocused.toString => DonutTypes.ProjectFocused
      case skillFocused.toString => DonutTypes.SkillFocused
      case o => throw new IllegalArgumentException(s"Invalid donut type: $o")
    }

    override def withType(`type`: DonutType): String = `type` match {
      case DonutTypes.ProjectFocused => projectFocused
      case DonutTypes.SkillFocused => skillFocused
      case o => throw new IllegalArgumentException(s"Invalid donut type: $o")
    }
  }

  object WeaveType extends TypeResolver[WeaveTypes.WeaveType] {
    val priority = "priority"
    val pdr = "pdr"
    val bau = "bau"

    override def withName(name: String): WeaveType = name match {
      case priority.toString => WeaveTypes.Priority
      case pdr.toString => WeaveTypes.PDR
      case bau.toString => WeaveTypes.BAU
      case o => throw new IllegalArgumentException(s"Invalid weave type: $o")
    }

    override def withType(`type`: WeaveType): String = `type` match {
      case WeaveTypes.Priority => priority
      case WeaveTypes.PDR => pdr
      case WeaveTypes.BAU => bau
      case o => throw new IllegalArgumentException(s"Invalid weave type: $o")
    }
  }

  object HobbyType extends TypeResolver[HobbyTypes.HobbyType] {
    val active = "active"
    val passive = "passive"

    override def withName(name: String): HobbyType = name match {
      case active.toString => HobbyTypes.Active
      case passive.toString => HobbyTypes.Passive
      case o => throw new IllegalArgumentException(s"Invalid hobby type: $o")
    }

    override def withType(`type`: HobbyType): String = `type` match {
      case HobbyTypes.Active => active
      case HobbyTypes.Passive => passive
      case o => throw new IllegalArgumentException(s"Invalid hobby type: $o")
    }
  }

  object HobbyFrequency extends TypeResolver[HobbyFrequencies.HobbyFrequency] {
    val oneOff = "one_off"
    val continuous = "continuous"

    override def withName(name: String): HobbyFrequency = name match {
      case oneOff.toString => HobbyFrequencies.OneOff
      case continuous.toString => HobbyFrequencies.Continuous
      case o => throw new IllegalArgumentException(s"Invalid hobby frequency: $o")
    }

    override def withType(`type`: HobbyFrequency): String = `type` match {
      case HobbyFrequencies.OneOff => oneOff
      case HobbyFrequencies.Continuous => continuous
      case o => throw new IllegalArgumentException(s"Invalid hobby frequency: $o")
    }
  }
}
