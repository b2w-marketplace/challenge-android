package com.bryanollivie.lojinha.ui.produto

import android.content.Context
import com.bryanollivie.lojinha.ui.base.BaseMvpPresenter
import com.bryanollivie.lojinha.ui.base.BaseMvpView


object ProdutoListContract {

    interface View : BaseMvpView {

        fun showProdutos(produtos: List<Any>)
    }

    interface Presenter : BaseMvpPresenter<View> {

        fun getProdutos(categoriaId:Int,context: Context)

    }
}
