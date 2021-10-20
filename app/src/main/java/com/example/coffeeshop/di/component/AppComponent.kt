package com.example.coffeeshop.di.component

import android.app.Application
import com.example.coffeeshop.CoffeeShopApplication
import com.example.coffeeshop.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, DispatcherModule::class, RepositoryModule::class,
        NetworkModule::class, DataSourcesModule::class, MainActivityModule::class, AppModule::class, ViewModelModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: CoffeeShopApplication)
}