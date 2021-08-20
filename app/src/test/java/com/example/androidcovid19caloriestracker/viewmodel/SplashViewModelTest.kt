/*
 * *
 *  * Created by dev on 8/20/21, 8:16 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 8/20/21, 8:16 PM
 *
 */

package com.example.androidcovid19caloriestracker.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SplashViewModelTest : TestCase(){

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var splashViewModel: SplashViewModel


    @Test
    fun `test splash navigation progress`(){

    }

}