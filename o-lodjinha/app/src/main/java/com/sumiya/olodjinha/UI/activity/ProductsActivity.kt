package com.sumiya.olodjinha.UI.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.sumiya.olodjinha.Model.CategoryModel
import com.sumiya.olodjinha.Model.ProductModel
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.UI.Activities.Base.BaseActivity
import com.sumiya.olodjinha.UI.Adapter.ProductsAdapter
import com.sumiya.olodjinha.ViewModel.ProductViewModel
import com.sumiya.olodjinha.ViewModel.ProductViewModelFactory
import kotlinx.android.synthetic.main.content_products.*

class ProductsActivity : BaseActivity() {
    lateinit var category: CategoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        category = (intent.extras.getSerializable("category") as? CategoryModel)!!

        configureUI()
        configureData()
    }

    fun configureUI() {
        setupToolbar(category.descricao)
        //showLoading("Carregando Produtos")

        productsRecycler.setHasFixedSize(true)
        productsRecycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        productsRecycler.addItemDecoration(DividerItemDecoration(applicationContext!!, LinearLayoutManager.VERTICAL))
    }

    fun configureData() {

        val productViewModel = ViewModelProviders
                .of(this, ProductViewModelFactory(category))
                .get(ProductViewModel::class.java)

        val productsAdapter = ProductsAdapter({ product : ProductModel -> productClicked(product)})

        productViewModel.itemPagedList.observe(this, object : Observer<PagedList<ProductModel>> {
            override fun onChanged(t: PagedList<ProductModel>?) {
                productsAdapter.submitList(t)
            }
        })

        productsRecycler.adapter = productsAdapter
    }

    private fun productClicked(product: ProductModel) {
        var productDetailIntent = Intent(this, ProductDetailActivity::class.java)
        productDetailIntent.putExtra("produto",product)
        startActivity(productDetailIntent)
    }
}
