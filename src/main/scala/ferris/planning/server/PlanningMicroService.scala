package ferris.planning.server

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.ActorMaterializer
import ferris.microservice.{MicroService, MicroServiceConfig}
import ferris.planning.route.PlanningRoute

object PlanningMicroService extends MicroService with PlanningRoute {
  override implicit lazy val system = ActorSystem()
  override implicit lazy val executor = system.dispatcher
  override implicit lazy val materializer = ActorMaterializer()

  override val logger = Logging(system, getClass)
  override val config = MicroServiceConfig
}

//object PlanningMicroService extends App with H2PlanningRepositoryComponent with H2TablesComponent{
//
//  import tables._
//  import tables.profile.api._
//
//  // Create an in-memory H2 database;
//  val db = Database.forConfig("db")
//
//  def freshTestData = Seq(
//    MessageRow("Dave", "Hello, HAL. Do you read me, HAL?"),
//    MessageRow("HAL",  "Affirmative, Dave. I read you."),
//    MessageRow("Dave", "Open the pod bay doors, HAL."),
//    MessageRow("HAL",  "I'm sorry, Dave. I'm afraid I can't do that.")
//  )
//
//  // An example query that selects a subset of messages:
//  val halSays = messages.filter(_.sender === "HAL")
//
//  // Helper method for running a query in this example file:
//  def exec[T](program: DBIO[T]): T = Await.result(db.run(program), 2 seconds)
//
//  // Create the "messages" table:
//  println("Creating database table")
//  exec(messages.schema.create)
//
//  // Create and insert the test data:
//  println("\nInserting test data")
//  exec(messages ++= freshTestData)
//
//  // Run the test query and print the results:
//  println("\nSelecting all messages:")
//  exec( messages.result ) foreach { println }
//
//  println("\nSelecting only messages from HAL:")
//  exec( halSays.result ) foreach { println }
//}
