package com.planning.route

import akka.stream.Materializer
import com.planning.service.ServiceComponent

import scala.concurrent.ExecutionContext

trait Route {
  this: ServiceComponent =>

  implicit def routeEx: ExecutionContext
  implicit def mat: Materializer

}
