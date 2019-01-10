package br.com.android.seiji.cache.mapper

import br.com.android.seiji.cache.model.CachedCategory
import br.com.android.seiji.data.model.CategoryEntity
import javax.inject.Inject

class CachedCategoryMapper @Inject constructor() : CacheMapper<CachedCategory, CategoryEntity> {

    override fun mapFromCached(type: CachedCategory): CategoryEntity {
        return CategoryEntity(
                type.id, type.categoriaDescricao, type.categoriaUrlImagem
        )
    }

    override fun mapToCached(type: CategoryEntity): CachedCategory {
        return CachedCategory(
                type.id, type.descricao, type.urlImagem
        )
    }
}