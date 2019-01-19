package br.com.cemobile.data.local.mapper

import br.com.cemobile.data.local.database.CategoryEntity
import br.com.cemobile.domain.model.Category

object ToCategoryEntity {

    operator fun invoke(category: Category) = CategoryEntity(
        id = category.id,
        description = category.description,
        imageUrl =  category.imageUrl
    )

}