package com.eric.alodjinha.features.product

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
import com.eric.alodjinha.features.product.productdetail.ProductDetailActivity
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsActivity : AppCompatActivity(), ProductsView {

    private val presenter: ProductsPresenter = ProductsPresenterImpl(this)
    private val mProducts: MutableList<Product> = ArrayList()
    private var productsAdapter: ProductListAdapter? = null
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private val COUNT_ITENS = 20
    val linearLayoutManager = LinearLayoutManager(this)
    var categoryId = 0
    var categoryName = ""

    companion object {

        fun starter(context: Context, categoryId: Int, categoryName: String) {

            val intent = Intent(context, ProductsActivity::class.java)
            intent.putExtra(Constants.CATEGORY_ID, categoryId)
            intent.putExtra(Constants.CATEGORY_NAME, categoryName)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        categoryId = intent.getIntExtra(Constants.CATEGORY_ID, 0)
        categoryName = intent.getStringExtra(Constants.CATEGORY_NAME)
        presenter.onCreate(categoryId)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun configureViews() {

        setTitle(categoryName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        productsAdapter = ProductListAdapter(mProducts!!)
        productsAdapter?.onClick = { ProductDetailActivity.starter(this, it.id, categoryName) }

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        recyclerViewProducts.layoutManager = linearLayoutManager
        recyclerViewProducts.addItemDecoration(dividerItemDecoration)
        recyclerViewProducts.adapter = productsAdapter

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

                presenter.loadMoreProducts(page, COUNT_ITENS)
            }
        }

        recyclerViewProducts.addOnScrollListener(scrollListener!!)
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
