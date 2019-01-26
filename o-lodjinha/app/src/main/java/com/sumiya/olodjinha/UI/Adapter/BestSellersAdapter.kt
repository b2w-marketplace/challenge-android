package com.sumiya.olodjinha.ui.adapter

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sumiya.olodjinha.model.ProductDataModel
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.R
import kotlinx.android.synthetic.main.view_product.view.*
import java.text.NumberFormat
import java.util.*

class ProductAdapter(private val products: ProductDataModel, val clickListener: (ProductModel) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val product = products.data[p1]

        p0.bindView(product,clickListener)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_product, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  products.data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(product: ProductModel, clickListener: (ProductModel) -> Unit) {
            val productDescription = itemView.descriptionLabel
            val productImage = itemView.productImage
            val oldPrice = itemView.oldPriceLabel
            val priceLabel = itemView.priceLabel

            productDescription.text = product.nome

            val format = NumberFormat.getCurrencyInstance(Locale("pt","BR"))
            val precoDe = format.format(product.precoDe)
            val precoPor = format.format(product.precoPor)

            oldPrice.text = "De $precoDe"
            oldPrice.setPaintFlags(oldPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

            priceLabel.text = "Por $precoPor"

            Glide
                    .with(itemView)
                    .load(product.urlImagem)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.ic_error_outline_black_24dp))
                    .into(productImage)

            itemView.setOnClickListener { clickListener(product) }
        }
    }
}