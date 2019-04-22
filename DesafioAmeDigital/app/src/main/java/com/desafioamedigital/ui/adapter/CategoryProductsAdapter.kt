package com.desafioamedigital.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.desafioamedigital.R
import com.desafioamedigital.model.dto.Product
import com.desafioamedigital.ui.activity.category.CategoryActivity
import com.desafioamedigital.util.loadImage
import com.desafioamedigital.util.priceDecimalFormat
import com.desafioamedigital.util.recyclerview.RecyclerViewOnClickListener
import com.desafioamedigital.util.setStrikeThrough
import kotlinx.android.synthetic.main.item_product.view.*

class CategoryProductsAdapter(private val context: CategoryActivity, private var productList: List<Product>)
    : RecyclerView.Adapter<CategoryProductsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, p0, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(productList[position])

    override fun getItemCount(): Int = productList.size

    fun updateProducts(newProducts: List<Product>){
        productList += newProducts
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        fun bind(item: Product){
            with(itemView){
                tv_best_seller_product.text = item.name
                iv_best_seller_product.loadImage(item.imageUrl, 250)
                tv_price_to.text = context.resources.getString(R.string.best_sellers_adapter_to, item.priceTo.priceDecimalFormat())
                tv_price_from.text = context.resources.getString(R.string.best_sellers_adapter_from, item.priceFrom.priceDecimalFormat())
                tv_price_from.setStrikeThrough()
            }

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            context.onItemClick(adapterPosition)
        }
    }
}