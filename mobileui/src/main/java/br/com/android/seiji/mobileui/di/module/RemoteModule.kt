package br.com.android.seiji.mobileui.di.module

import br.com.android.seiji.data.repository.banners.BannersRemote
import br.com.android.seiji.mobileui.BuildConfig
import br.com.android.seiji.remote.BannersRemoteImpl
import br.com.android.seiji.remote.service.BannerService
import br.com.android.seiji.remote.service.BannerServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideBannerService(): BannerService {
            return BannerServiceFactory.makeBannerService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindBannersRemote(bannersRemoteImpl: BannersRemoteImpl): BannersRemote
}