package com.future.bliblibazaar.auth.model

import com.google.gson.annotations.SerializedName

data class AuthenticateRequest(
    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String
)