package br.com.android.seiji.cache.mapper

import br.com.android.seiji.cache.model.CachedCategory
import br.com.android.seiji.cache.model.CachedProduct
import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.model.ProductEntity
import javax.inject.Inject


class CachedProductMapper @Inject constructor() : CacheMapper<CachedProduct, ProductEntity> {

    override fun mapFromCached(type: CachedProduct): ProductEntity {
        return ProductEntity(
                type.id, type.nome, type.descricao, type.precoDe, type.precoPor, type.urlImagem,
                CategoryEntity(type.categoria.id, type.categoria.categoriaDescricao, type.categoria.categoriaUrlImagem)
        )
    }

    override fun mapToCached(type: ProductEntity): CachedProduct {
        return CachedProduct(
                type.id, type.nome, type.descricao, type.precoDe, type.precoPor, type.urlImagem,
                CachedCategory(type.category.id, type.category.descricao, type.category.urlImagem)
        )
    }
}
