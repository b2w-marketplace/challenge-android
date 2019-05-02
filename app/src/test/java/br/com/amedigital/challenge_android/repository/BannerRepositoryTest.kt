package br.com.amedigital.challenge_android.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.com.amedigital.challenge_android.api.BannerService
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.entity.Banner
import br.com.amedigital.challenge_android.models.network.GetBannersResponse
import br.com.amedigital.challenge_android.room.BannerDao
import br.com.amedigital.challenge_android.utils.ApiUtil.successCall
import br.com.amedigital.challenge_android.utils.MockTestUtil.Companion.mockBannerList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BannerRepositoryTest {

    private lateinit var bannerRepository: BannerRepository
    private val bannerDao = mock<BannerDao>()
    private val bannerService = mock<BannerService>()

    @Rule
    @JvmField val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        bannerRepository = BannerRepository(bannerService, bannerDao)
    }

    @Test
    fun loadBannerListFromNetwork() {

        val loadFromDB = MutableLiveData<List<Banner>>()

        whenever(bannerDao.getBannerList()).thenReturn(loadFromDB)

        val mockResponse = GetBannersResponse(emptyList())
        val call = successCall(mockResponse)
        whenever(bannerService.getBannerList()).thenReturn(call)

        bannerRepository.getBanners()
        verify(bannerDao).getBannerList()
        verifyNoMoreInteractions(bannerService)

    }

}