package com.future.bliblibazaar.auth.repositories

import com.future.bliblibazaar.BBResponse
import com.future.bliblibazaar.auth.model.AuthenticateRequest
import com.future.bliblibazaar.auth.model.AuthenticateResponse
import com.future.bliblibazaar.auth.model.UserDto
import com.future.bliblibazaar.auth.network.AuthService
import com.future.bliblibazaar.network.RetrofitClient
import retrofit2.Response

class AuthRepository {
    private val authService: AuthService = RetrofitClient.createAuthService()

    suspend fun login(request: AuthenticateRequest): Response<AuthenticateResponse> {
        return authService.login(request)
    }

    suspend fun register(request: UserDto): BBResponse<UserDto> {
        return authService.register(request)
    }

}