package com.example.blockchain.data

import com.google.gson.annotations.SerializedName

data class Rates (
    @field:SerializedName("BCH") val bch: Double,
    @field:SerializedName("BTC") val btc: Double,
    @field:SerializedName("ETH") val eth: Double,
    @field:SerializedName("LTC") val ltc: Double
)