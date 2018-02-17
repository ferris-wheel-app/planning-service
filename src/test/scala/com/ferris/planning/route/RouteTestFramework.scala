package com.ferris.planning.route

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.ActorMaterializer
import com.ferris.microservice.exceptions.ApiExceptionFormats
import com.ferris.microservice.service.MicroServiceConfig
import com.ferris.planning.server.PlanningServer
import com.ferris.planning.service.PlanningServiceComponent
import org.scalatest.{FunSpec, Matchers, Outcome}
import org.scalatest.mockito.MockitoSugar.mock

trait MockPlanningServiceComponent extends PlanningServiceComponent {
  override val planningService: PlanningService = mock[PlanningService]
}

trait RouteTestFramework extends FunSpec with ScalatestRouteTest with PlanningRestFormats with ApiExceptionFormats with Matchers {

  var testServer: PlanningServer with PlanningServiceComponent = _
  var route: Route = _

  override def withFixture(test: NoArgTest): Outcome = {
    testServer = new PlanningServer with MockPlanningServiceComponent {
      override implicit lazy val system = ActorSystem()

      override implicit lazy val executor = system.dispatcher

      override implicit lazy val materializer = ActorMaterializer()

      override implicit val routeEc = scala.concurrent.ExecutionContext.global

      override val config = MicroServiceConfig

      override val logger: LoggingAdapter = Logging(system, getClass)
    }

    super.withFixture(test)
  }
}
