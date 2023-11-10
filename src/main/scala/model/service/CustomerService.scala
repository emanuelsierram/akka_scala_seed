package model.service

import model.entity.Customer
import persistence.CustomerRepository

import javax.inject._
import scala.concurrent.Future


@Singleton
class CustomerService @Inject()(customerRepository: CustomerRepository) {

  def getCustomersAll(): Future[Seq[Customer]] = customerRepository.getAllCustomers

  def createCustomer(customer: Customer): Future[Int] ={
    customerRepository.addCustomer(customer)}
}
