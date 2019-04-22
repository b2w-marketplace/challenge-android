package com.desafioamedigital.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.desafioamedigital.R

fun ImageView.loadImage(url: String, size: Int){
    Glide.with(context)
        .load(url)
        .override(size)
        .fitCenter()
        .placeholder(R.drawable.broken_image)
        .error(R.drawable.broken_image)
        .into(this)
}

fun ImageView.loadImage(url: String){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.broken_image)
        .error(R.drawable.broken_image)
        .into(this)
}