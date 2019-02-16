package br.com.b2w.lodjinha.features.banner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import br.com.b2w.lodjinha.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_banner.view.*
import java.lang.Exception

class BannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val imagesUrl: MutableList<String> = mutableListOf()

    init {
        View.inflate(context, R.layout.view_banner, this)
    }

    fun setImages(imagesUrl: List<String>) {
        this.imagesUrl.addAll(imagesUrl)
        setupViewPager()
    }

    private fun hideLoading() {
        bannerProgressBar.visibility = View.INVISIBLE
    }

    private fun setupViewPager() {
        bannerViewPager.adapter = ImageSliderAdapter()
    }

    inner class ImageSliderAdapter : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageLayout = LayoutInflater.from(context).inflate(R.layout.view_banner_image, container, false).apply {
                Picasso.get().load(imagesUrl[position]).into(findViewById(R.id.bannerImageView),
                    object : Callback {
                        override fun onSuccess() {
                            hideLoading()
                        }

                        override fun onError(e: Exception?) {
                            hideLoading()
                        }
                    }
                )
            }
            container.addView(imageLayout)
            return imageLayout
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
            container.removeView(`object` as View)

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

        override fun getCount(): Int = imagesUrl.size

        override fun getPageTitle(position: Int): CharSequence? = ""
    }
}