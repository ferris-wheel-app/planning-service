package com.ferris.planning.repo

import com.ferris.planning.db.H2TablesComponent

import scala.concurrent.ExecutionContext
import scala.util.Random

trait H2PlanningRepositoryComponent extends SqlPlanningRepositoryComponent with H2TablesComponent {

  override implicit val repoEc = scala.concurrent.ExecutionContext.Implicits.global

  val randomSchemaName: String = "s" + Random.nextInt().toString.drop(1)
  val jdbcUrl: String = s"jdbc:h2:mem:$randomSchemaName;MODE=MySQL;DATABASE_TO_UPPER=false;INIT=CREATE SCHEMA IF NOT EXISTS $randomSchemaName;DB_CLOSE_DELAY=-1"
  import tables.profile.api._
  override val db: Database = Database.forURL(jdbcUrl, driver = "org.h2.Driver")

  override val repo = new SqlPlanningRepository
}
