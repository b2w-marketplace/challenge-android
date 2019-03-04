package br.com.andrecouto.alodjinha.domain.factory.banner

import br.com.andrecouto.alodjinha.DataFactory
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner

object BannerFactory {

    fun makeBanner() : Banner {
        return Banner(DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString())
    }

    fun makeBannerList(count: Int) : List<Banner>{
        val banners = mutableListOf<Banner>()
        repeat(count) {
            banners.add(makeBanner())
        }
        return banners
    }
}