package com.ferris.planning.sample

import java.util.UUID

import com.ferris.planning.command.Commands._
import com.ferris.planning.model.Model._
import com.ferris.planning.rest.Resources.In._
import com.ferris.planning.rest.Resources.Out._
import com.ferris.planning.rest.TypeFields.BacklogItemType

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

    val backlogItemCreation = CreateBacklogItem(
      summary = "I need to get my shit together",
      description = "I need to get my shit together",
      `type` = BacklogItemTypes.Issue
    )

    val backlogItemUpdate = UpdateBacklogItem(
      summary = Some("I want to be the best of the best at programming"),
      description = Some("I want to be the best of the best at programming"),
      `type` = Some(BacklogItemTypes.Issue)
    )

    val backlogItem = BacklogItem(
      uuid = UUID.randomUUID,
      summary = "I need to get my shit together",
      description = "I need to get my shit together",
      `type` = BacklogItemTypes.Issue
    )

    val epochCreation = CreateEpoch(
      name = "Messinaissance",
      totem = "Hero",
      question = "Am I capable of becoming an Übermensch?"
    )

    val epochUpdate = UpdateEpoch(
      name = Some("Messinaissance"),
      totem = Some("Hero"),
      question = Some("Am I capable of becoming an Übermensch?")
    )

    val epoch = Epoch(
      uuid = UUID.randomUUID,
      name = "Messinaissance",
      totem = "Hero",
      question = "Am I capable of becoming an Übermensch?"
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

    val backlogItemCreation = BacklogItemCreation(
      summary = domain.backlogItemCreation.summary,
      description = "I need to get my shit together",
      `type` = BacklogItemType.issue
    )

    val backlogItemUpdate = BacklogItemUpdate(
      summary = Some("I want to be the best of the best at programming"),
      description = Some("I want to be the best of the best at programming"),
      `type` = Some(BacklogItemType.issue)
    )

    val backlogItem = BacklogItemView(
      uuid = UUID.randomUUID,
      summary = "I need to get my shit together",
      description = "I need to get my shit together",
      `type` = BacklogItemType.issue
    )
  }
}
