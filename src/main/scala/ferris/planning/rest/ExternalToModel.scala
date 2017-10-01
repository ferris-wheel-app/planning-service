package ferris.planning.rest

import ferris.planning.model.Model.Message
import ferris.planning.rest.Resources.Out.ViewMessage

object ExternalToModel {

  implicit class MessageConversion(message: Message) {
    def toExternal: ViewMessage = {
      ViewMessage(
        sender = message.sender,
        content = message.content
      )
    }
  }
}
