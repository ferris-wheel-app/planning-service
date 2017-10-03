package ferris.planning.server

import akka.http.scaladsl.server.Route
import ferris.microservice.MicroService
import ferris.planning.route.PlanningRoute
import ferris.planning.service.PlanningServiceComponent

abstract class PlanningServer extends MicroService with PlanningRoute {
  this: PlanningServiceComponent =>

  def route: Route = planningRoute
}
