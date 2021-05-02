package com.future.bliblibazaar.cart.model

import com.google.gson.annotations.SerializedName

data class CartDto (
    @field:SerializedName("userEmail")
    val userEmail: String,

    @field:SerializedName("cartItems")
    val cartItems: List<CartItemDto>
)