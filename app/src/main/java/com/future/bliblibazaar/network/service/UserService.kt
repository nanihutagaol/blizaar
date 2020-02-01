package com.future.bliblibazaar.network.service

import com.future.bliblibazaar.auth.model.UserDto
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("users")
    fun getUsers(): Call<List<UserDto>>

}