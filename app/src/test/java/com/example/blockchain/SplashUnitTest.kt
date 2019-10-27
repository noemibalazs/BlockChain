package com.example.blockchain

import com.example.blockchain.ui.SplashActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest= Config.NONE)
class SplashUnitTest {

    private var splashActivity: SplashActivity?=null

    @Before
    fun create(){
        splashActivity = Robolectric.buildActivity(SplashActivity::class.java).create().resume().get()
    }

    @Test
    fun appContextTest(){
        val context = RuntimeEnvironment.systemContext
        Assert.assertEquals("android", context.packageName)
    }

}