package br.com.cemobile.lodjinha.ui.product.list

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Resource
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.databinding.ActivityCategoryProductsListBinding
import br.com.cemobile.lodjinha.ui.product.detail.ProductDetailsActivity
import br.com.cemobile.lodjinha.ui.product.list.adapters.ProductsAdapter
import br.com.cemobile.lodjinha.util.EXTRA_CATEGORY_DESCRIPTION
import br.com.cemobile.lodjinha.util.EXTRA_CATEGORY_ID
import br.com.cemobile.lodjinha.util.ScrollDirection
import kotlinx.android.synthetic.main.app_bar.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CategoryProductsListActivity : AppCompatActivity() {

    private val categoryId by lazy { intent.getLongExtra(EXTRA_CATEGORY_ID, -1) }
    private val categoryDescription by lazy { intent.getStringExtra(EXTRA_CATEGORY_DESCRIPTION) }
    private val viewModel: CategoryProductsViewModel by viewModel { parametersOf(categoryId) }
    private lateinit var adapter: ProductsAdapter
    private lateinit var binding: ActivityCategoryProductsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_products_list)
        setupViews()
        bindingObserver()
        binding.executePendingBindings()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupViews() {
        setupToolbar(categoryDescription)
        setupProductsRecycler()
    }

    private fun setupToolbar(title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = title
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * Responsible to setup products' recycler view
     */
    private fun setupProductsRecycler() {
        adapter = ProductsAdapter(::showProductDetails)
        binding.productsRecycler.also { recyclerView ->
            recyclerView.addItemDecoration(DividerItemDecoration(this, VERTICAL))
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    adapter.scrollDirection = if (dy > 0) ScrollDirection.DOWN else ScrollDirection.UP
                }
            })
        }
    }

    private fun bindingObserver() {
        // connect observers with related live data
        viewModel.getProductsLiveData().observe(this, Observer { products ->
            showProducts(products)
        })

        viewModel.getLoadingLiveData().observe(this, Observer { isVisible ->
            binding.resource = isVisible?.let {
                if (it) {
                    Resource.loading(true)
                } else {
                    Resource.loading(false)
                }
            } ?: Resource.loading(false)
            binding.executePendingBindings()
        })
    }

    private fun showProducts(products: PagedList<Product>?) {
        products?.let {
            adapter.submitList(it)
            binding.resource = if (adapter.itemCount + it.size > 0) {
                Resource.success(it)
            } else {
                Resource.success<Product>(null)
            }
            binding.executePendingBindings()
        } ?: showEmptyList()
    }

    private fun showEmptyList() {
        binding.resource = Resource.success(null)
    }

    /**
     * Display product details information
     */
    private fun showProductDetails(view: View, product: Product) {
        val productImage = view.findViewById<ImageView>(R.id.productImage)
        val imagePair = Pair.create(productImage as View, "tImage")

        val productName = view.findViewById<TextView>(R.id.productNameText)
        val namePair = Pair.create(productName as View, "tName")

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imagePair, namePair)
        val intent = ProductDetailsActivity.launchIntent(this, product)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    companion object {
        fun launchIntent(from: Context, categoryId: Long, categoryDescription: String) =
                Intent(from, CategoryProductsListActivity::class.java).apply {
                    putExtra(EXTRA_CATEGORY_ID, categoryId)
                    putExtra(EXTRA_CATEGORY_DESCRIPTION, categoryDescription)
                }
    }

}
