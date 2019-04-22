package com.desafioamedigital.ui.activity.home

import com.desafioamedigital.model.viewmodel.HomeViewModel
import com.desafioamedigital.ui.base.BaseContract

class HomeContract {

    interface View : BaseContract.BaseView {
        fun showHomeData(viewModel: HomeViewModel)
        fun showContent()
        fun hideContent()
    }

    interface Presenter : BaseContract.BasePresenter<View>{
        fun getApiResults()
    }

}