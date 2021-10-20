package com.example.coffeeshop.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.coffeeshop.data.source.repository.CoffeeShopRepository

class MyWorkerFactory(private val repository: CoffeeShopRepository) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return null
    }
}