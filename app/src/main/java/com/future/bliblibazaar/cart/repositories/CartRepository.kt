package com.future.bliblibazaar.cart.repositories

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.cart.model.CartDto
import com.future.bliblibazaar.network.RetrofitClient

class CartRepository {
    private val cartService = RetrofitClient.createCartService()

    suspend fun addItemToCart(productId: String): BBResponse<CartDto> {
        return cartService.addItemToCart(productId, 1)
    }

}