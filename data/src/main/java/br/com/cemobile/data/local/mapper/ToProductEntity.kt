package br.com.cemobile.data.local.mapper

import br.com.cemobile.data.local.database.ProductEntity
import br.com.cemobile.domain.model.Product

object ToProductEntity {

    operator fun invoke(product: Product) = ProductEntity(
        id = product.id,
        name = product.name,
        imageUrl =  product.imageUrl,
        description = product.description,
        fromPrice = product.fromPrice,
        byPrice = product.byPrice,
        bestSelling = product.bestSelling,
        categoryId = product.category.id
    )

}