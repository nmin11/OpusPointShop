package io.opus_point_shop.service

import io.opus_point_shop.repository.CartRepository
import io.opus_point_shop.repository.CustomerRepository
import io.opus_point_shop.repository.ProductCartRepository
import io.opus_point_shop.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PurchaseService(
    private val customerRepository: CustomerRepository,
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
    private val productCartRepository: ProductCartRepository
) {
    fun purchaseByMoney(customerId: Long): ResponseEntity<Any> {
        val customer = customerRepository.findById(customerId)
        if (customer.isEmpty) {
            return ResponseEntity.status(400).body("해당 유저는 존재하지 않습니다.")
        }
        val cart = cartRepository.findByCustomer(customer.get()).get()
        val productCarts = productCartRepository.findAllByCart(cart)
        if (productCarts.size == 0) {
            return ResponseEntity.status(400).body("장바구니에 담긴 상품이 없습니다.")
        }
        var price = 0
        var point = 0
        for (productCart in productCarts) {
            val product = productCart.product
            if (product.quantity < productCart.quantity) {
                return ResponseEntity.status(400).body("상품 재고가 부족합니다.")
            }
            price += product.price * productCart.quantity
            point += product.price * productCart.quantity * product.pointRate / 100
            product.quantity -= productCart.quantity
            productRepository.save(product)
        }
        if (customer.get().money < price) {
            return ResponseEntity.status(400).body("고객님의 돈이 충분하지 않습니다.")
        }
        customer.get().money -= price
        customer.get().point += point
        customerRepository.save(customer.get())
        cart.products = mutableListOf()
        cartRepository.save(cart)
        return ResponseEntity.status(200).body("성공적으로 구매했습니다.")
    }

    fun purchaseByPoint(customerId: Long): ResponseEntity<Any> {
        val customer = customerRepository.findById(customerId).get()
        val cart = cartRepository.findByCustomer(customer).get()
        val productCarts = productCartRepository.findAllByCart(cart)
        var price = 0
        for (productCart in productCarts) {
            val product = productCart.product
            if (product.quantity < productCart.quantity) {
                return ResponseEntity.status(400).body("상품 재고가 부족합니다.")
            }
            price += product.price * productCart.quantity
            product.quantity -= productCart.quantity
            productRepository.save(product)
        }
        if (customer.point < price) {
            return ResponseEntity.status(400).body("고객님의 포인트가 충분하지 않습니다.")
        }
        customer.point -= price
        customerRepository.save(customer)
        cart.products = mutableListOf()
        cartRepository.save(cart)
        return ResponseEntity.status(200).body("성공적으로 구매했습니다.")
    }
}