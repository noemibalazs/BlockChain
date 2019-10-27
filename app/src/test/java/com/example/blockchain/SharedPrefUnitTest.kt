package com.example.blockchain

import android.content.Context
import com.example.blockchain.helper.MySharedPreferency
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest= Config.NONE)
class SharedPrefUnitTest {

    private var myShared: MySharedPreferency? = null
    private val TEST_SYMBOL = "abc"

    @Before
    fun createSharedPref() {
        val sharedPref = RuntimeEnvironment.application.getSharedPreferences(
            "My preference", Context.MODE_PRIVATE);
        myShared = MySharedPreferency(sharedPref)
    }

    @Test
    fun addSymbol() {
        myShared?.saveCurrencySymbol(TEST_SYMBOL)
        val testValue = myShared?.getCurrencySymbol()
        Assert.assertEquals(testValue, TEST_SYMBOL)

    }
}