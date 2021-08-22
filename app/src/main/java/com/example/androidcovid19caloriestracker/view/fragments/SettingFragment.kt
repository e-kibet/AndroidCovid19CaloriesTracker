/*
 * *
 *  * Created by dev on 8/20/21, 10:16 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 8/20/21, 10:16 PM
 *
 */

package com.example.androidcovid19caloriestracker.view.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.androidcovid19caloriestracker.R

class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}