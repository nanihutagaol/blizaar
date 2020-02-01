package com.future.bliblibazaar.auth.network

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.auth.model.AuthenticateRequest
import com.future.bliblibazaar.auth.model.AuthenticateResponse
import com.future.bliblibazaar.auth.model.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth")
    suspend fun login(@Body request: AuthenticateRequest): Response<AuthenticateResponse>

    @POST("users/customer/register")
    suspend fun register(@Body request: UserDto): BBResponse<UserDto>

}