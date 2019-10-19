package com.example.blockchain.di.component

import com.example.blockchain.di.module.AppModule
import com.example.blockchain.di.module.NetworkModule
import com.example.blockchain.helper.MySharedPreferency
import com.example.blockchain.room.CurrencyDao
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface NetworkComponent {

    fun retrofit():Retrofit
    fun currencyDAO():CurrencyDao
    fun sharedPref(): MySharedPreferency

}