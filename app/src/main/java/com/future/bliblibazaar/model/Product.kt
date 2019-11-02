package com.future.bliblibazaar.model

data class Product(
    var productId: Int,
    var name: String,
    var description: String,
    var listPrice: Double,
    var offerPrice: Double,
    var stock: Int)