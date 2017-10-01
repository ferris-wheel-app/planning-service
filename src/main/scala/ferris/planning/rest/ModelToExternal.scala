package ferris.planning.rest

import java.util.UUID

import ferris.planning.model.Model.Message
import ferris.planning.rest.Resources.In.CreateMessage
import ferris.planning.rest.Resources.Out.ViewMessage

object ModelToExternal {

  implicit class MessageConversion(message: CreateMessage) {
    def toModel: Message = {
      Message(
        uuid = UUID.randomUUID(),
        sender = message.sender,
        content = message.content
      )
    }
  }
}
