package test

import br.com.android.seiji.domain.model.Categoria

object CategoriaFactory {

    fun makeCategoria(): Categoria {
        return Categoria(
            DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomString()
        )
    }

    fun makeCategoriaList(count: Int): List<Categoria> {
        val categoriasList = mutableListOf<Categoria>()
        repeat(count) {
            categoriasList.add(makeCategoria())
        }
        return categoriasList
    }
}