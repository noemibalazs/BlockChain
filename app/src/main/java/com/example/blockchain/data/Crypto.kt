package com.example.blockchain.data

import com.google.gson.annotations.SerializedName

data class Crypto(
    @field:SerializedName("BCH") var bch: CurrencyEntity,
    @field:SerializedName("BTC") var btc: CurrencyEntity,
    @field:SerializedName("ETH") var eth: CurrencyEntity,
    @field:SerializedName("LTC") var ltc: CurrencyEntity
)