package com.example.blockchain.network

import com.example.blockchain.data.CryptoCurrencies
import com.example.blockchain.data.CurrencyRates
import com.example.blockchain.data.TimeFrameRates
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("list")
    fun getCryptoCurrencyList(@Query("access_key") key:String): Call<CryptoCurrencies>

    @GET("live")
    fun getCryptoCurrencyRates(@Query("access_key") key: String): Call<CurrencyRates>

    @GET("timeframe")
    fun getCryptoCurrencyTimeFrameRates(@Query("access_key") key: String,
                                        @Query("start_date") startDate:String,
                                        @Query("end_date") endDate:String,
                                        @Query("symbols") symbols:String): Call<TimeFrameRates>
}