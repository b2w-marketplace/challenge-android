package br.com.b2w.lodjinha.features.banner

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import br.com.b2w.lodjinha.R
import kotlinx.android.synthetic.main.view_banner.view.*

class BannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val images: MutableList<Drawable> = mutableListOf()

    init {
        View.inflate(context, R.layout.view_banner, this)
    }

    fun setImages(images: List<Drawable>) {
        this.images.addAll(images)
        setupViewPager()
    }

    private fun setupViewPager() {
        bannerViewPager.adapter = ImageSliderAdapter()
    }

    inner class ImageSliderAdapter : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageLayout = LayoutInflater.from(context).inflate(R.layout.view_banner_image, container, false).apply {
                findViewById<ImageView>(R.id.bannerImageView).setImageDrawable(images[position])
            }
            container.addView(imageLayout)
            return imageLayout
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
            container.removeView(`object` as View)

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

        override fun getCount(): Int = images.size

        override fun getPageTitle(position: Int): CharSequence? = ""
    }
}