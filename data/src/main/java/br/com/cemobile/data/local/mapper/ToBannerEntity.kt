package br.com.cemobile.data.local.mapper

import br.com.cemobile.data.local.database.BannerEntity
import br.com.cemobile.domain.model.Banner

object ToBannerEntity {

    operator fun invoke(banner: Banner) = BannerEntity(
        id = banner.id,
        imageUrl =  banner.imageUrl,
        linkUrl = banner.linkUrl
    )

}