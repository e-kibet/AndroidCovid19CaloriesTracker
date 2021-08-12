/**
 * *
 *  * Created by evanskibet on 8/12/21, 1:18 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 8/12/21, 1:18 AM
 *
 */
package com.example.androidcovid19caloriestracker.model

import android.os.Parcelable
import com.example.androidcovid19caloriestracker.utils.foodNameForLayout
import com.example.androidcovid19caloriestracker.utils.foodNameToShortString
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food (
    val foodId: String = "",
    val label: String = "",
    val nutrients: Nutrients,
    val category: String = "",
    val categoryLabel: String = ""
) : Parcelable {
    val shortName
        get() = label.foodNameToShortString()

    val layoutName
        get() = label.foodNameForLayout()

}