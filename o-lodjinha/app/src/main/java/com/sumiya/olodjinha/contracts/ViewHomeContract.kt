package com.sumiya.olodjinha.contracts

import com.sumiya.olodjinha.contracts.base.ViewBaseContract
import com.sumiya.olodjinha.model.BannerDataModel

interface ViewHomeContract : ViewBaseContract {
    fun setBannerResponse(banners: BannerDataModel)
}