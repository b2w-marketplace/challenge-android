package com.b2w.lodjinha.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.b2w.lodjinha.R
import com.b2w.lodjinha.data.model.CategoryItem
import com.b2w.lodjinha.data.model.ProductItem
import com.b2w.lodjinha.ui.BaseFragment
import com.b2w.lodjinha.ui.home.banner.BannerPagerAdapter
import com.b2w.lodjinha.ui.home.category.CategoryListAdapter
import com.b2w.lodjinha.ui.product.EXTRA_PRODUCT
import com.b2w.lodjinha.ui.product.ProductActivity
import com.b2w.lodjinha.ui.productslist.EXTRA_PRODUCT_CATEGORY
import com.b2w.lodjinha.ui.productslist.ProductsListActivity
import com.b2w.lodjinha.ui.productslist.ProductsListAdapter
import com.b2w.lodjinha.util.bindView
import com.b2w.lodjinha.util.networkInfo
import it.xabaras.android.viewpagerindicator.widget.ViewPagerIndicator

class HomeFragment : BaseFragment(){

    val viewModel: HomeViewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    val indicator by bindView<ViewPagerIndicator>(R.id.viewPagerIndicator)
    val viewPager by bindView<ViewPager>(R.id.viewpager)
    val recyclerView by bindView<RecyclerView>(R.id.recyclerview)
    val recyclerViewBestSelling by bindView<RecyclerView>(R.id.recyclerview_bestselling)
    val progressBar by bindView<ProgressBar>(R.id.progress_bar)
    val bannersContainer by bindView<ConstraintLayout>(R.id.view_banners)
    val categoriesContainer by bindView<ConstraintLayout>(R.id.view_categories)
    val bestsellingContainer by bindView<ConstraintLayout>(R.id.view_bestselling)

    private val categoryOnClickListener = { list: List<CategoryItem>, position: Int, _: View ->
        val intent = Intent(this.activity, ProductsListActivity::class.java)
        intent.putExtra(EXTRA_PRODUCT_CATEGORY, list[position])
        startActivity(intent)
    }

    private val productOnClickListener = { list: List<ProductItem>, position: Int, _: View ->
        val intent = Intent(this.activity, ProductActivity::class.java)
        intent.putExtra(EXTRA_PRODUCT, list[position])
        startActivity(intent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onResume() {
        super.onResume()
                initObservers()

        viewModel.fetchBanners(this.activity?.networkInfo())
        viewModel.fetchCategories(this.activity?.networkInfo())
        viewModel.fetchBestSelling(this.activity?.networkInfo())
    }

    fun initObservers() {
        viewModel.bannersResult.observeResource(this, onSuccess = {
            viewPager.adapter = BannerPagerAdapter(it.list)
            indicator.initWithViewPager(viewPager)
            progressBar.visibility = View.GONE
            bannersContainer.visibility = View.VISIBLE
        }, onError = { showDefaultError() })

        viewModel.categoriesResult.observeResource(this, onSuccess = {
            recyclerView.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = CategoryListAdapter(it.list, categoryOnClickListener)
            categoriesContainer.visibility = View.VISIBLE
        }, onError = { showDefaultError() })

        viewModel.bestSellingResult.observeResource(this, onSuccess = {
            val linearLayout = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
            recyclerViewBestSelling.layoutManager = linearLayout
            recyclerViewBestSelling.addItemDecoration(DividerItemDecoration(this.activity, linearLayout.orientation))
            recyclerViewBestSelling.adapter = ProductsListAdapter(it.list, productOnClickListener)
            bestsellingContainer.visibility = View.VISIBLE
        }, onError = { showDefaultError() })
    }
}