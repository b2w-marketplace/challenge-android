package com.bryanollivie.lojinha.ui.home

import android.content.Context
import com.blankj.utilcode.util.NetworkUtils
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.BannerLoja
import com.bryanollivie.lojinha.data.model.Categoria
import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.remote.ServiceApi
import com.bryanollivie.lojinha.data.remote.ServiceImpl
import com.bryanollivie.lojinha.ui.base.BasePresenter


class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private val mApi = ServiceImpl()

    override fun getBanners(context: Context) {

        getView()!!.showLoading()
        if (NetworkUtils.isConnected()) {
            mApi.bannerFindAll(object : ServiceApi.ServiceCallback<ReturnBase> {
                override fun onLoaded(service_return: ReturnBase) {

                    if (service_return?.data != null) {
                        getView()?.showBanner(service_return.data as List<BannerLoja>)
                    }
                    getView()!!.hideLoading()
                }
            })
        } else {
            getView()?.hideLoading()
            getView()?.showError(R.string.no_connection)
        }

    }

    override fun getCategorias(context: Context) {

        getView()!!.showLoading()
        if (NetworkUtils.isConnected()) {
            mApi.categoriaFindAll(object : ServiceApi.ServiceCallback<ReturnBase> {
                override fun onLoaded(service_return: ReturnBase) {

                    if (service_return?.data != null) {
                        getView()?.showCategorias(service_return.data as List<Categoria>)
                    }
                    getView()!!.hideLoading()
                }
            })
        } else {
            getView()?.hideLoading()
            getView()?.showError(R.string.no_connection)
        }

    }

    override fun getMaisVendidos(context: Context) {

        getView()!!.showLoading()
        if (NetworkUtils.isConnected()) {
            mApi.produtoMaisVendidos(object : ServiceApi.ServiceCallback<ReturnBase> {
                override fun onLoaded(service_return: ReturnBase) {

                    if (service_return?.data != null) {
                        getView()?.showMaisVendidos(service_return.data as List<Produto>)
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


