package com.desafioamedigital.ui.activity.category

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.desafioamedigital.R
import com.desafioamedigital.model.dto.Category
import com.desafioamedigital.model.dto.Product
import com.desafioamedigital.ui.activity.product_details.ProductDetailsActivity
import com.desafioamedigital.ui.adapter.CategoryProductsAdapter
import com.desafioamedigital.ui.base.BaseActivity
import com.desafioamedigital.util.Extras
import com.desafioamedigital.util.jsonToObject
import com.desafioamedigital.util.objectToJson
import com.desafioamedigital.util.recyclerview.RecyclerViewOnClickListener
import kotlinx.android.synthetic.main.content_category.*
import kotlinx.android.synthetic.main.toolbar_default.*
import javax.inject.Inject

class CategoryActivity : BaseActivity(), CategoryContract.View, RecyclerViewOnClickListener {

    @Inject lateinit var presenter: CategoryContract.Presenter

    private lateinit var category: Category
    private lateinit var products: List<Product>
    private lateinit var productsAdapter: CategoryProductsAdapter

    private val LIMIT_PRODUCTS = 20
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        initDagger2()
        attachPresenter()
        setRecyclerViewScrollListener()
        setCategory()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearDisposable()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initDagger2() = DaggerCategoryComponent.builder()
        .categoryModule(CategoryModule(this))
        .build()
        .inject(this)

    private fun attachPresenter(){
        presenter.attachView(this)
    }

    private fun setCategory(){
        category = jsonToObject(intent.getStringExtra(Extras.EXTRA_CATEGORY))
        configureToolbar(category.description)
        getProducts()
    }

    private fun getProducts(){
        presenter.getProducts(category.id, offset, LIMIT_PRODUCTS)
    }

    override fun showEmptyView() {
        rv_products.visibility = View.GONE
        tv_empty_list.visibility = View.VISIBLE
    }

    override fun listProducts(products: List<Product>) {
        this.products = products
        offset += products.size

        rv_products.visibility = View.VISIBLE
        tv_empty_list.visibility = View.GONE

        productsAdapter = CategoryProductsAdapter(this, products)
        rv_products.adapter = productsAdapter
    }

    override fun addProducts(products: List<Product>) {
        this.products += products
        offset += products.size
        productsAdapter.updateProducts(products)
    }

    override fun showBottomProgress() {
        progress_products.visibility = View.VISIBLE
    }

    override fun hideBottomProgress() {
        progress_products.visibility = View.GONE
    }

    override fun onItemClick(postion: Int) {
        val intent = Intent(getContext(), ProductDetailsActivity::class.java)
        intent.putExtra(Extras.EXTRA_PRODUCT, objectToJson(products[postion]))
        startActivity(intent)
    }

    private fun setRecyclerViewScrollListener(){
        rv_products.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0 && isRecyclerViewMaxScrolled()){
                    getProducts()
                }
            }
        })
    }

    private fun isRecyclerViewMaxScrolled(): Boolean{
        val maxScroll = rv_products.computeVerticalScrollRange()
        val currentScroll = rv_products.computeVerticalScrollOffset() + rv_products.computeVerticalScrollExtent()
        return currentScroll >= maxScroll
    }
}
