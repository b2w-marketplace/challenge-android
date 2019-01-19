package br.com.cemobile.lodjinha.di

import br.com.cemobile.domain.features.banner.GetBanners
import br.com.cemobile.domain.features.banner.GetBannersImpl
import br.com.cemobile.domain.features.category.GetCategories
import br.com.cemobile.domain.features.category.GetCategoriesImpl
import br.com.cemobile.domain.features.homedata.GetHomeData
import br.com.cemobile.domain.features.homedata.GetHomeDataImpl
import br.com.cemobile.domain.features.product.*
import org.koin.dsl.module.module

val domainModule = module {

    single<GetBanners> { GetBannersImpl(get()) }

    single<GetCategories> { GetCategoriesImpl(get()) }

    single<GetBestSellingProducts> { GetBestSellingProductsImpl(get()) }

    single<GetHomeData> { GetHomeDataImpl(get()) }

    single<ReserveProduct> { ReserveProductImpl(get()) }

    single<GetProducts> { GetProductsImpl(get()) }

}