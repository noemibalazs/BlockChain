package com.example.blockchain.data

import com.google.gson.annotations.SerializedName

data class TimeFrame(
    @field:SerializedName("2019-10-14") val monday: Rates,
    @field:SerializedName("2019-10-15") val tuesday: Rates,
    @field:SerializedName("2019-10-16") val wednesday: Rates,
    @field:SerializedName("2019-10-17") val thursday: Rates,
    @field:SerializedName("2019-10-18") val friday: Rates,
    @field:SerializedName("2019-10-19") val saturday: Rates
)