package io.opus_point_shop.service

import io.opus_point_shop.dto.ChargeMoneyDto
import io.opus_point_shop.entity.Cart
import io.opus_point_shop.entity.Customer
import io.opus_point_shop.repository.CartRepository
import io.opus_point_shop.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val cartRepository: CartRepository
) {
    fun createCustomer(name: String): Customer? {
        val customer = customerRepository.save(Customer(name))
        cartRepository.save(Cart(customer))
        return customer
    }

    fun findAllCustomer(): MutableList<Customer> {
        return customerRepository.findAll()
    }

    fun findByName(name: String): Customer? {
        return customerRepository.findByName(name)
    }

    fun chargeMoney(dto: ChargeMoneyDto) {
        var customer: Customer = customerRepository.findById(dto.customerId).get()
        customer.money += dto.amount
        customerRepository.save(customer)
    }
}