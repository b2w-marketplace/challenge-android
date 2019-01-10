package br.com.android.seiji.data.mapper

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.domain.model.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() : EntityMapper<CategoryEntity, Category> {

    override fun mapFromEntity(entity: CategoryEntity): Category {
        return Category(
            entity.id, entity.descricao, entity.urlImagem
        )
    }

    override fun mapToEntity(domain: Category): CategoryEntity {
        return CategoryEntity(
            domain.id, domain.descricao, domain.urlImagem
        )
    }
}