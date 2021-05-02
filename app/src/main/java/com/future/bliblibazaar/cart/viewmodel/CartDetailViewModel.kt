package com.future.bliblibazaar.cart.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.future.bliblibazaar.cart.model.CartItemDto
import com.future.bliblibazaar.order.model.OrderDto
import com.future.bliblibazaar.cart.repositories.CartRepository

class CartDetailViewModel : ViewModel() {
    val cartItemsLiveData = MutableLiveData<List<CartItemDto>>()
    val orderLiveData = MutableLiveData<OrderDto>()
    private val cartRepository = CartRepository()

    suspend fun getCartItem(): Boolean {
        val response = cartRepository.getCartItems()

        return if (response.code == "200") {
            cartItemsLiveData.postValue(response.data!!.cartItems)
            true
        } else {
            Log.e("ERROR", response.message)
            false
        }
    }

    suspend fun checkout(): Boolean {
        val response = cartRepository.checkout()

        return if (response.code == "200") {
            orderLiveData.postValue(response.data)
            true
        } else {
            Log.e("ERROR", response.message)
            false
        }
    }

}