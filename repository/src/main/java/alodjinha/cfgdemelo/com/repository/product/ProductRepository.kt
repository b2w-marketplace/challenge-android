package alodjinha.cfgdemelo.com.repository.product

import alodjinha.cfgdemelo.com.repository.api.LodjinhaApi

class ProductRepository {

    private val lodjinhaApi by lazy { LodjinhaApi() }

    fun getProduct(id: Int) = lodjinhaApi.getProductById(id)
    fun bookProduct(id: Int) = lodjinhaApi.bookProduct(id)
}