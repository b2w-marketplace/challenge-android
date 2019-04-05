package br.com.andremoreira.lodjinha.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import br.com.andremoreira.lodjinha.R
import br.com.andremoreira.lodjinha.extensions.loadUrl
import br.com.andremoreira.lodjinha.repository.entity.Banner

import kotlinx.android.synthetic.main.frame_layout_banner.view.*

class BannerPagerAdapter(var mContext: Context, internal var banners: List<Banner>?) : PagerAdapter() {

    var mLayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return banners!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as FrameLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.frame_layout_banner, container, false)

        val banner = banners!![position]

        itemView.imageViewBanner.loadUrl(banner.urlImagem,itemView.progress)

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as FrameLayout)
    }
}