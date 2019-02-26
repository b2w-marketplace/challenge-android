package br.com.andrecouto.alodjinha.di.module

import br.com.andrecouto.alodjinha.data.source.remote.banner.BannerRemoteDataSource
import br.com.andrecouto.alodjinha.data.source.remote.banner.BannerRemoteDataSourceImpl
import br.com.andrecouto.alodjinha.data.source.remote.category.CategoryRemoteDataSource
import br.com.andrecouto.alodjinha.data.source.remote.category.CategoryRemoteDataSourceImpl
import br.com.andrecouto.alodjinha.data.source.remote.product.ProductRemoteDataSource
import br.com.andrecouto.alodjinha.data.source.remote.product.ProductRemoteDataSourceImpl
import br.com.andrecouto.alodjinha.data.source.remote.repository.BannerRepositoryImpl
import br.com.andrecouto.alodjinha.data.source.remote.repository.CategoryRepositoryImpl
import br.com.andrecouto.alodjinha.data.source.remote.repository.ProductRepositoryImpl
import br.com.andrecouto.alodjinha.data.source.remote.service.Service
import br.com.andrecouto.alodjinha.data.source.remote.service.ServiceFactory
import br.com.andrecouto.alodjinha.domain.repository.BannerRepository
import br.com.andrecouto.alodjinha.domain.repository.CategoryRepository
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideLodjinhaService() : Service {
            return ServiceFactory.makeLodjinhaService()
        }
    }

    @Binds
    abstract fun bindBannerRepository(bannerRepository: BannerRepositoryImpl) : BannerRepository

    @Binds
    abstract fun bindBannerRemoteDataSource(bannerRemoteDataSourceImpl: BannerRemoteDataSourceImpl) : BannerRemoteDataSource

    @Binds
    abstract fun bindCategoryRepository(categoriaRepositoryImpl: CategoryRepositoryImpl) : CategoryRepository

    @Binds
    abstract fun bindCategoryRemoteDataSource(categoriaRemoteDataSourceImpl: CategoryRemoteDataSourceImpl) : CategoryRemoteDataSource

    @Binds
    abstract fun bindProductRepository(produtoRepositoryImpl: ProductRepositoryImpl) : ProductRepository

    @Binds
    abstract fun bindproductRemoteDataSource(produtoRemoteDataSourceImpl: ProductRemoteDataSourceImpl) : ProductRemoteDataSource

}