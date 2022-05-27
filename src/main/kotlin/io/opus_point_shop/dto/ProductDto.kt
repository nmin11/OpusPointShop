package io.opus_point_shop.dto

data class ProductDto(
    var id: Long?,
    var name: String,
    var price: Int,
    var quantity: Int,
    var pointRate: Int,
    var owner: String
)
