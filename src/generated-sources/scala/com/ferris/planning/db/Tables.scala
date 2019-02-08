package com.ferris.planning.db
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
  lazy val schema: profile.SchemaDescription = Array(BacklogItemTable.schema, CurrentActivityTable.schema, EpochMissionTable.schema, EpochTable.schema, GoalBacklogItemTable.schema, GoalMissionTable.schema, GoalRelationshipTable.schema, GoalSkillTable.schema, GoalTable.schema, HobbyMissionTable.schema, HobbyRelationshipTable.schema, HobbySkillTable.schema, HobbyTable.schema, LaserDonutMissionTable.schema, LaserDonutRelationshipTable.schema, LaserDonutSkillTable.schema, LaserDonutTable.schema, MissionTable.schema, OneOffMissionTable.schema, OneOffRelationshipTable.schema, OneOffSkillTable.schema, OneOffTable.schema, PortionMissionTable.schema, PortionRelationshipTable.schema, PortionSkillTable.schema, PortionTable.schema, RelationshipTable.schema, ScheduledLaserDonutTable.schema, ScheduledOneOffMissionTable.schema, ScheduledOneOffRelationshipTable.schema, ScheduledOneOffSkillTable.schema, ScheduledOneOffTable.schema, SkillCategoryTable.schema, SkillTable.schema, ThemeTable.schema, ThreadMissionTable.schema, ThreadRelationshipTable.schema, ThreadSkillTable.schema, ThreadTable.schema, TodoTable.schema, WeaveMissionTable.schema, WeaveRelationshipTable.schema, WeaveSkillTable.schema, WeaveTable.schema, YearTable.schema).reduceLeft(_ ++ _)
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

  /** Entity class storing rows of table EpochMissionTable
   *  @param epochId Database column EPOCH_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class EpochMissionRow(epochId: Long, missionId: Long, level: String)
  /** GetResult implicit for fetching EpochMissionRow objects using plain SQL queries */
  implicit def GetResultEpochMissionRow(implicit e0: GR[Long], e1: GR[String]): GR[EpochMissionRow] = GR{
    prs => import prs._
    EpochMissionRow.tupled((<<[Long], <<[Long], <<[String]))
  }
  /** Table description of table EPOCH_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class EpochMissionTable(_tableTag: Tag) extends profile.api.Table[EpochMissionRow](_tableTag, "EPOCH_MISSION") {
    def * = (epochId, missionId, level) <> (EpochMissionRow.tupled, EpochMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(epochId), Rep.Some(missionId), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> EpochMissionRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column EPOCH_ID SqlType(BIGINT) */
    val epochId: Rep[Long] = column[Long]("EPOCH_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing EpochTable (database name EPOCH_FK) */
    lazy val epochTableFk = foreignKey("EPOCH_FK", epochId, EpochTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing MissionTable (database name MISSION_FK1) */
    lazy val missionTableFk = foreignKey("MISSION_FK1", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (epochId,missionId) (database name CONSTRAINT_INDEX_3CF) */
    val index1 = index("CONSTRAINT_INDEX_3CF", (epochId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table EpochMissionTable */
  lazy val EpochMissionTable = new TableQuery(tag => new EpochMissionTable(tag))

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
    /** Foreign key referencing GoalTable (database name GOAL_FK1) */
    lazy val goalTableFk = foreignKey("GOAL_FK1", goalId, GoalTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table GoalBacklogItemTable */
  lazy val GoalBacklogItemTable = new TableQuery(tag => new GoalBacklogItemTable(tag))

  /** Entity class storing rows of table GoalMissionTable
   *  @param goalId Database column GOAL_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT) */
  case class GoalMissionRow(goalId: Long, missionId: Long)
  /** GetResult implicit for fetching GoalMissionRow objects using plain SQL queries */
  implicit def GetResultGoalMissionRow(implicit e0: GR[Long]): GR[GoalMissionRow] = GR{
    prs => import prs._
    GoalMissionRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table GOAL_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class GoalMissionTable(_tableTag: Tag) extends profile.api.Table[GoalMissionRow](_tableTag, "GOAL_MISSION") {
    def * = (goalId, missionId) <> (GoalMissionRow.tupled, GoalMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(goalId), Rep.Some(missionId)).shaped.<>({r=>import r._; _1.map(_=> GoalMissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column GOAL_ID SqlType(BIGINT) */
    val goalId: Rep[Long] = column[Long]("GOAL_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")

    /** Foreign key referencing GoalTable (database name GOAL_FK4) */
    lazy val goalTableFk = foreignKey("GOAL_FK4", goalId, GoalTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing MissionTable (database name MISSION_FK2) */
    lazy val missionTableFk = foreignKey("MISSION_FK2", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (goalId,missionId) (database name CONSTRAINT_INDEX_16E) */
    val index1 = index("CONSTRAINT_INDEX_16E", (goalId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table GoalMissionTable */
  lazy val GoalMissionTable = new TableQuery(tag => new GoalMissionTable(tag))

  /** Entity class storing rows of table GoalRelationshipTable
   *  @param goalId Database column GOAL_ID SqlType(BIGINT)
   *  @param relationshipId Database column RELATIONSHIP_ID SqlType(BIGINT) */
  case class GoalRelationshipRow(goalId: Long, relationshipId: Long)
  /** GetResult implicit for fetching GoalRelationshipRow objects using plain SQL queries */
  implicit def GetResultGoalRelationshipRow(implicit e0: GR[Long]): GR[GoalRelationshipRow] = GR{
    prs => import prs._
    GoalRelationshipRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table GOAL_RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class GoalRelationshipTable(_tableTag: Tag) extends profile.api.Table[GoalRelationshipRow](_tableTag, "GOAL_RELATIONSHIP") {
    def * = (goalId, relationshipId) <> (GoalRelationshipRow.tupled, GoalRelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(goalId), Rep.Some(relationshipId)).shaped.<>({r=>import r._; _1.map(_=> GoalRelationshipRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column GOAL_ID SqlType(BIGINT) */
    val goalId: Rep[Long] = column[Long]("GOAL_ID")
    /** Database column RELATIONSHIP_ID SqlType(BIGINT) */
    val relationshipId: Rep[Long] = column[Long]("RELATIONSHIP_ID")

    /** Foreign key referencing GoalTable (database name GOAL_FK3) */
    lazy val goalTableFk = foreignKey("GOAL_FK3", goalId, GoalTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing RelationshipTable (database name RELATIONSHIP_FK1) */
    lazy val relationshipTableFk = foreignKey("RELATIONSHIP_FK1", relationshipId, RelationshipTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (goalId,relationshipId) (database name CONSTRAINT_INDEX_D6) */
    val index1 = index("CONSTRAINT_INDEX_D6", (goalId, relationshipId), unique=true)
  }
  /** Collection-like TableQuery object for table GoalRelationshipTable */
  lazy val GoalRelationshipTable = new TableQuery(tag => new GoalRelationshipTable(tag))

  /** Entity class storing rows of table GoalSkillTable
   *  @param goalId Database column GOAL_ID SqlType(BIGINT)
   *  @param skillId Database column SKILL_ID SqlType(BIGINT)
   *  @param relevance Database column RELEVANCE SqlType(VARCHAR), Length(36,true)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class GoalSkillRow(goalId: Long, skillId: Long, relevance: String, level: String)
  /** GetResult implicit for fetching GoalSkillRow objects using plain SQL queries */
  implicit def GetResultGoalSkillRow(implicit e0: GR[Long], e1: GR[String]): GR[GoalSkillRow] = GR{
    prs => import prs._
    GoalSkillRow.tupled((<<[Long], <<[Long], <<[String], <<[String]))
  }
  /** Table description of table GOAL_SKILL. Objects of this class serve as prototypes for rows in queries. */
  class GoalSkillTable(_tableTag: Tag) extends profile.api.Table[GoalSkillRow](_tableTag, "GOAL_SKILL") {
    def * = (goalId, skillId, relevance, level) <> (GoalSkillRow.tupled, GoalSkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(goalId), Rep.Some(skillId), Rep.Some(relevance), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> GoalSkillRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column GOAL_ID SqlType(BIGINT) */
    val goalId: Rep[Long] = column[Long]("GOAL_ID")
    /** Database column SKILL_ID SqlType(BIGINT) */
    val skillId: Rep[Long] = column[Long]("SKILL_ID")
    /** Database column RELEVANCE SqlType(VARCHAR), Length(36,true) */
    val relevance: Rep[String] = column[String]("RELEVANCE", O.Length(36,varying=true))
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing GoalTable (database name GOAL_FK2) */
    lazy val goalTableFk = foreignKey("GOAL_FK2", goalId, GoalTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing SkillTable (database name SKILL_FK1) */
    lazy val skillTableFk = foreignKey("SKILL_FK1", skillId, SkillTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (goalId,skillId) (database name CONSTRAINT_INDEX_3C) */
    val index1 = index("CONSTRAINT_INDEX_3C", (goalId, skillId), unique=true)
  }
  /** Collection-like TableQuery object for table GoalSkillTable */
  lazy val GoalSkillTable = new TableQuery(tag => new GoalSkillTable(tag))

  /** Entity class storing rows of table GoalTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param themeId Database column THEME_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param safetyNet Database column SAFETY_NET SqlType(TINYINT)
   *  @param worldView Database column WORLD_VIEW SqlType(TINYINT)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param graduation Database column GRADUATION SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP) */
  case class GoalRow(id: Long, uuid: String, themeId: String, summary: String, description: String, safetyNet: Byte, worldView: Byte, status: String, graduation: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching GoalRow objects using plain SQL queries */
  implicit def GetResultGoalRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Byte], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[GoalRow] = GR{
    prs => import prs._
    GoalRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[Byte], <<[Byte], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table GOAL. Objects of this class serve as prototypes for rows in queries. */
  class GoalTable(_tableTag: Tag) extends profile.api.Table[GoalRow](_tableTag, "GOAL") {
    def * = (id, uuid, themeId, summary, description, safetyNet, worldView, status, graduation, createdOn, lastModified) <> (GoalRow.tupled, GoalRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(themeId), Rep.Some(summary), Rep.Some(description), Rep.Some(safetyNet), Rep.Some(worldView), Rep.Some(status), Rep.Some(graduation), Rep.Some(createdOn), lastModified).shaped.<>({r=>import r._; _1.map(_=> GoalRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column SAFETY_NET SqlType(TINYINT) */
    val safetyNet: Rep[Byte] = column[Byte]("SAFETY_NET")
    /** Database column WORLD_VIEW SqlType(TINYINT) */
    val worldView: Rep[Byte] = column[Byte]("WORLD_VIEW")
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

  /** Entity class storing rows of table HobbyMissionTable
   *  @param hobbyId Database column HOBBY_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT) */
  case class HobbyMissionRow(hobbyId: Long, missionId: Long)
  /** GetResult implicit for fetching HobbyMissionRow objects using plain SQL queries */
  implicit def GetResultHobbyMissionRow(implicit e0: GR[Long]): GR[HobbyMissionRow] = GR{
    prs => import prs._
    HobbyMissionRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table HOBBY_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class HobbyMissionTable(_tableTag: Tag) extends profile.api.Table[HobbyMissionRow](_tableTag, "HOBBY_MISSION") {
    def * = (hobbyId, missionId) <> (HobbyMissionRow.tupled, HobbyMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(hobbyId), Rep.Some(missionId)).shaped.<>({r=>import r._; _1.map(_=> HobbyMissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column HOBBY_ID SqlType(BIGINT) */
    val hobbyId: Rep[Long] = column[Long]("HOBBY_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")

    /** Foreign key referencing HobbyTable (database name HOBBY_FK3) */
    lazy val hobbyTableFk = foreignKey("HOBBY_FK3", hobbyId, HobbyTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing MissionTable (database name MISSION_FK7) */
    lazy val missionTableFk = foreignKey("MISSION_FK7", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (hobbyId,missionId) (database name CONSTRAINT_INDEX_55) */
    val index1 = index("CONSTRAINT_INDEX_55", (hobbyId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table HobbyMissionTable */
  lazy val HobbyMissionTable = new TableQuery(tag => new HobbyMissionTable(tag))

  /** Entity class storing rows of table HobbyRelationshipTable
   *  @param hobbyId Database column HOBBY_ID SqlType(BIGINT)
   *  @param relationshipId Database column RELATIONSHIP_ID SqlType(BIGINT) */
  case class HobbyRelationshipRow(hobbyId: Long, relationshipId: Long)
  /** GetResult implicit for fetching HobbyRelationshipRow objects using plain SQL queries */
  implicit def GetResultHobbyRelationshipRow(implicit e0: GR[Long]): GR[HobbyRelationshipRow] = GR{
    prs => import prs._
    HobbyRelationshipRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table HOBBY_RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class HobbyRelationshipTable(_tableTag: Tag) extends profile.api.Table[HobbyRelationshipRow](_tableTag, "HOBBY_RELATIONSHIP") {
    def * = (hobbyId, relationshipId) <> (HobbyRelationshipRow.tupled, HobbyRelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(hobbyId), Rep.Some(relationshipId)).shaped.<>({r=>import r._; _1.map(_=> HobbyRelationshipRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column HOBBY_ID SqlType(BIGINT) */
    val hobbyId: Rep[Long] = column[Long]("HOBBY_ID")
    /** Database column RELATIONSHIP_ID SqlType(BIGINT) */
    val relationshipId: Rep[Long] = column[Long]("RELATIONSHIP_ID")

    /** Foreign key referencing HobbyTable (database name HOBBY_FK2) */
    lazy val hobbyTableFk = foreignKey("HOBBY_FK2", hobbyId, HobbyTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing RelationshipTable (database name RELATIONSHIP_FK6) */
    lazy val relationshipTableFk = foreignKey("RELATIONSHIP_FK6", relationshipId, RelationshipTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (hobbyId,relationshipId) (database name CONSTRAINT_INDEX_2D) */
    val index1 = index("CONSTRAINT_INDEX_2D", (hobbyId, relationshipId), unique=true)
  }
  /** Collection-like TableQuery object for table HobbyRelationshipTable */
  lazy val HobbyRelationshipTable = new TableQuery(tag => new HobbyRelationshipTable(tag))

  /** Entity class storing rows of table HobbySkillTable
   *  @param hobbyId Database column HOBBY_ID SqlType(BIGINT)
   *  @param skillId Database column SKILL_ID SqlType(BIGINT)
   *  @param relevance Database column RELEVANCE SqlType(VARCHAR), Length(36,true)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class HobbySkillRow(hobbyId: Long, skillId: Long, relevance: String, level: String)
  /** GetResult implicit for fetching HobbySkillRow objects using plain SQL queries */
  implicit def GetResultHobbySkillRow(implicit e0: GR[Long], e1: GR[String]): GR[HobbySkillRow] = GR{
    prs => import prs._
    HobbySkillRow.tupled((<<[Long], <<[Long], <<[String], <<[String]))
  }
  /** Table description of table HOBBY_SKILL. Objects of this class serve as prototypes for rows in queries. */
  class HobbySkillTable(_tableTag: Tag) extends profile.api.Table[HobbySkillRow](_tableTag, "HOBBY_SKILL") {
    def * = (hobbyId, skillId, relevance, level) <> (HobbySkillRow.tupled, HobbySkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(hobbyId), Rep.Some(skillId), Rep.Some(relevance), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> HobbySkillRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column HOBBY_ID SqlType(BIGINT) */
    val hobbyId: Rep[Long] = column[Long]("HOBBY_ID")
    /** Database column SKILL_ID SqlType(BIGINT) */
    val skillId: Rep[Long] = column[Long]("SKILL_ID")
    /** Database column RELEVANCE SqlType(VARCHAR), Length(36,true) */
    val relevance: Rep[String] = column[String]("RELEVANCE", O.Length(36,varying=true))
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing HobbyTable (database name HOBBY_FK1) */
    lazy val hobbyTableFk = foreignKey("HOBBY_FK1", hobbyId, HobbyTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing SkillTable (database name SKILL_FK6) */
    lazy val skillTableFk = foreignKey("SKILL_FK6", skillId, SkillTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (hobbyId,skillId) (database name CONSTRAINT_INDEX_4F) */
    val index1 = index("CONSTRAINT_INDEX_4F", (hobbyId, skillId), unique=true)
  }
  /** Collection-like TableQuery object for table HobbySkillTable */
  lazy val HobbySkillTable = new TableQuery(tag => new HobbySkillTable(tag))

  /** Entity class storing rows of table HobbyTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param safetyNet Database column SAFETY_NET SqlType(TINYINT)
   *  @param worldView Database column WORLD_VIEW SqlType(TINYINT)
   *  @param frequency Database column FREQUENCY SqlType(VARCHAR), Length(36,true)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class HobbyRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, safetyNet: Byte, worldView: Byte, frequency: String, `type`: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching HobbyRow objects using plain SQL queries */
  implicit def GetResultHobbyRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Byte], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[HobbyRow] = GR{
    prs => import prs._
    HobbyRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[Byte], <<[Byte], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table HOBBY. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class HobbyTable(_tableTag: Tag) extends profile.api.Table[HobbyRow](_tableTag, "HOBBY") {
    def * = (id, uuid, goalId, summary, description, safetyNet, worldView, frequency, `type`, createdOn, lastModified, lastPerformed) <> (HobbyRow.tupled, HobbyRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(safetyNet), Rep.Some(worldView), Rep.Some(frequency), Rep.Some(`type`), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> HobbyRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column SAFETY_NET SqlType(TINYINT) */
    val safetyNet: Rep[Byte] = column[Byte]("SAFETY_NET")
    /** Database column WORLD_VIEW SqlType(TINYINT) */
    val worldView: Rep[Byte] = column[Byte]("WORLD_VIEW")
    /** Database column FREQUENCY SqlType(VARCHAR), Length(36,true) */
    val frequency: Rep[String] = column[String]("FREQUENCY", O.Length(36,varying=true))
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

  /** Entity class storing rows of table LaserDonutMissionTable
   *  @param laserDonutId Database column LASER_DONUT_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT) */
  case class LaserDonutMissionRow(laserDonutId: Long, missionId: Long)
  /** GetResult implicit for fetching LaserDonutMissionRow objects using plain SQL queries */
  implicit def GetResultLaserDonutMissionRow(implicit e0: GR[Long]): GR[LaserDonutMissionRow] = GR{
    prs => import prs._
    LaserDonutMissionRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table LASER_DONUT_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class LaserDonutMissionTable(_tableTag: Tag) extends profile.api.Table[LaserDonutMissionRow](_tableTag, "LASER_DONUT_MISSION") {
    def * = (laserDonutId, missionId) <> (LaserDonutMissionRow.tupled, LaserDonutMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(laserDonutId), Rep.Some(missionId)).shaped.<>({r=>import r._; _1.map(_=> LaserDonutMissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column LASER_DONUT_ID SqlType(BIGINT) */
    val laserDonutId: Rep[Long] = column[Long]("LASER_DONUT_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")

    /** Foreign key referencing LaserDonutTable (database name LASER_DONUT_FK4) */
    lazy val laserDonutTableFk = foreignKey("LASER_DONUT_FK4", laserDonutId, LaserDonutTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing MissionTable (database name MISSION_FK5) */
    lazy val missionTableFk = foreignKey("MISSION_FK5", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (laserDonutId,missionId) (database name CONSTRAINT_INDEX_5) */
    val index1 = index("CONSTRAINT_INDEX_5", (laserDonutId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table LaserDonutMissionTable */
  lazy val LaserDonutMissionTable = new TableQuery(tag => new LaserDonutMissionTable(tag))

  /** Entity class storing rows of table LaserDonutRelationshipTable
   *  @param laserDonutId Database column LASER_DONUT_ID SqlType(BIGINT)
   *  @param relationshipId Database column RELATIONSHIP_ID SqlType(BIGINT) */
  case class LaserDonutRelationshipRow(laserDonutId: Long, relationshipId: Long)
  /** GetResult implicit for fetching LaserDonutRelationshipRow objects using plain SQL queries */
  implicit def GetResultLaserDonutRelationshipRow(implicit e0: GR[Long]): GR[LaserDonutRelationshipRow] = GR{
    prs => import prs._
    LaserDonutRelationshipRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table LASER_DONUT_RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class LaserDonutRelationshipTable(_tableTag: Tag) extends profile.api.Table[LaserDonutRelationshipRow](_tableTag, "LASER_DONUT_RELATIONSHIP") {
    def * = (laserDonutId, relationshipId) <> (LaserDonutRelationshipRow.tupled, LaserDonutRelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(laserDonutId), Rep.Some(relationshipId)).shaped.<>({r=>import r._; _1.map(_=> LaserDonutRelationshipRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column LASER_DONUT_ID SqlType(BIGINT) */
    val laserDonutId: Rep[Long] = column[Long]("LASER_DONUT_ID")
    /** Database column RELATIONSHIP_ID SqlType(BIGINT) */
    val relationshipId: Rep[Long] = column[Long]("RELATIONSHIP_ID")

    /** Foreign key referencing LaserDonutTable (database name LASER_DONUT_FK3) */
    lazy val laserDonutTableFk = foreignKey("LASER_DONUT_FK3", laserDonutId, LaserDonutTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing RelationshipTable (database name RELATIONSHIP_FK4) */
    lazy val relationshipTableFk = foreignKey("RELATIONSHIP_FK4", relationshipId, RelationshipTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (laserDonutId,relationshipId) (database name CONSTRAINT_INDEX_F) */
    val index1 = index("CONSTRAINT_INDEX_F", (laserDonutId, relationshipId), unique=true)
  }
  /** Collection-like TableQuery object for table LaserDonutRelationshipTable */
  lazy val LaserDonutRelationshipTable = new TableQuery(tag => new LaserDonutRelationshipTable(tag))

  /** Entity class storing rows of table LaserDonutSkillTable
   *  @param laserDonutId Database column LASER_DONUT_ID SqlType(BIGINT)
   *  @param skillId Database column SKILL_ID SqlType(BIGINT)
   *  @param relevance Database column RELEVANCE SqlType(VARCHAR), Length(36,true)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class LaserDonutSkillRow(laserDonutId: Long, skillId: Long, relevance: String, level: String)
  /** GetResult implicit for fetching LaserDonutSkillRow objects using plain SQL queries */
  implicit def GetResultLaserDonutSkillRow(implicit e0: GR[Long], e1: GR[String]): GR[LaserDonutSkillRow] = GR{
    prs => import prs._
    LaserDonutSkillRow.tupled((<<[Long], <<[Long], <<[String], <<[String]))
  }
  /** Table description of table LASER_DONUT_SKILL. Objects of this class serve as prototypes for rows in queries. */
  class LaserDonutSkillTable(_tableTag: Tag) extends profile.api.Table[LaserDonutSkillRow](_tableTag, "LASER_DONUT_SKILL") {
    def * = (laserDonutId, skillId, relevance, level) <> (LaserDonutSkillRow.tupled, LaserDonutSkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(laserDonutId), Rep.Some(skillId), Rep.Some(relevance), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> LaserDonutSkillRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column LASER_DONUT_ID SqlType(BIGINT) */
    val laserDonutId: Rep[Long] = column[Long]("LASER_DONUT_ID")
    /** Database column SKILL_ID SqlType(BIGINT) */
    val skillId: Rep[Long] = column[Long]("SKILL_ID")
    /** Database column RELEVANCE SqlType(VARCHAR), Length(36,true) */
    val relevance: Rep[String] = column[String]("RELEVANCE", O.Length(36,varying=true))
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing LaserDonutTable (database name LASER_DONUT_FK2) */
    lazy val laserDonutTableFk = foreignKey("LASER_DONUT_FK2", laserDonutId, LaserDonutTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing SkillTable (database name SKILL_FK4) */
    lazy val skillTableFk = foreignKey("SKILL_FK4", skillId, SkillTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (laserDonutId,skillId) (database name CONSTRAINT_INDEX_18) */
    val index1 = index("CONSTRAINT_INDEX_18", (laserDonutId, skillId), unique=true)
  }
  /** Collection-like TableQuery object for table LaserDonutSkillTable */
  lazy val LaserDonutSkillTable = new TableQuery(tag => new LaserDonutSkillTable(tag))

  /** Entity class storing rows of table LaserDonutTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param safetyNet Database column SAFETY_NET SqlType(TINYINT)
   *  @param worldView Database column WORLD_VIEW SqlType(TINYINT)
   *  @param milestone Database column MILESTONE SqlType(VARCHAR), Length(256,true)
   *  @param order Database column ORDER SqlType(INTEGER)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class LaserDonutRow(id: Long, uuid: String, goalId: String, summary: String, description: String, safetyNet: Byte, worldView: Byte, milestone: String, order: Int, status: String, `type`: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching LaserDonutRow objects using plain SQL queries */
  implicit def GetResultLaserDonutRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Byte], e3: GR[Int], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[LaserDonutRow] = GR{
    prs => import prs._
    LaserDonutRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[Byte], <<[Byte], <<[String], <<[Int], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table LASER_DONUT. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class LaserDonutTable(_tableTag: Tag) extends profile.api.Table[LaserDonutRow](_tableTag, "LASER_DONUT") {
    def * = (id, uuid, goalId, summary, description, safetyNet, worldView, milestone, order, status, `type`, createdOn, lastModified, lastPerformed) <> (LaserDonutRow.tupled, LaserDonutRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(goalId), Rep.Some(summary), Rep.Some(description), Rep.Some(safetyNet), Rep.Some(worldView), Rep.Some(milestone), Rep.Some(order), Rep.Some(status), Rep.Some(`type`), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> LaserDonutRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13, _14)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column SAFETY_NET SqlType(TINYINT) */
    val safetyNet: Rep[Byte] = column[Byte]("SAFETY_NET")
    /** Database column WORLD_VIEW SqlType(TINYINT) */
    val worldView: Rep[Byte] = column[Byte]("WORLD_VIEW")
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

  /** Entity class storing rows of table MissionTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param name Database column NAME SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP) */
  case class MissionRow(id: Long, uuid: String, name: String, description: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching MissionRow objects using plain SQL queries */
  implicit def GetResultMissionRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Timestamp]]): GR[MissionRow] = GR{
    prs => import prs._
    MissionRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table MISSION. Objects of this class serve as prototypes for rows in queries. */
  class MissionTable(_tableTag: Tag) extends profile.api.Table[MissionRow](_tableTag, "MISSION") {
    def * = (id, uuid, name, description, createdOn, lastModified) <> (MissionRow.tupled, MissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(name), Rep.Some(description), Rep.Some(createdOn), lastModified).shaped.<>({r=>import r._; _1.map(_=> MissionRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column NAME SqlType(VARCHAR), Length(256,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(256,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_6) */
    val index1 = index("CONSTRAINT_INDEX_6", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table MissionTable */
  lazy val MissionTable = new TableQuery(tag => new MissionTable(tag))

  /** Entity class storing rows of table OneOffMissionTable
   *  @param oneOffId Database column ONE_OFF_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT) */
  case class OneOffMissionRow(oneOffId: Long, missionId: Long)
  /** GetResult implicit for fetching OneOffMissionRow objects using plain SQL queries */
  implicit def GetResultOneOffMissionRow(implicit e0: GR[Long]): GR[OneOffMissionRow] = GR{
    prs => import prs._
    OneOffMissionRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table ONE_OFF_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class OneOffMissionTable(_tableTag: Tag) extends profile.api.Table[OneOffMissionRow](_tableTag, "ONE_OFF_MISSION") {
    def * = (oneOffId, missionId) <> (OneOffMissionRow.tupled, OneOffMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(oneOffId), Rep.Some(missionId)).shaped.<>({r=>import r._; _1.map(_=> OneOffMissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ONE_OFF_ID SqlType(BIGINT) */
    val oneOffId: Rep[Long] = column[Long]("ONE_OFF_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")

    /** Foreign key referencing MissionTable (database name MISSION_FK8) */
    lazy val missionTableFk = foreignKey("MISSION_FK8", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing OneOffTable (database name ONE_OFF_FK3) */
    lazy val oneOffTableFk = foreignKey("ONE_OFF_FK3", oneOffId, OneOffTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (oneOffId,missionId) (database name CONSTRAINT_INDEX_F0) */
    val index1 = index("CONSTRAINT_INDEX_F0", (oneOffId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table OneOffMissionTable */
  lazy val OneOffMissionTable = new TableQuery(tag => new OneOffMissionTable(tag))

  /** Entity class storing rows of table OneOffRelationshipTable
   *  @param oneOffId Database column ONE_OFF_ID SqlType(BIGINT)
   *  @param relationshipId Database column RELATIONSHIP_ID SqlType(BIGINT) */
  case class OneOffRelationshipRow(oneOffId: Long, relationshipId: Long)
  /** GetResult implicit for fetching OneOffRelationshipRow objects using plain SQL queries */
  implicit def GetResultOneOffRelationshipRow(implicit e0: GR[Long]): GR[OneOffRelationshipRow] = GR{
    prs => import prs._
    OneOffRelationshipRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table ONE_OFF_RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class OneOffRelationshipTable(_tableTag: Tag) extends profile.api.Table[OneOffRelationshipRow](_tableTag, "ONE_OFF_RELATIONSHIP") {
    def * = (oneOffId, relationshipId) <> (OneOffRelationshipRow.tupled, OneOffRelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(oneOffId), Rep.Some(relationshipId)).shaped.<>({r=>import r._; _1.map(_=> OneOffRelationshipRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ONE_OFF_ID SqlType(BIGINT) */
    val oneOffId: Rep[Long] = column[Long]("ONE_OFF_ID")
    /** Database column RELATIONSHIP_ID SqlType(BIGINT) */
    val relationshipId: Rep[Long] = column[Long]("RELATIONSHIP_ID")

    /** Foreign key referencing OneOffTable (database name ONE_OFF_FK2) */
    lazy val oneOffTableFk = foreignKey("ONE_OFF_FK2", oneOffId, OneOffTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing RelationshipTable (database name RELATIONSHIP_FK7) */
    lazy val relationshipTableFk = foreignKey("RELATIONSHIP_FK7", relationshipId, RelationshipTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (oneOffId,relationshipId) (database name CONSTRAINT_INDEX_FD) */
    val index1 = index("CONSTRAINT_INDEX_FD", (oneOffId, relationshipId), unique=true)
  }
  /** Collection-like TableQuery object for table OneOffRelationshipTable */
  lazy val OneOffRelationshipTable = new TableQuery(tag => new OneOffRelationshipTable(tag))

  /** Entity class storing rows of table OneOffSkillTable
   *  @param oneOffId Database column ONE_OFF_ID SqlType(BIGINT)
   *  @param skillId Database column SKILL_ID SqlType(BIGINT)
   *  @param relevance Database column RELEVANCE SqlType(VARCHAR), Length(36,true)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class OneOffSkillRow(oneOffId: Long, skillId: Long, relevance: String, level: String)
  /** GetResult implicit for fetching OneOffSkillRow objects using plain SQL queries */
  implicit def GetResultOneOffSkillRow(implicit e0: GR[Long], e1: GR[String]): GR[OneOffSkillRow] = GR{
    prs => import prs._
    OneOffSkillRow.tupled((<<[Long], <<[Long], <<[String], <<[String]))
  }
  /** Table description of table ONE_OFF_SKILL. Objects of this class serve as prototypes for rows in queries. */
  class OneOffSkillTable(_tableTag: Tag) extends profile.api.Table[OneOffSkillRow](_tableTag, "ONE_OFF_SKILL") {
    def * = (oneOffId, skillId, relevance, level) <> (OneOffSkillRow.tupled, OneOffSkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(oneOffId), Rep.Some(skillId), Rep.Some(relevance), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> OneOffSkillRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ONE_OFF_ID SqlType(BIGINT) */
    val oneOffId: Rep[Long] = column[Long]("ONE_OFF_ID")
    /** Database column SKILL_ID SqlType(BIGINT) */
    val skillId: Rep[Long] = column[Long]("SKILL_ID")
    /** Database column RELEVANCE SqlType(VARCHAR), Length(36,true) */
    val relevance: Rep[String] = column[String]("RELEVANCE", O.Length(36,varying=true))
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing OneOffTable (database name ONE_OFF_FK1) */
    lazy val oneOffTableFk = foreignKey("ONE_OFF_FK1", oneOffId, OneOffTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing SkillTable (database name SKILL_FK7) */
    lazy val skillTableFk = foreignKey("SKILL_FK7", skillId, SkillTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (oneOffId,skillId) (database name CONSTRAINT_INDEX_1E) */
    val index1 = index("CONSTRAINT_INDEX_1E", (oneOffId, skillId), unique=true)
  }
  /** Collection-like TableQuery object for table OneOffSkillTable */
  lazy val OneOffSkillTable = new TableQuery(tag => new OneOffSkillTable(tag))

  /** Entity class storing rows of table OneOffTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param safetyNet Database column SAFETY_NET SqlType(TINYINT)
   *  @param worldView Database column WORLD_VIEW SqlType(TINYINT)
   *  @param estimate Database column ESTIMATE SqlType(BIGINT)
   *  @param order Database column ORDER SqlType(INTEGER)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class OneOffRow(id: Long, uuid: String, goalId: Option[String], description: String, safetyNet: Byte, worldView: Byte, estimate: Long, order: Int, status: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching OneOffRow objects using plain SQL queries */
  implicit def GetResultOneOffRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Byte], e4: GR[Int], e5: GR[java.sql.Timestamp], e6: GR[Option[java.sql.Timestamp]]): GR[OneOffRow] = GR{
    prs => import prs._
    OneOffRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[Byte], <<[Byte], <<[Long], <<[Int], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table ONE_OFF. Objects of this class serve as prototypes for rows in queries. */
  class OneOffTable(_tableTag: Tag) extends profile.api.Table[OneOffRow](_tableTag, "ONE_OFF") {
    def * = (id, uuid, goalId, description, safetyNet, worldView, estimate, order, status, createdOn, lastModified, lastPerformed) <> (OneOffRow.tupled, OneOffRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(description), Rep.Some(safetyNet), Rep.Some(worldView), Rep.Some(estimate), Rep.Some(order), Rep.Some(status), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> OneOffRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column GOAL_ID SqlType(VARCHAR), Length(36,true) */
    val goalId: Rep[Option[String]] = column[Option[String]]("GOAL_ID", O.Length(36,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column SAFETY_NET SqlType(TINYINT) */
    val safetyNet: Rep[Byte] = column[Byte]("SAFETY_NET")
    /** Database column WORLD_VIEW SqlType(TINYINT) */
    val worldView: Rep[Byte] = column[Byte]("WORLD_VIEW")
    /** Database column ESTIMATE SqlType(BIGINT) */
    val estimate: Rep[Long] = column[Long]("ESTIMATE")
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

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_DC) */
    val index1 = index("CONSTRAINT_INDEX_DC", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table OneOffTable */
  lazy val OneOffTable = new TableQuery(tag => new OneOffTable(tag))

  /** Entity class storing rows of table PortionMissionTable
   *  @param portionId Database column PORTION_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT) */
  case class PortionMissionRow(portionId: Long, missionId: Long)
  /** GetResult implicit for fetching PortionMissionRow objects using plain SQL queries */
  implicit def GetResultPortionMissionRow(implicit e0: GR[Long]): GR[PortionMissionRow] = GR{
    prs => import prs._
    PortionMissionRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table PORTION_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class PortionMissionTable(_tableTag: Tag) extends profile.api.Table[PortionMissionRow](_tableTag, "PORTION_MISSION") {
    def * = (portionId, missionId) <> (PortionMissionRow.tupled, PortionMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(portionId), Rep.Some(missionId)).shaped.<>({r=>import r._; _1.map(_=> PortionMissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column PORTION_ID SqlType(BIGINT) */
    val portionId: Rep[Long] = column[Long]("PORTION_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")

    /** Foreign key referencing MissionTable (database name MISSION_FK6) */
    lazy val missionTableFk = foreignKey("MISSION_FK6", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing PortionTable (database name PORTION_FK3) */
    lazy val portionTableFk = foreignKey("PORTION_FK3", portionId, PortionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (portionId,missionId) (database name CONSTRAINT_INDEX_B0) */
    val index1 = index("CONSTRAINT_INDEX_B0", (portionId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table PortionMissionTable */
  lazy val PortionMissionTable = new TableQuery(tag => new PortionMissionTable(tag))

  /** Entity class storing rows of table PortionRelationshipTable
   *  @param portionId Database column PORTION_ID SqlType(BIGINT)
   *  @param relationshipId Database column RELATIONSHIP_ID SqlType(BIGINT) */
  case class PortionRelationshipRow(portionId: Long, relationshipId: Long)
  /** GetResult implicit for fetching PortionRelationshipRow objects using plain SQL queries */
  implicit def GetResultPortionRelationshipRow(implicit e0: GR[Long]): GR[PortionRelationshipRow] = GR{
    prs => import prs._
    PortionRelationshipRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table PORTION_RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class PortionRelationshipTable(_tableTag: Tag) extends profile.api.Table[PortionRelationshipRow](_tableTag, "PORTION_RELATIONSHIP") {
    def * = (portionId, relationshipId) <> (PortionRelationshipRow.tupled, PortionRelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(portionId), Rep.Some(relationshipId)).shaped.<>({r=>import r._; _1.map(_=> PortionRelationshipRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column PORTION_ID SqlType(BIGINT) */
    val portionId: Rep[Long] = column[Long]("PORTION_ID")
    /** Database column RELATIONSHIP_ID SqlType(BIGINT) */
    val relationshipId: Rep[Long] = column[Long]("RELATIONSHIP_ID")

    /** Foreign key referencing PortionTable (database name PORTION_FK2) */
    lazy val portionTableFk = foreignKey("PORTION_FK2", portionId, PortionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing RelationshipTable (database name RELATIONSHIP_FK5) */
    lazy val relationshipTableFk = foreignKey("RELATIONSHIP_FK5", relationshipId, RelationshipTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (portionId,relationshipId) (database name CONSTRAINT_INDEX_24) */
    val index1 = index("CONSTRAINT_INDEX_24", (portionId, relationshipId), unique=true)
  }
  /** Collection-like TableQuery object for table PortionRelationshipTable */
  lazy val PortionRelationshipTable = new TableQuery(tag => new PortionRelationshipTable(tag))

  /** Entity class storing rows of table PortionSkillTable
   *  @param portionId Database column PORTION_ID SqlType(BIGINT)
   *  @param skillId Database column SKILL_ID SqlType(BIGINT)
   *  @param relevance Database column RELEVANCE SqlType(VARCHAR), Length(36,true)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class PortionSkillRow(portionId: Long, skillId: Long, relevance: String, level: String)
  /** GetResult implicit for fetching PortionSkillRow objects using plain SQL queries */
  implicit def GetResultPortionSkillRow(implicit e0: GR[Long], e1: GR[String]): GR[PortionSkillRow] = GR{
    prs => import prs._
    PortionSkillRow.tupled((<<[Long], <<[Long], <<[String], <<[String]))
  }
  /** Table description of table PORTION_SKILL. Objects of this class serve as prototypes for rows in queries. */
  class PortionSkillTable(_tableTag: Tag) extends profile.api.Table[PortionSkillRow](_tableTag, "PORTION_SKILL") {
    def * = (portionId, skillId, relevance, level) <> (PortionSkillRow.tupled, PortionSkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(portionId), Rep.Some(skillId), Rep.Some(relevance), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> PortionSkillRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column PORTION_ID SqlType(BIGINT) */
    val portionId: Rep[Long] = column[Long]("PORTION_ID")
    /** Database column SKILL_ID SqlType(BIGINT) */
    val skillId: Rep[Long] = column[Long]("SKILL_ID")
    /** Database column RELEVANCE SqlType(VARCHAR), Length(36,true) */
    val relevance: Rep[String] = column[String]("RELEVANCE", O.Length(36,varying=true))
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing PortionTable (database name PORTION_FK1) */
    lazy val portionTableFk = foreignKey("PORTION_FK1", portionId, PortionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing SkillTable (database name SKILL_FK5) */
    lazy val skillTableFk = foreignKey("SKILL_FK5", skillId, SkillTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (portionId,skillId) (database name CONSTRAINT_INDEX_DD) */
    val index1 = index("CONSTRAINT_INDEX_DD", (portionId, skillId), unique=true)
  }
  /** Collection-like TableQuery object for table PortionSkillTable */
  lazy val PortionSkillTable = new TableQuery(tag => new PortionSkillTable(tag))

  /** Entity class storing rows of table PortionTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param laserDonutId Database column LASER_DONUT_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param safetyNet Database column SAFETY_NET SqlType(TINYINT)
   *  @param worldView Database column WORLD_VIEW SqlType(TINYINT)
   *  @param order Database column ORDER SqlType(INTEGER)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class PortionRow(id: Long, uuid: String, laserDonutId: String, summary: String, safetyNet: Byte, worldView: Byte, order: Int, status: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching PortionRow objects using plain SQL queries */
  implicit def GetResultPortionRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Byte], e3: GR[Int], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[PortionRow] = GR{
    prs => import prs._
    PortionRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[Byte], <<[Byte], <<[Int], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table PORTION. Objects of this class serve as prototypes for rows in queries. */
  class PortionTable(_tableTag: Tag) extends profile.api.Table[PortionRow](_tableTag, "PORTION") {
    def * = (id, uuid, laserDonutId, summary, safetyNet, worldView, order, status, createdOn, lastModified, lastPerformed) <> (PortionRow.tupled, PortionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(laserDonutId), Rep.Some(summary), Rep.Some(safetyNet), Rep.Some(worldView), Rep.Some(order), Rep.Some(status), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> PortionRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column LASER_DONUT_ID SqlType(VARCHAR), Length(36,true) */
    val laserDonutId: Rep[String] = column[String]("LASER_DONUT_ID", O.Length(36,varying=true))
    /** Database column SUMMARY SqlType(VARCHAR), Length(256,true) */
    val summary: Rep[String] = column[String]("SUMMARY", O.Length(256,varying=true))
    /** Database column SAFETY_NET SqlType(TINYINT) */
    val safetyNet: Rep[Byte] = column[Byte]("SAFETY_NET")
    /** Database column WORLD_VIEW SqlType(TINYINT) */
    val worldView: Rep[Byte] = column[Byte]("WORLD_VIEW")
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

  /** Entity class storing rows of table RelationshipTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param name Database column NAME SqlType(VARCHAR), Length(256,true)
   *  @param category Database column CATEGORY SqlType(VARCHAR), Length(36,true)
   *  @param traits Database column TRAITS SqlType(VARCHAR), Length(2000,true)
   *  @param likes Database column LIKES SqlType(VARCHAR), Length(2000,true)
   *  @param dislikes Database column DISLIKES SqlType(VARCHAR), Length(2000,true)
   *  @param hobbies Database column HOBBIES SqlType(VARCHAR), Length(2000,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastMeet Database column LAST_MEET SqlType(DATE) */
  case class RelationshipRow(id: Long, uuid: String, name: String, category: String, traits: String, likes: String, dislikes: String, hobbies: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastMeet: Option[java.sql.Date])
  /** GetResult implicit for fetching RelationshipRow objects using plain SQL queries */
  implicit def GetResultRelationshipRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Timestamp]], e4: GR[Option[java.sql.Date]]): GR[RelationshipRow] = GR{
    prs => import prs._
    RelationshipRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Date]))
  }
  /** Table description of table RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class RelationshipTable(_tableTag: Tag) extends profile.api.Table[RelationshipRow](_tableTag, "RELATIONSHIP") {
    def * = (id, uuid, name, category, traits, likes, dislikes, hobbies, createdOn, lastModified, lastMeet) <> (RelationshipRow.tupled, RelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(name), Rep.Some(category), Rep.Some(traits), Rep.Some(likes), Rep.Some(dislikes), Rep.Some(hobbies), Rep.Some(createdOn), lastModified, lastMeet).shaped.<>({r=>import r._; _1.map(_=> RelationshipRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column NAME SqlType(VARCHAR), Length(256,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(256,varying=true))
    /** Database column CATEGORY SqlType(VARCHAR), Length(36,true) */
    val category: Rep[String] = column[String]("CATEGORY", O.Length(36,varying=true))
    /** Database column TRAITS SqlType(VARCHAR), Length(2000,true) */
    val traits: Rep[String] = column[String]("TRAITS", O.Length(2000,varying=true))
    /** Database column LIKES SqlType(VARCHAR), Length(2000,true) */
    val likes: Rep[String] = column[String]("LIKES", O.Length(2000,varying=true))
    /** Database column DISLIKES SqlType(VARCHAR), Length(2000,true) */
    val dislikes: Rep[String] = column[String]("DISLIKES", O.Length(2000,varying=true))
    /** Database column HOBBIES SqlType(VARCHAR), Length(2000,true) */
    val hobbies: Rep[String] = column[String]("HOBBIES", O.Length(2000,varying=true))
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_MEET SqlType(DATE) */
    val lastMeet: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("LAST_MEET")

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_7) */
    val index1 = index("CONSTRAINT_INDEX_7", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table RelationshipTable */
  lazy val RelationshipTable = new TableQuery(tag => new RelationshipTable(tag))

  /** Entity class storing rows of table ScheduledLaserDonutTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param laserDonutId Database column LASER_DONUT_ID SqlType(BIGINT)
   *  @param tier Database column TIER SqlType(INTEGER)
   *  @param isCurrent Database column IS_CURRENT SqlType(TINYINT) */
  case class ScheduledLaserDonutRow(id: Long, laserDonutId: Long, tier: Int, isCurrent: Byte)
  /** GetResult implicit for fetching ScheduledLaserDonutRow objects using plain SQL queries */
  implicit def GetResultScheduledLaserDonutRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Byte]): GR[ScheduledLaserDonutRow] = GR{
    prs => import prs._
    ScheduledLaserDonutRow.tupled((<<[Long], <<[Long], <<[Int], <<[Byte]))
  }
  /** Table description of table SCHEDULED_LASER_DONUT. Objects of this class serve as prototypes for rows in queries. */
  class ScheduledLaserDonutTable(_tableTag: Tag) extends profile.api.Table[ScheduledLaserDonutRow](_tableTag, "SCHEDULED_LASER_DONUT") {
    def * = (id, laserDonutId, tier, isCurrent) <> (ScheduledLaserDonutRow.tupled, ScheduledLaserDonutRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(laserDonutId), Rep.Some(tier), Rep.Some(isCurrent)).shaped.<>({r=>import r._; _1.map(_=> ScheduledLaserDonutRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column LASER_DONUT_ID SqlType(BIGINT) */
    val laserDonutId: Rep[Long] = column[Long]("LASER_DONUT_ID")
    /** Database column TIER SqlType(INTEGER) */
    val tier: Rep[Int] = column[Int]("TIER")
    /** Database column IS_CURRENT SqlType(TINYINT) */
    val isCurrent: Rep[Byte] = column[Byte]("IS_CURRENT")

    /** Foreign key referencing LaserDonutTable (database name LASER_DONUT_FK1) */
    lazy val laserDonutTableFk = foreignKey("LASER_DONUT_FK1", laserDonutId, LaserDonutTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (laserDonutId) (database name CONSTRAINT_INDEX_8) */
    val index1 = index("CONSTRAINT_INDEX_8", laserDonutId, unique=true)
  }
  /** Collection-like TableQuery object for table ScheduledLaserDonutTable */
  lazy val ScheduledLaserDonutTable = new TableQuery(tag => new ScheduledLaserDonutTable(tag))

  /** Entity class storing rows of table ScheduledOneOffMissionTable
   *  @param scheduledOneOffId Database column SCHEDULED_ONE_OFF_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT) */
  case class ScheduledOneOffMissionRow(scheduledOneOffId: Long, missionId: Long)
  /** GetResult implicit for fetching ScheduledOneOffMissionRow objects using plain SQL queries */
  implicit def GetResultScheduledOneOffMissionRow(implicit e0: GR[Long]): GR[ScheduledOneOffMissionRow] = GR{
    prs => import prs._
    ScheduledOneOffMissionRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table SCHEDULED_ONE_OFF_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class ScheduledOneOffMissionTable(_tableTag: Tag) extends profile.api.Table[ScheduledOneOffMissionRow](_tableTag, "SCHEDULED_ONE_OFF_MISSION") {
    def * = (scheduledOneOffId, missionId) <> (ScheduledOneOffMissionRow.tupled, ScheduledOneOffMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(scheduledOneOffId), Rep.Some(missionId)).shaped.<>({r=>import r._; _1.map(_=> ScheduledOneOffMissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column SCHEDULED_ONE_OFF_ID SqlType(BIGINT) */
    val scheduledOneOffId: Rep[Long] = column[Long]("SCHEDULED_ONE_OFF_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")

    /** Foreign key referencing MissionTable (database name MISSION_FK9) */
    lazy val missionTableFk = foreignKey("MISSION_FK9", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing ScheduledOneOffTable (database name SCHEDULED_ONE_OFF_FK3) */
    lazy val scheduledOneOffTableFk = foreignKey("SCHEDULED_ONE_OFF_FK3", scheduledOneOffId, ScheduledOneOffTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (scheduledOneOffId,missionId) (database name CONSTRAINT_INDEX_4E9) */
    val index1 = index("CONSTRAINT_INDEX_4E9", (scheduledOneOffId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table ScheduledOneOffMissionTable */
  lazy val ScheduledOneOffMissionTable = new TableQuery(tag => new ScheduledOneOffMissionTable(tag))

  /** Entity class storing rows of table ScheduledOneOffRelationshipTable
   *  @param scheduledOneOffId Database column SCHEDULED_ONE_OFF_ID SqlType(BIGINT)
   *  @param relationshipId Database column RELATIONSHIP_ID SqlType(BIGINT) */
  case class ScheduledOneOffRelationshipRow(scheduledOneOffId: Long, relationshipId: Long)
  /** GetResult implicit for fetching ScheduledOneOffRelationshipRow objects using plain SQL queries */
  implicit def GetResultScheduledOneOffRelationshipRow(implicit e0: GR[Long]): GR[ScheduledOneOffRelationshipRow] = GR{
    prs => import prs._
    ScheduledOneOffRelationshipRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table SCHEDULED_ONE_OFF_RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class ScheduledOneOffRelationshipTable(_tableTag: Tag) extends profile.api.Table[ScheduledOneOffRelationshipRow](_tableTag, "SCHEDULED_ONE_OFF_RELATIONSHIP") {
    def * = (scheduledOneOffId, relationshipId) <> (ScheduledOneOffRelationshipRow.tupled, ScheduledOneOffRelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(scheduledOneOffId), Rep.Some(relationshipId)).shaped.<>({r=>import r._; _1.map(_=> ScheduledOneOffRelationshipRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column SCHEDULED_ONE_OFF_ID SqlType(BIGINT) */
    val scheduledOneOffId: Rep[Long] = column[Long]("SCHEDULED_ONE_OFF_ID")
    /** Database column RELATIONSHIP_ID SqlType(BIGINT) */
    val relationshipId: Rep[Long] = column[Long]("RELATIONSHIP_ID")

    /** Foreign key referencing RelationshipTable (database name RELATIONSHIP_FK8) */
    lazy val relationshipTableFk = foreignKey("RELATIONSHIP_FK8", relationshipId, RelationshipTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing ScheduledOneOffTable (database name SCHEDULED_ONE_OFF_FK2) */
    lazy val scheduledOneOffTableFk = foreignKey("SCHEDULED_ONE_OFF_FK2", scheduledOneOffId, ScheduledOneOffTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (scheduledOneOffId,relationshipId) (database name CONSTRAINT_INDEX_C) */
    val index1 = index("CONSTRAINT_INDEX_C", (scheduledOneOffId, relationshipId), unique=true)
  }
  /** Collection-like TableQuery object for table ScheduledOneOffRelationshipTable */
  lazy val ScheduledOneOffRelationshipTable = new TableQuery(tag => new ScheduledOneOffRelationshipTable(tag))

  /** Entity class storing rows of table ScheduledOneOffSkillTable
   *  @param scheduledOneOffId Database column SCHEDULED_ONE_OFF_ID SqlType(BIGINT)
   *  @param skillId Database column SKILL_ID SqlType(BIGINT)
   *  @param relevance Database column RELEVANCE SqlType(VARCHAR), Length(36,true)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class ScheduledOneOffSkillRow(scheduledOneOffId: Long, skillId: Long, relevance: String, level: String)
  /** GetResult implicit for fetching ScheduledOneOffSkillRow objects using plain SQL queries */
  implicit def GetResultScheduledOneOffSkillRow(implicit e0: GR[Long], e1: GR[String]): GR[ScheduledOneOffSkillRow] = GR{
    prs => import prs._
    ScheduledOneOffSkillRow.tupled((<<[Long], <<[Long], <<[String], <<[String]))
  }
  /** Table description of table SCHEDULED_ONE_OFF_SKILL. Objects of this class serve as prototypes for rows in queries. */
  class ScheduledOneOffSkillTable(_tableTag: Tag) extends profile.api.Table[ScheduledOneOffSkillRow](_tableTag, "SCHEDULED_ONE_OFF_SKILL") {
    def * = (scheduledOneOffId, skillId, relevance, level) <> (ScheduledOneOffSkillRow.tupled, ScheduledOneOffSkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(scheduledOneOffId), Rep.Some(skillId), Rep.Some(relevance), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> ScheduledOneOffSkillRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column SCHEDULED_ONE_OFF_ID SqlType(BIGINT) */
    val scheduledOneOffId: Rep[Long] = column[Long]("SCHEDULED_ONE_OFF_ID")
    /** Database column SKILL_ID SqlType(BIGINT) */
    val skillId: Rep[Long] = column[Long]("SKILL_ID")
    /** Database column RELEVANCE SqlType(VARCHAR), Length(36,true) */
    val relevance: Rep[String] = column[String]("RELEVANCE", O.Length(36,varying=true))
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing ScheduledOneOffTable (database name SCHEDULED_ONE_OFF_FK1) */
    lazy val scheduledOneOffTableFk = foreignKey("SCHEDULED_ONE_OFF_FK1", scheduledOneOffId, ScheduledOneOffTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing SkillTable (database name SKILL_FK8) */
    lazy val skillTableFk = foreignKey("SKILL_FK8", skillId, SkillTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (scheduledOneOffId,skillId) (database name CONSTRAINT_INDEX_92) */
    val index1 = index("CONSTRAINT_INDEX_92", (scheduledOneOffId, skillId), unique=true)
  }
  /** Collection-like TableQuery object for table ScheduledOneOffSkillTable */
  lazy val ScheduledOneOffSkillTable = new TableQuery(tag => new ScheduledOneOffSkillTable(tag))

  /** Entity class storing rows of table ScheduledOneOffTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param occursOn Database column OCCURS_ON SqlType(TIMESTAMP)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param safetyNet Database column SAFETY_NET SqlType(TINYINT)
   *  @param worldView Database column WORLD_VIEW SqlType(TINYINT)
   *  @param estimate Database column ESTIMATE SqlType(BIGINT)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class ScheduledOneOffRow(id: Long, uuid: String, occursOn: java.sql.Timestamp, goalId: Option[String], description: String, safetyNet: Byte, worldView: Byte, estimate: Long, status: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ScheduledOneOffRow objects using plain SQL queries */
  implicit def GetResultScheduledOneOffRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]], e4: GR[Byte], e5: GR[Option[java.sql.Timestamp]]): GR[ScheduledOneOffRow] = GR{
    prs => import prs._
    ScheduledOneOffRow.tupled((<<[Long], <<[String], <<[java.sql.Timestamp], <<?[String], <<[String], <<[Byte], <<[Byte], <<[Long], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table SCHEDULED_ONE_OFF. Objects of this class serve as prototypes for rows in queries. */
  class ScheduledOneOffTable(_tableTag: Tag) extends profile.api.Table[ScheduledOneOffRow](_tableTag, "SCHEDULED_ONE_OFF") {
    def * = (id, uuid, occursOn, goalId, description, safetyNet, worldView, estimate, status, createdOn, lastModified, lastPerformed) <> (ScheduledOneOffRow.tupled, ScheduledOneOffRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(occursOn), goalId, Rep.Some(description), Rep.Some(safetyNet), Rep.Some(worldView), Rep.Some(estimate), Rep.Some(status), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> ScheduledOneOffRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column OCCURS_ON SqlType(TIMESTAMP) */
    val occursOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("OCCURS_ON")
    /** Database column GOAL_ID SqlType(VARCHAR), Length(36,true) */
    val goalId: Rep[Option[String]] = column[Option[String]]("GOAL_ID", O.Length(36,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column SAFETY_NET SqlType(TINYINT) */
    val safetyNet: Rep[Byte] = column[Byte]("SAFETY_NET")
    /** Database column WORLD_VIEW SqlType(TINYINT) */
    val worldView: Rep[Byte] = column[Byte]("WORLD_VIEW")
    /** Database column ESTIMATE SqlType(BIGINT) */
    val estimate: Rep[Long] = column[Long]("ESTIMATE")
    /** Database column STATUS SqlType(VARCHAR), Length(36,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(36,varying=true))
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_PERFORMED SqlType(TIMESTAMP) */
    val lastPerformed: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_PERFORMED")

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_16) */
    val index1 = index("CONSTRAINT_INDEX_16", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table ScheduledOneOffTable */
  lazy val ScheduledOneOffTable = new TableQuery(tag => new ScheduledOneOffTable(tag))

  /** Entity class storing rows of table SkillCategoryTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param name Database column NAME SqlType(VARCHAR), Length(256,true)
   *  @param parentCategory Database column PARENT_CATEGORY SqlType(BIGINT)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP) */
  case class SkillCategoryRow(id: Long, uuid: String, name: String, parentCategory: Option[Long], createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching SkillCategoryRow objects using plain SQL queries */
  implicit def GetResultSkillCategoryRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Long]], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]]): GR[SkillCategoryRow] = GR{
    prs => import prs._
    SkillCategoryRow.tupled((<<[Long], <<[String], <<[String], <<?[Long], <<[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table SKILL_CATEGORY. Objects of this class serve as prototypes for rows in queries. */
  class SkillCategoryTable(_tableTag: Tag) extends profile.api.Table[SkillCategoryRow](_tableTag, "SKILL_CATEGORY") {
    def * = (id, uuid, name, parentCategory, createdOn, lastModified) <> (SkillCategoryRow.tupled, SkillCategoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(name), parentCategory, Rep.Some(createdOn), lastModified).shaped.<>({r=>import r._; _1.map(_=> SkillCategoryRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column NAME SqlType(VARCHAR), Length(256,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(256,varying=true))
    /** Database column PARENT_CATEGORY SqlType(BIGINT) */
    val parentCategory: Rep[Option[Long]] = column[Option[Long]]("PARENT_CATEGORY")
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")

    /** Foreign key referencing SkillCategoryTable (database name CATEGORY_FK1) */
    lazy val skillCategoryTableFk = foreignKey("CATEGORY_FK1", parentCategory, SkillCategoryTable)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_3D) */
    val index1 = index("CONSTRAINT_INDEX_3D", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table SkillCategoryTable */
  lazy val SkillCategoryTable = new TableQuery(tag => new SkillCategoryTable(tag))

  /** Entity class storing rows of table SkillTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param name Database column NAME SqlType(VARCHAR), Length(256,true)
   *  @param parentCategory Database column PARENT_CATEGORY SqlType(BIGINT)
   *  @param proficiency Database column PROFICIENCY SqlType(VARCHAR), Length(36,true)
   *  @param practisedHours Database column PRACTISED_HOURS SqlType(BIGINT)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastApplied Database column LAST_APPLIED SqlType(TIMESTAMP) */
  case class SkillRow(id: Long, uuid: String, name: String, parentCategory: Long, proficiency: String, practisedHours: Long, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastApplied: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching SkillRow objects using plain SQL queries */
  implicit def GetResultSkillRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Timestamp]]): GR[SkillRow] = GR{
    prs => import prs._
    SkillRow.tupled((<<[Long], <<[String], <<[String], <<[Long], <<[String], <<[Long], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table SKILL. Objects of this class serve as prototypes for rows in queries. */
  class SkillTable(_tableTag: Tag) extends profile.api.Table[SkillRow](_tableTag, "SKILL") {
    def * = (id, uuid, name, parentCategory, proficiency, practisedHours, createdOn, lastModified, lastApplied) <> (SkillRow.tupled, SkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(name), Rep.Some(parentCategory), Rep.Some(proficiency), Rep.Some(practisedHours), Rep.Some(createdOn), lastModified, lastApplied).shaped.<>({r=>import r._; _1.map(_=> SkillRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column NAME SqlType(VARCHAR), Length(256,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(256,varying=true))
    /** Database column PARENT_CATEGORY SqlType(BIGINT) */
    val parentCategory: Rep[Long] = column[Long]("PARENT_CATEGORY")
    /** Database column PROFICIENCY SqlType(VARCHAR), Length(36,true) */
    val proficiency: Rep[String] = column[String]("PROFICIENCY", O.Length(36,varying=true))
    /** Database column PRACTISED_HOURS SqlType(BIGINT) */
    val practisedHours: Rep[Long] = column[Long]("PRACTISED_HOURS")
    /** Database column CREATED_ON SqlType(TIMESTAMP) */
    val createdOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_ON")
    /** Database column LAST_MODIFIED SqlType(TIMESTAMP) */
    val lastModified: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_MODIFIED")
    /** Database column LAST_APPLIED SqlType(TIMESTAMP) */
    val lastApplied: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("LAST_APPLIED")

    /** Foreign key referencing SkillCategoryTable (database name CATEGORY_FK2) */
    lazy val skillCategoryTableFk = foreignKey("CATEGORY_FK2", parentCategory, SkillCategoryTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (uuid) (database name CONSTRAINT_INDEX_4B) */
    val index1 = index("CONSTRAINT_INDEX_4B", uuid, unique=true)
  }
  /** Collection-like TableQuery object for table SkillTable */
  lazy val SkillTable = new TableQuery(tag => new SkillTable(tag))

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

  /** Entity class storing rows of table ThreadMissionTable
   *  @param threadId Database column THREAD_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT) */
  case class ThreadMissionRow(threadId: Long, missionId: Long)
  /** GetResult implicit for fetching ThreadMissionRow objects using plain SQL queries */
  implicit def GetResultThreadMissionRow(implicit e0: GR[Long]): GR[ThreadMissionRow] = GR{
    prs => import prs._
    ThreadMissionRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table THREAD_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class ThreadMissionTable(_tableTag: Tag) extends profile.api.Table[ThreadMissionRow](_tableTag, "THREAD_MISSION") {
    def * = (threadId, missionId) <> (ThreadMissionRow.tupled, ThreadMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(threadId), Rep.Some(missionId)).shaped.<>({r=>import r._; _1.map(_=> ThreadMissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column THREAD_ID SqlType(BIGINT) */
    val threadId: Rep[Long] = column[Long]("THREAD_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")

    /** Foreign key referencing MissionTable (database name MISSION_FK3) */
    lazy val missionTableFk = foreignKey("MISSION_FK3", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing ThreadTable (database name THREAD_FK3) */
    lazy val threadTableFk = foreignKey("THREAD_FK3", threadId, ThreadTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (threadId,missionId) (database name CONSTRAINT_INDEX_2A) */
    val index1 = index("CONSTRAINT_INDEX_2A", (threadId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table ThreadMissionTable */
  lazy val ThreadMissionTable = new TableQuery(tag => new ThreadMissionTable(tag))

  /** Entity class storing rows of table ThreadRelationshipTable
   *  @param threadId Database column THREAD_ID SqlType(BIGINT)
   *  @param relationshipId Database column RELATIONSHIP_ID SqlType(BIGINT) */
  case class ThreadRelationshipRow(threadId: Long, relationshipId: Long)
  /** GetResult implicit for fetching ThreadRelationshipRow objects using plain SQL queries */
  implicit def GetResultThreadRelationshipRow(implicit e0: GR[Long]): GR[ThreadRelationshipRow] = GR{
    prs => import prs._
    ThreadRelationshipRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table THREAD_RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class ThreadRelationshipTable(_tableTag: Tag) extends profile.api.Table[ThreadRelationshipRow](_tableTag, "THREAD_RELATIONSHIP") {
    def * = (threadId, relationshipId) <> (ThreadRelationshipRow.tupled, ThreadRelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(threadId), Rep.Some(relationshipId)).shaped.<>({r=>import r._; _1.map(_=> ThreadRelationshipRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column THREAD_ID SqlType(BIGINT) */
    val threadId: Rep[Long] = column[Long]("THREAD_ID")
    /** Database column RELATIONSHIP_ID SqlType(BIGINT) */
    val relationshipId: Rep[Long] = column[Long]("RELATIONSHIP_ID")

    /** Foreign key referencing RelationshipTable (database name RELATIONSHIP_FK2) */
    lazy val relationshipTableFk = foreignKey("RELATIONSHIP_FK2", relationshipId, RelationshipTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing ThreadTable (database name THREAD_FK2) */
    lazy val threadTableFk = foreignKey("THREAD_FK2", threadId, ThreadTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (threadId,relationshipId) (database name CONSTRAINT_INDEX_34) */
    val index1 = index("CONSTRAINT_INDEX_34", (threadId, relationshipId), unique=true)
  }
  /** Collection-like TableQuery object for table ThreadRelationshipTable */
  lazy val ThreadRelationshipTable = new TableQuery(tag => new ThreadRelationshipTable(tag))

  /** Entity class storing rows of table ThreadSkillTable
   *  @param threadId Database column THREAD_ID SqlType(BIGINT)
   *  @param skillId Database column SKILL_ID SqlType(BIGINT)
   *  @param relevance Database column RELEVANCE SqlType(VARCHAR), Length(36,true)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class ThreadSkillRow(threadId: Long, skillId: Long, relevance: String, level: String)
  /** GetResult implicit for fetching ThreadSkillRow objects using plain SQL queries */
  implicit def GetResultThreadSkillRow(implicit e0: GR[Long], e1: GR[String]): GR[ThreadSkillRow] = GR{
    prs => import prs._
    ThreadSkillRow.tupled((<<[Long], <<[Long], <<[String], <<[String]))
  }
  /** Table description of table THREAD_SKILL. Objects of this class serve as prototypes for rows in queries. */
  class ThreadSkillTable(_tableTag: Tag) extends profile.api.Table[ThreadSkillRow](_tableTag, "THREAD_SKILL") {
    def * = (threadId, skillId, relevance, level) <> (ThreadSkillRow.tupled, ThreadSkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(threadId), Rep.Some(skillId), Rep.Some(relevance), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> ThreadSkillRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column THREAD_ID SqlType(BIGINT) */
    val threadId: Rep[Long] = column[Long]("THREAD_ID")
    /** Database column SKILL_ID SqlType(BIGINT) */
    val skillId: Rep[Long] = column[Long]("SKILL_ID")
    /** Database column RELEVANCE SqlType(VARCHAR), Length(36,true) */
    val relevance: Rep[String] = column[String]("RELEVANCE", O.Length(36,varying=true))
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing SkillTable (database name SKILL_FK2) */
    lazy val skillTableFk = foreignKey("SKILL_FK2", skillId, SkillTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing ThreadTable (database name THREAD_FK1) */
    lazy val threadTableFk = foreignKey("THREAD_FK1", threadId, ThreadTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (threadId,skillId) (database name CONSTRAINT_INDEX_B) */
    val index1 = index("CONSTRAINT_INDEX_B", (threadId, skillId), unique=true)
  }
  /** Collection-like TableQuery object for table ThreadSkillTable */
  lazy val ThreadSkillTable = new TableQuery(tag => new ThreadSkillTable(tag))

  /** Entity class storing rows of table ThreadTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param safetyNet Database column SAFETY_NET SqlType(TINYINT)
   *  @param worldView Database column WORLD_VIEW SqlType(TINYINT)
   *  @param performance Database column PERFORMANCE SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class ThreadRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, safetyNet: Byte, worldView: Byte, performance: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ThreadRow objects using plain SQL queries */
  implicit def GetResultThreadRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Byte], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[ThreadRow] = GR{
    prs => import prs._
    ThreadRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[Byte], <<[Byte], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table THREAD. Objects of this class serve as prototypes for rows in queries. */
  class ThreadTable(_tableTag: Tag) extends profile.api.Table[ThreadRow](_tableTag, "THREAD") {
    def * = (id, uuid, goalId, summary, description, safetyNet, worldView, performance, createdOn, lastModified, lastPerformed) <> (ThreadRow.tupled, ThreadRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(safetyNet), Rep.Some(worldView), Rep.Some(performance), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> ThreadRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column SAFETY_NET SqlType(TINYINT) */
    val safetyNet: Rep[Byte] = column[Byte]("SAFETY_NET")
    /** Database column WORLD_VIEW SqlType(TINYINT) */
    val worldView: Rep[Byte] = column[Byte]("WORLD_VIEW")
    /** Database column PERFORMANCE SqlType(VARCHAR), Length(36,true) */
    val performance: Rep[String] = column[String]("PERFORMANCE", O.Length(36,varying=true))
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
   *  @param parentId Database column PARENT_ID SqlType(VARCHAR), Length(36,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param order Database column ORDER SqlType(INTEGER)
   *  @param isDone Database column IS_DONE SqlType(TINYINT)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class TodoRow(id: Long, uuid: String, parentId: String, description: String, order: Int, isDone: Byte, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching TodoRow objects using plain SQL queries */
  implicit def GetResultTodoRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int], e3: GR[Byte], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[TodoRow] = GR{
    prs => import prs._
    TodoRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[Int], <<[Byte], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table TODO. Objects of this class serve as prototypes for rows in queries. */
  class TodoTable(_tableTag: Tag) extends profile.api.Table[TodoRow](_tableTag, "TODO") {
    def * = (id, uuid, parentId, description, order, isDone, createdOn, lastModified, lastPerformed) <> (TodoRow.tupled, TodoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), Rep.Some(parentId), Rep.Some(description), Rep.Some(order), Rep.Some(isDone), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> TodoRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column UUID SqlType(VARCHAR), Length(36,true) */
    val uuid: Rep[String] = column[String]("UUID", O.Length(36,varying=true))
    /** Database column PARENT_ID SqlType(VARCHAR), Length(36,true) */
    val parentId: Rep[String] = column[String]("PARENT_ID", O.Length(36,varying=true))
    /** Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true) */
    val description: Rep[String] = column[String]("DESCRIPTION", O.Length(2000,varying=true))
    /** Database column ORDER SqlType(INTEGER) */
    val order: Rep[Int] = column[Int]("ORDER")
    /** Database column IS_DONE SqlType(TINYINT) */
    val isDone: Rep[Byte] = column[Byte]("IS_DONE")
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

  /** Entity class storing rows of table WeaveMissionTable
   *  @param weaveId Database column WEAVE_ID SqlType(BIGINT)
   *  @param missionId Database column MISSION_ID SqlType(BIGINT) */
  case class WeaveMissionRow(weaveId: Long, missionId: Long)
  /** GetResult implicit for fetching WeaveMissionRow objects using plain SQL queries */
  implicit def GetResultWeaveMissionRow(implicit e0: GR[Long]): GR[WeaveMissionRow] = GR{
    prs => import prs._
    WeaveMissionRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table WEAVE_MISSION. Objects of this class serve as prototypes for rows in queries. */
  class WeaveMissionTable(_tableTag: Tag) extends profile.api.Table[WeaveMissionRow](_tableTag, "WEAVE_MISSION") {
    def * = (weaveId, missionId) <> (WeaveMissionRow.tupled, WeaveMissionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(weaveId), Rep.Some(missionId)).shaped.<>({r=>import r._; _1.map(_=> WeaveMissionRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column WEAVE_ID SqlType(BIGINT) */
    val weaveId: Rep[Long] = column[Long]("WEAVE_ID")
    /** Database column MISSION_ID SqlType(BIGINT) */
    val missionId: Rep[Long] = column[Long]("MISSION_ID")

    /** Foreign key referencing MissionTable (database name MISSION_FK4) */
    lazy val missionTableFk = foreignKey("MISSION_FK4", missionId, MissionTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing WeaveTable (database name WEAVE_FK3) */
    lazy val weaveTableFk = foreignKey("WEAVE_FK3", weaveId, WeaveTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (weaveId,missionId) (database name CONSTRAINT_INDEX_ED) */
    val index1 = index("CONSTRAINT_INDEX_ED", (weaveId, missionId), unique=true)
  }
  /** Collection-like TableQuery object for table WeaveMissionTable */
  lazy val WeaveMissionTable = new TableQuery(tag => new WeaveMissionTable(tag))

  /** Entity class storing rows of table WeaveRelationshipTable
   *  @param weaveId Database column WEAVE_ID SqlType(BIGINT)
   *  @param relationshipId Database column RELATIONSHIP_ID SqlType(BIGINT) */
  case class WeaveRelationshipRow(weaveId: Long, relationshipId: Long)
  /** GetResult implicit for fetching WeaveRelationshipRow objects using plain SQL queries */
  implicit def GetResultWeaveRelationshipRow(implicit e0: GR[Long]): GR[WeaveRelationshipRow] = GR{
    prs => import prs._
    WeaveRelationshipRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table WEAVE_RELATIONSHIP. Objects of this class serve as prototypes for rows in queries. */
  class WeaveRelationshipTable(_tableTag: Tag) extends profile.api.Table[WeaveRelationshipRow](_tableTag, "WEAVE_RELATIONSHIP") {
    def * = (weaveId, relationshipId) <> (WeaveRelationshipRow.tupled, WeaveRelationshipRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(weaveId), Rep.Some(relationshipId)).shaped.<>({r=>import r._; _1.map(_=> WeaveRelationshipRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column WEAVE_ID SqlType(BIGINT) */
    val weaveId: Rep[Long] = column[Long]("WEAVE_ID")
    /** Database column RELATIONSHIP_ID SqlType(BIGINT) */
    val relationshipId: Rep[Long] = column[Long]("RELATIONSHIP_ID")

    /** Foreign key referencing RelationshipTable (database name RELATIONSHIP_FK3) */
    lazy val relationshipTableFk = foreignKey("RELATIONSHIP_FK3", relationshipId, RelationshipTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing WeaveTable (database name WEAVE_FK2) */
    lazy val weaveTableFk = foreignKey("WEAVE_FK2", weaveId, WeaveTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (weaveId,relationshipId) (database name CONSTRAINT_INDEX_DF) */
    val index1 = index("CONSTRAINT_INDEX_DF", (weaveId, relationshipId), unique=true)
  }
  /** Collection-like TableQuery object for table WeaveRelationshipTable */
  lazy val WeaveRelationshipTable = new TableQuery(tag => new WeaveRelationshipTable(tag))

  /** Entity class storing rows of table WeaveSkillTable
   *  @param weaveId Database column WEAVE_ID SqlType(BIGINT)
   *  @param skillId Database column SKILL_ID SqlType(BIGINT)
   *  @param relevance Database column RELEVANCE SqlType(VARCHAR), Length(36,true)
   *  @param level Database column LEVEL SqlType(VARCHAR), Length(36,true) */
  case class WeaveSkillRow(weaveId: Long, skillId: Long, relevance: String, level: String)
  /** GetResult implicit for fetching WeaveSkillRow objects using plain SQL queries */
  implicit def GetResultWeaveSkillRow(implicit e0: GR[Long], e1: GR[String]): GR[WeaveSkillRow] = GR{
    prs => import prs._
    WeaveSkillRow.tupled((<<[Long], <<[Long], <<[String], <<[String]))
  }
  /** Table description of table WEAVE_SKILL. Objects of this class serve as prototypes for rows in queries. */
  class WeaveSkillTable(_tableTag: Tag) extends profile.api.Table[WeaveSkillRow](_tableTag, "WEAVE_SKILL") {
    def * = (weaveId, skillId, relevance, level) <> (WeaveSkillRow.tupled, WeaveSkillRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(weaveId), Rep.Some(skillId), Rep.Some(relevance), Rep.Some(level)).shaped.<>({r=>import r._; _1.map(_=> WeaveSkillRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column WEAVE_ID SqlType(BIGINT) */
    val weaveId: Rep[Long] = column[Long]("WEAVE_ID")
    /** Database column SKILL_ID SqlType(BIGINT) */
    val skillId: Rep[Long] = column[Long]("SKILL_ID")
    /** Database column RELEVANCE SqlType(VARCHAR), Length(36,true) */
    val relevance: Rep[String] = column[String]("RELEVANCE", O.Length(36,varying=true))
    /** Database column LEVEL SqlType(VARCHAR), Length(36,true) */
    val level: Rep[String] = column[String]("LEVEL", O.Length(36,varying=true))

    /** Foreign key referencing SkillTable (database name SKILL_FK3) */
    lazy val skillTableFk = foreignKey("SKILL_FK3", skillId, SkillTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing WeaveTable (database name WEAVE_FK1) */
    lazy val weaveTableFk = foreignKey("WEAVE_FK1", weaveId, WeaveTable)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)

    /** Uniqueness Index over (weaveId,skillId) (database name CONSTRAINT_INDEX_E5) */
    val index1 = index("CONSTRAINT_INDEX_E5", (weaveId, skillId), unique=true)
  }
  /** Collection-like TableQuery object for table WeaveSkillTable */
  lazy val WeaveSkillTable = new TableQuery(tag => new WeaveSkillTable(tag))

  /** Entity class storing rows of table WeaveTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param uuid Database column UUID SqlType(VARCHAR), Length(36,true)
   *  @param goalId Database column GOAL_ID SqlType(VARCHAR), Length(36,true)
   *  @param summary Database column SUMMARY SqlType(VARCHAR), Length(256,true)
   *  @param description Database column DESCRIPTION SqlType(VARCHAR), Length(2000,true)
   *  @param safetyNet Database column SAFETY_NET SqlType(TINYINT)
   *  @param worldView Database column WORLD_VIEW SqlType(TINYINT)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(36,true)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(36,true)
   *  @param createdOn Database column CREATED_ON SqlType(TIMESTAMP)
   *  @param lastModified Database column LAST_MODIFIED SqlType(TIMESTAMP)
   *  @param lastPerformed Database column LAST_PERFORMED SqlType(TIMESTAMP) */
  case class WeaveRow(id: Long, uuid: String, goalId: Option[String], summary: String, description: String, safetyNet: Byte, worldView: Byte, status: String, `type`: String, createdOn: java.sql.Timestamp, lastModified: Option[java.sql.Timestamp], lastPerformed: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching WeaveRow objects using plain SQL queries */
  implicit def GetResultWeaveRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Byte], e4: GR[java.sql.Timestamp], e5: GR[Option[java.sql.Timestamp]]): GR[WeaveRow] = GR{
    prs => import prs._
    WeaveRow.tupled((<<[Long], <<[String], <<?[String], <<[String], <<[String], <<[Byte], <<[Byte], <<[String], <<[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table WEAVE. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class WeaveTable(_tableTag: Tag) extends profile.api.Table[WeaveRow](_tableTag, "WEAVE") {
    def * = (id, uuid, goalId, summary, description, safetyNet, worldView, status, `type`, createdOn, lastModified, lastPerformed) <> (WeaveRow.tupled, WeaveRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(uuid), goalId, Rep.Some(summary), Rep.Some(description), Rep.Some(safetyNet), Rep.Some(worldView), Rep.Some(status), Rep.Some(`type`), Rep.Some(createdOn), lastModified, lastPerformed).shaped.<>({r=>import r._; _1.map(_=> WeaveRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column SAFETY_NET SqlType(TINYINT) */
    val safetyNet: Rep[Byte] = column[Byte]("SAFETY_NET")
    /** Database column WORLD_VIEW SqlType(TINYINT) */
    val worldView: Rep[Byte] = column[Byte]("WORLD_VIEW")
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
