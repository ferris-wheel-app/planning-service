package com.ferris.planning.rest.conversions

import com.ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import com.ferris.planning.rest.Resources.In.{MessageCreation, MessageUpdate}

object ExternalToCommand {

  implicit class MessageCreationConversion(message: MessageCreation) {
    def toCommand: CreateMessage = {
      CreateMessage(
        sender = message.sender,
        content = message.content
      )
    }
  }

  implicit class MessageUpdateConversion(message: MessageUpdate) {
    def toCommand: UpdateMessage = {
      UpdateMessage(
        sender = message.sender,
        content = message.content
      )
    }
  }
}
