package com.b2w.lodjinha.ui.home.banner

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.FragmentActivity
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.b2w.lodjinha.R
import com.b2w.lodjinha.data.model.BannerItem
import com.squareup.picasso.Picasso
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class BannerPagerAdapter(val banners: List<BannerItem>): PagerAdapter(){

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0.equals(p1)
    }

    override fun getCount(): Int {
        return banners.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = LayoutInflater.from(container.context).inflate(R.layout.view_banner_item, container, false)
        val imageView : ImageView = imageLayout.findViewById(R.id.view_banner_item_img)

        Picasso.get()
            .load(banners[position].image)
            .into(imageView)

        (container as ViewPager).addView(imageLayout)

        imageLayout.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(banners[position].link)
            (container.context as FragmentActivity).startActivity(intent)
        }

        return imageLayout
    }
}