package com.example.blockchain.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.blockchain.helper.MySharedPreferency
import com.example.blockchain.room.CurrencyDao
import com.example.blockchain.room.CurrencyDataBase
import com.example.blockchain.util.CURRENCY_DB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context = app

    @Provides
    @Singleton
    fun getCurrencyDataBase(): CurrencyDataBase{
        return Room.databaseBuilder(app, CurrencyDataBase::class.java, CURRENCY_DB).build()
    }

    @Provides
    @Singleton
    fun getCurrencyDao(db: CurrencyDataBase): CurrencyDao{
        return db.getCurrencyDao()
    }

    @Provides
    @Singleton
    fun getSharedPref(): MySharedPreferency{
        return MySharedPreferency(app.getSharedPreferences("My pref", Context.MODE_PRIVATE))
    }

}