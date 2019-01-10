package br.com.android.seiji.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.android.seiji.domain.interactor.product.GetProductsByCategoryId
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.presentation.mapper.ProductViewMapper
import br.com.android.seiji.presentation.model.ProductView
import br.com.android.seiji.presentation.state.ResourceState
import br.com.android.seiji.presentation.test.factory.DataFactory
import br.com.android.seiji.presentation.test.factory.ProductFactory
import br.com.android.seiji.presentation.viewModel.GetProductsByCategoryIdViewModel
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class GetProductByCategoryIdViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var getProductsByCategoryId = mock<GetProductsByCategoryId>()
    var viewMapper = mock<ProductViewMapper>()
    var getproductByCategoryIdViewModel = GetProductsByCategoryIdViewModel(
            getProductsByCategoryId, viewMapper
    )

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Product>>>()

    @Test
    fun fetchProductsByCategoryIdExecutesUseCase() {
        val categoryId = DataFactory.randomInt()
        val offset = DataFactory.randomInt()
        val limit = DataFactory.randomInt()

        getproductByCategoryIdViewModel.fetchProductsByCategoryId(categoryId, offset, limit)
        verify(getProductsByCategoryId, times(1)).execute(any(), eq(
                GetProductsByCategoryId.Params.forProduct(categoryId, offset, limit)
        ))
    }

    @Test
    fun fetchProductsByCategoryIdReturnsSuccess() {
        val products = ProductFactory.makeProductList(3)
        val productsViews = ProductFactory.makeProductViewList(3)

        stubProductMapperMapToView(productsViews[0], products[0])
        stubProductMapperMapToView(productsViews[1], products[1])
        stubProductMapperMapToView(productsViews[2], products[2])

        val categoryId = DataFactory.randomInt()
        val offset = DataFactory.randomInt()
        val limit = DataFactory.randomInt()

        getproductByCategoryIdViewModel.fetchProductsByCategoryId(categoryId, offset, limit)

        verify(getProductsByCategoryId).execute(captor.capture(), eq(
                GetProductsByCategoryId.Params.forProduct(categoryId, offset, limit)
        ))
        captor.firstValue.onNext(products)

        assertEquals(
                ResourceState.SUCCESS,
                getproductByCategoryIdViewModel.getProductsByCategoryId().value?.status
        )
    }

    @Test
    fun fetchProductByCategoryIdReturnsData() {
        val products = ProductFactory.makeProductList(3)
        val productsViews = ProductFactory.makeProductViewList(3)

        stubProductMapperMapToView(productsViews[0], products[0])
        stubProductMapperMapToView(productsViews[1], products[1])
        stubProductMapperMapToView(productsViews[2], products[2])

        val categoryId = DataFactory.randomInt()
        val offset = DataFactory.randomInt()
        val limit = DataFactory.randomInt()

        getproductByCategoryIdViewModel.fetchProductsByCategoryId(categoryId, offset, limit)

        verify(getProductsByCategoryId).execute(captor.capture(), eq(
                GetProductsByCategoryId.Params.forProduct(categoryId, offset, limit)
        ))

        captor.firstValue.onNext(products)

        assertEquals(
                productsViews,
                getproductByCategoryIdViewModel.getProductsByCategoryId().value?.data
        )
    }

    @Test
    fun fetchProductsByCategoryIdReturnsError() {
        val categoryId = DataFactory.randomInt()
        val offset = DataFactory.randomInt()
        val limit = DataFactory.randomInt()

        getproductByCategoryIdViewModel.fetchProductsByCategoryId(categoryId, offset, limit)

        verify(getProductsByCategoryId).execute(captor.capture(), eq(
                GetProductsByCategoryId.Params.forProduct(categoryId, offset, limit)
        ))
        captor.firstValue.onError(RuntimeException())

        assertEquals(
                ResourceState.ERROR,
                getproductByCategoryIdViewModel.getProductsByCategoryId().value?.status
        )
    }

    @Test
    fun fetchProductsByCategoryIdReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        val categoryId = DataFactory.randomInt()
        val offset = DataFactory.randomInt()
        val limit = DataFactory.randomInt()

        getproductByCategoryIdViewModel.fetchProductsByCategoryId(categoryId, offset, limit)

        verify(getProductsByCategoryId).execute(captor.capture(),
                eq(GetProductsByCategoryId.Params.forProduct(categoryId, offset, limit
                )))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, getproductByCategoryIdViewModel.getProductsByCategoryId().value?.message)
    }

    private fun stubProductMapperMapToView(
            productView: ProductView,
            product: Product
    ) {
        whenever(viewMapper.mapToView(product))
                .thenReturn(productView)
    }
}