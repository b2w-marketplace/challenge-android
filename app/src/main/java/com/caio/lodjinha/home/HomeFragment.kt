package com.caio.lodjinha.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caio.lodjinha.R
import com.caio.lodjinha.base.BaseFragment
import com.caio.lodjinha.home.adapter.BannerPagerAdapter
import com.caio.lodjinha.home.adapter.CategoriesAdapter
import com.caio.lodjinha.home.adapter.ProductsMoreSallersAdapter
import com.caio.lodjinha.viewmodel.BannerViewModel
import com.caio.lodjinha.viewmodel.CategoryViewModel
import com.caio.lodjinha.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {

    private lateinit var productViewModel: ProductViewModel

    private lateinit var bannerViewModel: BannerViewModel

    private lateinit var categoryViewModel: CategoryViewModel

    companion object {

        var homeFragment: HomeFragment? = null

        fun getInstance(): HomeFragment {

            if (homeFragment == null) homeFragment = HomeFragment()
            return homeFragment!!
        }
    }

    private fun initViewModel() {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        bannerViewModel = ViewModelProviders.of(this).get(BannerViewModel::class.java)
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

        createBanner()
        createCategories()
        createProductsMoreSallers()
    }

    private fun createProductsMoreSallers() {
        productViewModel.getProductsMoreSallers().observe(this, Observer {
            if (it!!.isSuccess()){
                val adapter = ProductsMoreSallersAdapter(it.data!!.data)
                adapter.onClick = {

//                    ProductDetailActivity.starter(context!!, it.id, it.categoria.descricao)
                }

                val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                rvProducts.layoutManager = LinearLayoutManager(context)
                rvProducts.addItemDecoration(dividerItemDecoration)
                rvProducts.adapter = adapter
            }
        })
    }

    private fun createCategories() {
        categoryViewModel.getCategory().observe(this, Observer {
            if(it!!.isSuccess()){
                val adapter = CategoriesAdapter(it.data!!.data)
                adapter.onClick = {

                    Log.d("OnClickCategory->"+it.id,it.descricao)
//            ProductsActivity.starter(context!!, it.id, it.descricao)
                }
                rvCategories.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false
                )
                rvCategories.adapter = adapter
            }
        })
    }

    private fun createBanner() {
        bannerViewModel.getBanner()?.observe(this, Observer {
            if (it!!.isSuccess()) {
                viewPagerBanner.adapter = BannerPagerAdapter(context!!, it.data!!.data)
                tabLayoutBanner.setupWithViewPager(viewPagerBanner, true)
            }
        })
    }
}
