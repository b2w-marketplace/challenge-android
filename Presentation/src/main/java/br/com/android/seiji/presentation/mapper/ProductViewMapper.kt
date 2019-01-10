package br.com.android.seiji.presentation.mapper

import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.presentation.model.CategoryView
import br.com.android.seiji.presentation.model.ProductView
import javax.inject.Inject

class ProductViewMapper @Inject constructor() : Mapper<ProductView, Product> {

    override fun mapToView(type: Product): ProductView {
        return ProductView(
                type.id, type.nome, type.descricao, type.precoDe, type.precoPor, type.urlImagem,
                CategoryView(type.category.id, type.category.descricao, type.category.urlImagem))
    }
}