package br.com.android.seiji.remote

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.remote.mapper.BannersResponseModelMapper
import br.com.android.seiji.remote.model.BannerModel
import br.com.android.seiji.remote.model.BannerResponseModel
import br.com.android.seiji.remote.service.LodjinhaService
import br.com.android.seiji.remote.test.factory.BannerFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BannersRemoteImplTest {

    private val mapper = mock<BannersResponseModelMapper>()
    private val service = mock<LodjinhaService>()
    private val remote = BannersRemoteImpl(service, mapper)

    @Test
    fun getBannersComplete() {
        stubBannerService(Flowable.just(BannerFactory.makeBannerResponse()))
        stubBannersResponseModelMapperMapFromModel(BannerFactory.makeBannerModel(), BannerFactory.makeBannerEntity())

        val testObserver = remote.getBanners().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBannersCallsServer() {
        stubBannerService(Flowable.just(BannerFactory.makeBannerResponse()))
        stubBannersResponseModelMapperMapFromModel(BannerFactory.makeBannerModel(), BannerFactory.makeBannerEntity())

        remote.getBanners().test()
        verify(service).getBanners()
    }

    @Test
    fun getBannersReturnsData() {
        val response = BannerFactory.makeBannerResponse()
        stubBannerService(Flowable.just(response))

        val entities = mutableListOf<BannerEntity>()
        response.data.forEach {
            val entity = BannerFactory.makeBannerEntity()
            entities.add(entity)
            stubBannersResponseModelMapperMapFromModel(it, entity)
        }

        val testObserver = remote.getBanners().test()
        testObserver.assertValue(entities)
    }

    private fun stubBannerService(observable: Flowable<BannerResponseModel>) {
        whenever(service.getBanners())
            .thenReturn(observable)
    }

    private fun stubBannersResponseModelMapperMapFromModel(
        model: BannerModel,
        entity: BannerEntity
    ) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }
}