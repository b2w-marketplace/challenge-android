package br.com.android.seiji.remote.test.factory

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.remote.model.BannerModel
import br.com.android.seiji.remote.model.BannerResponseModel

object BannerFactory {

    fun makeBannerModel(): BannerModel {
        return BannerModel(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeBannerEntity(): BannerEntity {
        return BannerEntity(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeBannerResponse(): BannerResponseModel {
        return BannerResponseModel(listOf(makeBannerModel(), makeBannerModel(), makeBannerModel()))
    }

}