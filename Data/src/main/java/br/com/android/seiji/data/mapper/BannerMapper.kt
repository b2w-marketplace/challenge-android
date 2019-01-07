package br.com.android.seiji.data.mapper

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.domain.model.Banner
import javax.inject.Inject

class BannerMapper @Inject constructor() : EntityMapper<BannerEntity, Banner> {

    override fun mapFromEntity(entity: BannerEntity): Banner {
        return Banner(
            entity.id, entity.urlImagem, entity.linkUrl
        )
    }

    override fun mapToEntity(domain: Banner): BannerEntity {
        return BannerEntity(
            domain.id, domain.urlImagem, domain.linkUrl
        )
    }
}