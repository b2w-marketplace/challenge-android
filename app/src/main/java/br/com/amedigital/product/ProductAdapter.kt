package br.com.amedigital.product

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.amedigital.R
import br.com.amedigital.model.Product
import br.com.amedigital.util.formatToMoney
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter (private var products: List<Product>?,
                      private val listener: (Int) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products?.get(position)
        holder.bind(product, position, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val constraintLayoutContent: ConstraintLayout = itemView.constraintLayoutContentProd
        private val imageViewProduct : ImageView = itemView.imageViewProductDesc
        private val textViewDescriptionProd : TextView = itemView.textViewDescr
        private val textViewDe : TextView = itemView.textViewDe
        private val textViewPor : TextView = itemView.textViewPor

        fun bind(product: Product?, position: Int, listener: (Int) -> Unit) {
            product?.apply {
                Picasso.get()
                    .load(product.urlImage)
                    .placeholder(R.mipmap.image_place_holder)
                    .into(imageViewProduct)
            }
            textViewDescriptionProd.text = product?.description
            textViewDe.text = "De ${product?.priceOf?.formatToMoney()}"
            textViewPor.text = "Por ${product?.priceFor?.formatToMoney()}"

            constraintLayoutContent.setOnClickListener {
                listener(position)
            }
        }
    }
}