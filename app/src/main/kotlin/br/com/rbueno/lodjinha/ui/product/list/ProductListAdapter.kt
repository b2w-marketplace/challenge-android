package br.com.rbueno.lodjinha.ui.product.list

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.util.ImageLoader
import br.com.rbueno.lodjinha.util.toMoneyDisplay


class ProductListAdapter(private val items: ProductList) : RecyclerView.Adapter<ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_product_list_item, parent, false)
        return ProductListViewHolder(view)
    }

    override fun getItemCount() = items.data.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bindView(items.data[position])
    }

}

class ProductListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val imageProduct by lazy { view.findViewById<ImageView>(R.id.image_product) }
    private val textProductName by lazy { view.findViewById<TextView>(R.id.text_product_name) }
    private val textOldPrice by lazy { view.findViewById<TextView>(R.id.text_old_price) }
    private val textNewPrice by lazy { view.findViewById<TextView>(R.id.text_new_price) }

    fun bindView(product: Product) {
        with(product) {
            ImageLoader.loadImage(urlImage, imageProduct)
            textProductName.text = name

            val spanOldPrice = SpannableString(
                String.format(
                    view.context.getString(R.string.old_price_format),
                    oldPrice.toMoneyDisplay()
                )
            )
            spanOldPrice.setSpan(StrikethroughSpan(), 0, spanOldPrice.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            textOldPrice.text = spanOldPrice

            textNewPrice.text =
                String.format(view.context.getString(R.string.new_price_format), newPrice.toMoneyDisplay())
        }
    }
}