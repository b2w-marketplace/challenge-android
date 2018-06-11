package br.com.andreguedes.alodjinha.helper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ImageHelper {

    fun loadImage(context: Context, file: String, imgView: ImageView) {
        Glide.with(context)
                .load(file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgView)
    }

}