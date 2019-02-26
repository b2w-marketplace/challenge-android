package marcus.com.br.b2wtest.ui.categoryproduct

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_category_product.*
import kotlinx.android.synthetic.main.toolbar.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.model.data.CategoryData
import marcus.com.br.b2wtest.model.data.ProductData
import marcus.com.br.b2wtest.ui.BaseActivity
import marcus.com.br.b2wtest.ui.BaseRecyclerAdapter
import marcus.com.br.b2wtest.ui.ProductListAdapter
import marcus.com.br.b2wtest.ui.main.MainNavigator

class CategoryProductActivity : BaseActivity(), BaseRecyclerAdapter.OnItemClickListener {

    private lateinit var categoryData: CategoryData
    private val productListAdapter = ProductListAdapter()
    private var offset = 0

    private val categoryProductViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(CategoryProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_product)
        init()
    }

    private fun init() {
        categoryData = intent.getParcelableExtra("categoryData")
        setupToolbar()
        setupProductList()
        initObservers()
        categoryProductViewModel.getProductsByCategory(0 * 20, categoryData.id)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.findViewById<ImageView>(R.id.toolbarImage).visibility = View.GONE
        toolbar.findViewById<TextView>(R.id.toolbarTitle).text = categoryData.description
    }

    private fun setupProductList() {
        productListAdapter.listener = this
        activityCategoryProductList.adapter = productListAdapter
        activityCategoryProductList.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun initObservers() {
        categoryProductViewModel.loading.observe(this, Observer {

        })

        categoryProductViewModel.productResult.observeResource(this, onSuccess = {
            successProduct(it.productList)
        }, onError = {

        })
    }

    private fun successProduct(productList: List<ProductData>) {
        productListAdapter.addToList(productList as ArrayList<ProductData>)
    }

    override fun onItemClick(view: View, position: Int) {
        val productData = productListAdapter.getItem(position)
        MainNavigator.navigateToProductDetailActivity(applicationContext, productData)
    }
}