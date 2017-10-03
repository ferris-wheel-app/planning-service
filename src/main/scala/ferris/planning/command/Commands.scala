package ferris.planning.command

object Commands {

  case class CreateMessage(sender: String, content: String)

  case class UpdateMessage(sender: String, content: String)
}
