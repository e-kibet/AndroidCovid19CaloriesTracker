package com.example.androidcovid19caloriestracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var name: MutableLiveData<String>? = null

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