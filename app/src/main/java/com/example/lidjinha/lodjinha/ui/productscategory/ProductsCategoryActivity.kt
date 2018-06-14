package com.example.lidjinha.lodjinha.ui.productscategory

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.example.lidjinha.lodjinha.R
import com.example.lidjinha.lodjinha.data.usecase.ProductsUseCase
import com.example.lidjinha.lodjinha.model.Categorie
import com.example.lidjinha.lodjinha.model.Product
import kotlinx.android.synthetic.main.activity_products_category.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductsCategoryActivity : AppCompatActivity(), ProductsCategoryContract.View {

    companion object {
        val EXTRA_CATEGORIE = "extra_product"
    }

    lateinit var presenter: ProductsCategoryPresenter
    lateinit var progress: ProgressDialog
    lateinit var progressBar: ProgressBar
    lateinit var category: Categorie
    lateinit var productsCategoryAdapter: ProductsCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_category)

        val bundle = intent.extras
        category = bundle.getSerializable(EXTRA_CATEGORIE) as Categorie

        val productsUseCase = ProductsUseCase()

        productsCategoryAdapter = ProductsCategoryAdapter(this, this::getMoreProducts)
        val productsList = rv_products_category
        productsList.layoutManager = LinearLayoutManager(this)
        productsList.adapter = productsCategoryAdapter

        presenter = ProductsCategoryPresenter(this, productsUseCase)
        presenter.getProducts(category.id, false)

        progressBar = pb_progress_bar

        setupToolbar(category.description)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.clearPages()
        super.onDestroy()
    }

    override fun setupComponentsView(products: List<Product>) {
        productsCategoryAdapter.addProducts(products.toMutableList())
    }

    override fun showProgress() {
        progress = ProgressDialog.show(this, getString(R.string.wait_please), getString(R.string.searching_informations))
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        progress?.dismiss()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun getMoreProducts() {
        presenter.getProducts(category.id, true)
    }

    private fun setupToolbar(description: String) {
        val toolbar: Toolbar = tb_generic_toolbar
        toolbar.title = description
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}