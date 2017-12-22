package com.ferris.planning.rest.conversions

import com.ferris.planning.command.Commands._
import com.ferris.planning.rest.Resources.In._
import com.ferris.planning.rest.TypeFields

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

  implicit class BacklogItemCreationConversion(backlogItem: BacklogItemCreation) {
    def toCommand: CreateBacklogItem = {
      CreateBacklogItem(
        summary = backlogItem.summary,
        description = backlogItem.description,
        `type` = TypeFields.BacklogItemType.withName(backlogItem.backlogItemType)
      )
    }
  }

  implicit class BacklogItemUpdateConversion(backlogItem: BacklogItemUpdate) {
    def toCommand: UpdateBacklogItem = {
      UpdateBacklogItem(
        summary = backlogItem.summary,
        description = backlogItem.description,
        `type` = TypeFields.BacklogItemType.withName(backlogItem.backlogItemType)
      )
    }
  }
}
