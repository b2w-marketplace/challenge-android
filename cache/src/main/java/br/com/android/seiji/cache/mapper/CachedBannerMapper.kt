package br.com.android.seiji.cache.mapper

import br.com.android.seiji.cache.model.CachedBanner
import br.com.android.seiji.data.model.BannerEntity
import javax.inject.Inject

class CachedBannerMapper @Inject constructor() : CacheMapper<CachedBanner, BannerEntity> {

    override fun mapFromCached(type: CachedBanner): BannerEntity {
        return BannerEntity(
            type.id, type.urlImagem, type.linkUrl
        )
    }

    override fun mapToCached(type: BannerEntity): CachedBanner {
        return CachedBanner(
            type.id, type.urlImagem, type.linkUrl
        )
    }
}