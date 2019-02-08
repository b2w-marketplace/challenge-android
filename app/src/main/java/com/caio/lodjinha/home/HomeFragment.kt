package com.caio.lodjinha.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import com.caio.lodjinha.base.BaseFragment
import com.caio.lodjinha.viewmodel.BannerViewModel
import com.caio.lodjinha.viewmodel.ProductViewModel


class HomeFragment : BaseFragment() {

    private lateinit var productViewModel: ProductViewModel

    private lateinit var bannerViewModel: BannerViewModel

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()


        bannerViewModel.getBanner()?.observe(this, Observer {
            if (it!!.isSuccess()) {
                Log.d("BANNER", it.data.toString()+" mídias encontradas")
            }
        })
    }
}
