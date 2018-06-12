package br.com.andreguedes.alodjinha.ui.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
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
class HomeFragment : Fragment(), HomeContract.View,
        HomeBannerPagerAdapter.OnHomeBannerSelectedListener,
        HomeCategoriesAdapter.OnCategoryItemSelected,
        HomeBestSellerProductsAdapter.OnBestSellerProductSelected{

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var bannerAdapter : HomeBannerPagerAdapter
    private var categoriesAdapter = HomeCategoriesAdapter(this)
    private val bestSellerProductsAdapter = HomeBestSellerProductsAdapter(this)

    private var currentBanner = 0

    private lateinit var timer : Timer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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

        categories_list.itemAnimator = DefaultItemAnimator()
        categories_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categories_list.adapter = categoriesAdapter

        best_sellers_list.itemAnimator = DefaultItemAnimator()
        best_sellers_list.layoutManager = LinearLayoutManager(context)
        best_sellers_list.adapter = bestSellerProductsAdapter
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
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl)))
    }

    override fun categorySelected(category: Category) {
        //TODO Show all products from selected category
    }

    override fun bestSellerProductSelected(product: Product) {
        //TODO Show product detail
    }

    override fun setBanners(banners: List<Banner>) {
        progress_banner.visibility = View.GONE
        bannerAdapter.setBanners(banners)
    }

    override fun setCategories(categories: List<Category>) {
        progress_categories.visibility = View.GONE
        categoriesAdapter.setCategories(categories)
    }

    override fun setProductsBestSellers(productsBestSellers: List<Product>) {
        progress_best_sellers.visibility = View.GONE
        bestSellerProductsAdapter.setBestSellerProducts(productsBestSellers)
    }

}