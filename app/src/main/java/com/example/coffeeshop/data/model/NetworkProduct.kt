package com.example.coffeeshop.data.model

import com.google.gson.annotations.SerializedName

class NetworkProduct(
    val id: String,

    @SerializedName("product_name")
    val name: String,

    @SerializedName("gross_price")
    val grossPrice: String
)