package br.com.cemobile.lodjinha.ui.product.list.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cemobile.domain.model.Product
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.databinding.ListItemProductBinding
import br.com.cemobile.lodjinha.extensions.applyScrollFromBottomAnimation
import br.com.cemobile.lodjinha.ui.product.list.ProductViewModel
import br.com.cemobile.lodjinha.util.ScrollDirection

class ProductsAdapter(
    private val itemClickListener: ((View, Product) -> Unit)?
) : PagedListAdapter<Product, ProductsAdapter.ViewHolder>(ProductsDiffCallback()) {

    var scrollDirection = ScrollDirection.DOWN

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_product, parent, false
            )
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getItem(position)?.let { product ->
            with(viewHolder) {
                itemView.tag = product
                bind(product, itemClickListener)
            }
        }
    }

    inner class ViewHolder(
        private val binding: ListItemProductBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, itemClickListener: ((View, Product) -> Unit)?) {
            with(binding) {
                viewModel = ProductViewModel(binding.root.context, product)
                rootLayout.setOnClickListener { view ->
                    itemClickListener?.invoke(view, product)
                }
                executePendingBindings()
            }
            applyScrollFromBottomAnimation(binding.root, scrollDirection)
        }

    }

}
