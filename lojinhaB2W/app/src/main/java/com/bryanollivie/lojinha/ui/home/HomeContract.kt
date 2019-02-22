package com.bryanollivie.lojinha.ui.home

import android.content.Context
import com.bryanollivie.lojinha.data.model.BannerLoja
import com.bryanollivie.lojinha.ui.base.BaseMvpPresenter
import com.bryanollivie.lojinha.ui.base.BaseMvpView

object HomeContract {

    interface View : BaseMvpView {

        fun showBanner(banners: List<Any>)

        fun showCategorias(categorias: List<Any>)

        fun showMaisVendidos(maisVendidos: List<Any>)
    }

    interface Presenter : BaseMvpPresenter<View> {

        fun getBanners(context: Context)

        fun getCategorias(context: Context)

        fun getMaisVendidos(context: Context)

    }
}
