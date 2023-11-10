
/*package persistence

import model.entity.Customer
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CustomerRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  object CustomerRepositoryH2 {
    private val customers = TableQuery[CustomerTable]
    implicit val execute: Repository[Customer] = new Repository[Customer] {
      override def crate(value: Customer): Future[Int] = db.run(customers+=value)

      override def find(): Future[Seq[Customer]] = db.run(customers.result)
    }

  }

  private class CustomerTable(tag: Tag) extends Table[Customer](tag, "CUSTOMERS") {
    def id = column[String]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def email = column[String]("EMAIL")

    def * = (id, name, email) <> ((Customer.apply _).tupled, Customer.unapply)
  }

}

*/