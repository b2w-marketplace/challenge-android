package com.caio.lodjinha.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.caio.challengeandroid.R
import com.caio.challengeandroid.product.ProductDetailActivity
import com.caio.challengeandroid.product.ProductsListActivity
import com.caio.lodjinha.base.BaseFragment
import com.caio.lodjinha.base.Constants
import com.caio.lodjinha.extensions.openActivity
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
        with(categoriesAdapter){
            setListCategories(categories)
            onClick = {
                activity?.openActivity(ProductsListActivity::class.java){
                    putString(Constants.CATEGORY_NAME, it.descricao)
                    putInt(Constants.CATEGORY_ID, it.id)
                }
            }
            rvCategories.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            rvCategories.adapter = this
        }
    }

    private fun createBannerPager(banners: List<Banner>) {
        with(bannerPagerAdapter){
            setListBanners(banners)
            viewPagerBanner.adapter = this
            tabLayoutBanner.setupWithViewPager(viewPagerBanner, true)
        }
    }

    private fun createListProductsMoreSallers(products: List<Product>) {
        with(productsMoreSallersAdapter){
            setListProducts(products)
            onClick = {prod->
                activity?.openActivity(ProductDetailActivity::class.java){
                    putInt(Constants.PRODUCT_ID, prod.id)
                    putString(Constants.CATEGORY_NAME, prod.categoria.descricao)
                }
            }
        }

        with(rvProducts){
            val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(dividerItemDecoration)
            adapter = productsMoreSallersAdapter
        }
    }
}
