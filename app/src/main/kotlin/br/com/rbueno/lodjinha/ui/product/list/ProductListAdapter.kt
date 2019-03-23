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


class ProductListAdapter(
    private val items: List<Product>,
    private val clickListener: (product: Product) -> Unit
) :
    RecyclerView.Adapter<ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_product_list_item, parent, false)
        return ProductListViewHolder(view, clickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bindView(items[position])
    }

}

private const val TYPE_ITEM = 0
private const val TYPE_LOADING = 1

class ProductPagedListAdapter(
    private val items: MutableList<Product>,
    private val clickListener: (product: Product) -> Unit

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var hasMoreItems = true

    override fun getItemViewType(position: Int) = if (hasMoreItems && position == items.size) {
        TYPE_LOADING
    } else TYPE_ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_LOADING) {
            LoadingViewHolder(
                layoutInflater.inflate(
                    R.layout.view_product_loading_item, parent,
                    false
                )
            )
        } else {
            ProductListViewHolder(
                layoutInflater.inflate(R.layout.view_product_list_item, parent, false),
                clickListener
            )
        }
    }

    override fun getItemCount(): Int {
        if (hasMoreItems) return items.size + 1
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductListViewHolder) {
            holder.bindView(items[position])
        }
    }

    fun addPage(page: List<Product>, hasMoreItems: Boolean) {
        this.hasMoreItems = hasMoreItems
        items.addAll(page)
        notifyDataSetChanged()
    }
}

class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

class ProductListViewHolder(
    private val view: View,
    private val clickListener: (product: Product) -> Unit
) : RecyclerView.ViewHolder(view) {

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

        view.setOnClickListener { clickListener.invoke(product) }
    }
}