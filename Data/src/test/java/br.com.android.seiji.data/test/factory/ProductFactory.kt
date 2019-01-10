package br.com.android.seiji.data.test.factory

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.domain.model.Product

object ProductFactory {

    fun makeProductEntity(): ProductEntity {
        return ProductEntity(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
            CategoryFactory.makeCategoryEntity()
        )
    }

    fun makeProduct(): Product {
        return Product(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
            CategoryFactory.makeCategory()
        )
    }

    fun makeProductList(count: Int): List<ProductEntity> {
        val productList = mutableListOf<ProductEntity>()
        repeat(count) {
            productList.add(makeProductEntity())
        }
        return productList
    }
}