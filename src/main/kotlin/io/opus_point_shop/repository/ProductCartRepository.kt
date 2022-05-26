package io.opus_point_shop.repository

import io.opus_point_shop.entity.ProductCart
import org.springframework.data.jpa.repository.JpaRepository

interface ProductCartRepository: JpaRepository<ProductCart, Long> {
}