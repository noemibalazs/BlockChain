package com.example.blockchain.util

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.blockchain.R
import com.example.blockchain.data.Crypto
import com.example.blockchain.data.Rates


fun View.setOnDebounceClickListener(clicked: (View) -> Unit){
    this.setOnClickListener(object : DebounceClickListener(){
        override fun onDebounce(v: View) {
            clicked(v)
        }
    })
}

fun loadPicture(link:String, image: ImageView){
    Glide.with(image.context)
        .load(link)
        .error(R.drawable.icon)
        .placeholder(R.drawable.icon)
        .into(image)
}

fun Crypto.updateRates(rates: Rates?){
    rates?.let {
        this.bch.rate = it.bch
        this.btc.rate = it.btc
        this.eth.rate = it.eth
        this.ltc.rate = it.ltc
    }

}

fun Context.openActivity(dest: Class<*>){
    this.startActivity(Intent(this,dest ))
}

