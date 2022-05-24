package io.opus_point_shop.service

import io.opus_point_shop.dto.ChargeMoneyDto
import io.opus_point_shop.entity.Customer
import io.opus_point_shop.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun createCustomer(name: String) {
        customerRepository.save(Customer(name))
    }

    fun findAllCustomer() {
        customerRepository.findAll()
    }

    fun findByName(name: String) {
        customerRepository.findByName(name)
    }

    fun chargeMoney(dto: ChargeMoneyDto) {
        var customer: Customer = customerRepository.findById(dto.customerId).get()
        customer.money = dto.amount
        customerRepository.save(customer)
    }
}