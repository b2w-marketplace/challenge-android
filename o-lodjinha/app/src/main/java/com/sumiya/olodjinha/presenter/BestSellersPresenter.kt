package com.sumiya.olodjinha.presenter

import com.sumiya.olodjinha.contracts.PresenterBestSellersContract
import com.sumiya.olodjinha.contracts.ViewBestSellersFragmentContract
import com.sumiya.olodjinha.model.ProductDataModel
import com.sumiya.olodjinha.service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BestSellersPresenter(val viewBestSellers: ViewBestSellersFragmentContract) : PresenterBestSellersContract {
    //Methods
    override fun getBestSellers() {
        val call = APIService().product().listTop()

        call.enqueue(object : Callback<ProductDataModel> {
            override fun onFailure(call: Call<ProductDataModel>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<ProductDataModel>?, response: Response<ProductDataModel>?) {
                if (response != null) {
                    viewBestSellers.setSalesResponse(response.body()!!)
                }
            }
        })
    }
}