package io.opus_point_shop.repository

import io.opus_point_shop.entity.Cart
import io.opus_point_shop.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository: JpaRepository<Cart, Long> {
    fun findByCustomer(customer: Customer?): Cart?
}