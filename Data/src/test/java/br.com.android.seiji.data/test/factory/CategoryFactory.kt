package br.com.android.seiji.data.test.factory

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.domain.model.Category

object CategoryFactory {

    fun makeCategoryEntity(): CategoryEntity {
        return CategoryEntity(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeCategory(): Category {
        return Category(
            DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeCategoryList(count: Int): List<CategoryEntity> {
        val categoriasList = mutableListOf<CategoryEntity>()
        repeat(count) {
            categoriasList.add(makeCategoryEntity())
        }
        return categoriasList
    }
}