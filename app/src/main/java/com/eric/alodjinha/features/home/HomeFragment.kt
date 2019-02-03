package com.eric.alodjinha.feature.home

import android.content.Intent
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
import com.eric.alodjinha.features.product.adapter.ProductListAdapter
import com.eric.alodjinha.features.product.model.Product
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.eric.alodjinha.MainActivity
import com.eric.alodjinha.base.Constants
import com.eric.alodjinha.features.product.ProductActivity
import com.eric.alodjinha.features.product.ProductsFragment


class HomeFragment : Fragment(), HomeFragmentView {

    val presenter: HomeFragmentPresenter = HomeFragmentPresenterImpl(this)
    var mActivity: MainActivity? = null

    companion object {

        var homeFragment: HomeFragment? = null

        fun getInstance(): HomeFragment {

            if (homeFragment == null) homeFragment = HomeFragment()
            return homeFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate()
        mActivity = context as MainActivity
    }

    override fun onDestroy() {

        presenter.onDestroy()
        super.onDestroy()
    }

    override fun receiveBanner(banners: List<Banner>) {

        viewPagerBanner.adapter = BannerPagerAdapter(context!!, banners)
        tabLayoutBanner.setupWithViewPager(viewPagerBanner, true)
    }

    override fun receiveCategories(categories: List<Category>) {

        val adapter = CategoriesAdapter(categories)
        adapter.onClick = {
            //mActivity?.addFragment(ProductsFragment.getInstance(it.id))

            val extras = Bundle()
            extras.putInt(Constants.CANTEGORY_ID, it.id)
            extras.putString(Constants.CANTEGORY_NAME, it.descricao)
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra(Constants.CANTEGORY, extras)
            context?.startActivity(intent)
        }

        recyclerViewCategories.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        recyclerViewCategories.adapter = adapter
    }

    override fun receiveProductsMoreSallers(products: List<Product>) {

        val adapter = ProductListAdapter(products)
        adapter.onClick = {

        }

        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        recyclerViewProducts.layoutManager = LinearLayoutManager(context)
        recyclerViewProducts.addItemDecoration(dividerItemDecoration)
        recyclerViewProducts.adapter = adapter
    }

    override fun showLoading() {

        progressBar.visible()
    }

    override fun hideLoading() {

        progressBar.gone()
    }
}