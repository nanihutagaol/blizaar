package com.future.bliblibazaar.bazaar.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.bazaar.model.BazaarDto
import com.future.bliblibazaar.bazaar.repositories.BazaarRepository
import com.future.bliblibazaar.order.model.PageableOrderDto
import com.future.bliblibazaar.order.repositories.OrderRepository
import com.future.bliblibazaar.util.Constants
import kotlinx.coroutines.launch

class BazaarViewModel : ViewModel() {
    val bazaarsLiveData = MutableLiveData<List<BazaarDto>>()
    val orderLiveData = MutableLiveData<PageableOrderDto>()
    private val bazaarRepository = BazaarRepository()
    private val orderRepository = OrderRepository()

    suspend fun getActiveBazaars(): Boolean {
        val response = bazaarRepository.getBazaars()

        return if (response.code == Constants.RESPONSE_SUCCESS) {
            bazaarsLiveData.postValue(response.data)
            true
        } else {
            Log.e("BLIZAAR_ERROR", "Unauthorized")
            false
        }
    }

    suspend fun getOrder(): Boolean {
        val response = orderRepository.getUserOrder()

        return if (response.code == Constants.RESPONSE_SUCCESS) {
            orderLiveData.postValue(response.data)
            true
        } else {
            Log.e("BLIZAAR_ERROR", "Unauthorized")
            false
        }
    }

}