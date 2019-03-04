package br.com.andrecouto.alodjinha.data.repository

import br.com.andrecouto.alodjinha.data.source.remote.category.CategoryRemoteDataSource
import br.com.andrecouto.alodjinha.data.source.remote.repository.CategoryRepositoryImpl
import br.com.andrecouto.alodjinha.domain.factory.category.CategoryFactory
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test

class CategoryRemoteDataSourceImplTest {

    private val categoriaRemoteDataSource = mock<CategoryRemoteDataSource>()
    private val repository = CategoryRepositoryImpl(categoriaRemoteDataSource)

    @Test
    fun getCategoriesCompletes() {
        stubGetCategories(Single.just(CategoryFactory.makeCategoryList(3)))
        val testObserver = repository.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesReturnData() {
        val categories = CategoryFactory.makeCategoryList(3)
        stubGetCategories(Single.just(categories))
        val testObserver = repository.getCategories().test()
        testObserver.assertValue(categories)
    }

    private fun stubGetCategories(single: Single<List<Category>>) {
        whenever(categoriaRemoteDataSource.getCategories())
                .thenReturn(single)
    }
}