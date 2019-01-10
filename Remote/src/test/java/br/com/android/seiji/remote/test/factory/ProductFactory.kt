package br.com.android.seiji.remote.test.factory

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.remote.model.ProductModel
import br.com.android.seiji.remote.model.ProductResponseModel

object ProductFactory {

    fun makeProductModel(): ProductModel {
        return ProductModel(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
            CategoryFactory.makeCategoryModel()
        )
    }

    fun makeProductEntity(): ProductEntity {
        return ProductEntity(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
            CategoryFactory.makeCategoryEntity()
        )
    }

    fun makeProductResponse(): ProductResponseModel {
        return ProductResponseModel(
            listOf(
                makeProductModel(),
                makeProductModel(), makeProductModel()
            )
        )
    }
}