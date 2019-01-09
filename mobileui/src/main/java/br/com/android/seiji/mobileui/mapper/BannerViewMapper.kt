package br.com.android.seiji.mobileui.mapper

import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.presentation.model.BannerView
import javax.inject.Inject

class BannerViewMapper @Inject constructor() : ViewMapper<BannerView, Banner> {
    override fun mapToView(presentation: BannerView): Banner {
        return Banner(
            presentation.id, presentation.urlImagem, presentation.linkUrl
        )
    }
}