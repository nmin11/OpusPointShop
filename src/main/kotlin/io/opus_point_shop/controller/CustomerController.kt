package io.opus_point_shop.controller

import io.opus_point_shop.dto.ChargeMoneyDto
import io.opus_point_shop.entity.Customer
import io.opus_point_shop.service.CartService
import io.opus_point_shop.service.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(
    private val customerService: CustomerService,
    private val cartService: CartService,
) {
    @PostMapping("/customer")
    fun createCustomer(@RequestParam name: String): Customer? {
        return customerService.createCustomer(name)
    }

    @GetMapping("/all-customer")
    fun findAllCustomer(): MutableList<Customer> {
        return customerService.findAllCustomer()
    }

    @GetMapping("/customer")
    fun findByName(@RequestParam name: String): Customer? {
        return customerService.findByName(name)
    }

    @PatchMapping("/charge-money")
    fun chargeMoney(@RequestBody dto: ChargeMoneyDto) {
        customerService.chargeMoney(dto)
    }
}