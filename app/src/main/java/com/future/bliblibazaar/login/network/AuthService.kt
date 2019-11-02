package com.future.bliblibazaar.login.network

import com.future.bliblibazaar.login.model.AuthenticateRequest
import com.future.bliblibazaar.login.model.AuthenticateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth")
    suspend fun login(@Body request: AuthenticateRequest): Response<AuthenticateResponse>

}