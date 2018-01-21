name := "planning-service"
version := "1.0"

/** main project containing main source code depending on slick and codegen project */
lazy val root = (project in file("."))
  .settings(rootSettings)
  .settings(sharedSettings)
  .settings(slick := slickCodeGenTask.value) // register manual sbt command)
  .settings(sourceGenerators in Compile += slickCodeGenTask.taskValue) // register automatic code generation on every compile, remove for only manual use)
  .settings(sourceManaged in Compile <<= baseDirectory { _ / generatedSourcesFolder })
  .dependsOn(codegen)


/** codegen project containing the customized code generator */
lazy val codegen = project
  .settings(sharedSettings)
  .settings(libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickV)

lazy val rootSettings = {
  scalaVersion := "2.12.1"

  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

  libraryDependencies ++= {
    val akkaV           = "2.4.16"
    val scalaTestV      = "3.0.1"
    val akkaHttpV       = "10.0.1"
    val mysqlConnectorV = "5.1.40"
    val flywayV         = "3.2.1"
    Seq(
      "com.typesafe.akka" %% "akka-actor"           % akkaV,
      "com.typesafe.akka" %% "akka-stream"          % akkaV,
      "com.typesafe.akka" %% "akka-http-core"       % akkaHttpV,
      "com.typesafe.akka" %% "akka-http"            % akkaHttpV,
      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpV,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
      "com.typesafe.akka" %% "akka-http-jackson"    % akkaHttpV,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpV,
      "mysql"             %  "mysql-connector-java" % mysqlConnectorV,
      "org.flywaydb"      %  "flyway-core"          % flywayV,
      "org.scalatest"     %% "scalatest"            % scalaTestV       % "test"
    )
  }
}

// shared sbt config between main project and codegen project
lazy val sharedSettings = Seq(
  scalaVersion := "2.12.1",
  scalacOptions := Seq("-feature", "-unchecked", "-deprecation"),
  libraryDependencies ++= Seq(
    "com.typesafe.slick"  %% "slick"          % slickV,
    "com.typesafe.slick"  %% "slick-hikaricp" % slickV,
    "org.slf4j"           %  "slf4j-nop"      % "1.7.10",
    "com.h2database"      %  "h2"             % "1.4.187"
  )
)

lazy val slickV = "3.2.0-M2"
lazy val generatedSourcesFolder = "src/generated-sources/scala"

// code generation task that calls the customized code generator
lazy val slick = taskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = Def.task {
  val dir = baseDirectory { _ / generatedSourcesFolder }.value
  val cp = (dependencyClasspath in Compile).value
  val r = (runner in Compile).value
  val s = streams.value
  val outputDir = dir.getPath // place generated files in sbt's managed sources folder
  toError(r.run("com.ferris.codegen.CustomizedCodeGenerator", cp.files, Array(outputDir), s.log))
  val fname = outputDir + "/com/ferris/planning/table/Tables.scala"
  Seq(file(fname))
}

Revolver.settings
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerExposedPorts := Seq(9000)
dockerEntrypoint := Seq("bin/%s" format executableScriptName.value, "-Dconfig.resource=docker.conf")

flywayUrl := "jdbc:mysql://localhost:3306/planning"

flywayUser := "root"
