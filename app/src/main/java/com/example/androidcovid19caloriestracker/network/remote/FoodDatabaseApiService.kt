package com.example.androidcovid19caloriestracker.network.remote
import com.example.androidcovid19caloriestracker.BuildConfig
import com.example.androidcovid19caloriestracker.responses.ResponseJson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BuildConfig.BASE_URL)
    .build()

interface FoodDatabaseApiService {
    @GET("parser?")
    fun getSpecificFood(@Query("ingr") food: String,
                        @Query("app_id") appId: String = BuildConfig.APP_ID,
                        @Query("app_key") appKey: String = BuildConfig.APP_KEY): Deferred<ResponseJson>
}

object FoodDatabaseApi {
    val retrofitService: FoodDatabaseApiService by lazy { retrofit.create(FoodDatabaseApiService::class.java) }
}