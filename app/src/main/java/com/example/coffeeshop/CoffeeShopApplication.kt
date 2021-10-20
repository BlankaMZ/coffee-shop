package com.example.coffeeshop

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import com.example.coffeeshop.data.source.repository.CoffeeShopRepository
import com.example.coffeeshop.di.AppInjector
import com.example.coffeeshop.worker.MyWorkerFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CoffeeShopApplication : Application(), Configuration.Provider, HasAndroidInjector {

    @Inject
    lateinit var coffeeShopRepository: CoffeeShopRepository

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        val myWorkerFactory = DelegatingWorkerFactory()
        myWorkerFactory.addFactory(MyWorkerFactory(coffeeShopRepository))


        return Configuration.Builder()
            .setMinimumLoggingLevel(Log.INFO)
            .setWorkerFactory(myWorkerFactory)
            .build()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}