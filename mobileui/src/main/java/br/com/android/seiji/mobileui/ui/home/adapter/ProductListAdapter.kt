package br.com.android.seiji.mobileui.ui.home.adapter

import android.graphics.Paint
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.mobileui.R
import br.com.android.seiji.mobileui.utils.toCurrency
import com.squareup.picasso.Picasso

class ProductListAdapter(
    var productList: List<Product>,
    private val onItemClickListener: (item: List<Product>, position: Int, view: View) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    override fun getItemCount(): Int = productList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_best_seller_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(productList[position]) { view ->
            onItemClickListener(productList, position, view)
        }
    }

    open class ProductListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val itemLayoutContainer = view.findViewById<ConstraintLayout>(R.id.layoutItemProductLayout)
        private val imageProduct = view.findViewById<ImageView>(R.id.imageProduct)
        private val textProductName = view.findViewById<TextView>(R.id.textProductName)
        private val textProductPreviousPrice = view.findViewById<TextView>(R.id.textProductPreviousPrice)
        private val textProductCurrentPrice = view.findViewById<TextView>(R.id.textProductCurrentPrice)

        open fun bind(product: Product, listener: (View) -> Unit) {
            Picasso.get()
                .load(product.urlImagem)
                .error(R.drawable.img_not_found_primary)
                .into(imageProduct)

            textProductName.text = product.nome

            textProductPreviousPrice.text = textProductPreviousPrice.resources.getString(
                R.string.product_previous_price,
                product.precoDe.toCurrency()
            )
            textProductPreviousPrice.paintFlags = textProductPreviousPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            textProductCurrentPrice.text = product.precoPor.toCurrency()

            itemLayoutContainer.setOnClickListener { listener(itemLayoutContainer) }
        }
    }
}