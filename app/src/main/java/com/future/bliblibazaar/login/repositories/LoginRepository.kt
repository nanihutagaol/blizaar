package com.future.bliblibazaar.login.repositories

import android.content.Context
import com.future.bliblibazaar.login.model.AuthenticateRequest
import com.future.bliblibazaar.login.model.AuthenticateResponse
import com.future.bliblibazaar.login.network.AuthService
import com.future.bliblibazaar.network.RetrofitClient
import com.future.bliblibazaar.util.SharedPreferencesManager
import retrofit2.Response

class LoginRepository() {
    private val authService: AuthService = RetrofitClient.createAuthService()

    suspend fun login(request: AuthenticateRequest): Response<AuthenticateResponse> {
        return authService.login(request)
    }

    fun updateToken(token: String){
//        if(SharedPreferencesManager.checkExistence(context, "blizaar_token")) {
//            SharedPreferencesManager.putCache(context, "blizaar_token", token)
//        }
    }

}