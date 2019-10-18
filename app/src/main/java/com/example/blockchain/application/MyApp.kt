package com.example.blockchain.application

import android.app.Application
import android.icu.util.Currency
import com.example.blockchain.di.component.CurrencyComponent
import com.example.blockchain.di.component.DaggerCurrencyComponent
import com.example.blockchain.di.component.DaggerNetworkComponent
import com.example.blockchain.di.component.NetworkComponent
import com.example.blockchain.di.module.AppModule
import com.example.blockchain.di.module.CurrencyModule
import com.example.blockchain.di.module.NetworkModule


@Suppress("DEPRECATION")
class MyApp :Application() {

    var currencyComponent:  CurrencyComponent?=null
    var netComponent: NetworkComponent?=null

    override fun onCreate() {
        super.onCreate()

        netComponent = provideNetworkComponent(this)
        currencyComponent = provideCurrencyComponent()
    }


    private fun provideNetworkComponent(app:Application):NetworkComponent{
        return DaggerNetworkComponent.builder()
            .appModule(AppModule(app))
            .networkModule(NetworkModule())
            .build()
    }

    private fun provideCurrencyComponent(): CurrencyComponent{
        return DaggerCurrencyComponent.builder()
            .networkComponent(netComponent)
            .currencyModule(CurrencyModule())
            .build()
    }

}