package br.com.amedigital

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ss.com.bannerslider.ImageLoadingService

class PicassoImageLoadingService : ImageLoadingService  {

    override fun loadImage(url: String?, imageView: ImageView?) {
        Picasso.get().load(url).resize(1000, 650).centerInside().into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        Picasso.get().load(resource).into(imageView)
    }

    override fun loadImage(url: String?, placeHolder: Int, errorDrawable: Int, imageView: ImageView?) {
        Picasso.get().load(url).placeholder(placeHolder).error(errorDrawable).into(imageView)
    }

}