package br.com.android.seiji.mobileui.di.module

import br.com.android.seiji.data.store.banners.BannersDataRepository
import br.com.android.seiji.data.store.category.CategoriesDataRepository
import br.com.android.seiji.data.store.product.ProductsDataRepository
import br.com.android.seiji.domain.repository.BannerRepository
import br.com.android.seiji.domain.repository.CategoryRepository
import br.com.android.seiji.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindBannersDataRepository(dataRepository: BannersDataRepository): BannerRepository

    @Binds
    abstract fun bindCategoriesDataRepository(dataRepository: CategoriesDataRepository): CategoryRepository

    @Binds
    abstract fun bindProductsDataRepository(dataRepository: ProductsDataRepository): ProductRepository
}