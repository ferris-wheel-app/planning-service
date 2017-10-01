package ferris.planning.route

import akka.stream.Materializer
import ferris.planning.service.PlanningServiceComponent

import scala.concurrent.ExecutionContext

trait PlanningRoute {
  this: PlanningServiceComponent =>

  implicit def executionContext: ExecutionContext
  implicit def materializer: Materializer

}
