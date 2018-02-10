package com.ferris.planning.sample

import java.util.UUID

import com.ferris.planning.command.Commands.{CreateMessage, UpdateMessage}
import com.ferris.planning.model.Model.Message

object SampleData {

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

  val message2 = Message(
    uuid = UUID.randomUUID(),
    sender = "HAL",
    content = "I'm sorry, Dave. I'm afraid I can't do that."
  )
}
