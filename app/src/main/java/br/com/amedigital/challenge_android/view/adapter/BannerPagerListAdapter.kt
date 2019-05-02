package br.com.amedigital.challenge_android.view.adapter

import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.models.entity.Banner
import com.squareup.picasso.Picasso

class BannerPagerListAdapter (private val bannerArrayList: ArrayList<Banner>): PagerAdapter() {

    private lateinit var inflater: LayoutInflater

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return bannerArrayList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = LayoutInflater.from(container.context)
        val bannerLayout = inflater.inflate(R.layout.fragment_banner_pager_list_item, container, false)!!

        val imageView = bannerLayout.findViewById(R.id.bannerImage) as ImageView
        Picasso.with(imageView.context).load(bannerArrayList[position].urlImagem).into(imageView)
        imageView.setOnClickListener {

            val openBannerURL = Intent(Intent.ACTION_VIEW)
            openBannerURL.data = Uri.parse(bannerArrayList[position].linkUrl)
            imageView.context.startActivity(openBannerURL)
        }

        container.addView(bannerLayout, 0)

        return bannerLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

}