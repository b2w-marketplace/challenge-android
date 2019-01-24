package com.sumiya.olodjinha.UI.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sumiya.olodjinha.Model.ProductDataModel
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.Service.APIService
import com.sumiya.olodjinha.UI.Adapter.ProductAdapter
import kotlinx.android.synthetic.main.fragment_best_sellers.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.DividerItemDecoration
import com.sumiya.olodjinha.Model.ProductModel


class BestSellersFragment : Fragment() {

    private var listener: BestSellersListener? = null

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

        val call = APIService().product().listTop()

        call.enqueue(object : Callback<ProductDataModel> {
            override fun onFailure(call: Call<ProductDataModel>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<ProductDataModel>?, response: Response<ProductDataModel>?) {
                if (response != null) {
                    val products = response.body()!!

                    bestSellersRecycler.adapter = ProductAdapter(
                            products,
                            { product : ProductModel -> productClicked(product) }
                    )

                    bestSellersRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    ViewCompat.setNestedScrollingEnabled(bestSellersRecycler, false)
                    bestSellersRecycler.addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
                }
            }
        })
    }

    private fun productClicked(product: ProductModel) {
        listener!!.openProductdetail(product)
    }

    interface BestSellersListener {
        fun openProductdetail(product: ProductModel)
    }
}
