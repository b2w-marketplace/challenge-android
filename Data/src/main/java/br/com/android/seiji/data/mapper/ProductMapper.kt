package br.com.android.seiji.data.mapper

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.domain.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() : EntityMapper<ProductEntity, Product> {

    override fun mapFromEntity(entity: ProductEntity): Product {
        return Product(
            entity.id, entity.nome, entity.descricao,
            entity.precoDe, entity.precoPor, entity.urlImagem,
            Category(entity.category.id, entity.category.descricao, entity.category.urlImagem)
        )
    }

    override fun mapToEntity(domain: Product): ProductEntity {
        return ProductEntity(
            domain.id, domain.nome, domain.descricao,
            domain.precoDe, domain.precoPor, domain.urlImagem,
            CategoryEntity(domain.category.id, domain.category.descricao, domain.category.urlImagem)
        )
    }
}