package com.planning.db

trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._

  final case class MessageRow(sender: String, content: String, id: Long = 0L)

  class MessageTable(tag: Tag)
    extends Table[MessageRow](tag, "message") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def sender = column[String]("sender")
    def content = column[String]("content")

    def * = (sender, content, id).mapTo[MessageRow]
  }

  protected val messages = TableQuery[MessageTable]
}
