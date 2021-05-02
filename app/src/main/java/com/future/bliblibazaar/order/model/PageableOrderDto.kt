package com.future.bliblibazaar.order.model

import com.google.gson.annotations.SerializedName

data class PageableOrderDto(
    @field:SerializedName("totalPage")
    val totalPage: Int,

    @field:SerializedName("orders")
    val orders: List<OrderDto>
)
