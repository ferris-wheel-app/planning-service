name := "planning-rest-contract"

organization := "com.ferris"

version := "0.0.1"

scalaVersion in ThisBuild := "2.12.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  Seq(
    "com.ferris"        %% "ferris-http-microservice"   % dependencies.ferrisMicroserviceV,
    "com.ferris"        %% "ferris-json-utils"          % dependencies.ferrisJsonUtilsV,
    "com.github.fommil" %% "spray-json-shapeless"       % dependencies.fommilV,
    "org.scalatest"     %% "scalatest"                  % dependencies.scalaTestV       % Test
  )
}

lazy val dependencies = new {
  val ferrisMicroserviceV         = "0.0.1"
  val ferrisJsonUtilsV            = "0.0.2"
  val ferrisClientV               = "0.0.1"
  val ferrisCommonV               = "0.0.5"
  val fommilV                     = "1.4.0"
  val scalaTestV                  = "3.0.1"
}
