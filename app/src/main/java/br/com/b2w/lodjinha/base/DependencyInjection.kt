package br.com.b2w.lodjinha.base

import androidx.lifecycle.MutableLiveData
import br.com.b2w.lodjinha.Api
import br.com.b2w.lodjinha.HomeViewModel
import br.com.b2w.lodjinha.features.product.data.LoadingState
import br.com.b2w.lodjinha.features.product.data.ProductsFactory
import br.com.b2w.lodjinha.features.product.presentation.ProductsAdapter
import br.com.b2w.lodjinha.features.product.presentation.ProductsViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import org.koin.androidx.viewmodel.ext.koin.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyInjection {
    private const val BASE_URL = "https://alodjinha.herokuapp.com/"

    val productsModule = module {
        single { ProductsAdapter() }
        viewModel { ProductsViewModel(get(), get()) }
        single { ProductsFactory(get(), get()) }
        single { MutableLiveData<LoadingState>() }
    }

    val homeModule = module(override = true) {
        viewModel { HomeViewModel(get()) }
    }

    val networkModule = module(override = true) {
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(get())
                .build()
            retrofit.create(Api::class.java)
        }
        single {
            OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build()
        }
    }
}