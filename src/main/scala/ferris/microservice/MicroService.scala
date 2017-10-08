package ferris.microservice

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import akka.stream.scaladsl.Flow
import com.typesafe.config.Config

import scala.concurrent.ExecutionContextExecutor

trait MicroService extends App with MicroServiceConfigComponent {
  implicit val system: ActorSystem
  implicit val executor: ExecutionContextExecutor
  implicit val materializer: Materializer

  val config: MicroServiceConfig

  val logger: LoggingAdapter

  def route: Route

  def startUp() = {
    Http().bindAndHandle(route, config.interface, config.port)
  }
}
