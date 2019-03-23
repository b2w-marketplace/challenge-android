package br.com.rbueno.lodjinha.repository

import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.model.Category
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.network.LodjinhaApi
import io.reactivex.Single

interface HomeRepository {
    fun getBanner(): Single<Banner>
    fun getCategory(): Single<Category>
    fun getProductsMostSold(): Single<ProductList>
}

class NetworkHomeRepository(private val api: LodjinhaApi) : HomeRepository {
    override fun getCategory() = api.getCategory()

    override fun getProductsMostSold() = api.getProductsMostSold()

    override fun getBanner() = api.getBanner()
}