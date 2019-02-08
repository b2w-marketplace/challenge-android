package com.caio.lodjinha.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caio.lodjinha.R
import com.caio.lodjinha.base.BaseFragment
import com.caio.lodjinha.home.adapter.BannerPagerAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

        createBanner()
    }

    private fun createBanner() {
        bannerViewModel.getBanner()?.observe(this, Observer {
            if (it!!.isSuccess()) {
                viewPagerBanner.adapter = BannerPagerAdapter(context!!, it.data!!.data)
                tabLayoutBanner.setupWithViewPager(viewPagerBanner, true)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
