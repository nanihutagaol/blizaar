package com.future.bliblibazaar.bazaar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.bazaar.model.BazaarItemDto
import com.future.bliblibazaar.bazaar.repositories.BazaarRepository
import com.future.bliblibazaar.cart.repositories.CartRepository
import kotlinx.coroutines.launch

class BazaarDetailViewModel : ViewModel() {
    val bazaarDetailLiveData = MutableLiveData<List<BazaarItemDto>>()
    private val bazaarRepository = BazaarRepository()
    private val cartRepository = CartRepository()

    fun getBazaarDetail(bazaarId: Long) {
        viewModelScope.launch {
            val response = bazaarRepository.getBazaarItems(bazaarId)

            if (response.code == "200") {
                bazaarDetailLiveData.postValue(response.data)
            } else {

            }
        }
    }

    fun addItemToCart(productId: String) {
        viewModelScope.launch {
            val response = cartRepository.addItemToCart(productId)

            if (response.code == "200") {

            } else {

            }
        }
    }

}