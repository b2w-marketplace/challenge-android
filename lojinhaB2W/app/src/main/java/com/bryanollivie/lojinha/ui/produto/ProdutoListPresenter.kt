package com.bryanollivie.lojinha.ui.produto

import android.content.Context
import com.blankj.utilcode.util.NetworkUtils
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.remote.ServiceApi
import com.bryanollivie.lojinha.data.remote.ServiceImpl
import com.bryanollivie.lojinha.ui.base.BasePresenter

class ProdutoListPresenter : BasePresenter<ProdutoListContract.View>(), ProdutoListContract.Presenter {

    private val mApi = ServiceImpl()

    override fun getProdutos(categoriaId: Int, context: Context) {

        getView()!!.showLoading()
        if (NetworkUtils.isConnected()) {
            mApi.produtoFindByCategoria(categoriaId, object : ServiceApi.ServiceCallback<ReturnBase> {
                override fun onLoaded(return_service: ReturnBase) {

                    if (return_service.data != null) {
                        getView()?.showProdutos(return_service.data!!)
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


