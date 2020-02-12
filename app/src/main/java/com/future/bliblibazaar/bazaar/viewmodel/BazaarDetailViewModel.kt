package com.future.bliblibazaar.bazaar.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.bazaar.model.BazaarItemDto
import com.future.bliblibazaar.bazaar.repositories.BazaarRepository
import com.future.bliblibazaar.cart.repositories.CartRepository
import kotlinx.coroutines.launch

class BazaarDetailViewModel : ViewModel() {
    val bazaarDetailLiveData = MutableLiveData<List<BazaarItemDto>>()
    val addItemLiveData = MutableLiveData<Boolean>()
    private val bazaarRepository = BazaarRepository()
    private val cartRepository = CartRepository()

    fun getBazaarDetail(bazaarId: Long) {
        viewModelScope.launch {
            val response = bazaarRepository.getBazaarItems(bazaarId)

            if (response.code == "200") {
                bazaarDetailLiveData.postValue(response.data)
            } else {
                Log.e("ERROR", response.message)
            }
        }
    }

    suspend fun addItemToCart(productId: String): Boolean {
        val response = cartRepository.addItemToCart(productId)

        return if (response.code == "200") {
            addItemLiveData.postValue(true)
            true
        } else {
            Log.e("ERROR", response.message)
            false
        }
    }

}