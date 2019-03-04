package br.com.andrecouto.alodjinha.domain.interactor.category

import br.com.andrecouto.alodjinha.domain.factory.category.CategoryFactory
import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import br.com.andrecouto.alodjinha.domain.repository.CategoryRepository
import br.com.andrecouto.alodjinha.domain.usecase.category.GetCategories
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCategoriesUseCaseTest {

    private lateinit var getCategoriesUseCase: GetCategories
    @Mock
    lateinit var categoryRepository : CategoryRepository
    @Mock
    lateinit var domainErrorUtil: DomainErrorUtil

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategoriesUseCase = GetCategories(domainErrorUtil, categoryRepository)
    }

    @Test
    fun getCategoriesCompletes() {
        stubCategories(Single.just(CategoryFactory.makeCategoryList(3)))
        val testSingle = getCategoriesUseCase.buildUseCaseObservable().test()
        testSingle.assertComplete()
    }

    @Test
    fun getCategoriesReturnData() {
        val categories = CategoryFactory.makeCategoryList(3)
        stubCategories(Single.just(categories))
        val testSingle = getCategoriesUseCase.buildUseCaseObservable().test()
        testSingle.assertValue(categories)
    }

    private fun stubCategories(observable: Single<List<Category>>) {
        whenever(categoryRepository.getCategories())
                .thenReturn(observable)
    }
}