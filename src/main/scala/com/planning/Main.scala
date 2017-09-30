package com.planning

import com.planning.db.H2TablesComponent
import com.planning.repo.H2RepositoryComponent
import com.planning.server.Server
import com.planning.service.DefaultServiceComponent

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App with H2RepositoryComponent with H2TablesComponent{

  import tables._
  import tables.profile.api._

  // Create an in-memory H2 database;
  val db = Database.forConfig("db")

  def freshTestData = Seq(
    MessageRow("Dave", "Hello, HAL. Do you read me, HAL?"),
    MessageRow("HAL",  "Affirmative, Dave. I read you."),
    MessageRow("Dave", "Open the pod bay doors, HAL."),
    MessageRow("HAL",  "I'm sorry, Dave. I'm afraid I can't do that.")
  )

  // An example query that selects a subset of messages:
  val halSays = messages.filter(_.sender === "HAL")

  // Helper method for running a query in this example file:
  def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 2 seconds)

  // Create the "messages" table:
  println("Creating database table")
  exec(messages.schema.create)

  // Create and insert the test data:
  println("\nInserting test data")
  exec(messages ++= freshTestData)

  // Run the test query and print the results:
  println("\nSelecting all messages:")
  exec( messages.result ) foreach { println }

  println("\nSelecting only messages from HAL:")
  exec( halSays.result ) foreach { println }
}
