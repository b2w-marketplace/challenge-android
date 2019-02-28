package com.caio.lodjinha.di

import com.caio.lodjinha.home.adapter.BannerPagerAdapter
import com.caio.lodjinha.home.adapter.CategoriesAdapter
import com.caio.lodjinha.home.adapter.ProductsMoreSallersAdapter
import com.caio.lodjinha.repository.BannerRepository
import com.caio.lodjinha.repository.CategoryRepository
import com.caio.lodjinha.repository.ProductRepository
import com.caio.lodjinha.repository.RemoteConstant
import com.caio.lodjinha.repository.remote.BannerREST
import com.caio.lodjinha.repository.remote.CategoryREST
import com.caio.lodjinha.repository.remote.ProductREST
import com.caio.lodjinha.viewmodel.HomeViewModel
import com.caio.lodjinha.viewmodel.ProductDetailViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyInjection {

    val homeModule = module(override = true) {
        viewModel { HomeViewModel(get(), get(), get()) }
        factory { BannerPagerAdapter(get()) }
        factory { ProductsMoreSallersAdapter() }
        factory { CategoriesAdapter() }
    }

    val productDetailModule = module(override = true){
        viewModel { ProductDetailViewModel(get()) }
    }

    val repositoryModule = module {
        single { ProductRepository(get()) }
        single { CategoryRepository(get()) }
        single { BannerRepository(get()) }
    }

    val networkModule = module(override = true) {
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl(RemoteConstant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(get())
                .build()
            retrofit.create(BannerREST::class.java)
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl(RemoteConstant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(get())
                .build()
            retrofit.create(ProductREST::class.java)
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl(RemoteConstant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(get())
                .build()
            retrofit.create(CategoryREST::class.java)
        }
        single {
            OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }
    }
}