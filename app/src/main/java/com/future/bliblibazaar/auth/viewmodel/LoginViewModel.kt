package com.future.bliblibazaar.auth.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.auth.model.AuthenticateRequest
import com.future.bliblibazaar.auth.model.AuthenticateResponse
import com.future.bliblibazaar.auth.repositories.AuthRepository
import com.future.bliblibazaar.util.Constants
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val loginLiveData = MutableLiveData<AuthenticateResponse>()
    private val authRepository = AuthRepository()

    suspend fun login(request: AuthenticateRequest): Boolean {
        val response = authRepository.login(request)

        return if (response.code == Constants.RESPONSE_SUCCESS) {
            loginLiveData.postValue(response.data)
            true
        } else {
            Log.e("ERROR", response.message)
            false
        }
    }

}