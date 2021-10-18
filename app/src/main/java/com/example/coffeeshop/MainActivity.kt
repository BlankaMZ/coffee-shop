package com.example.coffeeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.myNavHostFragment)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.myNavHostFragment).navigateUp()
}