package br.com.android.seiji.mobileui.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.mobileui.R
import br.com.android.seiji.mobileui.di.ViewModelFactory
import br.com.android.seiji.mobileui.extensions.gone
import br.com.android.seiji.mobileui.extensions.invisible
import br.com.android.seiji.mobileui.extensions.visible
import br.com.android.seiji.mobileui.mapper.BannerViewMapper
import br.com.android.seiji.mobileui.mapper.CategoryViewMapper
import br.com.android.seiji.mobileui.mapper.ProductViewMapper
import br.com.android.seiji.mobileui.ui.home.adapter.BannerListAdapter
import br.com.android.seiji.mobileui.ui.home.adapter.CategoryListAdapter
import br.com.android.seiji.mobileui.ui.home.adapter.ProductListAdapter
import br.com.android.seiji.mobileui.ui.productDetail.ProductDetailActivity
import br.com.android.seiji.mobileui.ui.productDetail.ProductDetailActivity.Companion.EXTRA_PRODUCT
import br.com.android.seiji.mobileui.ui.productsByCategory.ProductsByCategoryListActivity
import br.com.android.seiji.mobileui.ui.productsByCategory.ProductsByCategoryListActivity.Companion.EXTRA_CATEGORY
import br.com.android.seiji.presentation.model.BannerView
import br.com.android.seiji.presentation.model.CategoryView
import br.com.android.seiji.presentation.model.ProductView
import br.com.android.seiji.presentation.state.Resource
import br.com.android.seiji.presentation.state.ResourceState
import br.com.android.seiji.presentation.viewModel.GetBannersViewModel
import br.com.android.seiji.presentation.viewModel.GetBestSellerProductsViewModel
import br.com.android.seiji.presentation.viewModel.GetCategoriesViewModel
import com.antonyt.infiniteviewpager.InfinitePagerAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_component_banners.*
import kotlinx.android.synthetic.main.view_component_best_sellers.*
import kotlinx.android.synthetic.main.view_component_categories.*
import timber.log.Timber
import javax.inject.Inject


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var bannerMapper: BannerViewMapper
    @Inject
    lateinit var categoryMapper: CategoryViewMapper
    @Inject
    lateinit var productMapper: ProductViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()

        initViewModels()

        contentLayoutContainer.invisible()
        homeProgressBar.visible()

        getBannerViewModel.fetchBanners()
        getCategoriesViewModel.fetchCategories()
        getBestSellerProductsViewModel.fetchBestSellerProducts()
    }

    private fun initViewModels() {
        getBannerViewModel.getBanners().observe(this,
            Observer<Resource<List<BannerView>>> {
                it?.let {
                    handleBannerDataState(it)
                }
            })

        getCategoriesViewModel.getCategories().observe(this,
            Observer<Resource<List<CategoryView>>> {
                it?.let {
                    handleCategoryDataState(it)
                }
            })

        getBestSellerProductsViewModel.getBestSellerProducts().observe(this,
            Observer<Resource<List<ProductView>>> {
                it?.let {
                    handleBestSellerProductsDataState(it)
                }
            })
    }

    private fun handleBannerDataState(resource: Resource<List<BannerView>>) {
        Timber.i(resource.status.toString())
        when (resource.status) {
            ResourceState.LOADING -> {
            }
            ResourceState.SUCCESS -> {
                setBannerSuccess(resource.data?.map { bannerMapper.mapToView(it) })
            }
            ResourceState.ERROR -> {
                Timber.e(resource.message)
            }
        }
    }

    private fun handleCategoryDataState(resource: Resource<List<CategoryView>>) {
        Timber.i(resource.status.toString())
        when (resource.status) {
            ResourceState.LOADING -> {
            }
            ResourceState.SUCCESS -> {
                setCategoriesSuccess(resource.data?.map { categoryMapper.mapToView(it) })
            }
            ResourceState.ERROR -> {
                Timber.e(resource.message)
            }
        }
    }

    private fun handleBestSellerProductsDataState(resource: Resource<List<ProductView>>) {
        Timber.i(resource.status.toString())
        when (resource.status) {
            ResourceState.LOADING -> {
            }
            ResourceState.SUCCESS -> {
                setBestSellerProductsSuccess(resource.data?.map { productMapper.mapToView(it) })
            }
            ResourceState.ERROR -> {
                Timber.e(resource.message)
            }
        }
    }

    private fun setBannerSuccess(banners: List<Banner>?) {
        banners?.let {
            val bannerListAdapter = BannerListAdapter(it, bannerOnClickListener)
            val wrappedAdapter = InfinitePagerAdapter(bannerListAdapter)
            viewPagerBanner.offscreenPageLimit = 3
            viewPagerBanner.adapter = wrappedAdapter
            viewPagerCircleIndicator.setOnSurfaceCount(3)
            viewPagerCircleIndicator.setRisingCount(0)
            viewPagerCircleIndicator.setViewPager(viewPagerBanner)
        }

    }

    private fun setCategoriesSuccess(categories: List<Category>?) {
        categories?.let {
            recyclerViewCategories.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            recyclerViewCategories.setHasFixedSize(true)
            recyclerViewCategories.isNestedScrollingEnabled = false
            recyclerViewCategories.adapter = CategoryListAdapter(it, categoryOnClickListener)
        }
    }

    private fun setBestSellerProductsSuccess(bestSellers: List<Product>?) {
        bestSellers?.let {
            val linearLayout = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL, false
            )
            recyclerViewBestSellers.layoutManager = linearLayout
            recyclerViewBestSellers.setHasFixedSize(true)
            recyclerViewBestSellers.isNestedScrollingEnabled = false
            recyclerViewBestSellers.addItemDecoration(
                DividerItemDecoration(this.activity, linearLayout.orientation)
            )
            recyclerViewBestSellers.adapter = ProductListAdapter(it, productOnClickListener)

            contentLayoutContainer.visible()
            homeProgressBar.gone()
        }
    }

    private val getBannerViewModel: GetBannersViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GetBannersViewModel::class.java)
    }

    private val getCategoriesViewModel: GetCategoriesViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GetCategoriesViewModel::class.java)
    }
    private val getBestSellerProductsViewModel: GetBestSellerProductsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GetBestSellerProductsViewModel::class.java)
    }

    private val bannerOnClickListener = { item: List<Banner>, position: Int, _: View ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item[position].linkUrl))
        startActivity(intent)
    }

    private val categoryOnClickListener = { item: List<Category>, position: Int, _: View ->
        val intent = Intent(this.activity, ProductsByCategoryListActivity::class.java)
        intent.putExtra(EXTRA_CATEGORY, item[position])
        startActivity(intent)
    }

    private val productOnClickListener = { item: List<Product>, position: Int, _: View ->
        val intent = Intent(this.activity, ProductDetailActivity::class.java)
        intent.putExtra(EXTRA_PRODUCT, item[position])
        startActivity(intent)
    }
}