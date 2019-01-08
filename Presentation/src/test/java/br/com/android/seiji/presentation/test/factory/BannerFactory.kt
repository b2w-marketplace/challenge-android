package br.com.android.seiji.presentation.test.factory

import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.presentation.model.BannerView

object BannerFactory {

    fun makeBannerView(): BannerView {
        return BannerView(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeBanner(): Banner {
        return Banner(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeBannerViewList(count: Int): List<BannerView> {
        val banners = mutableListOf<BannerView>()
        repeat(count) {
            banners.add(makeBannerView())
        }
        return banners
    }

    fun makeBannerList(count: Int): List<Banner> {
        val banners = mutableListOf<Banner>()
        repeat(count) {
            banners.add(makeBanner())
        }
        return banners
    }
}