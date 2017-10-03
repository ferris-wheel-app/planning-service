package ferris.planning.rest.conversions

import ferris.planning.model.Model.Message
import ferris.planning.rest.Resources.Out.MessageView

object ModelToView {

  implicit class MessageConversion(message: Message) {
    def toView: MessageView = {
      MessageView(
        sender = message.sender,
        content = message.content
      )
    }
  }
}
