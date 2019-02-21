package com.bryanollivie.lojinha.ui.home

import android.content.Context
import com.blankj.utilcode.util.NetworkUtils
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.remote.ServiceApi
import com.bryanollivie.lojinha.data.remote.ServiceImpl
import com.bryanollivie.lojinha.ui.base.BasePresenter

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private val mApi = ServiceImpl()

    override fun getCategorias(context: Context) {

        getView()!!.showLoading()
        if (NetworkUtils.isConnected()) {
            mApi.categoriaFindAll(object : ServiceApi.ServiceCallback<ReturnBase> {
                override fun onLoaded(return_service: ReturnBase) {

                    if (return_service.data != null) {

                        if (return_service.data is List)
                            getView()?.showCategorias(return_service.data!!)
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
                override fun onLoaded(return_service: ReturnBase) {

                    if (return_service?.data != null) {
                        getView()?.showMaisVendidos(return_service.data!!)
                    }
                    getView()!!.hideLoading()
                }
            })
        } else {
            getView()?.hideLoading()
            getView()?.showError(R.string.no_connection)
        }

    }
/*


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

*/

}


