package com.example.b2w.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.b2w.R
import com.example.b2w.activity.CategoryActivity
import com.example.b2w.adapter.ProductAdapter
import com.example.b2w.model.Product
import com.example.b2w.util.BUNDLE_CATEGORY
import com.example.b2w.util.URL_PRODUCT
import com.example.b2w.util.mountProducts
import com.example.b2w.util.showToast
import kotlinx.android.synthetic.main.fragment_category.view.*


class ProductFragment : Fragment() {
    private lateinit var mView: View
    private lateinit var products: MutableList<Product>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_category, container, false)

        retrieveProducts()

        return mView
    }

    fun itemClicked(position: Int){
        startActivity(
            Intent(activity!!, CategoryActivity::class.java)
                .putExtra(BUNDLE_CATEGORY, products[position].categoria?.descricao))
    }

    private fun retrieveProducts(){
        Volley.newRequestQueue(mView.context).add(object: StringRequest(
           Request.Method.GET,
            URL_PRODUCT,
            Response.Listener {
                products = mountProducts(it)

                mView.recycler_category.layoutManager = LinearLayoutManager(mView.context, LinearLayoutManager.VERTICAL, false)
                mView.recycler_category.adapter = ProductAdapter(activity!!, products)
            },
            Response.ErrorListener {
                showToast(view!!.context, "Ocorreu um erro ao tentar listar o banner.")
            }
        ){})
    }
}
