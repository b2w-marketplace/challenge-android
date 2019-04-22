package com.desafioamedigital.ui.activity.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.desafioamedigital.R
import com.desafioamedigital.model.dto.BannerList
import com.desafioamedigital.model.viewmodel.HomeViewModel
import com.desafioamedigital.ui.activity.category.CategoryActivity
import com.desafioamedigital.ui.activity.product_details.ProductDetailsActivity
import com.desafioamedigital.ui.adapter.BestSellersAdapter
import com.desafioamedigital.ui.adapter.CategoriesAdapter
import com.desafioamedigital.ui.adapter.BannersViewPagerAdapter
import com.desafioamedigital.ui.base.BaseActivity
import com.desafioamedigital.util.Extras
import com.desafioamedigital.util.objectToJson
import com.desafioamedigital.util.recyclerview.MultipleRecyclerViewOnClickListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.toolbar_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View, MultipleRecyclerViewOnClickListener {

    object RecyclerViewCode{
        const val CATEGORIES = 1
        const val BEST_SELLERS = 2
    }

    @Inject lateinit var presenter: HomeContract.Presenter

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initDagger2()
        configureDrawer(drawer, toolbar_home, nv_menu)
        attachPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearDisposable()
    }

    private fun initDagger2() = DaggerHomeComponent.builder()
        .homeModule(HomeModule(this))
        .build()
        .inject(this)

    override fun showHomeData(viewModel: HomeViewModel) {
        this.homeViewModel = viewModel
        setBannerView(viewModel.bannerList)
        rv_categories.adapter = CategoriesAdapter(this, viewModel.categoryList.categoryList)
        rv_best_sellers.adapter = BestSellersAdapter(this, viewModel.productList.productList)
    }

    override fun showContent() {
        ll_content.visibility = View.VISIBLE
    }

    override fun hideContent() {
        ll_content.visibility = View.GONE
    }

    private fun attachPresenter(){
        presenter.attachView(this)
        presenter.getApiResults()
    }

    private fun setBannerView(bannerList: BannerList){
        vp_banner.adapter = BannersViewPagerAdapter(this, bannerList)
        tl_indicator.setupWithViewPager(vp_banner)
        //ci_indicator.setViewPager(vp_banner)
    }

    override fun onItemClick(position: Int, recyclerCode: Int) {

        when(recyclerCode){
            RecyclerViewCode.CATEGORIES -> {
                val intent = Intent(getContext(), CategoryActivity::class.java)
                intent.putExtra(Extras.EXTRA_CATEGORY, objectToJson(homeViewModel.categoryList.categoryList[position]))
                startActivity(intent)
            }

            RecyclerViewCode.BEST_SELLERS -> {
                val intent = Intent(getContext(), ProductDetailsActivity::class.java)
                intent.putExtra(Extras.EXTRA_PRODUCT, objectToJson(homeViewModel.productList.productList[position]))
                startActivity(intent)
            }
        }

    }
}