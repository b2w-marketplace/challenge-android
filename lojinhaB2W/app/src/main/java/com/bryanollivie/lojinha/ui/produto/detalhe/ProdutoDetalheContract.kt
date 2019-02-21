package com.bryanollivie.lojinha.ui.produto.detalhe

import android.content.Context
import com.bryanollivie.lojinha.ui.base.BaseMvpPresenter
import com.bryanollivie.lojinha.ui.base.BaseMvpView


object ProdutoDetalheContract {

    interface View : BaseMvpView {

        fun showProduto(produto: Any)
    }

    interface Presenter : BaseMvpPresenter<View> {

        fun getProduto(produtoId: Int, context: Context)

    }
}
