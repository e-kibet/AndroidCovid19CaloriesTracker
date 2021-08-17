package com.example.androidcovid19caloriestracker.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel(
    @SuppressLint("StaticFieldLeak") val context: Context
): ViewModel() {

    private var ready: MutableLiveData<Boolean>? = null

    fun setSplashTimeout(value: Boolean){
        Handler(Looper.myLooper()!!).postDelayed({
            ready?.value = value
        }, 2000)
    }
    
    fun getSplashTimeout(): MutableLiveData<Boolean>?{
        if(ready == null){
            ready = MutableLiveData()
        }
        return ready
    }
}