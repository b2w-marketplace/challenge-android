package br.com.douglas.fukuhara.lodjinha.presenter

import br.com.douglas.fukuhara.lodjinha.interfaces.HomeContract
import br.com.douglas.fukuhara.lodjinha.network.RestClient
import br.com.douglas.fukuhara.lodjinha.presenter.helper.HomePresenterHelper.getBannerData
import br.com.douglas.fukuhara.lodjinha.presenter.helper.HomePresenterHelper.getBestSellingData
import br.com.douglas.fukuhara.lodjinha.presenter.helper.HomePresenterHelper.getCategoryData
import br.com.douglas.fukuhara.lodjinha.utils.TestUtils.getGenericServer
import br.com.douglas.fukuhara.lodjinha.utils.TestUtils.getServerErrorWithMessage
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executor

class HomePresenterTest {

    private val mView: HomeContract.View = mockk(relaxed = true)
    private val mClient: RestClient = mockk(relaxed = true)
    private lateinit var mPresenter: HomeContract.Presenter

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

        mPresenter = HomePresenter(mView, mClient)
    }

    @Test
    fun `Given A Generic Banner Fetch Failure Response Then Display A Generic Message Error`() {
        every { mClient.api.banner } returns getGenericServer()

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showBannerLoading()
                hideBannerLoading()
                onBannerContentFailedGenericError()
            }
            verify(exactly = 0) {
                onBannerContentFailed(any())
                onBannerContentLoaded(any())
            }
        }
    }

    @Test
    fun `Given A Banner Fetch Failure Response With Message Then Display The Error Message`() {
        val serverResponseMessageRef = "Esta é uma mensagem de erro que veio do servidor"
        every { mClient.api.banner } returns getServerErrorWithMessage(serverResponseMessageRef)

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showBannerLoading()
                hideBannerLoading()
                onBannerContentFailed(serverResponseMessageRef)
            }
            verify(exactly = 0) {
                onBannerContentFailedGenericError()
                onBannerContentLoaded(any())
            }
        }
    }

    @Test
    fun `Given A Banner Fetch Success Then Display The Banner List`() {
        val bannerVo = getBannerData()
        every { mClient.api.banner } returns Observable.just(bannerVo)

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showBannerLoading()
                hideBannerLoading()
                onBannerContentLoaded(bannerVo.data)
            }
            verify(exactly = 0) {
                onBannerContentFailedGenericError()
                onBannerContentFailed(any())
            }
        }
    }

    @Test
    fun `Given A Generic Category Fetch Failure Response Then Display A Generic Message Error`() {
        every { mClient.api.categoria } returns getGenericServer()

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showCategoryLoading()
                hideCategoryLoading()
                onCategoryContentFailedGenericError()
            }
            verify(exactly = 0) {
                onCategoryContentFailed(any())
                onCategoryContentLoaded(any())
            }
        }
    }

    @Test
    fun `Given A Category Fetch Failure Response With Message Then Display The Error Message`() {
        val serverResponseMessageRef = "Esta é uma mensagem de erro que veio do servidor"
        every { mClient.api.categoria } returns getServerErrorWithMessage(serverResponseMessageRef)

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showCategoryLoading()
                hideCategoryLoading()
                onCategoryContentFailed(serverResponseMessageRef)
            }
            verify(exactly = 0) {
                onCategoryContentFailedGenericError()
                onCategoryContentLoaded(any())
            }
        }
    }

    @Test
    fun `Given A Category Fetch Success Then Display The Banner List`() {
        val categoryData = getCategoryData()
        every { mClient.api.categoria } returns Observable.just(categoryData)

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showCategoryLoading()
                hideCategoryLoading()
                onCategoryContentLoaded(categoryData.data)
            }
            verify(exactly = 0) {
                onCategoryContentFailedGenericError()
                onCategoryContentFailed(any())
            }
        }
    }

    @Test
    fun `Given A Generic Best Selling Fetch Failure Response Then Display A Generic Message Error`() {
        every { mClient.api.produtoMaisVendido } returns getGenericServer()

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showBestSellerProductsLoading()
                hideBestSellerProductsLoading()
                onBestSellerContentFailedGenericError()
            }
            verify(exactly = 0) {
                onBestSellerContentFailed(any())
                onBestSellerContentLoaded(any())
            }
        }
    }

    @Test
    fun `Given A Best Selling Fetch Failure Response With Message Then Display The Error Message`() {
        val serverResponseMessageRef = "Esta é uma mensagem de erro que veio do servidor"
        every { mClient.api.produtoMaisVendido } returns getServerErrorWithMessage(serverResponseMessageRef)

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showBestSellerProductsLoading()
                hideBestSellerProductsLoading()
                onBestSellerContentFailed(serverResponseMessageRef)
            }
            verify(exactly = 0) {
                onBestSellerContentFailedGenericError()
                onBestSellerContentLoaded(any())
            }
        }
    }

    @Test
    fun `Given A Best Selling Fetch Success Then Display The Banner List`() {
        val bestSellingData = getBestSellingData()
        every { mClient.api.produtoMaisVendido } returns Observable.just(bestSellingData)

        mPresenter.fetchContent()

        with(mView) {
            verifyOrder {
                showBestSellerProductsLoading()
                hideBestSellerProductsLoading()
                onBestSellerContentLoaded(bestSellingData.data)
            }
            verify(exactly = 0) {
                onBestSellerContentFailedGenericError()
                onBestSellerContentFailed(any())
            }
        }
    }
}