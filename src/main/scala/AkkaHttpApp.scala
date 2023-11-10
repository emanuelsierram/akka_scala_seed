package configuration.app_object
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import com.typesafe.config.ConfigFactory
import model.entity.{Customer, CustomerJsonProtocol}
import model.service.{CustomerService}
import persistence.CustomerRepository

import scala.io.StdIn
import scala.reflect.ClassTag

object AkkaHttpApp extends App with CustomerJsonProtocol {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val host = "localhost"
  val port = 8080

  val customerRepository = new CustomerRepository()
  val customerService = new CustomerService(customerRepository)


  val route = path("customers") {
    post {
      entity(as[Customer]) { customer =>
        complete {
          customerService.createCustomer(customer).map(_ => "Customer created successfully")
        }
      }
    } ~
      get {
        complete {
          customerService.getCustomersAll()
        }
      }
  }

  val bindingFuture =Http().bindAndHandle(route, host, port)
  println(s"Server online at http://$host:$port/")

  StdIn.readLine()

  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}