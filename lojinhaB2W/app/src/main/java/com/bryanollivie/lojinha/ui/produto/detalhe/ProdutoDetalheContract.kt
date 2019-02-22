package com.bryanollivie.lojinha.ui.produto.detalhe

import android.content.Context
import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.ui.base.BaseMvpPresenter
import com.bryanollivie.lojinha.ui.base.BaseMvpView


object ProdutoDetalheContract {

    interface View : BaseMvpView {

        fun showProduto(produto: Produto)

        fun showMsg(msg: String)


    }

    interface Presenter : BaseMvpPresenter<View> {

        fun getProduto(produtoId: Int, context: Context)

        fun reserverProduto(produtoId: Int, context: Context)

    }
}
