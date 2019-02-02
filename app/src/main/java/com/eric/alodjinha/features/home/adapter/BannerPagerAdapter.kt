package com.eric.alodjinha.features.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.eric.alodjinha.R
import com.eric.alodjinha.features.home.model.Banner
import com.squareup.picasso.Picasso
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

        Picasso.get()
            .load(banner.urlImagem)
            .placeholder(R.drawable.shopping_bag)
            .error(R.drawable.warning)
            .into(itemView.imageViewBanner)

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as FrameLayout)
    }
}