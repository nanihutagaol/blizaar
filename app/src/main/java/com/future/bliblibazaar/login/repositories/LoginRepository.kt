package com.future.bliblibazaar.login.repositories

import com.future.bliblibazaar.login.model.AuthenticateRequest
import com.future.bliblibazaar.login.model.AuthenticateResponse
import com.future.bliblibazaar.network.RetrofitClient
import com.future.bliblibazaar.login.network.AuthService
import retrofit2.Response

class LoginRepository {
    private val authService: AuthService = RetrofitClient.createAuthService()

    suspend fun login(request: AuthenticateRequest): Response<AuthenticateResponse> {
        return authService.login(request)
    }

    fun updateToken(token: String){
        //TODO sharedpreference
    }

}