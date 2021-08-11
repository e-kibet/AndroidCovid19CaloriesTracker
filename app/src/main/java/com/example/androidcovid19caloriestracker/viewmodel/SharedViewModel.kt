package com.example.androidcovid19caloriestracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SharedViewModel: ViewModel() {

    private var name: MutableLiveData<String>? = null

    init {
        viewModelScope.launch {
            val sdf = SimpleDateFormat("yyyy-M-dd")
            setNameData(sdf.format(Date()).toString())
        }
    }

    fun setNameData(nameData: String) {
        name?.value = nameData
    }

    fun getNameData(): MutableLiveData<String>? {
        if (name == null) {
            name = MutableLiveData()
        }
        return name
    }
}