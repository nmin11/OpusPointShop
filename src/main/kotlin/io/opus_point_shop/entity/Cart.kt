package io.opus_point_shop.entity

import lombok.Getter
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Getter
class Cart (customer: Customer) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne
    var customer: Customer = customer

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL])
    var products: MutableList<ProductCart> = mutableListOf()
}