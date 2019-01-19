package br.com.cemobile.lodjinha.util.image

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import br.com.cemobile.lodjinha.R
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideImageDownloader(private val context: Context) : ImageDownloader {

    override fun loadUrl(
        url: String,
        @DrawableRes errorRes: Int,
        imageView: ImageView,
        progressBar: ProgressBar?
    ) {
        GlideApp.with(context)
            .load(url)
            .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.white_two)))
            .error(errorRes)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }

    override fun loadUrl(
        url: String,
        @DrawableRes errorRes: Int,
        imageView: ImageView,
        block: () -> Unit
    ) {
        GlideApp.with(context)
            .load(url)
            .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.white_two)))
            .error(errorRes)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                ): Boolean {
                    block()
                    return false
                }

                override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(imageView)
    }

    override fun loadUrlCrop(
        url: String,
        @DrawableRes errorRes: Int,
        imageView: ImageView,
        width: Int,
        height: Int,
        progressBar: ProgressBar?
    ) {
        GlideApp.with(context)
            .load(url)
            .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.white_two)))
            .error(errorRes)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }
            })
            .override(width, height)
            .centerCrop()
            .into(imageView)
    }

    override fun loadUrlWithTransition(
        activity: AppCompatActivity,
        url: String,
        @DrawableRes errorRes: Int,
        imageView: ImageView
    ) {
        activity.supportPostponeEnterTransition()
        GlideApp.with(context)
            .load(url)
            .error(errorRes)
            .centerCrop()
            .dontAnimate()
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    activity.supportStartPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    activity.supportStartPostponedEnterTransition()
                    return false
                }
            })
            .into(imageView)
    }

}