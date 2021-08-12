package com.example.androidcovid19caloriestracker.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcovid19caloriestracker.network.local.FoodDatabaseDao
import com.example.androidcovid19caloriestracker.viewmodel.Home2ViewModel

class OverviewViewModelFactory (
    private val dataSource: FoodDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Home2ViewModel::class.java)) {
            return Home2ViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Error occurred, Unknown ViewModel class")
    }
}