package com.example.blockchain

import com.example.blockchain.data.CurrencyEntity
import com.example.blockchain.room.CurrencyDao
import com.example.blockchain.room.CurrencyDataBase
import com.example.blockchain.ui.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.Mockito.`when`

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest= Config.NONE)
class CurrencyUnitTest {

    @get:Rule
    public var mockitoRule = MockitoJUnit.rule()

    @Mock
    val mockCurrencyDao: CurrencyDao? = null

    @Mock
    val mockMyDatabase: CurrencyDataBase? = null


    @Before
    fun setUp() {
        `when`(mockMyDatabase!!.getCurrencyDao()).thenReturn(mockCurrencyDao)
    }

    @Test
    @Throws(Exception::class)
    fun addEntityTest(){
        val entity = CurrencyEntity("BTC", "Bitcoin", "Bitcoin BTC", "icon", 0.0)
        val id = mockCurrencyDao?.addEntity2DB(entity)
        Assert.assertNotNull(id)
    }

}