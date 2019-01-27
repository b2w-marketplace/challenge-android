package com.sumiya.olodjinha.presenter

import com.sumiya.olodjinha.contracts.PresenterHomeContract
import com.sumiya.olodjinha.contracts.ViewHomeContract
import com.sumiya.olodjinha.model.BannerDataModel
import com.sumiya.olodjinha.service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(val homeView: ViewHomeContract) : PresenterHomeContract {
    //Methods
    override fun getBanners() {
        val call = APIService().banners().list()

        call.enqueue(object : Callback<BannerDataModel?> {
            override fun onFailure(call: Call<BannerDataModel?>?, t: Throwable?) {
                homeView.hideLoading()
                if (t != null) {
                    homeView.handleError(t)
                }
            }

            override fun onResponse(call: Call<BannerDataModel?>?, response: Response<BannerDataModel?>?) {
                homeView.hideLoading()
                if (response != null) {
                    homeView.setBannerResponse(response.body()!!)
                }
            }
        })
    }
}