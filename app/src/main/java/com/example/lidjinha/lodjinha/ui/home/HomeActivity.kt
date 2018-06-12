package com.example.lidjinha.lodjinha.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.lidjinha.lodjinha.R
import com.example.lidjinha.lodjinha.data.usecase.BannerUseCase
import com.example.lidjinha.lodjinha.data.usecase.CategoriesUseCase
import com.example.lidjinha.lodjinha.data.usecase.ProductsUseCase
import com.example.lidjinha.lodjinha.model.Banner
import com.example.lidjinha.lodjinha.model.Categorie
import com.example.lidjinha.lodjinha.model.Product
import com.example.lidjinha.lodjinha.util.widgets.DynamicViewPager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    lateinit var banners: List<Banner>
    lateinit var offerBanners: DynamicViewPager
    lateinit var progress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bannerUseCase = BannerUseCase()
        val categorieUseCase = CategoriesUseCase()
        val productsUseCase = ProductsUseCase()

        val presenter = HomePresenter(this, bannerUseCase, categorieUseCase, productsUseCase)
        presenter.getBanners()
        presenter.getCategories()
        presenter.getBestSellers()

        setupToolbar()
    }

    override fun setupOfferBanners(banners: List<Banner>) {
        this.banners = banners
        offerBanners = vp_offerts_banners
        offerBanners.visibility = View.VISIBLE
        val bannersPagerAdapter = BannersPagerAdapter(supportFragmentManager)
        offerBanners.adapter = bannersPagerAdapter
    }

    override fun setupCategories(categories: List<Categorie>) {
        val categoriesList = rv_categories_list
        val categoriesAdapter = CategoriesAdapter(categories, this)
        categoriesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoriesList.adapter = categoriesAdapter
    }

    override fun setupBestSellers(bestSellers: List<Product>) {
        val bestSellersList = rv_best_sellers_list
        val bestSellersAdapter = BestSellersAdapter(bestSellers, this)
        bestSellersList.layoutManager = LinearLayoutManager(this)
        bestSellersList.isNestedScrollingEnabled = false
        bestSellersList.adapter = bestSellersAdapter
    }

    override fun showProgress() {
        progress = ProgressDialog.show(this, getString(R.string.wait_please), getString(R.string.searching_informations))
    }

    override fun hideProgress() {
        progress.dismiss()
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = tb_generic_toolbar
        toolbar.title = getString(R.string.lodjinha)
        setSupportActionBar(toolbar)
    }

    inner class BannersPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {

            return BannerFragment.getInstance(banners[position].linkUrl, banners[position].linkUrl)
        }

        override fun getCount(): Int {
            return banners.size
        }
    }
}
