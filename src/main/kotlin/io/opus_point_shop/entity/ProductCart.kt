package io.opus_point_shop.entity

import lombok.Getter
import javax.persistence.*

@Entity
@Getter
class ProductCart (product: Product, cart: Cart, quantity: Int) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var quantity: Int = quantity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product = product

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    var cart: Cart = cart
}