package com.bryanollivie.lojinha.ui.produto.detalhe

import android.content.Context
import com.blankj.utilcode.util.NetworkUtils
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.remote.ServiceApi
import com.bryanollivie.lojinha.data.remote.ServiceImpl
import com.bryanollivie.lojinha.ui.base.BasePresenter


class ProdutoDetalhePresenter : BasePresenter<ProdutoDetalheContract.View>(), ProdutoDetalheContract.Presenter {

    private val mApi = ServiceImpl()

    override fun getProduto(produtoId: Int, context: Context) {

        getView()!!.showLoading()
        if (NetworkUtils.isConnected()) {
            mApi.produtoFindById(produtoId, object : ServiceApi.ServiceCallback<Produto> {
                override fun onLoaded(return_service: Produto) {

                    if (return_service != null) {
                        getView()?.showProduto(return_service)
                    }
                    getView()!!.hideLoading()
                }
            })
        } else {
            getView()?.hideLoading()
            getView()?.showError(R.string.no_connection)
        }

    }

    override fun reserverProduto(produtoId: Int, context: Context) {
        getView()!!.showLoading()
        if (NetworkUtils.isConnected()) {
            mApi.reservarProdutoById(produtoId, object : ServiceApi.ServiceCallback<ReturnBase> {
                override fun onLoaded(return_service: ReturnBase) {

                    if (return_service != null) {
                        getView()?.showMsg(return_service.result.toString())
                    }
                    getView()!!.hideLoading()
                }
            })
        } else {
            getView()?.hideLoading()
            getView()?.showError(R.string.no_connection)
        }
    }

}


