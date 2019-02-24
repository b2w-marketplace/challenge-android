package marcus.com.br.b2wtest.ui.main.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.include_banner.*
import kotlinx.android.synthetic.main.include_category.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.helper.snap.OnSnapPositionChangeListener
import marcus.com.br.b2wtest.helper.snap.attachSnapHelperWithListener
import marcus.com.br.b2wtest.model.data.BannerData
import marcus.com.br.b2wtest.model.data.CategoryData
import marcus.com.br.b2wtest.ui.BaseFragment

class HomeFragment : BaseFragment() {

    private val bannersAdapter = BannersAdapter()
    private val categoriesAdapter = CategoriesAdapter()

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
        setupSwipeRefresh()
        setupBanner()
        setupCategory()
        initObservers()
        fetchData()
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
    }

    private fun fetchData() {
        homeViewModel.getBanners()
        homeViewModel.getCategories()
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

    companion object {
        const val TAG = "home.tag"
    }
}