package com.ferris.microservice.service

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.Materializer

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
