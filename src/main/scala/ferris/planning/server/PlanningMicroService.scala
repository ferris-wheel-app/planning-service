package ferris.planning.server

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.ActorMaterializer
import ferris.microservice.MicroServiceConfig
import ferris.planning.db.H2TablesComponent
import ferris.planning.repo.H2PlanningRepositoryComponent
import ferris.planning.service.DefaultPlanningServiceComponent

object PlanningMicroService extends PlanningServer
  with DefaultPlanningServiceComponent
  with H2PlanningRepositoryComponent
  with H2TablesComponent {
  override implicit lazy val system = ActorSystem()
  override implicit lazy val executor = system.dispatcher
  override implicit lazy val materializer = ActorMaterializer()
  override implicit val repoEc = scala.concurrent.ExecutionContext.global
  override implicit val routeEc = scala.concurrent.ExecutionContext.global

  override val logger = Logging(system, getClass)
  override val config = MicroServiceConfig

  override val db = tables.profile.api.Database.forConfig("db")

  startUp()
}
