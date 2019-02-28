package com.caio.lodjinha.repository

import com.caio.lodjinha.repository.remote.CategoryREST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CategoryRepository (private val categoryREST: CategoryREST) {

    suspend fun getCategory() = withContext(Dispatchers.IO){
        return@withContext categoryREST.getCategory().await().data
    }


}