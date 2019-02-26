package br.com.andrecouto.alodjinha.data.source.remote.product

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRemoteDataSource {
    fun getTopSellingProducts() : Single<List<Product>>

    fun getProductList(categoryId: Int) : Single<List<Product>>

    fun getProductDetailById(productId: Int) : Single<Product>

    fun retainProduct(productId: Int) : Completable
}