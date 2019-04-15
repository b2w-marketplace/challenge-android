package br.com.dafle.alodjinha.ui.products

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.base.BaseActivity
import br.com.dafle.alodjinha.model.Category
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.ui.products.details.ProductDetailsActivity
import br.com.dafle.alodjinha.util.BaseAdapter
import br.com.dafle.alodjinha.util.toCoin
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.item_product.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsActvity : BaseActivity() {

    val viewModel: ProductsViewModel by viewModel()
    var category: Category? = null
    var isScrolling = false

    companion object {
        val EXTRA_CATEGORY = "EXTRA_CATEGORY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        category = intent.extras?.getParcelable<Category>(EXTRA_CATEGORY)?.apply {
            title = descricao
            viewModel.fetchProducts(id)
        }

        swipeRefresh.setOnRefreshListener {
            category?.let {
                viewModel.fetchProducts(it.id, true)
            }
        }

        viewModel.progress.observe(this, Observer {
            val visibility = if (it) View.VISIBLE else View.GONE
            progressCenter.visibility = visibility
            progressBarBottom.visibility = visibility
            swipeRefresh.isRefreshing = false
        })

        viewModel.errorState.observe(this, Observer {
            alert { title = it; okButton { it.dismiss() } }.also {
                setTheme(R.style.AlertDialogTheme)
            }.show()
        })

        val adapter = BaseAdapter<Product>(R.layout.item_product) { product, v ->
            Glide.with(this).load(product.urlImagem).placeholder(R.drawable.placeholder).into(v.ivProduct)
            v.tvProductName.text = product.nome
            v.tvValueFrom.paintFlags = v.tvValueFrom.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            v.tvValueFrom.text = product.precoDe.toCoin()
            v.tvValueTo.text = product.precoPor.toCoin()
            v.setOnClickListener { startActivity<ProductDetailsActivity>(ProductDetailsActivity.EXTRA_PRODUCT to product) }
        }
        val manager = LinearLayoutManager(this)
        recyclerView.let {
            it.layoutManager = manager
            it.adapter = adapter
        }

        viewModel.items.observe(this, Observer {
            adapter.setItems(it)
            tvEmpty.text = if (viewModel.items.value?.size ?: 0 == 0) String.format(getString(R.string.products_activity_no_item_category), title) else ""
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && isScrolling && (manager.childCount + manager.findFirstVisibleItemPosition() == manager.itemCount && manager.itemCount <= viewModel.totalItemsApi)) {
                    progressBarBottom.visibility = View.VISIBLE
                    isScrolling = false
                    category?.let {
                        viewModel.fetchProducts(it.id)
                    }
                }
            }
        })
    }
}
