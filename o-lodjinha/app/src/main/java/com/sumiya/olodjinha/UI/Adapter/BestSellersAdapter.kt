package com.sumiya.olodjinha.UI.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sumiya.olodjinha.Model.CategoryDataModel
import com.sumiya.olodjinha.Model.CategoryModel
import com.sumiya.olodjinha.Model.ProductDataModel
import com.sumiya.olodjinha.Model.ProductModel
import com.sumiya.olodjinha.R
import kotlinx.android.synthetic.main.view_category.view.*

class BestSellersAdapter(private val products: ProductDataModel) : RecyclerView.Adapter<BestSellersAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val product = products.data[p1]

        p0?.let {
            it.bindView(product)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_category, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  products.data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(category: ProductModel) {
            val categoryTitle = itemView.categoryNameLabel
            val categoryImage = itemView.categoryImage

            categoryTitle.text = category.descricao

            Glide.with(itemView).load(category.urlImagem).into(categoryImage)
        }
    }
}