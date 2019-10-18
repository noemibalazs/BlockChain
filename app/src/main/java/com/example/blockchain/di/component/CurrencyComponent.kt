package com.example.blockchain.di.component

import com.example.blockchain.di.module.CurrencyModule
import com.example.blockchain.di.scope.CurrencyScope
import com.example.blockchain.ui.MainActivity
import com.example.blockchain.ui.SplashActivity
import dagger.Component

@Component(dependencies = [NetworkComponent::class], modules = [CurrencyModule::class])
@CurrencyScope
interface CurrencyComponent {

    fun injectMain(main: MainActivity)
    fun injectSplash(splash: SplashActivity)
}