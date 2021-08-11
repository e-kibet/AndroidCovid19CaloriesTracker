package com.example.androidcovid19caloriestracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SharedViewModel: ViewModel() {

    private var name: MutableLiveData<String>? = null

    fun setNameData(nameData: String) {
        name?.value = nameData
    }

    fun getNameData(): MutableLiveData<String>? {
        if (name == null) {
            Log.i("SharedViewModel", "SharedViewModel created! ${name}.toString()}")
            name = MutableLiveData()
        }
        return name
    }
}