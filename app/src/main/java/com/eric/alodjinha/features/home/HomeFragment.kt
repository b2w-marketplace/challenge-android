package com.eric.alodjinha.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eric.alodjinha.R
import com.eric.alodjinha.base.gone
import com.eric.alodjinha.base.visible
import com.eric.alodjinha.features.home.HomeFragmentPresenter
import com.eric.alodjinha.features.home.HomeFragmentPresenterImpl
import com.eric.alodjinha.features.home.HomeFragmentView
import com.eric.alodjinha.features.home.adapter.BannerPagerAdapter
import com.eric.alodjinha.features.home.adapter.CategoriesAdapter
import com.eric.alodjinha.features.home.model.Banner
import com.eric.alodjinha.features.home.model.Category
import com.eric.alodjinha.features.home.model.Product
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    override fun receiveCategories(categories: List<Category>) {

        val adapter = CategoriesAdapter(categories)
        adapter.onClick = {}

        recyclerViewCategories.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategories.adapter = adapter
    }

    override fun receiveProductsMoreSallers(products: List<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {

        progressBar.visible()
    }

    override fun hideLoading() {

        progressBar.gone()
    }
}