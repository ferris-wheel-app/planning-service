package com.ferris.planning.model

import java.util.UUID

object Model {
  case class Message(uuid: UUID, sender: String, content: String)
}
