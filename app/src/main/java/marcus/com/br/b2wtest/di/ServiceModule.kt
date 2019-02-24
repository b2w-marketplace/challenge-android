package marcus.com.br.b2wtest.di

import dagger.Module
import dagger.Provides
import marcus.com.br.b2wtest.model.api.Api
import marcus.com.br.b2wtest.model.service.BannerService
import marcus.com.br.b2wtest.model.service.CategoryService
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideBannerService(api: Api) = BannerService(api)

    @Provides
    @Singleton
    fun provideCategorieService(api: Api) = CategoryService(api)
}