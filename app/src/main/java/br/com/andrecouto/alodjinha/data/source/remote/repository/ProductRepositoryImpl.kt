package br.com.andrecouto.alodjinha.data.source.remote.repository

import br.com.andrecouto.alodjinha.data.source.remote.product.ProductRemoteDataSource
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

//TODO Local Data Source
class ProductRepositoryImpl @Inject constructor(
    var produtoRemoteDataSource: ProductRemoteDataSource
): ProductRepository {

    override fun retainProduct(productId: Int): Completable {
        return produtoRemoteDataSource.retainProduct(productId)
    }

    override fun getProduct(productId: Int): Single<Product> {
        return produtoRemoteDataSource.getProductDetailById(productId)
    }

    override fun getProducts(categoryId: Int): Single<List<Product>> {
        return produtoRemoteDataSource.getProductList(categoryId)
    }

    override fun getTopSellingProducts(): Single<List<Product>> {
        return produtoRemoteDataSource.getTopSellingProducts()
    }
}