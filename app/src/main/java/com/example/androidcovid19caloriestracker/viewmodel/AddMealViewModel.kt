package com.example.androidcovid19caloriestracker.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddMealViewModel : ViewModel() {
    private val _qrScanVisibility = MutableLiveData<Int>()
    private val _autoSearchBarVisibility = MutableLiveData<Int>()

    val qrScanVisibility: LiveData<Int> get() = _qrScanVisibility

    val autoSearchBarVisibility: LiveData<Int> get() = _autoSearchBarVisibility

    init {
        showAutocompleteSearchBar()
    }

    fun showAutocompleteSearchBar(){
        _autoSearchBarVisibility.postValue(View.VISIBLE)
    }

    fun hideAutocompleteSearchBar(){
        _autoSearchBarVisibility.postValue(View.GONE)
    }

    fun showQrScan() {
        _qrScanVisibility.postValue(View.VISIBLE)
    }

    fun hideQrScan() {
        _qrScanVisibility.postValue(View.GONE)
    }
}