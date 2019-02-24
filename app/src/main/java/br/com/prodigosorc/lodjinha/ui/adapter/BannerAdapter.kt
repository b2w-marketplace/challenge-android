package br.com.prodigosorc.lodjinha.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.prodigosorc.lodjinha.models.Banner
import br.com.prodigosorc.lodjinha.ui.adapter.listener.OnItemBannerClickListener
import com.squareup.picasso.Picasso

class BannerAdapter(
    private val banner: List<Banner>?,
    private val context: Context?,
    private var onItemBannerClickListener: OnItemBannerClickListener? = null
) : PagerAdapter() {
    override fun isViewFromObject(view: View, ob: Any): Boolean {
        return view == ob
    }

    override fun getCount(): Int {
        return banner?.size!!
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)

        Picasso.get()
            .load(banner?.get(position)?.urlImagem)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .fit()
            .into(imageView)
        container.addView(imageView, 0)

        imageView.setOnClickListener { onItemBannerClickListener?.onItemClick(banner?.get(position)?.linkUrl) }

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, ob: Any) {
        container.removeView(ob as ImageView?)
    }

    fun setOnItemClickListener(onItemBannerClickListener: OnItemBannerClickListener) {
        this.onItemBannerClickListener = onItemBannerClickListener
    }
}