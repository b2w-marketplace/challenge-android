package br.com.android.seiji.remote.test.factory

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.remote.model.CategoryModel
import br.com.android.seiji.remote.model.CategoryResponseModel

object CategoryFactory {

    fun makeCategoryModel(): CategoryModel {
        return CategoryModel(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeCategoryResponse(): CategoryResponseModel {
        return CategoryResponseModel(listOf(makeCategoryModel(), makeCategoryModel(), makeCategoryModel()))
    }
}