package br.com.android.seiji.mobileui.mapper

import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.presentation.model.ProductView
import javax.inject.Inject

class ProductViewMapper @Inject constructor() : ViewMapper<ProductView, Product> {
    override fun mapToView(presentation: ProductView): Product {
        return Product(
            presentation.id, presentation.nome, presentation.descricao, presentation.precoDe,
            presentation.precoPor, presentation.urlImagem, Category(
                presentation.category.id,
                presentation.category.descricao, presentation.category.urlImagem
            )
        )
    }
}