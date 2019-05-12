package br.com.douglas.fukuhara.lodjinha.presenter

import br.com.douglas.fukuhara.lodjinha.interfaces.ProductsByListCategoryContract
import br.com.douglas.fukuhara.lodjinha.network.RestClient
import br.com.douglas.fukuhara.lodjinha.presenter.helper.ProductsListByCategoryPresenterHelper.getOnlyOneProductWhenQueryWithOffsetZeroLimitTwelve
import br.com.douglas.fukuhara.lodjinha.presenter.helper.ProductsListByCategoryPresenterHelper.getTwelveProductsWhenQueryWithOffsetZeroLimitTwelve
import br.com.douglas.fukuhara.lodjinha.presenter.helper.ProductsListByCategoryPresenterHelper.getZeroProductWhenQueryWithOffsetTwelveLimitTwelve
import br.com.douglas.fukuhara.lodjinha.utils.TestUtils
import io.mockk.*
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executor

class ProductsListByCategoryPresenterTest {

    private val mView: ProductsByListCategoryContract.View = mockk(relaxed = true)
    private val mClient: RestClient = mockk(relaxed = true)
    private lateinit var mPresenter: ProductsByListCategoryContract.Presenter
    private val CATEGORY_ID = 123
    private val RETRIEVE_DATA_LIMIT = 20

    @Before
    fun setUp() {
        object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
            }
        }.run {
            RxJavaPlugins.setInitIoSchedulerHandler { this }
            RxJavaPlugins.setInitComputationSchedulerHandler { this }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { this }
            RxJavaPlugins.setInitSingleSchedulerHandler { this }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { this }
        }

        mPresenter = ProductsListByCategoryPresenter(mView, mClient, CATEGORY_ID)
    }

    @Test
    fun `Given A Server Failure Response With Message Then Show Server Message Error`() {
        val serverResponseMessageRef = "Esta é uma mensagem de erro que veio do servidor"
        every { mClient.api.getProduto(0,RETRIEVE_DATA_LIMIT, CATEGORY_ID) } returns TestUtils.getServerErrorWithMessage(serverResponseMessageRef)

        mPresenter.fetchData()

        with(mView) {
            verifyOrder {
                showLoader()
                dismissLoader()
                onProductFetchFailed(serverResponseMessageRef)
            }
            verify(exactly = 0) {
                showRecyclerViewContainer()
                displayListOfProducts(any())
                onProductListFailedGenericError()
            }
        }
    }

    @Test
    fun `Given A Server Failure Response Without Message Then Show A Generic Message Error`() {
        every { mClient.api.getProduto(0,RETRIEVE_DATA_LIMIT, CATEGORY_ID) } returns TestUtils.getGenericServer()

        mPresenter.fetchData()

        with(mView) {
            verifyOrder {
                showLoader()
                dismissLoader()
                onProductListFailedGenericError()
            }
            verify(exactly = 0) {
                showRecyclerViewContainer()
                displayListOfProducts(any())
                onProductFetchFailed(any())
            }
        }
    }

    @Test
    fun `Given A Category With Size As Same As Limit, Then Only One Fetch Must Be Performed`() {
        val listOfProducts = getTwelveProductsWhenQueryWithOffsetZeroLimitTwelve()
        every { mClient.api.getProduto(0, RETRIEVE_DATA_LIMIT, CATEGORY_ID) } returns Observable.just(listOfProducts)

        mPresenter.fetchData()

        with(mView) {
            verifyOrder {
                showLoader()
                dismissLoader()
                showRecyclerViewContainer()
                displayListOfProducts(listOfProducts.data)
            }
        }
    }

    @Test
    fun `Given A Category With Size Zero, Then Nothing To Display`() {
        val listOfProductsFetch2 = getZeroProductWhenQueryWithOffsetTwelveLimitTwelve()
        every { mClient.api.getProduto(0, RETRIEVE_DATA_LIMIT, CATEGORY_ID) } returns Observable.just(listOfProductsFetch2)

        mPresenter.fetchData()

        with(mView) {
            verifyOrder {
                showLoader()
                dismissLoader()
            }
            verify(exactly = 0) {
                showRecyclerViewContainer()
                displayListOfProducts(any())
            }
        }
    }

    @Test
    fun `When The First Fetch Returns A List Smaller Than Limit, Then No Further Fetches Will Be Done`() {
        val listWithASingleProduct = getOnlyOneProductWhenQueryWithOffsetZeroLimitTwelve()
        every { mClient.api.getProduto(0, RETRIEVE_DATA_LIMIT, CATEGORY_ID) } returns Observable.just(listWithASingleProduct)

        mPresenter.fetchData()
        mPresenter.fetchData()

        verify(exactly = 1) {
            mClient.api.getProduto(0, RETRIEVE_DATA_LIMIT, CATEGORY_ID)
        }
    }

    @Test
    fun `When A Product List Returned Is Empty, Then GetProduto Api Should Not Be Invoked Anymore`() {
        val listOfProducts = getTwelveProductsWhenQueryWithOffsetZeroLimitTwelve()
        every { mClient.api.getProduto(0, RETRIEVE_DATA_LIMIT, CATEGORY_ID) } returns Observable.just(listOfProducts)
        val listOfProductsFetch2 = getZeroProductWhenQueryWithOffsetTwelveLimitTwelve()
        every { mClient.api.getProduto(20, RETRIEVE_DATA_LIMIT, CATEGORY_ID) } returns Observable.just(listOfProductsFetch2)

        mPresenter.fetchData()
        mPresenter.fetchData()
        mPresenter.fetchData()

        verify(exactly = 2) {
            mClient.api.getProduto(or(0,20), RETRIEVE_DATA_LIMIT, CATEGORY_ID)
        }
    }
}