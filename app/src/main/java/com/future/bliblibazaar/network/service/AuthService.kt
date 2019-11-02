package com.future.bliblibazaar.network.service

import com.future.bliblibazaar.webmodel.AuthenticateRequest
import com.future.bliblibazaar.webmodel.AuthenticateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth")
    fun login(@Body request: AuthenticateRequest): Call<AuthenticateResponse>

}