package com.sumiya.olodjinha.ui.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.ui.adapter.viewHolder.ProductViewHolder

class ProductsAdapter(val clickListener: (ProductModel) -> Unit) :
        PagedListAdapter<ProductModel, ProductViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_product, p0, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        val product = getItem(p1)

        if (product != null) {
            p0.bindView(product, clickListener)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(p0: ProductModel, p1: ProductModel): Boolean {
                return p0.id == p1.id
            }

            override fun areContentsTheSame(p0: ProductModel, p1: ProductModel): Boolean {
                return p0 == p1
            }
        }
    }
}