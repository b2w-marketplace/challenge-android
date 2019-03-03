package br.com.andrecouto.alodjinha.util.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import br.com.andrecouto.alodjinha.R
import br.com.andrecouto.alodjinha.util.GlideApp
import com.bumptech.glide.request.RequestOptions

object ImageViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setSurveyUrl(view: ImageView, imageUrl: String) {
        if (imageUrl.isNotEmpty()) {

            GlideApp.with(view.context)
                    .load(imageUrl)
                    .dontAnimate()
                    .placeholder(R.drawable.bg_image_not_available)
                    .into(view)
        }
    }
}