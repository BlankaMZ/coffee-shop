package com.example.coffeeshop.data.source.remote

import com.example.coffeeshop.data.model.NetworkProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoffeeShopApiService {
    @GET("products")
    suspend fun getProducts(@Query("name") name: String?): Response<List<NetworkProduct>>
}