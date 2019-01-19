package br.com.cemobile.data.local.mapper

import br.com.cemobile.data.local.database.BannerEntity
import br.com.cemobile.domain.model.Banner

object ToBanner {

    operator fun invoke(entity: BannerEntity) = Banner(
        id = entity.id,
        imageUrl =  entity.imageUrl,
        linkUrl = entity.linkUrl
    )

}