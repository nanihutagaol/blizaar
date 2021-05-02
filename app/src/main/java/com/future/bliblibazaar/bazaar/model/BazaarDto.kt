package com.future.bliblibazaar.bazaar.model

import com.google.gson.annotations.SerializedName

data class BazaarDto(
    @field:SerializedName("bazaarId")
    val id: Long,

    @field:SerializedName("bazaarName")
    val name: String,

    @field:SerializedName("bazaarStartDate")
    val startDate: Long,

    @field:SerializedName("bazaarEndDate")
    val endDate: Long
)