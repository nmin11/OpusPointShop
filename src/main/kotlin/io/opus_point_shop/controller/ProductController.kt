package io.opus_point_shop.controller

import io.opus_point_shop.dto.RegisterProductDto
import io.opus_point_shop.entity.Product
import io.opus_point_shop.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(private val productService: ProductService) {
    @PostMapping("/product")
    fun registerProduct(@RequestBody dto: RegisterProductDto) {
        productService.registerProduct(dto)
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