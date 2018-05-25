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
  lazy val schema: profile.SchemaDescription = Array(BacklogItemTable.schema, CurrentActivityTable.schema, EpochTable.schema, GoalBacklogItemTable.schema, GoalTable.schema, HobbyTable.schema, LaserDonutTable.schema, MessageTable.schema, PortionTable.schema, ScheduledLaserDonutTable.schema, ThemeTable.schema, ThreadTable.schema, TodoTable.schema, WeaveTable.schema, YearTable.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table BacklogItemTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP) */
  case class BacklogItemRow(id: Long, uuid: String, summary: String, description: String, `type`: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching BacklogItemRow objects using plain SQL queries */
  implicit def GetResultBacklogItemRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Timestamp]]): GR[BacklogItemRow] = GR{
    prs => import prs._
    BacklogItemRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table BACKLOG_ITEM. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class BacklogItemTable(_tableTag: Tag) extends profile.api.Table[BacklogItemRow](_tableTag, "BACKLOG_ITEM") {
    def * = (id, uuid, summary, description, `type`, createdOn, lastModified) <> (BacklogItemRow.tupled, BacklogItemRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(summary), Rep.Some(description), Rep.Some(`type`), Rep.Some(createdOn), lastModified).shaped.<>({r=>import r._; _1.map(_=> BacklogItemRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_D) */
    val index1 = index("CONSTRAINT_INDEX_D", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table BacklogItemTable */
  lazy val BacklogItemTable = new TableQuery(tag => new BacklogItemTable(tag))

  /** Entity class storing rows of table CurrentActivityTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param currentLaserDonut Database column CURRENT_LASER_DONUT SqlType(BIGINT)
   *  @param currentPortion Database column CURRENT_PORTION SqlType(BIGINT)
   *  @param lastDailyUpdate Database column LAST_DAILY_UPDATE SqlType(TIMESTAMP)
   *  @param lastWeeklyUpdate Database column LAST_WEEKLY_UPDATE SqlType(TIMESTAMP) */
  case class CurrentActivityRow(id: Long, currentLaserDonut: Long, currentPortion: Long, lastDailyUpdate: java.sql.Timestamp, lastWeeklyUpdate: java.sql.Timestamp)
  /** GetResult implicit for fetching CurrentActivityRow objects using plain SQL queries */
  implicit def GetResultCurrentActivityRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp]): GR[CurrentActivityRow] = GR{
    prs => import prs._
    CurrentActivityRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table CURRENT_ACTIVITY. Objects of this class serve as prototypes for rows in queries. */
  class CurrentActivityTable(_tableTag: Tag) extends profile.api.Table[CurrentActivityRow](_tableTag, "CURRENT_ACTIVITY") {
    def * = (id, currentLaserDonut, currentPortion, lastDailyUpdate, lastWeeklyUpdate) <> (CurrentActivityRow.tupled, CurrentActivityRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(currentLaserDonut), Rep.Some(currentPortion), Rep.Some(lastDailyUpdate), Rep.Some(lastWeeklyUpdate)).shaped.<>({r=>import r._; _1.map(_=> CurrentActivityRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column CURRENT_LASER_DONUT SqlType(BIGINT) */
    val currentLaserDonut: Rep[Long] = column[Long]("CURRENT_LASER_DONUT")
    /** Database column CURRENT_PORTION SqlType(BIGINT) */
    val currentPortion: Rep[Long] = column[Long]("CURRENT_PORTION")
    /** Database column LAST_DAILY_UPDATE SqlType(TIMESTAMP) */
    val lastDailyUpdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("LAST_DAILY_UPDATE")
    /** Database column LAST_WEEKLY_UPDATE SqlType(TIMESTAMP) */
    val lastWeeklyUpdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("LAST_WEEKLY_UPDATE")

    /** Foreign key referencing LaserDonutTable (database name CURRENT_LASER_DONUT_FK) */
    lazy val laserDonutTableFk = foreignKey("CURRENT_LASER_DONUT_FK", currentLaserDonut, LaserDonutTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing PortionTable (database name CURRENT_PORTION_FK) */
    lazy val portionTableFk = foreignKey("CURRENT_PORTION_FK", currentPortion, PortionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (currentLaserDonut,currentPortion) (database name CONSTRAINT_INDEX_80) */
    val index1 = index("CONSTRAINT_INDEX_80", (currentLaserDonut, currentPortion), unique=true)
  }
  /** Collection-like TableQuery object for table CurrentActivityTable */
  lazy val CurrentActivityTable = new TableQuery(tag => new CurrentActivityTable(tag))

  /** Entity class storing rows of table EpochTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param name Database column NAME SqlType(VARCHAR), Length(256,true)
   *  @param totem Database column TOTEM SqlType(VARCHAR), Length(256,true)
   *  @param question Database column QUESTION SqlType(VARCHAR), Length(256,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP) */
  case class EpochRow(id: Long, uuid: String, name: String, totem: String, question: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching EpochRow objects using plain SQL queries */
  implicit def GetResultEpochRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Timestamp]]): GR[EpochRow] = GR{
    prs => import prs._
    EpochRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table EPOCH. Objects of this class serve as prototypes for rows in queries. */
  class EpochTable(_tableTag: Tag) extends profile.api.Table[EpochRow](_tableTag, "EPOCH") {
    def * = (id, uuid, name, totem, question, createdOn, lastModified) <> (EpochRow.tupled, EpochRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(name), Rep.Some(totem), Rep.Some(question), Rep.Some(createdOn), lastModified).shaped.<>({r=>import r._; _1.map(_=> EpochRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")

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
   *  @param graduation Database column GRADUATION SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP) */
  case class GoalRow(id: Long, uuid: String, themeId: String, summary: String, description: String, level: Int, priority: Byte, status: String, graduation: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching GoalRow objects using plain SQL queries */
  implicit def GetResultGoalRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[Byte], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[GoalRow] = GR{
    prs => import prs._
    GoalRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[Byte], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table GOAL. Objects of this class serve as prototypes for rows in queries. */
  class GoalTable(_tableTag: Tag) extends profile.api.Table[GoalRow](_tableTag, "GOAL") {
    def * = (id, uuid, themeId, summary, description, level, priority, status, graduation, createdOn, lastModified) <> (GoalRow.tupled, GoalRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(themeId), Rep.Some(summary), Rep.Some(description), Rep.Some(level), Rep.Some(priority), Rep.Some(status), Rep.Some(graduation), Rep.Some(createdOn), lastModified).shaped.<>({r=>import r._; _1.map(_=> GoalRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")

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
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class HobbyRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, frequency: String, status: String, `type`: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching HobbyRow objects using plain SQL queries */
  implicit def GetResultHobbyRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[HobbyRow] = GR{
    prs => import prs._
    HobbyRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table HOBBY. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class HobbyTable(_tableTag: Tag) extends profile.api.Table[HobbyRow](_tableTag, "HOBBY") {
    def * = (id, uuid, goalId, summary, description, frequency, status, `type`, createdOn, lastModified, lastPerformed) <> (HobbyRow.tupled, HobbyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(frequency), Rep.Some(status), Rep.Some(`type`), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> HobbyRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_PERFORMED SqlType(TIMESTAMP) */
    val lastPerformed: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_PERFORMED")

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
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class LaserDonutRow(id: Long, uuid: String, goalId: String, summary: String, description: String, milestone: String, order: Int, status: String, `type`: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching LaserDonutRow objects using plain SQL queries */
  implicit def GetResultLaserDonutRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[LaserDonutRow] = GR{
    prs => import prs._
    LaserDonutRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table LASER_DONUT. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class LaserDonutTable(_tableTag: Tag) extends profile.api.Table[LaserDonutRow](_tableTag, "LASER_DONUT") {
    def * = (id, uuid, goalId, summary, description, milestone, order, status, `type`, createdOn, lastModified, lastPerformed) <> (LaserDonutRow.tupled, LaserDonutRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(goalId), Rep.Some(summary), Rep.Some(description), Rep.Some(milestone), Rep.Some(order), Rep.Some(status), Rep.Some(`type`), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> LaserDonutRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_PERFORMED SqlType(TIMESTAMP) */
    val lastPerformed: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_PERFORMED")

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
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class PortionRow(id: Long, uuid: String, laserDonutId: String, summary: String, order: Int, status: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching PortionRow objects using plain SQL queries */
  implicit def GetResultPortionRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[PortionRow] = GR{
    prs => import prs._
    PortionRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[Int], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table PORTION. Objects of this class serve as prototypes for rows in queries. */
  class PortionTable(_tableTag: Tag) extends profile.api.Table[PortionRow](_tableTag, "PORTION") {
    def * = (id, uuid, laserDonutId, summary, order, status, createdOn, lastModified, lastPerformed) <> (PortionRow.tupled, PortionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(laserDonutId), Rep.Some(summary), Rep.Some(order), Rep.Some(status), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> PortionRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_PERFORMED SqlType(TIMESTAMP) */
    val lastPerformed: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_PERFORMED")

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_1) */
    val index1 = index("CONSTRAINT_INDEX_1", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table PortionTable */
  lazy val PortionTable = new TableQuery(tag => new PortionTable(tag))

  /** Entity class storing rows of table ScheduledLaserDonutTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param laserDonutId Database column LASER_DONUT_ID SqlType(BIGINT)
   *  @param tier Database column TIER SqlType(INTEGER)
   *  @param current Database column CURRENT SqlType(TINYINT) */
  case class ScheduledLaserDonutRow(id: Long, laserDonutId: Long, tier: Int, current: Byte)
  /** GetResult implicit for fetching ScheduledLaserDonutRow objects using plain SQL queries */
  implicit def GetResultScheduledLaserDonutRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Byte]): GR[ScheduledLaserDonutRow] = GR{
    prs => import prs._
    ScheduledLaserDonutRow.tupled((<<[Long], <<[Long], <<[Int], <<[Byte]))
  }
  /** Table description of table SCHEDULED_LASER_DONUT. Objects of this class serve as prototypes for rows in queries. */
  class ScheduledLaserDonutTable(_tableTag: Tag) extends profile.api.Table[ScheduledLaserDonutRow](_tableTag, "SCHEDULED_LASER_DONUT") {
    def * = (id, laserDonutId, tier, current) <> (ScheduledLaserDonutRow.tupled, ScheduledLaserDonutRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(laserDonutId), Rep.Some(tier), Rep.Some(current)).shaped.<>({r=>import r._; _1.map(_=> ScheduledLaserDonutRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column LASER_DONUT_ID SqlType(BIGINT) */
    val laserDonutId: Rep[Long] = column[Long]("LASER_DONUT_ID")
    /** Database column TIER SqlType(INTEGER) */
    val tier: Rep[Int] = column[Int]("TIER")
    /** Database column CURRENT SqlType(TINYINT) */
    val current: Rep[Byte] = column[Byte]("CURRENT")

    /** Foreign key referencing LaserDonutTable (database name LASER_DONUT_FK) */
    lazy val laserDonutTableFk = foreignKey("LASER_DONUT_FK", laserDonutId, LaserDonutTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (laserDonutId) (database name CONSTRAINT_INDEX_8) */
    val index1 = index("CONSTRAINT_INDEX_8", laserDonutId, unique=true)
  }
  /** Collection-like TableQuery object for table ScheduledLaserDonutTable */
  lazy val ScheduledLaserDonutTable = new TableQuery(tag => new ScheduledLaserDonutTable(tag))

  /** Entity class storing rows of table ThemeTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param yearId Database column YEAR_ID SqlType(VARCHAR), Length(36,true)
   *  @param name Database column NAME SqlType(VARCHAR), Length(256,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP) */
  case class ThemeRow(id: Long, uuid: String, yearId: String, name: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ThemeRow objects using plain SQL queries */
  implicit def GetResultThemeRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Timestamp]]): GR[ThemeRow] = GR{
    prs => import prs._
    ThemeRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table THEME. Objects of this class serve as prototypes for rows in queries. */
  class ThemeTable(_tableTag: Tag) extends profile.api.Table[ThemeRow](_tableTag, "THEME") {
    def * = (id, uuid, yearId, name, createdOn, lastModified) <> (ThemeRow.tupled, ThemeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(yearId), Rep.Some(name), Rep.Some(createdOn), lastModified).shaped.<>({r=>import r._; _1.map(_=> ThemeRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column YEAR_ID SqlType(VARCHAR), Length(36,true) */
    val yearId: Rep[String] = column[String]("YEAR_ID", O.Length(36,varying=true))
    /** Database column NAME SqlType(VARCHAR), Length(256,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(256,varying=true))
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")

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
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class ThreadRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, status: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ThreadRow objects using plain SQL queries */
  implicit def GetResultThreadRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[ThreadRow] = GR{
    prs => import prs._
    ThreadRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table THREAD. Objects of this class serve as prototypes for rows in queries. */
  class ThreadTable(_tableTag: Tag) extends profile.api.Table[ThreadRow](_tableTag, "THREAD") {
    def * = (id, uuid, goalId, summary, description, status, createdOn, lastModified, lastPerformed) <> (ThreadRow.tupled, ThreadRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(status), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> ThreadRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_PERFORMED SqlType(TIMESTAMP) */
    val lastPerformed: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_PERFORMED")

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
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class TodoRow(id: Long, uuid: String, portionId: String, description: String, order: Int, status: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching TodoRow objects using plain SQL queries */
  implicit def GetResultTodoRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[TodoRow] = GR{
    prs => import prs._
    TodoRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[Int], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table TODO. Objects of this class serve as prototypes for rows in queries. */
  class TodoTable(_tableTag: Tag) extends profile.api.Table[TodoRow](_tableTag, "TODO") {
    def * = (id, uuid, portionId, description, order, status, createdOn, lastModified, lastPerformed) <> (TodoRow.tupled, TodoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(portionId), Rep.Some(description), Rep.Some(order), Rep.Some(status), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> TodoRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_PERFORMED SqlType(TIMESTAMP) */
    val lastPerformed: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_PERFORMED")

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
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class WeaveRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, status: String, `type`: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching WeaveRow objects using plain SQL queries */
  implicit def GetResultWeaveRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[WeaveRow] = GR{
    prs => import prs._
    WeaveRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table WEAVE. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class WeaveTable(_tableTag: Tag) extends profile.api.Table[WeaveRow](_tableTag, "WEAVE") {
    def * = (id, uuid, goalId, summary, description, status, `type`, createdOn, lastModified, lastPerformed) <> (WeaveRow.tupled, WeaveRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(status), Rep.Some(`type`), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> WeaveRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_PERFORMED SqlType(TIMESTAMP) */
    val lastPerformed: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_PERFORMED")

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_4E) */
    val index1 = index("CONSTRAINT_INDEX_4E", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table WeaveTable */
  lazy val WeaveTable = new TableQuery(tag => new WeaveTable(tag))

  /** Entity class storing rows of table YearTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param epochId Database column EPOCH_ID SqlType(VARCHAR), Length(36,true)
   *  @param startDate Database column START_DATE SqlType(DATE)
   *  @param finishDate Database column FINISH_DATE SqlType(DATE)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP) */
  case class YearRow(id: Long, uuid: String, epochId: String, startDate: java.sql.Date, finishDate: java.sql.Date, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching YearRow objects using plain SQL queries */
  implicit def GetResultYearRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Date], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[YearRow] = GR{
    prs => import prs._
    YearRow.tupled((<<[Long], <<[String], <<[String], <<[java.sql.Date], <<[java.sql.Date], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table YEAR. Objects of this class serve as prototypes for rows in queries. */
  class YearTable(_tableTag: Tag) extends profile.api.Table[YearRow](_tableTag, "YEAR") {
    def * = (id, uuid, epochId, startDate, finishDate, createdOn, lastModified) <> (YearRow.tupled, YearRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(epochId), Rep.Some(startDate), Rep.Some(finishDate), Rep.Some(createdOn), lastModified).shaped.<>({r=>import r._; _1.map(_=> YearRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column EPOCH_ID SqlType(VARCHAR), Length(36,true) */
    val epochId: Rep[String] = column[String]("EPOCH_ID", O.Length(36,varying=true))
    /** Database column START_DATE SqlType(DATE) */
    val startDate: Rep[java.sql.Date] = column[java.sql.Date]("START_DATE")
    /** Database column FINISH_DATE SqlType(DATE) */
    val finishDate: Rep[java.sql.Date] = column[java.sql.Date]("FINISH_DATE")
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_2) */
    val index1 = index("CONSTRAINT_INDEX_2", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table YearTable */
  lazy val YearTable = new TableQuery(tag => new YearTable(tag))
}
