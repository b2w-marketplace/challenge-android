package br.com.rbueno.lodjinha.ui.product.list

import android.os.Bundle
import android.widget.Button
import android.widget.ViewFlipper
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.ui.BaseActivity
import br.com.rbueno.lodjinha.ui.IconToolbar
import br.com.rbueno.lodjinha.ui.home.CATEGORY_ID_ARG
import br.com.rbueno.lodjinha.ui.home.TOOLBAR_TITLE_ARG
import br.com.rbueno.lodjinha.util.InfiniteScrollListener
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.viewmodel.ITEMS_PER_PAGE
import br.com.rbueno.lodjinha.viewmodel.ProductViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

private const val PRODUCT_LIST_FLIPPER_POSITION = 0
private const val ERROR_FLIPPER_POSITION = 1

class ProductListActivity : BaseActivity() {
    @Inject
    lateinit var factory: ProductViewModel.ProductViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(ProductViewModel::class.java) }

    private val recyclerProducts by lazy { findViewById<RecyclerView>(R.id.recycler_products) }
    private val adapter by lazy { ProductPagedListAdapter(mutableListOf()) { navigateToProduct(it) } }
    private val flipperProduct by lazy { findViewById<ViewFlipper>(R.id.flipper_product) }
    private val buttonTryAgain by lazy { findViewById<Button>(R.id.button_try_again) }

    private var hasMoreItems = true

    override fun getIconToolBar() = IconToolbar.BACK

    override fun getToolbarText() = intent.getStringExtra(TOOLBAR_TITLE_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_product_list)
        setupRecyclerView()
        initViewModel()
        configTryAgainButton()
    }

    private fun configTryAgainButton() {
        buttonTryAgain?.setOnClickListener {
            viewModel.currentListQuantity = 0
            viewModel.loadProductListPage(intent.getIntExtra(CATEGORY_ID_ARG, 1))
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@ProductListActivity) {
                flipperProduct?.displayedChild = ERROR_FLIPPER_POSITION
            }

            productListLiveData.observe(this@ProductListActivity) {
                flipperProduct?.displayedChild = PRODUCT_LIST_FLIPPER_POSITION
                hasMoreItems = it.data.isNotEmpty() && it.data.size == ITEMS_PER_PAGE
                addPageToAdapter(it.data)
            }

        }.loadProductListPage(intent.getIntExtra(CATEGORY_ID_ARG, 1))
    }

    private fun addPageToAdapter(page: List<Product>) {
        adapter.addPage(page, hasMoreItems)
    }

    private fun setupRecyclerView() {
        with(recyclerProducts) {
            this?.adapter = this@ProductListActivity.adapter
            this?.layoutManager = LinearLayoutManager(this@ProductListActivity)
            this?.addItemDecoration(
                DividerItemDecoration(
                    this@ProductListActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            this?.addOnScrollListener(InfiniteScrollListener({
                if (hasMoreItems) {
                    viewModel.clearProductListLiveData()
                    viewModel.loadProductListPage(intent.getIntExtra(CATEGORY_ID_ARG, 1))
                }
            }, layoutManager as LinearLayoutManager))
        }
    }
}