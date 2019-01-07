package br.com.android.seiji.cache.test.factory

import br.com.android.seiji.cache.model.CachedBanner
import br.com.android.seiji.data.model.BannerEntity

object BannerDataFactory {

    fun makeCachedBanner(): CachedBanner {
        return CachedBanner(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeBannerEntity(): BannerEntity {
        return BannerEntity(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }
}