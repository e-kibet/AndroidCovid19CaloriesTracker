/*
 * *
 *  * Created by dev on 8/12/21, 5:20 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 8/12/21, 5:20 PM
 *
 */

package com.example.androidcovid19caloriestracker.network.remote



object FoodDatabaseApi {
    val retrofitService: FoodDatabaseApiService by lazy { retrofit.create(FoodDatabaseApiService::class.java) }
}