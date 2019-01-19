package br.com.cemobile.data.local.mapper

import br.com.cemobile.data.local.database.ProductWithCategoryEntity
import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Product

object ToProduct {

    operator fun invoke(entity: ProductWithCategoryEntity): Product {
        val category = buildCategory(entity)
        return Product(
            id = entity.product.id,
            name = entity.product.name,
            imageUrl = entity.product.imageUrl,
            description = entity.product.description,
            fromPrice = entity.product.fromPrice,
            byPrice = entity.product.byPrice,
            category = category,
            bestSelling = entity.product.bestSelling
        )
    }

    private fun buildCategory(entity: ProductWithCategoryEntity): Category = with(entity) {
        Category(
            id = product.categoryId,
            description = categoryDescription,
            imageUrl = categoryImageUrl
        )
    }

}