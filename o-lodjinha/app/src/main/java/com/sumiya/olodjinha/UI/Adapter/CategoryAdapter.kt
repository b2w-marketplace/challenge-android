package com.sumiya.olodjinha.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.model.CategoryDataModel
import com.sumiya.olodjinha.model.CategoryModel
import kotlinx.android.synthetic.main.view_category.view.*

class CategoryAdapter(private val categories: CategoryDataModel, val clickListener: (CategoryModel) -> Unit) : Adapter<CategoryAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return categories.data.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context)
                .inflate(R.layout.view_category, p0, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val category = categories.data[p1]

        p0.bindView(category, clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(category: CategoryModel, clickListener: (CategoryModel) -> Unit) {

            val categoryTitle = itemView.categoryNameLabel
            val categoryImage = itemView.categoryImage

            categoryTitle.text = category.descricao

            Glide
                    .with(categoryImage.context)
                    .load(category.urlImagem)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.ic_error_outline_black_24dp))
                    .into(categoryImage)

            itemView.setOnClickListener { clickListener(category) }
        }
    }
}

