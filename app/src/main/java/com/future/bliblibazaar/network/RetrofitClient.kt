package com.future.bliblibazaar.network

import com.future.bliblibazaar.network.service.AuthService
import com.future.bliblibazaar.network.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val API_URL = "http://10.0.2.2:9000/api/"

    fun createAuthService(): AuthService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AuthService::class.java)
    }

    fun createUserService(): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UserService::class.java)
    }

}