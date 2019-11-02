package com.future.bliblibazaar.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.login.model.AuthenticateRequest
import com.future.bliblibazaar.login.model.AuthenticateResponse
import com.future.bliblibazaar.login.repositories.LoginReporsitory
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val loginLiveData = MutableLiveData<AuthenticateResponse>()
    private val loginRepository = LoginReporsitory()


    fun login(request: AuthenticateRequest) {
        viewModelScope.launch {
            val response = loginRepository.login(request)
            if (response.isSuccessful) {

                loginLiveData.postValue(response.body())

                /**
                 * if token exist call repository.updateToken(string)
                 */
            } else {
                //TODO HTTP Failure
            }
        }
    }
}