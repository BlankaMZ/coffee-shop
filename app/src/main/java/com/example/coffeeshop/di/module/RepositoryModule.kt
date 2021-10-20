package com.example.coffeeshop.di.module

import com.example.coffeeshop.data.source.repository.CoffeeShopRepository
import com.example.coffeeshop.data.source.repository.CoffeeShopRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: CoffeeShopRepositoryImpl): CoffeeShopRepository
}