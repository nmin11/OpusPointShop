package io.opus_point_shop.dto

data class RegisterProductDto(
    var name: String,
    var price: Int,
    var quantity: Int,
    var pointRate: Int,
    var owner: String
)
