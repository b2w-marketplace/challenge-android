package br.com.rbueno.lodjinha.ui.home.mostsold

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
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.ui.home.TOOLBAR_TITLE_ARG
import br.com.rbueno.lodjinha.ui.product.list.ProductListAdapter
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.viewmodel.HomeViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

const val PRODUCT_ID_ARG = "product_id"

private const val MOST_SOLD_FLIPPER_POSITION = 0
private const val LOADING_FLIPPER_POSITION = 1
private const val ERROR_FLIPPER_POSITION = 2

class MostSoldFragment : Fragment() {

    @Inject
    lateinit var factory: HomeViewModel.HomeViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(HomeViewModel::class.java) }
    private val buttonTryAgain by lazy { view?.findViewById<Button>(R.id.button_try_again) }

    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recycler_products) }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_most_sold, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        configTryAgainButton()
    }

    private fun configTryAgainButton() {
        buttonTryAgain?.setOnClickListener {
            viewModel.loadMostSoldProducts()
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@MostSoldFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild = ERROR_FLIPPER_POSITION
                }
            }

            loadingLiveData.observe(this@MostSoldFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild = if (it) LOADING_FLIPPER_POSITION else MOST_SOLD_FLIPPER_POSITION
                }
            }

            mostSoldLiveData.observe(this@MostSoldFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild = MOST_SOLD_FLIPPER_POSITION
                }
                configRecyclerView(it)
                clearMostSoldLiveData()
            }
        }.loadMostSoldProducts()
    }


    private fun configRecyclerView(productList: ProductList) {
        with(recyclerView) {
            this?.adapter = ProductListAdapter(productList.data) { navigateToProduct(it) }
            this?.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MostSoldFragment.context)
            this?.addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    this@MostSoldFragment.context,
                    VERTICAL
                )
            )
        }
    }

    private fun navigateToProduct(product: Product) {
        findNavController().navigate(R.id.product_detail_dest, Bundle().apply {
            putInt(PRODUCT_ID_ARG, product.id)
            putString(TOOLBAR_TITLE_ARG, product.category.description)
        })
    }
}