package io.opus_point_shop.service

import io.opus_point_shop.dto.ChargeMoneyDto
import io.opus_point_shop.entity.Cart
import io.opus_point_shop.entity.Customer
import io.opus_point_shop.repository.CartRepository
import io.opus_point_shop.repository.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val cartRepository: CartRepository
) {
    fun createCustomer(name: String): ResponseEntity<Any> {
        if (findByName(name) != null) {
            return ResponseEntity.status(400).body("이미 존재하는 유저 이름입니다.")
        }
        val customer = customerRepository.save(Customer(name))
        cartRepository.save(Cart(customer))
        return ResponseEntity.status(200).body(customer)
    }

    fun findAllCustomer(): MutableList<Customer> {
        return customerRepository.findAll()
    }

    fun findByName(name: String): Customer? {
        return customerRepository.findByName(name)
    }

    fun chargeMoney(dto: ChargeMoneyDto): ResponseEntity<Any> {
        val customer: Customer
        try {
            customer = customerRepository.findById(dto.customerId).get()
        } catch (e: Exception) {
            return ResponseEntity.status(400).body("존재하지 않는 회원입니다.")
        }
        customer.money += dto.amount
        customerRepository.save(customer)
        return ResponseEntity.status(200).body(customer)
    }
}