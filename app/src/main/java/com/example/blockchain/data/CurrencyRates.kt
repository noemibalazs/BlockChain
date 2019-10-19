package com.example.blockchain.data

import com.google.gson.annotations.SerializedName

data class CurrencyRates(
    @field:SerializedName("rates") val rates: Rates
) {
}