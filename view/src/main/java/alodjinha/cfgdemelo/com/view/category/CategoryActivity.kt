package alodjinha.cfgdemelo.com.view.category

import alodjinha.cfgdemelo.com.model.Product
import alodjinha.cfgdemelo.com.view.R
import alodjinha.cfgdemelo.com.view.adapter.ProductsAdapter
import alodjinha.cfgdemelo.com.view.product.ProductActivity
import alodjinha.cfgdemelo.com.viewmodel.category.CategoryViewModel
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity(), ProductsAdapter.ProductClickListener {

    private val categoryViewModel by lazy { CategoryViewModel() }
    private val compositeDisposable by lazy { CompositeDisposable() }
    private var products: ArrayList<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val categoryName = intent.getStringExtra(CATEGORY_NAME)
        val categoryId = intent.getIntExtra(CATEGORY_ID, 0)
        supportActionBar?.title = categoryName
        getProductsByCategoryId(categoryId)
    }

    private fun getProductsByCategoryId(categoryId: Int) {
        Thread {
            categoryViewModel.getProductsByCategoryId(categoryId)
        }.start()

        categoryViewModel.productsObservable.subscribe {
            runOnUiThread {
                if (products == null) {
                    products = it.products as ArrayList<Product>
                } else {
                    products!!.addAll(it.products)
                }

                if (products!!.isNotEmpty()) {
                    val adapter = rvProductsOfCategory.adapter
                    if (adapter == null) {
                        pbCategoryProducts.visibility = View.GONE
                        rvProductsOfCategory.layoutManager = LinearLayoutManager(this)
                        rvProductsOfCategory.adapter = ProductsAdapter(this, products!!, this@CategoryActivity)
                        setRecyclerViewListener(rvProductsOfCategory, categoryId)
                    } else {
                        pbCategoryProducts.visibility = View.GONE
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    ivEmpty.visibility = View.VISIBLE
                    pbCategoryProducts.visibility = View.GONE
                }
            }
        }.let { compositeDisposable.add(it) }
    }

    private fun setRecyclerViewListener(recyclerView: RecyclerView, categoryId: Int) {
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (dy > 0) {
                    val visibleItemCount = recyclerView.layoutManager!!.childCount
                    val totalItemCount = recyclerView.layoutManager!!.itemCount
                    val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItem >= totalItemCount) {
                        runOnUiThread {
                            pbCategoryProducts.visibility = View.VISIBLE
                        }
                        Thread {
                            categoryViewModel.getProductsByCategoryId(categoryId)
                        }.start()
                    }
                }
            }
        })
    }

    override fun getProduct(context: Context, product: Product) {
        startActivity(ProductActivity.getActivityIntent(this, product.id, product.imageUrl))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            HOME -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun getActivityIntent(context: Context, categoryId: Int, categoryName: String): Intent {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra(CATEGORY_ID, categoryId)
            intent.putExtra(CATEGORY_NAME, categoryName)
            return intent
        }
        private const val CATEGORY_ID = "categoryId"
        private const val CATEGORY_NAME = "categoryName"
        private const val HOME = 16908332
    }
}
