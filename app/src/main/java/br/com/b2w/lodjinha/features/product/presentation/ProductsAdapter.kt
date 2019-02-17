package br.com.b2w.lodjinha.features.product.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.b2w.lodjinha.R
import br.com.b2w.lodjinha.features.category.Category
import br.com.b2w.lodjinha.features.product.Product
import br.com.b2w.lodjinha.features.product.data.LoadingState
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter : PagedListAdapter<Product, RecyclerView.ViewHolder>(diffCallback) {

    private var loadingState: LoadingState? = null
    private var productSelectedListener: ((Product) -> Unit)? = null

    fun onProductSelected(param: (Product) -> Unit) {
        productSelectedListener = param
    }

    fun updateLoadingState(loadingState: LoadingState) {
        this.loadingState = loadingState
    }

    fun clear() {
        submitList(null)
        currentList?.dataSource?.invalidate()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == PRODUCT_ITEM) {
            ProductViewHolder(inflater.inflate(R.layout.product_item, parent, false))
        } else {
            LoadingViewHolder(inflater.inflate(R.layout.loading_item, parent, false))
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            getItem(position).also { if (it != null) holder.bind(it) }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (loadingState == LoadingState.LOADING && position == itemCount - 1) LOADING_ITEM else PRODUCT_ITEM

    private inner class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            with(view) {
                Picasso.get()
                    .load(product.urlImagem)
//                    .placeholder(br.com.b2w.lodjinha.R.drawable.ic_exclamation_circle_solid)
                    .into(productImageView)
                productNameTextView.text = product.name
                productOldPriceTextView.text = context.getString(R.string.product_old_price, product.oldPrice.toString())
                productNewPriceTextView.text = context.getString(R.string.product_new_price, product.newPrice.toString())
                setOnClickListener { productSelectedListener?.invoke(product) }
            }
        }

    }

    private class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        private const val PRODUCT_ITEM = 1
        private const val LOADING_ITEM = 2
        private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
        }
    }
}