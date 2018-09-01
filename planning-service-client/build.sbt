name := "planning-service-client"

organization := "com.ferris"

version := "multi-project-publishing-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-actor"                 % dependencies.akkaV,
    "com.typesafe.akka" %% "akka-stream"                % dependencies.akkaV,
    "com.typesafe.akka" %% "akka-http-core"             % dependencies.akkaHttpV,
    "com.typesafe.akka" %% "akka-http"                  % dependencies.akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json"       % dependencies.akkaHttpV,
    "com.ferris"        %% "ferris-http-service-client" % dependencies.ferrisClientV,
    "com.ferris"        %% "planning-rest-contract"     % dependencies.planningRestV,
    "org.scalatest"     %% "scalatest"                  % dependencies.scalaTestV       % Test,
    "org.mockito"       %  "mockito-all"                % dependencies.mockitoV         % Test
  )
}

lazy val dependencies = new {
  val akkaV                       = "2.4.16"
  val akkaHttpV                   = "10.0.1"
  val ferrisClientV               = "0.0.1"
  val planningRestV               = "multi-project-publishing-SNAPSHOT"
  val scalaTestV                  = "3.0.1"
  val mockitoV                    = "1.10.19"
}
