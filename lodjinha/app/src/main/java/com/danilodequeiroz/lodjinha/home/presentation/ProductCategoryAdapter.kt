package com.danilodequeiroz.lodjinha.home.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danilodequeiroz.lodjinha.R
import com.danilodequeiroz.lodjinha.home.domain.ProductCategoryViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product_category.view.*
import java.lang.Exception


class ProductCategoryAdapter(
    private val banners: MutableList<ProductCategoryViewModel>, private val onCategoryClick : ProductCategoryViewHolder.OnCategoryClick) : RecyclerView.Adapter<ProductCategoryViewHolder>() {



    override fun onBindViewHolder(holder: ProductCategoryViewHolder, position: Int) = holder.bind(banners[position])

    override fun getItemCount(): Int = banners.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoryViewHolder =
        ProductCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_category, parent, false), onCategoryClick)

}


class ProductCategoryViewHolder(itemView: View, private val onCategoryClick: OnCategoryClick) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: ProductCategoryViewModel) {
        loadImage(item)
    }

    fun loadImage(item: ProductCategoryViewModel){
        itemView.apply {
            progressBar.visibility = View.VISIBLE
            txtProductCategoryDesc.text = item.descricao

            Picasso.get().load(item.urlImagem).error(R.drawable.ic_generic_category).into(imgProductCategory, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                }
            })

            this.setOnClickListener { onCategoryClick.onCategoryClicked(this, item) }
        }

    }

    interface OnCategoryClick {
        fun onCategoryClicked(view: View, productCategory: ProductCategoryViewModel)
    }

}