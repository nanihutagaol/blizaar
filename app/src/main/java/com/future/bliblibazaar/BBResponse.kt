package com.future.bliblibazaar

import com.google.gson.annotations.SerializedName

data class BBResponse<T>(
    @field:SerializedName("code")
    val code: String,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: T? = null
)