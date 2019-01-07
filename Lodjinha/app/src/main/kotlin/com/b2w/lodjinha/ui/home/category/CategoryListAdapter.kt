package com.b2w.lodjinha.ui.home.category

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.b2w.lodjinha.R
import com.b2w.lodjinha.data.model.CategoryItem
import com.squareup.picasso.Picasso

class CategoryListAdapter(
    var items: List<CategoryItem>,
    private val onItemClickListener: (item: List<CategoryItem>, position: Int, view: View) -> Unit
) : RecyclerView.Adapter<CategoryListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        return CategoryListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_category_item, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        holder.bind(items[position]) { view ->
            onItemClickListener(items, position, view)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = position
}

open class CategoryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val container = view.findViewById<ConstraintLayout>(R.id.view_category_container)
    val imageview = view.findViewById<ImageView>(R.id.view_category_image)
    val description = view.findViewById<TextView>(R.id.view_category_text)

    open fun bind(category: CategoryItem, listener: (View) -> Unit) {
        description.text = category.description

        Picasso.get()
            .load(category.image)
            .placeholder(R.drawable.greyish_shape_oval)
            .error(R.drawable.greyish_shape_oval)
            .into(imageview)

        container.setOnClickListener { listener(container) }
    }
}