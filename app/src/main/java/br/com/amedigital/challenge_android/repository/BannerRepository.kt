package br.com.amedigital.challenge_android.repository

import androidx.lifecycle.LiveData
import br.com.amedigital.challenge_android.api.ApiResponse
import br.com.amedigital.challenge_android.api.BannerService
import br.com.amedigital.challenge_android.mappers.GetBannersResponseMapper
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.entity.Banner
import br.com.amedigital.challenge_android.models.network.GetBannersResponse
import br.com.amedigital.challenge_android.room.BannerDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BannerRepository  @Inject
constructor(val bannerService: BannerService, val bannerDao: BannerDao)
    : Repository {

    init {
        Timber.d("Injection BannerRepository")
    }

    fun getBanners(): LiveData<Resource<List<Banner>>> {
        return object : NetworkBoundRepository<List<Banner>, GetBannersResponse, GetBannersResponseMapper>() {
            override fun saveFetchData(items: GetBannersResponse) {
                bannerDao.insertBannerList(items.data)
            }

            override fun shouldFetch(data: List<Banner>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Banner>> {
                return bannerDao.getBannerList()
            }

            override fun fetchService(): LiveData<ApiResponse<GetBannersResponse>> {
                return bannerService.getBannerList()
            }

            override fun mapper(): GetBannersResponseMapper {
                return GetBannersResponseMapper()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("onFetchFailed : $message")
            }
        }.asLiveData()
    }
}