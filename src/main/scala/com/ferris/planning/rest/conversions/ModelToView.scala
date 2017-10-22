package com.ferris.planning.rest.conversions

import com.ferris.planning.model.Model.Message
import com.ferris.planning.model.Model.Message
import com.ferris.planning.rest.Resources.Out.MessageView

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
