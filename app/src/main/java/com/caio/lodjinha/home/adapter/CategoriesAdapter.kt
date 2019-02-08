package com.caio.lodjinha.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caio.lodjinha.R
import com.caio.lodjinha.extensions.loadUrl
import com.caio.lodjinha.repository.entity.Category
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoriesAdapter(var categories: List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var onClick: (Category) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.imageViewCategory.loadUrl(category.urlImagem,holder.process)

        holder.textViewCategory.text = category.descricao
        holder.itemView.setOnClickListener { onClick(category) }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textViewCategory = view.tvCategory
        val imageViewCategory = view.ivCategory
        val process = view.progress

    }
}