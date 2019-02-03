package com.eric.alodjinha.features.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eric.alodjinha.R
import com.eric.alodjinha.base.Constants
import com.eric.alodjinha.features.product.adapter.ProductListAdapter
import com.eric.alodjinha.features.product.model.Product
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment(), ProductsFragmentView {

    val presenter: ProductsFragmentPresenter = ProductsFragmentPresenterImpl(this)
    val mProducts : MutableList<Product> = ArrayList()
    var productsAdapter : ProductListAdapter? = null


    companion object {

        var homeFragment: ProductsFragment? = null

        fun getInstance(categoryId: Int): ProductsFragment {

            if (homeFragment == null) {

                val extras = Bundle()
                extras.putInt(Constants.CANTEGORY_ID, categoryId)
                homeFragment = ProductsFragment()
                homeFragment?.arguments = extras
            }
            return homeFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(arguments?.get(Constants.CANTEGORY_ID) as Int)
    }

    override fun configureViews() {

        productsAdapter = ProductListAdapter(mProducts)

        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        recyclerViewProducts.layoutManager = LinearLayoutManager(context)
        recyclerViewProducts.addItemDecoration(dividerItemDecoration)
        recyclerViewProducts.adapter = productsAdapter

    }

    override fun receiveProducts(products: List<Product>) {

        mProducts.addAll(products)
        productsAdapter?.notifyDataSetChanged()

    }

    override fun onDestroy() {

        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showLoading() {

        //  progressBar.visible()
    }

    override fun hideLoading() {

        //   progressBar.gone()
    }
}