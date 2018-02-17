package com.ferris.planning.sample

import java.util.UUID

import com.ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import com.ferris.planning.model.Model.Message
import com.ferris.planning.rest.Resources.In.{MessageCreation, MessageUpdate}
import com.ferris.planning.rest.Resources.Out.MessageView

object SampleData {

  object domain {
    val messageCreation = CreateMessage(
      sender = "Dave",
      content = "Open the pod bay doors, HAL."
    )

    val messageUpdate = UpdateMessage(
      sender = Some("Dave"),
      content = Some("Open the pod bay doors, HAL. Please?")
    )

    val message = Message(
      uuid = UUID.randomUUID(),
      sender = "Dave",
      content = "Open the pod bay doors, HAL."
    )
  }

  object rest {
    val messageCreation = MessageCreation(
      sender = domain.messageCreation.sender,
      content = domain.messageCreation.content
    )

    val messageUpdate = MessageUpdate(
      sender = domain.messageUpdate.sender,
      content = domain.messageUpdate.content
    )

    val message = MessageView(
      uuid = domain.message.uuid,
      sender = domain.message.sender,
      content = domain.message.content
    )
  }
}
