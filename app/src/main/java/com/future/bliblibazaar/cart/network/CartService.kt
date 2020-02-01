package com.future.bliblibazaar.cart.network

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.cart.model.CartDto
import retrofit2.http.POST
import retrofit2.http.Path

interface CartService {

    @POST("carts/{productId}/{cartItemQty}")
    suspend fun addItemToCart(@Path("productId") productId: String, @Path("cartItemQty") cartItemQty: Int): BBResponse<CartDto>

}