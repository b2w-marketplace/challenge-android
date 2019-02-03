package com.eric.alodjinha.features.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eric.alodjinha.R
import com.eric.alodjinha.base.Constants
import com.eric.alodjinha.base.gone
import com.eric.alodjinha.base.helpers.EndlessRecyclerViewScrollListener
import com.eric.alodjinha.base.visible
import com.eric.alodjinha.features.product.adapter.ProductListAdapter
import com.eric.alodjinha.features.product.model.Product
import kotlinx.android.synthetic.main.fragment_products.*

class ProductActivity : AppCompatActivity(), ProductsFragmentView {

    private val presenter: ProductsFragmentPresenter = ProductsFragmentPresenterImpl(this)
    private val mProducts: MutableList<Product> = ArrayList()
    private var productsAdapter: ProductListAdapter? = null
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private val COUNT_ITENS = 20
    val linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        var categoryId = 0
        var categoryName = ""
        if (intent.extras.get(Constants.CANTEGORY) != null) {

            categoryId = intent.extras.getInt(Constants.CANTEGORY_ID)
//            categoryName = intent.extras.getString(Constants.CANTEGORY_NAME)
        }

        presenter.onCreate(categoryId)
    }

    override fun configureViews() {

        productsAdapter = ProductListAdapter(mProducts)

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

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
