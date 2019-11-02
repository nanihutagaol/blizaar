package com.future.bliblibazaar.webmodel

import com.google.gson.annotations.SerializedName

data class AuthenticateResponse(@SerializedName("Token") val token: String)