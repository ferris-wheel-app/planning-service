package com.ferris.planning.command

import com.ferris.planning.model.Model.BacklogItemTypes

object Commands {

  case class CreateMessage(sender: String, content: String)

  case class UpdateMessage(sender: String, content: String)

  case class CreateBacklogItem (
    summary: String,
    description: String,
    `type`: BacklogItemTypes.BacklogItemType
  )
}
