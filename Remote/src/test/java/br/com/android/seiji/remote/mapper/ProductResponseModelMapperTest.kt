package br.com.android.seiji.remote.mapper

import br.com.android.seiji.remote.test.factory.ProductFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ProductResponseModelMapperTest {

    private val mapper = ProductsResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = ProductFactory.makeProductModel()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.nome, entity.nome)
        assertEquals(model.precoDe, entity.precoDe)
        assertEquals(model.precoPor, entity.precoPor)
        assertEquals(model.descricao, entity.descricao)
        assertEquals(model.urlImagem, entity.urlImagem)

        assertEquals(model.category.id, entity.category.id)
        assertEquals(model.category.descricao, entity.category.descricao)
        assertEquals(model.category.urlImagem, entity.category.urlImagem)
    }
}