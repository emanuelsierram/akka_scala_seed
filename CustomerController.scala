package controllers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import model.entity.{Customer, CustomerJsonProtocol}
import model.service.CustomerService

import javax.inject.Inject
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import spray.json._
import akka.http.scaladsl.marshalling.PredefinedToEntityMarshallers._

class CustomerController @Inject()(customerService: CustomerService)(implicit exec: ExecutionContext) extends CustomerJsonProtocol {

  def routes: Route = {
    pathPrefix("customers") {
      path("create") {
        post {
          entity(as[Customer]) { customer =>
            onComplete(customerService.createCustomer(customer)) { _ =>
              complete("Customer created successfully")
            }
          }
        }
      } ~
        path("list") {
          get {
            complete(customerService.getCustomersAll())
          }
        }
    }
  }










  /*def getCustomers: Route = path("customers") {
    get {
      val customers = customerService.getUsersAll()
      complete(customers.map(_.toJson))
    }
  }

  def createCustomer: Route = path("customers") {
    post {
      entity(as[Customer]) { customer =>
        val result = customerService.createVisitor(customer)
        onComplete(result) {
          case Success(_) => complete(StatusCodes.Created)
          case Failure(ex) => complete(StatusCodes.InternalServerError, ex.getMessage)
        }
      }
    }
  }*/
}