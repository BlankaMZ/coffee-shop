package com.example.coffeeshop.di.module

import com.example.coffeeshop.ui.productlist.ProductListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProductListFragment(): ProductListFragment
}