package com.sumiya.olodjinha.presenter

import com.sumiya.olodjinha.contracts.PresenterProductDetailContract
import com.sumiya.olodjinha.contracts.ViewProductDetailContract
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.model.ReservationModel
import com.sumiya.olodjinha.service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailPresenter(val viewDetail: ViewProductDetailContract) : PresenterProductDetailContract {

    //Methods
    override fun getProduct(productId: Int) {
        val call = APIService().product().get(productId)

        call.enqueue(object : Callback<ProductModel> {
            override fun onFailure(call: Call<ProductModel>?, t: Throwable?) {
                viewDetail.hideLoading()

                if (t != null) {
                    viewDetail.handleError(t)
                }
            }

            override fun onResponse(call: Call<ProductModel>?, response: Response<ProductModel>?) {
                viewDetail.hideLoading()

                if (response != null) {
                    viewDetail.setProductResponse(response.body()!!)
                }
            }
        })
    }

    override fun setProductReservation(productId: Int) {
        val call = APIService().product().post(productId)

        call.enqueue(object : Callback<ReservationModel> {
            override fun onFailure(call: Call<ReservationModel>?, t: Throwable?) {
                if (t != null) {
                    viewDetail.handleError(t)
                }
            }

            override fun onResponse(call: Call<ReservationModel>?, response: Response<ReservationModel>?) {
                if (response != null) {
                    viewDetail.showReservationResponse()
                }
            }
        })
    }
}