package io.opus_point_shop.controller

import io.opus_point_shop.dto.ChargeMoneyDto
import io.opus_point_shop.service.CustomerService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class CustomerController(private val customerService: CustomerService) {
    @PostMapping("/customer")
    fun createCustomer(name: String) {
        customerService.createCustomer(name)
    }

    @GetMapping("/all-customer")
    fun findAllCustomer() {
        customerService.findAllCustomer()
    }

    @GetMapping("/customer")
    fun findByName(@RequestParam name: String) {
        customerService.findByName(name)
    }

    @PatchMapping("charge-money")
    fun chargeMoney(@RequestBody dto: ChargeMoneyDto) {
        customerService.chargeMoney(dto)
    }
}