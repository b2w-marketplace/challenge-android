package br.com.andrecouto.alodjinha.data.source.remote.product

import br.com.andrecouto.alodjinha.data.source.remote.service.Service
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
        val service: Service
) : ProductRemoteDataSource{


    override fun retainProduct(productId: Int): Completable {
        return service.retainProduct(productId)
    }


    override fun getProductDetailById(productId: Int): Single<Product> {
        return service.getProductDetailById(productId)
    }

    override fun getTopSellingProducts(): Single<List<Product>> {
        return service
                .getTopSellingProducts()
                .map { it.data }
    }

    override fun getProductList(categoryId: Int): Single<List<Product>> {
        return service
                .getProducts(categoryId)
                .map { it.data }
    }
}