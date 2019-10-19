package com.example.blockchain.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blockchain.R
import com.example.blockchain.application.MyApp
import com.example.blockchain.room.CurrencyDao
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var currencyDao: CurrencyDao?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this.applicationContext as MyApp).currencyComponent?.injectMain(this)
    }
}
