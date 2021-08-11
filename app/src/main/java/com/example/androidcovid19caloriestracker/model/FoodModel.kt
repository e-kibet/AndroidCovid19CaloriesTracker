package com.example.androidcovid19caloriestracker.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.androidcovid19caloriestracker.utils.doublesToIntOrOne
import kotlin.math.roundToInt

@Entity(tableName = "food_table")
data class FoodModel (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    var name: String?,

    var grams: Double,

    var carbs: Double,

    var proteins: Double,

    var fats: Double,

    var kcal: Double,

    var date: String,

    var time: String?

) {
    @Ignore
    val totalNutrients: Int = doublesToIntOrOne(carbs, proteins, fats)

    @Ignore
    val carbsPercent: Int = (100 * carbs.roundToInt() ) / totalNutrients

    @Ignore
    val proteinPercent: Int = (100 * proteins.roundToInt()) / totalNutrients

    @Ignore
    val fatPercent: Int = (100 * fats.roundToInt()) / totalNutrients

    @Ignore
    val sumPercent: Int = carbsPercent + proteinPercent + fatPercent
}