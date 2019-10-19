package com.example.blockchain.helper

import android.content.SharedPreferences
import com.example.blockchain.util.CURRENCY_SYMBOL

class MySharedPreferency(val shared: SharedPreferences) {

    fun saveCurrencySymbol(symbol:String){
        shared.edit().putString(CURRENCY_SYMBOL,symbol).apply()
    }

    fun getCurrencySymbol():String =  shared.getString(CURRENCY_SYMBOL, "")

}