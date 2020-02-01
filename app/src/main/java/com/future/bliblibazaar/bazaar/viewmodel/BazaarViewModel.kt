package com.future.bliblibazaar.bazaar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.bazaar.model.BazaarDto
import com.future.bliblibazaar.bazaar.repositories.BazaarRepository
import kotlinx.coroutines.launch

class BazaarViewModel : ViewModel() {
    val bazaarsLiveData = MutableLiveData<List<BazaarDto>>()
    private val bazaarRepository = BazaarRepository()

    fun getActiveBazaars() {
        viewModelScope.launch {
            val response = bazaarRepository.getBazaars()

            if (response.code == "200") {
                bazaarsLiveData.postValue(response.data)
            } else {
                //TODO Throw error here
            }
        }
    }

}