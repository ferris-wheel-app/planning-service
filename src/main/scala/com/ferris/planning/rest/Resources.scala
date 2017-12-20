package com.ferris.planning.rest

object Resources {

  object In {
    case class MessageCreation(sender: String, content: String)
    case class MessageUpdate(sender: Option[String], content: Option[String])
  }

  object Out {
    case class MessageView(sender: String, content: String)
  }
}
