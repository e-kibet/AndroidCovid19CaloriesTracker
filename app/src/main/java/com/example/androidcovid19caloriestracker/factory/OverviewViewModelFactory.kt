package com.example.androidcovid19caloriestracker.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcovid19caloriestracker.network.local.FoodDatabaseDao
import com.example.androidcovid19caloriestracker.viewmodel.OverviewViewModel

class OverviewViewModelFactory (
    private val dataSource: FoodDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            return OverviewViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}