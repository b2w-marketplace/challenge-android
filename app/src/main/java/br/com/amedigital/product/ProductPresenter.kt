package br.com.amedigital.product

import br.com.amedigital.network.model.BodyRequestProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductPresenter : ProductContract.ProductActionListener{

    companion object {
        const val ERROR_DEFAULT = "An error has occurred"
        const val EMPTY_LIST = "Empty list"
        const val ERROR_RESERVE = "Could not save your product"
    }

    override fun loadProductsMoreSales(call: Call<BodyRequestProduct>, view: ProductContract.View) {
        view.setProgressProductMore(true)
        call.enqueue(object : Callback<BodyRequestProduct> {

            override fun onResponse(call: Call<BodyRequestProduct>, response: Response<BodyRequestProduct>) {
                view.setProgressProductMore(false)
                if (response.isSuccessful) {
                    response.body()?.apply {
                        if (data.size > 0) {
                            view.showProductsMore(data)
                        } else {
                            view.showEmpytProductMore(EMPTY_LIST)
                        }
                    } ?: kotlin.run {
                        view.showEmpytProductMore(EMPTY_LIST)
                    }
                } else {
                    view.showErrorProductMore(ERROR_DEFAULT)
                }
            }

            override fun onFailure(call: Call<BodyRequestProduct>, t: Throwable) {
                view.setProgressProductMore(false)
                t.message?.apply {
                    view.showErrorProductMore(this)
                }
            }
        })
    }

    override fun listProduct(call: Call<BodyRequestProduct>, view: ProductContract.ViewProductCategoryId) {
        view.setProgressProdCategoryId(true)
        call.enqueue(object : Callback<BodyRequestProduct> {

            override fun onResponse(call: Call<BodyRequestProduct>, response: Response<BodyRequestProduct>) {
                view.setProgressProdCategoryId(false)
                if (response.isSuccessful) {
                    response.body()?.apply {
                        if (data.size > 0) {
                            view.showProdCategoryId(data)
                        } else {
                            view.showEmpytProductCategory(EMPTY_LIST)
                        }
                    } ?: kotlin.run {
                        view.showEmpytProductCategory(EMPTY_LIST)
                    }
                } else {
                    view.showErrorProductCategoryId(ERROR_DEFAULT)
                }
            }

            override fun onFailure(call: Call<BodyRequestProduct>, t: Throwable) {
                view.setProgressProdCategoryId(false)
                t.message?.apply {
                    view.showErrorProductCategoryId(this)
                }
            }
        })
    }

    override fun reserve(call: Call<Void>, view: ProductContract.ViewReserveProduct) {
        view.setProgressReserve(true)
        call.enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                view.setProgressReserve(false)
                if (response.isSuccessful) {
                    view.successReserve()
                } else {
                    view.showErrorReserve(ERROR_RESERVE)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                view.setProgressReserve(false)
                t.message?.apply {
                    view.showErrorReserve(this)
                }
            }

        })
    }
}