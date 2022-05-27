package io.opus_point_shop.service

import io.opus_point_shop.repository.CartRepository
import io.opus_point_shop.repository.CustomerRepository
import io.opus_point_shop.repository.ProductCartRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PurchaseService(
    private val customerRepository: CustomerRepository,
    private val cartRepository: CartRepository,
    private val productCartRepository: ProductCartRepository
) {
    fun purchaseByMoney(customerId: Long) {
        val customer = customerRepository.findById(customerId).get()
        val cart = cartRepository.findByCustomer(customer).get()
        val productCarts = productCartRepository.findAllByCart(cart)
        var price = 0
        var point = 0
        for (productCart in productCarts) {
            price += productCart.product.price * productCart.quantity
            point += productCart.product.price * productCart.quantity * productCart.product.pointRate / 100
        }
        customer.money -= price
        customer.point += point
        customerRepository.save(customer)
        cart.products = mutableListOf()
        cartRepository.save(cart)
    }

    fun purchaseByPoint(customerId: Long) {
        val customer = customerRepository.findById(customerId).get()
        val cart = cartRepository.findByCustomer(customer).get()
        val productCarts = productCartRepository.findAllByCart(cart)
        var price = 0
        for (productCart in productCarts) {
            price += productCart.product.price * productCart.quantity
        }
        customer.point -= price
        customerRepository.save(customer)
        cart.products = mutableListOf()
        cartRepository.save(cart)
    }
}