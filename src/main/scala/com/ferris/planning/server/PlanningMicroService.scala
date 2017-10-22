package com.ferris.planning.server

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.ActorMaterializer
import com.ferris.microservice.MicroServiceConfig
import com.ferris.planning.db.MySQLTablesComponent
import com.ferris.planning.repo.MySQLPlanningRepositoryComponent
import com.ferris.planning.service.DefaultPlanningServiceComponent
import com.ferris.microservice.MicroServiceConfig
import com.ferris.planning.db.MySQLTablesComponent
import com.ferris.planning.repo.MySQLPlanningRepositoryComponent
import com.ferris.planning.service.DefaultPlanningServiceComponent

object PlanningMicroService extends PlanningServer
  with DefaultPlanningServiceComponent
  with MySQLPlanningRepositoryComponent
  with MySQLTablesComponent {
  override implicit lazy val system = ActorSystem()
  override implicit lazy val executor = system.dispatcher
  override implicit lazy val materializer = ActorMaterializer()
  override implicit val repoEc = scala.concurrent.ExecutionContext.global
  override implicit val routeEc = scala.concurrent.ExecutionContext.global

  override val logger = Logging(system, getClass)
  override val config = MicroServiceConfig

  val db = tables.profile.api.Database.forConfig("db")

  startUp()
}
