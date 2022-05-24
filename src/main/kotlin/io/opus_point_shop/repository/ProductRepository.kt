package io.opus_point_shop.repository

import io.opus_point_shop.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
    fun findByName(name: String?): Product?
}