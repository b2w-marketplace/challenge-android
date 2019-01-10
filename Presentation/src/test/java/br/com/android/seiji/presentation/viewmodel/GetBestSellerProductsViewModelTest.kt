package br.com.android.seiji.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.android.seiji.domain.interactor.product.GetBestSellerProducts
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.presentation.mapper.ProductViewMapper
import br.com.android.seiji.presentation.model.ProductView
import br.com.android.seiji.presentation.state.ResourceState
import br.com.android.seiji.presentation.test.factory.DataFactory
import br.com.android.seiji.presentation.test.factory.ProductFactory
import br.com.android.seiji.presentation.viewModel.GetBestSellerProductsViewModel
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class GetBestSellerProductsViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var getBestSellerProducts = mock<GetBestSellerProducts>()
    var viewMapper = mock<ProductViewMapper>()
    var getBestSellerProductsViewModel = GetBestSellerProductsViewModel(
            getBestSellerProducts, viewMapper
    )

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Product>>>()

    @Test
    fun fetchBestSellerProductsExecutesUseCase() {
        getBestSellerProductsViewModel.fetchBestSellerProducts()

        verify(getBestSellerProducts, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchBestSellerProductsReturnsSuccess() {
        val products = ProductFactory.makeProductList(3)
        val productsViews = ProductFactory.makeProductViewList(3)

        stubProductMapperMapToView(productsViews[0], products[0])
        stubProductMapperMapToView(productsViews[1], products[1])
        stubProductMapperMapToView(productsViews[2], products[2])

        getBestSellerProductsViewModel.fetchBestSellerProducts()

        verify(getBestSellerProducts).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(products)

        assertEquals(
                ResourceState.SUCCESS,
                getBestSellerProductsViewModel.getBestSellerProducts().value?.status
        )
    }

    @Test
    fun fetchBestSellerProductsReturnsData() {
        val products = ProductFactory.makeProductList(3)
        val productsViews = ProductFactory.makeProductViewList(3)

        stubProductMapperMapToView(productsViews[0], products[0])
        stubProductMapperMapToView(productsViews[1], products[1])
        stubProductMapperMapToView(productsViews[2], products[2])

        getBestSellerProductsViewModel.fetchBestSellerProducts()

        verify(getBestSellerProducts).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(products)

        assertEquals(
                productsViews,
                getBestSellerProductsViewModel.getBestSellerProducts().value?.data
        )
    }

    @Test
    fun fetchBestSellerProductsReturnsError() {
        getBestSellerProductsViewModel.fetchBestSellerProducts()

        verify(getBestSellerProducts).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(
                ResourceState.ERROR,
                getBestSellerProductsViewModel.getBestSellerProducts().value?.status
        )
    }

    @Test
    fun fetchBestSellerProductsReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        getBestSellerProductsViewModel.fetchBestSellerProducts()

        verify(getBestSellerProducts).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, getBestSellerProductsViewModel.getBestSellerProducts().value?.message)
    }

    private fun stubProductMapperMapToView(
            productView: ProductView,
            product: Product
    ) {
        whenever(viewMapper.mapToView(product))
                .thenReturn(productView)
    }
}