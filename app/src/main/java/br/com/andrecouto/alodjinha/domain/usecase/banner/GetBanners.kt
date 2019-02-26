package br.com.andrecouto.alodjinha.domain.usecase.banner

import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import br.com.andrecouto.alodjinha.domain.repository.BannerRepository
import br.com.andrecouto.alodjinha.domain.usecase.base.SingleUseCase
import br.com.andrecouto.alodjinha.domain.usecase.product.GetProductDetails
import io.reactivex.Single
import javax.inject.Inject

class GetBanners @Inject constructor(
        errorUtil: DomainErrorUtil,
        private val bannerRepository: BannerRepository) : SingleUseCase<List<Banner>, GetProductDetails.Params>(errorUtil) {

    override fun buildUseCaseObservable(params: GetProductDetails.Params?): Single<List<Banner>> {
        return bannerRepository.getBanners()
    }
}