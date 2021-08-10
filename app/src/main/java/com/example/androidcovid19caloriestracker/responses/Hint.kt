package com.example.androidcovid19caloriestracker.responses

import com.example.androidcovid19caloriestracker.model.Food

data class Hint (
    val food: Food,
    val measures: List<Measure>
)