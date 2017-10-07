package ferris.planning.db.conversions

import java.util.UUID

import ferris.planning.db.Tables
import ferris.planning.model.Model.Message

class TableConversions(val tables: Tables) {

  implicit class ProfileCreationBuilder(val rows: (tables.MessageRow)) {
    def asMessage: Message = rows match { case row =>
      Message(
        uuid = UUID.fromString(row.uuid),
        sender = row.sender,
        content = row.content
      )
    }
  }
}
