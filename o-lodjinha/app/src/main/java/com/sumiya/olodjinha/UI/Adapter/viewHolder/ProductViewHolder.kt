package com.sumiya.olodjinha.ui.adapter.viewHolder

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.model.ProductModel
import kotlinx.android.synthetic.main.view_product.view.*
import java.text.NumberFormat
import java.util.*

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(product: ProductModel, clickListener: (ProductModel) -> Unit) {
        val productDescription = itemView.descriptionLabel
        val productImage = itemView.productImage
        val oldPrice = itemView.oldPriceLabel
        val priceLabel = itemView.priceLabel

        productDescription.text = product.nome

        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        val precoDe = format.format(product.precoDe)
        val precoPor = format.format(product.precoPor)

        oldPrice.text = String.format(itemView.context.resources.getString(R.string.old_price), precoDe)
        oldPrice.paintFlags = oldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        priceLabel.text = String.format(itemView.context.getString(R.string.price), precoPor)

        Glide
                .with(itemView)
                .load(product.urlImagem)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_error_outline_black_24dp))
                .into(productImage)

        itemView.setOnClickListener { clickListener(product) }
    }
}