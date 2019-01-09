package test

import br.com.android.seiji.domain.model.Category

object CategoryFactory {

    fun makeCategory(): Category {
        return Category(
            DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomString()
        )
    }

    fun makeCategoryList(count: Int): List<Category> {
        val categoriasList = mutableListOf<Category>()
        repeat(count) {
            categoriasList.add(makeCategory())
        }
        return categoriasList
    }
}