package ferris.planning.rest

object Resources {

  object In {
    case class MessageCreation(sender: String, content: String)
    case class MessageUpdate(sender: String, content: String)
  }

  object Out {
    case class MessageView(sender: String, content: String)
  }
}
