package com.example.blockchain

import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.blockchain.helper.MySharedPreferency
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MySharedPrefInstrumentationTest {

    private var myShared: MySharedPreferency? = null
    private val TEST_SYMBOL = "abc"

    @Before
    fun createSharedPref() {
        myShared = MySharedPreferency(
            InstrumentationRegistry.getInstrumentation().context.getSharedPreferences(
                "My pref",
                Context.MODE_PRIVATE
            )
        )
    }

    @Test
    fun addSymbol() {
        myShared?.saveCurrencySymbol(TEST_SYMBOL)
        val testValue = myShared?.getCurrencySymbol()
        Assert.assertEquals(testValue, TEST_SYMBOL)

    }
}