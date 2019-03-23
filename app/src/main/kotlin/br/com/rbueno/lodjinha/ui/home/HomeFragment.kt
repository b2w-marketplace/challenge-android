package br.com.rbueno.lodjinha.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.*
import br.com.rbueno.lodjinha.ui.home.banner.BannerAdapter
import br.com.rbueno.lodjinha.ui.home.category.CategoryAdapter
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.viewmodel.HomeViewModel
import dagger.android.support.AndroidSupportInjection
import me.relex.circleindicator.CircleIndicator
import javax.inject.Inject

private const val CONTENT_FLIPPER_POSITION = 0
private const val LOADING_FLIPPER_POSITION = 1
private const val ERROR_FLIPPER_POSITION = 2

const val CATEGORY_ID_ARG = "category_id"
const val PRODUCT_ID_ARG = "product_id"

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: HomeViewModel.HomeViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(HomeViewModel::class.java) }
    private val buttonTryAgain by lazy { view?.findViewById<Button>(R.id.button_try_again) }
    private val recyclerViewCategory by lazy { view?.findViewById<RecyclerView>(R.id.recycler_category) }
    private val recyclerViewProduct by lazy { view?.findViewById<RecyclerView>(R.id.recycler_products) }
    private val viewPagerBanner by lazy { view?.findViewById<ViewPager>(R.id.view_pager_banner) }
    private val viewPagerBannerIndicator by lazy { view?.findViewById<CircleIndicator>(R.id.indicator) }
    private val flipper by lazy { view?.findViewById<ViewFlipper>(R.id.flipper_home) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        configTryAgainButton()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun configTryAgainButton() {
        buttonTryAgain?.setOnClickListener {
            viewModel.loadHome()
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@HomeFragment) {
                flipper?.displayedChild = ERROR_FLIPPER_POSITION
            }

            loadingLiveData.observe(this@HomeFragment) {
                flipper?.displayedChild = LOADING_FLIPPER_POSITION
            }

            homeLiveData.observe(this@HomeFragment) {
                configBanner(it.banner)
                configCategory(it.category)
                configMostSold(it.productList)
                clearHomeLiveData()
                flipper?.displayedChild = CONTENT_FLIPPER_POSITION
            }
        }.loadHome()
    }

    private fun configBanner(banner: Banner) {
        val adapter = BannerAdapter(banner)
        viewPagerBanner?.adapter = adapter
        viewPagerBannerIndicator?.let {
            it.setViewPager(viewPagerBanner)
            adapter.registerDataSetObserver(it.dataSetObserver)
        }
    }

    private fun configCategory(category: Category) {
        recyclerViewCategory?.adapter = CategoryAdapter(category) {
            navigateToProductList(it)
        }
    }

    private fun configMostSold(productList: ProductList) {
        with(recyclerViewProduct) {
            this?.adapter =
                br.com.rbueno.lodjinha.ui.product.list.ProductListAdapter(productList.data) { navigateToProduct(it) }
            this?.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@HomeFragment.context)
            this?.addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    this@HomeFragment.context,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun navigateToProductList(categoryItem: CategoryItem) {
        findNavController().navigate(R.id.product_list_dest,
            Bundle().apply {
                putString(TOOLBAR_TITLE_ARG, categoryItem.description)
                putInt(CATEGORY_ID_ARG, categoryItem.id)
            })
    }

    private fun navigateToProduct(product: Product) {
        findNavController().navigate(R.id.product_detail_dest, Bundle().apply {
            putInt(PRODUCT_ID_ARG, product.id)
            putString(TOOLBAR_TITLE_ARG, product.category.description)
        })
    }
}