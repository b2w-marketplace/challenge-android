package br.com.andreguedes.alodjinha.ui.main.home

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Banner
import br.com.andreguedes.alodjinha.helper.ImageHelper

class HomeBannerPagerAdapter(private val context: Context, private val listener: OnHomeBannerSelectedListener) : PagerAdapter() {

    private val layoutInflater = LayoutInflater.from(context)

    private var banners = arrayListOf<Banner>()

    fun setBanners(banners: List<Banner>) {
        this.banners.clear()
        this.banners.addAll(banners)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflate = layoutInflater.inflate(R.layout.item_banner_pager, container, false)
        bindItem(inflate, position)
        container.addView(inflate)
        return inflate
    }

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = banners.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private fun bindItem(inflate: View, position: Int) : ImageView {
        val imgBanner = inflate.findViewById<ImageView>(R.id.img_pass)
        val banner = banners[position]
        banner.let {
            ImageHelper.loadImage(context, it.urlImagem!!, imgBanner)
        }
        imgBanner.setOnClickListener {
            listener.bannerSelected(banner.linkUrl!!)
        }
        return imgBanner
    }

    interface OnHomeBannerSelectedListener {

        fun bannerSelected(linkUrl: String)

    }

}