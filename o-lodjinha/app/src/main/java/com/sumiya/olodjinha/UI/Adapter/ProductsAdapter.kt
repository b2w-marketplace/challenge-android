package com.sumiya.olodjinha.UI.Adapter

import android.arch.paging.PagedListAdapter
import android.graphics.Paint
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sumiya.olodjinha.Model.ProductDataModel
import com.sumiya.olodjinha.Model.ProductModel
import com.sumiya.olodjinha.R
import kotlinx.android.synthetic.main.view_product.view.*
import java.text.NumberFormat
import java.util.*


class ProductsAdapter(val clickListener: (ProductModel) -> Unit):
        PagedListAdapter<ProductModel, ProductsAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_product, p0, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        val product = getItem(p1)

        if (product != null) {
            p0.bindView(product,clickListener)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(p0: ProductModel, p1: ProductModel): Boolean {
                return p0.id == p1.id
            }

            override fun areContentsTheSame(p0: ProductModel, p1: ProductModel): Boolean {
                return p0 == p1
            }
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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