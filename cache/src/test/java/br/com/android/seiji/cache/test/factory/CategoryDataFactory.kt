package br.com.android.seiji.cache.test.factory

import br.com.android.seiji.cache.model.CachedCategory
import br.com.android.seiji.data.model.CategoryEntity

object CategoryDataFactory {

    fun makeCachedCategory(): CachedCategory {
        return CachedCategory(
                DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeCategoryEntity(): CategoryEntity {
        return CategoryEntity(
                DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }
}