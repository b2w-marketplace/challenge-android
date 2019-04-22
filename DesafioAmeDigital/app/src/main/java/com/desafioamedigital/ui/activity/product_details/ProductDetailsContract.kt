package com.desafioamedigital.ui.activity.product_details

import com.desafioamedigital.ui.base.BaseContract

class ProductDetailsContract{

    interface View: BaseContract.BaseView

    interface Presenter: BaseContract.BasePresenter<View>{
        fun reserveProduct(productId: Int)
    }

}