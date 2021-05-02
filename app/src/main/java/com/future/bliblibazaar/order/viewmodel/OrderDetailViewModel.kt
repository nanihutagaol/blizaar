package com.future.bliblibazaar.order.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.order.model.OrderDto
import com.future.bliblibazaar.order.repositories.OrderRepository
import com.future.bliblibazaar.util.Constants
import kotlinx.coroutines.launch

class OrderDetailViewModel : ViewModel() {
    val orderDetailLiveData = MutableLiveData<OrderDto>()
    val orderRepository = OrderRepository()

    fun getOrder(orderId: String) {
        viewModelScope.launch {
            val response = orderRepository.getOrder(orderId)

            if (response.code == Constants.RESPONSE_SUCCESS) {
                orderDetailLiveData.postValue(response.data)
            } else {
                Log.e("ERROR", response.message)
            }
        }
    }

}