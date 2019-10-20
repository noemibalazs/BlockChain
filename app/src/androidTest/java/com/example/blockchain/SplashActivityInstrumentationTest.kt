package com.example.blockchain

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.blockchain.ui.SplashActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SplashActivityInstrumentationTest {

    @get:Rule
    val rule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun appContextTest(){
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals("com.example.blockchain.test", context.packageName)
    }
}