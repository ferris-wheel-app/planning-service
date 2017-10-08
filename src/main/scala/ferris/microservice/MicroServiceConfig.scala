package ferris.microservice

import com.typesafe.config.{Config, ConfigFactory}

trait MicroServiceConfigComponent {
  val config: MicroServiceConfig
}

class MicroServiceConfig(config: Config) {
  val port = config.getInt("http.port")
  val interface = config.getString("http.interface")
}

object MicroServiceConfig extends MicroServiceConfig(ConfigFactory.load())
