package com.future.bliblibazaar.login.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.login.model.AuthenticateRequest
import com.future.bliblibazaar.login.model.AuthenticateResponse
import com.future.bliblibazaar.login.repositories.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val loginLiveData = MutableLiveData<AuthenticateResponse>()
    private val loginRepository = LoginRepository()

    fun login(request: AuthenticateRequest) {
        viewModelScope.launch {
            val response = loginRepository.login(request)

            if (response.isSuccessful) {
                Log.i("Success", "Everything's good")
                loginLiveData.postValue(response.body())

                /**
                 * if token exist call repository.updateToken(string)
                 */
            } else {
                Log.e("ERROR", "Something wrong");
                //TODO HTTP Failure
            }
        }
    }

}