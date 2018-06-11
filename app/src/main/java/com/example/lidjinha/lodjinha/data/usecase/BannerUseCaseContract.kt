package com.example.lidjinha.lodjinha.data.usecase

import com.example.lidjinha.lodjinha.model.Banner
import kotlin.reflect.KFunction1

interface BannerUseCaseContract {

    fun getBanners(onBannersRetrieved: KFunction1<List<Banner>, Unit>)

}