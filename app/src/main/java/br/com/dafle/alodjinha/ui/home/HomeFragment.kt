package br.com.dafle.alodjinha.ui.home

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.model.Category
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.ui.products.ProductsActvity
import br.com.dafle.alodjinha.ui.products.details.ProductDetailsActivity
import br.com.dafle.alodjinha.util.BaseAdapter
import br.com.dafle.alodjinha.util.toCoin
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_product.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    val viewModel: HomeViewModel by viewModel()

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorState.observe(this, Observer {
            context?.alert {
                title = it
                also {
                    context?.setTheme(R.style.AlertDialogTheme)
                }
                okButton {
                    it.dismiss()
                }
            }?.show()
        })
        swipeRefresh.setOnRefreshListener { viewModel.fetch() }
        viewModel.progress.observe(this, Observer {
            val visibility = if (it) View.VISIBLE else View.GONE
            progressView.visibility = visibility
            swipeRefresh.isRefreshing = false
        })

        val adapter = BaseAdapter<Category>(R.layout.item_category) { category, v ->
            Glide.with(this).load(category.urlImagem).placeholder(R.drawable.placeholder).into(v.ivCategory)
            v.tvCategory.text = category.descricao
            v.setOnClickListener { this.navigateToProducts(category) }
        }
        categoriesRecyclerView.let {
            it.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            it.adapter = adapter
        }
        viewModel.categories.observe(this, Observer { adapter.setItems(it) })

        val bestSellerAdapter = BaseAdapter<Product>(R.layout.item_product) { product, v ->
            Glide.with(this).load(product.urlImagem).placeholder(R.drawable.placeholder).into(v.ivProduct)
            v.tvProductName.text = product.nome
            v.tvValueFrom.paintFlags = v.tvValueFrom.paintFlags or STRIKE_THRU_TEXT_FLAG
            v.tvValueFrom.text = product.precoDe.toCoin()
            v.tvValueTo.text = product.precoPor.toCoin()
            v.setOnClickListener { context?.startActivity<ProductDetailsActivity>(ProductDetailsActivity.EXTRA_PRODUCT to product) }
        }
        bestSellerRecyclerView.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = bestSellerAdapter
        }
        viewModel.bestSellers.observe(this, Observer { bestSellerAdapter.setItems(it) })

        viewModel.banners.observe(this, Observer {
            activity?.apply {
                viewPager.adapter = HomeBannerPageAdapter(supportFragmentManager, it.map { HomeBannerFragment.newInstance(it) })
            }
        })

        viewModel.fetch()
    }

    private fun navigateToProducts(category: Category) {
        activity?.startActivity<ProductsActvity>(ProductsActvity.EXTRA_CATEGORY to category)
    }
}