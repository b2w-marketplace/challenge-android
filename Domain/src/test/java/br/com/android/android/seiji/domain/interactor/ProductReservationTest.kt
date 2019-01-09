package br.com.android.android.seiji.domain.interactor

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.product.PostProductReservation
import br.com.android.seiji.domain.repository.ProductRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.DataFactory

class ProductReservationTest {

    private lateinit var postProductReservation: PostProductReservation

    @Mock
    lateinit var productsRepository: ProductRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        postProductReservation = PostProductReservation(productsRepository, postExecutionThread)
    }

    @Test
    fun productReservationCompletes() {
        stubProductReservation(Completable.complete())
        val testObserver = postProductReservation.buildUseCaseCompletable(
            PostProductReservation.Params.forProduct(DataFactory.randomInt())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun productReservationThrowsException() {
        postProductReservation.buildUseCaseCompletable().test()
    }

    private fun stubProductReservation(completable: Completable) {
        whenever(productsRepository.doProductReservation(any()))
            .thenReturn(completable)
    }
}