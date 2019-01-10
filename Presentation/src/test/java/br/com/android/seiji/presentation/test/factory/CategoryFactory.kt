package br.com.android.seiji.presentation.test.factory

import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.presentation.model.CategoryView

object CategoryFactory {

    fun makeCategoryView(): CategoryView {
        return CategoryView(
                DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeCategory(): Category {
        return Category(
                DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString()
        )
    }

    fun makeCategoryViewList(count: Int): List<CategoryView> {
        val categories = mutableListOf<CategoryView>()
        repeat(count) {
            categories.add(makeCategoryView())
        }
        return categories
    }

    fun makeCategoryList(count: Int): List<Category> {
        val categories = mutableListOf<Category>()
        repeat(count) {
            categories.add(makeCategory())
        }
        return categories
    }
}