package marcus.com.br.b2wtest.ui.main.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.include_banner.*
import kotlinx.android.synthetic.main.include_category.*
import kotlinx.android.synthetic.main.include_product_sellers.*
import kotlinx.android.synthetic.main.toolbar.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.helper.snap.OnSnapPositionChangeListener
import marcus.com.br.b2wtest.helper.snap.attachSnapHelperWithListener
import marcus.com.br.b2wtest.model.data.BannerData
import marcus.com.br.b2wtest.model.data.CategoryData
import marcus.com.br.b2wtest.model.data.ProductData
import marcus.com.br.b2wtest.ui.BaseFragment
import marcus.com.br.b2wtest.ui.BaseRecyclerAdapter
import marcus.com.br.b2wtest.ui.main.MainActivity
import marcus.com.br.b2wtest.ui.main.MainNavigator

class HomeFragment : BaseFragment(), BaseRecyclerAdapter.OnItemClickListener {

    private val bannersAdapter = BannersAdapter()
    private val categoriesAdapter = CategoriesAdapter()
    private val bestSellersProductAdapter = BestSellersProductAdapter()

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupToolbar()
        setupSwipeRefresh()
        setupBanner()
        setupCategory()
        setupBestSeller()
        initObservers()
        fetchData()
    }

    private fun setupToolbar() {
        activity?.let {
            val toolbar = (activity as MainActivity).toolbar
            toolbar.findViewById<ImageView>(R.id.toolbarImage).visibility = View.VISIBLE
            toolbar.findViewById<TextView>(R.id.toolbarTitle).text = getString(R.string.app_name)
        }
    }

    private fun setupBanner() {
        fragmentHomeBanners.adapter = bannersAdapter
        fragmentHomeBanners.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupCategory() {
        fragmentHomeCategories.adapter = categoriesAdapter
        fragmentHomeCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupBestSeller() {
        bestSellersProductAdapter.listener = this
        fragmentHomeProducts.adapter = bestSellersProductAdapter
        fragmentHomeProducts.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupSwipeRefresh() {
        fragmentHomeSwipe.setOnRefreshListener {
            fetchData()
        }
    }

    private fun initObservers() {
        homeViewModel.bannerResult.observeResource(this, onSuccess = {
            fragmentHomeSwipe.isRefreshing = false
            successBanner(it.bannerList)
        }, onError = {

        })

        homeViewModel.categorieResult.observeResource(this, onSuccess = {
            successCategory(it.categoryList)
        }, onError = {

        })

        homeViewModel.productBestResult.observeResource(this, onSuccess = {
            successBestProducts(it.productList)
        }, onError = {

        })
    }

    private fun fetchData() {
        homeViewModel.getBanners()
        homeViewModel.getCategories()
        homeViewModel.getBestSellers()
    }

    private fun successBanner(bannerList: List<BannerData>) {
        fragmentHomeBannersIndicator.removeAllViews()
        bannersAdapter.addToList(bannerList as ArrayList<BannerData>)
        fragmentHomeBannersIndicator.attach(fragmentHomeBanners)
        fragmentHomeBanners.onFlingListener = null
        fragmentHomeBanners.attachSnapHelperWithListener(
            PagerSnapHelper(),
            object : OnSnapPositionChangeListener {
                override fun onSnapPositionChange(position: Int) {
                    fragmentHomeBannersIndicator.setSelected(position)
                }
            }
        )
    }

    private fun successCategory(categoryList: List<CategoryData>) {
        categoriesAdapter.addToList(categoryList as ArrayList<CategoryData>)
    }

    private fun successBestProducts(productList: List<ProductData>) {
        bestSellersProductAdapter.addToList(productList as ArrayList<ProductData>)
    }

    override fun onItemClick(view: View, position: Int) {
        context?.let {
            val productData = bestSellersProductAdapter.getItem(position)
            MainNavigator.navigateToProductDetailActivity(it, productData)
        }
    }

    companion object {
        const val TAG = "home.tag"
    }
}