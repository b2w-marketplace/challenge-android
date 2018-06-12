package com.example.lidjinha.lodjinha.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.lidjinha.lodjinha.R
import com.example.lidjinha.lodjinha.model.Product
import com.squareup.picasso.Picasso


class BestSellersAdapter(private val products: List<Product>,
                         private val context: Context) :
        RecyclerView.Adapter<BestSellersAdapter.ViewHolder>() {

    private val FOR_VALUE = "Por "
    private val BY_VALUE = "De: "


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_best_sellers_list, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(context).load(products[position].urlImage).into(holder.image)
        holder.name.text = products[position].name
        holder.fullPrice.text = BY_VALUE + String.format("%.2f", products[position].fullPrice).replace(".", ".")
        holder.fullPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.salePrice.text = FOR_VALUE + String.format("%.2f", products[position].salePrice).replace(".", ".")
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.iv_product)
        val name: TextView = itemView.findViewById(R.id.tv_product_name)
        val fullPrice: TextView = itemView.findViewById(R.id.tv_full_price)
        val salePrice: TextView = itemView.findViewById(R.id.tv_sale_price)

    }
}