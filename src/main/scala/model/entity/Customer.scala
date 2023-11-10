package model.entity

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol


case class Customer(id: Int, name: String, email: String)

trait CustomerJsonProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val customerFormat = jsonFormat3(Customer)
}

