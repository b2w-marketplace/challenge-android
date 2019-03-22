package br.com.rbueno.lodjinha.ui.home.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.util.ImageLoader
import br.com.rbueno.lodjinha.util.openUrl

class BannerAdapter(private val items: Banner) : PagerAdapter() {
    override fun isViewFromObject(view: View, otherObject: Any): Boolean {
        return view == otherObject
    }

    override fun getCount() = items.bannerItem.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageBanner = LayoutInflater.from(container.context)
            .inflate(R.layout.view_banner_item, container, false) as ImageView

        val bannerItem = items.bannerItem[position]
        ImageLoader.loadImage(bannerItem.urlImage, imageBanner)

        imageBanner.setOnClickListener {
            container.context.openUrl(bannerItem.linkUrl)
        }

        if (container is ViewPager) {
            container.addView(imageBanner)
        }

        return imageBanner
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        if (container is ViewPager) {
            container.removeView(view as View)
        }
    }
}