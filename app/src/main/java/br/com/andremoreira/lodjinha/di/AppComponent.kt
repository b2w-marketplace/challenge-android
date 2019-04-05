package br.com.andremoreira.lodjinha.di


import br.com.andremoreira.lodjinha.viewmodel.BannerViewModel
import br.com.andremoreira.lodjinha.viewmodel.CategoryViewModel
import br.com.andremoreira.lodjinha.viewmodel.ProductViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RemoteModule::class))
@Singleton interface AppComponent {
    fun inject(productViewModel: ProductViewModel)

    fun inject(bannerViewModel: BannerViewModel)

    fun inject(categoryViewModel: CategoryViewModel)

    fun inject(app: ApplicationBase)
}
