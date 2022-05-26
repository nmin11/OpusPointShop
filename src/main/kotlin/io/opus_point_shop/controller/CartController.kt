package io.opus_point_shop.controller

import io.opus_point_shop.dto.AddToCartDto
import io.opus_point_shop.entity.Cart
import io.opus_point_shop.service.CartService
import org.springframework.web.bind.annotation.*

@RestController
class CartController(private val cartService: CartService) {
    @GetMapping("/cart")
    fun findByCustomer(@RequestParam customerId: Long): Cart? {
        return cartService.findByCustomer(customerId)
    }

    @PostMapping("/add-to-cart")
    fun addToCart(@RequestBody dto: AddToCartDto) {
        return cartService.addToCart(dto)
    }
}