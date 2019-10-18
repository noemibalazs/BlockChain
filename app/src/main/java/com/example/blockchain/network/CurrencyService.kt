package com.example.blockchain.network

import com.example.blockchain.data.CryptoCurrencies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("list")
    fun getCryptoCurrencies(@Query("access_key") key:String): Call<CryptoCurrencies>
}