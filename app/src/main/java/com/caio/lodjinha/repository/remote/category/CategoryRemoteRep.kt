package com.caio.lodjinha.repository.remote.category

import javax.inject.Inject

class CategoryRemoteRep @Inject constructor(private val categoryREST: CategoryREST) {

    fun getCategory() = categoryREST.getCategory()
}