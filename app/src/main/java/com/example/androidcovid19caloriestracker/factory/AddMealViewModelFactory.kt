package com.example.androidcovid19caloriestracker.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcovid19caloriestracker.model.Food
import com.example.androidcovid19caloriestracker.network.local.FoodDatabaseDao
import com.example.androidcovid19caloriestracker.viewmodel.AddFoodView2Model

class AddMealViewModelFactory(
    private val food: Food,
    private val dataSource: FoodDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddFoodView2Model::class.java)) {
            return AddFoodView2Model(food, dataSource,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}