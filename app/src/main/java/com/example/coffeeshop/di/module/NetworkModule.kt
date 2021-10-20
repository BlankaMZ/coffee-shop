package com.example.coffeeshop.di.module

import com.example.coffeeshop.data.source.remote.CoffeeShopApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideCoffeeShopApiService(retrofit: Retrofit): CoffeeShopApiService {
        return retrofit.create(CoffeeShopApiService::class.java)
    }
}