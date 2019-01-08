package br.com.android.seiji.presentation.mapper

import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.presentation.model.BannerView
import javax.inject.Inject

class BannerViewMapper @Inject constructor() : Mapper<BannerView, Banner> {

    override fun mapToView(type: Banner): BannerView {
        return BannerView(
            type.id, type.urlImagem, type.linkUrl
        )
    }
}