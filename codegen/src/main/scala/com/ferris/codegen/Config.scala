package com.ferris.codegen

object Config{
  val preScript = "codegen/src/main/resources/create_enum_for_h2.sql"
  val scriptDir = "src/main/resources/db/migration/"
  val initScripts = Seq("V1__starting_schema.sql")
  val url = "jdbc:h2:mem:base;INIT=" +
    s"runscript from '$preScript'\\;" +
    initScripts.map(s"runscript from '$scriptDir"+_+"'").mkString("\\;")
  val jdbcDriver =  "org.h2.Driver"
  val slickProfile = slick.jdbc.H2Profile
}
