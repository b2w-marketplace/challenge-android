package br.com.android.seiji.presentation.mapper

import br.com.android.seiji.presentation.test.factory.CategoryFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoryViewMapperTest {

    private val mapper = CategoryViewMapper()

    @Test
    fun mapToViewMapsData() {
        val category = CategoryFactory.makeCategory()
        val categoryView = mapper.mapToView(category)

        assertEquals(category.id, categoryView.id)
        assertEquals(category.descricao, categoryView.descricao)
        assertEquals(category.urlImagem, categoryView.urlImagem)
    }
}