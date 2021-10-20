package com.example.coffeeshop.data.source.remote

import com.example.coffeeshop.data.model.NetworkProduct
import com.example.coffeeshop.di.scope.IoDispatcher
import com.example.coffeeshop.utils.Result as thisResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoffeeShopRemoteDataSourceImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val apiService: CoffeeShopApiService
) : CoffeeShopRemoteDataSource {

    override suspend fun getProducts(): thisResult<List<NetworkProduct>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = apiService.getProducts()
                if (result.isSuccessful) {
                    val productsList = result.body()
                    thisResult.Success(productsList)
                } else {
                    thisResult.Success(null)
                }
            } catch (exception: Exception) {
                thisResult.Error(exception)
            }
        }

}