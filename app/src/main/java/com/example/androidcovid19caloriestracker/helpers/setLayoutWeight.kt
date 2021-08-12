/**
 * *
 *  * Created by evanskibet on 8/12/21, 1:18 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 8/12/21, 1:18 AM
 *
 */

package com.example.androidcovid19caloriestracker.helpers

import android.view.View
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("android:layout_weight")
fun setLayoutWeight(view: View, weight: Float) {
    val layoutParams = view.layoutParams as LinearLayout.LayoutParams
    layoutParams.weight = weight
    view.layoutParams = layoutParams
}