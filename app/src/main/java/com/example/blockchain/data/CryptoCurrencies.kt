package com.example.blockchain.data

import com.google.gson.annotations.SerializedName

data class CryptoCurrencies(
    @field:SerializedName("crypto") val crypto: Crypto
)