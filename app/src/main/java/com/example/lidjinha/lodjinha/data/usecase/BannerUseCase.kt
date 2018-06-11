package com.example.lidjinha.lodjinha.data.usecase

import com.example.lidjinha.lodjinha.data.remote.repository.BannerRepository
import com.example.lidjinha.lodjinha.model.Banner
import kotlin.reflect.KFunction1

class BannerUseCase : BannerUseCaseContract {

    override fun getBanners(onBannersRetrieved: KFunction1<@ParameterName(name = "banners") List<Banner>, Unit>) {
        BannerRepository.instance.getBanners(onBannersRetrieved)
    }

}