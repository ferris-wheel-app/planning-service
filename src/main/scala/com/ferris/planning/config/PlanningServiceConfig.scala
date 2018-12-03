package com.ferris.planning.config

import com.typesafe.config.{Config, ConfigFactory}

case class PlanningServiceConfig(acceptableProgress: Int) {
  require(acceptableProgress >= 0 && acceptableProgress <= 100)
}

object PlanningServiceConfig {
  def apply(config: Config): PlanningServiceConfig = {
    PlanningServiceConfig(config.getInt("planning-service.env.pyramid.acceptable-progress"))
  }
}

object DefaultPlanningServiceConfig {
  def apply: PlanningServiceConfig = PlanningServiceConfig(ConfigFactory.load())
}
