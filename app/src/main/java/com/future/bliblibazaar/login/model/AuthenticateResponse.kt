package com.future.bliblibazaar.login.model

import com.google.gson.annotations.SerializedName

data class AuthenticateResponse(@field:SerializedName("token") val token: String)