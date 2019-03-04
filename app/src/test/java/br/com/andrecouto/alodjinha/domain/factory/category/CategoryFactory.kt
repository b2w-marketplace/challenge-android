package br.com.andrecouto.alodjinha.domain.factory.category

import br.com.andrecouto.alodjinha.DataFactory
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category

object CategoryFactory {

    fun makeCategory() : Category {
        return Category(DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString())
    }

    fun makeCategoryList(count: Int) : List<Category>{
        val categories = mutableListOf<Category>()
        repeat(count) {
            categories.add(makeCategory())
        }
        return categories
    }
}