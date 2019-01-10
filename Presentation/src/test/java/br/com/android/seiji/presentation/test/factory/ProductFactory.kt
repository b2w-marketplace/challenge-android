package br.com.android.seiji.presentation.test.factory

import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.presentation.model.ProductView

object ProductFactory {

    fun makeProductView(): ProductView {
        return ProductView(
                DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
                CategoryFactory.makeCategoryView()
        )
    }

    fun makeProduct(): Product {
        return Product(
                DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
                CategoryFactory.makeCategory())
    }

    fun makeProductViewList(count: Int): List<ProductView> {
        val products = mutableListOf<ProductView>()
        repeat(count) {
            products.add(makeProductView())
        }
        return products
    }

    fun makeProductList(count: Int): List<Product> {
        val products = mutableListOf<Product>()
        repeat(count) {
            products.add(makeProduct())
        }
        return products
    }
}