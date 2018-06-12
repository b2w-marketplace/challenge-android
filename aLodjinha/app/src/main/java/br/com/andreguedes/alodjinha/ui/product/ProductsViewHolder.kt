package br.com.andreguedes.alodjinha.ui.product

import android.view.View
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.helper.StringHelper
import br.com.andreguedes.alodjinha.ui.category_products.CategoryProductsViewHolder
import br.com.andreguedes.alodjinha.ui.category_products.OnItemClickListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsViewHolder(view: View, private val listener: OnItemClickListener) : CategoryProductsViewHolder<Any>(view, listener) {

    override fun onCategoryProductsBindViewHolder(item: Any) {
        super.onBindViewHolder(item)

        if (item is Product) {
            Glide.with(itemView.context).load(item.urlImagem).error(R.drawable.logo_menu).into(itemView.img_product)
            itemView.txt_product_name.text = item.nome
            itemView.txt_price_from.text = String.format(
                    itemView.context.getString(R.string.price_from),
                    StringHelper.formatCurrencyNew(item.precoDe!!))
            itemView.txt_price_to.text = String.format(
                    itemView.context.getString(R.string.price_to),
                    StringHelper.formatCurrencyNew(item.precoPor!!))

            itemView.setOnClickListener {
                listener.onItemClick(itemView, item)
            }
        }
    }

}