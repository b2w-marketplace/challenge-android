package br.com.b2w.lodjinha.base

import br.com.b2w.lodjinha.Api
import br.com.b2w.lodjinha.HomeViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module.module
import org.koin.androidx.viewmodel.ext.koin.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyInjection {
    private const val BASE_URL = "https://alodjinha.herokuapp.com/"

    val homeModule = module(override = true) {
        viewModel { HomeViewModel(get()) }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            retrofit.create(Api::class.java)
        }
    }
}