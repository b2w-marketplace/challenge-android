package br.com.android.seiji.data.test.factory

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.domain.model.Banner

object BannerFactory {

    fun makeBannerEntity(): BannerEntity {
        return BannerEntity(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeBanner(): Banner {
        return Banner(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }
}