package br.com.andrecouto.alodjinha.domain.factory.product

import br.com.andrecouto.alodjinha.DataFactory
import br.com.andrecouto.alodjinha.domain.factory.category.CategoryFactory
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product

object ProductFactory {

    fun makeProduct() : Product {
        return Product(DataFactory.randomInt(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomFloat(),
                DataFactory.randomFloat(), DataFactory.randomString(),
                CategoryFactory.makeCategory())
    }

    fun makeProductList(count: Int) : List<Product>{
        val products = mutableListOf<Product>()
        repeat(count) {
            products.add(makeProduct())
        }
        return products
    }
}