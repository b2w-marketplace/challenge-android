package br.com.andreguedes.alodjinha.ui.category_products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import br.com.andreguedes.alodjinha.BuildConfig
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.ui.base.BaseActivity
import br.com.andreguedes.alodjinha.ui.product.detail.ProductDetailActivity
import kotlinx.android.synthetic.main.activity_category_products.*

class CategoryProductsActivity : BaseActivity(), CategoryProductsContract.View, OnItemClickListener {

    override lateinit var presenter: CategoryProductsContract.Presenter

    private lateinit var onScrollListener: RecyclerView.OnScrollListener

    private var categoryProductsAdapter = CategoryProductsAdapter(this)
    private lateinit var category: Category

    private var offset = 0

    companion object {

        const val EXTRA_CATEGORY = "br.com.andreguedes.alodjinha.ui.category.CategoryProductsActivity.EXTRA_CATEGORY"

        fun getStartIntent(context: Context, category: Category): Intent {
            val intent = Intent(context, CategoryProductsActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY, category)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_products)

        category = intent.getSerializableExtra(EXTRA_CATEGORY) as Category

        initToolbar(R.id.toolbar_normal)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initUI()
        addListeners()
    }

    override fun onResume() {
        super.onResume()

        supportActionBar!!.title = category.descricao
    }

    override fun onPause() {
        super.onPause()

        presenter.unsubscribe()
    }

    override fun initUI() {
        presenter = CategoryProductsPresenter(this, category.id!!)
        presenter.getProductsFromCategory(offset)

        val layoutManager = LinearLayoutManager(this)

        products_list.itemAnimator = DefaultItemAnimator()
        products_list.layoutManager = layoutManager
        products_list.adapter = categoryProductsAdapter

        onScrollListener = object : CategoryProductsEndlessRecyclerOnScrollListerner(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                offset += BuildConfig.LIMIT_OFFSET
                presenter.getProductsFromCategory(offset)
            }
        }
        products_list.addOnScrollListener(onScrollListener)
    }

    override fun addListeners() { }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun setProducts(productsList: List<Product>) {
        progress_products.visibility = View.GONE

        categoryProductsAdapter.setItens(productsList, true)
    }

    override fun onItemClick(view: View, o: Any?) {
        if (o is Product) {
            startActivity(ProductDetailActivity.getStartIntent(this, o))
        }
    }

}