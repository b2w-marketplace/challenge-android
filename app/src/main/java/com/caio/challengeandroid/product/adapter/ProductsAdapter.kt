package com.caio.challengeandroid.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.caio.challengeandroid.R
import com.caio.lodjinha.extensions.formatNumberReal
import com.caio.lodjinha.extensions.loadUrl
import com.caio.lodjinha.extensions.priceFrom
import com.caio.lodjinha.repository.entity.Product
import kotlinx.android.synthetic.main.item_products.view.*

class ProductsAdapter : PagedListAdapter<Product, RecyclerView.ViewHolder>(diffCallback) {

    var onClick: (Product) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(
            inflater.inflate(R.layout.item_products, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            getItem(position).also { if (it != null) holder.bind(it) }
        }
    }

    fun clear() {
        submitList(null)
        currentList?.dataSource?.invalidate()
    }

    private inner class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            with(view) {
                ivProduct.loadUrl(product.urlImagem,progress)
                tvProductName.text = product.nome
                tvPriceFrom.priceFrom()
                tvPriceFrom.text = product.precoDe.formatNumberReal()
                tvPriceBy.text = product.precoPor.formatNumberReal()
                itemView.setOnClickListener { onClick(product) }
            }
        }

    }

    companion object {
        private const val PRODUCT_ITEM = 1
        private const val LOADING_ITEM = 2
        private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
        }
    }
}