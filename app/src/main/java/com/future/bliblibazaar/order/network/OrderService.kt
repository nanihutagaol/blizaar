package com.future.bliblibazaar.order.network

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.order.model.OrderDto
import com.future.bliblibazaar.order.model.PageableOrderDto
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderService {

    @GET("orders/user")
    suspend fun getUserOrder(): BBResponse<PageableOrderDto>

    @GET("orders/{orderId}/search")
    suspend fun getOrder(@Path("orderId") orderId: String): BBResponse<OrderDto>

}