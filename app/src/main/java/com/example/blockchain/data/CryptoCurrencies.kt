package com.example.blockchain.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CryptoCurrencies(
    @field:SerializedName("crypto") val crypto: Crypto
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readParcelable<Crypto>(Crypto::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(crypto, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CryptoCurrencies> =
            object : Parcelable.Creator<CryptoCurrencies> {
                override fun createFromParcel(source: Parcel): CryptoCurrencies =
                    CryptoCurrencies(source)

                override fun newArray(size: Int): Array<CryptoCurrencies?> = arrayOfNulls(size)
            }
    }
}