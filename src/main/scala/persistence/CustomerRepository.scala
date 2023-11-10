package persistence

import model.entity.Customer
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class CustomerRepository {
  val profile: JdbcProfile = slick.jdbc.MySQLProfile
  import profile.api._

  val db: Database = Database.forConfig("myPlay.db")

  // Define la tabla Customers
  private class Customers(tag: Tag) extends Table[Customer](tag, "customers") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def email = column[String]("email")
    def * = (id, name, email) <> (Customer.tupled, Customer.unapply)
  }

  private val customers = TableQuery[Customers]

  // Métodos de repositorio
  def getAllCustomers: Future[Seq[Customer]] = db.run(customers.result)
  def getCustomerById(id: Int): Future[Option[Customer]] = db.run(customers.filter(_.id === id).result.headOption)
  def addCustomer(customer: Customer): Future[Int] = db.run(customers returning customers.map(_.id) += customer)
  def updateCustomer(customer: Customer): Future[Int] = db.run(customers.filter(_.id === customer.id).map(c => (c.name, c.email)).update((customer.name, customer.email)))
  def deleteCustomer(id: Int): Future[Int] = db.run(customers.filter(_.id === id).delete)
}