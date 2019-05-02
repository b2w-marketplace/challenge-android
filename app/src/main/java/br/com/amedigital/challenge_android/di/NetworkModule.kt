package br.com.amedigital.challenge_android.di

import androidx.annotation.NonNull
import br.com.amedigital.challenge_android.api.*
import br.com.amedigital.challenge_android.api.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://alodjinha.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideBannerService(@NonNull retrofit: Retrofit): BannerService {
        return retrofit.create(BannerService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoriaService(@NonNull retrofit: Retrofit): CategoriaService {
        return retrofit.create(CategoriaService::class.java)
    }

    @Provides
    @Singleton
    fun provideProdutoService(@NonNull retrofit: Retrofit): ProdutoService {
        return retrofit.create(ProdutoService::class.java)
    }
}