package com.example.lidjinha.lodjinha.data.usecase

import com.example.lidjinha.lodjinha.data.remote.repository.CategoriesRepository
import com.example.lidjinha.lodjinha.model.Categorie
import kotlin.reflect.KFunction1

class CategoriesUseCase : CategoriesUseCaseContract {

    override fun getCategories(onBannersRetrieved: KFunction1<List<Categorie>, Unit>) {
        CategoriesRepository.instance.getCategories(onBannersRetrieved)
    }
}