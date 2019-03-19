package com.danilodequeiroz.lodjinha.home.domain

import com.danilodequeiroz.lodjinha.common.util.bannersMapper
import com.danilodequeiroz.lodjinha.common.util.categoriesMapper
import com.danilodequeiroz.lodjinha.common.util.productsMapper
import com.danilodequeiroz.webapi.LodjinhaRestRepository
import io.reactivex.Single

class HomeInteractor(private val lodjinhaRestRepository: LodjinhaRestRepository) : HomeUseCase {

    override fun getBanners(): Single<MutableList<BannerViewModel>> {
        return lodjinhaRestRepository.getBanners().map { payload -> payload.bannersMapper() }
    }

    override fun getProductCategories(): Single<MutableList<ProductCategoryViewModel>> {
        return lodjinhaRestRepository.getProductCategories().map { payload -> payload.categoriesMapper() }
    }

    override fun getAllBestSellingProducts(): Single<MutableList<ProductViewModel>> {
        return lodjinhaRestRepository.getBestSellingProducts().map { payload -> payload.productsMapper() }
    }

}
