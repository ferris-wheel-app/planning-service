name := "planning-service"
version := "1.0"
scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.11.8", "2.12.3")
resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"

libraryDependencies ++= {
  val akkaV = "10.0.10"
  val scalaMajorMinorVersion = "2.11"
  val scalaTestV = "3.0.4"
  val slickVersion = "3.2.1"
  val circeV = "0.8.0"
  val mysqlConnectorVersion = "5.1.34"
  Seq(
    "com.typesafe.akka" %% "akka-http-core" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaV,
    "de.heikoseeberger" % s"akka-http-circe_${scalaMajorMinorVersion}" % "1.19.0-M2",

    "com.typesafe.slick" % s"slick_${scalaMajorMinorVersion}" % slickVersion,
    "com.typesafe.slick" % s"slick-hikaricp_${scalaMajorMinorVersion}" % "3.1.0",
    "mysql" % "mysql-connector-java" % mysqlConnectorVersion,
    "org.postgresql" % "postgresql" % "42.1.4",
    "com.h2database" % "h2" % "1.4.191",
    "org.flywaydb" % "flyway-core" % "4.2.0",

    "com.zaxxer" % "HikariCP" % "2.7.0",
    "org.slf4j" % "slf4j-nop" % "1.7.25",

    "io.circe" %% "circe-core" % circeV,
    "io.circe" %% "circe-generic" % circeV,
    "io.circe" %% "circe-parser" % circeV,

    "org.scalatest" %% "scalatest" % scalaTestV % "test",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaV % "test",
    "ru.yandex.qatools.embed" % "postgresql-embedded" % "2.4" % "test"
  )
}

Revolver.settings
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerExposedPorts := Seq(9000)
dockerEntrypoint := Seq("bin/%s" format executableScriptName.value, "-Dconfig.resource=docker.conf")

flywayUrl := "jdbc:mysql://localhost:3306/planning"

flywayUser := "root"
