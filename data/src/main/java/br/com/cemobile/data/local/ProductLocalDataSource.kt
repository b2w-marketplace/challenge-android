package br.com.cemobile.data.local

import br.com.cemobile.data.local.database.LodjinhaDao
import br.com.cemobile.data.local.mapper.ToProduct
import br.com.cemobile.data.local.mapper.ToProductEntity
import br.com.cemobile.data.source.LocalDataNotFoundException
import br.com.cemobile.data.source.ProductDataSource
import br.com.cemobile.data.source.Result
import br.com.cemobile.domain.model.Product

class ProductLocalDataSource(private val dao: LodjinhaDao) : ProductDataSource {

    override suspend fun getBestSellingProducts(): Result<List<Product>> {
        val entities = dao.bestSellingProducts()
        return if (entities.isNotEmpty()) {
            val products = entities.map { ToProduct(it) }
            Result.Success(products)
        } else {
            Result.Error(LocalDataNotFoundException())
        }
    }

    override suspend fun getAllProducts(
        offset: Int,
        limit: Int,
        categoryId: Long
    ): Result<List<Product>> {
        val entities = dao.products()
        return if (entities.isNotEmpty()) {
            val products = entities.map { ToProduct(it) }
            Result.Success(products)
        } else {
            Result.Error(LocalDataNotFoundException())
        }
    }

    override suspend fun getProductById(id: Long): Result<Product> {
        val productEntity = dao.product(id)
        val product =  ToProduct(productEntity)
        return Result.Success(product)
    }

    override fun save(products: List<Product>) {
        val entities = products.map { ToProductEntity(it) }
        dao.saveProducts(entities)
    }

    override fun save(product: Product) {
        val productEntity = ToProductEntity(product)
        dao.saveProduct(productEntity)
    }

    override fun deleteAll() {
        dao.deleteAllProducts()
    }

    override suspend fun reserve(productId: Long): Result<Boolean> =
        throw UnsupportedOperationException()

}