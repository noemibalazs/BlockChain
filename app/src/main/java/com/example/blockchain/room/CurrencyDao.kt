package com.example.blockchain.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blockchain.data.CurrencyEntity

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getCurrencyList():LiveData<MutableList< CurrencyEntity>>

    @Query("SELECT * FROM currency WHERE symbol = :symbol")
    fun getCurrency(symbol: String):LiveData<CurrencyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCurrencyList(list: MutableList<CurrencyEntity>)
}
