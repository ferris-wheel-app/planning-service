package ferris.planning.rest

object Resources {

  object In {
    case class CreateMessage(sender: String, content: String)
    case class UpdateMessage(sender: String, content: String)
  }

  object Out {
    case class ViewMessage(sender: String, content: String)
  }
}
