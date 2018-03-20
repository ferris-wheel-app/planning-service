package com.ferris.planning.table
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(BacklogItemTable.schema, EpochTable.schema, GoalBacklogItemTable.schema, GoalTable.schema, HobbyTable.schema, LaserDonutTable.schema, MessageTable.schema, PortionTable.schema, ThemeTable.schema, ThreadTable.schema, TodoTable.schema, WeaveTable.schema, YearTable.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table BacklogItemTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true) */
  case class BacklogItemRow(id: Long, uuid: String, summary: String, description: String, `type`: String)
  /** GetResult implicit for fetching BacklogItemRow objects using plain SQL queries */
  implicit def GetResultBacklogItemRow(implicit e0: GR[Long], e1: GR[String]): GR[BacklogItemRow] = GR{
    prs => import prs._
    BacklogItemRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table BACKLOG_ITEM. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class BacklogItemTable(_tableTag: Tag) extends profile.api.Table[BacklogItemRow](_tableTag, "BACKLOG_ITEM") {
    def * = (id, uuid, summary, description, `type`) <> (BacklogItemRow.tupled, BacklogItemRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(summary), Rep.Some(description), Rep.Some(`type`)).shaped.<>({r=>import r._; _1.map(_=> BacklogItemRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column SUMMARY SqlType(VARCHAR), Length(256,true) */
    val summary: Rep[String] = column[String]("SUMMARY", O.Length(256,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column TYPE SqlType(VARCHAR), Length(36,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("TYPE", O.Length(36,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_D) */
    val index1 = index("CONSTRAINT_INDEX_D", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table BacklogItemTable */
  lazy val BacklogItemTable = new TableQuery(tag => new BacklogItemTable(tag))

  /** Entity class storing rows of table EpochTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param name Database column NAME SqlType(VARCHAR), Length(256,true)
   *  @param totem Database column TOTEM SqlType(VARCHAR), Length(256,true)
   *  @param question Database column QUESTION SqlType(VARCHAR), Length(256,true) */
  case class EpochRow(id: Long, uuid: String, name: String, totem: String, question: String)
  /** GetResult implicit for fetching EpochRow objects using plain SQL queries */
  implicit def GetResultEpochRow(implicit e0: GR[Long], e1: GR[String]): GR[EpochRow] = GR{
    prs => import prs._
    EpochRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table EPOCH. Objects of this class serve as prototypes for rows in queries. */
  class EpochTable(_tableTag: Tag) extends profile.api.Table[EpochRow](_tableTag, "EPOCH") {
    def * = (id, uuid, name, totem, question) <> (EpochRow.tupled, EpochRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(name), Rep.Some(totem), Rep.Some(question)).shaped.<>({r=>import r._; _1.map(_=> EpochRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column NAME SqlType(VARCHAR), Length(256,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(256,varying=true))
    /** Database column TOTEM SqlType(VARCHAR), Length(256,true) */
    val totem: Rep[String] = column[String]("TOTEM", O.Length(256,varying=true))
    /** Database column QUESTION SqlType(VARCHAR), Length(256,true) */
    val question: Rep[String] = column[String]("QUESTION", O.Length(256,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_3) */
    val index1 = index("CONSTRAINT_INDEX_3", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table EpochTable */
  lazy val EpochTable = new TableQuery(tag => new EpochTable(tag))

  /** Entity class storing rows of table GoalBacklogItemTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param goalId Database column GOAL_ID SqlType(BIGINT)
   *  @param backlogItemId Database column BACKLOG_ITEM_ID SqlType(BIGINT) */
  case class GoalBacklogItemRow(id: Long, goalId: Long, backlogItemId: Long)
  /** GetResult implicit for fetching GoalBacklogItemRow objects using plain SQL queries */
  implicit def GetResultGoalBacklogItemRow(implicit e0: GR[Long]): GR[GoalBacklogItemRow] = GR{
    prs => import prs._
    GoalBacklogItemRow.tupled((<<[Long], <<[Long], <<[Long]))
  }
  /** Table description of table GOAL_BACKLOG_ITEM. Objects of this class serve as prototypes for rows in queries. */
  class GoalBacklogItemTable(_tableTag: Tag) extends profile.api.Table[GoalBacklogItemRow](_tableTag, "GOAL_BACKLOG_ITEM") {
    def * = (id, goalId, backlogItemId) <> (GoalBacklogItemRow.tupled, GoalBacklogItemRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(goalId), Rep.Some(backlogItemId)).shaped.<>({r=>import r._; _1.map(_=> GoalBacklogItemRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column GOAL_ID SqlType(BIGINT) */
    val goalId: Rep[Long] = column[Long]("GOAL_ID")
    /** Database column BACKLOG_ITEM_ID SqlType(BIGINT) */
    val backlogItemId: Rep[Long] = column[Long]("BACKLOG_ITEM_ID")

    /** Foreign key referencing BacklogItemTable (database name BACKLOG_ITEM_FK) */
    lazy val backlogItemTableFk = foreignKey("BACKLOG_ITEM_FK", backlogItemId, BacklogItemTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing GoalTable (database name GOAL_FK) */
    lazy val goalTableFk = foreignKey("GOAL_FK", goalId, GoalTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table GoalBacklogItemTable */
  lazy val GoalBacklogItemTable = new TableQuery(tag => new GoalBacklogItemTable(tag))

  /** Entity class storing rows of table GoalTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param themeId Database column THEME_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param level Database column LEVEL SqlType(INTEGER)
   *  @param priority Database column PRIORITY SqlType(TINYINT)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param graduation Database column GRADUATION SqlType(VARCHAR), Length(36,true) */
  case class GoalRow(id: Long, uuid: String, themeId: String, summary: String, description: String, level: Int, priority: Byte, status: String, graduation: String)
  /** GetResult implicit for fetching GoalRow objects using plain SQL queries */
  implicit def GetResultGoalRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[Byte]): GR[GoalRow] = GR{
    prs => import prs._
    GoalRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[Byte], <<[String], <<[String]))
  }
  /** Table description of table GOAL. Objects of this class serve as prototypes for rows in queries. */
  class GoalTable(_tableTag: Tag) extends profile.api.Table[GoalRow](_tableTag, "GOAL") {
    def * = (id, uuid, themeId, summary, description, level, priority, status, graduation) <> (GoalRow.tupled, GoalRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(themeId), Rep.Some(summary), Rep.Some(description), Rep.Some(level), Rep.Some(priority), Rep.Some(status), Rep.Some(graduation)).shaped.<>({r=>import r._; _1.map(_=> GoalRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column THEME_ID SqlType(VARCHAR), Length(36,true) */
    val themeId: Rep[String] = column[String]("THEME_ID", O.Length(36,varying=true))
    /** Database column SUMMARY SqlType(VARCHAR), Length(256,true) */
    val summary: Rep[String] = column[String]("SUMMARY", O.Length(256,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column LEVEL SqlType(INTEGER) */
    val level: Rep[Int] = column[Int]("LEVEL")
    /** Database column PRIORITY SqlType(TINYINT) */
    val priority: Rep[Byte] = column[Byte]("PRIORITY")
    /** Database column STATUS SqlType(VARCHAR), Length(36,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(36,varying=true))
    /** Database column GRADUATION SqlType(VARCHAR), Length(36,true) */
    val graduation: Rep[String] = column[String]("GRADUATION", O.Length(36,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_21) */
    val index1 = index("CONSTRAINT_INDEX_21", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table GoalTable */
  lazy val GoalTable = new TableQuery(tag => new GoalTable(tag))

  /** Entity class storing rows of table HobbyTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param frequency Database column FREQUENCY SqlType(VARCHAR), Length(36,true)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true) */
  case class HobbyRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, frequency: String, status: String, `type`: String)
  /** GetResult implicit for fetching HobbyRow objects using plain SQL queries */
  implicit def GetResultHobbyRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[HobbyRow] = GR{
    prs => import prs._
    HobbyRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table HOBBY. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class HobbyTable(_tableTag: Tag) extends profile.api.Table[HobbyRow](_tableTag, "HOBBY") {
    def * = (id, uuid, goalId, summary, description, frequency, status, `type`) <> (HobbyRow.tupled, HobbyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(frequency), Rep.Some(status), Rep.Some(`type`)).shaped.<>({r=>import r._; _1.map(_=> HobbyRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column GOAL_ID SqlType(VARCHAR), Length(36,true) */
    val goalId: Rep[Option[String]] = column[Option[String]]("GOAL_ID", O.Length(36,varying=true))
    /** Database column SUMMARY SqlType(VARCHAR), Length(256,true) */
    val summary: Rep[String] = column[String]("SUMMARY", O.Length(256,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column FREQUENCY SqlType(VARCHAR), Length(36,true) */
    val frequency: Rep[String] = column[String]("FREQUENCY", O.Length(36,varying=true))
    /** Database column STATUS SqlType(VARCHAR), Length(36,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(36,varying=true))
    /** Database column TYPE SqlType(VARCHAR), Length(36,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("TYPE", O.Length(36,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_41) */
    val index1 = index("CONSTRAINT_INDEX_41", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table HobbyTable */
  lazy val HobbyTable = new TableQuery(tag => new HobbyTable(tag))

  /** Entity class storing rows of table LaserDonutTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param milestone Database column MILESTONE SqlType(VARCHAR), Length(256,true)
   *  @param order Database column ORDER SqlType(INTEGER)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true) */
  case class LaserDonutRow(id: Long, uuid: String, goalId: String, summary: String, description: String, milestone: String, order: Int, status: String, `type`: String)
  /** GetResult implicit for fetching LaserDonutRow objects using plain SQL queries */
  implicit def GetResultLaserDonutRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int]): GR[LaserDonutRow] = GR{
    prs => import prs._
    LaserDonutRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[String], <<[String]))
  }
  /** Table description of table LASER_DONUT. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class LaserDonutTable(_tableTag: Tag) extends profile.api.Table[LaserDonutRow](_tableTag, "LASER_DONUT") {
    def * = (id, uuid, goalId, summary, description, milestone, order, status, `type`) <> (LaserDonutRow.tupled, LaserDonutRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(goalId), Rep.Some(summary), Rep.Some(description), Rep.Some(milestone), Rep.Some(order), Rep.Some(status), Rep.Some(`type`)).shaped.<>({r=>import r._; _1.map(_=> LaserDonutRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column GOAL_ID SqlType(VARCHAR), Length(36,true) */
    val goalId: Rep[String] = column[String]("GOAL_ID", O.Length(36,varying=true))
    /** Database column SUMMARY SqlType(VARCHAR), Length(256,true) */
    val summary: Rep[String] = column[String]("SUMMARY", O.Length(256,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column MILESTONE SqlType(VARCHAR), Length(256,true) */
    val milestone: Rep[String] = column[String]("MILESTONE", O.Length(256,varying=true))
    /** Database column ORDER SqlType(INTEGER) */
    val order: Rep[Int] = column[Int]("ORDER")
    /** Database column STATUS SqlType(VARCHAR), Length(36,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(36,varying=true))
    /** Database column TYPE SqlType(VARCHAR), Length(36,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("TYPE", O.Length(36,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_E) */
    val index1 = index("CONSTRAINT_INDEX_E", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table LaserDonutTable */
  lazy val LaserDonutTable = new TableQuery(tag => new LaserDonutTable(tag))

  /** Entity class storing rows of table MessageTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param sender Database column SENDER SqlType(VARCHAR), Length(256,true)
   *  @param content Database column CONTENT SqlType(VARCHAR), Length(2000,true) */
  case class MessageRow(id: Long, uuid: String, sender: String, content: String)
  /** GetResult implicit for fetching MessageRow objects using plain SQL queries */
  implicit def GetResultMessageRow(implicit e0: GR[Long], e1: GR[String]): GR[MessageRow] = GR{
    prs => import prs._
    MessageRow.tupled((<<[Long], <<[String], <<[String], <<[String]))
  }
  /** Table description of table MESSAGE. Objects of this class serve as prototypes for rows in queries. */
  class MessageTable(_tableTag: Tag) extends profile.api.Table[MessageRow](_tableTag, "MESSAGE") {
    def * = (id, uuid, sender, content) <> (MessageRow.tupled, MessageRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(sender), Rep.Some(content)).shaped.<>({r=>import r._; _1.map(_=> MessageRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column SENDER SqlType(VARCHAR), Length(256,true) */
    val sender: Rep[String] = column[String]("SENDER", O.Length(256,varying=true))
    /** Database column CONTENT SqlType(VARCHAR), Length(2000,true) */
    val content: Rep[String] = column[String]("CONTENT", O.Length(2000,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_6) */
    val index1 = index("CONSTRAINT_INDEX_6", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table MessageTable */
  lazy val MessageTable = new TableQuery(tag => new MessageTable(tag))

  /** Entity class storing rows of table PortionTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param laserDonutId Database column LASER_DONUT_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param order Database column ORDER SqlType(INTEGER)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true) */
  case class PortionRow(id: Long, uuid: String, laserDonutId: String, summary: String, order: Int, status: String)
  /** GetResult implicit for fetching PortionRow objects using plain SQL queries */
  implicit def GetResultPortionRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int]): GR[PortionRow] = GR{
    prs => import prs._
    PortionRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[Int], <<[String]))
  }
  /** Table description of table PORTION. Objects of this class serve as prototypes for rows in queries. */
  class PortionTable(_tableTag: Tag) extends profile.api.Table[PortionRow](_tableTag, "PORTION") {
    def * = (id, uuid, laserDonutId, summary, order, status) <> (PortionRow.tupled, PortionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(laserDonutId), Rep.Some(summary), Rep.Some(order), Rep.Some(status)).shaped.<>({r=>import r._; _1.map(_=> PortionRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column LASER_DONUT_ID SqlType(VARCHAR), Length(36,true) */
    val laserDonutId: Rep[String] = column[String]("LASER_DONUT_ID", O.Length(36,varying=true))
    /** Database column SUMMARY SqlType(VARCHAR), Length(256,true) */
    val summary: Rep[String] = column[String]("SUMMARY", O.Length(256,varying=true))
    /** Database column ORDER SqlType(INTEGER) */
    val order: Rep[Int] = column[Int]("ORDER")
    /** Database column STATUS SqlType(VARCHAR), Length(36,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(36,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_1) */
    val index1 = index("CONSTRAINT_INDEX_1", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table PortionTable */
  lazy val PortionTable = new TableQuery(tag => new PortionTable(tag))

  /** Entity class storing rows of table ThemeTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param yearId Database column YEAR_ID SqlType(VARCHAR), Length(36,true)
   *  @param name Database column NAME SqlType(VARCHAR), Length(256,true) */
  case class ThemeRow(id: Long, uuid: String, yearId: String, name: String)
  /** GetResult implicit for fetching ThemeRow objects using plain SQL queries */
  implicit def GetResultThemeRow(implicit e0: GR[Long], e1: GR[String]): GR[ThemeRow] = GR{
    prs => import prs._
    ThemeRow.tupled((<<[Long], <<[String], <<[String], <<[String]))
  }
  /** Table description of table THEME. Objects of this class serve as prototypes for rows in queries. */
  class ThemeTable(_tableTag: Tag) extends profile.api.Table[ThemeRow](_tableTag, "THEME") {
    def * = (id, uuid, yearId, name) <> (ThemeRow.tupled, ThemeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(yearId), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> ThemeRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column YEAR_ID SqlType(VARCHAR), Length(36,true) */
    val yearId: Rep[String] = column[String]("YEAR_ID", O.Length(36,varying=true))
    /** Database column NAME SqlType(VARCHAR), Length(256,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(256,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_4) */
    val index1 = index("CONSTRAINT_INDEX_4", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table ThemeTable */
  lazy val ThemeTable = new TableQuery(tag => new ThemeTable(tag))

  /** Entity class storing rows of table ThreadTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true) */
  case class ThreadRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, status: String)
  /** GetResult implicit for fetching ThreadRow objects using plain SQL queries */
  implicit def GetResultThreadRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[ThreadRow] = GR{
    prs => import prs._
    ThreadRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table THREAD. Objects of this class serve as prototypes for rows in queries. */
  class ThreadTable(_tableTag: Tag) extends profile.api.Table[ThreadRow](_tableTag, "THREAD") {
    def * = (id, uuid, goalId, summary, description, status) <> (ThreadRow.tupled, ThreadRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(status)).shaped.<>({r=>import r._; _1.map(_=> ThreadRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column GOAL_ID SqlType(VARCHAR), Length(36,true) */
    val goalId: Rep[Option[String]] = column[Option[String]]("GOAL_ID", O.Length(36,varying=true))
    /** Database column SUMMARY SqlType(VARCHAR), Length(256,true) */
    val summary: Rep[String] = column[String]("SUMMARY", O.Length(256,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column STATUS SqlType(VARCHAR), Length(36,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(36,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_9) */
    val index1 = index("CONSTRAINT_INDEX_9", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table ThreadTable */
  lazy val ThreadTable = new TableQuery(tag => new ThreadTable(tag))

  /** Entity class storing rows of table TodoTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param portionId Database column PORTION_ID SqlType(VARCHAR), Length(36,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param order Database column ORDER SqlType(INTEGER)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true) */
  case class TodoRow(id: Long, uuid: String, portionId: String, description: String, order: Int, status: String)
  /** GetResult implicit for fetching TodoRow objects using plain SQL queries */
  implicit def GetResultTodoRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int]): GR[TodoRow] = GR{
    prs => import prs._
    TodoRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[Int], <<[String]))
  }
  /** Table description of table TODO. Objects of this class serve as prototypes for rows in queries. */
  class TodoTable(_tableTag: Tag) extends profile.api.Table[TodoRow](_tableTag, "TODO") {
    def * = (id, uuid, portionId, description, order, status) <> (TodoRow.tupled, TodoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(portionId), Rep.Some(description), Rep.Some(order), Rep.Some(status)).shaped.<>({r=>import r._; _1.map(_=> TodoRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column PORTION_ID SqlType(VARCHAR), Length(36,true) */
    val portionId: Rep[String] = column[String]("PORTION_ID", O.Length(36,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column ORDER SqlType(INTEGER) */
    val order: Rep[Int] = column[Int]("ORDER")
    /** Database column STATUS SqlType(VARCHAR), Length(36,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(36,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_27) */
    val index1 = index("CONSTRAINT_INDEX_27", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table TodoTable */
  lazy val TodoTable = new TableQuery(tag => new TodoTable(tag))

  /** Entity class storing rows of table WeaveTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true) */
  case class WeaveRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, status: String, `type`: String)
  /** GetResult implicit for fetching WeaveRow objects using plain SQL queries */
  implicit def GetResultWeaveRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[WeaveRow] = GR{
    prs => import prs._
    WeaveRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table WEAVE. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class WeaveTable(_tableTag: Tag) extends profile.api.Table[WeaveRow](_tableTag, "WEAVE") {
    def * = (id, uuid, goalId, summary, description, status, `type`) <> (WeaveRow.tupled, WeaveRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(status), Rep.Some(`type`)).shaped.<>({r=>import r._; _1.map(_=> WeaveRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column GOAL_ID SqlType(VARCHAR), Length(36,true) */
    val goalId: Rep[Option[String]] = column[Option[String]]("GOAL_ID", O.Length(36,varying=true))
    /** Database column SUMMARY SqlType(VARCHAR), Length(256,true) */
    val summary: Rep[String] = column[String]("SUMMARY", O.Length(256,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column STATUS SqlType(VARCHAR), Length(36,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(36,varying=true))
    /** Database column TYPE SqlType(VARCHAR), Length(36,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("TYPE", O.Length(36,varying=true))

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_4E) */
    val index1 = index("CONSTRAINT_INDEX_4E", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table WeaveTable */
  lazy val WeaveTable = new TableQuery(tag => new WeaveTable(tag))

  /** Entity class storing rows of table YearTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param epochId Database column EPOCH_ID SqlType(VARCHAR), Length(36,true)
   *  @param startDate Database column START_DATE SqlType(TIMESTAMP)
   *  @param finishDate Database column FINISH_DATE SqlType(TIMESTAMP) */
  case class YearRow(id: Long, uuid: String, epochId: String, startDate: java.sql.Timestamp, finishDate: java.sql.Timestamp)
  /** GetResult implicit for fetching YearRow objects using plain SQL queries */
  implicit def GetResultYearRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[YearRow] = GR{
    prs => import prs._
    YearRow.tupled((<<[Long], <<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table YEAR. Objects of this class serve as prototypes for rows in queries. */
  class YearTable(_tableTag: Tag) extends profile.api.Table[YearRow](_tableTag, "YEAR") {
    def * = (id, uuid, epochId, startDate, finishDate) <> (YearRow.tupled, YearRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(epochId), Rep.Some(startDate), Rep.Some(finishDate)).shaped.<>({r=>import r._; _1.map(_=> YearRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column EPOCH_ID SqlType(VARCHAR), Length(36,true) */
    val epochId: Rep[String] = column[String]("EPOCH_ID", O.Length(36,varying=true))
    /** Database column START_DATE SqlType(TIMESTAMP) */
    val startDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("START_DATE")
    /** Database column FINISH_DATE SqlType(TIMESTAMP) */
    val finishDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("FINISH_DATE")

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_2) */
    val index1 = index("CONSTRAINT_INDEX_2", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table YearTable */
  lazy val YearTable = new TableQuery(tag => new YearTable(tag))
}
