package br.com.android.seiji.mobileui.di.module

import br.com.android.seiji.data.repository.banners.BannersRemote
import br.com.android.seiji.data.repository.category.CategoriesRemote
import br.com.android.seiji.data.repository.product.ProductsRemote
import br.com.android.seiji.mobileui.BuildConfig
import br.com.android.seiji.remote.BannersRemoteImpl
import br.com.android.seiji.remote.CategoriesRemoteImpl
import br.com.android.seiji.remote.ProductsRemoteImpl
import br.com.android.seiji.remote.service.LodjinhaService
import br.com.android.seiji.remote.service.LodjinhaServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideBannerService(): LodjinhaService {
            return LodjinhaServiceFactory.makeLodjinhaService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindBannersRemote(bannersRemoteImpl: BannersRemoteImpl): BannersRemote

    @Binds
    abstract fun bindCategoriesRemote(categoriesRemoteImpl: CategoriesRemoteImpl): CategoriesRemote

    @Binds
    abstract fun bindProductsRemote(productsRemoteImpl: ProductsRemoteImpl): ProductsRemote
}