package br.com.android.seiji.cache.test.factory

import br.com.android.seiji.cache.model.CachedProduct
import br.com.android.seiji.data.model.ProductEntity

object ProductDataFactory {

    fun makeCachedProduct(): CachedProduct {
        return CachedProduct(
                DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
                CategoryDataFactory.makeCachedCategory()
        )
    }

    fun makeProductEntity(): ProductEntity {
        return ProductEntity(
                DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
                CategoryDataFactory.makeCategoryEntity())
    }
}