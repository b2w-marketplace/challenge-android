package com.desafioamedigital.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.desafioamedigital.R
import com.desafioamedigital.model.dto.BannerList
import com.desafioamedigital.util.loadImage
import kotlinx.android.synthetic.main.item_banner.view.*

class BannersViewPagerAdapter(private val context: Context, private val bannerList: BannerList) : PagerAdapter() {

    override fun isViewFromObject(view: View, any: Any): Boolean = view == any

    override fun getCount(): Int = bannerList.bannerList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_banner, container, false)

        val banner = bannerList.bannerList[position]
        view.iv_banner.loadImage(banner.imageUrl)
        view.setOnClickListener {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(banner.linkUrl)))
        }

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}