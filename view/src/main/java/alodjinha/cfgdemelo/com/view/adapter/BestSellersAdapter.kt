package alodjinha.cfgdemelo.com.view.adapter

import alodjinha.cfgdemelo.com.model.BestSeller
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

class BestSellersAdapter(val context: Context, val bestSellers: List<BestSeller>, private val bestSellerClickListener: BestSellerClickListener): RecyclerView.Adapter<BestSellersAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BestSellersAdapter.RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_view_holder, parent, false)

        return RecyclerViewHolder(view, bestSellerClickListener)
    }

    override fun getItemCount(): Int {
        return bestSellers.count()
    }

    override fun onBindViewHolder(holder: BestSellersAdapter.RecyclerViewHolder, position: Int) {
        holder.bindData(context, bestSellers, position)
    }

    class RecyclerViewHolder(itemView: View, private val bestSellerClickListener: BestSellerClickListener): RecyclerView.ViewHolder(itemView) {
        private val bestSellerImage: ImageView = itemView.findViewById(R.id.ivProductPhoto)
        private val bestSellerName: TextView = itemView.findViewById(R.id.tvProductName)
        private val bestSellerPriceFrom: TextView = itemView.findViewById(R.id.priceFrom)
        private val bestSellerPriceFor: TextView = itemView.findViewById(R.id.priceFor)

        fun bindData(context: Context, bestSellers: List<BestSeller>, position: Int) {
            Picasso.with(context)
                .load(bestSellers[position].imageUrl)
                .into(bestSellerImage)
            bestSellerName.text = bestSellers[position].name
            bestSellerPriceFrom.text = bestSellers[position].priceFrom.toMoney()
            bestSellerPriceFrom.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            bestSellerPriceFor.text = bestSellers[position].priceFor.toMoney()
            itemView.setOnClickListener {
                bestSellerClickListener.getBestSeller(context, bestSellers[position])
            }
        }
    }

    interface BestSellerClickListener {
        fun getBestSeller(context: Context, bestSeller: BestSeller)
    }


}

fun Int.toMoney(): String {
    val formatter = DecimalFormat("##,####")
    val numberFormatted = formatter.format(this.toDouble())
    return "R$$numberFormatted,00"
}

fun Double.toMoney(): String {
    val formatter = DecimalFormat("##,####")
    val numberFormatted = formatter.format(this)
    return "R$$numberFormatted,00"
}
