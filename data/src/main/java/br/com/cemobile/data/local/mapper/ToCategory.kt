package br.com.cemobile.data.local.mapper

import br.com.cemobile.data.local.database.CategoryEntity
import br.com.cemobile.domain.model.Category

object ToCategory {

    operator fun invoke(entity: CategoryEntity) = Category(
        id = entity.id,
        description = entity.description,
        imageUrl =  entity.imageUrl
    )

}