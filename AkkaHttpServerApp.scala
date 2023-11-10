package configuration.app_object

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import controllers.CustomerController
import model.service.CustomerService
import persistence.CustomerRepository
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.guice.GuiceApplicationBuilder
import slick.basic.{BasicProfile, DatabaseConfig}

object AkkaHttpServerApp extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  private val host = "localhost"
  private val port = 8080

  val databaseConfigProvider = new DatabaseConfigProvider() {
    override def get[P <: BasicProfile]: DatabaseConfig[P] = ???
  }
  val customerRepository = new CustomerRepository(databaseConfigProvider)
  val customerService = new CustomerService(customerRepository)

  private val customerController = new CustomerController(customerService)

  // Start the Akka HTTP server
  Http().bindAndHandle(customerController.routes, host, port)
  println(s"Server online at http://$host:$port/")
}
