package com.danilodequeiroz.lodjinha.productlist.presentation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilodequeiroz.lodjinha.R
import com.danilodequeiroz.lodjinha.common.decoration.SimpleDividerItemDecoration
import com.danilodequeiroz.lodjinha.common.presentation.*
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.lodjinha.productdetail.presentation.ProductDetailActivity
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductListActivity : AppCompatActivity(), ProductViewHolder.OnProductClick {

    companion object {
        const val CATEGORY_ID = "category-id"
        const val CATEGORY_NAME = "category-name"
    }

    private val productListViewModel: ProductListViewModel by viewModel()

    private lateinit var productAdapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        toolbarLogo.visibility = View.GONE
        toolbarTitle.visibility = View.GONE
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent?.extras?.getString(CATEGORY_NAME)?.let {
            supportActionBar?.title = it
        }
        observeproductsList()
        intent?.extras?.getInt(CATEGORY_ID)?.let { categoryId ->
            savedInstanceState?.let {
                productListViewModel.restoreProducts()
            } ?: kotlin.run {
                productListViewModel.updateProducts(categoryId)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onProductClicked(view: View, product: ProductViewModel) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtras(bundleOf(ProductDetailActivity.PRODUCT_ID to product.id))
        startActivity(intent)
        overridePendingTransition(R.anim.enter, R.anim.exit)
    }

    private fun observeproductsList() {
        productListViewModel.stateProductsList.observe(this, Observer<ListState> { state ->
            state?.let {
                when (state) {
                    is DefaultState -> productsListBind(it.data.filterIsInstance<ProductViewModel>().toMutableList())
                    is EmptyListState -> emptyBind()
                    is LoadingState -> productsListLoadingBind()
                    is ErrorState -> productsListErrorBind()
                }
            }
        })
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun productsListBind(products: MutableList<ProductViewModel>) {
        productAdapter = ProductAdapter(products, this)
        recyclerProducts.adapter = productAdapter
        recyclerProducts.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerProducts.addItemDecoration(SimpleDividerItemDecoration(this))
        recyclerProducts.visibility = View.VISIBLE
        errorViewProducts.visibility = View.GONE
        progressBarProducts.visibility = View.GONE
        emptyProducts.visibility = View.GONE
    }

    private fun emptyBind() {
        recyclerProducts.visibility = View.GONE
        progressBarProducts.visibility = View.GONE
        errorViewProducts.visibility = View.GONE
        emptyProducts.visibility = View.VISIBLE
        emptyProducts.setOnClickListener {
            intent?.extras?.getInt(CATEGORY_ID)?.let { productListViewModel.updateProducts(it) }
        }
    }

    private fun productsListLoadingBind() {
        recyclerProducts.visibility = View.GONE
        errorViewProducts.visibility = View.INVISIBLE
        emptyProducts.visibility = View.INVISIBLE
        progressBarProducts.visibility = View.VISIBLE
    }

    private fun productsListErrorBind() {
        recyclerProducts.visibility = View.GONE
        progressBarProducts.visibility = View.GONE
        emptyProducts.visibility = View.GONE
        errorViewProducts.visibility = View.VISIBLE
        errorViewProducts.setOnClickListener {
            intent?.extras?.getInt(CATEGORY_ID)?.let { productListViewModel.updateProducts(it) }
        }
    }

}