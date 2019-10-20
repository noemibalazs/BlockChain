package com.example.blockchain

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.blockchain.data.CurrencyEntity
import com.example.blockchain.room.CurrencyDao
import com.example.blockchain.room.CurrencyDataBase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class CurrencyDataBaseInstrumentationTest {

    private var currencyDao: CurrencyDao?=null
    private var currencyDB:CurrencyDataBase?=null

    @Before
    fun createDB(){
        currencyDB = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
            CurrencyDataBase::class.java).build()
        currencyDao = currencyDB?.getCurrencyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB(){
        currencyDB?.close()
    }

    @Test
    @Throws(Exception::class)
    fun addEntityTest(){
        val entity = CurrencyEntity("BTC", "Bitcoin", "Bitcoin BTC", "icon", 0.0)
        val id = currencyDao?.addEntity2DB(entity)
        assertNotNull(id)
    }
}