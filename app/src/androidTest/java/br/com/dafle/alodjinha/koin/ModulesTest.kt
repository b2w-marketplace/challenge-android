package br.com.dafle.alodjinha.koin

import br.com.dafle.alodjinha.business.ProductBusinnes
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object ModulesTest {

    val appModule = module {
        single { CreateModulesTest.provideGson() }
        single { CreateModulesTest.provideRetrofitInterface(get()) }
        single { CreateModulesTest.provideCoinsService(get()) }
    }

    val businnesModule = module {
        single { ProductBusinnes(get()) }
    }

    val viewModelModule= module {
        viewModel { MainViewModel(get()) }
    }
}