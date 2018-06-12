package br.com.andreguedes.alodjinha.ui.product

import android.view.View
import br.com.andreguedes.alodjinha.ui.category_products.CategoryProductsViewHolder

class NoProductsViewHolder(
        val view: View
) : CategoryProductsViewHolder<Any>(view) {

    override fun onCategoryProductsBindViewHolder(item: Any) {
        super.onBindViewHolder(item)
    }

}