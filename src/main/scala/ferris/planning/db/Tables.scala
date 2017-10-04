package ferris.planning.db

trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._

  lazy val schema: profile.SchemaDescription = Array(messages.schema).reduceLeft(_ ++ _)

  final case class MessageRow(sender: String, content: String, id: Long = 0L)

  class MessageTable(tag: Tag)
    extends Table[MessageRow](tag, "message") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def sender = column[String]("sender")
    def content = column[String]("content")

    def * = (sender, content, id).mapTo[MessageRow]
  }

  lazy val messages = TableQuery[MessageTable]
}
