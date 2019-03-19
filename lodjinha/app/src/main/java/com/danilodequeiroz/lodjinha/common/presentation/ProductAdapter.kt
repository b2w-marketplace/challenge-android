package com.danilodequeiroz.lodjinha.common.presentation

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danilodequeiroz.lodjinha.R
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*


class ProductAdapter(private val products: MutableList<ProductViewModel>, private val onCategoryClick: ProductViewHolder.OnProductClick) : RecyclerView.Adapter<ProductViewHolder>() {


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(products[position])

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
            ProductViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_product,
                            parent,
                            false
                    ), onCategoryClick
            )

}

class ProductViewHolder(itemView: View, private val onCategoryClick: OnProductClick) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: ProductViewModel) {
        loadImage(item)
    }

    fun loadImage(item: ProductViewModel) {
        itemView.apply {
            progressBar.visibility = View.VISIBLE
            txtProductName.text = item.nome
            txtOldPrice.text = resources.getString(R.string.old_price_label, item.precoDe)
            txtOldPrice.paintFlags = txtOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            txtPrice.text = resources.getString(R.string.new_price_label, item.precoPor)
            Picasso.get().load(item.urlImagem).error(R.drawable.ic_generic_category).into(imgProduct, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                }
            })

            this.setOnClickListener { onCategoryClick.onProductClicked(this, item) }
        }
    }

    interface OnProductClick {
        fun onProductClicked(view: View, product: ProductViewModel)
    }

}