package io.opus_point_shop.service

import io.opus_point_shop.dto.RegisterProductDto
import io.opus_point_shop.entity.Product
import io.opus_point_shop.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun registerProduct(dto: RegisterProductDto) {
        productRepository.save(Product(dto.name, dto.price, dto.quantity, dto.owner))
    }

    fun findAllProduct(): MutableList<Product> {
        return productRepository.findAll()
    }

    fun findByName(name: String): Product? {
        return productRepository.findByName(name)
    }
}