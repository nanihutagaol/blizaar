package com.future.bliblibazaar.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.auth.model.UserDto
import com.future.bliblibazaar.auth.repositories.AuthRepository
import com.future.bliblibazaar.util.Constants
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val registerLiveData = MutableLiveData<UserDto>()
    private val authRepository = AuthRepository()

    fun register(request: UserDto) {
        viewModelScope.launch {
            val response = authRepository.register(request)

            if (response.code == Constants.RESPONSE_SUCCESS) {
                registerLiveData.postValue(response.data)
            } else {
                //TODO Throw exception
            }
        }
    }

}