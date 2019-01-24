package com.sumiya.olodjinha.UI.Adapter

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sumiya.olodjinha.Model.ProductDataModel
import com.sumiya.olodjinha.Model.ProductModel
import com.sumiya.olodjinha.R
import kotlinx.android.synthetic.main.view_product.view.*
import java.text.NumberFormat
import java.util.*
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG



class BestSellersAdapter(private val products: ProductDataModel) : RecyclerView.Adapter<BestSellersAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val product = products.data[p1]

        p0?.let {
            it.bindView(product)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_product, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  products.data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(product: ProductModel) {
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

            Glide.with(itemView).load(product.urlImagem).into(productImage)
        }
    }
}