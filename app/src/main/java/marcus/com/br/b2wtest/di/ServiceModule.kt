package marcus.com.br.b2wtest.di

import dagger.Module
import dagger.Provides
import marcus.com.br.b2wtest.model.api.Api
import marcus.com.br.b2wtest.model.service.BannerService
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideBannerService(api: Api) = BannerService(api)
}