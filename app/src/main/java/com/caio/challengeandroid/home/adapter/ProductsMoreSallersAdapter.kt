package com.caio.lodjinha.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.caio.challengeandroid.R
import com.caio.lodjinha.extensions.formatNumberReal
import com.caio.lodjinha.extensions.loadUrl
import com.caio.lodjinha.extensions.priceFrom
import com.caio.lodjinha.repository.entity.Product
import kotlinx.android.synthetic.main.item_products.view.*

class ProductsMoreSallersAdapter : RecyclerView.Adapter<ProductsMoreSallersAdapter.ViewHolder>() {

    var onClick: (Product) -> Unit = {}

    private var mProducts: List<Product>? = null

    fun setListProducts(products: List<Product>){
        this.mProducts = products
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_products, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mProducts?.get(position)?.let { product ->
            holder.imageViewProduct.loadUrl(product.urlImagem,holder.progress)

            holder.textViewProductName.text = product.nome

            holder.textViewPriceFrom.priceFrom()
            holder.textViewPriceFrom.text = product.precoDe.formatNumberReal()
            holder.textViewPriceBy.text = product.precoPor.formatNumberReal()
            holder.itemView.setOnClickListener { onClick(product) }
        }
    }

    override fun getItemCount(): Int {
        return mProducts!!.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageViewProduct = view.ivProduct
        val textViewProductName = view.tvProductName
        val textViewPriceFrom = view.tvPriceFrom
        val textViewPriceBy = view.tvPriceBy
        val progress = view.progress
    }
}