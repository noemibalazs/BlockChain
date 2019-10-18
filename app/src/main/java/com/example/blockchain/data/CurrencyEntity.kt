package com.example.blockchain.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency")
data class CurrencyEntity(
    @PrimaryKey
    @field:SerializedName("symbol") val symbol: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("name_full") val eth: String,
    @field:SerializedName("icon_url") val ltc: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(symbol)
        writeString(name)
        writeString(eth)
        writeString(ltc)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CurrencyEntity> = object : Parcelable.Creator<CurrencyEntity> {
            override fun createFromParcel(source: Parcel): CurrencyEntity = CurrencyEntity(source)
            override fun newArray(size: Int): Array<CurrencyEntity?> = arrayOfNulls(size)
        }
    }
}