package com.future.bliblibazaar.network.service

import com.future.bliblibazaar.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("users/customer/register")
    fun register(request: User): Call<User>

    @GET("users")
    fun getUsers(): Call<List<User>>

}