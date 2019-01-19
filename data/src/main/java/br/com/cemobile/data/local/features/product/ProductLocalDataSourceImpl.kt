package br.com.cemobile.data.local.features.product

import br.com.cemobile.data.local.database.LodjinhaDao
import br.com.cemobile.data.local.errors.LocalDataNotFoundException
import br.com.cemobile.data.local.mapper.ToProduct
import br.com.cemobile.data.local.mapper.ToProductEntity
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class ProductLocalDataSourceImpl(
    private val dao: LodjinhaDao,
    private val ioContext: CoroutineContext = Dispatchers.IO
) : ProductLocalDataSource {

    override suspend fun getBestSellingProducts(): Result<List<Product>> = withContext(ioContext) {
        val entities = dao.bestSellingProducts()
        return@withContext if (entities.isNotEmpty()) {
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
    ): Result<List<Product>> = withContext(ioContext) {
        val entities = dao.products()
        return@withContext  if (entities.isNotEmpty()) {
            val products = entities.map { ToProduct(it) }
            Result.Success(products)
        } else {
            Result.Error(LocalDataNotFoundException())
        }
    }

    override suspend fun getProductById(id: Long): Result<Product> = withContext(ioContext) {
        val entity = dao.product(id)
        val product = ToProduct(entity)
        return@withContext Result.Success(product)
    }

    override suspend fun save(products: List<Product>) {
        if (products.isEmpty()) {
            Timber.e("Não há products para salvar no database")
            return
        }

        val entities = products.map { ToProductEntity(it) }

        withContext(ioContext) {
            dao.saveProducts(entities)
        }
    }

    override suspend fun save(product: Product) {
        val entity = ToProductEntity(product)

        withContext(ioContext) {
            dao.saveProduct(entity)
        }
    }

    override suspend fun deleteAll() = withContext(ioContext) {
        dao.deleteAllProducts()
    }

}