package com.eric.alodjinha.features.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eric.alodjinha.R
import com.eric.alodjinha.base.Constants
import com.eric.alodjinha.features.product.adapter.ProductListAdapter
import com.eric.alodjinha.features.product.model.Product
import kotlinx.android.synthetic.main.fragment_products.*
import com.eric.alodjinha.base.helpers.EndlessRecyclerViewScrollListener
import androidx.recyclerview.widget.RecyclerView
import com.eric.alodjinha.base.gone
import com.eric.alodjinha.base.visible

class ProductsFragment : Fragment(), ProductsView {

    private val presenter: ProductsPresenter = ProductsPresenterImpl(this)
    private val mProducts: MutableList<Product> = ArrayList()
    private var productsAdapter: ProductListAdapter? = null
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private val COUNT_ITENS = 20
    val linearLayoutManager = LinearLayoutManager(context)

    companion object {

        var homeFragment: ProductsFragment? = null

        fun getInstance(categoryId: Int): ProductsFragment {

            if (homeFragment == null) {

                val extras = Bundle()
                extras.putInt(Constants.CATEGORY_ID, categoryId)
                homeFragment = ProductsFragment()
                homeFragment?.arguments = extras
            }
            return homeFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(arguments?.get(Constants.CATEGORY_ID) as Int)
    }

    override fun configureViews() {

        productsAdapter = ProductListAdapter(mProducts)

        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)

        recyclerViewProducts.layoutManager = linearLayoutManager
        recyclerViewProducts.addItemDecoration(dividerItemDecoration)
        recyclerViewProducts.adapter = productsAdapter

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

                presenter.loadMoreProducts(page, COUNT_ITENS)
            }
        }

        recyclerViewProducts.setOnScrollListener(scrollListener)
    }

    override fun receiveProducts(products: List<Product>) {

        mProducts.addAll(products)
        productsAdapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {

        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showLoading() {

          progressBar.visible()
    }

    override fun hideLoading() {

           progressBar.gone()
    }
}