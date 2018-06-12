package br.com.andreguedes.alodjinha.ui.main.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.helper.StringHelper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*

class HomeBestSellerProductsAdapter(val listener: OnBestSellerProductSelected) : RecyclerView.Adapter<HomeBestSellerProductsAdapter.HomeBestSellerProductsViewHolder>() {

    private var bestSellerProducts = arrayListOf<Product>()

    fun setBestSellerProducts(bestSellerProductsList: List<Product>) {
        this.bestSellerProducts.clear()
        this.bestSellerProducts.addAll(bestSellerProductsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBestSellerProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return HomeBestSellerProductsViewHolder(view)
    }

    override fun getItemCount() = bestSellerProducts.size

    override fun onBindViewHolder(holder: HomeBestSellerProductsViewHolder, position: Int) {
        holder.bindItem(bestSellerProducts[position])
    }

    inner class HomeBestSellerProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(product: Product) {
            Glide.with(itemView.context).load(product.urlImagem).error(R.drawable.logo_menu).into(itemView.img_product)
            itemView.txt_product_name.text = product.nome
            itemView.txt_price_from.text = String.format(
                    itemView.context.getString(R.string.price_from),
                    StringHelper.formatCurrencyNew(product.precoDe!!))
            itemView.txt_price_to.text = String.format(
                    itemView.context.getString(R.string.price_to),
                    StringHelper.formatCurrencyNew(product.precoPor!!))

            itemView.setOnClickListener {
                listener.bestSellerProductSelected(product)
            }
        }

    }

    interface OnBestSellerProductSelected {
        fun bestSellerProductSelected(product: Product)
    }

}