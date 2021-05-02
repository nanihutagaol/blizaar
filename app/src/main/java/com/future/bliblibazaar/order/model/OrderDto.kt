package com.future.bliblibazaar.order.model

import com.future.bliblibazaar.cart.model.CartItemDto
import com.google.gson.annotations.SerializedName

data class OrderDto(
    @field:SerializedName("orderId")
    val orderId: String,

    @field:SerializedName("userEmail")
    val userEmail: String,

    @field:SerializedName("orderDate")
    val orderDate: String,

    @field:SerializedName("orderItems")
    val orderItems: List<CartItemDto>,

    @field:SerializedName("orderTotalItem")
    val orderTotalItem: Int,

    @field:SerializedName("orderTotalPrice")
    val orderTotalPrice: Long,

    @field:SerializedName("orderStatus")
    val orderStatus: String
)