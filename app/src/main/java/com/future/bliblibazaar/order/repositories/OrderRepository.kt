package com.future.bliblibazaar.order.repositories

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.network.RetrofitClient
import com.future.bliblibazaar.order.model.OrderDto
import com.future.bliblibazaar.order.model.PageableOrderDto

class OrderRepository {
    private val orderService = RetrofitClient.createOrderService()

    suspend fun getUserOrder(): BBResponse<PageableOrderDto> {
        return orderService.getUserOrder()
    }

    suspend fun getOrder(orderId: String): BBResponse<OrderDto> {
        return orderService.getOrder(orderId)
    }

}