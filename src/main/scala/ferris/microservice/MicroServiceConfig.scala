package ferris.microservice

import com.typesafe.config.{Config, ConfigFactory}

trait MicroServiceConfigComponent {
  val config: MicroServiceConfig
}

class MicroServiceConfig(config: Config) {
  val port = config.getInt("microservice.bind.port")
  val address = config.getString("microservice.bind.address")
}

object MicroServiceConfig extends MicroServiceConfig(ConfigFactory.load())
