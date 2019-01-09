package test

import br.com.android.seiji.domain.model.Category

object CategoriaFactory {

    fun makeCategoria(): Category {
        return Category(
            DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomString()
        )
    }

    fun makeCategoriaList(count: Int): List<Category> {
        val categoriasList = mutableListOf<Category>()
        repeat(count) {
            categoriasList.add(makeCategoria())
        }
        return categoriasList
    }
}