package br.com.dafle.alodjinha.koin

import br.com.dafle.alodjinha.business.HomeBusinnes
import br.com.dafle.alodjinha.business.ProductBusinnes
import br.com.dafle.alodjinha.ui.home.HomeViewModel
import br.com.dafle.alodjinha.ui.main.navigation.NavigationDrawerViewModel
import br.com.dafle.alodjinha.ui.products.ProductsViewModel
import br.com.dafle.alodjinha.ui.products.details.ProductDetailsViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object Modules {

    val appModule = module {
        single { CreateModules.provideGson() }
        single { CreateModules.provideRetrofitInterface(get()) }
        single { CreateModules.provideCoinsService(get()) }
        single { CreateModules.provideHomeService(get()) }
    }

    val businnesModule = module {
        single { ProductBusinnes(get()) }
        single { HomeBusinnes(get()) }
    }

    val viewModelModule = module {
        viewModel { NavigationDrawerViewModel(get()) }
        viewModel { HomeViewModel(get(), get(), get()) }
        viewModel { ProductsViewModel(get(), get()) }
        viewModel { ProductDetailsViewModel(get(), get()) }
    }
}