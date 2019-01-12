package br.com.android.seiji.mobileui.ui.productsByCategory

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.mobileui.R
import br.com.android.seiji.mobileui.di.ViewModelFactory
import br.com.android.seiji.mobileui.extensions.gone
import br.com.android.seiji.mobileui.mapper.ProductViewMapper
import br.com.android.seiji.mobileui.ui.productDetail.ProductDetailActivity
import br.com.android.seiji.mobileui.ui.productDetail.ProductDetailActivity.Companion.EXTRA_PRODUCT
import br.com.android.seiji.mobileui.ui.productsByCategory.adapter.ProductsByCategoryListAdapter
import br.com.android.seiji.mobileui.utils.EndlessScrollListener
import br.com.android.seiji.presentation.model.ProductView
import br.com.android.seiji.presentation.state.Resource
import br.com.android.seiji.presentation.state.ResourceState
import br.com.android.seiji.presentation.viewModel.GetProductsByCategoryIdViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_product_list.*
import timber.log.Timber
import javax.inject.Inject

class ProductsByCategoryListActivity : AppCompatActivity() {

    @Inject
    lateinit var productMapper: ProductViewMapper

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var productByCategoryListAdapter: ProductsByCategoryListAdapter
    private lateinit var extraCategory: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        AndroidInjection.inject(this)

        val category = intent.extras.getSerializable(EXTRA_CATEGORY) as Category
        extraCategory = category.copy()

        toolbarProductsByCategoryId.title = category.descricao
        setSupportActionBar(toolbarProductsByCategoryId)

        toolbarProductsByCategoryId.setTitleTextColor(ResourcesCompat.getColor(resources, android.R.color.white, null))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        initViewModel()
        configureRecyclerView()
        fetchProductsByCategory(0, LIMIT)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun configureRecyclerView() {
        val linearLayout = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )

        recyclerProductsByCategoryId.layoutManager = linearLayout
        recyclerProductsByCategoryId.addItemDecoration(
            DividerItemDecoration(this, linearLayout.orientation)
        )
        recyclerProductsByCategoryId.setHasFixedSize(true)
        recyclerProductsByCategoryId.isNestedScrollingEnabled = false

        productByCategoryListAdapter = ProductsByCategoryListAdapter(
            arrayListOf(),
            productOnClickListener
        )
        recyclerProductsByCategoryId.adapter = productByCategoryListAdapter

        recyclerProductsByCategoryId.addOnScrollListener(object :
            EndlessScrollListener(recyclerProductsByCategoryId.layoutManager as LinearLayoutManager) {

            override fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int) {}

            override fun onLoadMore(currentPage: Int, totalItemCount: Int) {
                progressBarBottomProductsByCategoryId.visibility = View.VISIBLE
//                Handler().postDelayed({
                val itemCount = recyclerProductsByCategoryId.adapter!!.itemCount
                fetchProductsByCategory(itemCount, itemCount + LIMIT)
//                }, 500)
            }
        })
    }

    private fun initViewModel() {
        getProductsByCategoryIdViewModel.getProductsByCategoryId().observe(this,
            Observer<Resource<List<ProductView>>> {
                it?.let {
                    handleProductsByCategoryIdDataState(it)
                }
            })
    }

    private fun fetchProductsByCategory(offset: Int, limit: Int) {
        getProductsByCategoryIdViewModel.fetchProductsByCategoryId(extraCategory.id, offset, limit)
    }

    private fun handleProductsByCategoryIdDataState(resource: Resource<List<ProductView>>) {
        Timber.i(resource.status.toString())
        when (resource.status) {
            ResourceState.LOADING -> {
            }

            ResourceState.SUCCESS -> {
                setProductsByCategoryIdSuccess(resource.data?.map { productMapper.mapToView(it) })
            }

            ResourceState.ERROR -> {
                Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun setProductsByCategoryIdSuccess(products: List<Product>?) {
        progressBarBottomProductsByCategoryId.gone()
        progressBarProductsByCategoryId.gone()
        productByCategoryListAdapter.addProducts(products!!)
        productByCategoryListAdapter.notifyDataSetChanged()
    }

    private val getProductsByCategoryIdViewModel: GetProductsByCategoryIdViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GetProductsByCategoryIdViewModel::class.java)
    }

    private val productOnClickListener = { product: Product, _: View ->
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra(EXTRA_PRODUCT, product)
        startActivity(intent)
    }

    companion object {
        const val LIMIT = 20
        const val EXTRA_CATEGORY = "EXTRA_CATEGORY"
    }

}