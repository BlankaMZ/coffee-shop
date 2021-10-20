package com.example.coffeeshop.data.model

data class Product (
    val id: String,
    val name: String,
    val grossPrice: String,
    val manufacturer: Manufacturer,
    val images: List<ProductImage>
)