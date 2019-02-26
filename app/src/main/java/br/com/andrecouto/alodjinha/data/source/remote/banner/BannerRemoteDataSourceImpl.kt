package br.com.andrecouto.alodjinha.data.source.remote.banner

import br.com.andrecouto.alodjinha.data.source.remote.service.Service
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import io.reactivex.Single
import javax.inject.Inject

class BannerRemoteDataSourceImpl @Inject constructor(
        var service: Service
) : BannerRemoteDataSource {

    override fun getBanners(): Single<List<Banner>> {
        return service.getBanners()
                .map {
                    it.data
                }
    }
}