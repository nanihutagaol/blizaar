package com.future.bliblibazaar.network

import android.content.Context.MODE_PRIVATE
import com.future.bliblibazaar.AppComponent
import com.future.bliblibazaar.auth.network.AuthService
import com.future.bliblibazaar.bazaar.network.BazaarService
import com.future.bliblibazaar.cart.network.CartService
import com.future.bliblibazaar.network.service.UserService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private val API_URL = "http://10.0.2.2:9000/api/"
    private var sharedPreferences =
        AppComponent.context?.getSharedPreferences("blibli_bazaar", MODE_PRIVATE)
    private var token = sharedPreferences?.getString("blizaar_token", "") ?: ""

    var client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    var clientWithAuthorization = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HeaderInterceptor(token))
        .build()

    fun createAuthService(): AuthService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AuthService::class.java)
    }

    fun createBazaarService(): BazaarService {
        val gson = GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(BazaarService::class.java)
    }

    fun createCartService(): CartService {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL).client(clientWithAuthorization)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CartService::class.java)
    }

}