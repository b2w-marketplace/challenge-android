package com.desafioamedigital.ui.activity.category

import com.desafioamedigital.model.dto.Product
import com.desafioamedigital.ui.base.BaseContract

class CategoryContract {

    interface View : BaseContract.BaseView {
        fun showEmptyView()
        fun listProducts(products: List<Product>)
        fun addProducts(products: List<Product>)
        fun showBottomProgress()
        fun hideBottomProgress()
    }

    interface Presenter : BaseContract.BasePresenter<View>{
        fun getProducts(categoryId: Int, offset: Int, limit: Int)
    }

}