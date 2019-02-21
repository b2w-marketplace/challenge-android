package com.bryanollivie.lojinha.ui.home

import android.content.Context
import com.bryanollivie.lojinha.data.model.BannerLoja
import com.bryanollivie.lojinha.data.model.Categoria
import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.ui.base.BaseMvpPresenter
import com.bryanollivie.lojinha.ui.base.BaseMvpView

object HomeContract {

    interface View : BaseMvpView {

        fun showBanner(banners: List<BannerLoja>)

        fun showCategorias(categorias: List<Categoria>)

        fun showMaisVendidos(maisVendidos: List<Produto>)
    }

    interface Presenter : BaseMvpPresenter<View> {

        fun getBanners(context: Context)

        fun getCategorias(context: Context)

        fun getMaisVendidos(context: Context)

    }
}
