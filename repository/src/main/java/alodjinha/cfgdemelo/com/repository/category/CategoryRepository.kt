package alodjinha.cfgdemelo.com.repository.category

import alodjinha.cfgdemelo.com.model.ProductsResponse
import alodjinha.cfgdemelo.com.repository.api.LodjinhaApi
import io.reactivex.Single

class CategoryRepository {
    private val lodjinhaApi by lazy { LodjinhaApi() }

    fun getProductsByCategoryId(offset: Int, limit: Int, id: Int): Single<ProductsResponse> =
        lodjinhaApi.getProductsByCategoryId(offset, limit, id)

}