package br.com.rbueno.lodjinha.ui.product.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ViewFlipper
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.ui.home.TOOLBAR_TITLE_ARG
import br.com.rbueno.lodjinha.ui.home.mostsold.PRODUCT_ID_ARG
import br.com.rbueno.lodjinha.util.InfiniteScrollListener
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.viewmodel.ProductViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

private const val PRODUCT_LIST_FLIPPER_POSITION = 0
private const val EMPTY_FLIPPER_POSITION = 1
private const val ERROR_FLIPPER_POSITION = 2

class ProductListFragment : Fragment() {

    @Inject
    lateinit var factory: ProductViewModel.ProductViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(ProductViewModel::class.java) }

    private val recyclerProducts by lazy { view?.findViewById<RecyclerView>(R.id.recycler_products) }
    private val navArgs by navArgs<ProductListFragmentArgs>()
    private val adapter by lazy { ProductPagedListAdapter(mutableListOf()) { navigateToProduct(it) } }
    private val flipperProduct by lazy { view?.findViewById<ViewFlipper>(R.id.flipper_product) }
    private val buttonTryAgain by lazy { view?.findViewById<Button>(R.id.button_try_again) }

    private var hasMoreItems = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initViewModel()
        configTryAgainButton()
    }

    private fun configTryAgainButton() {
        buttonTryAgain?.setOnClickListener {
            viewModel.currentListQuantity = 0
            viewModel.loadProductListPage(navArgs.categoryId)
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@ProductListFragment) {
                flipperProduct?.displayedChild = ERROR_FLIPPER_POSITION
            }

            productListLiveData.observe(this@ProductListFragment) {
                flipperProduct?.displayedChild = PRODUCT_LIST_FLIPPER_POSITION
                hasMoreItems = it.data.isNotEmpty()
                checkForEmptyState()
                addPageToAdapter(it.data)
            }

        }.loadProductListPage(navArgs.categoryId)
    }

    private fun checkForEmptyState() {
        if (!hasMoreItems && adapter.itemCount == 1) {
            flipperProduct?.displayedChild = EMPTY_FLIPPER_POSITION
        }
    }

    private fun addPageToAdapter(page: List<Product>) {
        adapter.addPage(page, hasMoreItems)
    }

    private fun setupRecyclerView() {
        with(recyclerProducts) {
            this?.adapter = this@ProductListFragment.adapter
            this?.layoutManager = LinearLayoutManager(this@ProductListFragment.context)
            this?.addItemDecoration(
                DividerItemDecoration(
                    this@ProductListFragment.context,
                    LinearLayoutManager.VERTICAL
                )
            )
            this?.addOnScrollListener(InfiniteScrollListener({
                if (hasMoreItems) {
                    viewModel.clearProductListLiveData()
                    viewModel.loadProductListPage(navArgs.categoryId)
                }
            }, layoutManager as LinearLayoutManager))
        }
    }

    private fun navigateToProduct(product: Product) {
        findNavController().navigate(R.id.product_detail_dest, Bundle().apply {
            putInt(PRODUCT_ID_ARG, product.id)
            putString(TOOLBAR_TITLE_ARG, product.category.description)
        })
    }
}