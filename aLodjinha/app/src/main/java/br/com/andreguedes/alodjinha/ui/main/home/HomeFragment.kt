package br.com.andreguedes.alodjinha.ui.main.home

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Banner
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.Product
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment(), HomeContract.View, HomeBannerPagerAdapter.OnHomeBannerSelectedListener {

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var bannerAdapter : HomeBannerPagerAdapter
    private var currentBanner = 0

    private lateinit var timer : Timer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onResume() {
        super.onResume()

        setupView()
        addListeners()

        presenter = HomePresenter(this)
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()

        timer.cancel()

        presenter.unsubscribe()
    }

    override fun setupView() {
        bannerAdapter = HomeBannerPagerAdapter(context!!, this)
        pager_banner.adapter = bannerAdapter

        banner_indicator.setViewPager(pager_banner)
        bannerAdapter.registerDataSetObserver(banner_indicator.dataSetObserver)

        val handler = Handler()
        val timerTask = object: TimerTask() {
            override fun run() {
                handler.post({
                    if (currentBanner == pager_banner.adapter!!.count) currentBanner = 0
                    pager_banner.setCurrentItem(currentBanner++, true)
                })
            }
        }
        timer = Timer()
        timer.schedule(timerTask, 3000, 3000)
    }

    override fun addListeners() {
        pager_banner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentBanner = position
            }

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {}
        })
    }

    override fun bannerSelected(linkUrl: String) {
        // TODO Open browser to link
    }

    override fun setBanners(banners: List<Banner>) {
        progress_banner.visibility = View.GONE
        bannerAdapter.setBanners(banners)
    }

    override fun setCategories(categories: List<Category>) {

    }

    override fun setProductsBestSellers(productsBestSellers: List<Product>) {

    }

}