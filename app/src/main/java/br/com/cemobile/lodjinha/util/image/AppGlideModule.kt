package br.com.cemobile.lodjinha.util.image

import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.os.Build
import android.util.Log
import br.com.cemobile.lodjinha.BuildConfig
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat.PREFER_RGB_565
import com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val decodeFormat = if (am.isLowRamDevice) PREFER_RGB_565 else PREFER_ARGB_8888
            val options = RequestOptions().format(decodeFormat)

            builder.setDefaultRequestOptions(options)
        } else {
            super.applyOptions(context, builder)
        }

        builder.setLogLevel(Log.ERROR)
    }

}