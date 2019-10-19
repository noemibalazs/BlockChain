package com.example.blockchain.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency")
data class CurrencyEntity(
    @PrimaryKey
    @field:SerializedName("symbol") val symbol: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("name_full") val fullName: String,
    @field:SerializedName("icon_url") val iconUrl: String,
    var rate: Double?
)