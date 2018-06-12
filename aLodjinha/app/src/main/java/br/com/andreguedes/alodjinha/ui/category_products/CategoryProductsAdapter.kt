package br.com.andreguedes.alodjinha.ui.category_products

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreguedes.alodjinha.BuildConfig
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.ui.product.LoadingProductsViewHolder
import br.com.andreguedes.alodjinha.ui.product.NoProductsViewHolder
import br.com.andreguedes.alodjinha.ui.product.ProductsViewHolder

enum class CategoryProducstViewHolderType(val abreviation: String) {
    LOADING("L"), PRODUCT("P"), NO_PRODUCT("N")
}

class CategoryProductsAdapter(
        private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryProductsViewHolder<Any>>() {

    private val itens = arrayListOf<Any>()

    fun setItens(itens: List<Any>, loading: Boolean) {
        remoteLastItem()

        this.itens.addAll(itens)
        if (itens.isNotEmpty() && loading) {
            if (this.itens.size == BuildConfig.LIMIT_OFFSET) {
                this.itens.add(CategoryProducstViewHolderType.LOADING.abreviation)
            }
        }

        if (this.itens.isEmpty()) {
            this.itens.add(CategoryProducstViewHolderType.NO_PRODUCT.abreviation)
        }
        notifyDataSetChanged()
    }

    private fun remoteLastItem() {
        if (itemCount > 0) {
            this.itens.removeAt(this.itens.size - 1)
        }
    }

    override fun onBindViewHolder(holder: CategoryProductsViewHolder<Any>, position: Int) {
        val any = itens[position]
        holder.onCategoryProductsBindViewHolder(any)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductsViewHolder<Any> {
        val view: View

        if (viewType == CategoryProducstViewHolderType.PRODUCT.ordinal) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
            return ProductsViewHolder(view, listener)
        } else if (viewType == CategoryProducstViewHolderType.NO_PRODUCT.ordinal) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_no_products, parent, false)
            return NoProductsViewHolder(view)
        }

        view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
        return LoadingProductsViewHolder(view)
    }

    override fun getItemCount() = itens.size

    override fun getItemViewType(position: Int): Int {
        val any = itens[position]
        return when {
            any is Product -> CategoryProducstViewHolderType.PRODUCT.ordinal
            any.toString().equals(CategoryProducstViewHolderType.LOADING.abreviation) -> CategoryProducstViewHolderType.LOADING.ordinal
            any.toString().equals(CategoryProducstViewHolderType.NO_PRODUCT.abreviation) -> CategoryProducstViewHolderType.NO_PRODUCT.ordinal
            else -> -1
        }
    }

}