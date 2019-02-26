package br.com.andrecouto.alodjinha.ui.fragment.home.adapter

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import com.viewpagerindicator.CirclePageIndicator

object ViewPagerBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["adapter", "onBannerSelected", "indicator"], requireAll = true)
    fun setAdapter(
            view: ViewPager,
            adapter: BannerPagerAdapter,
            viewModel: OnBannerSelected,
            indicator: CirclePageIndicator
    ) {

        view.adapter = adapter
        view.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                viewModel.onBannerSelected(position)
            }
        })
        indicator.setViewPager(view)
    }
}