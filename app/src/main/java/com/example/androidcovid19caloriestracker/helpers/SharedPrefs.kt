/*
 * *
 *  * Created by dev on 8/12/21, 1:18 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 8/12/21, 1:18 AM
 *
 */

package com.example.androidcovid19caloriestracker.helpers

import android.content.Context
import android.preference.PreferenceManager

import android.content.SharedPreferences


class PreferenceHelper(context: Context?) {
    private val mPrefs: SharedPreferences
    private val PREF_Key = "Key"
    fun get() = mPrefs.getString(PREF_Key, "")

      fun set(pREF_Key: String) {
            val mEditor: SharedPreferences.Editor = mPrefs.edit()
            mEditor.putString(PREF_Key, pREF_Key)
            mEditor.apply()
        }

    init {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    }
}