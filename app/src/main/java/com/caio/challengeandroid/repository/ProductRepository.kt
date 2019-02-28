package com.caio.lodjinha.repository

import com.caio.lodjinha.repository.remote.ProductREST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository (private val productREST: ProductREST) {

    suspend fun getProductsMoreSallers() = withContext(Dispatchers.IO){
        return@withContext productREST.getProductsMoreSallers().await().data
    }

//    suspend fun getProductsByCategory(offset: Int, limite: Int,categoriaId: Int) = withContext(Dispatchers.IO){
//        return@withContext productREST.getProductsByCategory(offset,limite,categoriaId)
//    }

    suspend fun getProductDetail(produtoId: Int) = withContext(Dispatchers.IO){
        return@withContext productREST.getProductDetail(produtoId).await()
    }

    suspend fun productReservation(produtoId: Int) = withContext(Dispatchers.IO){
        return@withContext productREST.productReservation(produtoId).await()
    }


}