package com.example.blockchain.data

import com.google.gson.annotations.SerializedName

data class Crypto(
    @field:SerializedName("BCH") val bch: CurrencyEntity,
    @field:SerializedName("BTC") val btc: CurrencyEntity,
    @field:SerializedName("ETH") val eth: CurrencyEntity,
    @field:SerializedName("LTC") val ltc: CurrencyEntity
)