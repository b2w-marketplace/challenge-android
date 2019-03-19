package com.danilodequeiroz.lodjinha.di

import com.danilodequeiroz.lodjinha.common.BaseApplication
import com.danilodequeiroz.lodjinha.home.domain.HomeInteractor
import com.danilodequeiroz.lodjinha.home.domain.HomeUseCase
import com.danilodequeiroz.lodjinha.home.presentation.HomeViewModel
import com.danilodequeiroz.lodjinha.productdetail.domain.ProductDetailInteractor
import com.danilodequeiroz.lodjinha.productdetail.domain.ProductDetailUseCase
import com.danilodequeiroz.lodjinha.productdetail.presentation.ProductDetailViewModel
import com.danilodequeiroz.lodjinha.productlist.domain.ProductsListListInteractor
import com.danilodequeiroz.lodjinha.productlist.domain.ProductsListUseCase
import com.danilodequeiroz.lodjinha.productlist.presentation.ProductListViewModel
import com.danilodequeiroz.webapi.LodjinhaMockRepository
import com.danilodequeiroz.webapi.LodjinhaRestRepository
import com.danilodequeiroz.webapi.providesMockRetrofitClient
import com.danilodequeiroz.webapi.providesRetrofitClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


const val SCHEDULER_MAIN_THREAD = "mainThread"
const val SCHEDULER_IO = "io"


val presentationModule = module {
    single {
        if (BaseApplication.mock)
            mockRepository()
        else
            realRepository()
    }

    factory<Scheduler>(SCHEDULER_MAIN_THREAD) { AndroidSchedulers.mainThread() }
    factory(SCHEDULER_IO) { Schedulers.io() }

    factory<HomeUseCase> { HomeInteractor(get()) }
    factory<ProductsListUseCase> { ProductsListListInteractor(get()) }
    factory<ProductDetailUseCase> { ProductDetailInteractor(get()) }

    viewModel { HomeViewModel(get(), get(SCHEDULER_IO), get(SCHEDULER_MAIN_THREAD)) }
    viewModel { ProductListViewModel(get(), get(SCHEDULER_IO), get(SCHEDULER_MAIN_THREAD)) }
    viewModel { ProductDetailViewModel(get(), get(SCHEDULER_IO), get(SCHEDULER_MAIN_THREAD)) }
}


fun realRepository() = providesRetrofitClient().create(LodjinhaRestRepository::class.java)
fun mockRepository(): LodjinhaRestRepository = LodjinhaMockRepository()