package br.com.android.seiji.data.mapper

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.data.test.factory.ProductFactory
import br.com.android.seiji.domain.model.Product
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ProductMapperTest {

    private val mapper = ProductMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = ProductFactory.makeProductEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = ProductFactory.makeProduct()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: ProductEntity, model: Product) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.nome, model.nome)
        assertEquals(entity.descricao, model.descricao)
        assertEquals(entity.precoDe, model.precoDe)
        assertEquals(entity.precoPor, model.precoPor)
        assertEquals(entity.urlImagem, model.urlImagem)

        assertEquals(entity.category.id, model.category.id)
        assertEquals(entity.category.urlImagem, model.category.urlImagem)
        assertEquals(entity.category.descricao, model.category.descricao)
    }
}