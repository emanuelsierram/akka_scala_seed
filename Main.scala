package configuration.app_object


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import model.service.CustomerService
import persistence.CustomerRepository
import play.api.db.slick.DatabaseConfigProvider
import play.api.inject.guice.GuiceApplicationBuilder
import slick.basic.{BasicProfile, DatabaseConfig}

import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher


    val databaseConfigProvider = new DatabaseConfigProvider() {
      override def get[P <: BasicProfile]: DatabaseConfig[P] = ???
    }
    val customerRepository = new CustomerRepository(databaseConfigProvider)
    val customerService = new CustomerService(customerRepository)

    val route =
      path("hello") {
        get {
          complete("hOLA")

        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}