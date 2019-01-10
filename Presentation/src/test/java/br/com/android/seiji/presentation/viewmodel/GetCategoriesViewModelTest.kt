package br.com.android.seiji.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.android.seiji.domain.interactor.category.GetCategories
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.presentation.mapper.CategoryViewMapper
import br.com.android.seiji.presentation.model.CategoryView
import br.com.android.seiji.presentation.state.ResourceState
import br.com.android.seiji.presentation.test.factory.CategoryFactory
import br.com.android.seiji.presentation.test.factory.DataFactory
import br.com.android.seiji.presentation.viewModel.GetCategoriesViewModel
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class GetCategoriesViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var getCategories = mock<GetCategories>()
    var viewMapper = mock<CategoryViewMapper>()
    var getCategoriesViewModel = GetCategoriesViewModel(
            getCategories, viewMapper
    )

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Category>>>()

    @Test
    fun fetchCategoriesExecutesUseCase() {
        getCategoriesViewModel.fetchCategories()

        verify(getCategories, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchCategoriesReturnsSuccess() {
        val categories = CategoryFactory.makeCategoryList(3)
        val categoriesViews = CategoryFactory.makeCategoryViewList(3)

        stubCategoryMapperMapToView(categoriesViews[0], categories[0])
        stubCategoryMapperMapToView(categoriesViews[1], categories[1])
        stubCategoryMapperMapToView(categoriesViews[2], categories[2])

        getCategoriesViewModel.fetchCategories()

        verify(getCategories).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(categories)

        assertEquals(
                ResourceState.SUCCESS,
                getCategoriesViewModel.getCategories().value?.status
        )
    }

    @Test
    fun fetchCategoriesReturnsData() {
        val categories = CategoryFactory.makeCategoryList(3)
        val categoriesViews = CategoryFactory.makeCategoryViewList(3)

        stubCategoryMapperMapToView(categoriesViews[0], categories[0])
        stubCategoryMapperMapToView(categoriesViews[1], categories[1])
        stubCategoryMapperMapToView(categoriesViews[2], categories[2])

        getCategoriesViewModel.fetchCategories()

        verify(getCategories).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(categories)

        assertEquals(
                categoriesViews,
                getCategoriesViewModel.getCategories().value?.data
        )
    }

    @Test
    fun fetchCategoriesReturnsError() {
        getCategoriesViewModel.fetchCategories()

        verify(getCategories).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(
                ResourceState.ERROR,
                getCategoriesViewModel.getCategories().value?.status
        )
    }

    @Test
    fun fetchCategoriesReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        getCategoriesViewModel.fetchCategories()

        verify(getCategories).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, getCategoriesViewModel.getCategories().value?.message)
    }

    private fun stubCategoryMapperMapToView(
            categoryView: CategoryView,
            category: Category
    ) {
        whenever(viewMapper.mapToView(category))
                .thenReturn(categoryView)
    }
}