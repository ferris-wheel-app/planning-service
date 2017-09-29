package com.planning.db

import com.planning.model.Model.Message

trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._

  class Messages(tag: Tag)
    extends Table[Message](tag, "message") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def sender = column[String]("sender")
    def content = column[String]("content")

    def * = (sender, content).mapTo[Message]
  }

  protected val messages = TableQuery[Messages]
}
