package com.example.coffeeshop.di.module

import com.example.coffeeshop.di.scope.PerActivity
import com.example.coffeeshop.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

}