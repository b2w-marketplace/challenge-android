package alodjinha.cfgdemelo.com.view.adapter

import alodjinha.cfgdemelo.com.model.Product
import alodjinha.cfgdemelo.com.view.R
import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class ProductsAdapter(val context: Context, val products: List<Product>, private val productClickListener: ProductClickListener): RecyclerView.Adapter<ProductsAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ProductsAdapter.RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_view_holder, parent, false)

        return RecyclerViewHolder(view, productClickListener)
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onBindViewHolder(holder: ProductsAdapter.RecyclerViewHolder, position: Int) {
        holder.bindData(context, products, position)
    }

    class RecyclerViewHolder(itemView: View, private val productClickListener: ProductClickListener): RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.ivProductPhoto)
        private val productName: TextView = itemView.findViewById(R.id.tvProductName)
        private val productPriceFrom: TextView = itemView.findViewById(R.id.priceFrom)
        private val productPriceFor: TextView = itemView.findViewById(R.id.priceFor)

        fun bindData(context: Context, products: List<Product>, position: Int) {
            Picasso.with(context)
                .load(products[position].imageUrl)
                .into(productImage)
            productName.text = products[position].name
            productPriceFrom.text = products[position].priceFrom.toMoney()
            productPriceFrom.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            productPriceFor.text = products[position].priceFor.toMoney()
            itemView.setOnClickListener {
                productClickListener.getProduct(context, products[position])
            }
        }
    }

    interface ProductClickListener {
        fun getProduct(context: Context, product: Product)
    }


}

fun Int.toMoney(): String {
    val formatter = DecimalFormat("#,##0.00")
    val numberFormatted = formatter.format(this.toDouble())
    return "R$$numberFormatted"
}

fun Double.toMoney(): String {
    val formatter = DecimalFormat("#,##0.00")
    val numberFormatted = formatter.format(this)
    return "R$$numberFormatted"
}
