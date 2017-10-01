package ferris.planning.server

import ferris.planning.route.PlanningRoute
import ferris.planning.service.PlanningServiceComponent

abstract class PlanningServer extends PlanningRoute {
  this: PlanningServiceComponent =>
}
