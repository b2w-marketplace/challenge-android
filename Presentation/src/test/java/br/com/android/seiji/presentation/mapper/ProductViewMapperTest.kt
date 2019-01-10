package br.com.android.seiji.presentation.mapper

import br.com.android.seiji.presentation.test.factory.ProductFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductViewMapperTest {

    private val mapper = ProductViewMapper()

    @Test
    fun mapToViewMapsData() {
        val product = ProductFactory.makeProduct()
        val productView = mapper.mapToView(product)

        assertEquals(product.id, productView.id)
        assertEquals(product.nome, productView.nome)
        assertEquals(product.descricao, productView.descricao)
        assertEquals(product.precoDe, productView.precoDe, 0.0001)
        assertEquals(product.precoPor, productView.precoPor, 0.0001)
        assertEquals(product.urlImagem, productView.urlImagem)

        assertEquals(product.category.id, productView.category.id)
        assertEquals(product.category.descricao, productView.category.descricao)
        assertEquals(product.category.urlImagem, productView.category.urlImagem)
    }
}