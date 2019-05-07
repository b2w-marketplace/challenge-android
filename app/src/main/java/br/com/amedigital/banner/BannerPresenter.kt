package br.com.amedigital.banner

import br.com.amedigital.network.model.BodyRequestBanner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BannerPresenter constructor (var call: Call<BodyRequestBanner>, var view: BannerContract.View) : BannerContract.BannerActionListener{

    companion object {
        const val ERROR_LIST = "Empty list"
        const val ERROR_DEFAULT = "An error has occurred"
    }

    override fun loadBanners() {
        view.setProgressIndicatorBanner(true)
        call.enqueue(object : Callback<BodyRequestBanner> {

            override fun onResponse(call: Call<BodyRequestBanner>, response: Response<BodyRequestBanner>) {
                view.setProgressIndicatorBanner(false)
                if (response.isSuccessful) {
                    response.body()?.apply {
                        if (data.size > 0) {
                            view.showBanners(this.data)
                        } else {
                            view.showErrorBanner(ERROR_LIST)
                        }
                    } ?: kotlin.run {
                        view.showErrorBanner(ERROR_LIST)
                    }
                } else {
                    view.showErrorBanner(ERROR_DEFAULT)
                }
            }

            override fun onFailure(call: Call<BodyRequestBanner>, t: Throwable) {
                view.setProgressIndicatorBanner(false)
                t.message?.apply {
                    view.showErrorBanner(this)
                }
            }
        })
    }
}