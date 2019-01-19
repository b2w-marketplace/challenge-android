package br.com.cemobile.lodjinha.ui.product.list

import android.content.Context
import br.com.cemobile.domain.model.Product
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.extensions.formatToBrazilianCurrency
import br.com.cemobile.lodjinha.ui.base.BaseViewModel

class ProductViewModel(context: Context, product: Product) : BaseViewModel<Product>() {

    val name = product.name
    val description = product.description
    val imageUrl = product.imageUrl
    val fromPrice: String by lazy {
        context.getString(R.string.from_price, product.fromPrice.formatToBrazilianCurrency())
    }
    val byPrice: String by lazy {
        context.getString(R.string.by_price, product.byPrice.formatToBrazilianCurrency())
    }

}