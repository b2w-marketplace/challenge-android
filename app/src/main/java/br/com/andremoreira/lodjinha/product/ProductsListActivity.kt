package br.com.andremoreira.lodjinha.product

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import br.com.andremoreira.lodjinha.R
import br.com.andremoreira.lodjinha.base.BaseActivity
import br.com.andremoreira.lodjinha.base.Constants
import br.com.andremoreira.lodjinha.di.ApplicationBase
import br.com.andremoreira.lodjinha.extensions.gone
import br.com.andremoreira.lodjinha.extensions.visible
import br.com.andremoreira.lodjinha.product.adapter.ProductListAdapter
import br.com.andremoreira.lodjinha.repository.entity.Product
import br.com.andremoreira.lodjinha.utils.EndlessRecyclerViewScrollListener
import br.com.andremoreira.lodjinha.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_list_products.*
import org.jetbrains.anko.startActivity

class ProductsListActivity : BaseActivity() {

    private val TAG: String = this::class.java.simpleName

    private val categoryId: Int by lazy { intent.getIntExtra(Constants.CATEGORY_ID,0)}
    private val categoryName: String? by lazy { intent.getStringExtra(Constants.CATEGORY_NAME)}

    private var productsListAdapter : ProductListAdapter? = null
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private val linearLayoutManager = LinearLayoutManager(this)

    private lateinit var productViewModel: ProductViewModel

    private val LIMITE_PRODUCT = 20
    private val mProducts: MutableList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_products)
        Log.d(TAG,"categoryId->"+categoryId)
        Log.d(TAG,"categoryName->"+categoryName)

        initViewModel()

        setupView()

        createProductList()
    }

    private fun initViewModel() {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        ApplicationBase.activityContext = this
    }

    private fun createProductList() {

        productsListAdapter = ProductListAdapter(mProducts)
        productsListAdapter?.onClick = {
            Log.d(TAG,"onClick->"+it.id+ categoryName)
            startActivity<ProductDetailActivity>(
                Constants.PRODUCT_ID to it.id,Constants.CATEGORY_NAME to it.categoria.descricao)
        }

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        rvProducts.layoutManager = linearLayoutManager
        rvProducts.addItemDecoration(dividerItemDecoration)
        rvProducts.adapter = productsListAdapter

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                getProductByCategoria(page)
            }
        }

        rvProducts.addOnScrollListener(scrollListener!!)

        getProductByCategoria()
    }

    private fun getProductByCategoria(offset: Int = 0) {
        productViewModel.getProductsByCategory(offset,LIMITE_PRODUCT,categoryId).observe(this, Observer {
            if (it!!.isSuccess()){
                refreshList(it.data!!.data)
            }
        })
    }

    private fun refreshList(products: List<Product>){
        mProducts.addAll(products)
        productsListAdapter?.notifyDataSetChanged()
        rvProducts.visible()
        Log.d(TAG,"mProducts->"+mProducts.size)
        if (!mProducts.isNotEmpty()) {
            rvProducts.gone()
            tvEmptyList.visible()
        }
    }

    private fun setupView() {
        setTitle(categoryName)
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