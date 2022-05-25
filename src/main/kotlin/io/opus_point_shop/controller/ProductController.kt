package io.opus_point_shop.controller

import io.opus_point_shop.entity.Product
import io.opus_point_shop.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ProductController(private val productService: ProductService) {
    @PostMapping("/product")
    fun registerProduct(name: String, price: Int, quantity: Int, owner: String) {
        productService.registerProduct(name, price, quantity, owner)
    }

    @GetMapping("/all-product")
    fun findAllProduct(): MutableList<Product> {
        return productService.findAllProduct()
    }

    @GetMapping("/product")
    fun findByName(@RequestParam name: String): Product? {
        return productService.findByName(name)
    }
}