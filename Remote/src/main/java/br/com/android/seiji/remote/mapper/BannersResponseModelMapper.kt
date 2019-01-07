package br.com.android.seiji.remote.mapper

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.remote.model.BannerModel
import javax.inject.Inject

class BannersResponseModelMapper @Inject constructor() : ModelMapper<BannerModel, BannerEntity> {

    override fun mapFromModel(model: BannerModel): BannerEntity {
        return BannerEntity(
            model.id, model.urlImagem, model.linkUrl
        )
    }
}