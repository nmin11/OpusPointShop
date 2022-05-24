package io.opus_point_shop.entity

import lombok.Getter
import javax.persistence.*

@Entity
@Getter
class Product (name: String, price: Int, quantity: Int, owner: String) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(unique = true)
    var name: String = name
    var price: Int = price
    var quantity: Int = quantity
    var owner: String = owner

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    var carts: MutableList<ProductCart> = mutableListOf()
}