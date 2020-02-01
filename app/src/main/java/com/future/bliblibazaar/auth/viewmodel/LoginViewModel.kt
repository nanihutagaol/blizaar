package com.future.bliblibazaar.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.auth.model.AuthenticateRequest
import com.future.bliblibazaar.auth.model.AuthenticateResponse
import com.future.bliblibazaar.auth.repositories.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val loginLiveData = MutableLiveData<AuthenticateResponse>()
    private val authRepository = AuthRepository()

    fun login(request: AuthenticateRequest) {
        viewModelScope.launch {
            val response = authRepository.login(request)

            if (response.isSuccessful) {
                loginLiveData.postValue(response.body())
            } else {

            }
        }
    }

}