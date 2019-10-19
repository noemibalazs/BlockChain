package com.example.blockchain.data

import com.google.gson.annotations.SerializedName

data class TimeFrameRates(
    @field:SerializedName("rates") val rates: TimeFrame
)