package br.com.android.seiji.remote

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.remote.mapper.CategoriesResponseModelMapper
import br.com.android.seiji.remote.model.CategoryModel
import br.com.android.seiji.remote.model.CategoryResponseModel
import br.com.android.seiji.remote.service.LodjinhaService
import br.com.android.seiji.remote.test.factory.CategoryFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoriesRemoteImplTest {

    private val mapper = mock<CategoriesResponseModelMapper>()
    private val service = mock<LodjinhaService>()
    private val remote = CategoriesRemoteImpl(service, mapper)

    @Test
    fun getCategoriesCompletes() {
        stubGetCategories(Flowable.just(CategoryFactory.makeCategoryResponse()))
        stubCategoriesResponseModelMapperMapFromModel(
            CategoryFactory.makeCategoryModel(),
            CategoryFactory.makeCategoryEntity()
        )

        val testObserver = remote.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesCallsServer() {
        stubGetCategories(Flowable.just(CategoryFactory.makeCategoryResponse()))
        stubCategoriesResponseModelMapperMapFromModel(
            CategoryFactory.makeCategoryModel(),
            CategoryFactory.makeCategoryEntity()
        )

        remote.getCategories().test()
        verify(service).getCategories()
    }

    @Test
    fun getCategoriesReturnsData() {
        val response = CategoryFactory.makeCategoryResponse()
        stubGetCategories(Flowable.just(response))

        val entities = mutableListOf<CategoryEntity>()
        response.data.forEach {
            val entity = CategoryFactory.makeCategoryEntity()
            entities.add(entity)
            stubCategoriesResponseModelMapperMapFromModel(it, entity)
        }

        val testObserver = remote.getCategories().test()
        testObserver.assertValue(entities)
    }

    private fun stubGetCategories(observable: Flowable<CategoryResponseModel>) {
        whenever(service.getCategories())
            .thenReturn(observable)
    }

    private fun stubCategoriesResponseModelMapperMapFromModel(
        model: CategoryModel,
        entity: CategoryEntity
    ) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }
}