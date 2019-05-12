package br.com.douglas.fukuhara.lodjinha.presenter

import br.com.douglas.fukuhara.lodjinha.interfaces.ProductDetailContract
import br.com.douglas.fukuhara.lodjinha.network.RestClient
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo
import br.com.douglas.fukuhara.lodjinha.presenter.helper.ProductDetailPresenterHelper.getFailureResponseWithMessageWhenReserving
import br.com.douglas.fukuhara.lodjinha.presenter.helper.ProductDetailPresenterHelper.getFailureResponseWithoutMessageWhenReserving
import br.com.douglas.fukuhara.lodjinha.presenter.helper.ProductDetailPresenterHelper.getProductObj
import br.com.douglas.fukuhara.lodjinha.presenter.helper.ProductDetailPresenterHelper.getSuccessResponseWhenReserving
import br.com.douglas.fukuhara.lodjinha.utils.TestUtils
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
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*
import java.util.concurrent.Executor

class ProductDetailPresenterTest {

    private val mModel: ProductDataVo = mockk(relaxed = true)
    private val mView: ProductDetailContract.View = mockk(relaxed = true)
    private val mClient: RestClient = mockk(relaxed = true)
    private lateinit var mPresenter: ProductDetailContract.Presenter

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
        mPresenter = ProductDetailPresenter(mModel, mView, mClient)
    }

    @Test
    fun `Given A Null Model Object Then Display No Content Available Information`() {
        mPresenter = ProductDetailPresenter(null, mView, mClient)
        mPresenter.displayProductDetail()

        verify { mView.displayNoContentAvailable() }
    }

    @Test
    fun `Given A Valid Model Object Then Display It On Screen`() {
        val model = getProductObj()
        val numberFormatter = NumberFormat.getInstance(Locale("pt", "BR"))
        numberFormatter.roundingMode = RoundingMode.HALF_UP
        numberFormatter.maximumFractionDigits = 2
        numberFormatter.minimumFractionDigits = 2

        mPresenter = ProductDetailPresenter(model, mView, mClient)
        mPresenter.displayProductDetail()

        verify(exactly = 0) { mView.displayNoContentAvailable() }
        verify {
            mView.populateProductLayout(
                    model.urlImagem,
                    model.nome,
                    numberFormatter.format(model.precoDe),
                    numberFormatter.format(model.precoPor),
                    model.descricao
            )
        }
    }

    @Test
    fun `When Click On Reserve Button, Given A Server Success Response Then Show Success Notification`() {
        val successResponseWhenReserving = getSuccessResponseWhenReserving()
        every { mClient.api.reserveProductId(any()) } returns Observable.just(successResponseWhenReserving)

        mPresenter.onReserveButtonPressed()

        with (mView) {
            verifyOrder {
                setFabEnabled(false)
                showReservationSuccess()
            }
            verify(exactly = 0) {
                showGenericFailureOnReservation()
                showFailureOnReservation(any())
            }
        }
    }

    @Test
    fun `When Click On Reserve Button, Given A Server 200 OK But Fail Response With Message Then Show Server Message Error Notification`() {
        val failureResponseWithMessageWhenReserving = getFailureResponseWithMessageWhenReserving()
        every { mClient.api.reserveProductId(any()) } returns Observable.just(failureResponseWithMessageWhenReserving)

        mPresenter.onReserveButtonPressed()

        with (mView) {
            verifyOrder {
                setFabEnabled(false)
                showFailureOnReservation(failureResponseWithMessageWhenReserving.mensagem)
            }
            verify(exactly = 0) {
                showReservationSuccess()
                showGenericFailureOnReservation()
            }
        }
    }

    @Test
    fun `When Click On Reserve Button, Given A Server 200 OK But Fail Response Without Message Then Show Generic Message Error Notification`() {
        val failureResponseWithoutMessageWhenReserving = getFailureResponseWithoutMessageWhenReserving()
        every { mClient.api.reserveProductId(any()) } returns Observable.just(failureResponseWithoutMessageWhenReserving)

        mPresenter.onReserveButtonPressed()

        with (mView) {
            verifyOrder {
                setFabEnabled(false)
                showGenericFailureOnReservation()
            }
            verify(exactly = 0) {
                showReservationSuccess()
                showFailureOnReservation(any())
            }
        }
    }

    @Test
    fun `When Click On Reserve Button, Given A Server Error With Message Then Show Server Message Error Notification`() {
        val serverResponseMessageRef = "Esta é uma mensagem de erro que veio do servidor"
        every { mClient.api.reserveProductId(any()) } returns TestUtils.getServerErrorWithMessage(serverResponseMessageRef)

        mPresenter.onReserveButtonPressed()

        with (mView) {
            verifyOrder {
                setFabEnabled(false)
                setFabEnabled(true)
                showFailureOnReservation(serverResponseMessageRef)
            }
            verify(exactly = 0) {
                showReservationSuccess()
                showGenericFailureOnReservation()
            }
        }
    }

    @Test
    fun `When Click On Reserve Button, Given A Server Error Without Message Then Show Server Message Error Notification`() {
        every { mClient.api.reserveProductId(any()) } returns TestUtils.getGenericServer()

        mPresenter.onReserveButtonPressed()

        with (mView) {
            verifyOrder {
                setFabEnabled(false)
                setFabEnabled(true)
                showGenericFailureOnReservation()
            }
            verify(exactly = 0) {
                showReservationSuccess()
                showFailureOnReservation(any())
            }
        }
    }

    @Test
    fun `When Click On Reserve Product Confirmation Then Fab Must Be Re-enabled`() {
        mPresenter.onDialogConfirmationPressed()

        verify {
            mView.setFabEnabled(true)
        }
    }
}