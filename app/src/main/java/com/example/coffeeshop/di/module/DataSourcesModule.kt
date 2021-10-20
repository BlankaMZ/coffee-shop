package com.example.coffeeshop.di.module

import com.example.coffeeshop.data.source.remote.CoffeeShopRemoteDataSource
import com.example.coffeeshop.data.source.remote.CoffeeShopRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: CoffeeShopRemoteDataSourceImpl): CoffeeShopRemoteDataSource
}