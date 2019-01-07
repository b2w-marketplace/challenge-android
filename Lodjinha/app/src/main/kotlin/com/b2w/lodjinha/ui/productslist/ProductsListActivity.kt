package com.b2w.lodjinha.ui.productslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.*
import android.view.View
import android.widget.ProgressBar
import com.b2w.lodjinha.R
import com.b2w.lodjinha.data.model.CategoryItem
import com.b2w.lodjinha.data.model.ProductItem
import com.b2w.lodjinha.ui.BaseActivity
import com.b2w.lodjinha.util.EndlessScrollListener
import com.b2w.lodjinha.util.bindView
import com.b2w.lodjinha.util.networkInfo
import android.support.v7.widget.RecyclerView
import com.b2w.lodjinha.ui.product.EXTRA_PRODUCT
import com.b2w.lodjinha.ui.product.ProductActivity

const val EXTRA_PRODUCT_CATEGORY = "EXTRA_PRODUCT_CATEGORY"
const val QUANTITY = 20

class ProductsListActivity : BaseActivity() {

    val productCategory: CategoryItem by lazy { intent.getParcelableExtra<CategoryItem>(EXTRA_PRODUCT_CATEGORY) }
    val viewModel: ProductsListViewModel by lazy { ViewModelProviders.of(this).get(ProductsListViewModel::class.java) }
    val toolbar by bindView<Toolbar>(R.id.view_toolbar)
    val recyclerView by bindView<RecyclerView>(R.id.recyclerview_products)

    val progressBar by bindView<ProgressBar>(R.id.progress_bar)
    val progressBarPagination by bindView<ProgressBar>(R.id.progress_bar_pagination)

    private val productOnClickListener = { list: List<ProductItem>, position: Int, _: View ->
        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra(EXTRA_PRODUCT, list[position])
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        configureToolbar()
        initProductObserver()
        initObserver()
        configureRecyclerView()

        viewModel.fetchProducts(networkInfo(), productCategory.id, 0, QUANTITY)
    }

    fun configureToolbar() {
        toolbar.title = productCategory.description
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun configureRecyclerView() {
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayout
        recyclerView.addItemDecoration(DividerItemDecoration(this, linearLayout.orientation))
        recyclerView.adapter = ProductsListAdapter(arrayListOf(), productOnClickListener)
        recyclerView.addOnScrollListener(object :
            EndlessScrollListener(recyclerView.layoutManager as LinearLayoutManager) {
            override fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int) {}

            override fun onLoadMore(currentPage: Int, totalItemCount: Int) {
                progressBarPagination.visibility = View.VISIBLE

                Handler().postDelayed({
                    initProductObserver()
                    val itemCount = recyclerView.adapter!!.itemCount
                    viewModel.fetchProducts(
                        networkInfo(), productCategory.id,
                        itemCount,
                        itemCount + QUANTITY
                    )
                }, 1000)
            }
        })
    }

    fun initProductObserver() {
        viewModel.productsResult.observeResource(this, onSuccess = {
            progressBarPagination.visibility = View.GONE
            val productsListAdapter = recyclerView.adapter as ProductsListAdapter
            productsListAdapter.addItems(it.list)
            productsListAdapter.notifyDataSetChanged()
        }, onError = {showDefaultError()})
    }

    fun initObserver() {
        viewModel.loadingProducts.observe(this, Observer {
            progressBar.visibility = if (viewModel.loadingProducts.value!!) View.VISIBLE else View.GONE
        })
    }
}