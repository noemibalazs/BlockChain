package com.example.blockchain.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.blockchain.R

fun loadPicture(link:String, image: ImageView){
    Glide.with(image.context)
        .load(link)
        .error(R.drawable.icon)
        .placeholder(R.drawable.icon)
        .into(image)
}

