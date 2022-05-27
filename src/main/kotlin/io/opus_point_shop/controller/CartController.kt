package io.opus_point_shop.controller

import io.opus_point_shop.dto.AddToCartDto
import io.opus_point_shop.dto.RemoveFromCartDto
import io.opus_point_shop.service.CartService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CartController(private val cartService: CartService) {
    @GetMapping("/cart")
    fun findByCustomer(@RequestParam customerId: Long): MutableList<Pair<String, Int>> {
        return cartService.findByCustomer(customerId)
    }

    @PostMapping("/add-to-cart")
    fun addToCart(@RequestBody dto: AddToCartDto): ResponseEntity<Any> {
        return cartService.addToCart(dto)
    }

    @DeleteMapping("/remove-from-cart")
    fun removeFromCart(@RequestBody dto: RemoveFromCartDto): ResponseEntity<String> {
        return cartService.removeFromCart(dto)
    }
}