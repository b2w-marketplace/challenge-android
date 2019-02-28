package com.caio.challengeandroid.repository.datasource

import androidx.paging.DataSource
import com.caio.lodjinha.repository.entity.Product
import com.caio.lodjinha.repository.remote.ProductREST

class ProductsDataFactory(
    private val productREST: ProductREST): DataSource.Factory<Int, Product>() {

    var categoryId: Int = -1

    override fun create(): DataSource<Int, Product>  = ProductsDataSource(productREST, categoryId)

}