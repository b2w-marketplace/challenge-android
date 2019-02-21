package com.bryanollivie.lojinha.ui.produto.detalhe

import android.content.Context
import com.blankj.utilcode.util.NetworkUtils
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.remote.ServiceApi
import com.bryanollivie.lojinha.data.remote.ServiceImpl
import com.bryanollivie.lojinha.ui.base.BasePresenter


class ProdutoDetalhePresenter : BasePresenter<ProdutoDetalheContract.View>(), ProdutoDetalheContract.Presenter {

    private val mApi = ServiceImpl()

    override fun getProduto(produtoId: Int, context: Context) {

        getView()!!.showLoading()
        if (NetworkUtils.isConnected()) {
            mApi.produtoFindById(produtoId, object : ServiceApi.ServiceCallback<ReturnBase> {
                override fun onLoaded(return_service: ReturnBase) {

                    if (return_service.data != null) {
                        getView()?.showProduto(return_service.data!!)
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


