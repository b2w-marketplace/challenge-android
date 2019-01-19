package br.com.cemobile.lodjinha.di

import br.com.cemobile.data.local.features.banner.BannerLocalDataSource
import br.com.cemobile.data.local.features.banner.BannerLocalDataSourceImpl
import br.com.cemobile.data.local.features.category.CategoryLocalDataSource
import br.com.cemobile.data.local.features.category.CategoryLocalDataSourceImpl
import br.com.cemobile.data.local.features.product.ProductLocalDataSource
import br.com.cemobile.data.local.features.product.ProductLocalDataSourceImpl
import br.com.cemobile.data.remote.features.banner.BannerRemoteDataSource
import br.com.cemobile.data.remote.features.banner.BannerRemoteDataSourceImpl
import br.com.cemobile.data.remote.features.category.CategoryRemoteDataSource
import br.com.cemobile.data.remote.features.category.CategoryRemoteDataSourceImpl
import br.com.cemobile.data.remote.features.product.ProductRemoteDataSource
import br.com.cemobile.data.remote.features.product.ProductRemoteDataSourceImpl
import br.com.cemobile.data.remote.webservices.WebServiceFactory
import br.com.cemobile.data.repository.BannerRepositoryImpl
import br.com.cemobile.data.repository.CategoryRepositoryImpl
import br.com.cemobile.data.repository.HomeDataRepositoryImpl
import br.com.cemobile.data.repository.ProductRepositoryImpl
import br.com.cemobile.domain.repository.BannerRepository
import br.com.cemobile.domain.repository.CategoryRepository
import br.com.cemobile.domain.repository.HomeDataRepository
import br.com.cemobile.domain.repository.ProductRepository
import org.koin.dsl.module.module

val dataModule = module {

    single { WebServiceFactory.create(debuggable = true) }

    single<BannerLocalDataSource> { BannerLocalDataSourceImpl(get()) }

    single<BannerRemoteDataSource> { BannerRemoteDataSourceImpl(get()) }

    single<BannerRepository> { BannerRepositoryImpl(get(), get()) }

    single<CategoryLocalDataSource> { CategoryLocalDataSourceImpl(get()) }

    single<CategoryRemoteDataSource> { CategoryRemoteDataSourceImpl(get()) }

    single<CategoryRepository> { CategoryRepositoryImpl(get(), get()) }

    single<ProductLocalDataSource> { ProductLocalDataSourceImpl(get()) }

    single<ProductRemoteDataSource> { ProductRemoteDataSourceImpl(get()) }

    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }

    single<HomeDataRepository> { HomeDataRepositoryImpl(get(), get(), get()) }

}