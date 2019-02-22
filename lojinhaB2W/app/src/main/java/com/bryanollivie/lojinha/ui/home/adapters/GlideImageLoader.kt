package com.bryanollivie.lojinha.ui.home.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jam.utils.easybanner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {

    override fun display(context: Context, path: Any, displayView: View) {
        Glide.with(context)
            .load(path as String)
            .into(displayView as ImageView)

    }

}