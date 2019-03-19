package com.danilodequeiroz.lodjinha.home.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.danilodequeiroz.lodjinha.R
import com.danilodequeiroz.lodjinha.common.decoration.LinePagerDecoration
import com.danilodequeiroz.lodjinha.common.decoration.SimpleDividerItemDecoration
import com.danilodequeiroz.lodjinha.common.presentation.*
import com.danilodequeiroz.lodjinha.home.domain.BannerViewModel
import com.danilodequeiroz.lodjinha.home.domain.ProductCategoryViewModel
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.lodjinha.productdetail.presentation.ProductDetailActivity
import com.danilodequeiroz.lodjinha.productlist.presentation.ProductListActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.include_banner_list.*
import kotlinx.android.synthetic.main.include_product_categories_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

val HOME_FRAGMENT_TAG = HomeFragment::class.java.name

class HomeFragment : Fragment(), ProductCategoryViewHolder.OnCategoryClick, ProductViewHolder.OnProductClick {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var bannerPagerAdapter: BannerAdapter
    private lateinit var productCategoryAdapter: ProductCategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.danilodequeiroz.lodjinha.R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeBanners()
        observeProductCategories()
        observeBestSellingProducts()

        savedInstanceState?.let {
            homeViewModel.restoreHome()
        } ?: kotlin.run {
            homeViewModel.initBanners()
            homeViewModel.initProductCategories()
            homeViewModel.initBestSellingProducts()
        }
    }

    override fun onCategoryClicked(view: View, productCategory: ProductCategoryViewModel) {
        productCategory.id?.let { categoryId ->
            activity?.applicationContext?.let {
                val intent = Intent(it, ProductListActivity::class.java)
                intent.putExtras(bundleOf(ProductListActivity.CATEGORY_ID to categoryId))
                intent.putExtras(bundleOf(ProductListActivity.CATEGORY_NAME to productCategory.descricao))
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter, R.anim.exit)
            }
        }
    }

    override fun onProductClicked(view: View, product: ProductViewModel) {
        product.id?.let { id ->
            activity?.applicationContext?.let {
                val intent = Intent(it, ProductDetailActivity::class.java)
                intent.putExtras(bundleOf(ProductDetailActivity.PRODUCT_ID to id))
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter, R.anim.exit)
            }
        }
    }

    private fun observeBanners() {
        homeViewModel.stateBanners.observe(this, Observer<ListState> { state ->
            state?.let {
                when (state) {
                    is DefaultState -> bannersBind(it.data.filterIsInstance<BannerViewModel>().toMutableList())
                    is LoadingState -> bannersLoadingBind()
                    is ErrorState -> bannersErrorBind()
                }
            }
        })
    }

    private fun bannersBind(mutableList: MutableList<BannerViewModel>) {
        bannerPagerAdapter = BannerAdapter(mutableList)
        recyclerBanner.adapter = bannerPagerAdapter
        recyclerBanner.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        recyclerBanner.onFlingListener = null
        snapHelper.attachToRecyclerView(recyclerBanner)
        recyclerBanner.addItemDecoration(LinePagerDecoration())
        recyclerBanner.visibility = View.VISIBLE
        errorViewBanner.visibility = View.GONE
        progressBarBanner.visibility = View.GONE
    }

    private fun bannersLoadingBind() {
        recyclerBanner.visibility = View.INVISIBLE
        errorViewBanner.visibility = View.INVISIBLE
        progressBarBanner.visibility = View.VISIBLE
    }

    private fun bannersErrorBind() {
        recyclerBanner.visibility = View.INVISIBLE
        progressBarBanner.visibility = View.INVISIBLE
        errorViewBanner.visibility = View.VISIBLE
        errorViewBanner.setOnClickListener { homeViewModel.initBanners() }
    }

    private fun observeProductCategories() {
        homeViewModel.stateProductCategories.observe(this, Observer<ListState> { state ->
            state?.let {
                when (state) {
                    is DefaultState -> productCategoriesBind(it.data.filterIsInstance<ProductCategoryViewModel>().toMutableList())
                    is LoadingState -> productCategoriesLoadingBind()
                    is ErrorState -> productCategoriesErrorBind()
                }
            }
        })
    }

    private fun productCategoriesBind(mutableList: MutableList<ProductCategoryViewModel>) {
        productCategoryAdapter = ProductCategoryAdapter(mutableList, this)
        recyclerProductCategories.adapter = productCategoryAdapter
        recyclerProductCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        val snapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(recyclerProductCategories)
        txtProductSubcategoryLabel.visibility = View.VISIBLE
        recyclerProductCategories.visibility = View.VISIBLE
        errorViewCategories.visibility = View.GONE
        progressBarCategories.visibility = View.GONE
    }

    private fun productCategoriesLoadingBind() {
        recyclerProductCategories.visibility = View.GONE
        txtProductSubcategoryLabel.visibility = View.GONE
        errorViewCategories.visibility = View.INVISIBLE
        progressBarCategories.visibility = View.VISIBLE
    }

    private fun productCategoriesErrorBind() {
        recyclerProductCategories.visibility = View.GONE
        txtProductSubcategoryLabel.visibility = View.GONE
        progressBarCategories.visibility = View.GONE
        errorViewCategories.visibility = View.VISIBLE
        errorViewCategories.setOnClickListener { homeViewModel.initProductCategories() }
    }

    private fun observeBestSellingProducts() {
        homeViewModel.stateBestSellingProducts.observe(this, Observer<ListState> { state ->
            state?.let {
                when (state) {
                    is DefaultState -> bestSellingProductsBind(it.data.filterIsInstance<ProductViewModel>().toMutableList())
                    is LoadingState -> bestSellingProductsLoadingBind()
                    is ErrorState -> bestSellingProductsErrorBind()
                }
            }
        })
    }

    private fun bestSellingProductsBind(mutableList: MutableList<ProductViewModel>) {
        productAdapter = ProductAdapter(mutableList, this)
        recyclerBestSellingProducts.adapter = productAdapter
        recyclerBestSellingProducts.isNestedScrollingEnabled = false
        recyclerBestSellingProducts.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        context?.let { recyclerBestSellingProducts.addItemDecoration(SimpleDividerItemDecoration(it)) }
        txtBestSellingProductsLabel.visibility = View.VISIBLE
        recyclerBestSellingProducts.visibility = View.VISIBLE
        errorViewBestSellingProducts.visibility = View.GONE
        progressBarBestSellingProducts.visibility = View.GONE
    }

    private fun bestSellingProductsLoadingBind() {
        recyclerBestSellingProducts.visibility = View.GONE
        txtBestSellingProductsLabel.visibility = View.GONE
        errorViewBestSellingProducts.visibility = View.INVISIBLE
        progressBarBestSellingProducts.visibility = View.VISIBLE
    }

    private fun bestSellingProductsErrorBind() {
        recyclerBestSellingProducts.visibility = View.GONE
        txtBestSellingProductsLabel.visibility = View.GONE
        progressBarBestSellingProducts.visibility = View.GONE
        errorViewBestSellingProducts.visibility = View.VISIBLE
        errorViewBestSellingProducts.setOnClickListener { homeViewModel.initBestSellingProducts() }
    }

}