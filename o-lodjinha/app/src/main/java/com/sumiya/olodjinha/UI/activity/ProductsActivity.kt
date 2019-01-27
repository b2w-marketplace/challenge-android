package com.sumiya.olodjinha.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.constants.ModelKeyConstants
import com.sumiya.olodjinha.model.CategoryModel
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.ui.activity.base.BaseActivity
import com.sumiya.olodjinha.ui.adapter.ProductsAdapter
import com.sumiya.olodjinha.viewModel.ProductViewModel
import com.sumiya.olodjinha.viewModel.ProductViewModelFactory
import kotlinx.android.synthetic.main.content_products.*

class ProductsActivity : BaseActivity() {
    //Variables
    lateinit var category: CategoryModel

    //Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        category = (intent.extras.getSerializable(ModelKeyConstants.categoryKey) as? CategoryModel)!!

        configureUI()
        configureData()
    }

    //Methods
    private fun configureUI() {
        setupToolbar(category.descricao)

        productsRecycler.setHasFixedSize(true)
        productsRecycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        productsRecycler.addItemDecoration(DividerItemDecoration(applicationContext!!, LinearLayoutManager.VERTICAL))
    }

    private fun configureData() {
        val productViewModel = ViewModelProviders
                .of(this, ProductViewModelFactory(category))
                .get(ProductViewModel::class.java)

        val productsAdapter = ProductsAdapter { product: ProductModel -> productClicked(product) }

        productViewModel.itemPagedList.observe(this, Observer<PagedList<ProductModel>> { t ->
            productsAdapter.submitList(t)
        })

        productsRecycler.adapter = productsAdapter
    }

    //Actions
    private fun productClicked(product: ProductModel) {
        val productDetailIntent = Intent(this, ProductDetailActivity::class.java)
        productDetailIntent.putExtra(ModelKeyConstants.productKey, product)
        startActivity(productDetailIntent)
    }
}
