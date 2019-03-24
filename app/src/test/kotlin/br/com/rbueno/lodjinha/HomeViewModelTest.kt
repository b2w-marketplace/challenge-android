package br.com.rbueno.lodjinha

import androidx.lifecycle.Observer
import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.model.Category
import br.com.rbueno.lodjinha.model.Home
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.repository.HomeRepository
import br.com.rbueno.lodjinha.viewmodel.HomeViewModel
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : BaseViewModelTest<HomeViewModel>() {

    @Mock
    private lateinit var repository: HomeRepository
    @Mock
    private lateinit var homeObserver: Observer<Home>

    @Before
    override fun setup() {
        viewModel = HomeViewModel(repository)
        viewModel.homeLiveData.observeForever(homeObserver)
        super.setup()
    }

    @After
    override fun teardown() {
        viewModel.homeLiveData.removeObserver(homeObserver)
        super.teardown()
    }

    @Test
    fun shouldPostHomeValue_WhenApiReturns() {
        //given
        `when`(repository.getBanner()).thenReturn(getMockedBannerList())
        `when`(repository.getCategory()).thenReturn(getMockedCategoryList())
        `when`(repository.getProductsMostSold()).thenReturn(getMockedProductList())
        //when
        viewModel.loadHome()
        //then
        verify(homeObserver).onChanged(viewModel.homeLiveData.value)
    }

    private fun getMockedProductList() = Single.just(ProductList(listOf()))

    private fun getMockedCategoryList() = Single.just(Category(listOf()))

    private fun getMockedBannerList() = Single.just(Banner(listOf()))
}