package com.eric.alodjinha.features.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eric.alodjinha.R
import com.eric.alodjinha.features.product.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_products.view.*

class ProductListAdapter(var categories: List<Product>) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var onClick: (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_products, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = categories[position]

        Picasso.get()
            .load(product.urlImagem)
            .placeholder(R.drawable.shopping_bag)
            .error(R.drawable.warning)
            .into(holder.imageViewProduct)

        holder.textViewProductName.text = product.nome
        holder.textViewPriceFrom.text = product.precoDe.toString()
        holder.textViewPriceBy.text = product.precoPor.toString()
        holder.itemView.setOnClickListener { onClick(product) }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageViewProduct = view.imageViewProduct
        val textViewProductName = view.textViewProductName
        val textViewPriceFrom = view.textViewPriceFrom
        val textViewPriceBy = view.textViewPriceBy
    }
}