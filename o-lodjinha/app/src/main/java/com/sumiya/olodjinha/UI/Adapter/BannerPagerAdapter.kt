package com.sumiya.olodjinha.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sumiya.olodjinha.model.BannerDataModel
import com.sumiya.olodjinha.ui.fragments.BannerFragment

class BannerPagerAdapter(fragmentManager: FragmentManager, private val banners: BannerDataModel) :
        FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        var bannerFrag = BannerFragment()
        bannerFrag.banner = banners.data[position]

        return bannerFrag
    }

    override fun getCount(): Int {
        return banners.data.size
    }
}