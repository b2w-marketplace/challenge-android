package br.com.android.seiji.data.test.factory

import br.com.android.seiji.data.model.ProductEntity

object ProductFactory {

    fun makeProduct(): ProductEntity {
        return ProductEntity(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
            CategoryFactory.makeCategoryEntity()
        )
    }

    fun makeProductList(count: Int): List<ProductEntity> {
        val productList = mutableListOf<ProductEntity>()
        repeat(count) {
            productList.add(makeProduct())
        }
        return productList
    }
}