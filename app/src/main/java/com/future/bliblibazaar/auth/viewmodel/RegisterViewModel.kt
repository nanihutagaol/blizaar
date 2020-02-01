package com.future.bliblibazaar.auth.viewmodel

import android.util.Log
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
            //TODO Register still error
            if(response.code == Constants.RESPONSE_SUCCESS) {
                Log.i("SUCCESS", "Successfully registered")
                registerLiveData.postValue(response.data)
            } else {
                Log.e("ERROR", "Error when register new account")
            }
        }
    }

}