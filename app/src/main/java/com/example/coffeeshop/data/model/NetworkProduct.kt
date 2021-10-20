package com.example.coffeeshop.data.model

import com.google.gson.annotations.SerializedName

class NetworkProduct(
    val id: String,

    @SerializedName("product_name")
    val name: String,

    val manufacturer: Manufacturer,

    @SerializedName("gross_price")
    val grossPrice: String,

    val images: List<ProductImage>
)