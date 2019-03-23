package br.com.rbueno.lodjinha.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ViewFlipper
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.model.Category
import br.com.rbueno.lodjinha.model.CategoryItem
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.ui.BaseActivity
import br.com.rbueno.lodjinha.ui.IconToolbar
import br.com.rbueno.lodjinha.ui.home.banner.BannerAdapter
import br.com.rbueno.lodjinha.ui.home.category.CategoryAdapter
import br.com.rbueno.lodjinha.ui.product.list.ProductListActivity
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.viewmodel.HomeViewModel
import dagger.android.AndroidInjection
import me.relex.circleindicator.CircleIndicator
import javax.inject.Inject

private const val CONTENT_FLIPPER_POSITION = 0
private const val LOADING_FLIPPER_POSITION = 1
private const val ERROR_FLIPPER_POSITION = 2

const val TOOLBAR_TITLE_ARG = "TOOLBAR_TITLE_ARG"
const val CATEGORY_ID_ARG = "CATEGORY_ID_ARG"
const val PRODUCT_ID_ARG = "PRODUCT_ID_ARG"

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var factory: HomeViewModel.HomeViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(HomeViewModel::class.java) }
    private val buttonTryAgain by lazy { findViewById<Button>(R.id.button_try_again) }
    private val recyclerViewCategory by lazy { findViewById<RecyclerView>(R.id.recycler_category) }
    private val recyclerViewProduct by lazy { findViewById<RecyclerView>(R.id.recycler_products) }
    private val viewPagerBanner by lazy { findViewById<ViewPager>(R.id.view_pager_banner) }
    private val viewPagerBannerIndicator by lazy { findViewById<CircleIndicator>(R.id.indicator) }
    private val flipper by lazy { findViewById<ViewFlipper>(R.id.flipper_home) }

    override fun getIconToolBar() = IconToolbar.MENU
    override fun getToolbarText() = getString(R.string.home_label)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_home)
        initViewModel()
        configTryAgainButton()
    }

    private fun configTryAgainButton() {
        buttonTryAgain?.setOnClickListener {
            viewModel.loadHome()
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@HomeActivity) {
                flipper?.displayedChild = ERROR_FLIPPER_POSITION
            }

            loadingLiveData.observe(this@HomeActivity) {
                flipper?.displayedChild = LOADING_FLIPPER_POSITION
            }

            homeLiveData.observe(this@HomeActivity) {
                if (recyclerViewCategory?.adapter == null) {
                    configBanner(it.banner)
                    configCategory(it.category)
                    configMostSold(it.productList)
                    clearHomeLiveData()
                }
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
            this?.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@HomeActivity)
            this?.addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    this@HomeActivity,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun navigateToProductList(categoryItem: CategoryItem) {
        startActivity(Intent(this, ProductListActivity::class.java).apply {
            putExtra(TOOLBAR_TITLE_ARG, categoryItem.description)
            putExtra(CATEGORY_ID_ARG, categoryItem.id)
        })
    }
}