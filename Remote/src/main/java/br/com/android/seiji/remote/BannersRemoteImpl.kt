package br.com.android.seiji.remote

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.repository.banners.BannersRemote
import br.com.android.seiji.remote.mapper.BannersResponseModelMapper
import br.com.android.seiji.remote.service.BannerService
import io.reactivex.Flowable
import javax.inject.Inject

class BannersRemoteImpl @Inject constructor(
    private val service: BannerService,
    private val mapper: BannersResponseModelMapper
) : BannersRemote {

    override fun getBanners(): Flowable<List<BannerEntity>> {
        return service.getBanners()
            .map { it ->
                it.data.map {
                    mapper.mapFromModel(it)
                }
            }
    }
}