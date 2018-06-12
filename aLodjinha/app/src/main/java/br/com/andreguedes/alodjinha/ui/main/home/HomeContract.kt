package br.com.andreguedes.alodjinha.ui.main.home

import br.com.andreguedes.alodjinha.data.model.Banner
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.ui.base.BasePresenter
import br.com.andreguedes.alodjinha.ui.base.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun setupView()
        fun addListeners()
        fun setBanners(banners: List<Banner>)
        fun setCategories(categories: List<Category>)
        fun setProductsBestSellers(productsBestSellers: List<Product>)
    }

    interface Presenter : BasePresenter {
        fun getBanners()
        fun getCategories()
        fun getProductsBestSellers()
    }

}