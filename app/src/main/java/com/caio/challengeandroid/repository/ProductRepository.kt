package com.caio.lodjinha.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.caio.challengeandroid.repository.datasource.ProductsDataFactory
import com.caio.lodjinha.repository.entity.Product
import com.caio.lodjinha.repository.remote.ProductREST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository (
    private val productREST: ProductREST,
    private val productsDataFactory: ProductsDataFactory) {

    private val LOAD_SIZE_HINT = 20
    private val PAGE_SIZE = 20

    suspend fun getProductsMoreSallers() = withContext(Dispatchers.IO){
        return@withContext productREST.getProductsMoreSallers().await().data
    }

    suspend fun getProductsByCategory(categoryId: Int): LiveData<PagedList<Product>> = withContext(Dispatchers.IO){
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        LivePagedListBuilder(
            productsDataFactory.apply { this.categoryId = categoryId },
            pagedListConfig).build()
    }

    suspend fun getProductDetail(produtoId: Int) = withContext(Dispatchers.IO){
        return@withContext productREST.getProductDetail(produtoId).await()
    }

    suspend fun productReservation(produtoId: Int) = withContext(Dispatchers.IO){
        return@withContext productREST.productReservation(produtoId).await()
    }


}