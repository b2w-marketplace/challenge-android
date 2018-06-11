package com.example.lidjinha.lodjinha.data.usecase

import com.example.lidjinha.lodjinha.model.Categorie
import kotlin.reflect.KFunction1

interface CategoriesUseCaseContract {

    fun getCategories(onCategoriesRetrieved: KFunction1<@ParameterName(name = "categories") List<Categorie>, Unit>)

}