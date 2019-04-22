package com.desafioamedigital.service.api

import com.desafioamedigital.model.dto.Product
import com.desafioamedigital.model.dto.ProductList
import com.desafioamedigital.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET(Constants.PRODUCT_BEST_SELLERS_ENDPOINT)
    fun getBestSellers(): Observable<ProductList>

    @GET(Constants.PRODUCT_BY_ID_ENDPOINT)
    fun getProduct(@Path(Constants.PRODUCT_ID_PATH) productId: Int): Observable<Product>

    @GET(Constants.PRODUCTS_BY_CATEGORY)
    fun getProductsByCategory(
        @Query(Constants.CATEGORY_ID_QUERY) categoryId: Int,
        @Query(Constants.OFFSET_QUERY) offset: Int,
        @Query(Constants.LIMIT_QUERY) limit: Int
    ): Observable<ProductList>

}