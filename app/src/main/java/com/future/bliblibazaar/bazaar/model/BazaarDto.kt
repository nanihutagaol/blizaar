package com.future.bliblibazaar.bazaar.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BazaarDto(
    @field:SerializedName("bazaarId")
    val id: Long,

    @field:SerializedName("bazaarName")
    val name: String

//    @field:SerializedName("bazaarStartDate")
//    val startDate: Date,
//
//    @field:SerializedName("bazaarEndDate")
//    val endDate: Date
): Parcelable