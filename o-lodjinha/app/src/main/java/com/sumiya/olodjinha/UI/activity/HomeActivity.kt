package com.sumiya.olodjinha.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.constants.ModelKeyConstants
import com.sumiya.olodjinha.contracts.ViewHomeContract
import com.sumiya.olodjinha.model.BannerDataModel
import com.sumiya.olodjinha.model.CategoryModel
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.presenter.HomePresenter
import com.sumiya.olodjinha.ui.activity.base.BaseDrawerActivity
import com.sumiya.olodjinha.ui.adapter.BannerPagerAdapter
import com.sumiya.olodjinha.ui.fragments.BannerFragment
import com.sumiya.olodjinha.ui.fragments.BestSellersFragment
import com.sumiya.olodjinha.ui.fragments.CategoryFragment
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*


class HomeActivity : BaseDrawerActivity(), CategoryFragment.CategoryListener,
        BestSellersFragment.BestSellersListener, BannerFragment.BannerListener, ViewHomeContract {

    //Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        configureDrawer()
        configureUI()
    }

    override fun onPostResume() {
        super.onPostResume()

        configureData()
    }

    //Methods
    fun configureUI() {
        showLoading(resources.getString(R.string.loading_home))

        this.supportActionBar?.setDisplayShowCustomEnabled(true)
        this.supportActionBar?.setDisplayShowTitleEnabled(false)

        val inflator = LayoutInflater.from(this)
        val v = inflator.inflate(R.layout.layout_title_view, null)

        this.supportActionBar?.setCustomView(v)
    }

    fun configureData() {
        val homePresenter = HomePresenter(this)
        homePresenter.getBanners()
    }

    // Contracts
    override fun setBannerResponse(banners: BannerDataModel) {
        bannerPager.adapter = BannerPagerAdapter(supportFragmentManager, banners)
    }

    override fun handleError(t: Throwable) {
    }

    //Listeners

    //    CategoryListener
    override fun requestProductList(category: CategoryModel) {
        val productIntent = Intent(this, ProductsActivity::class.java)
        productIntent.putExtra(ModelKeyConstants.categoryKey, category)
        startActivity(productIntent)
    }

    //    BestSellersListener
    override fun openProductdetail(product: ProductModel) {
        val productDetailIntent = Intent(this, ProductDetailActivity::class.java)
        productDetailIntent.putExtra(ModelKeyConstants.productKey, product)
        startActivity(productDetailIntent)
    }

    //    BannerListener
    override fun bannerCLick(bannerUrl: String) {
        val bannerIntent = Intent(Intent.ACTION_VIEW)
        bannerIntent.data = Uri.parse(bannerUrl)
        startActivity(bannerIntent)
    }
}