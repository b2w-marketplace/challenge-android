package com.bryanollivie.lojinha.ui.home.adapters

import android.view.ViewGroup
import android.widget.ImageView
import com.bannerlayout.listener.ImageLoaderManager
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.BannerLoja
import com.squareup.picasso.Picasso

class BannerAdapter : ImageLoaderManager<BannerLoja> {

    override fun display(container: ViewGroup, model: BannerLoja): ImageView {
        val imageView = ImageView(container.context)
        Picasso.get()
            .load(model.urlImagem)
            .placeholder(R.drawable.abc_action_bar_item_background_material)
            .error(R.drawable.abc_ic_clear_material)
            .centerCrop()
            .fit()
            .into(imageView)
        return imageView
    }

}
