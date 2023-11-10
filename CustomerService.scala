/*package model.service

import model.entity.Customer
import persistence.CustomerRepository

import javax.inject._
import scala.concurrent.Future


  @Singleton
  class CustomerService @Inject()(customerRepository: CustomerRepository) {

    def getCustomersAll(): Future[Seq[Customer]] = {customerRepository.CustomerRepositoryH2.execute.find()}

    def createCustomer(customer: Customer): Future[Int] ={customerRepository.CustomerRepositoryH2.execute.crate(customer)}
  }
*/