package br.com.cemobile.lodjinha.util.image

import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar

interface ImageDownloader {

    fun loadUrl(
        url: String,
        @DrawableRes errorRes: Int,
        imageView: ImageView,
        progressBar: ProgressBar? = null
    )

    fun loadUrl(
        url: String,
        @DrawableRes errorRes: Int,
        imageView: ImageView,
        block: () -> Unit
    )

    fun loadUrlCrop(
        url: String,
        @DrawableRes errorRes: Int,
        imageView: ImageView,
        width: Int,
        height: Int,
        progressBar: ProgressBar? = null
    )

    fun loadUrlWithTransition(
        activity: AppCompatActivity,
        url: String,
        @DrawableRes errorRes: Int,
        imageView: ImageView
    )

}