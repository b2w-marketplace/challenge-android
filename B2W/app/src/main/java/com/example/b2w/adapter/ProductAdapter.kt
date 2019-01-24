package com.example.b2w.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.b2w.R
import com.example.b2w.model.Product
import kotlinx.android.synthetic.main.adapter_product.view.*

class ProductAdapter(private val context: Context, private val products: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_product, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(products[position].urlImagem).into(holder.imgProduct)
        holder.txtProductDescription.text = products[position].nome
        holder.txtProductBeforePrice.text = "De: ${products[position].precoDe.toString()}"
        holder.txtProductAfterPrice.text = "Por ${products[position].precoPor.toString()}"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgProduct: ImageView = view.img_product
        var txtProductDescription: TextView = view.txt_product_description
        var txtProductBeforePrice: TextView = view.txt_product_before_price
        var txtProductAfterPrice: TextView = view.txt_product_after_price
    }
}