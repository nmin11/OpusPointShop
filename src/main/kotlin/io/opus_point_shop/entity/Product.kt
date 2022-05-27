package io.opus_point_shop.entity

import io.opus_point_shop.dto.ProductDto
import lombok.Getter
import javax.persistence.*

@Entity
@Getter
class Product (name: String, price: Int, quantity: Int, pointRate: Int, owner: String) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(unique = true)
    var name: String = name
    var price: Int = price
    var quantity: Int = quantity
    var pointRate: Int = pointRate
    var owner: String = owner

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    var carts: MutableList<ProductCart> = mutableListOf()

    fun dtoToEntity(product: Product): ProductDto {
        return ProductDto(
            product.id, product.name, product.price, product.quantity, product.pointRate, product.owner
        )
    }
}