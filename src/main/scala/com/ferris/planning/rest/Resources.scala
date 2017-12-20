package com.ferris.planning.rest

object Resources {

  object In {
    case class MessageCreation(sender: String, content: String)
    case class BacklogItemCreation(summary: String, description: String, backlogItemType: String)

    case class MessageUpdate(sender: Option[String], content: Option[String])
    case class BacklogItemUpdate(summary: String, description: String, backlogItemType: String)
  }

  object Out {
    case class MessageView(sender: String, content: String)
  }
}
