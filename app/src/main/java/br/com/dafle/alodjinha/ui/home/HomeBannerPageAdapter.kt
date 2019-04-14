package br.com.dafle.alodjinha.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeBannerPageAdapter(fm: FragmentManager, private val fragments: List<Fragment>): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}