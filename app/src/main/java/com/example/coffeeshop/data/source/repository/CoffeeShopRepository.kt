package com.example.coffeeshop.data.source.repository

import com.example.coffeeshop.data.model.Product
import com.example.coffeeshop.utils.Result as thisResult

interface CoffeeShopRepository {

    suspend fun getProducts(refresh: Boolean): thisResult<List<Product>?>
}