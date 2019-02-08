package com.caio.lodjinha.di

import com.caio.lodjinha.viewmodel.BannerViewModel
import com.caio.lodjinha.viewmodel.CategoryViewModel
import com.caio.lodjinha.viewmodel.ProductViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RemoteModule::class))
@Singleton interface AppComponent {
    fun inject(productViewModel: ProductViewModel)

    fun inject(bannerViewModel: BannerViewModel)

    fun inject(categoryViewModel: CategoryViewModel)

    fun inject(app: ApplicationBase)
}
