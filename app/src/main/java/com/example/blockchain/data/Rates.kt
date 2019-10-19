package com.example.blockchain.data

import com.google.gson.annotations.SerializedName

data class Rates (
    @field:SerializedName("BCH") var BCH: Double,
    @field:SerializedName("BTC") var BTC: Double,
    @field:SerializedName("ETH") var ETH: Double,
    @field:SerializedName("LTC") var LTC: Double
)
