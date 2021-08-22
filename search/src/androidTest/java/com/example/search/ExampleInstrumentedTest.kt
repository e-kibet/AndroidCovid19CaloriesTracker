/*
 * *
 *  * Created by dev on 8/22/21, 10:20 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 8/22/21, 10:20 PM
 *
 */

package com.example.search

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.search", appContext.packageName)
    }
}