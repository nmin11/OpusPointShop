package io.opus_point_shop.dto

data class AddToCartDto(
    var customerId: Long,
    var productId: Long,
    var quantity: Int
)
