package com.caio.challengeandroid.product

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caio.challengeandroid.R
import com.caio.challengeandroid.product.adapter.ProductsAdapter
import com.caio.lodjinha.base.BaseActivity
import com.caio.lodjinha.base.Constants
import com.caio.lodjinha.extensions.gone
import com.caio.lodjinha.extensions.openActivity
import com.caio.lodjinha.extensions.visible
import com.caio.lodjinha.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.activity_list_products.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ProductsListActivity: BaseActivity() {

    private val categoryId: Int by lazy { intent.getIntExtra(Constants.CATEGORY_ID,0)}

    private val categoryName: String? by lazy { intent.getStringExtra(Constants.CATEGORY_NAME)}

    private val productsAdapter: ProductsAdapter by inject()

    private val productListViewModel:ProductListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_products)
        setupView()
        setupRvProductList()
        observerProductList()
    }

    override fun onDestroy() {
        super.onDestroy()
        productsAdapter.clear()
    }

    private fun setupRvProductList() {
        with(rvProducts){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = productsAdapter
        }
        with(productsAdapter){
            onClick = { prod->
                Log.d("Prod","onClick->"+prod.id+ categoryName)
                openActivity(ProductDetailActivity::class.java){
                    putInt(Constants.PRODUCT_ID,prod.id)
                    putString(Constants.CATEGORY_NAME, prod.categoria.descricao)
                }
            }

        }
    }


    private fun observerProductList() {
        progressBar.visible()
        launch {
            productListViewModel.getProductsByCategory(categoryId).observe(this@ProductsListActivity, Observer {
                Log.d("submitList", "size->"+it.size.toString())
                productsAdapter.submitList(it)
                progressBar.gone()
                if (!it.isNotEmpty()) {
                    rvProducts.gone()
                    tvEmptyList.visible()
                }
            })
        }
    }

    private fun setupView() {
        title = categoryName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}