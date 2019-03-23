package br.com.rbueno.lodjinha.repository

import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.network.LodjinhaApi
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {
    fun getProduct(productId: Int): Single<Product>
    fun reserveProduct(productId: Int): Completable
}

class NetworkProductRepository(private val api: LodjinhaApi) : ProductRepository {
    override fun getProduct(productId: Int) = api.getProduct(productId)

    override fun reserveProduct(productId: Int) = api.reserveProduct(productId)
}