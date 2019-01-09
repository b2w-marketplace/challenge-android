package test

import br.com.android.seiji.domain.model.Product

object ProductFactory {

    fun makeProduct(): Product {
        return Product(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(),
            DataFactory.randomDouble(), DataFactory.randomDouble(), DataFactory.randomString(),
            CategoryFactory.makeCategory()
        )
    }

    fun makeProductList(count: Int): List<Product> {
        val productList = mutableListOf<Product>()
        repeat(count) {
            productList.add(makeProduct())
        }
        return productList
    }
}