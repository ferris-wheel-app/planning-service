package com.ferris.planning.rest

import com.ferris.planning.model.Model._

object TypeFields {

  sealed trait TypeResolver[T] {
    def withName(name: String): T
  }

  object BacklogItemType extends TypeResolver[BacklogItemTypes.BacklogItemType] {
    override def withName(name: String) = name match {
      case "idea" => BacklogItemTypes.Idea
      case "issue" => BacklogItemTypes.Issue
      case o => throw new IllegalArgumentException(s"Invalid backlog-item type: $o")
    }
  }
}
