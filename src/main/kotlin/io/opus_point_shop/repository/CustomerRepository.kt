package io.opus_point_shop.repository

import io.opus_point_shop.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, Long> {
    fun findByName(name: String): Customer?
}