package br.com.andrecouto.alodjinha.domain.repository

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {
    fun getTopSellingProducts() : Single<List<Product>>

    fun getProducts(categoryId: Int) : Single<List<Product>>

    fun getProduct(productId: Int) : Single<Product>

    fun retainProduct(productId: Int) : Completable
}