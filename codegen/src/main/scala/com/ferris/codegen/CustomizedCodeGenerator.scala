package com.ferris.codegen

import Config._
import slick.jdbc.H2Profile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

object CustomizedCodeGenerator{
  def main(args: Array[String]): Unit = {
    Await.ready(
      codegen.map(_.writeToFile(
        "slick.jdbc.H2Profile",
        args(0),
        "com.ferris.planning.table",
        "Tables",
        "Tables.scala"
      )).recover { case e: Exception =>
        e.printStackTrace()
      },
      20.seconds
    )
  }

  val db = H2Profile.api.Database.forURL(url,driver=jdbcDriver)
  val codegen = db.run{
    H2Profile.defaultTables.flatMap( H2Profile.createModelBuilder(_,false).buildModel )
  }.map{ model =>
    new slick.codegen.SourceCodeGenerator(model)
  }
}
