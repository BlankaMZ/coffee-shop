package com.example.coffeeshop.data.source.remote

import com.example.coffeeshop.data.model.NetworkProduct
import com.example.coffeeshop.utils.Result as thisResult

interface CoffeeShopRemoteDataSource {

    suspend fun getProducts() : thisResult<List<NetworkProduct>>
}