package com.caio.lodjinha.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.caio.challengeandroid.R
import com.caio.lodjinha.base.BaseFragment
import com.caio.lodjinha.home.adapter.BannerPagerAdapter
import com.caio.lodjinha.home.adapter.CategoriesAdapter
import com.caio.lodjinha.home.adapter.ProductsMoreSallersAdapter
import com.caio.lodjinha.repository.entity.Banner
import com.caio.lodjinha.repository.entity.Category
import com.caio.lodjinha.repository.entity.Product
import com.caio.lodjinha.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val bannerPagerAdapter : BannerPagerAdapter by inject()

    private val productsMoreSallersAdapter:ProductsMoreSallersAdapter by inject()

    private val categoriesAdapter:CategoriesAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerHomeData()
    }

    private fun observerHomeData() {
        launch { homeViewModel.loadHomeData() }
        homeViewModel.homeLiveData.observe(this, Observer { home ->
            homeProgressBar.hide()

            createBannerPager(home.banners)

            createListCategories(home.categories)

            createListProductsMoreSallers(home.products)

        })
    }

    private fun createListCategories(categories: List<Category>) {
        categoriesAdapter.setListCategories(categories)
        categoriesAdapter.onClick = {

//            startActivity<ProductsListActivity>(
//                Constants.CATEGORY_ID to it.id,
//                Constants.CATEGORY_NAME to it.descricao)
        }
        rvCategories.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        rvCategories.adapter = categoriesAdapter
    }

    private fun createBannerPager(banners: List<Banner>) {
        bannerPagerAdapter.setListBanners(banners)
        viewPagerBanner.adapter = bannerPagerAdapter
        tabLayoutBanner.setupWithViewPager(viewPagerBanner, true)
    }

    private fun createListProductsMoreSallers(products: List<Product>) {
        productsMoreSallersAdapter.setListProducts(products)
        productsMoreSallersAdapter.onClick = {prod->
//            activity?.openActivity(ProductDetailActivity::class.java){
//                putInt(Constants.PRODUCT_ID, prod.id)
//                putString(Constants.CATEGORY_NAME, prod.categoria.descricao)
//            }
        }

        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        rvProducts.layoutManager = LinearLayoutManager(context)
        rvProducts.addItemDecoration(dividerItemDecoration)
        rvProducts.adapter = productsMoreSallersAdapter
    }
}
