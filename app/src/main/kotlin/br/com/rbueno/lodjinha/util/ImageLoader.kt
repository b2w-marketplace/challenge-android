package br.com.rbueno.lodjinha.util

import android.widget.ImageView
import br.com.rbueno.lodjinha.R
import com.bumptech.glide.Glide

class ImageLoader {
    companion object {
        fun loadImage(
            imageUrl: String,
            imageView: ImageView,
            placeholderRes: Int = R.drawable.image_placeholder,
            imageErrorRes: Int = R.drawable.image_placeholder
        ) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(placeholderRes)
                .error(imageErrorRes)
                .into(imageView)
        }
    }
}