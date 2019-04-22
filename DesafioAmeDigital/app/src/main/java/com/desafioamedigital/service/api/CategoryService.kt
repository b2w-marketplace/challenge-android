package com.desafioamedigital.service.api

import com.desafioamedigital.model.dto.CategoryList
import com.desafioamedigital.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET

interface CategoryService {

    @GET(Constants.CATEGORY_ENDPOINT)
    fun getCategories(): Observable<CategoryList>

}