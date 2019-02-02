package com.eric.alodjinha.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eric.alodjinha.R
import com.eric.alodjinha.features.home.HomeFragmentPresenter
import com.eric.alodjinha.features.home.HomeFragmentPresenterImpl
import com.eric.alodjinha.features.home.HomeFragmentView
import com.eric.alodjinha.features.home.adapter.BannerPagerAdapter
import com.eric.alodjinha.features.home.model.Banner
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeFragmentView {

    val presenter: HomeFragmentPresenter = HomeFragmentPresenterImpl(this)

    companion object {

        var homeFragment: HomeFragment? = null

        fun getInstance(): HomeFragment {

            if (homeFragment == null) homeFragment = HomeFragment()
            return homeFragment!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate()
    }

    override fun receiveBanner(banners: List<Banner>) {

        viewPagerBanner.adapter = BannerPagerAdapter(context!!, banners)
        tabLayoutBanner.setupWithViewPager(viewPagerBanner, true)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}