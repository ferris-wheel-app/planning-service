package ferris.planning.db

trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._

  lazy val schema: profile.SchemaDescription = Array(messages.schema).reduceLeft(_ ++ _)

  final case class MessageRow(id: Long = 0L, uuid: String, sender: String, content: String)

  class MessageTable(tag: Tag)
    extends Table[MessageRow](tag, "message") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def uuid = column[String]("uuid")
    def sender = column[String]("sender")
    def content = column[String]("content")

    def * = (id, uuid, sender, content).mapTo[MessageRow]
  }

  lazy val messages = TableQuery[MessageTable]
}
