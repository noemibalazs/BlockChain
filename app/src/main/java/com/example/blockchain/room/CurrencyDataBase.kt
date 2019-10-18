package com.example.blockchain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.blockchain.data.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = 1, exportSchema = false)
abstract class CurrencyDataBase : RoomDatabase(){

    abstract fun getCurrencyDao(): CurrencyDao
}