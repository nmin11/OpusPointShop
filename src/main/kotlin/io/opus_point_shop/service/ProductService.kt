package io.opus_point_shop.service

import io.opus_point_shop.entity.Product
import io.opus_point_shop.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun registerProduct(name: String, price: Int, quantity: Int, owner: String) {
        productRepository.save(Product(name, price, quantity, owner))
    }

    fun findAllProduct(): MutableList<Product> {
        return productRepository.findAll()
    }

    fun findByName(name: String): Product? {
        return productRepository.findByName(name)
    }
}