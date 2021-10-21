package com.example.coffeeshop.data.source.repository

import com.example.coffeeshop.data.model.Product
import com.example.coffeeshop.data.source.remote.CoffeeShopRemoteDataSource
import com.example.coffeeshop.di.scope.IoDispatcher
import com.example.coffeeshop.utils.Result as thisResult
import com.example.coffeeshop.mapper.ProductListMapperRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoffeeShopRepositoryImpl @Inject constructor(
    private val remoteDataSource: CoffeeShopRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CoffeeShopRepository {
    override suspend fun getProducts(refresh: Boolean, name: String?): thisResult<List<Product>?> =
        withContext(ioDispatcher) {
            val mapper = ProductListMapperRemote()
            when (val response = remoteDataSource.getProducts(name)) {
                is thisResult.Success -> {
                    if (response.data != null) {
                        thisResult.Success(mapper.transformToDomain(response.data))
                    } else {
                        thisResult.Success(null)
                    }
                }
                is thisResult.Error -> {
                    thisResult.Error(response.exception)
                }
                else -> thisResult.Loading
            }
        }


}