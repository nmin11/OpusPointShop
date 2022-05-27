package io.opus_point_shop.controller

import io.opus_point_shop.dto.ProductDto
import io.opus_point_shop.dto.RegisterProductDto
import io.opus_point_shop.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(private val productService: ProductService) {
    @PostMapping("/product")
    fun registerProduct(@RequestBody dto: RegisterProductDto): ResponseEntity<Any> {
        return productService.registerProduct(dto)
    }

    @GetMapping("/all-product")
    fun findAllProduct(): MutableList<ProductDto> {
        return productService.findAllProduct()
    }

    @GetMapping("/product")
    fun findByName(@RequestParam name: String): ProductDto? {
        return productService.findByName(name)
    }
}