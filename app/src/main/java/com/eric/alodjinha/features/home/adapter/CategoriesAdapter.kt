package com.eric.alodjinha.features.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eric.alodjinha.R
import com.eric.alodjinha.features.home.model.Category
import com.squareup.picasso.Picasso
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

        Picasso.get()
            .load(category.urlImagem)
            .placeholder(R.drawable.shopping_bag)
            .error(R.drawable.warning)
            .into(holder.imageViewCategory)

        holder.textViewCategory.text = category.descricao
        holder.itemView.setOnClickListener { onClick(category) }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textViewCategory = view.textViewCategory
        val imageViewCategory = view.imageViewCategory

    }
}