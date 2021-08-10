package com.example.androidcovid19caloriestracker.network.remote
import com.example.androidcovid19caloriestracker.model.Meal
import retrofit2.Call
import retrofit2.http.GET


interface ApiServices {

    @GET("all")
    fun getCountries() : Call<List<Meal>>
}