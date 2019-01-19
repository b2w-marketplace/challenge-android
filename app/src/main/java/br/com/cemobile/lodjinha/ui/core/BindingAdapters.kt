package br.com.cemobile.lodjinha.ui.core

import android.databinding.BindingAdapter
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import br.com.cemobile.domain.model.Resource
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.util.image.GlideApp
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visible")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageFromUrl")
    fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            GlideApp.with(view.context)
                .load(imageUrl)
                .placeholder(ColorDrawable(ContextCompat.getColor(view.context, R.color.white_two)))
                .error(R.drawable.no_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("resourceText")
    fun resourceText(view: TextView, resource: Resource<Any>?) {
        resource?.error?.let {
            view.setText(it.localizedMessage)
        }
    }

}
