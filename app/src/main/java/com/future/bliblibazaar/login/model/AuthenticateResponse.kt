package com.future.bliblibazaar.login.model

import com.google.gson.annotations.SerializedName

data class AuthenticateResponse(@field:SerializedName("Token") val token: String)