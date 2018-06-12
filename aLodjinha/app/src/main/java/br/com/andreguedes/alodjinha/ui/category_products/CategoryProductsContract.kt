package br.com.andreguedes.alodjinha.ui.category_products

import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.ui.base.BasePresenter
import br.com.andreguedes.alodjinha.ui.base.BaseView

interface CategoryProductsContract {

    interface View : BaseView<Presenter> {
        fun setProducts(productsList: List<Product>)
    }

    interface Presenter : BasePresenter {
        fun getProductsFromCategory(offset: Int)
    }

}