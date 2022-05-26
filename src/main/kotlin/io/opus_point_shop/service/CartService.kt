package io.opus_point_shop.service

import io.opus_point_shop.dto.AddToCartDto
import io.opus_point_shop.entity.Cart
import io.opus_point_shop.entity.Customer
import io.opus_point_shop.entity.ProductCart
import io.opus_point_shop.repository.CartRepository
import io.opus_point_shop.repository.CustomerRepository
import io.opus_point_shop.repository.ProductCartRepository
import io.opus_point_shop.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val customerRepository: CustomerRepository,
    private val productRepository: ProductRepository,
    private val productCartRepository: ProductCartRepository
) {
    fun findByCustomer(customerId: Long): Cart? {
        val customer = customerRepository.findById(customerId).get()
        return cartRepository.findByCustomer(customer).get()
    }

    fun addToCart(dto: AddToCartDto) {
        val customer = customerRepository.findById(dto.customerId).get()
        val product = productRepository.findById(dto.productId).get()
        val cart = cartRepository.findByCustomer(customer).get()
        productCartRepository.save(ProductCart(product, cart, dto.quantity))
    }

    fun createCart(customer: Customer?) {
        customer?.let { Cart(it) }?.let { cartRepository.save(it) }
    }
}