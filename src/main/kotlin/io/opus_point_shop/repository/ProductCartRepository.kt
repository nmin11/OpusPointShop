package io.opus_point_shop.repository

import io.opus_point_shop.entity.Cart
import io.opus_point_shop.entity.Product
import io.opus_point_shop.entity.ProductCart
import org.springframework.data.jpa.repository.JpaRepository

interface ProductCartRepository: JpaRepository<ProductCart, Long> {
    fun findAllByCart(cart: Cart): MutableList<ProductCart>
    fun findAllByCartAndProduct(cart: Cart, product: Product): MutableList<ProductCart>
    fun deleteAllByCartAndProduct(cart: Cart, product: Product)
}