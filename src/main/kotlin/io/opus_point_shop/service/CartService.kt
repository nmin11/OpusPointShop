package io.opus_point_shop.service

import io.opus_point_shop.dto.AddToCartDto
import io.opus_point_shop.dto.RemoveFromCartDto
import io.opus_point_shop.entity.ProductCart
import io.opus_point_shop.repository.CartRepository
import io.opus_point_shop.repository.CustomerRepository
import io.opus_point_shop.repository.ProductCartRepository
import io.opus_point_shop.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CartService(
    private val cartRepository: CartRepository,
    private val customerRepository: CustomerRepository,
    private val productRepository: ProductRepository,
    private val productCartRepository: ProductCartRepository
) {
    fun findByCustomer(customerId: Long): MutableList<Pair<String, Int>> {
        val customer = customerRepository.findById(customerId).get()
        val cart = cartRepository.findByCustomer(customer).get()
        val productCarts = productCartRepository.findAllByCart(cart)
        val list: MutableList<Pair<String, Int>> = mutableListOf()
        for (productCart in productCarts) {
            list.add(Pair(productCart.product.name, productCart.quantity))
        }
        return list
    }

    fun addToCart(dto: AddToCartDto): ResponseEntity<Any> {
        val customer = customerRepository.findById(dto.customerId)
        if (customer.isEmpty) {
            return ResponseEntity.status(400).body("해당 유저는 존재하지 않습니다.")
        }
        val product = productRepository.findById(dto.productId)
        if (product.isEmpty) {
            return ResponseEntity.status(400).body("해당 상품이 존재하지 않습니다.")
        }
        val cart = cartRepository.findByCustomer(customer.get()).get()
        if (product.get().quantity < dto.quantity) {
            return ResponseEntity.status(400).body("상품 재고가 부족합니다.")
        }
        productCartRepository.save(ProductCart(product.get(), cart, dto.quantity))
        val result: Triple<String, String, Int> = Triple(customer.get().name, product.get().name, dto.quantity)
        return ResponseEntity.status(200).body(result)
    }

    fun removeFromCart(dto: RemoveFromCartDto): ResponseEntity<String> {
        val customer = customerRepository.findById(dto.customerId)
        if (customer.isEmpty) {
            return ResponseEntity.status(400).body("해당 유저는 존재하지 않습니다.")
        }
        val product = productRepository.findById(dto.productId)
        if (product.isEmpty) {
            return ResponseEntity.status(400).body("해당 상품이 존재하지 않습니다.")
        }
        val cart = cartRepository.findByCustomer(customer.get()).get()
        if (productCartRepository.findAllByCartAndProduct(cart, product.get()).size == 0) {
            return ResponseEntity.status(400).body("삭제할 항목이 없습니다.")
        }
        productCartRepository.deleteAllByCartAndProduct(cart, product.get())
        return ResponseEntity.status(200).body("장바구니에서 삭제되었습니다.")
    }
}