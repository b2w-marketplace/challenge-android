package com.b2w.lodjinha.ui.productslist

import android.graphics.Paint
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.b2w.lodjinha.R
import com.b2w.lodjinha.data.model.ProductItem
import com.b2w.lodjinha.util.toCurrency
import com.squareup.picasso.Picasso

class ProductsListAdapter(
    var items: MutableList<ProductItem>,
    private val onItemClickListener: (items: MutableList<ProductItem>, position: Int, view: View) -> Unit
) : RecyclerView.Adapter<ProductsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListViewHolder {
        return ProductsListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_product_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsListViewHolder, position: Int) {
        holder.bind(items[position]) { view ->
            onItemClickListener(items, position, view)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = position

    fun addItems(newItems: List<ProductItem>) {
        items.addAll(newItems)
    }
}

open class ProductsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val container = view.findViewById<ConstraintLayout>(R.id.view_product_container)
    val imageview = view.findViewById<ImageView>(R.id.view_product_image)
    val description = view.findViewById<TextView>(R.id.view_product_description)
    val fromPrice = view.findViewById<TextView>(R.id.view_product_from_price)
    val toPrice = view.findViewById<TextView>(R.id.view_product_to_price)

    open fun bind(product: ProductItem, listener: (View) -> Unit) {
        description.text = product.description

        Picasso.get()
            .load(product.image)
            .placeholder(R.drawable.greyish_shape_oval)
            .error(R.drawable.greyish_shape_oval)
            .into(imageview)

        fromPrice.text = fromPrice.resources.getString(R.string.product_from_price, product.fromPrice.toCurrency())
        fromPrice.paintFlags = fromPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG

        toPrice.text = fromPrice.resources.getString(R.string.product_to_price, product.toPrice.toCurrency())

        container.setOnClickListener { listener(container) }
    }
}