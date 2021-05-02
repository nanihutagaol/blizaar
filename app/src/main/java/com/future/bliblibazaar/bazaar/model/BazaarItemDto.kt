package com.future.bliblibazaar.bazaar.model

data class BazaarItemDto(
    val productId: String,
    val productName: String,
    val productCondition: String,
    val productCategory: String,
    val productReturnReason: String,
    val productGrade: String,
    val productListPrice: Double,
    val productOfferPrice: Double,
    val productStock: Int
)