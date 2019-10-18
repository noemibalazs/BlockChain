package com.example.blockchain.di.module

import com.example.blockchain.di.scope.CurrencyScope
import com.example.blockchain.network.CurrencyService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CurrencyModule {

    @Provides
    @CurrencyScope
    fun provideCurrencyService(retrofit: Retrofit):CurrencyService{
        return retrofit.create(CurrencyService::class.java)
    }
}