package com.sumiya.olodjinha.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.model.ProductDataModel
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.ui.adapter.viewHolder.ProductViewHolder

class BestSellersAdapter(private val products: ProductDataModel, private val clickListener: (ProductModel) -> Unit) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        val product = products.data[p1]

        p0.bindView(product, clickListener)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_product, p0, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.data.size
    }
}