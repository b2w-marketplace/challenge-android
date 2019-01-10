package br.com.android.seiji.remote.mapper

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.remote.model.ProductModel
import javax.inject.Inject

class ProductsResponseModelMapper @Inject constructor() : ModelMapper<ProductModel, ProductEntity> {

    override fun mapFromModel(model: ProductModel): ProductEntity {
        return ProductEntity(
            model.id, model.nome, model.descricao, model.precoDe, model.precoPor, model.urlImagem,
            CategoryEntity(model.category.id, model.category.descricao, model.category.urlImagem)
        )
    }
}