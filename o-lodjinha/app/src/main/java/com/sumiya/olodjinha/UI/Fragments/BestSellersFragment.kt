package com.sumiya.olodjinha.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.contracts.ViewBestSellersFragmentContract
import com.sumiya.olodjinha.model.ProductDataModel
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.presenter.BestSellersPresenter
import com.sumiya.olodjinha.ui.adapter.BestSellersAdapter
import kotlinx.android.synthetic.main.fragment_best_sellers.*

class BestSellersFragment : Fragment(), ViewBestSellersFragmentContract {
    //Variables
    private var listener: BestSellersListener? = null

    //Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_sellers, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BestSellersListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement BestSellersListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bestSellersPresenter = BestSellersPresenter(this)
        bestSellersPresenter.getBestSellers()
    }

    //Contract
    override fun setSalesResponse(products: ProductDataModel) {
        bestSellersRecycler.adapter = BestSellersAdapter(
                products,
                { product: ProductModel -> productClicked(product) }
        )

        bestSellersRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        ViewCompat.setNestedScrollingEnabled(bestSellersRecycler, false)
        bestSellersRecycler.addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
    }

    // Listeners
    private fun productClicked(product: ProductModel) {
        listener!!.openProductdetail(product)
    }

    //Fragment Listener
    interface BestSellersListener {
        fun openProductdetail(product: ProductModel)
    }
}
