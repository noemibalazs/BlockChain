package com.example.blockchain

import com.example.blockchain.ui.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest= Config.NONE)
class MainUnitTest {

    private var mainActivity: MainActivity?=null

    @Before
    fun create(){
        mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().resume().get()
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull(){
        Assert.assertNotNull(mainActivity)
    }
}