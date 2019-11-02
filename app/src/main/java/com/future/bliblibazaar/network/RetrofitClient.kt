package com.future.bliblibazaar.network

import com.future.bliblibazaar.login.network.AuthService
import com.future.bliblibazaar.network.service.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    private val API_URL = "http://192.168.43.91:9000/api/"
    var client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS).build()

    fun createAuthService(): AuthService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AuthService::class.java)
    }

    fun createUserService(): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(UserService::class.java)
    }

    //TODO Interceptor
}