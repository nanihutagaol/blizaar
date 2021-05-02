package com.future.bliblibazaar.cart.network

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.cart.model.CartDto
import com.future.bliblibazaar.order.model.OrderDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartService {

    @GET("carts")
    suspend fun getCartItem(): BBResponse<CartDto>

    @POST("carts/{productId}/{cartItemQty}")
    suspend fun addItemToCart(@Path("productId") productId: String, @Path("cartItemQty") cartItemQty: Int): BBResponse<CartDto>

    @POST("carts/checkout")
    suspend fun checkout(): BBResponse<OrderDto>

}