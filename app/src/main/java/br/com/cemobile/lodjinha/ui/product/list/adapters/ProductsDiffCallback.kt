package br.com.cemobile.lodjinha.ui.product.list.adapters

import android.support.v7.util.DiffUtil
import br.com.cemobile.domain.model.Product

class ProductsDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.description == newItem.description &&
        oldItem.name == newItem.name &&
        oldItem.category == newItem.category &&
        oldItem.imageUrl == newItem.imageUrl

}