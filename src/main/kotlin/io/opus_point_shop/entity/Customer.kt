package io.opus_point_shop.entity

import lombok.Getter
import javax.persistence.*

@Entity
@Getter
class Customer (name: String) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(unique = true)
    var name: String = name
    var money: Int = 0
    var point: Int = 0
}