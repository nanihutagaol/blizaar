package com.future.bliblibazaar.cart.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartItemDto(
    @field:SerializedName("productId")
    val productId: String,

    @field:SerializedName("cartItemName")
    val cartItemName: String,

    @field:SerializedName("cartItemReturnReason")
    val cartReturnReason: String,

    @field:SerializedName("cartItemCondition")
    val cartCondition: String,

    @field:SerializedName("cartItemOfferPrice")
    val cartItemOfferPrice: Double,

    @field:SerializedName("cartItemQty")
    val cartItemQty: Int
): Parcelable