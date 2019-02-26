package br.com.andrecouto.alodjinha.data.source.remote.repository

import br.com.andrecouto.alodjinha.data.source.remote.banner.BannerRemoteDataSource
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import br.com.andrecouto.alodjinha.domain.repository.BannerRepository
import io.reactivex.Single
import javax.inject.Inject

//TODO Local Data Source
class BannerRepositoryImpl @Inject constructor(
    var bannerRemoteDataSource: BannerRemoteDataSource
): BannerRepository {

    override fun getBanners(): Single<List<Banner>> = bannerRemoteDataSource.getBanners()

}