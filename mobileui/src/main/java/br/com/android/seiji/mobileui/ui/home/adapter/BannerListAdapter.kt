package br.com.android.seiji.mobileui.ui.home.adapter

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.mobileui.R
import com.squareup.picasso.Picasso

class BannerListAdapter(
    val banners: List<Banner>,
    private val onItemClickListener: (item: List<Banner>, position: Int, view: View) -> Unit
) : PagerAdapter() {

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return banners.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(container.context)
            .inflate(R.layout.item_banner, container, false)
        val imageView: ImageView = layout.findViewById(R.id.imageBanner)

        Picasso.get()
            .load(banners[position].urlImagem)
            .into(imageView)

        (container as ViewPager).addView(layout)

        layout.setOnClickListener {
            onItemClickListener(banners, position, layout)
        }

        return layout
    }
}