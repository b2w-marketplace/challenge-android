package br.com.rbueno.lodjinha

import androidx.lifecycle.Observer
import br.com.rbueno.lodjinha.model.CategoryItem
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.repository.ProductRepository
import br.com.rbueno.lodjinha.viewmodel.ProductViewModel
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductViewModelTest : BaseViewModelTest<ProductViewModel>() {

    @Mock
    private lateinit var repository: ProductRepository
    @Mock
    private lateinit var productListObserver: Observer<ProductList>
    @Mock
    private lateinit var productObserver: Observer<Product>
    @Mock
    private lateinit var reserveProductObserver: Observer<Unit>
    @Mock
    private lateinit var reserveErrorObserver: Observer<Unit>

    @Before
    override fun setup() {
        viewModel = ProductViewModel(repository)
        viewModel.productListLiveData.observeForever(productListObserver)
        viewModel.productLiveData.observeForever(productObserver)
        viewModel.reserveProductLiveData.observeForever(reserveProductObserver)
        viewModel.reservationErrorLiveData.observeForever(reserveErrorObserver)
        super.setup()
    }

    @After
    override fun teardown() {
        viewModel.productListLiveData.removeObserver(productListObserver)
        viewModel.productLiveData.removeObserver(productObserver)
        viewModel.reserveProductLiveData.removeObserver(reserveProductObserver)
        viewModel.reservationErrorLiveData.removeObserver(reserveErrorObserver)
        super.teardown()
    }

    @Test
    fun shouldPostProductValue_WhenApiReturns() {
        //given
        `when`(repository.getProduct(anyInt())).thenReturn(getMockedProduct())
        //when
        viewModel.loadProduct(anyInt())
        //then
        verify(productObserver).onChanged(viewModel.productLiveData.value)
    }

    @Test
    fun shouldPostReserveValue_WhenApiReturns() {
        //given
        `when`(repository.reserveProduct(anyInt())).thenReturn(Completable.complete())
        //when
        viewModel.reserveProduct(anyInt())
        //then
        verify(reserveProductObserver).onChanged(viewModel.reserveProductLiveData.value)
    }

    @Test
    fun shouldPostReserveErrorValue_WhenApiReturnsError() {
        //given
        `when`(repository.reserveProduct(anyInt())).thenReturn(getMockedError())
        //when
        viewModel.reserveProduct(anyInt())
        //then
        verify(reserveErrorObserver).onChanged(viewModel.reservationErrorLiveData.value)
    }

    private fun getMockedProduct() = Single.just(
        Product(
            0.0, CategoryItem(
                "", 0, ""
            ), "", 1, "", 0.0, ""
        )
    )


    private fun getMockedProductList() = Single.just(ProductList(listOf()))

    private fun getMockedError() = Completable.error(Throwable())

}